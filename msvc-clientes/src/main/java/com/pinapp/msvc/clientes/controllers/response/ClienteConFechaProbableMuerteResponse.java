package com.pinapp.msvc.clientes.controllers.response;

import com.pinapp.msvc.clientes.models.dto.ClienteConFechaProbableMuerte;
import com.pinapp.msvc.clientes.models.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ClienteConFechaProbableMuerteResponse {

    @Schema(description = "Identificador de la persona", example = "8")
    private final Long id;

    @Schema(description = "Nombre de la persona", example = "Fernando")
    private final String nombre;

    @Schema(description = "Apellido de la persona", example = "Gutierrez")
    private final String apellido;

    @Schema(description = "Edad de la persona", example = "25")
    private final Integer edad;

    @Schema(description = "Fecha de nacimiento de la persona", example = "1999-10-22")
    private final LocalDate fechaNacimiento;

    @Schema(description = "Fecha estimada de fallecimiento calculada para la persona", example = "2079-10-22")
    private final LocalDate fechaProbableMuerte;

    public ClienteConFechaProbableMuerteResponse(Long id, String nombre, String apellido, Integer edad, LocalDate fechaNacimiento, LocalDate fechaProbableMuerte) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaProbableMuerte = fechaProbableMuerte;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaProbableMuerte() {
        return fechaProbableMuerte;
    }

    public static ClienteConFechaProbableMuerteResponse fromDTO(ClienteConFechaProbableMuerte dto) {
        Cliente cliente = dto.getCliente();
        return new ClienteConFechaProbableMuerteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEdad(),
                cliente.getFechaNacimiento(),
                dto.getFechaProbableMuerte()
        );
    }
}

