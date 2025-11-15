package uy.ort.disaps.obligatorio.dominio;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;

public class Notificacion {
    @Getter
    public Date fechaHora;
    @Getter
    public String mensaje;
    @Getter
    public Propietario propietario;

    public Notificacion(Propietario prop, String mensaje, Date f){
        propietario=prop;
        this.mensaje=mensaje;
        fechaHora=f;
    }
    
}
