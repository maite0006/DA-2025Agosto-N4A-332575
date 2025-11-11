package uy.ort.disaps.obligatorio.dominio;

public class SBonificacionFrecuente extends StrategyBonificacion {
    @Override
    public double aplicarBonificacion(Puesto puesto, Propietario propietario, Transito transito) {
        return 1;
    }
    
}
