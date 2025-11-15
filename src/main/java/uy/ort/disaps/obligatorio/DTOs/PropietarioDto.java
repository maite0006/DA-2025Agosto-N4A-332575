package uy.ort.disaps.obligatorio.DTOs;

import jakarta.annotation.Generated;
import lombok.Getter;

public class PropietarioDto {
    
    @Getter
    public String nombre;
    @Getter
    public String estado;
    @Getter
    public String saldoA;

    public PropietarioDto(String nombre, String estado, String saldo){
        this.estado=estado;
        this.nombre=nombre;
        this.saldoA=saldo;
    }   

}
