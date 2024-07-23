package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.persistence;

import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity.Movimiento;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.mapper.MovimientoMapper;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.MovimientoDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.MovimientoRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MovimientoRepositoryImplements implements MovimientoRepository{
	
	private final MovimientoRepositorySqlServer movimientoRepositorySqlServer;
	private final MovimientoMapper movimientoMapper;
	
	public MovimientoRepositoryImplements(MovimientoRepositorySqlServer movimientoRepositorySqlServer, MovimientoMapper movimientoMapper) {
        this.movimientoRepositorySqlServer = movimientoRepositorySqlServer;
        this.movimientoMapper = movimientoMapper;
    }
	
	@Override
	public Iterable<MovimientoDTO> getMovimientos() {
		// TODO Auto-generated method stub
		return this.movimientoMapper.toMovimientos(this.movimientoRepositorySqlServer.findAll());
	}

	@Override
	public Optional<MovimientoDTO> getMovimientoById(Long id) {
		// TODO Auto-generated method stub
		Movimiento movimiento = this.movimientoRepositorySqlServer.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso no encontrado")
        );
        return Optional.of(this.movimientoMapper.toMovimientoDTO(movimiento));
	}

	@Override
	public MovimientoDTO saveMovimiento(MovimientoDTO movimientoDto) {
		Movimiento movimiento = this.movimientoMapper.toMovimientoEntity(movimientoDto);
        log.info("Movimiento: "+ movimiento.toString());
        return this.movimientoMapper.toMovimientoDTO(this.movimientoRepositorySqlServer.save(movimiento));
	}

	@Override
	public void deleteMovimiento(Long id) {
		// TODO Auto-generated method stub
		Movimiento movimientoEntity = this.movimientoRepositorySqlServer.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso no encontrado")
        );
        this.movimientoRepositorySqlServer.deleteById(movimientoEntity.getMovimientoid());
	}

	@Override
	public Optional<MovimientoDTO> getMovimientoActualByFecha(Long idcuenta) {
		// TODO Auto-generated method stub
		Optional<Movimiento> movimiento = this.movimientoRepositorySqlServer.findByIdCuentaFecha(idcuenta);
		if (movimiento.isPresent()) {
	        return Optional.of(this.movimientoMapper.toMovimientoDTO(movimiento.get()));
	    } else {
	        return Optional.empty();
	    }
	}
}
