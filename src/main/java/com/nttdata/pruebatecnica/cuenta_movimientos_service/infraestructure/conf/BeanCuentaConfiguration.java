package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.application.service.DomainCuentaService;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.repository.CuentaRepository;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.CuentaService;

@Configuration
public class BeanCuentaConfiguration {

    @Bean
    CuentaService cuentaBeanService(final CuentaRepository cuentaRepository){
        return new DomainCuentaService(cuentaRepository);
    }
}
