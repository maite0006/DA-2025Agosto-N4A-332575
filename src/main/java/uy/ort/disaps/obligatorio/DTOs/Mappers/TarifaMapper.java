package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.DTOs.TarifaDTO;
import uy.ort.disaps.obligatorio.dominio.Tarifa;

public class TarifaMapper {
    
    public static List<TarifaDTO> fromTarifa(List<Tarifa> tarifas){
         List<TarifaDTO> dtos= new ArrayList<>();
         for(Tarifa t: tarifas){
            TarifaDTO dto= new TarifaDTO(t.getCategoria(), t.getMonto());
            dtos.add(dto);
         }
         return dtos;
    }
}
