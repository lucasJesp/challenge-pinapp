package com.pinapp.msvc.clientes.controllers.request;


import com.pinapp.msvc.clientes.models.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import static com.pinapp.msvc.clientes.models.entity.RegexPatterns.NOMBRE_APELLIDO_REGEX;
import static com.pinapp.msvc.clientes.util.Messages.*;

public class ClienteRequest {

    @NotBlank(message = NOMBRE_OBLIGATORIO)
    @Size(min = 2, max = 50, message = NOMBRE_LONGITUD_INCORRECTA)
    @Pattern(regexp = NOMBRE_APELLIDO_REGEX, message = NOMBRE_INVALIDO)
    @Schema(description = "Nombre del cliente", example = "Fernando")
    private String nombre;

    @NotBlank(message = APELLIDO_OBLIGATORIO)
    @Size(min = 2, max = 50, message = APELLIDO_LONGITUD_INCORRECTA)
    @Pattern(regexp = NOMBRE_APELLIDO_REGEX, message = APELLIDO_INVALIDO)
    @Schema(description = "Apellido del cliente", example = "Gutierrez")
    private String apellido;

    @NotNull(message = EDAD_OBLIGATORIA)
    @Min(value = 0, message = "La edad debe ser un número no negativo")
    @Schema(description = "Edad del cliente", example = "25")
    private Integer edad;

    @NotNull(message = FECHA_NACIMIENTO_OBLIGATORIA)
    @Past(message = FECHA_NACIMIENTO_PASADA)
    private LocalDate fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente toEntity() {
        return new Cliente(this.nombre, this.apellido, this.edad, this.fechaNacimiento);
    }
}
