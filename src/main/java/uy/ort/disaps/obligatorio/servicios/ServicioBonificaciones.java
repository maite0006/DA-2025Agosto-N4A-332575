package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.BoniDTO;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.StrategyBonificacion;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;

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
    public Bonificacion obtenerBonificacionPropietario(Propietario prop, Puesto puesto) {
        for (AsignacionBonificacion asignacion : bonificacionesAsignadas) {
            boolean mismoProp = asignacion.getPropietario().equals(prop);
            boolean mismoPuesto = asignacion.getPuesto().equals(puesto);

            if (mismoProp && mismoPuesto) {
                return asignacion.getBonificacion();
            }
        }

        return null; 
    }
    public List<BoniDTO> getBonisDTOs() {
        List<BoniDTO> dtos = new ArrayList<>();

        for (Bonificacion b : bonificaciones) {
            dtos.add(new BoniDTO(b.getNombre()));
        }

        return dtos;
    }
    public List<AsignacionBonificacion> getAsignadas(int cedulaN) {
        List<AsignacionBonificacion> asignadas= new ArrayList<>();
        for(AsignacionBonificacion a: bonificacionesAsignadas){
            if(a.getPropietario().getCedula()==cedulaN){
                asignadas.add(a);
            }
        }
        return asignadas;
    
    }
    public void asignarBoni(String bonificacion, String puesto, String cedula)throws PeajeExcepcion {
        int cedulaN= Integer.parseInt(cedula.trim());
        Propietario prop=fachada.getInstancia().obtenerProp(cedulaN);
        if(!prop.asignaBonificaciones()){
            throw new PeajeExcepcion("El propietario esta deshabilitado. No se pueden asignar bonificacione");
        }
        Puesto p=fachada.getInstancia().obtenerPuesto(puesto);
        Bonificacion existe=obtenerBonificacionPropietario(prop, p);
        if(existe!=null){
            throw new PeajeExcepcion("Ya tiene una bonificación asignada para ese puesto");
        }
        Bonificacion b= bonificacionXNombre(bonificacion);
        AsignacionBonificacion asignacion= new AsignacionBonificacion(b, prop, p);
        bonificacionesAsignadas.add(asignacion);
        fachada.getInstancia().avisar(fachada.eventos.asignacionBoni);
    
    }

    public Bonificacion bonificacionXNombre(String bonificacion){
        for(Bonificacion b: bonificaciones){
            if (b.getNombre().equalsIgnoreCase(bonificacion)) {
                return b;
            }
        }
        return null;
    }
    public ArrayList<AsignacionBoniDTO> asignacionesDTOs(int cedulaN) {
        List<AsignacionBonificacion> asignaciones= getAsignadas(cedulaN);
        ArrayList<AsignacionBoniDTO> DTOs= new ArrayList<>();
        for(AsignacionBonificacion ab: asignaciones){
            AsignacionBoniDTO dto= new AsignacionBoniDTO(ab.getBonificacion().getNombre(), ab.getPuesto().getNombre(), String.valueOf(ab.getFechaAsignacion()));
            DTOs.add(dto);
        }
        return DTOs;
    }

    

}
