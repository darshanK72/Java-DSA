/**
 * Problem Statement:
 *     Delete the middle element of a stack.
 *     For odd size, delete (n+1)/2 th element
 *     For even size, delete (n/2) th element
 * 
 * Input:
 *     Stack of integers
 * 
 * Output:
 *     Stack with middle element removed
 * 
 * Example:
 *     Input Stack: [1,2,3,4,5] (top)
 *     Output Stack: [1,2,4,5] (top)
 * 
 *     Input Stack: [1,2,3,4] (top)
 *     Output Stack: [1,2,4] (top)
 */

import java.util.Stack;

public class j08DeleteMiddleOfStack {

    /**
     * Main method to demonstrate stack middle element deletion
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Original Stack: " + stack);
        deleteMid(stack);
        System.out.println("Stack after deleting middle element: " + stack);
    }

    /**
     * Delete middle element from stack
     * 
     * Approach 1: Using Auxiliary Stack
     * 
     * Intuition:
     * 1. Calculate middle position based on stack size
     * 2. Pop elements until middle and store in auxiliary stack
     * 3. Skip (pop) the middle element
     * 4. Push back all elements from auxiliary stack
     * 
     * Example:
     * Original: [1,2,3,4,5] (bottom to top)
     * After popping to aux: aux=[5,4,3], main=[1,2]
     * Skip middle: aux=[5,4], main=[1,2]
     * Final: [1,2,4,5]
     * 
     * Time Complexity: O(n) - need to handle each element
     * Space Complexity: O(n) - auxiliary stack for temporary storage
     * 
     * @param s input stack
     */
    public static void deleteMid(Stack<Integer> s) {
        int size = s.size();
        Stack<Integer> stack2 = new Stack<>();
        
        // Pop elements until middle
        while (stack2.size() <= (size / 2)) {
            stack2.push(s.pop());
        }
        
        // Skip middle element
        stack2.pop();
        
        // Push back remaining elements
        while (stack2.size() > 0) {
            s.push(stack2.pop());
        }
    }

    /**
     * Alternative Approach: Using Recursion
     * 
     * Intuition:
     * 1. Recursively reach middle element
     * 2. Remove middle element
     * 3. Push back all elements while backtracking
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - recursive call stack
     * 
     * @param s input stack
     * @param current current position while recursing
     */
    public static void deleteMidRecursive(Stack<Integer> s, int current) {
        // Base case: reached middle
        if (current == s.size()/2) {
            s.pop();
            return;
        }
        
        // Recursively reach middle
        int temp = s.pop();
        deleteMidRecursive(s, current + 1);
        
        // Push back while returning
        s.push(temp);
    }
}
