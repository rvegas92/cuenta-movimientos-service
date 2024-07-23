package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.application.service.DomainMovimientoService;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.CuentaRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.MovimientoRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.MovimientoService;

@Configuration
public class BeanMovimientoConfiguration {

    @Bean
    MovimientoService movimientoBeanService(final MovimientoRepository movimientoRepository,
    		final CuentaRepository cuentaRepository){
        return new DomainMovimientoService(movimientoRepository, cuentaRepository);
    }
}
