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
@RequestMapping("/accesoAdministrador")
@Scope("session")
public class ControladorLoginAdm {


    @PostMapping("/login")
    public List<Respuesta> loginAdministrador(@RequestParam int cedula, @RequestParam String contrasenia, HttpSession session) throws PeajeExcepcion {
        try {
            Administrador adm = fachada.getInstancia().LoginAdministrador(cedula, contrasenia);
            if (adm!=null) {
                
                session.setAttribute("Administrador", adm);
                return Respuesta.lista(new Respuesta( "loginExitoso", "MenuAdministrador.html"));
            }
            throw new PeajeExcepcion("Administrador no encontrado.");
        }
        catch (PeajeExcepcion e) {
            throw new PeajeExcepcion(e.getMessage());
        }
    } 

    @PostMapping("/logout")
    public List<Respuesta> logout(HttpSession sesionHttp) {
        Administrador usuario = (Administrador) sesionHttp.getAttribute("Administrador");
        if (usuario != null) {
            sesionHttp.removeAttribute("Administrador");
            sesionHttp.invalidate();
            fachada.getInstancia().EliminarSesion(usuario);
            return Respuesta.lista(new Respuesta("logoutExitoso", "LoginAdm.html"));
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "LoginAdm.html"));
    }
}


