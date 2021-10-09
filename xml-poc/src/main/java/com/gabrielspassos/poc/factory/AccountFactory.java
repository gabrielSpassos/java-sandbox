package com.gabrielspassos.poc.factory;

import com.gabrielspassos.poc.domain.Account;
import domain.enumarator.AccountType;

public class AccountFactory {

    public static Account createCorrenteAccount() {
        return createAccount(1, "260", "0001", "123456", AccountType.CONTA_CORRENTE);
    }

    public static Account createPagamentoAccount() {
        return createAccount(2, "260", "0001", "9884874", AccountType.CONTA_PAGAMENTO);
    }

    public static Account createPoupancaAccount() {
        return createAccount(3, "031", "3242", "6432464", AccountType.CONTA_POUPANCA);
    }

    public static Account createSalarioAccount() {
        return createAccount(4, "260", "0001", "0043238", AccountType.CONTA_SALARIO);
    }

    private static Account createAccount(Integer id, String bankCode, String branch, String number, AccountType type) {
        return Account.Builder.builder()
                .id(id)
                .bankCode(bankCode)
                .branch(branch)
                .number(number)
                .type(type)
                .build();
    }
}
