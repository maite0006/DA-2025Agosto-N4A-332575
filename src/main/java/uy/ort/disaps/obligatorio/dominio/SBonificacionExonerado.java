package uy.ort.disaps.obligatorio.dominio;

public class SBonificacionExonerado extends StrategyBonificacion {
    @Override
    public double aplicarBonificacion(Puesto puesto, Propietario propietario, Transito transito) {
        return 1;
    }
    
}
