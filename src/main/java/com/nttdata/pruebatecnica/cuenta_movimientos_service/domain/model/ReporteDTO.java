package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model;

import lombok.Data;

@Data
public class ReporteDTO {

	private String fecha;
	private String cliente;
	private String numeroCuenta; 
	private String tipo; 
	private Double saldoInicial;
	private Boolean estado;
	private Double movimiento;
	private Double saldoDisponible;
	
}
