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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Propietario)) return false;
        Propietario p = (Propietario) o;
        return cedula == p.cedula;
    }
    public int hashCode() {
         return Integer.hashCode(cedula);
    }

    public enum ResultadoCobro {
        SALDO_INSUFICIENTE,
        COBRO_OK,
        COBRO_OK_SALDO_MINIMO
    }
    
    public ResultadoCobro cobrarTransito(double monto) {

        if (saldoActual < monto) {
            return ResultadoCobro.SALDO_INSUFICIENTE;
        }

        saldoActual -= monto;

        if (saldoActual <= saldoMinimo) {
            return ResultadoCobro.COBRO_OK_SALDO_MINIMO;
        }

        return ResultadoCobro.COBRO_OK;
    }
}
