/**
 * Problem Statement:
 *     Implement a queue that supports basic operations using an array.
 *     https://practice.geeksforgeeks.org/problems/implement-queue-using-array/1
 * 
 * Input Operations:
 *     - push(x): Enqueue element x into queue
 *     - pop(): Dequeue element from queue and return it
 *     Additional operations:
 *     - peek(): Return front element without removing
 *     - isEmpty(): Check if queue is empty
 *     - isFull(): Check if queue is full
 *     - size(): Get current size of queue
 * 
 * Output:
 *     - For pop/peek: Return element if exists, -1 if queue empty
 *     - For isEmpty/isFull: Return boolean status
 *     - For size: Return current number of elements
 * 
 * Example:
 *     Input: 
 *     push(2), push(3)
 *     pop(), peek()
 *     push(4)
 *     size()
 *     
 *     Output:
 *     2, 3
 *     2
 * 
 *     Explanation:
 *     Queue after push operations: [2,3]
 *     pop() returns and removes 2
 *     peek() returns 3 without removing
 *     After push(4): [3,4]
 *     size() returns 2
 */

public class j05QueueUsingArray {
    /**
     * Approach: Circular Array Implementation
     * 
     * Intuition:
     * - Use circular array to efficiently utilize space
     * - front tracks the first element
     * - rear tracks the next free position
     * - When rear reaches end, wrap around to start if space available
     * - Keep track of size for distinguishing empty vs full conditions
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is array size (100005 in this case)
     */
    static class MyQueue {
        int front, rear, size;
        int arr[] = new int[100005];

        /**
         * Initialize empty queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        MyQueue() {
            front = 0;
            rear = 0;
            size = 0;
        }

        /**
         * Push element into queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         * @return void, silently fails if queue full
         */
        void push(int x) {
            if (isFull())
                return;

            arr[rear] = x;
            rear = (rear + 1);
            size++;
        }

        /**
         * Remove and return front element
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if empty
         */
        int pop() {
            if (isEmpty())
                return -1;

            int value = arr[front];
            front = (front + 1);
            size--;
            return value;
        }

        /**
         * Return front element without removing
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if empty
         */
        int peek() {
            if (isEmpty())
                return -1;
            return arr[front];
        }

        /**
         * Check if queue is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        boolean isEmpty() {
            return size == 0;
        }

        /**
         * Check if queue is full
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if full, false otherwise
         */
        boolean isFull() {
            return size == arr.length;
        }

        /**
         * Get current size of queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return number of elements in queue
         */
        int size() {
            return size;
        }
    }
}
