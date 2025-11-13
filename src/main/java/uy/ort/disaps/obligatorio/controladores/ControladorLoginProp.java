package uy.ort.disaps.obligatorio.controladores;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/accesoPropietario")
@Scope("session")
public class ControladorLoginProp {


    @PostMapping("/login")
    public List<Respuesta> loginPropietario(@RequestParam int cedula, @RequestParam String contrasenia, HttpSession session) throws PeajeExcepcion {
        try {
            Propietario propietario = fachada.getInstancia().LoginPropietario(cedula, contrasenia);
            if (propietario!=null) {
                
                session.setAttribute("propietario", propietario);
                return Respuesta.lista(new Respuesta( "loginExitoso", "MenuPropietario.html"));
            }
            throw new PeajeExcepcion("Usuario no encontrado.");
        }
        catch (PeajeExcepcion e) {
            throw new PeajeExcepcion(e.getMessage());
        }
    }   
    
    @PostMapping("/logout")
    public List<Respuesta> logout(HttpSession sesionHttp) {
        Propietario usuario = (Propietario) sesionHttp.getAttribute("propietario");
        
        if (usuario != null) {
            sesionHttp.removeAttribute("propietario");
            sesionHttp.invalidate();
            return Respuesta.lista(new Respuesta("logoutExitoso", "LoginProp.html"));
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "LoginProp.html"));
    }
}