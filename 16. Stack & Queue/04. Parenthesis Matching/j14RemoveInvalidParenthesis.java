/**
 * Problem Statement:
 *     LeetCode 301. Remove Invalid Parentheses
 * 
 *     Given a string s that contains parentheses and letters, remove the minimum number
 *     of invalid parentheses to make the input string valid. Return all possible results.
 * 
 * Input:
 *     - String containing parentheses and letters
 * 
 * Output:
 *     - List of all possible valid strings after minimum removals
 * 
 * Example:
 *     Input: "()())()"
 *     Output: ["()()()", "(())()"]
 *     
 *     Input: "(a)())()"
 *     Output: ["(a)()()", "(a())()"]
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class j14RemoveInvalidParenthesis {

    /**
     * Main method to remove invalid parentheses
     * 
     * Intuition:
     * 1. Find minimum removals needed using stack
     * 2. Try removing each parenthesis recursively
     * 3. Skip duplicates to avoid redundant processing
     * 4. Check validity when required removals reach zero
     * 
     * Time: O(2^n) - each character has two choices (keep/remove)
     * Space: O(n) - recursion stack and HashSet storage
     * 
     * @param s Input string
     * @return List of valid strings after removals
     */
    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> result = new HashSet<>();
        int minRemovalCount = minRemoval(s);
        removeInvalidPar(s, minRemovalCount, result, 0);
        return new ArrayList<>(result);
    }

    /**
     * Helper method to recursively try removing parentheses
     * 
     * Key Optimization:
     * Skip consecutive same characters (i != start && s.charAt(i) == s.charAt(i-1))
     * 
     * Reason for skipping:
     * 1. When we have consecutive same parentheses like "(((" or ")))"
     * 2. Removing any one of them would lead to the same result
     * 3. For example with "((()":
     *    - Removing first '(' -> "(())"
     *    - Removing second '(' -> "(())"
     *    - Both give same result, so we only process one
     * 4. This significantly reduces duplicate work in cases like "(((()"
     * 
     * @param s Current string being processed
     * @param rem Remaining removals needed
     * @param result Set to store valid results
     * @param start Starting index for current iteration
     */
    private static void removeInvalidPar(String s, int rem, HashSet<String> result, int start) {
        // Base case: no more removals needed
        if (rem == 0) {
            if (minRemoval(s) == 0) {  // Check if string is valid
                result.add(s);
            }
            return;
        }

        // Try removing each parenthesis
        for (int i = start; i < s.length(); i++) {
            // Skip consecutive same characters to avoid duplicates
            if (i != start && s.charAt(i) == s.charAt(i - 1))
                continue;

            // Only process parentheses
            if (s.charAt(i) != '(' && s.charAt(i) != ')')
                continue;

            // Remove current character and recurse
            String left = s.substring(0, i);
            String right = s.substring(i + 1);
            removeInvalidPar(left + right, rem - 1, result, i);
        }
    }

    /**
     * Helper method to calculate minimum removals needed
     * Uses stack to track unmatched parentheses
     * 
     * @param s Input string
     * @return Minimum number of removals needed
     */
    private static int minRemoval(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() == ')') {
                    stack.push(c);
                } else {
                    stack.pop();  // Matched pair found
                }
            }
        }
        
        return stack.size();  // Remaining unmatched parentheses
    }

    public static void main(String[] args) {
        j14RemoveInvalidParenthesis solution = new j14RemoveInvalidParenthesis();

        // Test Case 1: Multiple valid solutions
        System.out.println("Test 1: " + 
            solution.removeInvalidParentheses("()())()"));  // ["()()()", "(())()"]

        // Test Case 2: With letters
        System.out.println("Test 2: " + 
            solution.removeInvalidParentheses("(a)())"));  // ["(a)()", "(a())"]

        // Test Case 3: Consecutive parentheses
        System.out.println("Test 3: " + 
            solution.removeInvalidParentheses("(((("));  // [""]
    }
}
