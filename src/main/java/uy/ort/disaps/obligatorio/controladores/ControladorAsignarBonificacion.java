package uy.ort.disaps.obligatorio.controladores;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.BoniDTO;
import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/Bonificacion")
@Scope("session")

public class ControladorAsignarBonificacion {

     @GetMapping("/puestos")
    public List<PuestoDTO> obtenerPuestos (){
        
        List<PuestoDTO> dtos= fachada.getInstancia().puestosDTOs();
        return dtos;
    }
     @GetMapping("/bonificaciones")
     public List<BoniDTO> obtenerBonis(){
        List<BoniDTO> dtos =fachada.getInstancia().bonisDTOs();
        return dtos;
    }
    @GetMapping("/buscarPropietario")
    public List<Respuesta> buscarProp(@RequestParam String cedula) throws PeajeExcepcion{
        try {
            PropietarioDto dto= fachada.getInstancia().obtenerProp(cedula);
            return Respuesta.lista(new Respuesta("Propietario", dto));
            
        } catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta("errorP", e.getMessage()));
        }

    }   
    @PostMapping("Asignar")
    public List<Respuesta> asignarBoni(@RequestParam String bonificacion, @RequestParam String puesto, @RequestParam String cedula){
        try {
            fachada.getInstancia().asignarBonificacion(bonificacion, puesto, cedula);
            return Respuesta.lista(new Respuesta("Propietario", fachada.getInstancia().obtenerProp(cedula)));
            
        } catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta("errorB", e.getMessage()));
        }
    }
     


    
}