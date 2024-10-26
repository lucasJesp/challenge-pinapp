package com.pinapp.msvc.clientes.exceptions;

import java.io.Serial;

public class ClienteDuplicadoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3677193632656200774L;

    public ClienteDuplicadoException(String mensaje) {
        super(mensaje);
    }

}
