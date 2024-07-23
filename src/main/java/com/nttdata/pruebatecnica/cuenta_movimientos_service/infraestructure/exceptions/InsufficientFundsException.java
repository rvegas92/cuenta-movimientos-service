package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions;

public class InsufficientFundsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String message) {
        super(message);
    }
}
