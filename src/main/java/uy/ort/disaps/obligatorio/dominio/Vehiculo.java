package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

public class Vehiculo {
    
    @Getter
    private String matricula;
    @Getter
    private String modelo;
    @Getter
    private String color;
    @Getter
    private Categoria categoria;
    @Getter
    private Propietario propietario;

    public Vehiculo(String matricula, String modelo, String color, Categoria categoria, Propietario  propietario){
        this.categoria=categoria;
        this.color=color;
        this.matricula=matricula;
        this.modelo=modelo;
        this.propietario=propietario;
    }
}
