package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.dominio.Puesto;


public class PuestoMapper {
    public static List<PuestoDTO> fromPuestos(List<Puesto> puestos){
        List<PuestoDTO> dtos= new ArrayList<>();
        for(Puesto p: puestos){
            PuestoDTO dto= new PuestoDTO(p.getNombre(), TarifaMapper.fromTarifa(p.getTarifas()), p.getDireccion());
            dtos.add(dto);
        }
        return dtos;
    }
}

