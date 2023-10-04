package org.gabrielspassos;

import org.gabrielspassos.repository.AccountRepositoryImpl;
import org.gabrielspassos.repository.UserRepository;
import org.gabrielspassos.service.AccountService;
import org.gabrielspassos.service.CustomerService;
import org.gabrielspassos.service.TransactionService;
import org.gabrielspassos.service.UserService;

public class Main {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        System.out.println(customerService.findActiveCustomers());

        // constructor dependency injection
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
        AccountService accountService = new AccountService(accountRepository);
        System.out.println(accountService.findActiveAccounts());

        // setter dependency injection
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        System.out.println(userService.findActiveUsers());

        TransactionService transactionService = new TransactionService(accountService, userService);
        System.out.println(transactionService.updateAccountStatusToInactive("user@email.com", 123456L));
    }
}