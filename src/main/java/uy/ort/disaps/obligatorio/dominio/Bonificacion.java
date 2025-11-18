package uy.ort.disaps.obligatorio.dominio;

import java.util.Date;

import lombok.Getter;

public class Bonificacion {
    @Getter
    public String nombre;
    public StrategyBonificacion strategyBonificacion;

    public Bonificacion(String nombre, StrategyBonificacion strategyBonificacion) {
        this.nombre = nombre;
        this.strategyBonificacion = strategyBonificacion;
    }

    public double aplicar(Date fecha, boolean frecuenta, double montoO) {
        return strategyBonificacion.aplicarBonificacion(montoO, fecha, frecuenta);
    }
}
