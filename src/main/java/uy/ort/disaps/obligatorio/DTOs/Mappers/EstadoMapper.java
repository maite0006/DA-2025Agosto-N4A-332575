package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.EstadoDTO;
import uy.ort.disaps.obligatorio.dominio.EstadoPropietario;

public class EstadoMapper {
    public static List<EstadoDTO> fromEstado(List<EstadoPropietario> estados){
        List<EstadoDTO> dtos= new ArrayList<>();
        for(EstadoPropietario e: estados){
            EstadoDTO dto= new EstadoDTO(e.nombreEstado());
            dtos.add(dto);
        }
        return dtos;
    }
}
