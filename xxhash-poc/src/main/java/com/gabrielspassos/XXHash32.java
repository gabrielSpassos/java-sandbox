package com.gabrielspassos;

import java.nio.ByteBuffer;

public abstract class XXHash32 {

    public abstract int hash(byte[] buf, int off, int len, int seed);
    public abstract int hash(ByteBuffer buf, int off, int len, int seed);

    public final int hash(ByteBuffer buf, int seed) {
        final int hash = hash(buf, buf.position(), buf.remaining(), seed);
        buf.position(buf.limit());
        return hash;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
