package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.DTOs.TarifaDTO;
import uy.ort.disaps.obligatorio.dominio.Tarifa;

public class TarifaMapper {
    
    public static List<TarifaDTO> fromTarifa(List<Tarifa> tarifas){
         return tarifas.stream()
                .map(t -> new TarifaDTO(t.getCategoria(), t.getMonto()))
                .toList();
    }
}
