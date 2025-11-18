package uy.ort.disaps.obligatorio.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/Estado")

public class ControladorCambiarEstado {
    @GetMapping("/Propietario")
    public List<Respuesta> obtenerProp(@RequestParam String cedula) throws PeajeExcepcion{
        try {
           PropietarioDto dto= fachada.getInstancia().obtenerProp(cedula);
            return Respuesta.lista(new Respuesta("Propietario", dto));
            
        } catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        }
        
    }
}
