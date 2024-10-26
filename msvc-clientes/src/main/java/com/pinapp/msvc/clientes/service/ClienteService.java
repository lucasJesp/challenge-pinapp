package com.pinapp.msvc.clientes.service;

import com.pinapp.msvc.clientes.models.dto.ClienteConFechaProbableMuerte;
import com.pinapp.msvc.clientes.models.dto.ClienteKPI;
import com.pinapp.msvc.clientes.models.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listar();

    Cliente guardar(Cliente cliente);

    ClienteKPI obtenerKPI();

    List<ClienteConFechaProbableMuerte> listarConFechasProbables();

}
