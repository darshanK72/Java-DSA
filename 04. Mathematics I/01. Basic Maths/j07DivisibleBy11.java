
/**
 * Problem Statement:
 * 
 *     Given a string representing a non-negative integer, determine if the number is divisible by 11.
 * 
 * Input:
 *     - A string `n` representing a non-negative integer.
 * 
 * Output:
 *     - Return `true` if the number is divisible by 11, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     121
 *     Output:
 *     true
 * 
 *     Input:
 *     123
 *     Output:
 *     false
 * 
 * Constraints:
 *     - The input string `n` consists only of digits (0-9).
 */

import java.util.Scanner;

public class j07DivisibleBy11 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the input number as a string
        String n = in.next();

        // Checking if the number represented by the string is divisible by 11
        System.out.println(isDivisibleBy11(n));

        in.close();
    }

    /**
     * Intuition:
     * - A number is divisible by 11 if the difference between the sum of digits at
     * odd positions
     * and the sum of digits at even positions is divisible by 11.
     * - For example, for number 121: (1 + 1) - (2) = 2 - 2 = 0, which is divisible
     * by 11.
     * 
     * Approach:
     * - Loop through the digits, maintaining two sums: one for digits at odd
     * positions and one for even positions.
     * - Calculate the difference between these two sums and check if the result is
     * divisible by 11.
     */

    /**
     * Method to check if the number is divisible by 11 based on the sum of digits
     * at odd and even positions.
     * 
     * Time Complexity: O(n), where n is the number of digits in the input string.
     * Space Complexity: O(1), no additional space used except for integer
     * variables.
     * 
     * @param str The string representing the number.
     * @return true if the number is divisible by 11, otherwise false.
     */
    public static boolean isDivisibleBy11(String str) {
        int n = str.length();
        int oddDigSum = 0, evenDigSum = 0;

        // Looping through the string and calculating the sum of digits at odd and even
        // positions
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                oddDigSum += (str.charAt(i) - '0'); // Odd position (0-based indexing)
            } else {
                evenDigSum += (str.charAt(i) - '0'); // Even position (0-based indexing)
            }
        }

        // If the difference between odd and even digit sums is divisible by 11, return
        // true, else false
        return ((oddDigSum - evenDigSum) % 11 == 0);
    }
}
