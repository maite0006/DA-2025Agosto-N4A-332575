package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.BoniDTO;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;

public class BonificacacionMapper {

    public static List<BoniDTO>  fromBoni(List<Bonificacion> bonis){
          List<BoniDTO> dtos = new ArrayList<>();

        for (Bonificacion b : bonis) {
            dtos.add(new BoniDTO(b.getNombre()));
        }
        return dtos;
    }

    public static ArrayList<AsignacionBoniDTO> fromAsignaciones(List<AsignacionBonificacion> asigns){
        ArrayList<AsignacionBoniDTO> DTOs= new ArrayList<>();
        for(AsignacionBonificacion ab: asigns){
            AsignacionBoniDTO dto= new AsignacionBoniDTO(ab.getBonificacion().getNombre(), ab.getPuesto().getNombre(), String.valueOf(ab.getFechaAsignacion()));
            DTOs.add(dto);
        }
        return DTOs;
    }
    
}
