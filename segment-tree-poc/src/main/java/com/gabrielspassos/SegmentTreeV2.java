package com.gabrielspassos;

public class SegmentTreeV2 {

    private int n;
    private int A[];
    private int ST[];

    public SegmentTreeV2(int[] input) {
        this.n = input.length;
        this.A = input;
        this.ST = new int[4 * n];
    }

    public void build(int node, int L, int R) {
        if (L == R) {
            ST[node] = A[L];
        } else {
            int mid = (L + R) / 2;

            build(2 * node, L, mid);

            build(2 * node + 1, mid + 1, R);

            ST[node] = ST[2 * node] + ST[2 * node + 1];
        }
    }

    public int[] getTree() {
        return this.ST;
    }

    public void update(int node, int L, int R, int idx, int val) {
        if (L == R) {
            A[idx] += val;
            ST[node] += val;
        } else {
            int mid = (L + R) / 2;

            if (L <= idx && idx <= mid) {
                update(2 * node, L, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, R, idx, val);
            }

            ST[node] = ST[2 * node] + ST[2 * node + 1];
        }
    }

    public int query(int node, int tl, int tr, int l, int r) {
        if (r < tl || tr < l) {
            return 0;
        }

        if (l <= tl && tr <= r) {
            return ST[node];
        }

        int tm = (tl + tr) / 2;

        return query(2 * node, tl, tm, l, r)
                + query(2 * node + 1, tm + 1, tr, l, r);
    }
}
