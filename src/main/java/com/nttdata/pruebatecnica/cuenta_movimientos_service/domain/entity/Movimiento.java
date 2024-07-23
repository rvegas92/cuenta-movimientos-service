package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
