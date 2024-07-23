package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.CuentaService;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	
	private final CuentaService cuentaService;
    
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<Iterable<CuentaDTO>> getCuentas() {
        return new ResponseEntity<>(this.cuentaService.getCuentas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCliente(@PathVariable Long id){
        log.info("Id "+id);
        log.info("Cuenta {}",this.cuentaService.getCuentaById(id));
        return new ResponseEntity<>(this.cuentaService.getCuentaById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<CuentaDTO> saveCliente(@RequestBody CuentaDTO cuentaDTO){
        return new ResponseEntity<>(this.cuentaService.saveCuenta(cuentaDTO), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<CuentaDTO> actualizarCliente(@RequestBody CuentaDTO cuentaDTO) {
        log.info("Updating cuenta with personaid: {}", cuentaDTO.getCuentaid());
        try {
        	CuentaDTO updatedCuenta = this.cuentaService.saveCuenta(cuentaDTO);
            return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error("Cuenta with id {} not found", cuentaDTO.getCuentaid());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id){
    	log.info("Deleting cuenta with id: {}", id);
        try {
        	this.cuentaService.deleteCuenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            log.error("Cuenta with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<CuentaDTO> getCuentaBuscar(@RequestParam String search) {
        log.info("Searching for cuenta with term: {}", search);
        CuentaDTO cuentaDTO = this.cuentaService.getTermSearch(search);
        return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
    }
    
}
