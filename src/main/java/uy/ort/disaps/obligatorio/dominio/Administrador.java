package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
public class Administrador {
    @Getter
    private String nombreCompleto;
    @Getter
    private String contrasenia;
    @Getter
    private Long cedula;

    public Administrador(String nombreCompleto, String contrasenia, Long cedula) {
        this.nombreCompleto = nombreCompleto;
        this.contrasenia = contrasenia;
        this.cedula = cedula;
    }
}
