package com.gabrielspassos;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static java.nio.ByteOrder.BIG_ENDIAN;
import static java.nio.ByteOrder.LITTLE_ENDIAN;

public abstract class Access<T> {

    public static <T> Access<T> unsafe() {
        return (Access<T>) UnsafeAccess.INSTANCE;
    }

    public static Access<ByteBuffer> toByteBuffer() {
        return ByteBufferAccess.INSTANCE;
    }

    public static <T extends CharSequence> Access<T> toNativeCharSequence() {
        return (Access<T>) CharSequenceAccess.nativeCharSequenceAccess();
    }

    public static <T extends CharSequence> Access<T> toCharSequence(ByteOrder backingOrder) {
        return (Access<T>) CharSequenceAccess.charSequenceAccess(backingOrder);
    }

    protected Access() {}

    public long getLong(T input, long offset) {
        if (byteOrder(input) == LITTLE_ENDIAN) {
            return getUnsignedInt(input, offset) | (getUnsignedInt(input, offset + 4L) << 32);
        } else {
            return getUnsignedInt(input, offset + 4L) | (getUnsignedInt(input, offset) << 32);
        }
    }

    public long getUnsignedInt(T input, long offset) {
        return ((long) getInt(input, offset)) & 0xFFFFFFFFL;
    }

    public int getInt(T input, long offset) {
        if (byteOrder(input) == LITTLE_ENDIAN) {
            return getUnsignedShort(input, offset) | (getUnsignedShort(input, offset + 2L) << 16);
        } else {
            return getUnsignedShort(input, offset + 2L) | (getUnsignedShort(input, offset) << 16);
        }
    }

    public int getUnsignedShort(T input, long offset) {
        if (byteOrder(input) == LITTLE_ENDIAN) {
            return getUnsignedByte(input, offset) | (getUnsignedByte(input, offset + 1L) << 8);
        } else {
            return getUnsignedByte(input, offset + 1L) | (getUnsignedByte(input, offset) << 8);
        }
    }

    public int getShort(T input, long offset) {
        return (int) (short) getUnsignedShort(input, offset);
    }

    public int getUnsignedByte(T input, long offset) {
        return getByte(input, offset) & 0xFF;
    }

    public abstract int getByte(T input, long offset);

    // short names
    public long i64(final T input, final long offset) { return getLong(input, offset); }
    public long u32(final T input, final long offset) { return getUnsignedInt(input, offset); }
    public  int i32(final T input, final long offset) { return getInt(input, offset); }
    public  int u16(final T input, final long offset) { return getUnsignedShort(input, offset); }
    public  int i16(final T input, final long offset) { return getShort(input, offset); }
    public  int  u8(final T input, final long offset) { return getUnsignedByte(input, offset); }
    public  int  i8(final T input, final long offset) { return getByte(input, offset); }

    public abstract ByteOrder byteOrder(T input);

    public Access<T> byteOrder(final T input, final ByteOrder byteOrder) {
        return byteOrder(input) == byteOrder ? this : reverseAccess();
    }

    protected abstract Access<T> reverseAccess();

    static <T> Access<T> newDefaultReverseAccess(final Access<T> access) {
        return access instanceof ReverseAccess
                ? access.reverseAccess()
                : new ReverseAccess<T>(access);
    }

    private static class ReverseAccess<T> extends Access<T> {
        final Access<T> access;
        private ReverseAccess(final Access<T> access) {
            this.access = access;
        }
        @Override
        public long getLong(final T input, final long offset) {
            return Long.reverseBytes(access.getLong(input, offset));
        }
        @Override
        public long getUnsignedInt(final T input, final long offset) {
            return Long.reverseBytes(access.getUnsignedInt(input, offset)) >>> 32;
        }
        @Override
        public int getInt(final T input, final long offset) {
            return Integer.reverseBytes(access.getInt(input, offset));
        }
        @Override
        public int getUnsignedShort(final T input, final long offset) {
            return Integer.reverseBytes(access.getUnsignedShort(input, offset)) >>> 16;
        }
        @Override
        public int getShort(final T input, final long offset) {
            return Integer.reverseBytes(access.getShort(input, offset)) >> 16;
        }
        @Override
        public int getUnsignedByte(final T input, final long offset) {
            return access.getUnsignedByte(input, offset);
        }
        @Override
        public int getByte(final T input, final long offset) {
            return access.getByte(input, offset);
        }
        @Override
        public ByteOrder byteOrder(final T input) {
            return LITTLE_ENDIAN == access.byteOrder(input) ? BIG_ENDIAN : LITTLE_ENDIAN;
        }
        @Override
        protected Access<T> reverseAccess() {
            return access;
        }
    }
}




