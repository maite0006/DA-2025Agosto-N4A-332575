package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
public class AsignacionBonificacion {
    @Getter
    private Bonificacion bonificacion;
    @Getter
    private Vehiculo vehiculo;
    @Getter
    private String fechaAsignacion;
    @Getter
    private Puesto puesto;
}
