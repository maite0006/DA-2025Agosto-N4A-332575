package uy.ort.disaps.obligatorio.DTOs;

import lombok.Getter;

public class AsignacionBoniDTO {
    @Getter
    public String nombre;
    @Getter
    public String puesto;
    @Getter
    public String fecha;
    
    public AsignacionBoniDTO(String nombre, String puesto, String fecha) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.fecha = fecha;
    }

    
}
