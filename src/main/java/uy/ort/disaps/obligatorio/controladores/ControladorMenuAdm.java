package uy.ort.disaps.obligatorio.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;

import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.observador.Observable;
import uy.ort.disaps.obligatorio.observador.Observador;
import uy.ort.disaps.obligatorio.utils.ConexionNavegador;
import uy.ort.disaps.obligatorio.utils.Respuesta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/menuAdm")
@Scope("session")
public class ControladorMenuAdm implements Observador{

     private final ConexionNavegador conexionNavegador;

    public ControladorMenuAdm(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }
    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name="Administrador", required=false) Administrador admin) {
        if (admin == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "LoginAdm.html"));
        }
        System.out.println(admin);
        return Respuesta.lista(
            new Respuesta("NombreAdmin", admin.getNombreCompleto())
            
        );
    }

    @GetMapping(value="/registrarSSE", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }
    @Override
    public void actualizar(Object evento, Observable origen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
}
