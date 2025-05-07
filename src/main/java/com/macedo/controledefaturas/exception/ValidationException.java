package com.macedo.controledefaturas.exception;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super("Ops! Não foi possível validar suas informações.");
    }

    public ValidationException(String message) {
        super(message);
    }
}
