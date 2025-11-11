package uy.ort.disaps.obligatorio.dominio;
import java.util.List;

import lombok.Getter;

public class Propietario {
   
    @Getter
    private Long cedula;
    @Getter
    private String contrasenia;
    @Getter
    private String nombreCompleto;
    @Getter
    private double saldoActual;
    @Getter
    private double saldoMinimo;
    @Getter
    private String estado;//enum
}
