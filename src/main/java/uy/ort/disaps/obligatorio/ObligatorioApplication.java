package uy.ort.disaps.obligatorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

@SpringBootApplication
public class ObligatorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObligatorioApplication.class, args);
		cargarDatosDePrueba();
	}

	private static void cargarDatosDePrueba() {
		Admin administrador = new Admin();
	}
}
