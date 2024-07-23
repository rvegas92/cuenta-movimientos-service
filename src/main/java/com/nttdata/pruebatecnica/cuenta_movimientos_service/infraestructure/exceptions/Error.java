package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.exceptions;

import lombok.Data;

@Data
public class Error {
    private String code;
    private String message;

}
