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
import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.BoniDTO;
import uy.ort.disaps.obligatorio.DTOs.EstadoDTO;
import uy.ort.disaps.obligatorio.DTOs.NotificacionDTO;
import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;
import uy.ort.disaps.obligatorio.dominio.Categoria;
import uy.ort.disaps.obligatorio.dominio.EstadoPropietario;
import uy.ort.disaps.obligatorio.dominio.Notificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.Tarifa;
import uy.ort.disaps.obligatorio.dominio.Transito;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;
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

    public enum eventos{Notificacion, edicionProp, altaTransito, asignacionBoni}
    
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
    public void agregarAdministrador(Administrador adm) {
        sUsuarios.agregar(adm);
    }
    public void agregarPuesto(Puesto puesto) {
        sTransitos.agregarPuesto(puesto);
    }
    public List<Puesto> obtenerPuestos(){
        return sTransitos.getPuestos();
    }
    public void agregarCategoria(Categoria categoria) {
        sTransitos.agregarCategoria(categoria);
    }
    public void agregarTarifa(Tarifa tarifa) {
        sTransitos.agregarTarifa(tarifa);
    }   
    public void agregarVehiculo(Vehiculo vehiculo) {
        sTransitos.agregarVehiculo(vehiculo);
    }   
    public void agregarBonificacion(Bonificacion bonificacion) {
        sBonificaciones.agregarBonificacion(bonificacion);
    }
    public void agregarBoniAsignada(AsignacionBonificacion ba){
        sBonificaciones.agregarBonificacionAsignada(ba);
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
    public List<NotificacionDTO> getNotificacionesDTO(Propietario prop){
        return sNotificaciones.getNotificacionesDTO(prop);
    }
    public TransitoDTOA emularTransito(String matricula, String Puesto, String fechaHora) throws PeajeExcepcion, ParseException{
        return sTransitos.emularTransito(Puesto, matricula, fechaHora);
    }
    public ArrayList<TransitoDTOP> obtenerTransitoDTOs(Propietario p){
        return sTransitos.getTransitosDTO(p);
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
    public List<BoniDTO> bonisDTOs() {
        
        return sBonificaciones.getBonisDTOs();
    }
    public PropietarioDto obtenerPropDTO(Propietario p) throws PeajeExcepcion{
        return sUsuarios.PropietarioDTOCompleto(p);
    }
  
    public void asignarBonificacion(String bonificacion, String puesto, Propietario prop) throws PeajeExcepcion {
         sBonificaciones.asignarBoni(bonificacion,puesto,prop);
    }
     public Puesto obtenerPuesto(String puesto) {
       return sTransitos.buscarPuestoPorNombre(puesto);
    }
    public Propietario obtenerProp(int cedula) throws PeajeExcepcion {
        return sUsuarios.buscarPropietarioPorCedula(cedula);
    }
    public ArrayList<AsignacionBoniDTO> AsignacionesDTO(Propietario p) {
       return sBonificaciones.asignacionesDTOs(p);
    }
    public void eliminarNotis(Propietario propActual)throws PeajeExcepcion {
        sNotificaciones.eliminarNotis(propActual);
    }

    public List<EstadoPropietario> obtenerEstados() {
        return sUsuarios.getEstadosDisponibles();
    }
    public List<EstadoDTO> obtenerEstadoDTOs(List<EstadoPropietario> estados){
        return sUsuarios.estadosDTO(estados);
    }

    public String convertirDFecha(Date fecha) {
        return sTransitos.convertirDFecha(fecha);
    }

    public Double totalTransitos(String matricula) {
        return sTransitos.totalTransitos(matricula);
    }

    public int transitosXV(String matricula) {
        return sTransitos.transitosXV(matricula);
    }
}
