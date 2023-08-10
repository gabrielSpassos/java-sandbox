package org.gabrielspassos;

import org.gabrielspassos.repository.AccountRepository;
import org.gabrielspassos.repository.UserRepository;
import org.gabrielspassos.service.AccountService;
import org.gabrielspassos.service.CustomerService;
import org.gabrielspassos.service.UserService;

public class Main {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        System.out.println(customerService.findActiveCustomers());

        // constructor dependency injection
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        System.out.println(accountService.findActiveAccounts());

        // setter dependency injection
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        System.out.println(userService.findActiveUsers());
    }
}