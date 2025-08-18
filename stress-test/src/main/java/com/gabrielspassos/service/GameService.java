package com.gabrielspassos.service;

import com.gabrielspassos.dto.GameDTO;
import com.gabrielspassos.entity.GameEntity;
import com.gabrielspassos.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameEntity save(GameDTO gameDTO) {
        var gameEntity = buildGameEntity(gameDTO);
        return gameRepository.save(gameEntity);
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
