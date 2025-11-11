package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;

public class ServicioUsuarios {
    private List<Propietario> propietarios;
    private List<Administrador> administradores;
    
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
    public Propietario LoginPropietario(Long cedula, String contrasenia) throws PeajeExcepcion {
        Propietario p = buscarPropietarioPorCedula(cedula);
        if (p == null || !p.getContrasenia().equals(contrasenia)) {
            throw new PeajeExcepcion("Acceso denegado.");
        }
        if(!p.puedeIngresar()) {
            throw new PeajeExcepcion("Usuario deshabilitado, no puede ingresar al sistema");
        }
        return p;

    }

    public Propietario buscarPropietarioPorCedula(Long cedula) {
        for (Propietario p : propietarios) {
            if (p.getCedula().equals(cedula)) return p;
            
        }
        return null;
    }
    public Administrador LoginAdministrador(Long cedula, String contrasenia) throws PeajeExcepcion {
        Administrador a = buscarAdministradorPorCedula(cedula);
        if (a == null || !a.getContrasenia().equals(contrasenia)) {
            throw new PeajeExcepcion("Acceso denegado.");
        }
        return a;
    }
    public Administrador buscarAdministradorPorCedula(Long cedula) {
        for (Administrador a : administradores) {
            if (a.getCedula().equals(cedula)) return a;
            
        }
        return null;
    }
}
