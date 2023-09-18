package org.gabrielspassos.service;

import org.gabrielspassos.dto.AccountDTO;
import org.gabrielspassos.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {

    private final AccountRepository accountRepository;

    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> findActiveAccounts() {
        return accountRepository.findAll().stream()
                .filter(AccountDTO::getIsActive)
                .collect(Collectors.toList());
    }
}
