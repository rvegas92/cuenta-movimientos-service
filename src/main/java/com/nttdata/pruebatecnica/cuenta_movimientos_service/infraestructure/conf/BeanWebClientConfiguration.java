package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanWebClientConfiguration {
	
	@Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
