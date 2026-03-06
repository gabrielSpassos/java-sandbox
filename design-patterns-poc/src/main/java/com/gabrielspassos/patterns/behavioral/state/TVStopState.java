package com.gabrielspassos.patterns.behavioral.state;

public class TVStopState implements State {

    @Override
    public boolean doAction() {
        IO.println("TV is turned OFF");
        return false;
    }

}
