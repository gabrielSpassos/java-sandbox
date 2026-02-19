package com.gabrielspassos.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Cloneable {

    private final List<String> roles;

    public UserDTO() {
        this.roles = new ArrayList<>();
    }

    public UserDTO(List<String> roles) {
        this.roles = roles;
    }

    public boolean loadData() {
        this.roles.add("subscriber");
        return true;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean addRole(String role) {
        this.roles.add(role);
        return true;
    }

    public boolean removeRole(String role) {
        return this.roles.remove(role);
    }

    @Override
    public UserDTO clone() {
        var temp = new ArrayList<>(this.roles);
        return new UserDTO(temp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roles);
    }
}
