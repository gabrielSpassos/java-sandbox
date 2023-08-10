package org.gabrielspassos.dto;

public class AccountDTO {

    private String id;

    private Long number;

    private Boolean isActive;

    public AccountDTO() {
    }

    public AccountDTO(String id, Long number, Boolean isActive) {
        this.id = id;
        this.number = number;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", isActive=" + isActive +
                '}';
    }
}
