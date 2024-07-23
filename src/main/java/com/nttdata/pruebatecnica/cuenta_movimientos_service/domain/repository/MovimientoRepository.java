package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository;

import java.util.Optional;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.MovimientoDTO;

public interface MovimientoRepository {
	Iterable<MovimientoDTO> getMovimientos();
	Optional<MovimientoDTO> getMovimientoById (Long id);
	Optional<MovimientoDTO> getMovimientoActualByFecha(Long idcuenta);
	MovimientoDTO saveMovimiento(MovimientoDTO movimientoDto);
    void deleteMovimiento(Long id);
}
