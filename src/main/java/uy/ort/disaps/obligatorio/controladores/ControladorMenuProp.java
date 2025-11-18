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

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.NotificacionDTO;
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
@RequestMapping("/menuprop")
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
        List<NotificacionDTO> nDTOs= fachada.getInstancia().getNotificacionesDTO(propActual);
        List<AsignacionBoniDTO> bDTOs= fachada.getInstancia().AsignacionesDTO(propActual.getCedula());
        //  datos iniciales de cabecera
        return Respuesta.lista(
            new Respuesta("propietario", propDto),
            new Respuesta("transitos", transitos),
            new Respuesta("vehiculos", vDtos),
            new Respuesta("notificaciones", nDTOs),
            new Respuesta("bonificaciones", bDTOs)

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

          
               conexionNavegador.enviarJSON(respuestas);
            }
            if (evento.equals(fachada.eventos.altaNoti)) {
                    conexionNavegador.enviarJSON(Respuesta.lista( new Respuesta ("notificaciones", fachada.getInstancia().getNotificacionesDTO(propActual))));
                
            }
            if (evento.equals(fachada.eventos.altaTransito)) {
              
                conexionNavegador.enviarJSON(
                    Respuesta.lista(
                        new Respuesta("transitos", fachada.getInstancia().obtenerTransitoDTOs(propActual.getCedula())),
                        new Respuesta("vehiculos", fachada.getInstancia().obtenerVehiculosDTO(propActual))
                    )
                );   
            }
            if(evento.equals(fachada.eventos.asignacionBoni)){
                 conexionNavegador.enviarJSON(
                    Respuesta.lista(
                        new Respuesta("bonificaciones", fachada.getInstancia().AsignacionesDTO(propActual.getCedula()))
                    )
                ); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
    

    
}
