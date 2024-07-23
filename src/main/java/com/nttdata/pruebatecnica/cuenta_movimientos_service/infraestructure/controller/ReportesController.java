package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.application.service.DomainAsyncServices;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.CuentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/reportes")
public class ReportesController {
	
	private final CuentaService cuentaService;
	private final DomainAsyncServices domainAsyncServices;
    
    public ReportesController(CuentaService cuentaService, DomainAsyncServices domainAsyncServices) {
        this.cuentaService = cuentaService;
        this.domainAsyncServices = domainAsyncServices;
    }
    
    @GetMapping("/estadocuenta")
    public CompletableFuture<ResponseEntity<?>> getEstadoCuenta(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaInicio, 
    	@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaFin, 
    	@RequestParam String cliente){
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedFechaInicio = sdf.format(fechaInicio);
        String formattedFechaFin = sdf.format(fechaFin);

        //consultar microservcio cliente para traer sus datos por nombre de cliente
        return this.domainAsyncServices.callBuscarEndpoint(cliente)
        .thenApply(clienteData -> {
            log.info("Datos del cliente: " + clienteData);
            log.info("Datos para reporte: " + formattedFechaInicio + "," + formattedFechaFin + "," + cliente);
            String reporteJson = this.cuentaService.getReporte(formattedFechaInicio, formattedFechaFin, clienteData.getPersonaid());
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Object reporteObject = objectMapper.readValue(reporteJson, Object.class);
                return new ResponseEntity<>(reporteObject, HttpStatus.OK);
            } catch (IOException e) {
                log.error("Error al parsear el JSON del reporte", e);
                return new ResponseEntity<>("Error al procesar el reporte", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        })
        .exceptionally(ex -> {
            log.error("Error al obtener el reporte", ex);
            return new ResponseEntity<>("Error al obtener el reporte", HttpStatus.INTERNAL_SERVER_ERROR);
        });
    }

}
