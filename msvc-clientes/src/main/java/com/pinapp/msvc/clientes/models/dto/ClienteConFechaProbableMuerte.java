package com.pinapp.msvc.clientes.models.dto;

import com.pinapp.msvc.clientes.models.entity.Cliente;

import java.time.LocalDate;

public class ClienteConFechaProbableMuerte {
    private final Cliente cliente;
    private final LocalDate fechaProbableMuerte;

    public ClienteConFechaProbableMuerte(Cliente cliente, LocalDate fechaProbableMuerte) {
        this.cliente = cliente;
        this.fechaProbableMuerte = fechaProbableMuerte;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaProbableMuerte() {
        return fechaProbableMuerte;
    }

}
