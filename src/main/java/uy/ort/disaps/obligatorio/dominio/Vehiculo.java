package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

public class Vehiculo {
    
    @Getter
    private long matricula;
    @Getter
    private String modelo;
    @Getter
    private String color;
    @Getter
    private Categoria categoria;
    @Getter
    private Propietario propietario;
}
