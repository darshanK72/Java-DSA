/**
 * Problem Statement:
 *     Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 *     determine if the input string is valid.
 *     https://leetcode.com/problems/valid-parentheses/
 * 
 * Input:
 *     String containing only parentheses characters
 * 
 * Output:
 *     true if parentheses are valid, false otherwise
 * 
 * Valid Conditions:
 *     1. Open brackets must be closed by the same type of brackets
 *     2. Open brackets must be closed in the correct order
 *     3. Every close bracket has a corresponding open bracket
 * 
 * Example:
 *     Input: s = "({[]})"
 *     Output: true
 * 
 *     Input: s = "({[})"
 *     Output: false
 */

import java.util.Stack;

public class j01ValidParenthesis {
    /**
     * Main method to demonstrate parenthesis validation
     * Shows results for various test cases
     */
    public static void main(String[] args) {
        String s = "({[]})";
        System.out.println(isValid(s)); // true
        s = "({[})";
        System.out.println(isValid(s)); // false
        s = "({[}])";
        System.out.println(isValid(s)); // false
        s = "({[})]";
        System.out.println(isValid(s)); // false
    }

    /**
     * Check if string has valid parentheses
     * 
     * Approach: Using Stack
     * 
     * Intuition:
     * 1. Push opening brackets onto stack
     * 2. For closing bracket, check if matches top of stack
     * 3. Valid if stack empty after processing all characters
     * 
     * Algorithm:
     * 1. For each character in string:
     *    - If opening bracket: push to stack
     *    - If closing bracket:
     *      * If stack empty: return false (no matching open)
     *      * If matches top: pop stack
     *      * If doesn't match: return false (wrong order)
     * 2. Return true if stack empty (all matched)
     * 
     * Example:
     * s = "({[]})"
     * Stack changes:
     * ( → [(]
     * { → [({]
     * [ → [({[]
     * ] → [({]
     * } → [(]
     * ) → []
     * Empty stack → Valid
     * 
     * Time Complexity: O(n) - single pass through string
     * Space Complexity: O(n) - stack can store all open brackets
     * 
     * @param s input string containing parentheses
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Push opening brackets
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            // Handle closing brackets
            else if (stack.isEmpty()) {
                return false;  // No matching opening bracket
            }
            else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            }
            else if (c == ']' && stack.peek() == '[') {
                stack.pop();
            }
            else if (c == '}' && stack.peek() == '{') {
                stack.pop();
            }
            else {
                return false;  // Invalid matching
            }
        }
        
        return stack.isEmpty();  // Check all brackets matched
    }
}