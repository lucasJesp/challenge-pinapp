package com.pinapp.msvc.clientes.exceptions;

import java.io.Serial;

public class DomainException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 367719361656200774L;

    public DomainException(String mensaje) {
        super(mensaje);
    }
}
