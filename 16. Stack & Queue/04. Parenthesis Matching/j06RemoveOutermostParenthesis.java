
/**
 * Problem Statement:
 *     Remove the outermost parentheses of every primitive valid parentheses string in the input.
 *     A primitive valid parentheses string is one that cannot be split into two non-empty valid strings.
 *     https://leetcode.com/problems/remove-outermost-parentheses/
 * 
 * Input:
 *     String containing only parentheses '(' and ')'
 * 
 * Output:
 *     String with outermost parentheses removed from each primitive decomposition
 * 
 * Example 1:
 *     Input: "(()())(())"
 *     Output: "()()()"
 *     Primitive decomposition: "(()())" + "(())" → "()()" + "()"
 * 
 * Example 2:
 *     Input: "(()())(())(()(()))"
 *     Output: "()()()()(())"
 *     Primitive decomposition: "(()())" + "(())" + "(()(()))" → "()()" + "()" + "()(())"
 */

import java.util.Stack;

public class j06RemoveOutermostParenthesis {

    /**
     * Main method to demonstrate outermost parentheses removal
     * Shows results for both approaches with various test cases
     */
    public static void main(String[] args) {
        String s = "(()())(())";
        System.out.println(removeOuterParentheses(s)); // ()()()
        s = "(()())(())(()(()))";
        System.out.println(removeOuterParentheses(s)); // ()()()()(())
        s = "()()";
        System.out.println(removeOuterParentheses(s)); // ""
    }

    /**
     * Approach 1: Using Stack
     * 
     * Intuition:
     * 1. Use stack to track nesting level
     * 2. Include parenthesis only if not at outermost level
     * 3. Stack size > 1 indicates inner parentheses
     * 
     * Algorithm:
     * 1. For '(':
     * - Push to stack
     * - Append if stack size > 1 (not outermost)
     * 2. For ')':
     * - Append if stack size > 1 (not outermost)
     * - Pop from stack
     * 
     * Example:
     * s = "(()())"
     * ( → stack:[1], out:""
     * ( → stack:[1,2], out:"("
     * ) → stack:[1], out:"()"
     * ( → stack:[1,2], out:"()("
     * ) → stack:[1], out:"()()"
     * ) → stack:[], out:"()()"
     * 
     * Time Complexity: O(n) - single pass through string
     * Space Complexity: O(n) - stack can store all open brackets
     * 
     * @param s input string containing parentheses
     * @return string with outermost parentheses removed
     */
    public static String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder out = new StringBuilder("");
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                if (stack.size() > 1) {
                    out.append('(');
                }
            }
            if (c == ')') {
                if (stack.size() > 1) {
                    out.append(')');
                }
                stack.pop();
            }
        }
        return out.toString();
    }

    /**
     * Approach 2: Using Counter (Space Efficient)
     * 
     * Intuition:
     * 1. Use counter instead of stack to track nesting
     * 2. Counter > 1 indicates inner parentheses
     * 3. Increment for '(', decrement for ')'
     * 
     * Algorithm:
     * 1. For '(':
     * - Increment counter
     * - Append if counter > 1
     * 2. For ')':
     * - Append if counter > 1
     * - Decrement counter
     * 
     * Example:
     * s = "(()())"
     * ( → count:1, out:""
     * ( → count:2, out:"("
     * ) → count:1, out:"()"
     * ( → count:2, out:"()("
     * ) → count:1, out:"()()"
     * ) → count:0, out:"()()"
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - only uses counter variable
     * 
     * @param s input string containing parentheses
     * @return string with outermost parentheses removed
     */
    public static String removeOuterParenthesesEfficient(String s) {
        int ct = 0;
        StringBuilder out = new StringBuilder("");
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ct++;
                if (ct > 1) {
                    out.append('(');
                }
            }
            if (c == ')') {
                if (ct > 1) {
                    out.append(')');
                }
                ct--;
            }
        }
        return out.toString();
    }
}
