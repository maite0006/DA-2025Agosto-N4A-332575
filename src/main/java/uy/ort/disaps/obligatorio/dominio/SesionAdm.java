package uy.ort.disaps.obligatorio.dominio;

import java.util.Date;

public class SesionAdm {
    
    private Date fechaIngreso = new Date();
    private Administrador administrador;

    public SesionAdm(Administrador adm) {
        this.administrador = adm;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Administrador getUsuario() {
        return administrador;
    }
    
    
    
}