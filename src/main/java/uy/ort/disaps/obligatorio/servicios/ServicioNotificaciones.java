package uy.ort.disaps.obligatorio.servicios;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.NotificacionDTO;
import uy.ort.disaps.obligatorio.DTOs.Mappers.NotiMapper;
import uy.ort.disaps.obligatorio.dominio.Notificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;

public class ServicioNotificaciones {
    ArrayList<Notificacion> notificaciones= new ArrayList<Notificacion>();

    
    public void crearNotificacion(Propietario prop, String mensaje, Date f){
        Notificacion nueva= new Notificacion(prop,mensaje, f);
        notificaciones.add(nueva);
        fachada.getInstancia().avisar(fachada.eventos.Notificacion);
    }
    public List<Notificacion> getNotificaciones(Propietario prop){
        List<Notificacion> notis= new ArrayList<>();
        for (Notificacion n: notificaciones){
            if(n.getPropietario().equals(prop)){
                notis.add(n);
            }
        }
        return notis;
    }

    public List<NotificacionDTO> getNotificacionesDTO(Propietario prop){
        List<Notificacion> notis= getNotificaciones(prop);
        return NotiMapper.fromNotis(notis);
    }

    public void eliminarNotis(Propietario propActual) throws PeajeExcepcion{
         List<Notificacion> notis= getNotificaciones(propActual);
        if(notis.isEmpty()) throw new PeajeExcepcion("No hay notificaciones para borrar");
        notificaciones.removeIf(n -> n.getPropietario().equals(propActual));
        fachada.getInstancia().avisar(fachada.eventos.Notificacion);
    }

}
