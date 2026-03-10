package com.gabrielspassos.patterns.behavioral.iterator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IteratorTest {

    @Test
    void shouldAddChannel() {
        ChannelCollection channels = new ChannelCollectionImpl();
        Channel channel = new Channel(98.5, ChannelTypeEnum.ENGLISH);

        assertTrue(channels.addChannel(channel));
    }

    @Test
    void shouldRemoveChannel() {
        ChannelCollection channels = new ChannelCollectionImpl();
        Channel channel = new Channel(98.5, ChannelTypeEnum.ENGLISH);

        assertTrue(channels.addChannel(channel));
        assertTrue(channels.removeChannel(channel));
    }

    @Test
    void shouldIterateAllChannels() {
        ChannelCollection channels = populateChannels();

        ChannelIterator iterator = channels.iterator(ChannelTypeEnum.ALL);
        assertNotNull(iterator);

        boolean hasNext = iterator.hasNext();
        assertTrue(hasNext);

        Channel channel = iterator.next();
        assertNotNull(channel);
        assertNotNull(channel.getFrequency());
        assertNotNull(channel.getType());
        IO.println(channel);

        var count = 0;
        while (iterator.hasNext()) {
            Channel next = iterator.next();
            assertNotNull(next);
            assertNotNull(next.getFrequency());
            assertNotNull(next.getType());
            IO.println(next);
            count++;
        }

        assertEquals(8, count);
    }

    @Test
    void shouldIteratePortugueseChannels() {
        ChannelCollection channels = populateChannels();

        ChannelIterator iterator = channels.iterator(ChannelTypeEnum.PORTUGUESE);
        assertNotNull(iterator);

        boolean hasNext = iterator.hasNext();
        assertTrue(hasNext);

        Channel channel = iterator.next();
        assertNotNull(channel);
        assertNotNull(channel.getFrequency());
        assertNotNull(channel.getType());
        IO.println(channel);

        var count = 0;
        while (iterator.hasNext()) {
            Channel next = iterator.next();
            assertNotNull(next);
            assertNotNull(next.getFrequency());
            assertNotNull(next.getType());
            IO.println(next);
            count++;
        }

        assertEquals(2, count);
    }

    private ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelTypeEnum.PORTUGUESE));
        channels.addChannel(new Channel(100.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(101.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelTypeEnum.PORTUGUESE));
        channels.addChannel(new Channel(103.5, ChannelTypeEnum.FRENCH));
        channels.addChannel(new Channel(104.5, ChannelTypeEnum.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelTypeEnum.PORTUGUESE));
        channels.addChannel(new Channel(106.5, ChannelTypeEnum.FRENCH));
        return channels;
    }

}