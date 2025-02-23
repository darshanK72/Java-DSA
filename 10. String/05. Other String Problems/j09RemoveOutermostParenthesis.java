/**
 * Problem Statement:
 * 
 *     Given a string `s` representing a valid parentheses string, remove the outermost parentheses from every valid parentheses substring.
 *     A parentheses string is valid if every opening parenthesis '(' has a corresponding closing parenthesis ')'.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 10^5) consisting of only the characters '(' and ')'.
 * 
 * Output:
 *     - A string with the outermost parentheses removed from every valid parentheses substring.
 * 
 * Example:
 *     Input:
 *     "(()())(())(()(()))"
 *     Output:
 *     "()()()(())"
 * 
 *     Explanation:
 *     The result is the string obtained by removing the outermost parentheses of each valid parentheses substring:
 *     - From "(()())", remove the outermost parentheses to get "()".
 *     - From "(())", remove the outermost parentheses to get "()".
 *     - From "(()(()))", remove the outermost parentheses to get "()(())".
 */

public class j09RemoveOutermostParenthesis {
    public static void main(String args[]) {
        // Reading and outputting the result
        System.out.println(removeOuterParentheses("(()())(())(()(()))"));
    }

    /**
     * Approach: Stack-Based Parentheses Counting
     * 
     * Intuition:
     * - We need to remove the outermost parentheses from every valid substring. To achieve this, we can use a counter to track the depth of nested parentheses.
     * - As we iterate over the string, we count how many parentheses we encounter. When we encounter an opening parenthesis '(', we increase the count.
     * - When we encounter a closing parenthesis ')', we decrease the count.
     * - If the count is greater than 1 (meaning we're inside a valid substring), we append the character to the result. This effectively removes the outermost parentheses.
     * 
     * Time Complexity:
     * - O(n) (we only loop through the string once).
     * 
     * Space Complexity:
     * - O(n) (used to store the result string).
     * 
     * @param s The input string containing valid parentheses.
     * @return The string with the outermost parentheses removed.
     */
    public static String removeOuterParentheses(String s) {
        if (s.length() == 0)
            return ""; // Edge case for empty string

        StringBuilder out = new StringBuilder("");
        int count = 0; // This will track the depth of parentheses

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Only append if we are inside a valid parentheses substring (count > 0)
                if (count > 0) {
                    out.append("(");
                }
                count++; // Increase depth on encountering '('
            } else if (s.charAt(i) == ')') {
                count--; // Decrease depth on encountering ')'
                // Only append if we are inside a valid parentheses substring (count > 0)
                if (count > 0) {
                    out.append(")");
                }
            }
        }

        return out.toString(); // Return the final string with outermost parentheses removed
    }
}
