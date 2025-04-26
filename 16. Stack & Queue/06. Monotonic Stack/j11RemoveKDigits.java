/*-
 * Problem Statement:
 *     LeetCode 402. Remove K Digits
 * 
 *     Given string num representing a non-negative integer num, and an integer k,
 *     return the smallest possible integer after removing k digits from num.
 * 
 * Input:
 *     - String num containing digits (1 <= num.length <= 10^5)
 *     - Integer k where 0 <= k <= num.length
 *     - num consists of only digits
 *     - num does not have any leading zeros
 * 
 * Output:
 *     - String representing smallest possible number after removing k digits
 * 
 * Example 1:
 *     Input: num = "1432219", k = 3
 *     Output: "1219"
 *     
 *     Explanation:
 *     Remove digit 4 to get "132219"
 *     Remove digit 3 to get "12219"
 *     Remove digit 2 to get "1219"
 */

public class j11RemoveKDigits {

    public static void main(String[] args) {
        // Test cases
        String[][] testCases = {
            {"1432219", "3"},      // Basic case
            {"10200", "1"},        // Leading zeros
            {"10", "2"},           // Remove all digits
            {"112", "1"},          // Remove from middle
            {"1234567890", "9"},   // Large removal
            {"9", "1"}             // Single digit
        };

        for (String[] test : testCases) {
            String num = test[0];
            int k = Integer.parseInt(test[1]);
            System.out.println("Input: num = \"" + num + "\", k = " + k);
            System.out.println("Output: \"" + removeKdigits(num, k) + "\"");
            System.out.println();
        }
    }

    /*-
     * Approach: Using Monotonic Stack (StringBuilder)
     * 
     * Intuition:
     * - To get smallest number, we want to remove larger digits from left
     * - When we find a smaller digit, remove larger digits before it
     * - Use StringBuilder as stack to maintain remaining digits
     * - Handle leading zeros and empty result cases
     * 
     * Time Complexity: O(n)
     * - Single pass through string: O(n)
     * - Each digit pushed/popped at most once
     * - Leading zero removal: O(n)
     * 
     * Space Complexity: O(n)
     * - StringBuilder stores at most n digits
     */
    public static String removeKdigits(String num, int k) {
        // Use StringBuilder as stack
        StringBuilder out = new StringBuilder();
        
        // Process each digit
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // Remove larger digits when we find smaller one
            while (k > 0 && out.length() != 0 && 
                   out.charAt(out.length() - 1) > c) {
                out.deleteCharAt(out.length() - 1);
                k--;
            }
            out.append(c);
        }

        // Remove remaining k digits from end
        while (k-- > 0 && out.length() > 0) {
            out.deleteCharAt(out.length() - 1);
        }

        // Remove leading zeros
        int i = 0;
        while (i < out.length() && out.charAt(i) == '0') {
            out.deleteCharAt(i);
        }

        // Handle empty string case
        return out.length() == 0 ? "0" : out.toString();
    }
}