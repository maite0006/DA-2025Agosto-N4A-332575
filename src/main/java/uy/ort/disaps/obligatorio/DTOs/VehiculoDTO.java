package uy.ort.disaps.obligatorio.DTOs;

import lombok.Getter;

public class VehiculoDTO {
   @Getter
   public String matricula;
   @Getter
   public String modelo;
   @Getter
   public String color;
   @Getter
   public int transitos;
   @Getter
   public double totalT;

   public VehiculoDTO(String mat, String mod, String col, int cantT, double tot){
        matricula=mat;
        modelo=mod;
        color=col;
        transitos=cantT;
        totalT=tot;
   }

}
