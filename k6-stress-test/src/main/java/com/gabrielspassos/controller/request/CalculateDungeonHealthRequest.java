package com.gabrielspassos.controller.request;

import java.util.List;
import java.util.Objects;

public class CalculateDungeonHealthRequest {

    private String id;
    private List<List<Integer>> dungeon;

    public CalculateDungeonHealthRequest() {
    }

    public CalculateDungeonHealthRequest(String id, List<List<Integer>> dungeon) {
        this.id = id;
        this.dungeon = dungeon;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculateDungeonHealthRequest that = (CalculateDungeonHealthRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(dungeon, that.dungeon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dungeon);
    }

    @Override
    public String toString() {
        return "CalculateDungeonHealthRequest{" +
                "id='" + id + '\'' +
                ", dungeon=" + dungeon +
                '}';
    }
}
