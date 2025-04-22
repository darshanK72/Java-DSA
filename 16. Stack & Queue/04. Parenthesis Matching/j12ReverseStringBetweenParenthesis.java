/**
 * Problem Statement:
 *     Given a string s containing parentheses and lowercase English letters,
 *     reverse the strings contained in each pair of matching parentheses,
 *     starting from the innermost parentheses.
 *     https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * 
 * Input:
 *     String containing parentheses '(', ')' and lowercase letters
 * 
 * Output:
 *     String with content between each pair of parentheses reversed
 * 
 * Example 1:
 *     Input: "(abcd)"
 *     Output: "dcba"
 * 
 * Example 2:
 *     Input: "(u(love)i)"
 *     Output: "iloveu"
 *     Explanation: "love" is reversed first, then "uevoli" is reversed
 */

import java.util.Stack;

public class j12ReverseStringBetweenParenthesis {

    /**
     * Main method to demonstrate string reversal between parentheses
     */
    public static void main(String[] args) {
        String[] tests = {
            "(abcd)",          // "dcba"
            "(u(love)i)",      // "iloveu"
            "(ed(et(oc))el)",  // "leetcode"
            "(abcd)(efg)hij(klmno)pqr"  // "dcbagfehijnomlkpqr"
        };

        for (String test : tests) {
            System.out.printf("Input: %s%nStack approach: %s%nEfficient approach: %s%n%n", 
                test, reverseParentheses(test), reverseParenthesesEfficient(test));
        }
    }

    /**
     * Approach 1: Using Stack
     * 
     * Intuition:
     * 1. Push characters onto stack until ')'
     * 2. On finding ')', pop and reverse until '('
     * 3. Push reversed sequence back onto stack
     * 
     * Example Walkthrough:
     * "(abcd)" →
     * Push: [(,a,b,c,d]
     * Find ): reverse "abcd" → "dcba"
     * Final: [d,c,b,a]
     * 
     * Time Complexity: O(n^2) - nested reversals
     * Space Complexity: O(n) - stack storage
     * 
     * @param s input string
     * @return string with reversed substrings
     */
    public static String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == ')') {
                // Build reversed substring
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                stack.pop();  // Remove '('
                
                // Push reversed chars back
                for (char ch : temp.toString().toCharArray()) {
                    stack.push(ch);
                }
            } else {
                stack.push(c);
            }
        }

        // Build final result
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    /**
     * Approach 2: Using Pair Matching and Direction
     * 
     * Intuition:
     * 1. First pass: Match and store parentheses pairs
     * 2. Second pass: Navigate string with direction changes
     * 3. Reverse direction when crossing parentheses
     * 
     * Example:
     * "(abcd)" →
     * Pairs: [0↔4]
     * Navigate: 
     * → until '(' at 0
     * ← from 4 to 1 collecting chars
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param s input string
     * @return string with reversed substrings
     */
    public static String reverseParenthesesEfficient(String s) {
        int[] pairs = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        
        // Match parentheses pairs
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int openPos = stack.pop();
                pairs[openPos] = i;
                pairs[i] = openPos;
            }
        }

        // Navigate with direction changes
        StringBuilder result = new StringBuilder();
        int direction = 1;
        for (int i = 0; i < s.length(); i += direction) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pairs[i];          // Jump to matching bracket
                direction *= -1;       // Reverse direction
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}
