/*-
 * Problem Statement:
 *     Design a circular double-ended queue (deque) that supports various operations using a doubly linked list.
 *     https://leetcode.com/problems/design-circular-deque/
 * 
 * Input Operations:
 *     - insertFront(value): Add element at front of deque
 *     - insertLast(value): Add element at end of deque
 *     - deleteFront(): Delete element from front of deque
 *     - deleteLast(): Delete element from end of deque
 *     - getFront(): Get front element
 *     - getRear(): Get last element
 *     - isEmpty(): Check if deque is empty
 *     - isFull(): Check if deque is full
 * 
 * Output:
 *     - For insert/delete operations: Return true if successful, false otherwise
 *     - For get operations: Return element if exists, -1 if empty
 *     - For isEmpty/isFull: Return boolean status
 * 
 * Example:
 *     Input: 
 *     MyCircularDeque(5)
 *     insertFront(1), insertLast(2), insertFront(3), insertLast(4)
 *     insertFront(5)
 *     getRear()
 *     isFull()
 *     deleteLast()
 *     insertFront(5)
 *     getFront()
 *     
 *     Output:
 *     [true, true, true, true]
 *     false (deque full)
 *     4
 *     true
 *     true
 *     true
 *     5
 */

public class j10CircularDequeUsingLinkedList {

    public static void main(String args[]) {
        MyCircularDeque obj = new MyCircularDeque(5);
        System.out.println(obj.insertFront(1)); // true
        System.out.println(obj.insertLast(2)); // true
        System.out.println(obj.insertFront(3)); // true
        System.out.println(obj.insertLast(4)); // true
        System.out.println(obj.insertFront(5)); // false, deque is full
        System.out.println(obj.getRear()); // 4
        System.out.println(obj.isFull()); // true
        System.out.println(obj.deleteLast()); // true
        System.out.println(obj.insertFront(5)); // true
        System.out.println(obj.getFront()); // 5
        System.out.println(obj.isEmpty()); // false
    }

    /*-
     * Approach: Doubly Linked List Implementation
     * 
     * Intuition:
     * - Use doubly linked list with front and rear pointers
     * - Each node has next and prev pointers for bidirectional traversal
     * - Track size and capacity for full/empty state checks
     * - Front operations modify front pointer
     * - Rear operations modify rear pointer
     * - Maintain connections properly during insertions and deletions
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(k) where k is capacity of deque
     */
    public static class MyCircularDeque {

        /**
         * Node class for doubly linked list implementation
         */
        static class Node {
            int data;
            Node next;
            Node prev;

            public Node(int data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }

        Node front, rear;
        int size;
        int capacity;

        /**
         * Initialize empty deque with given capacity
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param k capacity of deque
         */
        public MyCircularDeque(int k) {
            this.capacity = k;
            this.front = null;
            this.rear = null;
            this.size = 0;
        }

        /**
         * Insert element at front of deque
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param value element to insert
         * @return true if successful, false if deque full
         */
        public boolean insertFront(int value) {
            if (this.isFull())
                return false;
            Node node = new Node(value);
            if (this.size == 0) {
                this.front = node;
                this.rear = node;
            } else {
                node.next = this.front;
                this.front.prev = node;
                this.front = node;
            }
            this.size++;
            return true;
        }

        /**
         * Insert element at end of deque
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param value element to insert
         * @return true if successful, false if deque full
         */
        public boolean insertLast(int value) {
            if (this.isFull())
                return false;
            Node node = new Node(value);
            if (this.size == 0) {
                this.front = node;
                this.rear = node;
            } else {
                node.prev = this.rear;
                this.rear.next = node;
                this.rear = node;
            }
            this.size++;
            return true;
        }

        /**
         * Delete element from front of deque
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if successful, false if deque empty
         */
        public boolean deleteFront() {
            if (this.isEmpty())
                return false;
            this.front = front.next;
            if (this.front != null)
                this.front.prev = null;
            this.size--;
            return true;
        }

        /**
         * Delete element from end of deque
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if successful, false if deque empty
         */
        public boolean deleteLast() {
            if (this.isEmpty())
                return false;
            this.rear = this.rear.prev;
            if (this.rear != null)
                this.rear.next = null;
            this.size--;
            return true;
        }

        /**
         * Get front element of deque
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if empty
         */
        public int getFront() {
            if (this.isEmpty())
                return -1;
            return this.front.data;
        }

        /**
         * Get rear element of deque
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return rear element if exists, -1 if empty
         */
        public int getRear() {
            if (this.isEmpty())
                return -1;
            return this.rear.data;
        }

        /**
         * Check if deque is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return this.size == 0;
        }

        /**
         * Check if deque is full
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
