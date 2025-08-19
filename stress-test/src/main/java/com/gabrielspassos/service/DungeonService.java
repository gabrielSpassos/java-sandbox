package com.gabrielspassos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import com.gabrielspassos.controller.response.CalculateDungeonHealthResponse;
import com.gabrielspassos.dto.GameDTO;
import com.gabrielspassos.entity.GameEntity;
import com.gabrielspassos.exceptions.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DungeonService {

    @Autowired
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    public List<CalculateDungeonHealthResponse> findAll() {
        return gameService.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public CalculateDungeonHealthResponse findById(String id) {
        var gameEntity = gameService.findByExecutionId(id);
        return convertToResponse(gameEntity);
    }

    public CalculateDungeonHealthResponse calculateMinimumHealth(CalculateDungeonHealthRequest request) {
        var dungeon = request.getDungeon();

        if (null == dungeon || dungeon.isEmpty() || dungeon.getFirst().isEmpty()) {
            return new CalculateDungeonHealthResponse(request.getId(), dungeon, 1);
        }

        int m = dungeon.size();
        int n = dungeon.getFirst().size();
        System.out.print("Dungeon: ");
        printDungeon(dungeon);

        // dp[i][j] represents the minimum health needed to reach the princess from cell (i,j)
        int[][] dp = new int[m][n];
        System.out.print("Start new board: ");
        printDungeon(dp);

        // Start from the princess' cell (bottom-right)
        dp[m-1][n-1] = Math.max(1, 1 - dungeon.get(m-1).get(n-1));
        System.out.print("Fill last cell: ");
        printDungeon(dp);

        // Fill the last column
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon.get(i).get(n-1));
        }
        System.out.print("Fill last column: ");
        printDungeon(dp);

        // Fill the last row
        for (int j = n - 2; j >= 0; j--) {
            dp[m-1][j] = Math.max(1, dp[m-1][j+1] - dungeon.get(m-1).get(j));
        }
        System.out.print("Fill last row: ");
        printDungeon(dp);

        // Fill the rest of the dp table
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int minHealthOnExit = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(1, minHealthOnExit - dungeon.get(i).get(j));
            }
        }
        System.out.print("Remaining board: ");
        printDungeon(dp);

        var executionId = request.getId();
        var minimalHealth = dp[0][0];
        var gameDTO = new GameDTO(executionId, dungeon, minimalHealth);
        gameService.save(gameDTO);

        return new CalculateDungeonHealthResponse(executionId, dungeon, minimalHealth);
    }

    private static void printDungeon(List<List<Integer>> dungeon) {
        System.out.println(dungeon);
    }

    private static void printDungeon(int[][] dp) {
        System.out.println(Arrays.deepToString(dp));
    }

    private CalculateDungeonHealthResponse convertToResponse(GameEntity gameEntity) {
        List<List<Integer>> dungeon = convertToDungeon(gameEntity);
        return new CalculateDungeonHealthResponse(gameEntity.getExecutionId(), dungeon, gameEntity.getMinimalHealth());
    }

    private List<List<Integer>> convertToDungeon(GameEntity gameEntity) {
        try {
            return objectMapper.readValue(gameEntity.getDungeon(), new TypeReference<>() {});
        } catch (Exception e) {
            throw new HttpException(500, "Error parsing dungeon data");
        }
    }
}
