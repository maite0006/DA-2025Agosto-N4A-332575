package uy.ort.disaps.obligatorio.servicios;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.dominio.Notificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;

public class ServicioNotificaciones {
    ArrayList<Notificacion> notificaciones= new ArrayList<Notificacion>();

    
    public void crearNotificacion(Propietario prop, String mensaje, Date f){
        Notificacion nueva= new Notificacion(prop,mensaje, f);
        notificaciones.add(nueva);
        fachada.getInstancia().avisar(fachada.eventos.altaNoti);
    }
    public List<Notificacion> getNotificaciones(Propietario prop){
        return notificaciones.stream()
            .filter(n -> n.getPropietario().equals(prop))
            .toList(); 
    }
}
