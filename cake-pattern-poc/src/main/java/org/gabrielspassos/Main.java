package org.gabrielspassos;

import org.gabrielspassos.application.LocalApp;

public class Main {
    public static void main(String[] args) {
        LocalApp localApp = new LocalApp();
        //local app has access to the repositories
        System.out.println(localApp.getAccountService().findActiveAccounts());
        System.out.println(localApp.getUserService().findActiveUsers());
        System.out.println(localApp.getTransactionService().updateAccountStatusToInactive("user@email.com", 123456L));
    }
}