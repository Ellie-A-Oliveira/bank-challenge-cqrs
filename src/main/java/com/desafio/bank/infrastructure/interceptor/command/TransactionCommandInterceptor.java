package com.desafio.bank.infrastructure.interceptor.command;

import com.desafio.bank.domain.command.transaction.CreateTransactionCommand;
import com.desafio.bank.infrastructure.repository.AccountViewRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class TransactionCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final AccountViewRepository accountRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            if (command.getPayload() instanceof CreateTransactionCommand cmd) {
                if (!accountRepository.existsById(cmd.accountId())) {
                    throw new IllegalArgumentException(
                            "Account does not exist: " + cmd.accountId()
                    );
                }
            }
            return command;
        };
    }
}
