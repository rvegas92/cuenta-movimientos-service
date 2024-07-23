package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository;

import java.util.Optional;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;

public interface CuentaRepository {
	Iterable<CuentaDTO> getCuentas();
	Optional<CuentaDTO> getCuentaById (Long id);
	CuentaDTO getTermSearch(String search);
	CuentaDTO saveCuenta (CuentaDTO clienteDto);
    void deleteCuenta(Long id);
}
