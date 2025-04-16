/*-
 * Problem Statement:
 *     Design a circular double-ended queue (deque) that supports various operations.
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

public class j09CircularDequeUsingArray {

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
     * Approach: Fixed Size Circular Array Implementation
     * 
     * Intuition:
     * - Use circular array with front and rear pointers
     * - Track size separately to distinguish empty vs full states
     * - For front operations: decrement front with wraparound
     * - For rear operations: increment rear with wraparound
     * - Use modulo arithmetic ((index + length) % length) for circular wraparound
     * - Front points to first element, rear to next free position
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(k) where k is size of deque
     */
    public static class MyCircularDeque {

        int[] arr;
        int size;
        int front;
        int rear;

        /**
         * Initialize circular deque with given size
         * Time Complexity: O(1)
         * Space Complexity: O(k)
         * 
         * @param k size of circular deque
         */
        public MyCircularDeque(int k) {
            this.arr = new int[k];
            this.size = 0;
            this.front = 0;
            this.rear = 0;
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

            this.front = (this.front - 1 + this.arr.length) % this.arr.length;
            this.arr[this.front] = value;
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
            this.arr[this.rear] = value;
            this.rear = (this.rear + 1) % this.arr.length;
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
            this.front = (this.front + 1) % this.arr.length;
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
            this.rear = (this.rear - 1 + this.arr.length) % this.arr.length;
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
            return this.arr[this.front];
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
            return this.arr[(this.rear - 1 + this.arr.length) % this.arr.length];
        }

        /**
         * Check if deque is empty
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
         * Check if deque is full
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
