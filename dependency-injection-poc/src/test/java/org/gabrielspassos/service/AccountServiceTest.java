package org.gabrielspassos.service;

import org.gabrielspassos.dto.AccountDTO;
import org.gabrielspassos.fakes.repository.FakeAccountRepository;
import org.gabrielspassos.repository.AccountRepository;
import org.gabrielspassos.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountServiceTest {

    @Test
    void shouldReturnActiveAccounts() {
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
        AccountService accountService = new AccountService(accountRepository);

        List<AccountDTO> activeAccounts = accountService.findActiveAccounts();

        assertNotNull(activeAccounts);
        assertFalse(activeAccounts.isEmpty());
    }

    @Test
    void shouldReturnEmptyActiveAccounts() {
        AccountRepository fakeAccountRepository = new FakeAccountRepository();
        // Easy way to mock/fake the customer repository
        AccountService accountService = new AccountService(fakeAccountRepository);

        List<AccountDTO> activeAccounts = accountService.findActiveAccounts();

        assertNotNull(activeAccounts);
        assertTrue(activeAccounts.isEmpty());
    }

}