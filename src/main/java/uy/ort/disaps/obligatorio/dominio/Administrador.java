package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
public class Administrador {
    @Getter
    private String nombreCompleto;
    @Getter
    private String contrasenia;
    @Getter
    private int cedula;

    public Administrador(int cedula, String contrasenia,String nombreCompleto ) {
        this.nombreCompleto = nombreCompleto;
        this.contrasenia = contrasenia;
        this.cedula = cedula;
    }
}
