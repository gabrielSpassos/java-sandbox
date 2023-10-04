package org.gabrielspassos.repository;

import org.gabrielspassos.dto.AccountDTO;

import java.util.List;

public interface AccountRepository {
    List<AccountDTO> findAll();
}
