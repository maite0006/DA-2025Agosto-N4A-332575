package uy.ort.disaps.obligatorio.DTOs;

import java.util.List;

import jakarta.annotation.Generated;
import lombok.Getter;

public class PuestoDTO {
    @Getter
    private String nombre;
    @Getter
    private List<TarifaDTO> tarifas;
    @Getter
    private String direccion;

    public PuestoDTO(String nombre, List<TarifaDTO> tarifas, String direccion) {
        this.nombre = nombre;
        this.tarifas = tarifas;
        this.direccion=direccion;
    }
}

