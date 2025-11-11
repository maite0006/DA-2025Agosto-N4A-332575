package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;

import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;
import uy.ort.disaps.obligatorio.dominio.StrategyBonificacion;

public class ServicioBonificaciones {
    public ArrayList<Bonificacion> bonificaciones=new ArrayList<>();
    public ArrayList<AsignacionBonificacion> bonificacionesAsignadas=new ArrayList<>();
    public ArrayList<StrategyBonificacion> strategies=new ArrayList<>();

    public ArrayList<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }
    public void agregarBonificacion(Bonificacion b) {
        bonificaciones.add(b);
    }
    public ArrayList<AsignacionBonificacion> getBonificacionesAsignadas() {
        return bonificacionesAsignadas;
    }
    public void agregarBonificacionAsignada(AsignacionBonificacion ab) {
        bonificacionesAsignadas.add(ab);
    }
    
}
