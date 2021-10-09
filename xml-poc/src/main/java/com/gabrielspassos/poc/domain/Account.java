package com.gabrielspassos.poc.domain;

import domain.enumarator.AccountType;

import java.util.Objects;

public class Account {

    private Integer id;
    private String bankCode;
    private String branch;
    private String number;
    private AccountType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public static final class Builder {
        private Account account;

        private Builder() {
            account = new Account();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Integer id) {
            account.setId(id);
            return this;
        }

        public Builder bankCode(String bankCode) {
            account.setBankCode(bankCode);
            return this;
        }

        public Builder branch(String branch) {
            account.setBranch(branch);
            return this;
        }

        public Builder number(String number) {
            account.setNumber(number);
            return this;
        }

        public Builder type(AccountType type) {
            account.setType(type);
            return this;
        }

        public Account build() {
            return account;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(id, account.id)) return false;
        if (!Objects.equals(bankCode, account.bankCode)) return false;
        if (!Objects.equals(branch, account.branch)) return false;
        if (!Objects.equals(number, account.number)) return false;
        return type == account.type;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bankCode != null ? bankCode.hashCode() : 0);
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", bankCode='" + bankCode + '\'' +
                ", branch='" + branch + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
