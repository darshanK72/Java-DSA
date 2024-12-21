/**
 * Problem Statement:
 *
 *     You are tasked with implementing a custom `HashSet` class. The set must provide
 *     the following methods:
 *     - add: Inserts an element into the set. If the element already exists, it should not be added.
 *     - contains: Checks if the set contains a given element.
 *     - remove: Removes the given element from the set.
 *     - size: Returns the number of elements in the set.
 *     - resize: Resizes the underlying storage array when the load factor exceeds 0.75.
 *     - toString: Returns a string representation of the set.
 *
 * Input:
 *     - None. The operations are carried out through method calls in the `main` function.
 *
 * Output:
 *     - The results of method calls such as add, contains, remove, and toString are printed.
 *
 * Example:
 *     Input:
 *     - add(5), add(10), add(15), add(10)
 *     - contains(10), contains(20)
 *     - toString, remove(10), contains(10)
 *     
 *     Output:
 *     Set contains 10: true
 *     Set contains 20: false
 *     [5, 10, 15]
 *     After removing 10: false
 *
 * Explanation:
 *     The set stores 5, 10, and 15, and duplicates are not added. The element 10 is removed.
 */

import java.util.LinkedList;

public class j02HashSetDesign {

    public static void main(String args[]) {
        HashSet<Integer> mySet = new HashSet<>();
        mySet.add(5);
        mySet.add(10);
        mySet.add(15);
        mySet.add(10);

        System.out.println("Set contains 10: " + mySet.contains(10));
        System.out.println("Set contains 20: " + mySet.contains(20));

        System.out.println(mySet);

        mySet.remove(10);
        System.out.println("After removing 10: " + mySet.contains(10));
    }

    /**
     * The custom HashSet class implementation.
     *
     * Approach:
     * - We use an array of LinkedLists to store elements. Each element is hashed to a bucket index.
     * - If there are collisions, they are handled by separate chaining using a LinkedList.
     * - The set dynamically resizes when the load factor exceeds 0.75.
     */

    public static class HashSet<K> {

        private LinkedList<Node>[] buckets;
        private int size;
        private int capacity;

        private class Node {
            K key;

            Node(K key) {
                this.key = key;
            }
        }

        /**
         * Constructor: Initializes the set with a small capacity (4).
         *
         * Time Complexity: O(n), where n is the initial capacity of the buckets array (4).
         * Space Complexity: O(n), as the space for the buckets array is created.
         */
        @SuppressWarnings("unchecked")
        public HashSet() {
            this.capacity = 4;
            this.buckets = new LinkedList[capacity];
            for (int i = 0; i < this.capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        /**
         * Method: Returns the number of elements in the set.
         *
         * Time Complexity: O(1), as the size is maintained.
         * Space Complexity: O(1), only a single integer is returned.
         */
        public int size() {
            return this.size;
        }

        /**
         * Method: Calculates the hash code for the given key, determining the bucket index.
         *
         * Time Complexity: O(1), as `hashCode()` is computed in constant time.
         * Space Complexity: O(1), as only an integer is returned.
         */
        public int hashCode(K key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        /**
         * Method: Resizes the internal buckets array when the load factor exceeds 0.75.
         *         All elements are rehashed and redistributed in the new array.
         *
         * Time Complexity: O(n), where n is the number of elements in the set, as all elements need to be rehashed.
         * Space Complexity: O(n), as a new array of linked lists is created.
         */
        @SuppressWarnings("unchecked")
        public void resize() {
            LinkedList<Node>[] oldBuckets = buckets;
            this.capacity = this.capacity * 2;
            this.buckets = new LinkedList[capacity];
            for (int i = 0; i < this.capacity; i++) {
                buckets[i] = new LinkedList<>();
            }
            this.size = 0;
            for (int i = 0; i < oldBuckets.length; i++) {
                for (Node node : oldBuckets[i]) {
                    this.add(node.key);
                }
            }
        }

        /**
         * Method: Adds a key to the set.
         *         If the key already exists, it will not be added.
         *
         * Time Complexity: 
         *    - O(1) on average, as we only need to compute the hash and insert into the appropriate bucket.
         *    - O(n) in the worst case when resizing occurs (when the load factor exceeds 0.75).
         * Space Complexity: O(1) for the operation itself, O(n) for storing elements when resizing.
         */
        public void add(K key) {
            if ((1.0 * size) / capacity > 0.75) {
                resize();
            }
            int bucketIndex = hashCode(key);
            boolean found = false;
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                buckets[bucketIndex].addLast(new Node(key));
                this.size++;
            }
        }

        /**
         * Method: Checks if a key exists in the set.
         *
         * Time Complexity: O(1) on average, as we hash the key and search in the corresponding bucket.
         * Space Complexity: O(1), as we only return a boolean value.
         */
        public boolean contains(K key) {
            int bucketIndex = hashCode(key);
            for (Node node : buckets[bucketIndex]) {
                if (node.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Method: Removes a key from the set if it exists.
         *
         * Time Complexity: O(1) on average for finding and removing the element in the corresponding bucket.
         * Space Complexity: O(1), as no additional space is used for removal.
         */
        public void remove(K key) {
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
            }
        }

        /**
         * Method: Returns a string representation of the set.
         *
         * Time Complexity: O(n), where n is the number of elements in the set.
         * Space Complexity: O(n), as we need to build a string representation of all elements.
         */
        public String toString() {
            StringBuilder out = new StringBuilder();
            out.append("[");
            int count = 0;
            for (int i = 0; i < capacity; i++) {
                for (Node node : buckets[i]) {
                    if (count != 0) {
                        out.append(", ");
                    }
                    out.append(node.key);
                    count++;
                }
            }
            out.append("]");
            return out.toString();
        }
    }
}
