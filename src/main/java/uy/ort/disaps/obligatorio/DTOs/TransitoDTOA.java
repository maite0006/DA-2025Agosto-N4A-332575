package uy.ort.disaps.obligatorio.DTOs;

import lombok.Getter;

public class TransitoDTOA {
    @Getter
    public String propietario;
    @Getter
    public String estadoP;
    @Getter
    public String categoria;
    @Getter
    public String bonificacion;
     @Getter
    public String costo;
     @Getter
    public String saldo;

     public TransitoDTOA(String propietario, String estadoP, String cate, String bonificacion, String costo, String saldo) {
        this.propietario = propietario;
        this.estadoP = estadoP;
        this.categoria=cate;
        this.bonificacion = bonificacion;
        this.costo = costo;
        this.saldo = saldo;
     }

    
}
