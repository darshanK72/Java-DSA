/**
 * Problem Statement:
 * 
 *     Design a custom implementation of a HashMap in Java. The HashMap should support the following operations:
 *     - `put(K key, V value)`: Inserts a key-value pair into the map.
 *     - `get(K key)`: Retrieves the value associated with the given key.
 *     - `getOrDefault(K key, V value)`: Retrieves the value for the given key, or returns a default value if the key is not found.
 *     - `containsKey(K key)`: Checks if a given key is present in the map.
 *     - `remove(K key)`: Removes the key-value pair from the map.
 *     - `resize()`: Resizes the internal array to handle a growing number of elements.
 * 
 *     You are also required to handle hash collisions by using separate chaining, i.e., using linked lists for each bucket.
 * 
 * Input:
 *     - An integer `n` representing the number of elements in the map.
 *     - An array `arr[]` of integers representing the keys. We will perform `put` operation using these keys.
 * 
 * Output:
 *     - A string representation of the hashmap after processing all the `put` operations.
 * 
 * Example:
 * 
 * Input:
 * 5
 * 1 2 3 1 2
 * 
 * Output:
 * {1 = 2, 2 = 2, 3 = 1}
 * 
 * Explanation:
 * The `put` operation inserts the key-value pairs, and duplicate keys (1 and 2) update their values.
 * 
 */

import java.util.LinkedList;
import java.util.Scanner;

public class j01HashMapDesign {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Create HashMap object
        HashMap<Integer, Integer> myMap = new HashMap<>();

        // Perform 'put' operation for each element
        for (int i = 0; i < n; i++) {
            myMap.put(arr[i], myMap.getOrDefault(arr[i], 0) + 1);
        }

        // Output the HashMap's string representation
        System.err.println(myMap.toString());
        in.close();
    }

    /**
     * Approach 1: HashMap Implementation using Separate Chaining
     * 
     * Intuition:
     * - The HashMap uses an array of LinkedLists (buckets) to handle collisions via separate chaining.
     * - Each bucket contains a list of nodes, and each node stores a key-value pair.
     * - The capacity of the array is resized when the load factor exceeds 0.75 to maintain optimal performance.
     * 
     * Time Complexity:
     * - O(1) for `put()`, `getOrDefault()`, `containsKey()`, and `remove()` on average.
     * - O(n) in the worst case when all keys hash to the same bucket (unlikely with a good hash function).
     * - O(n) for `resize()` operation when resizing occurs, but it happens less frequently as the map grows.
     * 
     * Space Complexity:
     * - O(n), where `n` is the number of elements in the map, because we store each key-value pair in a linked list.
     * 
     * @param nums The input array of numbers.
     * @return The result based on this approach.
     */
    public static class HashMap<K, V> {

        private LinkedList<Node>[] buckets;
        private int size;
        private int capacity;

        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.capacity = 4; // Initial capacity
            this.buckets = new LinkedList[capacity];
            for (int i = 0; i < this.capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        /**
         * Method: size()
         * 
         * Returns the number of key-value pairs in the hashmap.
         * 
         * Time Complexity:
         * - O(1), constant time to retrieve the size.
         * 
         * Space Complexity:
         * - O(1), as it only returns an integer value.
         * 
         * @return The size of the hashmap.
         */
        public int size() {
            return this.size;
        }

        /**
         * Method: hashCode(K key)
         * 
         * Calculates the hash value for a given key to determine which bucket the key should be placed in.
         * The hash code is computed using the built-in Java `hashCode()` method and is adjusted to fit the
         * current bucket array size using the modulus operator.
         * 
         * Time Complexity:
         * - O(1), constant time to calculate the hash code.
         * 
         * Space Complexity:
         * - O(1), constant space required to store the hash code.
         * 
         * @param key The key for which we want to calculate the hash code.
         * @return The hash code of the key.
         */
        public int hashCode(K key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        /**
         * Method: resize()
         * 
         * Doubles the size of the internal bucket array when the load factor exceeds 0.75.
         * Rehashes all the keys to the new buckets.
         * 
         * Time Complexity:
         * - O(n), where `n` is the number of elements in the hashmap, because all elements need to be rehashed.
         * 
         * Space Complexity:
         * - O(n), where `n` is the number of elements, because we are allocating space for a new bucket array.
         */
        @SuppressWarnings("unchecked")
        public void resize() {
            LinkedList<Node>[] oldBuckets = buckets;
            this.capacity = this.capacity * 2; // Double the capacity
            this.buckets = new LinkedList[capacity];
            for (int i = 0; i < this.capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
            this.size = 0;
            // Rehash all the old keys to new buckets
            for (int i = 0; i < oldBuckets.length; i++) {
                for (Node node : oldBuckets[i]) {
                    this.put(node.key, node.value);
                }
            }
        }

        /**
         * Method: toString()
         * 
         * Converts the hashmap to a string format where each key-value pair is displayed as `key = value`.
         * This helps in printing the current state of the hashmap.
         * 
         * Time Complexity:
         * - O(n), where `n` is the number of elements in the hashmap, as we traverse each bucket and node.
         * 
         * Space Complexity:
         * - O(n), for storing the string representation of the hashmap.
         * 
         * @return The string representation of the hashmap.
         */
        public String toString() {
            StringBuilder out = new StringBuilder();
            out.append("{");
            int count = 0;
            for (int i = 0; i < capacity; i++) {
                for (Node node : buckets[i]) {
                    if (count != 0)
                        out.append(",");
                    out.append(node.key + " = " + node.value);
                    count++;
                }
            }
            out.append("}");
            return out.toString();
        }

        /**
         * Method: containsKey(K key)
         * 
         * Checks whether the hashmap contains the given key. This is done by hashing the key to the corresponding
         * bucket and searching through the linked list in that bucket.
         * 
         * Time Complexity:
         * - O(1) on average, but can degrade to O(n) in the worst case (if all keys hash to the same bucket).
         * 
         * Space Complexity:
         * - O(1), as we only need a small amount of space to check for the key.
         * 
         * @param key The key to check in the hashmap.
         * @return True if the key exists, false otherwise.
         */
        public boolean containsKey(K key) {
            int bucketIndex = hashCode(key);
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Method: put(K key, V value)
         * 
         * Inserts a new key-value pair into the hashmap. If the key already exists, its value is updated.
         * If the load factor exceeds 0.75, the hashmap will resize.
         * 
         * Time Complexity:
         * - O(1) on average for the `put` operation.
         * - O(n) when resizing, as all keys must be rehashed to the new bucket array.
         * 
         * Space Complexity:
         * - O(n), where `n` is the number of elements in the hashmap, because we store each key-value pair in a linked list.
         */
        public void put(K key, V value) {
            // Resize if the load factor exceeds 0.75
            if ((1.0 * size) / capacity > 0.75) {
                resize();
            }
            Node n = new Node(key, value);
            boolean found = false;
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    node.value = value; // Update the value if the key is already present
                    found = true;
                    break;
                }
            }
            if (!found) {
                buckets[hashCode(key)].addLast(n);
                this.size++;
            }
        }

        /**
         * Method: get(K key)
         * 
         * Retrieves the value associated with the given key. If the key does not exist, an exception is thrown.
         * 
         * Time Complexity:
         * - O(1) on average, but can degrade to O(n) in the worst case (if all keys hash to the same bucket).
         * 
         * Space Complexity:
         * - O(1), as we only need a small amount of space to store the result.
         * 
         * @param key The key to retrieve the value for.
         * @return The value associated with the key.
         * @throws Exception If the key is not found in the hashmap.
         */
        public V get(K key) throws Exception {
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
            throw new Exception("No element found with key");
        }

        /**
         * Method: getOrDefault(K key, V value)
         * 
         * Retrieves the value associated with the given key. If the key is not found, returns the default value.
         * 
         * Time Complexity:
         * - O(1) on average for the `getOrDefault()` operation.
         * - O(n) in the worst case if all keys hash to the same bucket.
         * 
         * Space Complexity:
         * - O(1), as we only need a small amount of space to store the result.
         * 
         * @param key The key to retrieve the value for.
         * @param value The default value to return if the key is not found.
         * @return The value associated with the key or the default value.
         */
        public V getOrDefault(K key, V value) {
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
            return value;
        }

        /**
         * Method: remove(K key)
         * 
         * Removes the key-value pair associated with the given key from the hashmap.
         * 
         * Time Complexity:
         * - O(1) on average for the `remove()` operation.
         * - O(n) in the worst case if all keys hash to the same bucket.
         * 
         * Space Complexity:
         * - O(1), as we only need space to remove the key-value pair.
         * 
         * @param key The key to remove from the hashmap.
         * @return The value associated with the removed key, or null if the key was not found.
         */
        public V remove(K key) {
            int bucketIndex = hashCode(key);
            Node toRemove = null;
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    toRemove = node;
                    break;
                }
            }

            if (toRemove != null) {
                buckets[bucketIndex].remove(toRemove);
                this.size--;
                return toRemove.value;
            } else {
                return null; // Key not found
            }
        }
    }
}
