package com.gabrielspassos.hash;

import com.gabrielspassos.service.HashService;

import java.util.Objects;

public class CustomHashTable <K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private HashService hashService;
    private Entry<K, V>[] buckets;
    private int size;

    public CustomHashTable() {
        buckets = new Entry[DEFAULT_CAPACITY];
        hashService = new HashService();
    }

    public boolean put(K key, V value) {
        if ((float) size / buckets.length >= LOAD_FACTOR) {
            resize();
        }

        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = buckets[bucketIndex];

        if (null == current) {
            buckets[bucketIndex] = new Entry<>(key, value);
            size++;
            return true;
        }

        while (true) {
            if (Objects.equals(current.getKey(), key)) {
                current.setValue(value);
                return true;
            }

            if (null == current.getNext()) {
                current.setNext(new Entry<>(key, value));
                size++;
                return true;
            }

            current = current.getNext();
        }
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = buckets[bucketIndex];

        while (null != current) {
            if (Objects.equals(current.getKey(), key)) {
                return current.getValue();
            }

            current = current.getNext();
        }

        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = buckets[bucketIndex];
        Entry<K, V> previous = null;

        while (null != current) {
            if (Objects.equals(current.getKey(), key)) {
                if (null == previous) {
                    buckets[bucketIndex] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }

                size--;
                return current.getValue();
            }

            previous = current;
            current = current.getNext();
        }

        return null;
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(K key) {
        String keyAsString = null != key ? key.toString() : null;
        return hashService.hash(keyAsString) % buckets.length;
    }

    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;

        buckets = new Entry[oldBuckets.length * 2];
        size = 0;

        for (Entry<K, V> bucket : oldBuckets) {
            Entry<K, V> current = bucket;

            while (null != current) {
                put(current.getKey(), current.getValue());
                current = current.getNext();
            }
        }
    }
}
