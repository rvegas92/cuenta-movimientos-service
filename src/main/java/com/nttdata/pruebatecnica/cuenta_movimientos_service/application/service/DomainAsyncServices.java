package com.nttdata.pruebatecnica.cuenta_movimientos_service.application.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;

@Service
public class DomainAsyncServices {
	
	@Autowired
    private WebClient.Builder webClientBuilder;

    @Async
    public CompletableFuture<CuentaDTO> callBuscarEndpoint(String cliente) {
        return webClientBuilder.build()
                .get()
                .uri("https://clientepersonaservice-1c3339b4c909.herokuapp.com/clientes/buscar?search=" + cliente)
                .retrieve()
                .bodyToMono(CuentaDTO.class)
                .toFuture();
    }
    
}
