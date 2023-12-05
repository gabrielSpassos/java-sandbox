package org.gabrielspassos.dto;

import java.time.LocalDate;

public class TaskDTO {

    private String name;

    private LocalDate date;

    public TaskDTO(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
