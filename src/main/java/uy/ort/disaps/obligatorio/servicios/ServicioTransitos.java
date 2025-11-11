package uy.ort.disaps.obligatorio.servicios;

import java.util.ArrayList;

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
}   
