/**
 * Problem Statement:
 *     Given a string s of '(' and ')' parentheses, find minimum number of parentheses to add
 *     to make the string valid (has equal number of opening and closing parentheses).
 *     https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * 
 * Input:
 *     String containing only parentheses '(' and ')'
 * 
 * Output:
 *     Minimum number of parentheses needed to make string valid
 * 
 * Example 1:
 *     Input: "())"
 *     Output: 1
 *     Explanation: Add '(' at start: "(())"
 * 
 * Example 2:
 *     Input: "((("
 *     Output: 3
 *     Explanation: Add '))' at end: "((()))"
 */

import java.util.Stack;

public class j07MinAdditionToMakeValidParenthesisI {

    /**
     * Main method to demonstrate minimum parentheses addition
     * Shows results for various test cases
     */
    public static void main(String[] args) {
        String s = "())";
        System.out.println("Minimum number of parentheses to add: " + minAddToMakeValid(s));
    }
    
    /**
     * Calculate minimum parentheses needed for valid string
     * 
     * Approach: Single Pass Counter
     * 
     * Intuition:
     * 1. Track balance of opening vs closing brackets
     * 2. Track unbalanced closing brackets
     * 3. Final result is unbalanced closing + remaining opening
     * 
     * Algorithm:
     * 1. For '(': increment balance (need closing)
     * 2. For ')':
     *    - If balance > 0: decrement balance (matched)
     *    - If balance = 0: increment unbalanced (need opening)
     * 3. Return unbalanced + remaining balance
     * 
     * Example:
     * s = "())"
     * ( → balance:1, unbalanced:0
     * ) → balance:0, unbalanced:0
     * ) → balance:0, unbalanced:1
     * Result: 1 (need one opening bracket)
     * 
     * Time Complexity: O(n) - single pass through string
     * Space Complexity: O(1) - only uses two counters
     * 
     * @param s input string containing parentheses
     * @return minimum number of parentheses needed
     */
    public static int minAddToMakeValid(String s) {
        int openCount = 0;    // tracks unmatched opening brackets
        int closeNeeded = 0;  // tracks unmatched closing brackets
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--;  // matched pair
                } else {
                    closeNeeded++;  // need opening bracket
                }
            }
        }
        
        return openCount + closeNeeded;  // total brackets needed
    }

    /**
     * Alternative Approach: Stack Based
     * 
     * Intuition:
     * 1. Use stack to track opening brackets
     * 2. Count unmatched closing brackets
     * 3. Stack size gives remaining opening brackets
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int minAddToMakeValidUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        int unmatchedClose = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            } else {
                unmatchedClose++;
            }
        }
        
        return stack.size() + unmatchedClose;
    }
}
