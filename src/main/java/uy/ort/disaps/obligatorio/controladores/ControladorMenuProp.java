package uy.ort.disaps.obligatorio.controladores;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.observador.Observable;
import uy.ort.disaps.obligatorio.observador.Observador;
import uy.ort.disaps.obligatorio.utils.ConexionNavegador;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/menuProp")
@Scope("session") 
public class ControladorMenuProp implements Observador{
    
    private final ConexionNavegador conexionNavegador;


    public ControladorMenuProp(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }
    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "propietario", required = false) Propietario prop) {
        if (prop == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "LoginProp.html"));
        }

        // Enviar datos iniciales de cabecera
        return Respuesta.lista(
            new Respuesta("NombreUsuario", prop.getNombreCompleto()),
            new Respuesta("Estado", prop.getEstadoNombre()),
            new Respuesta("SaldoActual", "$"+ prop.getSaldoActual())

        );
    }

    // Canal SSE: conexión de la vista con el servidor
    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }

    //Actualizacion instantanea en caso de cambio datos user
    @Override
    public void actualizar(Object evento, Observable origen) {
      
       try {
            if (evento instanceof Propietario) {
                Propietario prop = (Propietario) evento;
                
                // Preparar respuestas
                List<Respuesta> respuestas = Respuesta.lista(
                    new Respuesta("NombreUsuario", prop.getNombreCompleto()),
                    new Respuesta("Estado", prop.getEstadoNombre()),
                    new Respuesta("SaldoActual", "$" + prop.getSaldoActual())
                );

                // Enviar a la vista mediante SSE
                SseEmitter emitter = conexionNavegador.getConexionSSE();
                if (emitter != null) {
                    emitter.send(respuestas); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
