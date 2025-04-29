/**
 * Problem Statement:
 *     LeetCode 1003. Check If Word Is Valid After Substitutions
 * 
 *     Given a string s, determine if it is valid. A string s is valid if we can 
 *     transform it into an empty string by applying the following operation any 
 *     number of times:
 *     - Take 'abc' substring and remove it from s
 * 
 * Input:
 *     - String s where 1 <= s.length <= 2 * 10^4
 *     - s consists of letters 'a', 'b', and 'c' only
 * 
 * Output:
 *     - Boolean indicating if string is valid
 * 
 * Example 1:
 *     Input: s = "aabcbc"
 *     Output: true
 *     
 *     Explanation:
 *     - Remove "abc" => "abc"
 *     - Remove "abc" => ""
 *     - Empty string is achieved, so return true
 * 
 * Example 2:
 *     Input: s = "abcabcababcc"
 *     Output: true
 *     
 *     Explanation:
 *     - Remove "abc" => "abcababcc"
 *     - Remove "abc" => "ababcc"
 *     - Remove "abc" => "cc"
 *     - String is not empty, so return false
 */

import java.util.Stack;

public class j05CheckValidWordAfterSubstitutions {

    public static void main(String args[]) {
        // Test cases with different scenarios
        String[] testCases = {
            "aabcbc",           // Valid case
            "abcabcababcc",     // Invalid case
            "abc",              // Single valid sequence
            "abcabc",          // Multiple valid sequences
            "aabbcc",          // Invalid sequence
            ""                  // Empty string
        };

        // Test each case
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Is Valid: " + isValid(test));
            System.out.println();
        }
    }

    /**
     * Approach: Using Stack
     * 
     * Intuition:
     * - Process string character by character
     * - When 'c' is found, check if previous two characters form 'abc'
     * - If valid sequence found, remove it (pop from stack)
     * - At end, stack should be empty for valid string
     * 
     * Time Complexity: O(n)
     * - Single pass through string
     * - Each character pushed/popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n characters
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        // Process each character
        for(char c : s.toCharArray()) {
            if(c == 'c') {
                // Check for 'abc' sequence
                if(!stack.isEmpty() && stack.peek() == 'b') {
                    stack.pop();  // Remove 'b'
                    if(!stack.isEmpty() && stack.peek() == 'a') {
                        stack.pop();  // Remove 'a'
                    } else {
                        return false;  // Invalid sequence
                    }
                } else {
                    return false;  // Invalid sequence
                }
            } else {
                stack.push(c);  // Add 'a' or 'b'
            }
        }
        
        // Valid if stack is empty
        return stack.isEmpty();
    }
}
