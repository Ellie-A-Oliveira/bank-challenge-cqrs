package com.desafio.bank.application.handler.saga.transaction;

import com.desafio.bank.domain.command.account.UpdateBalanceCommand;
import com.desafio.bank.domain.event.account.BalanceUpdatedEvent;
import com.desafio.bank.domain.event.transaction.TransactionCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Saga
@NoArgsConstructor
public class TransactionSaga {
    @Autowired
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "transactionId")
    public void on(TransactionCreatedEvent evt) {
        SagaLifecycle.associateWith("accountId", evt.accountId().toString());

        UpdateBalanceCommand cmd = new UpdateBalanceCommand(
                evt.accountId(),
                evt.amount(),
                evt.transactionType()
        );
        commandGateway.send(cmd);
    }

    @SagaEventHandler(associationProperty = "accountId")
    @EndSaga
    public void on(BalanceUpdatedEvent evt) { }
}
