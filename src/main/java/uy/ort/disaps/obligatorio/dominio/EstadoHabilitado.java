package uy.ort.disaps.obligatorio.dominio;

public class EstadoHabilitado extends EstadoPropietario {
    @Override
    public boolean puedeIngresar() {
        return true;
    }

    @Override
    public boolean puedeTransitar() {
        return true;
    }

    @Override
    public boolean recibeNotificaciones() {
        return true;
    }

    @Override
    public boolean aplicaBonificaciones() {
        return true;
    }
    @Override
    public  boolean asignaBonificaciones(){
        return true;
    }

    @Override
    public String nombreEstado() {
        return "Habilitado";
    }
    
}
