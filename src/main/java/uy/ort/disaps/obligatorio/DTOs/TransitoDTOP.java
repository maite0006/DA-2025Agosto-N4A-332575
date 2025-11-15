package uy.ort.disaps.obligatorio.DTOs;

import jakarta.annotation.Generated;
import lombok.Getter;

public class TransitoDTOP{
    
    @Getter
    public int Cedula;
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
    public String total;
    @Getter
    public String saldoActual;
    @Getter 
    public String FechaHora;
    @Getter 
    public String descuento;
    public TransitoDTOP(int cedula, String puesto, String matricula, String categoria, String tarifa,
            String bonificacion, String total, String saldoActual, String fechaHora, String descuento) {
        Cedula = cedula;
        this.puesto = puesto;
        this.matricula = matricula;
        this.categoria = categoria;
        this.tarifa = tarifa;
        this.bonificacion = bonificacion;
        this.total = total;
        this.saldoActual = saldoActual;
        FechaHora = fechaHora;
        this.descuento = descuento;
    }

}