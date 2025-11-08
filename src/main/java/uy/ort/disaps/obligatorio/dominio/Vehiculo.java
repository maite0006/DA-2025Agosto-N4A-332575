package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
import java.util.List;
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
    private List<Transito> transitos;
}
