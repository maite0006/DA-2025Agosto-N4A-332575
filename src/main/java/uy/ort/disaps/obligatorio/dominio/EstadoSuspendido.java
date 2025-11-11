package uy.ort.disaps.obligatorio.dominio;

public class EstadoSuspendido extends EstadoPropietario {
    @Override
    public boolean puedeIngresar() {
        return true;
    }

    @Override
    public boolean puedeTransitar() {
        return false;
    }

    @Override
    public boolean recibeNotificaciones() {
        return false; //??
    }

    @Override
    public boolean aplicaBonificaciones() {
        return false;
    }

    @Override
    public String nombreEstado() {
        return "Suspendido";
    }
    
}
