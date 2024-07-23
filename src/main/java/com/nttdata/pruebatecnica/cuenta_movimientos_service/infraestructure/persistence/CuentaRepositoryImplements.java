package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity.Cuenta;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.mapper.CuentaMapper;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.CuentaRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CuentaRepositoryImplements implements CuentaRepository{
	
	private final CuentaRepositorySqlServer cuentaRepositorySqlServer;
	private final CuentaMapper cuentaMapper;
	
	public CuentaRepositoryImplements(CuentaRepositorySqlServer cuentaRepositorySqlServer, CuentaMapper cuentaMapper) {
        this.cuentaRepositorySqlServer = cuentaRepositorySqlServer;
        this.cuentaMapper = cuentaMapper;
    }
	
	@Override
	public Iterable<CuentaDTO> getCuentas() {
		// TODO Auto-generated method stub
		return this.cuentaMapper.toCuentas(this.cuentaRepositorySqlServer.findAll());
	}

	@Override
	public Optional<CuentaDTO> getCuentaById(Long id) {
		// TODO Auto-generated method stub
		Cuenta cuenta = this.cuentaRepositorySqlServer.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso no encontrado")
        );
        return Optional.of(this.cuentaMapper.toCuentaDTO(cuenta));
	}

	@Override
	public CuentaDTO saveCuenta(CuentaDTO cuentaDto) {
		Cuenta cuenta = this.cuentaMapper.toCuentaEntity(cuentaDto);
		log.info("Id persona: "+ cuenta.getCuentaid());
        return this.cuentaMapper.toCuentaDTO(this.cuentaRepositorySqlServer.save(cuenta));
	}

	@Override
	public void deleteCuenta(Long id) {
		// TODO Auto-generated method stub
		Cuenta cuentaEntity = this.cuentaRepositorySqlServer.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso no encontrado")
        );
        this.cuentaRepositorySqlServer.deleteById(cuentaEntity.getCuentaid());
	}

	@Override
	public CuentaDTO getTermSearch(String search) {
		// TODO Auto-generated method stub
		log.info("Buscando en cuentas por el termino: "+search);
		List<Cuenta> cuentas = this.cuentaRepositorySqlServer.findBySearchTerm(search);
	    if (cuentas.isEmpty()) {
	        throw new ResourceNotFoundException("Recurso no encontrado");
	    }
	    return this.cuentaMapper.toCuentaDTO(cuentas.get(0));
	}

}
