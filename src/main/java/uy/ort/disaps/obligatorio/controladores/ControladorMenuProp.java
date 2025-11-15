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

import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTOP;
import uy.ort.disaps.obligatorio.DTOs.VehiculoDTO;
import uy.ort.disaps.obligatorio.DTOs.Mappers.NotiMapper;
import uy.ort.disaps.obligatorio.dominio.Notificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;
import uy.ort.disaps.obligatorio.observador.Observable;
import uy.ort.disaps.obligatorio.observador.Observador;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;
import uy.ort.disaps.obligatorio.utils.ConexionNavegador;
import uy.ort.disaps.obligatorio.utils.Respuesta;

@RestController
@RequestMapping("/menuProp")
@Scope("session") 
public class ControladorMenuProp implements Observador{
    
    private final ConexionNavegador conexionNavegador;
    private Propietario propActual;

    public ControladorMenuProp(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }
    @GetMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "propietario", required = false) Propietario prop) {
        if (prop == null) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "LoginProp.html"));
        }
        fachada.getInstancia().agregarObservador(this);
        this.propActual=prop;
        List<TransitoDTOP> transitos =fachada.getInstancia().obtenerTransitoDTOs(propActual.getCedula());
        PropietarioDto propDto= fachada.getInstancia().obtenerDTOProp(propActual.getNombreCompleto(), propActual.getEstadoNombre(), propActual.getSaldoActual());
        List<VehiculoDTO> vDtos= fachada.getInstancia().obtenerVehiculosDTO(propActual);

        //  datos iniciales de cabecera
        return Respuesta.lista(
            new Respuesta("propietario", propDto),
            new Respuesta("transitos", transitos),
            new Respuesta("vehiculos", vDtos)

        );
    }

    // Canal SSE: conexión de la vista con el servidor
    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }

  
    @Override
    public void actualizar(Object evento, Observable origen) {
      
        try {
            if (evento.equals(fachada.eventos.edicionProp)) {
                PropietarioDto prop= fachada.getInstancia().obtenerDTOProp(propActual.getNombreCompleto(), propActual.getEstadoNombre(), propActual.getSaldoActual());
  
                
                List<Respuesta> respuestas = Respuesta.lista(
                    new Respuesta("propietario", prop)
                );

          
                SseEmitter emitter = conexionNavegador.getConexionSSE();
                if (emitter != null) {
                    emitter.send(respuestas); 
                }
            }
            if (evento.equals(fachada.eventos.altaNoti)) {
                
                SseEmitter emitter = conexionNavegador.getConexionSSE();
                if (emitter != null) {
                   
                    conexionNavegador.enviarJSON(Respuesta.lista( notificaciones()));
                    
                }
            }
            if (evento.equals(fachada.eventos.altaTransito)) {
                SseEmitter emitter = conexionNavegador.getConexionSSE();
                if (emitter != null) {
                   
                    conexionNavegador.enviarJSON(
                        Respuesta.lista(
                            new Respuesta("transitos", fachada.getInstancia().obtenerTransitoDTOs(propActual.getCedula()))
                        )
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Respuesta notificaciones(){
        return new Respuesta ("notificaciones", NotiMapper.fromNotis(fachada.getInstancia().getNotificaciones(propActual)));
    }
    

    
}
