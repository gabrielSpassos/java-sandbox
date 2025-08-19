package com.gabrielspassos.service;

import com.gabrielspassos.dto.GameDTO;
import com.gabrielspassos.entity.GameEntity;
import com.gabrielspassos.exceptions.HttpException;
import com.gabrielspassos.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameEntity save(GameDTO gameDTO) {
        var gameEntity = buildGameEntity(gameDTO);
        return gameRepository.save(gameEntity);
    }

    public GameEntity findByExecutionId(String executionId) {
        return gameRepository.findByExecutionId(executionId)
                .orElseThrow(() -> new HttpException(404, "Game not found for execution ID: " + executionId));
    }

    public List<GameEntity> findAll() {
        return StreamSupport.stream(gameRepository.findAll().spliterator(), false).toList();
    }

    private GameEntity buildGameEntity(GameDTO gameDTO) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setExecutionId(gameDTO.getExecutionId());
        gameEntity.setDungeon(gameDTO.getDungeon().toString());
        gameEntity.setMinimalHealth(gameDTO.getMinimalHealth());
        gameEntity.setExecutionTime(OffsetDateTime.now());
        return gameEntity;
    }
}
