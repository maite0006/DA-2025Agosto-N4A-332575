package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.TransitoDTOP;
import uy.ort.disaps.obligatorio.dominio.Categoria;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.Transito;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;

public class TransitoMapper {

    public static ArrayList<TransitoDTOP> fromTransito(List<Transito> transitos){
        ArrayList<TransitoDTOP> res = new ArrayList<>();
        for (Transito t : transitos) {
                Puesto p=t.getPuesto();
                Vehiculo v=t.getVehiculo();                
                Categoria c= v.getCategoria();
                String f= fachada.getInstancia().convertirDFecha(t.getFecha());
                Double tarifa=t.getDescuentoAplicado()+t.getMontoF();
                TransitoDTOP dtoP= new TransitoDTOP(p.getNombre(),v.getMatricula(),c.getNombre(),String.valueOf(tarifa),t.getNombreBonificacion(), String.valueOf(t.getMontoF()),f, String.valueOf(t.getDescuentoAplicado()));
                res.add(dtoP);
        }
        return res;
    }
}
