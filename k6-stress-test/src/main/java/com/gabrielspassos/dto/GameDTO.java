package com.gabrielspassos.dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GameDTO {

    private String executionId;
    private List<List<Integer>> dungeon;
    private Integer minimalHealth;

    public GameDTO(String executionId, List<List<Integer>> dungeon, Integer minimalHealth) {
        this.executionId = executionId;
        this.dungeon = Optional.ofNullable(dungeon).orElse(List.of());
        this.minimalHealth = minimalHealth;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public List<List<Integer>> getDungeon() {
        return dungeon;
    }

    public void setDungeon(List<List<Integer>> dungeon) {
        this.dungeon = dungeon;
    }

    public Integer getMinimalHealth() {
        return minimalHealth;
    }

    public void setMinimalHealth(Integer minimalHealth) {
        this.minimalHealth = minimalHealth;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameDTO gameDTO = (GameDTO) o;
        return Objects.equals(executionId, gameDTO.executionId)
                && Objects.equals(dungeon, gameDTO.dungeon)
                && Objects.equals(minimalHealth, gameDTO.minimalHealth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executionId, dungeon, minimalHealth);
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "executionId='" + executionId + '\'' +
                ", dungeon='" + dungeon + '\'' +
                ", minimalHealth=" + minimalHealth +
                '}';
    }
}
