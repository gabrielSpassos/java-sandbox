package com.gabrielspassos.challenge16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
The game should start with 10 mosquitos, 1 exterminator.
The game should have a internal matrix of 100x100.
Every 1s the mosquito should move to a random position.
Every 1s the exterminator should move to a spesific position.
If the mosquito and the exterminator are in the same position, the mosquito should die.
If the mosquito moves 5 times without being killed, it should reproduce if there is a mosquito nearby.
The game should have a method that can return the number of mosquitos killed.
The game should have a method that can return the number of mosquitos alive.
The mosquito can walk in any direction (up, down, left, right, diagonals).
The exterminator can walk in one direction, it should swap the metrixs.
The exterminator can walk from the bottown left corner to the top right corner them from the botton to the top them return on the same route.
*/
public class Game {

    private static final int SIZE = 100;
    private static final int MOSQUITOS_INIT_COUNT = 10;

    private final Character[][] board = new Character[SIZE][SIZE];

    private final List<Mosquito> mosquitos = new ArrayList<>();
    private final Exterminator exterminator;

    private int killedMosquitos;
    private int aliveMosquitos;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public Game() {
        this.killedMosquitos = 0;
        this.aliveMosquitos = 0;

        exterminator = new Exterminator(new Position<>(0, 0));
        board[0][0] = exterminator;

        spawnInitialMosquitos();
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::tick, 0, 1, TimeUnit.SECONDS);
    }

    private void tick() {

        // 1) move mosquitos
        moveMosquitos();

        // 2) move exterminator (kills happen here)
        //moveExterminator();

        // 3) update counters
        aliveMosquitos = mosquitos.size();

        // debug
        System.out.println("Alive=" + aliveMosquitos + " | Killed=" + killedMosquitos);
    }

    private void moveMosquitos() {
        for (Mosquito m : new ArrayList<>(mosquitos)) {
            var moveResult = m.move(board);
            var newMosquitos = moveResult.left();
            mosquitos.addAll((Collection<? extends Mosquito>) newMosquitos);
        }
    }

    private void spawnInitialMosquitos() {
        for (int i = 0; i < MOSQUITOS_INIT_COUNT; i++) {
            var pos = randomPosition();
            var m = new Mosquito(randomPosition());
            mosquitos.add(m);

            board[pos.column()][pos.row()] = m;
        }

        aliveMosquitos = mosquitos.size();
    }

    private Position<Integer, Integer> randomPosition() {
        int r = ThreadLocalRandom.current().nextInt(SIZE);
        int c = ThreadLocalRandom.current().nextInt(SIZE);
        return new Position<>(r, c);
    }
}
