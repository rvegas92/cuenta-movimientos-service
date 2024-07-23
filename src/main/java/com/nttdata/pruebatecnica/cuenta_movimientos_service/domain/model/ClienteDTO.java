package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model;

import lombok.Data;

@Data
public class ClienteDTO {
	
	private Long personaid;
	private String nombre; 
	private String genero; 
	private Integer edad;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String contrasenia; 
	private Boolean estado;
    
}
