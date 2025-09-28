package com.desafio.bank.domain.exception;

import jakarta.persistence.EntityNotFoundException;

public class AccountViewNotFoundException extends EntityNotFoundException {
    public AccountViewNotFoundException(String msg) {
        super(msg);
    }
}
