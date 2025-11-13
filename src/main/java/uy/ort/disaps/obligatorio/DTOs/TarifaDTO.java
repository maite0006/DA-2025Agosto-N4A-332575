package uy.ort.disaps.obligatorio.DTOs;

import lombok.Getter;

public class TarifaDTO {
    @Getter
    private String categoria;
    @Getter
    private double monto;

    public TarifaDTO(String categoria, double monto) {
        this.categoria = categoria;
        this.monto = monto;
    }
}

