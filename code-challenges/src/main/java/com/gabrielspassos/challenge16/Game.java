package com.gabrielspassos.challenge16;

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

    private static final int[][] BOARD = new int[100][100];
    private static final int MOSQUITOS_INIT_COUNT = 10;
    private static final int EXTERMINATOR_COUNT = 1;

    private int killedMosquitos;
    private int aliveMosquitos;

    public Game() {
        this.killedMosquitos = 0;
        this.aliveMosquitos = 0;
    }
}
