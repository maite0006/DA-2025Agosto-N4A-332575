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
    public List<Respuesta> loginAdministrador(@RequestParam Long cedula, @RequestParam String contrasenia, HttpSession session)  {
        try {
            Administrador adm = fachada.getInstancia().LoginAdministrador(cedula, contrasenia);
            if (adm!=null) {
                
                session.setAttribute("Administrador", adm);
                return Respuesta.lista(new Respuesta( "Login exitoso", "menuPropietario.html"));
            }
            return Respuesta.lista(new Respuesta( "Error", "Administrador no encontrado"));
        }
        catch (PeajeExcepcion e) {
            return Respuesta.lista(new Respuesta( "Error", e.getMessage()));
        }
    }   
}

