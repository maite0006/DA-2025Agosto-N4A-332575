package uy.ort.disaps.obligatorio.dominio;

import java.util.Date;

public class Bonificacion {
    public String nombre;
    public StrategyBonificacion strategyBonificacion;
    public Puesto exonerado;

    public Bonificacion(String nombre, StrategyBonificacion strategyBonificacion, Puesto puestoExonerado) {
        this.nombre = nombre;
        this.strategyBonificacion = strategyBonificacion;
        this.exonerado=puestoExonerado;
    }

    public double aplicar(Date fecha, boolean frecuenta, double montoO) {
        return strategyBonificacion.aplicarBonificacion(montoO, fecha, frecuenta);
    }
}
