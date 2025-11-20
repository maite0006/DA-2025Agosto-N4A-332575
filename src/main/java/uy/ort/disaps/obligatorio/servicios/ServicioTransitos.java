package uy.ort.disaps.obligatorio.servicios;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import uy.ort.disaps.obligatorio.DTOs.PuestoDTO;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTOA;
import uy.ort.disaps.obligatorio.DTOs.Mappers.PuestoMapper;
import uy.ort.disaps.obligatorio.DTOs.Mappers.TransitoMapper;
import uy.ort.disaps.obligatorio.DTOs.TransitoDTOP;
import uy.ort.disaps.obligatorio.DTOs.VehiculoDTO;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;
import uy.ort.disaps.obligatorio.dominio.Categoria;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.Tarifa;
import uy.ort.disaps.obligatorio.dominio.Transito;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;
import uy.ort.disaps.obligatorio.dominio.Propietario.ResultadoCobro;
import uy.ort.disaps.obligatorio.excepciones.PeajeExcepcion;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;


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

    public TransitoDTOA emularTransito(String puesto, String vehiculo, String fecha)  throws PeajeExcepcion, ParseException{
        Puesto p= buscarPuestoPorNombre(puesto);
        Vehiculo v= buscarVehiculoPorMat(vehiculo);
        if (v==null)  
            throw new PeajeExcepcion("No existe el vehiculo");

        Date f=convertirFecha(fecha);
        Propietario prop= v.getPropietario();

        if(!prop.puedeTransitar())
             throw new PeajeExcepcion("El propietario del vehículo está "+ prop.getEstadoNombre() +", no puede realizar tránsitos");
        
        boolean PasoHoy= yaPasoHoy(p, v, f);
        Bonificacion b= fachada.getInstancia().obtenerBonificacionPropietario(prop, p);
        Double monto= obtenerTarifa(p, v.getCategoria());
        Double descuento=0.0;
        String boni="";
        if (b != null&&prop.aplicaBonificaciones()) {  
            boni=b.getNombre();
            descuento= monto-b.aplicar(f, PasoHoy, monto);
            monto= b.aplicar(f, PasoHoy, monto);
        }
        else{
            boni="No se aplicaron bonificaciones";
        }
        cobrarTransito(prop, p, monto, vehiculo, f);
        Transito t= new Transito(f, v, p,monto,descuento, boni);
        transitos.add(t);
        TransitoDTOA dtoA= new TransitoDTOA(prop.getNombreCompleto(), prop.getEstadoNombre(),v.getCategoria().getNombre(), boni, String.valueOf(monto), String.valueOf(prop.getSaldoActual()));
        fachada.getInstancia().avisar(fachada.eventos.edicionProp);
        fachada.getInstancia().avisar(fachada.eventos.altaTransito);
        return dtoA;
    }

    //Auxiliares
    public void cobrarTransito(Propietario prop, Puesto p, double monto, String v, Date f) throws PeajeExcepcion{

        ResultadoCobro res= prop.cobrarTransito(monto);
        switch (res) {
            case SALDO_INSUFICIENTE:
                throw new PeajeExcepcion("Saldo insuficiente.");
            case COBRO_OK_SALDO_MINIMO:
                if(prop.recibeNotificaciones()){ 
                    fachada.getInstancia().crearNotificacion(prop, "Pasaste por el "+ p.getNombre()+"-"+p.getDireccion() + "con el vehículo " + v, f);
                    fachada.getInstancia().crearNotificacion(prop, "Tu saldo actual es de $ "+prop.getSaldoActual()  + "- Te recomendamos hacer una recarga", f);
                }
                break;
            case COBRO_OK:
                if(prop.recibeNotificaciones()){
                    fachada.getInstancia().crearNotificacion(prop, "Pasaste por el "+ p.getNombre()+"-"+p.getDireccion() + "con el vehículo " + v, f);
                }
                break;
        }
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
    
    public int transitosXV(String matricula) {
        int contador = 0;
        
        for (Transito t : transitos) {
            if (t.getVehiculo().getMatricula().equals(matricula)) {
                contador++;
            }
        }
        
        return contador;
    }
    
    public double totalTransitos(String matricula) {
        double total = 0.0;
        
        for (Transito t : transitos) {
            if (t.getVehiculo().getMatricula().equals(matricula)) {
                total += t.getMontoF();  
            }
        }
        
        return total;
    }

    public double obtenerTarifa(Puesto p, Categoria c){
        for(Tarifa t: p.getTarifas()){
            if(t.getCategoria().equalsIgnoreCase(c.getNombre())){
                 return t.getMonto();
            }
        }
        return 0;
    }

    //Buscar
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

    //DTOS
    public ArrayList<TransitoDTOP> getTransitosDTO(Propietario prop){
        ArrayList<Transito> trs = new ArrayList<>();
        
        for (Transito t : transitos) {
            if (t.getVehiculo().getPropietario().equals(prop)) {
                trs.add(t);
            }
        }
        return TransitoMapper.fromTransito(trs);

    }

    public List<PuestoDTO> getPuestosDTOs(){
        return PuestoMapper.fromPuestos(puestos);
    }

    public List<VehiculoDTO> obtenerVDtosXProp(Propietario propActual) {
        List<VehiculoDTO> resultado = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if(v.getPropietario().equals(propActual)){
                Double total= totalTransitos(v.getMatricula());
                int transitosCant= transitosXV(v.getMatricula());
                VehiculoDTO dto = new VehiculoDTO(v.getMatricula(), v.getModelo(), v.getColor(), transitosCant, total);
                resultado.add(dto);
            }
        }

        return resultado;
    }


    //Conversion fechas
    public Date convertirFecha(String fechaStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return sdf.parse(fechaStr);
    }
    public String  convertirDFecha(Date fecha) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return isoFormat.format(fecha);
    }
}   
