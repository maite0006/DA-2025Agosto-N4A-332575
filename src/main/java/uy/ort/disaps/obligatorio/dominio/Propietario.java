package uy.ort.disaps.obligatorio.dominio;
import java.util.List;

import lombok.Getter;

public class Propietario {
   
    @Getter
    private int cedula;
    @Getter
    private String contrasenia;
    @Getter
    private String nombreCompleto;
    @Getter
    private double saldoActual;
    @Getter
    private double saldoMinimo;
    @Getter
    private EstadoPropietario estado;//enum

    public Propietario(int cedula, String contrasenia,String nombreC, double saldoA, double saldoMinimo) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreC;
        this.saldoActual = saldoA;
        this.saldoMinimo = saldoMinimo;
        this.estado = new EstadoHabilitado(); 
    }
    public boolean puedeIngresar() { return estado.puedeIngresar(); }
    public boolean puedeTransitar() { return estado.puedeTransitar(); }
    public boolean recibeNotificaciones() { return estado.recibeNotificaciones(); }
    public boolean aplicaBonificaciones() { return estado.aplicaBonificaciones(); }

    // Cambiar el estado dinámicamente
    public void cambiarEstado(EstadoPropietario nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String getEstadoNombre() {
        return estado.nombreEstado();
    }
}
