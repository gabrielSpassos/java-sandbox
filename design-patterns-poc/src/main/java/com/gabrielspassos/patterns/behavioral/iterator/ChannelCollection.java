package com.gabrielspassos.patterns.behavioral.iterator;

public interface ChannelCollection {

    boolean addChannel(Channel channel);

    boolean removeChannel(Channel channel);

    ChannelIterator iterator(ChannelTypeEnum type);
}
