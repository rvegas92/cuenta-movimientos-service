package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model;

import lombok.Data;

@Data
public class CuentaDTO {
	
	private Long cuentaid;
	private String numero_cuenta;
	private String tipo_cuenta; 
	private Double saldo_inicial;
	private Boolean estado;
	private Long personaid;
	
}
