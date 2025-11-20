package uy.ort.disaps.obligatorio.dominio;
import java.util.Date;

import lombok.Getter;

public class Transito {
    @Getter
    private Date fecha;
    @Getter
    private Vehiculo vehiculo;
    @Getter
    private Puesto puesto;
    @Getter
    private double montoF;
    @Getter
    private double descuentoAplicado;
    @Getter
    private String nombreBonificacion;
    
    public Transito(Date fecha, Vehiculo vehiculo, Puesto puesto, double montoF, double descuentoAplicado,
            String nombreBonificacion) {
        this.fecha = fecha;
        this.vehiculo = vehiculo;
        this.puesto = puesto;
        this.montoF = montoF;
        this.descuentoAplicado = descuentoAplicado;
        this.nombreBonificacion = nombreBonificacion;
    }


   
   
}
