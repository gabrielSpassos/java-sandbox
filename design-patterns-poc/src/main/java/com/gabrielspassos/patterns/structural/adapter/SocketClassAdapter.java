package com.gabrielspassos.patterns.structural.adapter;

public class SocketClassAdapter extends Socket implements SocketAdapter {

    @Override
    public Volt get220Volt() {
        var volt = getVolt();
        var newVolt = volt.getVolts() + 100;
        return new Volt(newVolt);
    }

    @Override
    public Volt get120Volt() {
        return getVolt();
    }

    @Override
    public Volt get12Volt() {
        var volt = getVolt();
        var newVolt = volt.getVolts() / 10;
        return new Volt(newVolt);
    }
}
