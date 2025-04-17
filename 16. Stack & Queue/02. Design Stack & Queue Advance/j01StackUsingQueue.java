/*-
 * Problem Statement:
 *     Implement a last-in-first-out (LIFO) stack using only two queues.
 *     https://leetcode.com/problems/implement-stack-using-queues/
 * 
 * Input Operations:
 *     - push(x): Push element x onto stack
 *     - pop(): Remove and return the top element
 *     - top(): Get the top element
 *     - empty(): Return whether the stack is empty
 * 
 * Output:
 *     - For pop/top: Return element if exists
 *     - For empty: Return boolean status
 * 
 * Example:
 *     Input: 
 *     MyStack stack = new MyStack();
 *     stack.push(1);
 *     stack.push(2);
 *     stack.top();
 *     stack.pop();
 *     stack.empty();
 *     
 *     Output:
 *     2
 *     2
 *     false
 * 
 *     Explanation:
 *     Push 1,2 onto stack
 *     top() returns 2 without removing it
 *     pop() removes and returns 2
 *     empty() returns false as stack still has 1
 */

import java.util.ArrayDeque;
import java.util.Queue;

public class j01StackUsingQueue {

    public static void main(String args[]){
        // Initialize stack using MyStack class
        MyStack stack = new MyStack();
        
        // Push elements onto stack
        stack.push(1);
        stack.push(2);
        System.out.println("Top element after push: " + stack.top()); // Should print 2
        
        // Pop element from stack
        System.out.println("Popped element: " + stack.pop()); // Should print 2
        
        // Check if stack is empty
        System.out.println("Is stack empty? " + stack.empty()); // Should print false
        System.out.println("Top element after pop: " + stack.top()); // Should print 1
    }

    /*-
     * Approach: Single Queue with Rotation
     * 
     * Intuition:
     * - Use single queue and maintain LIFO order by rotation
     * - For push: add new element and rotate existing elements behind it
     * - This way newest element stays at front (top of stack)
     * - Pop/Top operations directly work on front of queue
     * 
     * Time Complexity:
     * - Push: O(n) where n is current size of stack
     * - Pop/Top/Empty: O(1)
     * 
     * Space Complexity:
     * - O(n) where n is number of elements in stack
     */
    public static class MyStack {
        Queue<Integer> queue;

        /**
         * Initialize empty stack using queue
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public MyStack() {
            this.queue = new ArrayDeque<>();
        }

        /**
         * Push element onto stack
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * @param x element to push
         */
        public void push(int x) {
            this.queue.add(x);
            for (int i = 1; i < queue.size(); i++) {
                this.queue.add(this.queue.remove());
            }
        }

        /**
         * Remove and return top element
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return removed top element
         */
        public int pop() {
            return this.queue.remove();
        }

        /**
         * Get top element without removing
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return top element
         */
        public int top() {
            return queue.peek();
        }

        /**
         * Check if stack is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         * 
         * @return true if empty, false otherwise
         */
        public boolean empty() {
            return this.queue.size() == 0;
        }
    }
}
