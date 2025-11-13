package uy.ort.disaps.obligatorio.dominio;

import java.sql.Date;

public abstract class StrategyBonificacion {
    public abstract double  aplicarBonificacion(Puesto puesto, Propietario propietario, Vehiculo vehiculo, Date fecha);
}
