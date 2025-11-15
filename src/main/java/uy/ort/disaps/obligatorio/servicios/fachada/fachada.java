package uy.ort.disaps.obligatorio.servicios.fachada;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTOA;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTOP;
import uy.ort.disaps.obligatorio.DTOs.VehiculoDTO;
import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;
import uy.ort.disaps.obligatorio.dominio.Notificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.Transito;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.observador.Observable;
import uy.ort.disaps.obligatorio.servicios.ServicioBonificaciones;
import uy.ort.disaps.obligatorio.servicios.ServicioNotificaciones;
import uy.ort.disaps.obligatorio.servicios.ServicioTransitos;
import uy.ort.disaps.obligatorio.servicios.ServicioUsuarios;

public class fachada extends Observable {
    private static fachada instancia;
    private ServicioUsuarios sUsuarios;
    private ServicioBonificaciones sBonificaciones;
    private ServicioTransitos sTransitos;
    private ServicioNotificaciones sNotificaciones;
    public enum eventos{altaNoti, bajaNotis, edicionProp, altaTransito}
    
    private fachada() {
        sUsuarios = new ServicioUsuarios();
        sBonificaciones = new ServicioBonificaciones();
        sTransitos = new ServicioTransitos();
        sNotificaciones= new ServicioNotificaciones();
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
    public List<Puesto> obtenerPuestos(){
        return sTransitos.getPuestos();
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
    public Propietario LoginPropietario(int cedula, String contrasenia) throws PeajeExcepcion {
      return sUsuarios.LoginPropietario(cedula, contrasenia);
       
    }
    public Administrador LoginAdministrador(int cedula, String contrasenia) throws PeajeExcepcion {
        return sUsuarios.LoginAdministrador(cedula, contrasenia);
         
      }
    public void EliminarSesion(Administrador usuario) {
        sUsuarios.EliminarSesion(usuario);
    }
    public List<PuestoDTO> puestosDTOs(){
        return  sTransitos.getPuestosDTOs();
    }
    public List<Notificacion> getNotificaciones(Propietario prop){
        return sNotificaciones.getNotificaciones(prop);
    }
    public TransitoDTOA emularTransito(String matricula, String Puesto, String fechaHora) throws PeajeExcepcion, ParseException{
        return sTransitos.emularTransito(Puesto, matricula, fechaHora);
    }
    public ArrayList<TransitoDTOP> obtenerTransitoDTOs(int cedula){
        return sTransitos.getTransitos(cedula);
    }
    public PropietarioDto obtenerDTOProp(String nombre, String estado, double saldo){
        return sUsuarios.nuevoDTOProp(nombre, estado, saldo);
    }
    public List<VehiculoDTO> obtenerVehiculosDTO(Propietario propActual) {
       return sTransitos.obtenerVDtosXProp(propActual);
    }
	public Bonificacion obtenerBonificacionPropietario(Propietario prop, Puesto p) {
		return sBonificaciones.obtenerBonificacionPropietario(prop, p);
		
	}
    public void crearNotificacion(Propietario prop, String string, Date f) {
         sNotificaciones.crearNotificacion(prop, string, f);
    }

}
