package com.pinapp.msvc.clientes.controllers.response;

import com.pinapp.msvc.clientes.models.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ClienteResponse {
    @Schema(description = "Identificador del cliente", example = "8")
    private Long id;

    @Schema(description = "Nombre del cliente", example = "Fernando")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Gutierrez")
    private String apellido;

    @Schema(description = "Edad del cliente", example = "25")
    private Integer edad;

    @Schema(description = "Fecha de nacimiento del cliente", example = "1999-10-22")
    private LocalDate fechaNacimiento;

    public ClienteResponse() {
    }

    public ClienteResponse(Long id, String nombre, String apellido, Integer edad, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
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

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public static ClienteResponse fromEnity(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getApellido(),
                cliente.getEdad(), cliente.getFechaNacimiento());
    }
}