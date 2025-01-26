package com.start.CityVibe.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
    public OperacaoNaoPermitidaException(String message) {
        super(message);
    }
}