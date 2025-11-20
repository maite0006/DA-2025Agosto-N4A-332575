package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.EstadoDTO;
import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.DTOs.Mappers.EstadoMapper;
import uy.ort.disaps.obligatorio.DTOs.Mappers.PropMapper;
import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.EstadoDeshabilitado;
import uy.ort.disaps.obligatorio.dominio.EstadoHabilitado;
import uy.ort.disaps.obligatorio.dominio.EstadoPenalizado;
import uy.ort.disaps.obligatorio.dominio.EstadoPropietario;
import uy.ort.disaps.obligatorio.dominio.EstadoSuspendido;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.SesionAdm;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;

public class ServicioUsuarios {
    private List<Propietario> propietarios;
    private List<Administrador> administradores;
    private ArrayList<Administrador> admActivos= new ArrayList<>();
    private List<EstadoPropietario> estadosDisponibles = List.of(
        new EstadoHabilitado(),
        new EstadoDeshabilitado(),
        new EstadoPenalizado(),
        new EstadoSuspendido()
    );
    
    public ServicioUsuarios() {
        this.propietarios = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }
    public void agregar(Propietario propietario) {
        propietarios.add(propietario);
    }

    public void agregar(Administrador adm) {
        administradores.add(adm);
    }

    public Propietario LoginPropietario(int cedula, String contrasenia) throws PeajeExcepcion {
        Propietario p = buscarPropietarioPorCedula(cedula);
        if (p == null || !p.getContrasenia().equals(contrasenia)) {
            throw new PeajeExcepcion("Acceso denegado.");
        }
        if(!p.puedeIngresar()) {
            throw new PeajeExcepcion("Usuario deshabilitado, no puede ingresar al sistema");
        }
        return p;

    }

    public Administrador LoginAdministrador(int cedula, String contrasenia) throws PeajeExcepcion {
        Administrador a = buscarAdministradorPorCedula(cedula);
        if (a == null || !a.getContrasenia().equals(contrasenia)) {
            throw new PeajeExcepcion("Acceso denegado.");
        }
        if (admActivos.contains(a)) {
            throw new PeajeExcepcion("Ud. Ya está logueado.");
        }
        admActivos.add(a);
        return a;
    }

    
    public void EliminarSesion(Administrador usuario) {
        admActivos.remove(usuario);
    }

    //Obtener
    public Administrador buscarAdministradorPorCedula(int cedula) {
        for (Administrador a : administradores) {
            if (a.getCedula()==cedula) return a;
            
        }
        return null;
    }
    public Propietario buscarPropietarioPorCedula(int cedula) throws PeajeExcepcion {
        for (Propietario p : propietarios) {
            if (p.getCedula()==cedula) return p;
        }
        throw new PeajeExcepcion("no existe el Propietario");
    }
    public List<EstadoPropietario> getEstadosDisponibles() {
        return estadosDisponibles;
    }
    
    //DTOS
    public PropietarioDto PropietarioDTOCompleto(Propietario prop) throws PeajeExcepcion{
        if (prop==null) {
            throw new PeajeExcepcion("no existe el Propietario");
        }
        ArrayList<AsignacionBoniDTO> asign=fachada.getInstancia().AsignacionesDTO(prop);
        return PropMapper.fromProp(prop, asign);

    }
    public List<EstadoDTO> estadosDTO(List<EstadoPropietario> estados) {
        return EstadoMapper.fromEstado(estados);
    }

}
