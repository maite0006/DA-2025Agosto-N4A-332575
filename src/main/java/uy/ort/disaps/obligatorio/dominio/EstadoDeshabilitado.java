package uy.ort.disaps.obligatorio.dominio;

public class EstadoDeshabilitado extends EstadoPropietario {
    @Override
    public boolean puedeIngresar() {
        return false;
    }

    @Override
    public boolean puedeTransitar() {
        return false;
    }

    @Override
    public boolean recibeNotificaciones() {
        return true;
    }

    @Override
    public boolean aplicaBonificaciones() {
        return false;
    }
    @Override
    public  boolean asignaBonificaciones(){
        return false;
    }

    @Override
    public String nombreEstado() {
        return "Deshabilitado";
    }
    
}
