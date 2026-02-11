package com.gabrielspassos;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class BitMap {

    private final long[] words;
    private final int size;

    private static final int ADDRESS_BITS = 6;
    private static final int WORD_SIZE = 1 << ADDRESS_BITS;

    public BitMap(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        this.size = size;
        this.words = new long[(size + WORD_SIZE - 1) >>> ADDRESS_BITS];
    }

    private static int wordIndex(int bitIndex) {
        return bitIndex >>> ADDRESS_BITS;
    }

    private static long mask(int bitIndex) {
        return 1L << (bitIndex & (WORD_SIZE - 1));
    }

    private void check(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
    }

    public void set(int i) {
        check(i);
        words[wordIndex(i)] |= mask(i);
    }

    public void clear(int i) {
        check(i);
        words[wordIndex(i)] &= ~mask(i);
    }

    public void toggle(int i) {
        check(i);
        words[wordIndex(i)] ^= mask(i);
    }

    public boolean get(int i) {
        check(i);
        return (words[wordIndex(i)] & mask(i)) != 0;
    }

    public void clearAll() {
        Arrays.fill(words, 0);
    }

    public int cardinality() {
        int c = 0;
        for (long w : words)
            c += Long.bitCount(w);
        return c;
    }

    public void forEachSetBit(IntConsumer action) {
        for (int wi = 0; wi < words.length; wi++) {
            long w = words[wi];
            while (w != 0) {
                int bit = Long.numberOfTrailingZeros(w);
                action.accept((wi << ADDRESS_BITS) + bit);
                w &= (w - 1); // remove lowest set bit
            }
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(size);
        for (int i = 0; i < size; i++)
            sb.append(get(i) ? '1' : '0');
        return sb.toString();
    }
}
