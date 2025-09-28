package com.desafio.bank.infrastructure.interceptor.command;

import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.command.transaction.CreateTransactionCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class AccountCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final PasswordEncoder passwordEncoder;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            @Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            if (command.getPayload() instanceof CreateAccountCommand cmd) {
                CreateAccountCommand commandWithEncodedPassword = new CreateAccountCommand(
                        cmd.fullName(),
                        cmd.document(),
                        cmd.loginName(),
                        passwordEncoder.encode(cmd.password())
                );

                System.out.println(commandWithEncodedPassword);

                return GenericCommandMessage.asCommandMessage(commandWithEncodedPassword);
            }

            return command;
        };
    }
}
