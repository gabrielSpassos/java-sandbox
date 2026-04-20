package com.gabrielspassos;

public class SegmentTreeV2 {

    private final int n;
    private final int[] array;
    private final int[] tree;

    public SegmentTreeV2(int[] input) {
        this.n = input.length;
        this.array = input;
        this.tree = new int[2 * n];
        build(0, 0, n - 1);
    }

    public int[] getTree() {
        return this.tree;
    }

    public int[] getArray() {
        return array;
    }

    public void update(int idx, int newValue) {
        update(0, 0, n - 1, idx, newValue);
    }

    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = array[start];
        } else {
            int mid = (start + end) / 2;

            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    private void update(int node, int start, int end, int idx, int newValue) {
        if (start == end) {
            array[idx] = newValue;
            tree[node] = newValue;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid) {
                update(2 * node + 1, start, mid, idx, newValue);
            } else {
                update(2 * node + 2, mid + 1, end, idx, newValue);
            }

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    private int query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(2 * node + 1, start, mid, left, right)
                + query(2 * node + 2, mid + 1, end, left, right);
    }
}
