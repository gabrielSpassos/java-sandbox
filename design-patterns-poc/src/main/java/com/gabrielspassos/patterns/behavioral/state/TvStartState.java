package com.gabrielspassos.patterns.behavioral.state;

public class TvStartState implements State {

    @Override
    public boolean doAction() {
        IO.println("TV is turned ON");
        return true;
    }

}
