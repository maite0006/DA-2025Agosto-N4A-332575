package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.Propietario;

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
}
