/**
 * Problem Statement:
 *     Design a circular queue that supports enQueue, deQueue, Front, Rear, isEmpty and isFull operations.
 *     https://leetcode.com/problems/design-circular-queue/
 * 
 * Input Operations:
 *     - enQueue(value): Insert element into circular queue
 *     - deQueue(): Delete element from queue
 *     - Front(): Get front element
 *     - Rear(): Get last element
 *     - isEmpty(): Check whether queue is empty
 *     - isFull(): Check whether queue is full
 * 
 * Output:
 *     - For enQueue/deQueue: Return true if operation successful, false otherwise
 *     - For Front/Rear: Return element if exists, -1 if queue empty
 *     - For isEmpty/isFull: Return boolean status
 * 
 * Example:
 *     Input: 
 *     MyCircularQueue(5)
 *     enQueue(1), enQueue(2), enQueue(3), enQueue(4), enQueue(5)
 *     enQueue(6)
 *     Rear()
 *     isFull()
 *     deQueue()
 *     enQueue(6)
 *     Rear()
 *     
 *     Output:
 *     [true, true, true, true, true]
 *     false (queue full)
 *     5
 *     true
 *     true
 *     true
 *     6
 */

public class j08CircularQueueUsingLinkedList {

    public static void main(String args[]) {
        MyCircularQueue obj = new MyCircularQueue(5);
        System.out.println(obj.enQueue(1)); // true
        System.out.println(obj.enQueue(2)); // true
        System.out.println(obj.enQueue(3)); // true
        System.out.println(obj.enQueue(4)); // true
        System.out.println(obj.enQueue(5)); // true
        System.out.println(obj.enQueue(6)); // false, queue is full
        System.out.println(obj.Rear()); // 5
        System.out.println(obj.isFull()); // true
        System.out.println(obj.deQueue()); // true
        System.out.println(obj.enQueue(6)); // true
        System.out.println(obj.Rear()); // 6
        System.out.println(obj.isFull()); // true
    }

    /**
     * Approach: Fixed Size Linked List Implementation
     * 
     * Intuition:
     * - Use singly linked list with front and rear pointers
     * - Track size and capacity to maintain fixed size behavior
     * - No need for circular connection as we're using pointers
     * - Front for dequeue operations, rear for enqueue
     * - Size tracking helps in O(1) empty/full checks
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(k) where k is capacity of queue
     */
    public static class MyCircularQueue {
        /**
         * Node class for linked list implementation
         */
        static class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        Node front, rear;
        int size;
        int capacity;

        /**
         * Initialize circular queue with given capacity
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param k capacity of queue
         */
        public MyCircularQueue(int k) {
            this.capacity = k;
            this.size = 0;
            this.front = null;
            this.rear = null;
        }

        /**
         * Insert element into queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param value element to insert
         * @return true if successful, false if queue full
         */
        public boolean enQueue(int value) {
            if (this.isFull())
                return false;
            Node node = new Node(value);
            if (this.size == 0) {
                this.front = node;
                this.rear = node;
            } else {
                this.rear.next = node;
                this.rear = node;
            }
            this.size++;
            return true;
        }

        /**
         * Delete element from queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if successful, false if queue empty
         */
        public boolean deQueue() {
            if (this.isEmpty())
                return false;
            this.front = front.next;
            this.size--;
            return true;
        }

        /**
         * Get front element of queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if empty
         */
        public int Front() {
            if (this.isEmpty())
                return -1;
            return this.front.data;
        }

        /**
         * Get rear element of queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return rear element if exists, -1 if empty
         */
        public int Rear() {
            if (this.isEmpty())
                return -1;
            return this.rear.data;
        }

        /**
         * Check if queue is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return this.size == 0;
        }

        /**
         * Check if queue is full
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if full, false otherwise
         */
        public boolean isFull() {
            return this.size == this.capacity;
        }
    }
}
