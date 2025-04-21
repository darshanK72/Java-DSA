/**
 * Problem Statement:
 *     Given a string containing just the characters '(' and ')', find the length
 *     of the longest valid (well-formed) parentheses substring.
 *     https://leetcode.com/problems/longest-valid-parentheses/
 * 
 * Input:
 *     String containing only parentheses '(' and ')'
 * 
 * Output:
 *     Length of longest valid parentheses substring
 * 
 * Example 1:
 *     Input: "(()"
 *     Output: 2
 *     Explanation: Longest valid substring is "()"
 * 
 * Example 2:
 *     Input: ")()())"
 *     Output: 4
 *     Explanation: Longest valid substring is "()()"
 */

import java.util.Stack;

public class j03LongestValidParenthesis {

    /**
     * Approach 1: Using Stack and Length Array
     * 
     * Intuition:
     * 1. Store indices of opening brackets in stack
     * 2. Track valid lengths at each position
     * 3. When closing bracket found, add length to current position
     * 4. Consider previous valid length for concatenation
     * 
     * Example:
     * s = "()((()))"
     * i=1: [2]        - first pair
     * i=5: [2,4]      - inner pair
     * i=6: [2,6]      - middle pair
     * i=7: [8]        - outer pair
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int longestValidParentheses(String s) {
        int[] validLengths = new int[s.length()];
        Stack<Integer> openBrackets = new Stack<>();
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            
            if (currentChar == '(') {
                openBrackets.push(i);
            } else {  // closing bracket
                if (openBrackets.isEmpty()) {
                    continue;
                }
                
                int matchingOpenIndex = openBrackets.pop();
                int currentValidLength = i - matchingOpenIndex + 1;
                
                // Add previous valid length if exists
                if (matchingOpenIndex > 0) {
                    currentValidLength += validLengths[matchingOpenIndex - 1];
                }
                
                validLengths[i] = currentValidLength;
                maxLength = Math.max(maxLength, currentValidLength);
            }
        }
        
        return maxLength;
    }

    /**
     * Approach 2: Using Two Counters (Space Efficient)
     * 
     * Intuition:
     * 1. Use left and right counters for '(' and ')'
     * 2. Scan left to right and right to left
     * 3. Reset counters when invalid sequence found
     * 4. Update max length when counters are equal
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int longestValidParenthesesEfficient(String s) {
        int maxLength = 0;
        int leftCount = 0, rightCount = 0;
        
        // Left to right scan
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            
            if (leftCount == rightCount) {
                maxLength = Math.max(maxLength, 2 * rightCount);
            } else if (rightCount > leftCount) {
                leftCount = rightCount = 0;
            }
        }
        
        // Right to left scan
        leftCount = rightCount = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            
            if (leftCount == rightCount) {
                maxLength = Math.max(maxLength, 2 * leftCount);
            } else if (leftCount > rightCount) {
                leftCount = rightCount = 0;
            }
        }
        
        return maxLength;
    }
}
