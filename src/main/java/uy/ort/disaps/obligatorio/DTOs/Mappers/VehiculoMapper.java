package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.VehiculoDTO;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;

public class VehiculoMapper {
    
    public static List<VehiculoDTO> fromVehiculos(List<Vehiculo> vehiculos){
        List<VehiculoDTO> resultado = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            
                Double total= fachada.getInstancia().totalTransitos(v.getMatricula());
                int transitosCant= fachada.getInstancia().transitosXV(v.getMatricula());
                VehiculoDTO dto = new VehiculoDTO(v.getMatricula(), v.getModelo(), v.getColor(), transitosCant, total);
                resultado.add(dto);
            
        }
        return resultado;
    }
}
