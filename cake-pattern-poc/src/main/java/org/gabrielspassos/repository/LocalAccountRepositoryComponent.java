package org.gabrielspassos.repository;

import org.gabrielspassos.dto.AccountDTO;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public interface LocalAccountRepositoryComponent extends AccountRepositoryComponent {

    default AccountRepository getAccountRepository() {
        return new AccountRepository() {
            @Override
            public List<AccountDTO> findAll() {
                AccountDTO accountDTO = new AccountDTO(UUID.randomUUID().toString(), 123456L, true);
                return Collections.singletonList(accountDTO);
            }
        };
    }

}
