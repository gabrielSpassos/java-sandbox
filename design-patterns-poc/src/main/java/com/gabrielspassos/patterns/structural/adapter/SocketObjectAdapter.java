package com.gabrielspassos.patterns.structural.adapter;

public class SocketObjectAdapter implements SocketAdapter {

    private final Socket socket;

    public SocketObjectAdapter() {
        this.socket = new Socket();
    }

    @Override
    public Volt get220Volt() {
        var volt = socket.getVolt();
        var newVolt = volt.getVolts() + 100;
        return new Volt(newVolt);
    }

    @Override
    public Volt get120Volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt() {
        var volt = socket.getVolt();
        var newVolt = volt.getVolts() / 10;
        return new Volt(newVolt);
    }
}
