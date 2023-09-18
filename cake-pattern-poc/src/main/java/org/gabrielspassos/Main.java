package org.gabrielspassos;

import org.gabrielspassos.application.LocalApp;

public class Main {
    public static void main(String[] args) {
        LocalApp localApp = new LocalApp();
        System.out.println(localApp.getAccountService().findActiveAccounts());
    }
}