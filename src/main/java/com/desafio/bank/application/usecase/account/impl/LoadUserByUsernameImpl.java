package com.desafio.bank.application.usecase.account.impl;

import com.desafio.bank.application.usecase.account.GetAccountByLoginName;
import com.desafio.bank.application.usecase.account.LoadUserByUsername;
import com.desafio.bank.domain.entity.view.AccountView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadUserByUsernameImpl implements LoadUserByUsername {
    private final GetAccountByLoginName getAccountByLoginName;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountView accountView = getAccountByLoginName.execute(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
            }

            @Override
            public String getPassword() {
                return accountView.getPasswordHash();
            }

            @Override
            public String getUsername() {
                return accountView.getLoginName();
            }
        };
    }
}
