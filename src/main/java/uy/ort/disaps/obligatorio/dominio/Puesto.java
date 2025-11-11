package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
public class Puesto {
    @Getter
    private String nombre;
    @Getter
    private String direccion;
    @Getter
    private Tarifa tarifa;

}
