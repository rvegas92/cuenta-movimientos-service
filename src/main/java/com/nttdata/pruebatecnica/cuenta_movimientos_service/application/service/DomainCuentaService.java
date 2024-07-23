package com.nttdata.pruebatecnica.cuenta_movimientos_service.application.service;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.CuentaRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.CuentaService;

public class DomainCuentaService implements CuentaService{
	
	private final CuentaRepository cuentaRepository;
	
	public DomainCuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
	
	@Override
	public Iterable<CuentaDTO> getCuentas() {
		// TODO Auto-generated method stub
		return this.cuentaRepository.getCuentas();
	}

	@Override
	public CuentaDTO getCuentaById(Long id) {
		// TODO Auto-generated method stub
		return this.cuentaRepository.getCuentaById(id).get();
	}

	@Override
	public CuentaDTO saveCuenta(CuentaDTO cuentaDto) {
		// TODO Auto-generated method stub
		return this.cuentaRepository.saveCuenta(cuentaDto);
	}

	@Override
	public void deleteCuenta(Long id) {
		// TODO Auto-generated method stub
		this.cuentaRepository.deleteCuenta(id);
	}

	@Override
	public CuentaDTO getTermSearch(String search) {
		// TODO Auto-generated method stub
		return this.cuentaRepository.getTermSearch(search);
	}

}
