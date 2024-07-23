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
@Table(name = "cuenta")
@Getter
@Setter
@ToString
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuentaid;
	private String numero_cuenta; 
	private String tipo_cuenta; 
	private Double saldo_inicial;
	private Boolean estado;
	private Long personaid;
	
}
