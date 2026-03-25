package com.gabrielspassos;

import java.util.Arrays;

public class SegmentTree {

    private final int size;
    private final long[] tree;

    public SegmentTree(long[] input) {
        this.size = input.length;
        this.tree = new long[2 * size];
        buildTree(input);
    }

    private void buildTree(long[] input) {
        System.arraycopy(input, 0, tree, size, size);

        for (int i = size - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public void update(int index, long value) {
        int pos = index + size;
        tree[pos] = value;

        while (pos > 1) {
            pos >>= 1;
            tree[pos] = tree[pos << 1] + tree[pos << 1 | 1];
        }
    }

    public long query(int left, int right) {
        long result = 0;
        left += size;
        right += size;

        while (left < right) {
            if ((left & 1) == 1) result += tree[left++];
            if ((right & 1) == 1) result += tree[--right];

            left >>= 1;
            right >>= 1;
        }

        return result;
    }

    @Override
    public String toString() {
        return "SegmentTree{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }
}
