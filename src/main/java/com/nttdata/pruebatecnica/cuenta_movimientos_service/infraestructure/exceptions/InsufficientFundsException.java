package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
