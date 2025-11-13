package uy.ort.disaps.obligatorio.controladores;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/emularTransito")
@Scope("session")
public class ControladorEmularTransito {
    
    @GetMapping("/puestos")
    public List<PuestoDTO> obtenerPuestos (){
        
        List<PuestoDTO> dtos= fachada.getInstancia().puestosDTOs();
        return dtos;
    }
    
}
