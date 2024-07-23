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
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.MovimientoDTO;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.service.MovimientoService;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions.InsufficientFundsException;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
	
	private final MovimientoService movimientoService;
    
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<MovimientoDTO>> getMovimientos() {
        return new ResponseEntity<>(this.movimientoService.getMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> getMovimiento(@PathVariable Long id){
        log.info("Id "+id);
        log.info("Movimiento {}",this.movimientoService.getMovimientoById(id));
        return new ResponseEntity<>(this.movimientoService.getMovimientoById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> saveMovimiento(@RequestBody MovimientoDTO movimientoDTO){
    	try {
    		return new ResponseEntity<>(this.movimientoService.saveMovimiento(movimientoDTO), HttpStatus.CREATED);
    	} catch (InsufficientFundsException e) {
            log.error("Insufficient funds for movimiento: {}", movimientoDTO);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping
    public ResponseEntity<MovimientoDTO> actualizarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        log.info("Updating Movimiento with personaid: {}", movimientoDTO.getMovimientoid());
        try {
        	MovimientoDTO updatedMovimiento = this.movimientoService.saveMovimiento(movimientoDTO);
            return new ResponseEntity<>(updatedMovimiento, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error("Cuenta with id {} not found", movimientoDTO.getMovimientoid());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id){
    	log.info("Deleting movimiento with id: {}", id);
        try {
        	this.movimientoService.deleteMovimiento(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            log.error("Movimiento with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
