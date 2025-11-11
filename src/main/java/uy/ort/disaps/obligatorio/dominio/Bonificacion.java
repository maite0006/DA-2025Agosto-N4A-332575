package uy.ort.disaps.obligatorio.dominio;

public class Bonificacion {
    public String nombre;
    public StrategyBonificacion strategyBonificacion;

    public Bonificacion(String nombre, StrategyBonificacion strategyBonificacion) {
        this.nombre = nombre;
    }
}
