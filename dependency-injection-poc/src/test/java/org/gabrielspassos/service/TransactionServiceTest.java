package org.gabrielspassos.service;

import org.gabrielspassos.dto.AccountDTO;
import org.gabrielspassos.repository.AccountRepository;
import org.gabrielspassos.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceTest {

    @Test
    void shouldSetAccountAsInactive() {
        // is necessary to set all this "track" of dependencies to create the desired object
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        TransactionService transactionService = new TransactionService(accountService, userService);

        AccountDTO accountDTO = transactionService.updateAccountStatusToInactive("user@email.com", 123456L);

        assertNotNull(accountDTO);
        assertFalse(accountDTO.getIsActive());
    }

    @Test
    void shouldThrowErrorForNotValidUser() {
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        TransactionService transactionService = new TransactionService(accountService, userService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> transactionService.updateAccountStatusToInactive("test@email.com", 123456L));

        assertEquals("Invalid user", exception.getMessage());
    }

    @Test
    void shouldThrowErrorForNotValidAccount() {
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        TransactionService transactionService = new TransactionService(accountService, userService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> transactionService.updateAccountStatusToInactive("user@email.com", 56789L));

        assertEquals("Invalid account", exception.getMessage());
    }

}