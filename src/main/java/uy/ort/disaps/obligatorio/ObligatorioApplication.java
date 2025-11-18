package uy.ort.disaps.obligatorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

import uy.ort.disaps.obligatorio.dominio.Administrador;
import uy.ort.disaps.obligatorio.dominio.AsignacionBonificacion;
import uy.ort.disaps.obligatorio.dominio.Bonificacion;
import uy.ort.disaps.obligatorio.dominio.Categoria;
import uy.ort.disaps.obligatorio.dominio.Propietario;
import uy.ort.disaps.obligatorio.dominio.Puesto;
import uy.ort.disaps.obligatorio.dominio.SBonificacionExonerado;
import uy.ort.disaps.obligatorio.dominio.SBonificacionFrecuente;
import uy.ort.disaps.obligatorio.dominio.SBonificacionTrabajador;
import uy.ort.disaps.obligatorio.dominio.Tarifa;
import uy.ort.disaps.obligatorio.dominio.Vehiculo;
import uy.ort.disaps.obligatorio.servicios.ServicioUsuarios;
import uy.ort.disaps.obligatorio.servicios.fachada.fachada;


@SpringBootApplication
public class ObligatorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObligatorioApplication.class, args);
		cargarDatosDePrueba();
	}

	private static void cargarDatosDePrueba() {
		Administrador administrador = new Administrador(12345678,"admin.123","Usuario Administrador");
		Administrador administrador2 = new Administrador(12786543,"admin.1234","Usuario Administrador2");
		Propietario propietario = new Propietario(23456789,"prop.123","Usuario Propietario",2000.0,500.0);
		Propietario propietario2 = new Propietario(29877839,"prop.1234","Usuario Propietario3",1000.0,300.0);
		fachada.getInstancia().agregarAdministrador(administrador2);
		fachada.getInstancia().agregarAdministrador(administrador);
		fachada.getInstancia().agregar(propietario);
		fachada.getInstancia().agregar(propietario2);

		Categoria auto= new Categoria("Automovil");
		Categoria moto= new Categoria("Motocicleta");
		Categoria motorHome= new Categoria("Camion");
		Categoria camioneta= new Categoria("Camioneta");
		fachada.getInstancia().agregarCategoria(camioneta);
		fachada.getInstancia().agregarCategoria(motorHome);
		fachada.getInstancia().agregarCategoria(auto);
		fachada.getInstancia().agregarCategoria(moto);

		Tarifa tarifaCamioneta1= new Tarifa(120.0, camioneta);
		Tarifa tarifamotorHome1= new Tarifa(150.0, motorHome);
		Tarifa tarifaAuto1= new Tarifa(100, auto);
		Tarifa tarifaMoto1= new Tarifa(80, moto);
		fachada.getInstancia().agregarTarifa(tarifaMoto1);
		fachada.getInstancia().agregarTarifa(tarifaAuto1);
		fachada.getInstancia().agregarTarifa(tarifamotorHome1);
		fachada.getInstancia().agregarTarifa(tarifaCamioneta1);

		Tarifa tarifaCamioneta2= new Tarifa(110.0, camioneta);
		Tarifa tarifamotorHome2= new Tarifa(140.0, motorHome);
		Tarifa tarifaAuto2= new Tarifa(90, auto);
		Tarifa tarifaMoto2= new Tarifa(60, moto);
		fachada.getInstancia().agregarTarifa(tarifaMoto2);
		fachada.getInstancia().agregarTarifa(tarifaAuto2);
		fachada.getInstancia().agregarTarifa(tarifamotorHome2);
		fachada.getInstancia().agregarTarifa(tarifaCamioneta2);

		Tarifa tarifaCamioneta3= new Tarifa(130.0, camioneta);
		Tarifa tarifamotorHome3= new Tarifa(160.0, motorHome);
		Tarifa tarifaAuto3= new Tarifa(110, auto);
		Tarifa tarifaMoto3= new Tarifa(90, moto);
		fachada.getInstancia().agregarTarifa(tarifaMoto3);
		fachada.getInstancia().agregarTarifa(tarifaAuto3);
		fachada.getInstancia().agregarTarifa(tarifamotorHome3);
		fachada.getInstancia().agregarTarifa(tarifaCamioneta3);

		Puesto puesto1= new Puesto("Puesto Victoria", "Giannastassio KM25");
		puesto1.agregarTarifa(tarifaMoto3);
		puesto1.agregarTarifa(tarifamotorHome3);
		puesto1.agregarTarifa(tarifaAuto3);
		puesto1.agregarTarifa(tarifaCamioneta3);

		Puesto puesto2= new Puesto("Puesto Lunares", "Giannastassio KM40");
		puesto2.agregarTarifa(tarifaMoto2);
		puesto2.agregarTarifa(tarifamotorHome2);
		puesto2.agregarTarifa(tarifaAuto2);
		puesto2.agregarTarifa(tarifaCamioneta2);
		

		Puesto puesto3= new Puesto("Puesto Grande", "Giannastassio KM05");
		puesto3.agregarTarifa(tarifaMoto1);
		puesto3.agregarTarifa(tarifamotorHome1);
		puesto3.agregarTarifa(tarifaAuto1);
		puesto3.agregarTarifa(tarifaCamioneta1);

		fachada.getInstancia().agregarPuesto(puesto1);
		fachada.getInstancia().agregarPuesto(puesto2);
		fachada.getInstancia().agregarPuesto(puesto3);
		
		Vehiculo vehiculo1= new Vehiculo("AAA 5678", "Mustang 69","Verde", auto, propietario2);
		Vehiculo vehiculo2= new Vehiculo("ASZ 5006", "Cafe Racer","Negra", moto, propietario2);
		Vehiculo vehiculo3= new Vehiculo("APQ 4390", "Clase A", "Gris", motorHome, propietario2);

		Vehiculo vehiculo4= new Vehiculo("BDF 4095", "Dodge Challenger", "Bordo", auto, propietario);
		Vehiculo vehiculo5= new Vehiculo("BGH 0962", "Mercedes 1977", "Negro", auto, propietario);
		Vehiculo vehiculo6= new Vehiculo("BJK 7491", "Jeep Wrangler", "Verde", camioneta, propietario);
		fachada.getInstancia().agregarVehiculo(vehiculo6);
		fachada.getInstancia().agregarVehiculo(vehiculo5);
		fachada.getInstancia().agregarVehiculo(vehiculo4);
		fachada.getInstancia().agregarVehiculo(vehiculo3);
		fachada.getInstancia().agregarVehiculo(vehiculo2);
		fachada.getInstancia().agregarVehiculo(vehiculo1);

		Bonificacion exonerado= new Bonificacion("Exonerado", new SBonificacionExonerado());
		Bonificacion frecuente= new Bonificacion("Frecuente", new SBonificacionFrecuente());
		Bonificacion trabajador= new Bonificacion("Trabajador", new SBonificacionTrabajador());

		fachada.getInstancia().agregarBonificacion(exonerado);
		fachada.getInstancia().agregarBonificacion(frecuente);
		fachada.getInstancia().agregarBonificacion(trabajador);
		AsignacionBonificacion asignacion1= new AsignacionBonificacion(trabajador, propietario, puesto3);
		AsignacionBonificacion asignacion2= new AsignacionBonificacion(frecuente, propietario, puesto2);
		fachada.getInstancia().agregarBoniAsignada(asignacion2);
		fachada.getInstancia().agregarBoniAsignada(asignacion1);
		


	}
}
