package uy.ort.disaps.obligatorio.dominio;
import java.sql.Date;

import lombok.Getter;
public class AsignacionBonificacion {
    @Getter
    private Bonificacion bonificacion;
    @Getter
    private Propietario propietario;
    @Getter
    private Puesto puesto;
    @Getter
    private Date fechaAsignacion;

    public AsignacionBonificacion(Bonificacion bonificacion, Propietario propietario, Puesto puesto) {
        this.bonificacion = bonificacion;
        this.propietario = propietario;
        this.puesto = puesto;
        this.fechaAsignacion = new Date(System.currentTimeMillis()) ;
    }
}
