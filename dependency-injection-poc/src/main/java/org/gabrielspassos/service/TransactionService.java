package org.gabrielspassos.service;

import org.gabrielspassos.dto.AccountDTO;
import org.gabrielspassos.dto.UserDTO;

public class TransactionService {

    private final AccountService accountService;

    private final UserService userService;

    public TransactionService(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    public AccountDTO updateAccountStatusToInactive(String userLogin, Long accountNumber) {
        UserDTO userDTO = userService.findActiveUsers()
                .stream()
                .filter(user -> userLogin.equals(user.getLogin()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        AccountDTO accountDTO = accountService.findActiveAccounts()
                .stream()
                .filter(account -> accountNumber.equals(account.getNumber()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid account"));

        accountDTO.setIsActive(false);
        //should persist this, but for this POC this doesn't matter
        return accountDTO;
    }
}
