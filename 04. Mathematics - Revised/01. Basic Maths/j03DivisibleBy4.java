
/**
 * Problem Statement:
 * 
 *     Given a string representing a non-negative integer, determine if the number is divisible by 4.
 * 
 * Input:
 *     - A string `n` representing a non-negative integer.
 * 
 * Output:
 *     - Return `true` if the number is divisible by 4, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     1234
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
 * 
 */

import java.util.Scanner;

public class j03DivisibleBy4 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the input number as a string
        String n = in.next();

        // Checking if the number represented by the string is divisible by 4
        System.out.println(isDivisibleBy4(n));

        in.close();
    }

    /**
     * Intuition:
     * - A number is divisible by 4 if the number formed by its last two digits is
     * divisible by 4.
     * - Therefore, we extract the last two digits and check if they form a number
     * divisible by 4.
     * 
     * Time Complexity: O(1), as we're only considering the last two digits of the
     * number.
     * Space Complexity: O(1), no additional space used except for integer
     * variables.
     * 
     * @param str The string representing the number.
     * @return true if the number formed by the last two digits is divisible by 4,
     *         otherwise false.
     */
    public static boolean isDivisibleBy4(String str) {
        int n = str.length();

        // If the string is empty, it's not divisible by 4
        if (n == 0)
            return false;

        // If the string has only one digit, check if it is divisible by 4
        if (n == 1)
            return ((str.charAt(0) - '0') % 4 == 0);

        // Extract the last two digits
        int last = str.charAt(n - 1) - '0';
        int second_last = str.charAt(n - 2) - '0';

        // Check if the number formed by the last two digits is divisible by 4
        return ((second_last * 10 + last) % 4 == 0);
    }
}
