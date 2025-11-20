package uy.ort.disaps.obligatorio.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uy.ort.disaps.obligatorio.DTOs.EstadoDTO;
import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.dominio.EstadoPropietario;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/Estado")

public class ControladorCambiarEstado {
    
    Propietario prop;
    List<EstadoPropietario> estados;
    @GetMapping("/Propietario")
    public List<Respuesta> obtenerProp(@RequestParam int cedula) throws PeajeExcepcion{
        try {
            Propietario p= fachada.getInstancia().obtenerProp(cedula);
            prop=p; 
            PropietarioDto dto= fachada.getInstancia().obtenerPropDTO(p);
            return Respuesta.lista(new Respuesta("Propietario", dto));
            
        } catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        }
        
    }
    @GetMapping("/Obtener")
    public List<EstadoDTO> obtenerEstadoDTOs(){
        estados= fachada.getInstancia().obtenerEstados();
        return  fachada.getInstancia().obtenerEstadoDTOs(estados);
    }
    @PatchMapping("Cambiar")
    public List<Respuesta> cambiarEstado(@RequestParam int index, @RequestParam int cedula) throws PeajeExcepcion{
        try{
            if(prop.getCedula()!=cedula) prop= fachada.getInstancia().obtenerProp(cedula);
            if(prop.getEstadoNombre()==estados.get(index).nombreEstado()) {
                return Respuesta.lista(new Respuesta("error","El propietario ya esta en estado "+prop.getEstadoNombre()));
            }
            prop.cambiarEstado(estados.get(index));
            fachada.getInstancia().crearNotificacion(prop, "Se ha cambiado tu estado en el sistema. Tu estado actual es "+prop.getEstadoNombre(), new Date());
            fachada.getInstancia().avisar(fachada.eventos.edicionProp);
            PropietarioDto dto= fachada.getInstancia().obtenerPropDTO(prop);
            return Respuesta.lista(new Respuesta("Propietario", dto));

        }
        catch(PeajeExcepcion ex){
            return Respuesta.lista(new Respuesta("error", ex.getMessage()));
        }
    }
}
