package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.NotificacionDTO;
import uy.ort.disaps.obligatorio.dominio.Notificacion;


public class NotiMapper {
    public static List<NotificacionDTO> fromNotis(List<Notificacion> notis){
        List<NotificacionDTO> dtos= new ArrayList<>();
        for(Notificacion n: notis){
           NotificacionDTO noti= new NotificacionDTO(n.getMensaje(),n.getFechaHora());
            dtos.add(noti);
        }
         return dtos;
    }
}
