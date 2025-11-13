package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.dominio.Puesto;


public class PuestoMapper {
    public static List<PuestoDTO> fromPuestos(List<Puesto> puestos){
        
        return puestos.stream()
                    .map(p -> new PuestoDTO(p.getNombre(), TarifaMapper.fromTarifa(p.getTarifas()), p.getDireccion()))
                    .collect(Collectors.toList());

    }
}

