package com.gabrielspassos.poc.domain;

import domain.enumarator.PersonType;

import java.util.List;
import java.util.Objects;

public class Customer {

    private Integer id;
    private String name;
    private String document;
    private PersonType type;
    private String email;
    private Boolean isActive;
    private List<Account> accounts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        this.isActive = active;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String document;
        private PersonType type;
        private String email;
        private Boolean isActive;
        private List<Account> accounts;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder document(String document) {
            this.document = document;
            return this;
        }

        public Builder type(PersonType type) {
            this.type = type;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder accounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            customer.setDocument(document);
            customer.setType(type);
            customer.setEmail(email);
            customer.setIsActive(isActive);
            customer.setAccounts(accounts);
            return customer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(id, customer.id)) return false;
        if (!Objects.equals(name, customer.name)) return false;
        if (!Objects.equals(document, customer.document)) return false;
        if (type != customer.type) return false;
        if (!Objects.equals(email, customer.email)) return false;
        if (!Objects.equals(isActive, customer.isActive)) return false;
        return Objects.equals(accounts, customer.accounts);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (accounts != null ? accounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", accounts=" + accounts +
                '}';
    }
}
