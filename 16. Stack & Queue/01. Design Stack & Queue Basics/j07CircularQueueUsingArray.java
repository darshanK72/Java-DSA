/*-
 * Problem Statement:
 *     Design a circular queue that supports enQueue, deQueue, Front, Rear, isEmpty and isFull operations.
 *     https://leetcode.com/problems/design-circular-queue/
 * 
 * Input Operations:
 *     - enQueue(value): Insert element into circular queue
 *     - deQueue(): Delete element from circular queue
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

public class j07CircularQueueUsingArray {

    public static void main(String args[]){
        MyCircularQueue obj = new MyCircularQueue(5);
        System.out.println(obj.enQueue(1)); // true
        System.out.println(obj.enQueue(2)); // true
        System.out.println(obj.enQueue(3)); // true
        System.out.println(obj.enQueue(4)); // true
        System.out.println(obj.enQueue(5)); // true
        System.out.println(obj.enQueue(6)); // false, queue is full
        System.out.println(obj.Rear());     // 5
        System.out.println(obj.isFull());   // true
        System.out.println(obj.deQueue());  // true
        System.out.println(obj.enQueue(6)); // true
        System.out.println(obj.Rear());     // 6
        System.out.println(obj.isFull());   // true
    }

    /*-
     * Approach: Fixed Size Circular Array Implementation
     * 
     * Intuition:
     * - Use circular array with front and rear pointers
     * - Track size separately to distinguish empty vs full states
     * - Use modulo arithmetic for circular wraparound
     * - Initialize array with -1 to handle edge cases
     * - Front points to first element, rear to next free position
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(k) where k is size of circular queue
     */
    public static class MyCircularQueue {
        int[] arr;
        int size;
        int front;
        int rear;

        /**
         * Initialize circular queue with given size
         * Time Complexity: O(k)
         * Space Complexity: O(k)
         * 
         * @param k size of circular queue
         */
        public MyCircularQueue(int k) {
            this.arr = new int[k];
            this.size = 0;
            this.front = 0;
            this.rear = 0;
        }

        /**
         * Insert element into circular queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param value element to insert
         * @return true if successful, false if queue full
         */
        public boolean enQueue(int value) {
            if (this.isFull())
                return false;
            this.arr[this.rear] = value;
            this.rear = (this.rear + 1) % this.arr.length;
            this.size++;
            return true;
        }

        /**
         * Delete element from circular queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if successful, false if queue empty
         */
        public boolean deQueue() {
            if (this.isEmpty())
                return false;
            this.front = (this.front + 1) % this.arr.length;
            this.size--;
            return true;
        }

        /**
         * Get front element of circular queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if queue empty
         */
        public int Front() {
            if (this.isEmpty())
                return -1;
            return this.arr[this.front];
        }

        /**
         * Get rear element of circular queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return rear element if exists, -1 if queue empty
         */
        public int Rear() {
            if (this.isEmpty())
                return -1;
            return this.arr[(this.rear - 1 + this.arr.length) % this.arr.length];
        }

        /**
         * Check if circular queue is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            if (this.size == 0)
                return true;
            return false;
        }

        /**
         * Check if circular queue is full
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if full, false otherwise
         */
        public boolean isFull() {
            if (this.size == this.arr.length)
                return true;
            return false;
        }
    }

}
