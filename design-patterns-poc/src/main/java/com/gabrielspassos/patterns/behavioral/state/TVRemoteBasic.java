package com.gabrielspassos.patterns.behavioral.state;

public class TVRemoteBasic {

    private String state;

    public TVRemoteBasic() {
        this.state = "";
    }

    public void doAction(){
        if ("ON".equalsIgnoreCase(state)) {
            IO.println("TV is turned ON");
        } else if("OFF".equalsIgnoreCase(state)) {
            IO.println("TV is turned OFF");
        }
    }

    public void setState(String state) {
        this.state = state;
    }

    static void main() {
        TVRemoteBasic tvRemoteBasic = new TVRemoteBasic();

        tvRemoteBasic.setState("ON");
        tvRemoteBasic.doAction();

        tvRemoteBasic.setState("OFF");
        tvRemoteBasic.doAction();
    }
}
