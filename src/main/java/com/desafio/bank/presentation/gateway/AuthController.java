package com.desafio.bank.presentation.gateway;

import com.desafio.bank.application.usecase.account.GetAccountByLoginName;
import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.infrastructure.config.JwtTokenUtil;
import com.desafio.bank.presentation.gateway.dto.request.CreateAccountRequest;
import com.desafio.bank.presentation.gateway.dto.request.LoginToAccountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    // Use cases
    private final GetAccountByLoginName getAccountByLoginName;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody CreateAccountRequest request) {
        if (getAccountByLoginName.execute(request.loginName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        CreateAccountCommand command = new CreateAccountCommand(
                request.fullName(),
                request.document(),
                request.loginName(),
                request.password()
        );

        AccountView accountView = commandGateway.sendAndWait(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountView.getAccountId().toString());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginToAccountRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.loginName(),
                        request.password())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(token);
    }
}
