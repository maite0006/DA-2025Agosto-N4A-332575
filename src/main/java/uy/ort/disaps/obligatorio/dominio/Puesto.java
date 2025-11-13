package uy.ort.disaps.obligatorio.dominio;
import java.util.ArrayList;

import lombok.Getter;
public class Puesto {
    @Getter
    private String nombre;
    @Getter
    private String direccion;
    @Getter
    private ArrayList<Tarifa> tarifas=new ArrayList<Tarifa>();

    public Puesto(String nombre, String direccion){
        this.nombre=nombre;
        this.direccion=direccion;
    }
    public void agregarTarifa(Tarifa t) {
        this.tarifas.add(t);
    }
}


