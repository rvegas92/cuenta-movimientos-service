package com.nttdata.pruebatecnica.cuenta_movimientos_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CuentaMovimientosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentaMovimientosServiceApplication.class, args);
	}

}
