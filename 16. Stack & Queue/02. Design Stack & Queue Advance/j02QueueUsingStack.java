/**
 * Problem Statement:
 *     Implement a first-in-first-out (FIFO) queue using only two stacks.
 *     https://leetcode.com/problems/implement-queue-using-stacks/
 * 
 * Input Operations:
 *     - push(x): Push element x to the back of queue
 *     - pop(): Remove and return the element from front of queue
 *     - peek(): Get the front element
 *     - empty(): Return whether the queue is empty
 * 
 * Output:
 *     - For pop/peek: Return element if exists
 *     - For empty: Return boolean status
 * 
 * Example:
 *     Input: 
 *     MyQueue queue = new MyQueue();
 *     queue.push(1);
 *     queue.push(2);
 *     queue.peek();
 *     queue.pop();
 *     queue.empty();
 *     
 *     Output:
 *     1
 *     1
 *     false
 * 
 *     Explanation:
 *     Push 1,2 to queue
 *     peek() returns front element 1
 *     pop() removes and returns 1
 *     empty() returns false as 2 is still in queue
 */

import java.util.Stack;

public class j02QueueUsingStack {

    public static void main(String args[]) {
        // Initialize queue using MyQueue class
        MyQueue queue = new MyQueue();

        // Enqueue elements
        queue.push(1);
        queue.push(2);
        queue.push(3);

        // Peek front element
        System.out.println("Front element: " + queue.peek()); // Should print 1

        // Dequeue element
        System.out.println("Dequeued element: " + queue.pop()); // Should print 1

        // Check if queue is empty
        System.out.println("Is queue empty? " + queue.empty()); // Should print false
    }
    
    /**
     * Approach 1: Two Stacks with Push Heavy
     * 
     * Intuition:
     * - Use two stacks to reverse elements and maintain FIFO order
     * - stack1 maintains queue order, stack2 helps in reversal
     * - For push: transfer all from stack1 to stack2, add new element to stack1,
     *   transfer back all from stack2 to stack1
     * - For pop/peek: directly operate on stack1 as elements are in correct order
     * 
     * Time Complexity:
     * - Push: O(n) where n is current size of queue
     * - Pop/Peek/Empty: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is number of elements in queue
     */
    public static class MyQueue {
        Stack<Integer> stack1; // Main stack maintaining queue order
        Stack<Integer> stack2; // Helper stack for reversal

        /**
         * Initialize empty queue using two stacks
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public MyQueue() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        /**
         * Push element to back of queue
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         */
        public void push(int x) {
            // Transfer all elements to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            // Push new element to stack1
            stack1.push(x);
            // Transfer back all elements to stack1
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        /**
         * Remove and return front element
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element of queue
         */
        public int pop() {
            return stack1.pop();
        }

        /**
         * Get front element without removing
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return front element of queue
         */
        public int peek() {
            return stack1.peek();
        }

        /**
         * Check if queue is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        public boolean empty() {
            return stack1.isEmpty();
        }
    }
}
