package com.desafio.bank.application.usecase.account;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface LoadUserByUsername extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
