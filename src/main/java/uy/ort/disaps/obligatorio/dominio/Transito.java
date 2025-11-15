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
    private double total;

    public Transito(Date f, Vehiculo v, Puesto p, double total){
        fecha=f;
        vehiculo=v;
        puesto=p;
        this.total=total;
    }
   
}
