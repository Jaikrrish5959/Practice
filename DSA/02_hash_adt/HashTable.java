package DSA.hash_adt;

import java.util.*;

/**
 * Hash Table ADT (Using Chaining)
 */
public class HashTable<K, V> {

    private class HashNode {
        K key;
        V value;
        HashNode next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<HashNode> bucketArray;
    private int numBuckets;
    private int size;

    public HashTable(int numBuckets) {
        this.numBuckets = numBuckets;
        this.bucketArray = new ArrayList<>(numBuckets);
        this.size = 0;

        // Create empty chains
        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        // handle negative hash codes
        return index < 0 ? index * -1 : index;
    }

    // --- Core Operations ---

    public void insert(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = bucketArray.get(bucketIndex);

        // Check if key exists
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; // Update
                return;
            }
            head = head.next;
        }

        // Insert new node
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode newNode = new HashNode(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        // Load Factor check could be added here
        if ((1.0 * size) / numBuckets >= 0.7) {
            // rehash(); // Placeholder for rehash logic
        }
    }

    public V delete(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = bucketArray.get(bucketIndex);
        HashNode prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null)
            return null; // Not found

        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            bucketArray.set(bucketIndex, head.next);
        }

        return head.value;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < numBuckets; i++)
            bucketArray.set(i, null);
        size = 0;
    }

    // --- Iteration ---

    public List<K> getKeys() {
        List<K> keys = new ArrayList<>();
        for (HashNode head : bucketArray) {
            while (head != null) {
                keys.add(head.key);
                head = head.next;
            }
        }
        return keys;
    }
}
