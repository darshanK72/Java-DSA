/**
 * Problem Statement:
 *     Given two integer arrays pushed and popped with distinct values, verify if this could
 *     be the result of a sequence of push and pop operations on an initially empty stack.
 *     https://leetcode.com/problems/validate-stack-sequences/
 * 
 * Input:
 *     - pushed: Array representing push sequence
 *     - popped: Array representing pop sequence
 * 
 * Output:
 *     - true if sequence is valid, false otherwise
 * 
 * Example:
 *     Input: 
 *     pushed = [1,2,3,4,5]
 *     popped = [4,5,3,2,1]
 *     
 *     Output: true
 * 
 *     Explanation:
 *     Push 1,2,3,4
 *     Pop 4
 *     Push 5
 *     Pop 5,3,2,1
 */

import java.util.Stack;

public class j09ValidateStacks {

    /**
     * Main method to demonstrate stack sequence validation
     * Shows results for both approaches
     */
    public static void main(String[] args) {
        int[] pushed = { 1, 2, 3, 4, 5 };
        int[] popped = { 4, 5, 3, 2, 1 };
        System.out.println(validateStackSequences(pushed, popped)); // true
        System.out.println(validateStackSequencesEfficient(pushed, popped)); // true
    }

    /**
     * Approach 1: Using Explicit Stack
     * 
     * Intuition:
     * 1. Push elements onto stack
     * 2. For each push, check if current top matches next pop
     * 3. If matches, keep popping until mismatch
     * 4. Valid if stack empty after processing all elements
     * 
     * Example:
     * pushed=[1,2,3,4,5], popped=[4,5,3,2,1]
     * Push: [1] → [1,2] → [1,2,3] → [1,2,3,4]
     * Pop 4: [1,2,3]
     * Push 5, Pop 5: [1,2,3]
     * Pop 3,2,1: []
     * 
     * Time Complexity: O(n) - each element pushed/popped once
     * Space Complexity: O(n) - stack can hold all elements
     * 
     * @param pushed array representing push sequence
     * @param popped array representing pop sequence
     * @return true if sequence is valid, false otherwise
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && popped[j] == stack.peek()) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Approach 2: Using Input Array as Stack
     * 
     * Intuition:
     * 1. Use pushed array itself as stack
     * 2. Track top with index i
     * 3. Use pushed[0...i-1] as stack
     * 4. Overwrite values during push
     * 5. Decrease i during pop
     * 
     * Example:
     * pushed=[1,2,3,4,5], popped=[4,5,3,2,1]
     * i=4: [1,2,3,4] → i=3: [1,2,3]
     * i=4: [1,2,3,5] → i=3: [1,2,3]
     * i=2: [1,2] → i=1: [1] → i=0: []
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - no extra space
     * 
     * @param pushed array representing push sequence
     * @param popped array representing pop sequence
     * @return true if sequence is valid, false otherwise
     */
    public static boolean validateStackSequencesEfficient(int[] pushed, int[] popped) {
        int i = 0;
        int j = 0;
        for (int val : pushed) {
            pushed[i++] = val;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }
        return i == 0;
    }
}
