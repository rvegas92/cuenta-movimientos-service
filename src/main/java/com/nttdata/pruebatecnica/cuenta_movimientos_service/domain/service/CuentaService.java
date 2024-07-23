package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;

public interface CuentaService {
	Iterable<CuentaDTO> getCuentas();
	CuentaDTO getCuentaById(Long id);
	CuentaDTO getTermSearch(String search);
	CuentaDTO saveCuenta(CuentaDTO cuentaDto);
    void deleteCuenta(Long id);
    String getReporte(String fechaInicio, String fechaFin, Long personaId);
}
