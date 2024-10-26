package com.pinapp.msvc.clientes.controllers.response;

import com.pinapp.msvc.clientes.models.dto.ClienteKPI;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteKPIResponse {

    @Schema(description = "Promedio de edad calculado entre todos los clientes registrados", example = "35.4")
    private final double promedioEdad;

    @Schema(description = "Desviación estándar de las edades de todos los clientes registrados", example = "4.3")
    private final double desviacionEstandar;

    public ClienteKPIResponse(double promedioEdad, double desviacionEstandar) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandar = desviacionEstandar;
    }

    public double getPromedioEdad() {
        return promedioEdad;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public static ClienteKPIResponse fromDTO(ClienteKPI kpi) {
        return new ClienteKPIResponse(kpi.getPromedioEdad(), kpi.getDesviacionEstandar());
    }
}
