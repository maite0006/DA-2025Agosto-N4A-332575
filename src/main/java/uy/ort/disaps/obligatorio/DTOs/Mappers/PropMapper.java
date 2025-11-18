package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.text.SimpleDateFormat;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;

public class PropMapper {
    

    public static PropietarioDto fromProp(Propietario prop, List<AsignacionBonificacion> asignaciones) {
        if (prop == null) return null;

        
        PropietarioDto dto = new PropietarioDto(
            prop.getNombreCompleto(),
            prop.getEstado().nombreEstado(),
            String.valueOf(prop.getSaldoActual()) 
        );

        // Mapear las asignaciones a AsignacionBoniDTO
        if (asignaciones != null) {
            for (AsignacionBonificacion a : asignaciones) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
                String fechaStr = a.getFechaAsignacion() != null ? sdf.format(a.getFechaAsignacion()) : "";
                AsignacionBoniDTO asignadaDTO = new AsignacionBoniDTO(
                    a.getBonificacion().getNombre(), 
                    a.getPuesto().getNombre(),        
                    fechaStr           
                );
                dto.getAsignadas().add(asignadaDTO);
            }
        }

        return dto;
    }


}
