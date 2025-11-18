package uy.ort.disaps.obligatorio.controladores;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTOA;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/emularTransito")
public class ControladorEmularTransito {
    
    @GetMapping("/puestos")
    public List<PuestoDTO> obtenerPuestos (){
        
        List<PuestoDTO> dtos= fachada.getInstancia().puestosDTOs();
        return dtos;
    }
    
    @PostMapping("/emular")
    public List<Respuesta> emularTransito(@RequestParam String matricula, @RequestParam String puesto, @RequestParam String fechaHora) throws ParseException{
        try {
          TransitoDTOA dto= fachada.getInstancia().emularTransito(matricula, puesto, fechaHora);
          return Respuesta.lista( new Respuesta("resultadoTransito", dto));

        } catch (PeajeExcepcion e) {
           return Respuesta.lista(new Respuesta("error",e.getMessage()));
        }
    }

}
