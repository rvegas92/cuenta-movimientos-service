package com.nttdata.pruebatecnica.cuenta_movimientos_service.application.service;

import java.util.Optional;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.MovimientoDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.CuentaRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.MovimientoRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.MovimientoService;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions.InsufficientFundsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainMovimientoService implements MovimientoService{
	
	private final MovimientoRepository movimientoRepository;
	private final CuentaRepository cuentaRepository;
	
	public DomainMovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }
	
	@Override
	public Iterable<MovimientoDTO> getMovimientos() {
		// TODO Auto-generated method stub
		return this.movimientoRepository.getMovimientos();
	}

	@Override
	public MovimientoDTO getMovimientoById(Long id) {
		// TODO Auto-generated method stub
		return this.movimientoRepository.getMovimientoById(id).get();
	}

	@Override
	public MovimientoDTO saveMovimiento(MovimientoDTO movimientoDto) {
		// TODO Auto-generated method stub
		CuentaDTO cuentaDto = this.cuentaRepository.getTermSearch(movimientoDto.getNumerocuenta());
		//comprobamos si existe y obtenemos el ultimo movimiento por fecha con su saldo actual
		Optional<MovimientoDTO> ultimoMovimiento = this.movimientoRepository.getMovimientoActualByFecha(cuentaDto.getCuentaid());
		//si existe tomar para calculo su saldo sino se registra su primer movimiento con el saldo_inicial para calculo
		Double res = 0.0;
		if(ultimoMovimiento.isPresent()) res = ultimoMovimiento.get().getSaldo() + movimientoDto.getValor();
		else res = cuentaDto.getSaldo_inicial() + movimientoDto.getValor();
		log.info("Sumatoria: "+res);
		//para ambos casos validar que el saldo calculado no sea menor a 0 
		if(res < 0) throw new InsufficientFundsException("Saldo no disponible");
		
		movimientoDto.setSaldo(res);
		movimientoDto.setCuentaid(cuentaDto.getCuentaid());
		return this.movimientoRepository.saveMovimiento(movimientoDto);

	}

	@Override
	public void deleteMovimiento(Long id) {
		// TODO Auto-generated method stub
		this.movimientoRepository.deleteMovimiento(id);
	}

}
