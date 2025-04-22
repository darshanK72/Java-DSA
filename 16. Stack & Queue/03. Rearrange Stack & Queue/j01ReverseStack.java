/**
 * Problem Statement:
 *     Given a stack, reverse its contents using different approaches:
 *     1. Using Queue
 *     2. Using Array
 *     3. Using Recursion
 * 
 * Input:
 *     Stack of integers
 * 
 * Output:
 *     Same stack with elements in reversed order
 * 
 * Example:
 *     Input Stack: [1,2,3,4,5] (top)
 *     Output Stack: [5,4,3,2,1] (top)
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class j01ReverseStack {

    /**
     * Main method to demonstrate different stack reversal approaches
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }

        System.out.println("Original Stack: " + stack);
        
        // Test each approach
        Stack<Integer> s1 = (Stack<Integer>)stack.clone();
        reverseStackUsingQueue(s1);
        System.out.println("Reversed Stack using Queue: " + s1);

        Stack<Integer> s2 = (Stack<Integer>)stack.clone();
        reverseStackUsingArray(s2);
        System.out.println("Reversed Stack using Array: " + s2);

        Stack<Integer> s3 = (Stack<Integer>)stack.clone();
        reverseStackRecursive(s3);
        System.out.println("Reversed Stack using Recursion: " + s3);
    }

    /**
     * Approach 1: Using Queue
     * 
     * Intuition:
     * 1. Pop all elements from stack and enqueue to queue (reverses order)
     * 2. Dequeue all elements and push back to stack (maintains reversed order)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - uses additional queue
     * 
     * @param stack stack to be reversed
     */
    public static void reverseStackUsingQueue(Stack<Integer> stack) {
        Queue<Integer> queue = new LinkedList<>();
        
        // Step 1: Stack → Queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        
        // Step 2: Queue → Stack
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
    }

    /**
     * Approach 2: Using Array
     * 
     * Intuition:
     * 1. Pop all elements to array (reverses order)
     * 2. Push array elements back to stack (maintains reversed order)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - uses additional array
     * 
     * @param stack stack to be reversed
     */
    public static void reverseStackUsingArray(Stack<Integer> stack) {
        int[] array = new int[stack.size()];
        
        // Step 1: Stack → Array
        for (int i = 0; !stack.isEmpty(); i++) {
            array[i] = stack.pop();
        }
        
        // Step 2: Array → Stack
        for (int num : array) {
            stack.push(num);
        }
    }

    /**
     * Approach 3: Using Recursion
     * 
     * Intuition:
     * 1. Recursively reach bottom of stack
     * 2. Insert each element at bottom while backtracking
     * 
     * Example:
     * [1,2,3] (top) →
     * Pop 3, reverse [1,2] →
     * Pop 2, reverse [1] →
     * Pop 1, reverse [] →
     * Insert 1 at bottom: [1] →
     * Insert 2 at bottom: [2,1] →
     * Insert 3 at bottom: [3,2,1]
     * 
     * Time Complexity: O(n²) - n recursive calls, each doing O(n) work
     * Space Complexity: O(n) - recursive call stack
     * 
     * @param stack stack to be reversed
     */
    public static void reverseStackRecursive(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        
        // Remove top element
        int top = stack.pop();
        // Reverse remaining stack
        reverseStackRecursive(stack);
        // Insert removed element at bottom
        insertAtBottom(stack, top);
    }

    /**
     * Helper method to insert element at bottom of stack
     * 
     * @param stack stack to insert into
     * @param item element to insert at bottom
     */
    private static void insertAtBottom(Stack<Integer> stack, int item) {
        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }
        
        int top = stack.pop();
        insertAtBottom(stack, item);
        stack.push(top);
    }
}
