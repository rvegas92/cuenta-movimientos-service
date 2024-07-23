package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.MovimientoDTO;

public interface MovimientoService {
	Iterable<MovimientoDTO> getMovimientos();
	MovimientoDTO getMovimientoById(Long id);
	MovimientoDTO saveMovimiento(MovimientoDTO movimientoDto);
    void deleteMovimiento(Long id);
}
