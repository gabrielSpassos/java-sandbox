package com.gabrielspassos;

import java.nio.ByteBuffer;

import static com.gabrielspassos.SafeUtils.checkRange;

public class XXHash32Impl extends XXHash32 {

    public static final XXHash32 INSTANCE = new XXHash32Impl();
    private static XXHash32 SAFE_INSTANCE;

    @Override
    public int hash(byte[] buf, int off, int len, int seed) {
        checkRange(buf, off, len);
        return XXHashJNI.XXH32(buf, off, len, seed);
    }

    @Override
    public int hash(ByteBuffer buf, int off, int len, int seed) {
        if (buf.isDirect()) {
            checkRange(buf, off, len);
            return XXHashJNI.XXH32BB(buf, off, len, seed);
        } else if (buf.hasArray()) {
            return hash(buf.array(), off + buf.arrayOffset(), len, seed);
        } else {
            XXHash32 safeInstance = SAFE_INSTANCE;
            if (safeInstance == null) {
                safeInstance = SAFE_INSTANCE = XXHashFactory.safeInstance().hash32();
            }
            return safeInstance.hash(buf, off, len, seed);
        }
    }
}
