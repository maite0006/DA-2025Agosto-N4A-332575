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
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/Bonificacion")
@Scope("session")

public class ControladorAsignarBonificacion {

    Propietario prop;
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
    public List<Respuesta> buscarProp(@RequestParam int cedula) throws PeajeExcepcion{
        try {
            Propietario p= fachada.getInstancia().obtenerProp(cedula);
            prop=p;
            PropietarioDto dto= fachada.getInstancia().obtenerPropDTO(p);
            return Respuesta.lista(new Respuesta("Propietario", dto));
             
        } catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta("errorP", e.getMessage()));
        }


    }   
    @PostMapping("Asignar")
    public List<Respuesta> asignarBoni(@RequestParam String bonificacion, @RequestParam String puesto, @RequestParam int cedula){
        try {
            if(prop==null||prop.getCedula()!=cedula) prop=fachada.getInstancia().obtenerProp(cedula);
            fachada.getInstancia().asignarBonificacion(bonificacion, puesto, prop);
            return Respuesta.lista(new Respuesta("Propietario", fachada.getInstancia().obtenerPropDTO(prop)));
            
        } catch (PeajeExcepcion e) {
            if (prop == null) 
                return Respuesta.lista(new Respuesta("errorP", e.getMessage()));

            return Respuesta.lista(new Respuesta("errorB", e.getMessage()));
        }
    }
     


    
}