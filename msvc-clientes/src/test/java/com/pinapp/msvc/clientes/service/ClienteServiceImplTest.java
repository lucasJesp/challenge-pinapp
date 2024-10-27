package com.pinapp.msvc.clientes.service;

import com.pinapp.msvc.clientes.exceptions.ClienteDuplicadoException;
import com.pinapp.msvc.clientes.exceptions.DomainException;
import com.pinapp.msvc.clientes.models.dto.ClienteConFechaProbableMuerte;
import com.pinapp.msvc.clientes.models.dto.ClienteKPI;
import com.pinapp.msvc.clientes.models.entity.Cliente;
import com.pinapp.msvc.clientes.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {

    private ClienteServiceImpl clienteService;
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    void deberia_guardar_cliente() {
        Cliente cliente = new Cliente("Fernando", "Perez", 31, LocalDate.of(1993, 1, 1));

        when(clienteRepository.findByNombreAndApellidoAndEdadAndFechaNacimiento(
                cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getFechaNacimiento()
        )).thenReturn(Optional.empty());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.guardar(cliente);

        assertNotNull(result);
        assertEquals(cliente.getNombre(), result.getNombre());
        assertEquals(cliente.getApellido(), result.getApellido());
        assertEquals(cliente.getEdad(), result.getEdad());

    }


    @Test
    void deberia_fallar_por_cliente_duplicado() {
        Cliente cliente = new Cliente("John", "Doe", 31, LocalDate.of(1993, 1, 1));

        when(clienteRepository.findByNombreAndApellidoAndEdadAndFechaNacimiento(
                cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getFechaNacimiento()
        )).thenReturn(Optional.of(cliente));

        assertThrows(ClienteDuplicadoException.class, () -> clienteService.guardar(cliente));
    }

    @Test
    void deberia_fallar_por_edad_incorrecta() {
        Cliente cliente = new Cliente("John", "Doe", 30, LocalDate.of(1993, 1, 1));

        assertThrows(DomainException.class, () -> clienteService.guardar(cliente));
    }


    @Test
    void deberia_obtener_kpi() {

        List<Cliente> clientes = List.of(
                new Cliente("John", "Doe", 30, LocalDate.of(1993, 1, 1)),
                new Cliente("Jane", "Doe", 40, LocalDate.of(1983, 1, 1))
        );

        when(clienteRepository.findAll()).thenReturn(clientes);

        ClienteKPI kpi = clienteService.obtenerKPI();

        assertNotNull(kpi);
        assertEquals(35.0, kpi.getPromedioEdad());
        assertEquals(5.0, kpi.getDesviacionEstandar());
    }

    @Test
    void deberia_listar_clientes_con_fechas_probables() {

        List<Cliente> clientes = List.of(
                new Cliente("John", "Doe", 30, LocalDate.of(1993, 1, 1)),
                new Cliente("Jane", "Doe", 40, LocalDate.of(1983, 1, 1))
        );

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteConFechaProbableMuerte> result = clienteService.listarConFechasProbables();

        assertNotNull(result);
        assertEquals(clientes.size(), result.size());

        assertEquals(LocalDate.of(2073, 1, 1), result.get(0).getFechaProbableMuerte());
        assertEquals(LocalDate.of(2063, 1, 1), result.get(1).getFechaProbableMuerte());
    }

}
