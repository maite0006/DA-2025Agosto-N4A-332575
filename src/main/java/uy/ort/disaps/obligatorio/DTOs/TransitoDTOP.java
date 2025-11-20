package uy.ort.disaps.obligatorio.DTOs;

import jakarta.annotation.Generated;
import lombok.Getter;

public class TransitoDTOP{
    
    
    @Getter
    public String puesto;
    @Getter
    public String matricula;
    @Getter
    public String categoria;
    @Getter
    public String tarifa;
    @Getter
    public String bonificacion;
    @Getter
    public String descuento;
    @Getter
    public String totalF;
    @Getter 
    public String FechaHora;
    
    public TransitoDTOP( String puesto, String matricula, String categoria, String tarifa,
            String bonificacion, String total, String fechaHora, String descuento) {
        this.puesto = puesto;
        this.matricula = matricula;
        this.categoria = categoria;
        this.tarifa = tarifa;
        this.bonificacion = bonificacion;
        this.descuento = descuento;
        this.totalF = total;
        FechaHora = fechaHora;
    }

}