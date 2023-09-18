package org.gabrielspassos.service;

import org.gabrielspassos.repository.AccountRepositoryComponent;

public interface AccountServiceComponent extends AccountRepositoryComponent {

    default AccountService getAccountService() {
        return new AccountService(getAccountRepository());
    }

}
