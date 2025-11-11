package uy.ort.disaps.obligatorio.controladores;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/accesoPropietario")
@Scope("session")
public class ControladorLoginProp {


    @PostMapping("/login")
    public List<Respuesta> loginPropietario(@RequestParam Long cedula, @RequestParam String contrasenia, HttpSession session) {
        try {
            Propietario propietario = fachada.getInstancia().LoginPropietario(cedula, contrasenia);
            if (propietario!=null) {
                
                session.setAttribute("propietario", propietario);
                return Respuesta.lista(new Respuesta( "Login exitoso", "menuPropietario.html"));
            }
            return Respuesta.lista(new Respuesta( "Error", "Propietario no encontrado"));
        }
        catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta( "Error", e.getMessage()));
        }
    }   
}