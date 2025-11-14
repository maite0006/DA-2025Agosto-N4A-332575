package uy.ort.disaps.obligatorio.dominio;

import java.util.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

public class SBonificacionTrabajador extends StrategyBonificacion {

    @Override
    public double aplicarBonificacion(double montoO,  Date fecha, boolean frecuento) {
        LocalDate localDate = fecha.toInstant()
                               .atZone(ZoneId.systemDefault())
                               .toLocalDate();

        DayOfWeek dia = localDate.getDayOfWeek();
        if (dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
            return montoO*0.2;
        }
        return montoO;
    }
    
    
    
}
