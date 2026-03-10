package com.gabrielspassos.patterns.behavioral.iterator;

public class Channel {

    private Double frequency;
    private ChannelTypeEnum type;

    public Channel(Double frequency, ChannelTypeEnum type) {
        this.frequency = frequency;
        this.type = type;
    }

    public Double getFrequency() {
        return frequency;
    }

    public ChannelTypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "frequency=" + frequency +
                ", type=" + type +
                '}';
    }
}
