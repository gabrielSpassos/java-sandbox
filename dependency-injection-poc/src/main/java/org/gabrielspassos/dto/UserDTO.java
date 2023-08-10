package org.gabrielspassos.dto;

public class UserDTO {

    private String id;

    private String login;

    private boolean isActive;

    public UserDTO() {
    }

    public UserDTO(String id, String login, boolean isActive) {
        this.id = id;
        this.login = login;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
