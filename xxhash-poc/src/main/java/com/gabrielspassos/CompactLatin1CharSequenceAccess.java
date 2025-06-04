package com.gabrielspassos;

import java.nio.ByteOrder;

import static com.gabrielspassos.UnsafeAccess.BYTE_BASE;
import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class CompactLatin1CharSequenceAccess extends Access<byte[]> {

    static final Access<byte[]> INSTANCE = new CompactLatin1CharSequenceAccess();

    private static final Access<byte[]> INSTANCE_NON_NATIVE = Access.newDefaultReverseAccess(INSTANCE);

    private static final UnsafeAccess UNSAFE = UnsafeAccess.INSTANCE;

    private static final long UNSAFE_IDX_ADJUST
            = BYTE_BASE * 2L + (ByteOrder.nativeOrder() == LITTLE_ENDIAN ? 1 : 0);
    private static final long ARRAY_IDX_ADJUST
            = ByteOrder.nativeOrder() == LITTLE_ENDIAN ? 1 : 0;

    private CompactLatin1CharSequenceAccess() {}

    @Override
    public long getLong(final byte[] input, final long offset) {
        final long byteIdx = (offset + UNSAFE_IDX_ADJUST) >> 1;
        final long compact = UNSAFE.getUnsignedInt(input, byteIdx);
        long expanded = ((compact << 16) | compact) & 0xFFFF0000FFFFL;
        expanded = ((expanded << 8) | expanded) & 0xFF00FF00FF00FFL;
        if (((int)offset & 1) == 1) {
            return expanded << 8;
        }
        return expanded;
    }

    @Override
    public int getInt(final byte[] input, final long offset) {
        final long byteIdx = (offset + UNSAFE_IDX_ADJUST) >> 1;
        final int compact = UNSAFE.getShort(input, byteIdx) & 0xFFFF;
        final int expanded = ((compact << 8) | compact) & 0xFF00FF;
        if (((int)offset & 1) == 1) {
            return expanded << 8;
        }
        return expanded;
    }

    @Override
    public long getUnsignedInt(final byte[] input, final long offset) {
        final long byteIdx = (offset + UNSAFE_IDX_ADJUST) >> 1;
        final int compact = UNSAFE.getShort(input, byteIdx) & 0xFFFF;
        final long expanded = (long)(((compact << 8) | compact) & 0xFF00FF);
        if (((int)offset & 1) == 1) {
            return expanded << 8;
        }
        return expanded;
    }

    @Override
    public int getShort(final byte[] input, final long offset) {
        if (((int)offset & 1) == 0) {
            final int byteIdx = (int)(offset >> 1);
            return (int)input[byteIdx] & 0xFF;
        } else {
            final int byteIdx = (int)((offset + ARRAY_IDX_ADJUST) >> 1);
            return (int)input[byteIdx] << 8;
        }
    }

    @Override
    public int getUnsignedShort(final byte[] input, final long offset) {
        if (((int)offset & 1) == 0) {
            final int byteIdx = (int)(offset >> 1);
            return (int)input[byteIdx] & 0xFF;
        } else {
            final int byteIdx = (int)((offset + ARRAY_IDX_ADJUST) >> 1);
            return ((int)input[byteIdx] & 0xFF) << 8;
        }
    }

    @Override
    public int getByte(final byte[] input, final long offset) {
        if (ARRAY_IDX_ADJUST == ((int)offset & 1)) {
            return 0;
        } else {
            return (int)input[(int)(offset >> 1)];
        }
    }

    @Override
    public int getUnsignedByte(final byte[] input, final long offset) {
        if (ARRAY_IDX_ADJUST == ((int)offset & 1)) {
            return 0;
        } else {
            return (int)input[(int)(offset >> 1)] & 0xFF;
        }
    }

    @Override
    public ByteOrder byteOrder(final byte[] input) {
        return UNSAFE.byteOrder(input);
    }

    @Override
    protected Access<byte[]> reverseAccess() {
        return INSTANCE_NON_NATIVE;
    }
}
