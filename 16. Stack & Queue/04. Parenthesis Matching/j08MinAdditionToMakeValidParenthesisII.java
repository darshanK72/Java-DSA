/**
 * Problem Statement:
 *     Given a parentheses string s containing only '(' and ')', where each '(' must be followed by 
 *     exactly two consecutive ')', return the minimum number of parentheses to add to make it valid.
 *     https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 * 
 * Input:
 *     String containing only parentheses '(' and ')'
 * 
 * Output:
 *     Minimum number of parentheses needed to make string valid
 * 
 * Valid Rules:
 *     1. Every '(' must be followed by exactly two consecutive ')'
 *     2. Left parenthesis '(' and right parenthesis ')' must be properly nested
 * 
 * Example 1:
 *     Input: "())"
 *     Output: 1
 *     Explanation: Add '(' at start: "(())"
 * 
 * Example 2:
 *     Input: "))())("
 *     Output: 3
 *     Explanation: Add '(' before first two )), and add )) after last (
 */

import java.util.Stack;

public class j08MinAdditionToMakeValidParenthesisII {

    /**
     * Calculate minimum insertions needed for valid parentheses string
     * 
     * Approach: Single Pass with Look-ahead
     * 
     * Intuition:
     * 1. Each '(' must be followed by two ')'
     * 2. Track needed opening and closing brackets separately
     * 3. Look ahead for consecutive closing brackets
     * 
     * Algorithm:
     * 1. For '(':
     *    - Increment open (needs two closing brackets)
     * 2. For ')':
     *    - Check if matches existing open bracket
     *    - Look ahead for second closing bracket
     *    - Track missing brackets accordingly
     * 
     * Example:
     * s = "())"
     * ( → open:1 (needs two closing)
     * ) → open:0, close:1 (needs one more closing)
     * ) → close:1 (matched second closing)
     * Result: 1 (need one opening bracket)
     * 
     * Time Complexity: O(n) - single pass with look-ahead
     * Space Complexity: O(1) - only uses two counters
     * 
     * @param s input string containing parentheses
     * @return minimum number of parentheses needed
     */
    public static int minInsertions(String s) {
        int openNeeded = 0;   // tracks required opening brackets
        int closeNeeded = 0;  // tracks required closing brackets
        
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            char next = i + 1 < s.length() ? s.charAt(i + 1) : '#';

            if (current == '(') {
                openNeeded++;  // each '(' needs two ')'
            } else {  // when we find ')'
                // Check if we have matching '('
                if (openNeeded > 0) {
                    openNeeded--;  // use existing '('
                } else {
                    closeNeeded++;  // need one extra '('
                }

                // Check for second consecutive ')'
                if (next == ')') {
                    i++;  // skip next ')' as we handled the pair
                } else {
                    closeNeeded++;  // need one more ')'
                }
            }
        }

        return 2 * openNeeded + closeNeeded;  // each '(' needs two ')', plus single ')' needs
    }

    /**
     * Alternative Approach: Using Stack
     * 
     * Intuition:
     * 1. Use stack to track opening brackets
     *    - Each '(' needs exactly two ')'
     *    - Stack helps track which '(' are waiting for closing brackets
     *    - Stack size tells us how many '(' still need matching
     * 
     * 2. Process closing brackets in pairs
     *    - When we see ')', look ahead for another ')'
     *    - If found pair '))', try to match with '(' from stack
     *    - If no '(' in stack, need to add one
     *    - If single ')', need to add another closing bracket
     * 
     * 3. Track missing brackets separately
     *    - For paired '))', but no '(' → need one '('
     *    - For single ')' with '(' → need one more ')'
     *    - For single ')' without '(' → need '(' and ')'
     *    - For remaining '(' in stack → need two ')' each
     * 
     * Example States:
     * "())("
     * ( → stack:[( ]
     * ) → stack:[ ], need one more ')'
     * ) → stack:[ ], matched with previous
     * ( → stack:[( ], need two ')'
     * Final: need 3 insertions (one ')' + two ')')
     * 
     * Time Complexity: O(n) - single pass through string
     * Space Complexity: O(n) - stack can store all opening brackets
     */
    public static int minInsertionsUsingStack(String s) {
        // Stack to store opening brackets that need matching
        Stack<Character> stack = new Stack<>();
        // Count of total insertions needed
        int insertions = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Found opening bracket
            if (s.charAt(i) == '(') {
                stack.push('(');
                continue;
            }else{
                // Case 1a: Found closing bracket - check for pair
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // Case 1a1: Have matching opening bracket
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    // Case 1a2: No matching opening bracket
                    else {
                        insertions++;  // Need one opening bracket
                    }
                    i++;  // Skip next closing bracket as we processed the pair
                }
                // Case 1b: Single closing bracket
                else {
                    // Case 1b1: Have matching opening bracket
                    if (!stack.isEmpty()) {
                        stack.pop();
                        insertions++;  // Need one more closing bracket
                    }
                    // Case 1b2: No matching opening bracket
                    else {
                        insertions += 2;  // Need both opening and extra closing
                    }
                }
            }
        }
        
        // Add two closing brackets for each remaining opening bracket
        return insertions + 2 * stack.size();
    }
}