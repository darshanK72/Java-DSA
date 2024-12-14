/**
 * Problem Statement:
 * 
 *     Given an integer `n`, reverse its digits and return the resulting number.
 *     The solution should handle both positive and negative integers, as well as overflow conditions.
 * 
 * Input:
 *     - An integer n (−2^31 <= n <= 2^31 − 1), where `n` can be any 32-bit signed integer.
 * 
 * Output:
 *     - An integer representing the reversed digits of `n`.
 *     - If the reversed number overflows, return 0.
 * 
 * Example:
 *     Input:
 *     123
 *     Output:
 *     321
 * 
 *     Explanation:
 *     The number 123 reversed becomes 321.
 */

import java.util.Scanner;

// Complexity: O(log10 N)

public class j07ReverseNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input number n
        // Call the solution methods and print the results
        System.out.println(reverseNive(n));
        System.out.println(reverseForNegatives(n));
        System.out.println(reverseForNegativesEfficient(n));
        System.out.println(reverseForNegativesMoreEfficient(n));
        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Reverse Number (For Positive Numbers Only)
     * 
     * Intuition:
     * - This method works for positive numbers only. We reverse the digits of the number
     *   by extracting each digit (using modulo 10) and constructing the reversed number incrementally.
     * 
     * Time Complexity:
     * - O(log10 N), because we iterate through each digit of the number.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space to store the result and intermediate values.
     * 
     * @param n The input number.
     * @return The reversed number.
     */
    public static int reverseNive(int n) {
        int r = 0; // To store the reversed number
        while (n > 0) {
            r = r * 10 + n % 10; // Append the last digit to the reversed number
            n /= 10; // Remove the last digit from n
        }
        return r; // Return the reversed number
    }

    /**
     * Approach 2: Reverse Number (Works for Both Positive and Negative Numbers, But Cannot Handle Overflow)
     * 
     * Intuition:
     * - This method works for both positive and negative numbers. The logic is the same as Approach 1, 
     *   but with support for negative numbers. It handles the negative sign by retaining it while
     *   reversing the digits. However, this method does not check for overflow conditions.
     *  
     * 
     * Time Complexity:
     * - O(log10 N), as we process each digit of the number.
     * 
     * Space Complexity:
     * - O(1), constant space for storing the result.
     * 
     * @param n The input number.
     * @return The reversed number.
     */
    public static int reverseForNegatives(int n) {
        int r = 0; // To store the reversed number
        while (n != 0) {
            r = r * 10 + n % 10; // Append the last digit to the reversed number
            n /= 10; // Remove the last digit from n
        }
        return r; // Return the reversed number
    }

    /**
     * Approach 3: Reverse Number (Handles Overflow for Positive and Negative Numbers)
     * 
     * Intuition:
     * - This method is similar to Approach 2 but adds a check to handle overflow.
     *   If the reversed number exceeds the bounds of a 32-bit signed integer, it returns 0.
     * 
     * Time Complexity:
     * - O(log10 N), as we iterate through the digits of the number.
     * 
     * Space Complexity:
     * - O(1), constant space for storing the result and intermediate values.
     * 
     * @param n The input number.
     * @return The reversed number or 0 if overflow occurs.
     */
    public static int reverseForNegativesEfficient(int n) {
        int r = 0; // To store the reversed number
        while (n != 0) {
            // Check for overflow
            if (r > Integer.MAX_VALUE / 10 || r < Integer.MIN_VALUE / 10) {
                return 0; // Return 0 if the number overflows
            }
            r = r * 10 + n % 10; // Append the last digit to the reversed number
            n /= 10; // Remove the last digit from n
        }
        return r; // Return the reversed number
    }

    /**
     * Approach 4: Reverse Number (More Efficient Overflow Check)
     * 
     * Intuition:
     * - This method is even more efficient at detecting overflow by directly checking after each calculation whether the number exceeds 
     *   the 32-bit signed integer range. If it does, we return 0.
     * 
     * Time Complexity:
     * - O(log10 N), as we process each digit of the number.
     * 
     * Space Complexity:
     * - O(1), constant space for storing the result.
     * 
     * @param n The input number.
     * @return The reversed number or 0 if overflow occurs.
     */
    public static int reverseForNegativesMoreEfficient(int n) {
        int r = 0; // To store the reversed number
        while (n != 0) {
            int d = n % 10; // Get the last digit of n
            int rn = r * 10 + d; // Calculate the potential new reversed number
            // Check if the reversed number overflows
            if (rn / 10 != r) {
                return 0; // Return 0 if overflow occurs
            }
            r = rn; // Update r with the new reversed number
            n /= 10; // Remove the last digit from n
        }
        return r; // Return the reversed number
    }

    /**
     * Approach 5: Reverse Number Using Recursion (Optional)
     * 
     * Intuition:
     * - This method uses recursion to reverse the digits of the number.
     *   The base case checks if the last digit is the same as the number itself (single digit).
     *   Otherwise, it computes the last digit and uses recursion for the remaining number.
     * 
     * Time Complexity:
     * - O(log10 N), as each recursive call processes one digit.
     * 
     * Space Complexity:
     * - O(log10 N), due to recursion stack depth.
     * 
     * @param n The input number.
     * @return The reversed number.
     */
    public static int reverseNum(int n) {
        if (n % 10 == n)
            return n; // Base case: return when n has only one digit
        return (n % 10) * (int) Math.pow(10, (int) (Math.log10(n / 10)) + 1) + reverseNum(n / 10);
    }
}
