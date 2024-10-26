package com.pinapp.msvc.clientes.models.entity;

public class RegexPatterns {

    private RegexPatterns() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static final String NOMBRE_APELLIDO_REGEX = "^[A-Za-záéíóúÁÉÍÓÚñÑ]+$";
}
