
/**
 * Problem Statement:
 * 
 *     Given a string representing a non-negative integer, determine if the number is divisible by 9.
 * 
 * Input:
 *     - A string `n` representing a non-negative integer.
 * 
 * Output:
 *     - Return `true` if the number is divisible by 9, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     81
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

public class j06DivisibleBy9 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the input number as a string
        String n = in.next();

        // Checking if the number represented by the string is divisible by 9
        System.out.println(isDivisibleBy9(n));

        in.close();
    }

    /**
     * Intuition:
     * - A number is divisible by 9 if the sum of its digits is divisible by 9.
     * - Therefore, we calculate the sum of the digits of the number and check if
     * the sum is divisible by 9.
     */

    /**
     * Method to check if the sum of digits of the number is divisible by 9.
     * 
     * Time Complexity: O(n), where n is the number of digits in the input string.
     * Space Complexity: O(1), no additional space used except for integer
     * variables.
     * 
     * @param str The string representing the number.
     * @return true if the sum of digits is divisible by 9, otherwise false.
     */
    public static boolean isDivisibleBy9(String str) {
        int n = str.length();
        int digitSum = 0;

        // Looping through the string and adding each digit to the digitSum
        for (int i = 0; i < n; i++) {
            digitSum += (str.charAt(i) - '0'); // Convert character to integer and add it to the sum
        }

        // If the sum of digits is divisible by 9, return true, else false
        return (digitSum % 9 == 0);
    }
}
