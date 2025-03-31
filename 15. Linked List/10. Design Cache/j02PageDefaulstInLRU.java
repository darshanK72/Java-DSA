/**
 * Problem Statement:
 * 
 *     Given a sequence of page requests and a cache of fixed capacity, calculate the number of page faults
 *     using the Least Recently Used (LRU) cache replacement policy.
 * 
 * Input:
 *     - `N`: The number of page requests.
 *     - `C`: The capacity of the cache.
 *     - `pages[]`: An array representing the sequence of page requests.
 * 
 * Output:
 *     - The number of page faults that occur while processing the page requests.
 * 
 * Example:
 *     Input:
 *         N = 5, C = 3, pages = [1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5]
 *     Output:
 *         8
 * 
 *     Explanation:
 *         The cache starts empty. As pages are requested, page faults occur when a page is not in the cache.
 *         The LRU policy is used to evict the least recently used page when the cache is full.
 */

import java.util.HashMap;

public class j02PageDefaulstInLRU {

    /**
     * Method: pageFaults
     * 
     * Intuition:
     * - Use an LRU Cache to simulate the page replacement process.
     * - For each page request, check if the page is in the cache:
     *     - If the page is not in the cache, it is a page fault.
     *     - Add the page to the cache using the `put` method.
     * 
     * Explanation:
     * 1. Initialize an LRU Cache with the given capacity.
     * 2. Traverse the array of page requests:
     *     - If the page is not in the cache (`get` returns -1), increment the page fault counter.
     *     - Add the page to the cache using the `put` method.
     * 3. Return the total number of page faults.
     * 
     * Time Complexity:
     * - O(N), where `N` is the number of page requests. Each `get` and `put` operation is O(1).
     * 
     * Space Complexity:
     * - O(C), where `C` is the capacity of the cache.
     * 
     * @param N The number of page requests.
     * @param C The capacity of the cache.
     * @param pages The array of page requests.
     * @return The number of page faults.
     */
    static int pageFaults(int N, int C, int pages[]) {
        LRUCache cache = new LRUCache(C); // Initialize the LRU Cache
        int faults = 0; // Counter for page faults

        for (int i = 0; i < N; i++) {
            if (cache.get(pages[i]) == -1) { // If the page is not in the cache
                faults++; // Increment the page fault counter
            }
            cache.put(pages[i], pages[i]); // Add the page to the cache
        }

        return faults; // Return the total number of page faults
    }

    /**
     * LRUCache Class:
     * 
     * Implements the LRU Cache using a combination of a HashMap and a Doubly Linked List.
     * - The HashMap provides O(1) access to cache items.
     * - The Doubly Linked List maintains the order of usage, with the most recently used item at the head
     *   and the least recently used item at the tail.
     */
    static class LRUCache {

        /**
         * Node Class:
         * 
         * Represents a node in the doubly linked list. Each node contains:
         * - `key`: The key of the cache item.
         * - `value`: The value of the cache item.
         * - `next`: A reference to the next node in the list.
         * - `prev`: A reference to the previous node in the list.
         */
        static class Node {
            public int key;
            public int value;
            public Node next;
            public Node prev;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.next = null;
                this.prev = null;
            }
        }

        private int capacity; // Maximum capacity of the cache
        private int size; // Current size of the cache
        private Node head; // Dummy head of the doubly linked list
        private Node tail; // Dummy tail of the doubly linked list
        private HashMap<Integer, Node> map; // HashMap to store key-node mappings

        /**
         * Constructor:
         * Initializes the LRU Cache with the given capacity.
         * 
         * @param capacity The maximum number of items the cache can hold.
         */
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.head = new Node(-1, -1); // Dummy head node
            this.tail = new Node(-1, -1); // Dummy tail node
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.map = new HashMap<>();
        }

        /**
         * Get Operation:
         * Retrieves the value of the key if it exists in the cache, otherwise returns -1.
         * Moves the accessed node to the head of the list to mark it as recently used.
         * 
         * @param key The key to retrieve.
         * @return The value of the key, or -1 if the key is not found.
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1; // Key not found
            }
            Node node = map.get(key);
            delete(node); // Remove the node from its current position
            insert(node); // Insert the node at the head of the list
            return node.value;
        }

        /**
         * Put Operation:
         * Inserts or updates the value of the key in the cache.
         * If the cache reaches its capacity, evicts the least recently used item.
         * 
         * @param key The key to insert or update.
         * @param value The value to associate with the key.
         */
        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                delete(node); // Remove the existing node
                node.value = value; // Update the value
                insert(node); // Reinsert the node at the head
            } else {
                Node node = new Node(key, value);
                if (this.size == this.capacity) {
                    Node evictNode = tail.prev; // Least recently used node
                    delete(evictNode); // Remove it from the list
                    map.remove(evictNode.key); // Remove it from the map
                    this.size--;
                }
                map.put(key, node); // Add the new node to the map
                insert(node); // Insert the new node at the head
                this.size++;
            }
        }

        /**
         * Insert Operation:
         * Inserts a node at the head of the doubly linked list.
         * 
         * @param node The node to insert.
         */
        private void insert(Node node) {
            node.next = this.head.next;
            node.prev = this.head;
            this.head.next.prev = node;
            this.head.next = node;
        }

        /**
         * Delete Operation:
         * Removes a node from the doubly linked list.
         * 
         * @param node The node to remove.
         */
        private void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}