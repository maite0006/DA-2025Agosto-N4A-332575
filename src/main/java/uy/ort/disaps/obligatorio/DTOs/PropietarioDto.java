package uy.ort.disaps.obligatorio.DTOs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.Setter;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;

public class PropietarioDto {
    
    @Getter
    public String nombre;
    @Getter
    public String estado;
    @Getter
    public String saldoA;
    @Getter  @Setter
    public ArrayList<AsignacionBoniDTO> asignadas= new ArrayList<AsignacionBoniDTO>();

    public PropietarioDto(String nombre, String estado, String saldo){
        this.estado=estado;
        this.nombre=nombre;
        this.saldoA=saldo;
    }   
     

}
