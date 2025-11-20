package uy.ort.disaps.obligatorio.DTOs.Mappers;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import uy.ort.disaps.obligatorio.DTOs.AsignacionBoniDTO;
import uy.ort.disaps.obligatorio.DTOs.PropietarioDto;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Propietario;

public class PropMapper {
    

    public static PropietarioDto fromProp(Propietario prop, ArrayList<AsignacionBoniDTO> asignaciones) {
        if (prop == null) return null;
        PropietarioDto dto = new PropietarioDto(
            prop.getNombreCompleto(),
            prop.getEstado().nombreEstado(),
            String.valueOf(prop.getSaldoActual()) 

        );
        dto.setAsignadas(asignaciones);
       

        return dto;
    }


}
