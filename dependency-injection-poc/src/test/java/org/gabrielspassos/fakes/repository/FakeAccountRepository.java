package org.gabrielspassos.fakes.repository;

import org.gabrielspassos.dto.AccountDTO;
import org.gabrielspassos.repository.AccountRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FakeAccountRepository implements AccountRepository {

    @Override
    public List<AccountDTO> findAll() {
        AccountDTO accountDTO = new AccountDTO(UUID.randomUUID().toString(), 123456L, false);
        return Collections.singletonList(accountDTO);
    }

}
