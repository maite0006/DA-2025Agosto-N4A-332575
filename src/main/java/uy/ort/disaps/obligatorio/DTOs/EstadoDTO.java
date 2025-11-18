package uy.ort.disaps.obligatorio.DTOs;

import jakarta.annotation.Generated;
import lombok.Getter;

public class EstadoDTO {
    @Getter 
    public String Nombre;
    public EstadoDTO(String nombre){
            this.Nombre=nombre;
    }
}
