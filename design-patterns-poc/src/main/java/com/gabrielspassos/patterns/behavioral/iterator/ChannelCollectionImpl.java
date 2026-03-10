package com.gabrielspassos.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class ChannelCollectionImpl implements ChannelCollection {

    private final List<Channel> channels;

    public ChannelCollectionImpl() {
        this.channels = new ArrayList<>();
    }

    @Override
    public boolean addChannel(Channel channel) {
        return channels.add(channel);
    }

    @Override
    public boolean removeChannel(Channel channel) {
        return channels.remove(channel);
    }

    @Override
    public ChannelIterator iterator(ChannelTypeEnum type) {
        return new ChannelIteratorImpl(type, this.channels);
    }

    private class ChannelIteratorImpl implements ChannelIterator {

        private ChannelTypeEnum channelType;
        private List<Channel> channels;
        private int position;

        public ChannelIteratorImpl(ChannelTypeEnum channelType, List<Channel> channels) {
            this.channelType = channelType;
            this.channels = channels;
        }

        @Override
        public boolean hasNext() {
            while(position < channels.size()) {
                Channel channel = channels.get(position);
                if (ChannelTypeEnum.ALL.equals(channelType) || channelType.equals(channel.getType())) {
                    return true;
                } else {
                    position++;
                }
            }

            return false;
        }

        @Override
        public Channel next() {
            Channel channel = channels.get(position);
            position++;
            return channel;
        }
    }
}
