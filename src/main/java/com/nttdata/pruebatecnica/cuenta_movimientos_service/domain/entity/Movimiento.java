package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
@ToString
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movimientoid;
	private String fecha; 
	private String tipo_movimiento; 
	private Double valor;
	private Double saldo;
	private Long cuentaid;
	
}
