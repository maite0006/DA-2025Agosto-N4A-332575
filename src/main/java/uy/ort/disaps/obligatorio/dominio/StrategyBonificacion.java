package uy.ort.disaps.obligatorio.dominio;

public abstract class StrategyBonificacion {
    public abstract double  aplicarBonificacion(Puesto puesto, Propietario propietario, Transito transito);
}
