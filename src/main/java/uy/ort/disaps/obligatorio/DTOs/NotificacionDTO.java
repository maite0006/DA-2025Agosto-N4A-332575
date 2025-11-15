package uy.ort.disaps.obligatorio.DTOs;


import java.util.Date;

import lombok.Getter;

public class NotificacionDTO {
    @Getter
    public String mensaje;
    @Getter
    public Date fechaHora;
    public NotificacionDTO(String mensaje, Date f){
        this.mensaje=mensaje;
        fechaHora=f;
    }
}
