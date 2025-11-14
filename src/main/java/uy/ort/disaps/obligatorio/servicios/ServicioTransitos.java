package uy.ort.disaps.obligatorio.servicios;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.DTOs.Mappers.PuestoMapper;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTO;
import uy.ort.disaps.obligatorio.dominio.Categoria;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.Tarifa;
import uy.ort.disaps.obligatorio.dominio.Transito;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;

public class ServicioTransitos {
    private ArrayList<Transito> transitos=new ArrayList<>();
    private ArrayList<Puesto> puestos=new ArrayList<>();
    private ArrayList<Tarifa> tarifas=new ArrayList<>();
    private ArrayList<Categoria> categorias=new ArrayList<>();
    private ArrayList<Vehiculo> vehiculos=new ArrayList<>();
    private final ServicioBonificacion sBonificacion;
    private final ServicioNotificacion sNotificacion;

    public void agregarTransito(Transito t){
        transitos.add(t);
    }
    public ArrayList<Transito> getTransitos(){
        return transitos;
    }
    public void agregarPuesto(Puesto p){
        puestos.add(p);
    }
    public ArrayList<Puesto> getPuestos(){
        return puestos;
    }
    public void agregarTarifa(Tarifa t){
        tarifas.add(t);
    }
    public ArrayList<Tarifa> getTarifas(){
        return tarifas;
    }
    public void agregarCategoria(Categoria c){
        categorias.add(c);
    }
    public ArrayList<Categoria> getCategorias(){
        return categorias;
    }
    public void agregarVehiculo(Vehiculo v){
        vehiculos.add(v);
    }
    public ArrayList<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    public TransitoDTO emularTransito(String puesto, String vehiculo, String fecha){
        Puesto p= buscarPuestoPorNombre(puesto);
        Vehiculo v= buscarVehiculoPorMat(vehiculo);
        Propietario prop= v.
    }

    public boolean yaPasoHoy(Puesto puesto, Vehiculo vehiculo, Date fecha) {

        LocalDate fechaLocal = fecha.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();

        for (Transito t : transitos) {
            LocalDate fechaT = t.getFecha().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

            boolean mismoDia = fechaT.isEqual(fechaLocal);
            boolean mismoVehiculo = t.getVehiculo().equals(vehiculo);
            boolean mismoPuesto = t.getPuesto().equals(puesto);

            if (mismoDia && mismoVehiculo && mismoPuesto) {
                return true;
            }
        }

        return false;
    }
    public Puesto buscarPuestoPorNombre(String nombre) {
        for (Puesto p : puestos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
    public Vehiculo buscarVehiculoPorMat(String matricula){
        for (Vehiculo v: vehiculos){
            if (v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }


    public List<PuestoDTO> getPuestosDTOs(){
        return PuestoMapper.fromPuestos(puestos);
    }
    
}   
