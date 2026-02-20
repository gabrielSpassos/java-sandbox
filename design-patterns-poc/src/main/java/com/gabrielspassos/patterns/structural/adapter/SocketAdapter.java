package com.gabrielspassos.patterns.structural.adapter;

public interface SocketAdapter {

    Volt get220Volt();

    Volt get120Volt();

    Volt get12Volt();
}
