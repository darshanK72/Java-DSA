/**
 * Problem Statement:
 * 
 *     Implement a function that converts a string to an integer. The function should handle the following:
 *     - Leading and trailing whitespaces should be ignored.
 *     - A plus or minus sign can precede the number to indicate whether it is positive or negative.
 *     - If the string is not a valid integer or overflows, return the appropriate limits (`Integer.MAX_VALUE` or `Integer.MIN_VALUE`).
 *     - The function should stop processing when it encounters a non-digit character.
 * 
 * Input:
 *     - A string `s` which may represent an integer, with possible leading/trailing whitespaces, and/or a plus or minus sign.
 * 
 * Output:
 *     - The integer representation of the string if it is a valid number, or the boundary values (`Integer.MAX_VALUE` or `Integer.MIN_VALUE`) if the number is out of the valid integer range.
 * 
 * Example:
 *     Input:
 *     "42"
 *     Output:
 *     42
 * 
 *     Input:
 *     "   -42"
 *     Output:
 *     -42
 * 
 *     Input:
 *     "4193 with words"
 *     Output:
 *     4193
 * 
 *     Input:
 *     "words and 987"
 *     Output:
 *     0
 */

import java.util.Scanner;

public class j06StringToInteger {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: a string that potentially represents an integer
        // Calling the atoi method and printing the result
        System.out.println(atoi(s));
        in.close();
    }

    /**
     * Approach: String to Integer Conversion (atoi)
     * 
     * Intuition:
     * - The main idea is to process the string character by character.
     * - First, we trim the string of leading whitespaces, then check for an optional sign ('+' or '-') to indicate the number's sign.
     * - After this, we extract the numeric characters, ignoring any non-numeric ones.
     * - We handle potential integer overflow by checking whether the result exceeds the range of valid integers.
     * 
     * Time Complexity:
     * - O(s.length()), where `s` is the input string. We iterate through the string once to process its characters.
     * 
     * Space Complexity:
     * - O(1), since we are using a constant amount of extra space for the integer result.
     * 
     * @param s The input string that needs to be converted to an integer.
     * @return The integer value parsed from the string, or Integer.MAX_VALUE/Integer.MIN_VALUE if the number overflows.
     */
    public static int atoi(String s) {
        if (s.length() == 0)
            return 0; // Return 0 if the string is empty
        int i = 0;
        boolean sign = false;

        // Skip leading whitespaces
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        if (i == s.length())
            return 0; // If only whitespace exists, return 0

        // Check for the sign of the number
        if (s.charAt(i) == '+') {
            i++;
        } else if (s.charAt(i) == '-') {
            i++;
            sign = true;
        }

        // Skip leading zeros
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }

        if (i == s.length())
            return 0; // If only zeros exist, return 0

        int j = i;

        // Find the digits of the number
        while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
            j++;
        }

        int ans = 0;
        // Process the digits and convert to integer
        for (int k = i; k < j; k++) {
            int temp = ans * 10 + (s.charAt(k) - '0');
            // Handle overflow by checking if the new number is larger than the current
            // result
            if (temp / 10 != ans) {
                if (sign)
                    return Integer.MIN_VALUE; // Return minimum value for overflow in negative numbers
                return Integer.MAX_VALUE; // Return maximum value for overflow in positive numbers
            } else {
                ans = temp; // Update the result
            }
        }

        // Return the final result, applying the sign
        return sign ? -1 * ans : ans;
    }
}
