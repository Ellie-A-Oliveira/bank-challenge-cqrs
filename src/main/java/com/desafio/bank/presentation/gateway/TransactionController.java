package com.desafio.bank.presentation.gateway;

import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.command.transaction.CreateTransactionCommand;
import com.desafio.bank.domain.entity.view.TransactionView;
import com.desafio.bank.domain.query.transaction.GetTransactionByIdQuery;
import com.desafio.bank.presentation.gateway.dto.request.CreateTransactionForAccountRequest;
import io.axoniq.axonserver.grpc.query.Query;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionView>> getById(@PathVariable UUID id) {
        GetTransactionByIdQuery query = new GetTransactionByIdQuery(id);
        List<TransactionView> transactionViews = queryGateway
                .query(query, ResponseTypes.multipleInstancesOf(TransactionView.class))
                .join();

        return ResponseEntity.ok(transactionViews);
    }

    @PostMapping
    public ResponseEntity<UUID> createForAccount(
            @Valid @RequestBody CreateTransactionForAccountRequest request) {
        CreateTransactionCommand command = new CreateTransactionCommand(
                request.amount(),
                request.transactionType(),
                request.accountId()
        );

        TransactionView transactionView = commandGateway
                .sendAndWait(command);

        return ResponseEntity.status(201).body(transactionView.getTransactionId());
    }
}
