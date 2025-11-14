package uy.ort.disaps.obligatorio.dominio;

import java.util.Date;

public class SBonificacionExonerado extends StrategyBonificacion {

    @Override
    public double aplicarBonificacion(double montoO,  Date fecha, boolean frecuento) {
        return 0;
    }
   
    
}
