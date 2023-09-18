package org.gabrielspassos.application.fakes.repository;

import org.gabrielspassos.dto.AccountDTO;
import org.gabrielspassos.repository.AccountRepository;
import org.gabrielspassos.repository.AccountRepositoryComponent;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public interface FakeLocalAccountRepositoryComponent extends AccountRepositoryComponent {

    default AccountRepository getAccountRepository() {
        return new AccountRepository() {
            @Override
            public List<AccountDTO> findAll() {
                AccountDTO accountDTO = new AccountDTO(UUID.randomUUID().toString(), 123456L, false);
                return Collections.singletonList(accountDTO);
            }
        };
    }
}
