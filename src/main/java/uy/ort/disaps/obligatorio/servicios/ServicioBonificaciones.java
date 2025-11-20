package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.BoniDTO;
import uy.ort.disaps.obligatorio.DTOs.Mappers.BonificacacionMapper;
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

    //Obtener
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
    public Bonificacion bonificacionXNombre(String bonificacion){
        for(Bonificacion b: bonificaciones){
            if (b.getNombre().equalsIgnoreCase(bonificacion)) {
                return b;
            }
        }
        return null;
    }

    public List<AsignacionBonificacion> getAsignadas(Propietario p) {
        List<AsignacionBonificacion> asignadas= new ArrayList<>();
        for(AsignacionBonificacion a: bonificacionesAsignadas){
            if(a.getPropietario().equals(p)){
                asignadas.add(a);
            }
        }
        return asignadas;
    
    }

    //DTOS
    public List<BoniDTO> getBonisDTOs() { 
        return BonificacacionMapper.fromBoni(bonificaciones);
    }
    public ArrayList<AsignacionBoniDTO> asignacionesDTOs(Propietario p) {
        List<AsignacionBonificacion> asignaciones= getAsignadas(p);
       return BonificacacionMapper.fromAsignaciones(asignaciones);
    }

   

    public void asignarBoni(String bonificacion, String puesto, Propietario prop)throws PeajeExcepcion {
        if(puesto=="") throw new PeajeExcepcion("Debe especificar un peaje");
        if(bonificacion=="") throw new PeajeExcepcion("Debe especificar una bonificacion");
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

    
    

    

}
