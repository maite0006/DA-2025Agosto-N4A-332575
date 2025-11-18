package uy.ort.disaps.obligatorio.dominio;

public abstract class EstadoPropietario {
    public abstract boolean puedeIngresar();
    public abstract boolean puedeTransitar();
    public abstract boolean recibeNotificaciones();
    public abstract boolean aplicaBonificaciones();
    public abstract boolean asignaBonificaciones();
    public abstract String nombreEstado();
}
