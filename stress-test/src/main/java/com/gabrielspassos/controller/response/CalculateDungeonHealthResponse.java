package com.gabrielspassos.controller.response;

import java.util.List;
import java.util.Objects;

public class CalculateDungeonHealthResponse {

    private String id;
    private List<List<Integer>> dungeon;
    private Integer minimalHealth;

    public CalculateDungeonHealthResponse() {
    }

    public CalculateDungeonHealthResponse(String id, List<List<Integer>> dungeon, Integer minimalHealth) {
        this.id = id;
        this.dungeon = dungeon;
        this.minimalHealth = minimalHealth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        CalculateDungeonHealthResponse that = (CalculateDungeonHealthResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dungeon, that.dungeon)
                && Objects.equals(minimalHealth, that.minimalHealth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dungeon, minimalHealth);
    }

    @Override
    public String toString() {
        return "CalculateDungeonHealthResponse{" +
                "id='" + id + '\'' +
                ", dungeon=" + dungeon +
                ", minimalHealth=" + minimalHealth +
                '}';
    }
}
