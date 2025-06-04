package com.gabrielspassos.api.request;

import java.util.Objects;
import java.util.UUID;

public class UpdatePersonRequest {

    private UUID uuid;
    private String firstName;
    private String lastName;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatePersonRequest that = (UpdatePersonRequest) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UpdatePersonRequest{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
