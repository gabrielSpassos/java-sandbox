package com.gabrielspassos.repository;

import com.gabrielspassos.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, String> {

    Optional<GameEntity> findByExecutionId(String executionId);

}
