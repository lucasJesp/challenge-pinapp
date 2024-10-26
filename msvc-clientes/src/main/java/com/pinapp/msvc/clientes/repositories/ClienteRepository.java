package com.pinapp.msvc.clientes.repositories;

import com.pinapp.msvc.clientes.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByNombreAndApellidoAndEdadAndFechaNacimiento(String nombre, String apellido, Integer edad, LocalDate fechaNacimiento);
}
