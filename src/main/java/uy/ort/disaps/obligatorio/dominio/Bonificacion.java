package uy.ort.disaps.obligatorio.dominio;

import java.sql.Date;

public class Bonificacion {
    public String nombre;
    public StrategyBonificacion strategyBonificacion;
    public Puesto exonerado;

    public Bonificacion(String nombre, StrategyBonificacion strategyBonificacion, Puesto puestoExonerado) {
        this.nombre = nombre;
        this.strategyBonificacion = strategyBonificacion;
        this.exonerado=puestoExonerado;
    }

    public double aplicar(Propietario propietario, Vehiculo vehiculo, Puesto puesto, Date fecha) {
        return strategyBonificacion.aplicarBonificacion(puesto, propietario, vehiculo, fecha);
    }
}
