/**
 * Problem Statement:
 *     Given a string s containing parentheses and lowercase English letters,
 *     remove the minimum number of invalid parentheses to make the input string valid.
 *     https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * 
 * Input:
 *     String containing parentheses '(', ')' and lowercase letters
 * 
 * Output:
 *     Valid parentheses string after removing minimum invalid brackets
 * 
 * Example 1:
 *     Input: "lee(t(c)o)de)"
 *     Output: "lee(t(c)o)de"
 *     Explanation: Remove last ')'
 * 
 * Example 2:
 *     Input: "a)b(c)d"
 *     Output: "ab(c)d"
 *     Explanation: Remove first ')'
 */

import java.util.Stack;

public class j09MinRemovalToMakeValidParenthesis {

    public static void main(String[] args) {
        String s = "a)b(c)d";
        System.out.println("Minimum number of parentheses to remove: " + minRemoveToMakeValid(s));
    }

    /**
     * Remove minimum invalid parentheses to make string valid
     * 
     * Approach: Using Stack and Boolean Array
     * 
     * Intuition:
     * 1. Track unbalanced parentheses using boolean array
     * 2. Use stack to match opening with closing brackets
     * 3. Mark unmatched brackets for removal
     * 4. Build result excluding marked brackets
     * 
     * Algorithm:
     * 1. First Pass:
     *    - Mark all opening brackets as unbalanced initially
     *    - For closing bracket, try to match with last opening
     *    - If matched, mark opening as balanced
     *    - If unmatched, mark closing as unbalanced
     * 
     * 2. Second Pass:
     *    - Include only balanced brackets and letters
     *    - Skip all marked unbalanced brackets
     * 
     * Example:
     * s = "a)b(c)d"
     * First Pass:
     * a → no mark
     * ) → unbalanced[1] = true (no matching opening)
     * b → no mark
     * ( → unbalanced[3] = true initially
     * c → no mark
     * ) → unbalanced[3] = false (matched with previous)
     * d → no mark
     * Result: "ab(c)d"
     * 
     * Time Complexity: O(n) - two passes through string
     * Space Complexity: O(n) - boolean array and stack
     * 
     * @param s input string containing parentheses and letters
     * @return valid string after removing invalid parentheses
     */
    public static String minRemoveToMakeValid(String s) {
        // Track unbalanced parentheses positions
        boolean[] unbalanced = new boolean[s.length()];
        // Stack to store indices of opening brackets
        Stack<Integer> stack = new Stack<>();

        // First pass: Mark unbalanced parentheses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
                unbalanced[i] = true;  // Mark as unbalanced initially
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    unbalanced[stack.pop()] = false;  // Match found, mark opening as balanced
                } else {
                    unbalanced[i] = true;  // No matching opening bracket
                }
            }
        }

        // Second pass: Build result excluding unbalanced brackets
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!unbalanced[i]) {
                result.append(s.charAt(i));
            }
        }
        
        return result.toString();
    }
}
