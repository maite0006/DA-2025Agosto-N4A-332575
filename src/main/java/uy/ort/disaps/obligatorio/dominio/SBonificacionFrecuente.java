package uy.ort.disaps.obligatorio.dominio;
import java.util.Date;

public class SBonificacionFrecuente extends StrategyBonificacion {

    @Override
    public double aplicarBonificacion(double montoO,  Date fecha, boolean frecuento) {
      if (frecuento) {
        return montoO/2;
      }
      return montoO;
    }
    
    
}
