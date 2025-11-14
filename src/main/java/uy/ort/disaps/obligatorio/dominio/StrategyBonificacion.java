package uy.ort.disaps.obligatorio.dominio;

import java.util.Date;

public abstract class StrategyBonificacion {
    public abstract double  aplicarBonificacion(double montoO,  Date fecha, boolean frecuento);
}
