package com.desafio.bank.domain.exception;

public class InvalidDocumentException extends IllegalArgumentException {
    public InvalidDocumentException(String msg) {
        super(msg);
    }
}
