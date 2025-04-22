/**
 * Problem Statement:
 *     Given a stack of integers, sort it in ascending order using recursion.
 *     Only the following operations are allowed:
 *     1. push(x) : Push element x onto stack
 *     2. pop()   : Remove and return top element from stack
 *     3. peek()  : Return top element without removing it
 *     4. isEmpty(): Return true if stack is empty
 * 
 * Input:
 *     Stack of integers
 * 
 * Output:
 *     Same stack with elements sorted in ascending order (smallest at bottom)
 * 
 * Example:
 *     Input Stack:  [5,1,4,2,3] (top)
 *     Output Stack: [1,2,3,4,5] (top)
 */

import java.util.Stack;

public class j03SortStack {

    /**
     * Main method to demonstrate stack sorting
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 5; i >= 1; i--) {
            stack.push(i);
        }

        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack: " + stack);
    }

    /**
     * Sort stack using recursion
     * 
     * Intuition:
     * 1. Remove elements one by one using recursion
     * 2. Insert each element at its correct position in sorted portion
     * 
     * Example Walkthrough:
     * [5,1,4,2,3] →
     * Remove 3, sort [5,1,4,2] →
     * Remove 2, sort [5,1,4] →
     * Remove 4, sort [5,1] →
     * Remove 1, sort [5] →
     * Base case: [5]
     * Insert back: 1,2,3,4,5 in correct positions
     * 
     * Time Complexity: O(n²) - n recursive calls, each doing O(n) work
     * Space Complexity: O(n) - recursive call stack
     * 
     * @param stack stack to be sorted
     */
    public static void sortStack(Stack<Integer> stack) {
        // Base case: empty or single element
        if (stack.isEmpty()) {
            return;
        }

        // Remove top element
        int current = stack.pop();
        
        // Recursively sort remaining stack
        sortStack(stack);
        
        // Insert current element at correct position
        insertAtCorrectPos(stack, current);
    }

    /**
     * Helper method to insert element at correct position in sorted stack
     * 
     * Intuition:
     * 1. If current element is larger than top or stack empty, push
     * 2. Otherwise, remove top element and recurse
     * 3. Push removed elements back after insertion
     * 
     * Example:
     * Insert 3 into [1,2,4,5]:
     * 5 > 3: remove 5
     * 4 > 3: remove 4
     * 3 > 2: insert 3
     * Push back: 4,5
     * 
     * @param stack sorted stack
     * @param value element to insert
     */
    public static void insertAtCorrectPos(Stack<Integer> stack, int value) {
        // Base case: insert if stack empty or value larger than top
        if (stack.isEmpty() || stack.peek() <= value) {
            stack.push(value);
            return;
        }

        // Remove top element
        int top = stack.pop();
        
        // Recursively insert value
        insertAtCorrectPos(stack, value);
        
        // Push back removed element
        stack.push(top);
    }
}
