package com.desafio.bank.infrastructure.interceptor.command;

import com.desafio.bank.domain.command.account.CreateAccountCommand;
import com.desafio.bank.domain.command.transaction.CreateTransactionCommand;
import com.desafio.bank.domain.exception.InvalidDocumentException;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                if (!isValidDocument(cmd.document())) {
                    throw new InvalidDocumentException("Invalid document provided.");
                }
                CreateAccountCommand commandWithEncodedPassword = new CreateAccountCommand(
                        cmd.fullName(),
                        cmd.document(),
                        passwordEncoder.encode(cmd.password())
                );

                return GenericCommandMessage.asCommandMessage(commandWithEncodedPassword);
            }

            return command;
        };
    }

    private boolean isValidDocument(String document) {
        if (document == null) return false;

        String documentNumbers = document.replaceAll("[^0-9]", "");
        if (documentNumbers.length() != 11 || !documentNumbers.matches("[0-9]{11}")) {
            return false;
        }

        if (documentNumbers.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        int weight = 10;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(documentNumbers.charAt(i)) * weight--;
        }

        int d1 = 11 - (sum % 11);
        if (d1 == 10 || d1 == 11) d1 = 0;

        sum = 0;
        weight = 11;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(documentNumbers.charAt(i)) * weight--;
        }

        int d2 = 11 - (sum % 11);
        if (d2 == 10 || d2 == 11) d2 = 0;

        return d1 == Character.getNumericValue(documentNumbers.charAt(9))
                && d2 == Character.getNumericValue(documentNumbers.charAt(10));
    }
}
