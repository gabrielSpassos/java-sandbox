package com.gabrielspassos.dto;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class PersonDTO {

    private Long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;

    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id)
                && Objects.equals(uuid, personDTO.uuid)
                && Objects.equals(firstName, personDTO.firstName)
                && Objects.equals(lastName, personDTO.lastName)
                && Objects.equals(createdAt, personDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, firstName, lastName, createdAt);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
