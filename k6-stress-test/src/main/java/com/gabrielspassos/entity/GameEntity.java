package com.gabrielspassos.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.Objects;

@Table("game")
public class GameEntity {

    @Id
    @Column("id")
    private String id;

    @Column("execution_id")
    private String executionId;

    @Column("dungeon")
    private String dungeon;

    @Column("minimal_health")
    private Integer minimalHealth;

    @Column("execution_time")
    private OffsetDateTime executionTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getDungeon() {
        return dungeon;
    }

    public void setDungeon(String dungeon) {
        this.dungeon = dungeon;
    }

    public Integer getMinimalHealth() {
        return minimalHealth;
    }

    public void setMinimalHealth(Integer minimalHealth) {
        this.minimalHealth = minimalHealth;
    }

    public OffsetDateTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(OffsetDateTime executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(executionId, that.executionId)
                && Objects.equals(dungeon, that.dungeon)
                && Objects.equals(minimalHealth, that.minimalHealth)
                && Objects.equals(executionTime, that.executionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, executionId, dungeon, minimalHealth, executionTime);
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "id='" + id + '\'' +
                ", executionId='" + executionId + '\'' +
                ", dungeon='" + dungeon + '\'' +
                ", minimalHealth=" + minimalHealth +
                ", executionTime=" + executionTime +
                '}';
    }
}
