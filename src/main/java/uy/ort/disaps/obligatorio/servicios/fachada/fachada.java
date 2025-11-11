package uy.ort.disaps.obligatorio.servicios.fachada;

import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Transito;
import uy.ort.disaps.obligatorio.servicios.ServicioBonificaciones;
import uy.ort.disaps.obligatorio.servicios.ServicioTransitos;
import uy.ort.disaps.obligatorio.servicios.ServicioUsuarios;

public class fachada {
    private static fachada instancia;
    private ServicioUsuarios sUsuarios;
    private ServicioBonificaciones sBonificaciones;
    private ServicioTransitos sTransitos;

    private fachada() {
        sUsuarios = new ServicioUsuarios();
        sBonificaciones = new ServicioBonificaciones();
        sTransitos = new ServicioTransitos();
    }
    public static fachada getInstancia() {
        if (instancia == null) {
            instancia = new fachada();
        }
        return instancia;
    }
    public void agregar(Propietario usuario) {
        sUsuarios.agregar(usuario);
    }
    public void agregarAdministrador(uy.ort.disaps.obligatorio.dominio.Administrador adm) {
        sUsuarios.agregar(adm);
    }
    public void agregarPuesto(uy.ort.disaps.obligatorio.dominio.Puesto puesto) {
        sTransitos.agregarPuesto(puesto);
    }
    public void agregarCategoria(uy.ort.disaps.obligatorio.dominio.Categoria categoria) {
        sTransitos.agregarCategoria(categoria);
    }
    public void agregarTarifa(uy.ort.disaps.obligatorio.dominio.Tarifa tarifa) {
        sTransitos.agregarTarifa(tarifa);
    }   
    public void agregarVehiculo(uy.ort.disaps.obligatorio.dominio.Vehiculo vehiculo) {
        sTransitos.agregarVehiculo(vehiculo);
    }   
    public void agregarBonificacion(uy.ort.disaps.obligatorio.dominio.Bonificacion bonificacion) {
        sBonificaciones.agregarBonificacion(bonificacion);
    }
   


}
