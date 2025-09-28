package com.desafio.bank.presentation.gateway;

import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.entity.view.AccountView;
import com.desafio.bank.domain.query.account.GetAccountByIdQuery;
import com.desafio.bank.presentation.gateway.dto.request.CreateAccountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public ResponseEntity<AccountView> getById(@PathVariable UUID id) {
        GetAccountByIdQuery query = new GetAccountByIdQuery(id);

        AccountView accountView = queryGateway
                .query(query, AccountView.class)
                .join();

        return ResponseEntity.ok(accountView);
    }

    @PostMapping
    public ResponseEntity<UUID> create(
            @Valid @RequestBody CreateAccountRequest request) {
        CreateAccountCommand command = new CreateAccountCommand(
                request.fullName(),
                request.document(),
                request.loginName(),
                request.passwordHash()
        );

        AccountView accountView = commandGateway.sendAndWait(command);
        return ResponseEntity.status(201).body(accountView.getAccountId());
    }
}
