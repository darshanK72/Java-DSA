/**
 * Problem Statement:
 *     Given a queue, reverse its contents using different approaches:
 *     1. Using Stack
 *     2. Using Array
 *     3. Using Recursion
 * 
 * Input:
 *     Queue of integers
 * 
 * Output:
 *     Same queue with elements in reversed order
 * 
 * Example:
 *     Input Queue: [1,2,3,4,5] (front)
 *     Output Queue: [5,4,3,2,1] (front)
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class j02ReverseQueue {

    /**
     * Main method to demonstrate different queue reversal approaches
     */
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            queue.add(i);
        }

        System.out.println("Original Queue: " + queue);

        // Test each approach with separate copies
        Queue<Integer> q1 = new LinkedList<>(queue);
        reverseQueueUsingStack(q1);
        System.out.println("Reversed Queue using Stack: " + q1);

        Queue<Integer> q2 = new LinkedList<>(queue);
        reverseQueueUsingArray(q2);
        System.out.println("Reversed Queue using Array: " + q2);

        Queue<Integer> q3 = new LinkedList<>(queue);
        reverseQueueRecursive(q3);
        System.out.println("Reversed Queue using Recursion: " + q3);
    }

    /**
     * Approach 1: Using Stack
     * 
     * Intuition:
     * 1. Remove all elements from queue and push to stack (reverses order)
     * 2. Pop all elements from stack and add to queue (maintains reversed order)
     * 
     * Example:
     * Queue: [1,2,3] (front) →
     * Stack: [3,2,1] (top) →
     * Queue: [3,2,1] (front)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - uses additional stack
     * 
     * @param q queue to be reversed
     */
    public static void reverseQueueUsingStack(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<>();
        
        // Step 1: Queue → Stack
        while (!q.isEmpty()) {
            stack.push(q.remove());
        }
        
        // Step 2: Stack → Queue
        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }
    }

    /**
     * Approach 2: Using Array
     * 
     * Intuition:
     * 1. Store elements in array in queue order
     * 2. Add elements back to queue in reverse order
     * 
     * Example:
     * Queue: [1,2,3] →
     * Array: [1,2,3] →
     * Add back: 3,2,1
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - uses additional array
     * 
     * @param q queue to be reversed
     */
    public static void reverseQueueUsingArray(Queue<Integer> q) {
        int[] array = new int[q.size()];
        
        // Step 1: Queue → Array
        for (int i = 0; !q.isEmpty(); i++) {
            array[i] = q.remove();
        }
        
        // Step 2: Array → Queue (in reverse)
        for (int i = array.length - 1; i >= 0; i--) {
            q.add(array[i]);
        }
    }

    /**
     * Approach 3: Using Recursion
     * 
     * Intuition:
     * 1. Recursively reach end of queue
     * 2. Add elements back while backtracking
     * 
     * Example:
     * [1,2,3] →
     * Remove 1, reverse [2,3] →
     * Remove 2, reverse [3] →
     * Remove 3, reverse [] →
     * Add back: 3,2,1
     * 
     * Time Complexity: O(n) - each element processed once
     * Space Complexity: O(n) - recursive call stack
     * 
     * @param q queue to be reversed
     */
    public static void reverseQueueRecursive(Queue<Integer> q) {
        if (q.isEmpty()) {
            return;
        }
        
        // Remove front element
        int front = q.remove();
        // Reverse remaining queue
        reverseQueueRecursive(q);
        // Add removed element at end
        q.add(front);
    }
}
