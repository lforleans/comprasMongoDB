package com.altran.desafio.exceptions;

public class DesafioException extends RuntimeException {
    public DesafioException(String message) {
        super(message);
    }

    public DesafioException(Exception e){
        super(e);
    }
}
