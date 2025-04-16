/*-
 * Problem Statement:
 *     Implement a queue that supports basic operations using a Linked List.
 *     https://practice.geeksforgeeks.org/problems/implement-queue-using-linked-list/1
 * 
 * Input Operations:
 *     - push(x): Enqueue element x into queue
 *     - pop(): Dequeue element from queue and return it
 *     Additional operations:
 *     - peek(): Return front element without removing
 *     - isEmpty(): Check if queue is empty
 *     - size(): Get current size of queue
 * 
 * Output:
 *     - For pop/peek: Return element if exists, -1 if queue empty
 *     - For isEmpty: Return boolean status
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

public class j06QueueUsingLinkedList {
    /*-
     * Approach: Linked List Implementation
     * 
     * Intuition:
     * - Use a linked list with front and rear pointers
     * - front points to first element for O(1) dequeue
     * - rear points to last element for O(1) enqueue
     * - Handle empty queue case by checking front == null
     * - Handle single element case by checking front == rear
     * 
     * Time Complexity:
     * - All operations: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is number of elements in queue
     */

    /**
     * Node class for queue implementation
     */
    public static class QueueNode {
        int data;
        QueueNode next;

        QueueNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class MyQueue {
        QueueNode front, rear;
        private int size;  // Track size of queue

        /**
         * Initialize empty queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        MyQueue() {
            front = null;
            rear = null;
            size = 0;
        }

        /**
         * Push element into queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @param a element to push
         */
        void push(int a) {
            QueueNode node = new QueueNode(a);
            if (rear == null) {
                rear = node;
                front = node;
            } else {
                rear.next = node;
                rear = node;
            }
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
            if (front == null)
                return -1;
            int out = front.data;
            if (front == rear) {
                front = null;
                rear = null;
            } else {
                front = front.next;
            }
            size--;
            return out;
        }

        /**
         * Return front element without removing
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element if exists, -1 if empty
         */
        int peek() {
            if (front == null)
                return -1;
            return front.data;
        }

        /**
         * Check if queue is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        boolean isEmpty() {
            return front == null;
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
