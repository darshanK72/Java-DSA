
/**
 * Problem Statement:
 * 
 *     Given a string representing a non-negative integer, determine if the number is divisible by 3.
 * 
 * Input:
 *     - A string `n` representing a non-negative integer.
 * 
 * Output:
 *     - Return `true` if the number is divisible by 3, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     123
 *     Output:
 *     true
 * 
 *     Input:
 *     124
 *     Output:
 *     false
 * 
 * Constraints:
 *     - The input string `n` consists only of digits (0-9).
 * 
 */

import java.util.Scanner;

public class j02DivisibleBy3 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the input number as a string
        String n = in.next();

        // Checking if the number represented by the string is divisible by 3
        System.out.println(isDivisibleBy3(n));

        in.close();
    }

    /**
     * Intuition:
     * - A number is divisible by 3 if the sum of its digits is divisible by 3.
     * - Therefore, we calculate the sum of the digits of the number and check if
     * the sum is divisible by 3
     */

    /**
     * Method to check if the sum of digits of the number is divisible by 3.
     * 
     * Time Complexity: O(n), where n is the number of digits in the input string.
     * Space Complexity: O(1), no additional space used except for integer
     * variables.
     * 
     * @param str The string representing the number.
     * @return true if the sum of digits is divisible by 3, otherwise false.
     */
    public static boolean isDivisibleBy3(String str) {
        int digitSum = 0;

        // Looping through the string and adding each digit to the digitSum
        for (int i = 0; i < str.length(); i++) {
            digitSum += (str.charAt(i) - '0'); // Convert character to integer and add it to the sum
        }

        // If the sum of digits is divisible by 3, return true, else false
        return (digitSum % 3 == 0);
    }
}
