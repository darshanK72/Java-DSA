/**
 * Problem Statement:
 * 
 *     Given a string `s` consisting of '(' and ')' characters, return the minimum number of parentheses we must add to make the string valid.
 *     A parentheses string is valid if:
 *         1. Every opening parenthesis '(' has a corresponding closing parenthesis ')'.
 *         2. The closing parentheses appear after the corresponding opening parentheses.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 10^5) consisting of only '(' and ')'.
 * 
 * Output:
 *     - The minimum number of parentheses that must be added to make the string valid.
 * 
 * Example:
 *     Input:
 *     "()))(())(()(()))"
 *     Output:
 *     4
 * 
 *     Explanation:
 *     - We need to add 4 parentheses to make the string valid.
 *     - The steps can be:
 *         1. Add '(' at the beginning to balance the first ')'.
 *         2. Add ')' at the end to balance the last '('.
 *         3. Add ')' after the second '('.
 *         4. Add ')' to balance the last '('.
 */

public class j10MakeValidParenthesis {
    public static void main(String args[]) {
        // Reading and outputting the result
        System.out.println(minAddToMakeValid("()))(())(()(()))"));
    }

    /**
     * Approach: Greedy Counting with Balance Tracking
     * 
     * Intuition:
     * - The core idea is to traverse the string while maintaining a balance of the parentheses:
     *     - `c` represents the count of unmatched opening parentheses `(`.
     *     - `t` represents the count of unmatched closing parentheses `)`.
     * - We iterate over the string:
     *     - When encountering an opening parenthesis `(`, we increment `c`.
     *     - When encountering a closing parenthesis `)`, we decrement `c`.
     *     - If `c` becomes negative (indicating an unmatched closing parenthesis `)`), we increment `t` (representing an unmatched `)` that needs an opening `(` to balance it) and reset `c` to 0 (since we now treat this unmatched `)` as balanced).
     * - After processing the string, if `c > 0`, it means there are unmatched opening parentheses, and we need to add `c` closing parentheses.
     * - The result is the sum of the unmatched opening parentheses `c` and the unmatched closing parentheses `t`.
     * 
     * Time Complexity:
     * - O(n) (since we only loop through the string once).
     * 
     * Space Complexity:
     * - O(1) (since we only use a few variables to track the count).
     * 
     * @param s The input string containing parentheses.
     * @return The minimum number of parentheses to add to make the string valid.
     */
    public static int minAddToMakeValid(String s) {
        int c = 0; // Count of unmatched opening parentheses '('
        int t = 0; // Count of unmatched closing parentheses ')'

        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                c++; // Increase count for unmatched opening parentheses
            } else {
                c--; // Decrease count for unmatched opening parentheses
            }
            // If the count goes negative, we have more closing parentheses
            if (c < 0) {
                t++; // This unmatched closing parenthesis needs an opening parenthesis
                c++; // Reset count of unmatched opening parentheses
            }
        }

        // The total parentheses to add is the sum of unmatched opening and closing
        // parentheses
        return c + Math.abs(t);
    }
}
