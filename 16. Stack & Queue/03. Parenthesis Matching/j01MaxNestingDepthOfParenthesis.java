/**
 * Problem Statement:
 *     Given a valid parentheses string, find the maximum nesting depth.
 *     The depth of a valid parentheses string is the maximum number of nested parentheses.
 *     https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 * 
 * Input:
 *     String containing valid parentheses '(' and ')'
 * 
 * Output:
 *     Maximum nesting depth as integer
 * 
 * Example 1:
 *     Input: "(1+(2*3)+((8)/4))+1"
 *     Output: 3
 *     Explanation: Digit tokens don't affect depth. Deepest part is "((8))"
 * 
 * Example 2:
 *     Input: "(1)+((2))+(((3)))"
 *     Output: 3
 *     Explanation: Maximum depth occurs at "(((3)))"
 */

import java.util.Stack;

public class j01MaxNestingDepthOfParenthesis {
    /**
     * Main method to demonstrate maximum nesting depth calculation
     * Shows results for both approaches with test cases
     */
    public static void main(String[] args) {
        String[] tests = {
            "()",           // 1 - simple pair
            "(())",         // 2 - nested once
            "(()(()))",     // 3 - complex nesting
            "(()())",       // 2 - adjacent pairs
            "((()))"        // 3 - triple nesting
        };

        for (String test : tests) {
            System.out.printf("Input: %s%nStack approach: %d%nEfficient approach: %d%n%n", 
                test, maxDepth(test), maxDepthEfficient(test));
        }
    }

    /**
     * Approach 1: Using Stack
     * 
     * Intuition:
     * 1. Use stack to track opening brackets
     * 2. Stack size at any point represents current depth
     * 3. Track maximum stack size seen
     * 
     * Example Walkthrough:
     * "(()(()))" →
     * ( → stack:[1], max=1
     * ( → stack:[1,1], max=2
     * ) → stack:[1], max=2
     * ( → stack:[1,1], max=2
     * ( → stack:[1,1,1], max=3
     * ) → stack:[1,1], max=3
     * ) → stack:[1], max=3
     * ) → stack:[], max=3
     * 
     * Time Complexity: O(n) - process each char once
     * Space Complexity: O(n) - stack can store all opening brackets
     * 
     * @param s input string of valid parentheses
     * @return maximum nesting depth
     */
    public static int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int maxDepth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                maxDepth = Math.max(maxDepth, stack.size());
            } else if (c == ')') {
                stack.pop();
            }
        }
        
        return maxDepth;
    }

    /**
     * Approach 2: Using Counter (Space Efficient)
     * 
     * Intuition:
     * 1. Use counter instead of stack
     * 2. Increment for '(', decrement for ')'
     * 3. Counter value represents current depth
     * 
     * Example Walkthrough:
     * "(()(()))" →
     * ( → depth=1, max=1
     * ( → depth=2, max=2
     * ) → depth=1, max=2
     * ( → depth=2, max=2
     * ( → depth=3, max=3
     * ) → depth=2, max=3
     * ) → depth=1, max=3
     * ) → depth=0, max=3
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - only uses two variables
     * 
     * @param s input string of valid parentheses
     * @return maximum nesting depth
     */
    public static int maxDepthEfficient(String s) {
        int maxDepth = 0;
        int currentDepth = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (s.charAt(i) == ')') {
                currentDepth--;
            }
        }
        
        return maxDepth;
    }
}