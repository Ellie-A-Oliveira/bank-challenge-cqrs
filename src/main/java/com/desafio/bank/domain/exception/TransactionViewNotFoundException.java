package com.desafio.bank.domain.exception;

import jakarta.persistence.EntityNotFoundException;

public class TransactionViewNotFoundException extends EntityNotFoundException {
    public TransactionViewNotFoundException(String msg) {
        super(msg);
    }
}
