package uy.ort.disaps.obligatorio.dominio;
import lombok.Getter;
public class Tarifa {
    @Getter
    private double monto;
    @Getter
    private Categoria categoria;

    public Tarifa(double monto, Categoria cat){
        this.monto=monto;
        this.categoria=cat;
    }
    
    public String getCategoria(){
        return categoria.getNombre();
    }
}
