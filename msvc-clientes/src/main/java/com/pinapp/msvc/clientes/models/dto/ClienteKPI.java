package com.pinapp.msvc.clientes.models.dto;

public class ClienteKPI {
    private double promedioEdad;
    private double desviacionEstandar;

    public ClienteKPI(double promedioEdad, double desviacionEstandar) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandar = desviacionEstandar;
    }

    public double getPromedioEdad() {
        return promedioEdad;
    }

    public void setPromedioEdad(double promedioEdad) {
        this.promedioEdad = promedioEdad;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }
}
