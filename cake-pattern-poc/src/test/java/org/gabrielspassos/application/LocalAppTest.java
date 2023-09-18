package org.gabrielspassos.application;

import org.gabrielspassos.application.fakes.application.FakeLocalApp;
import org.gabrielspassos.dto.AccountDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalAppTest {

    @Test
    void shouldReturnFindAllActives() {
        LocalApp localApp = new LocalApp();

        List<AccountDTO> activeAccounts = localApp.getAccountService().findActiveAccounts();

        assertNotNull(activeAccounts);
        assertFalse(activeAccounts.isEmpty());
    }

    @Test
    void shouldReturnAllAccounts() {
        LocalApp localApp = new LocalApp();

        List<AccountDTO> allAccounts = localApp.getAccountRepository().findAll();

        assertNotNull(allAccounts);
        assertFalse(allAccounts.isEmpty());
    }

    @Test
    void shouldReturnEmptyActiveAccounts() {
        FakeLocalApp fakeLocalApp = new FakeLocalApp();

        List<AccountDTO> activeAccounts = fakeLocalApp.getAccountService().findActiveAccounts();

        assertNotNull(activeAccounts);
        assertTrue(activeAccounts.isEmpty());
    }

}