package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class MovimientoDTO {
	
	private Long movimientoid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String Fecha;
	private String tipo_movimiento;
	private Double valor; 
	private Double saldo;
	private Long cuentaid;
	private String numerocuenta;
	
}
