package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
public class Administrador {
    @Getter
    private String nombreCompleto;
    @Getter
    private String contrasenia;
    @Getter
    private Long cedula;
}
