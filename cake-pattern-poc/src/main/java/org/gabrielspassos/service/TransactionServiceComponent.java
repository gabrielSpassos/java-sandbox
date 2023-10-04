package org.gabrielspassos.service;

public interface TransactionServiceComponent extends AccountServiceComponent, UserServiceComponent {

    default TransactionService getTransactionService() {
        return new TransactionService(getAccountService(), getUserService());
    }

}
