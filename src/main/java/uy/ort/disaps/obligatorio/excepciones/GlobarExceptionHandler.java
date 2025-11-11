package uy.ort.disaps.obligatorio.excepciones;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobarExceptionHandler {
    private final int errorCodeStatus = 299;

    @ExceptionHandler(PeajeExcepcion.class)
    public ResponseEntity<String> manejarException(PeajeExcepcion ex) {
       return ResponseEntity.status(errorCodeStatus).body(ex.getMessage());
    }
}
