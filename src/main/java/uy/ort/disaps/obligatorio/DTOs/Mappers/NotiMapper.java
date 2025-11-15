package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.NotificacionDTO;
import uy.ort.disaps.obligatorio.dominio.Notificacion;


public class NotiMapper {
    public static List<NotificacionDTO> fromNotis(List<Notificacion> notis){
         return notis.stream()
                .map(n -> new NotificacionDTO(n.getMensaje(),n.getFechaHora()))
                .toList();
    }
}
