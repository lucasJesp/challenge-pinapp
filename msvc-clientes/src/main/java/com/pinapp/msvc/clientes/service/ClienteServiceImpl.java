package com.pinapp.msvc.clientes.service;

import com.pinapp.msvc.clientes.exceptions.ClienteDuplicadoException;
import com.pinapp.msvc.clientes.exceptions.DomainException;
import com.pinapp.msvc.clientes.models.dto.ClienteConFechaProbableMuerte;
import com.pinapp.msvc.clientes.models.dto.ClienteKPI;
import com.pinapp.msvc.clientes.models.entity.Cliente;
import com.pinapp.msvc.clientes.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pinapp.msvc.clientes.util.Messages.CLIENTES_NO_OBTENIDOS;
import static com.pinapp.msvc.clientes.util.Messages.CLIENTE_DUPLICADO;
import static com.pinapp.msvc.clientes.util.Messages.EDAD_INCORRECTA;
import static com.pinapp.msvc.clientes.util.Messages.FECHAS_PROBABLES_NO_OBTENIDAS;
import static com.pinapp.msvc.clientes.util.Messages.KPI_NO_OBTENIDOS;


@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public List<Cliente> listar() {
        try {
            return (List<Cliente>) clienteRepository.findAll();
        } catch (Exception e) {
            throw new DomainException(CLIENTES_NO_OBTENIDOS);
        }
    }

    @Override
    @Transactional
    public Cliente guardar(Cliente cliente) {

        validaEdad(cliente.getEdad(), cliente.getFechaNacimiento());

        Optional<Cliente> clienteExistente = clienteRepository.findByNombreAndApellidoAndEdadAndFechaNacimiento(
                cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getFechaNacimiento()
        );

        if (clienteExistente.isPresent()) {
            throw new ClienteDuplicadoException(CLIENTE_DUPLICADO);
        }

        // Guardar el cliente si no hay duplicados
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteKPI obtenerKPI() {
        try {
            List<Cliente> clientes = listar();
            double promedio = clientes.stream()
                    .mapToInt(Cliente::getEdad)
                    .average()
                    .orElse(0.0);

            double desviacionEstandar = Math.sqrt(clientes.stream()
                    .mapToDouble(cliente -> Math.pow(cliente.getEdad() - promedio, 2))
                    .average()
                    .orElse(0.0));

            return new ClienteKPI(promedio, desviacionEstandar);
        } catch (Exception e) {
            throw new DomainException(KPI_NO_OBTENIDOS);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteConFechaProbableMuerte> listarConFechasProbables() {
        try {
            List<Cliente> clientes = listar();
            List<ClienteConFechaProbableMuerte> clientesConFechas = new ArrayList<>();

            for (Cliente cliente : clientes) {
                LocalDate fechaProbableMuerte = calcularFechaProbableMuerte(cliente.getFechaNacimiento());
                clientesConFechas.add(new ClienteConFechaProbableMuerte(cliente, fechaProbableMuerte));
            }

            return clientesConFechas;

        } catch (Exception e) {

            throw new DomainException(FECHAS_PROBABLES_NO_OBTENIDAS);
        }

    }


    public LocalDate calcularFechaProbableMuerte(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return null;
        }
        return fechaNacimiento.plusYears(80); // Suponiendo una esperanza de vida de 80 años
    }

    private void validaEdad(int edad, LocalDate fechaNacimiento) {

        int edadCalculada = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        if (edadCalculada != edad) {
            throw new DomainException(EDAD_INCORRECTA);
        }
    }

}
