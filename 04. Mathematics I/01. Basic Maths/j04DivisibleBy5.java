
/**
 * Problem Statement:
 * 
 *     Given a string representing a non-negative integer, determine if the number is divisible by 5.
 *     A number is divisible by 5 if its last digit is either 0 or 5.
 * 
 * Input:
 *     - A string `n` representing a non-negative integer.
 * 
 * Output:
 *     - Return `true` if the number is divisible by 5, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     125
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

public class j04DivisibleBy5 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the input number as a string
        String n = in.next();

        // Checking if the number represented by the string is divisible by 5
        System.out.println(isDivisibleBy5(n));

        in.close();
    }

    /**
     * Intuition:
     * - A number is divisible by 5 if its last digit is either 0 or 5.
     * - We simply check the last digit of the number and return true if it's 0 or
     * 5.
     * 
     * Time Complexity: O(1), as we only check the last digit of the number.
     * Space Complexity: O(1), no additional space used except for integer
     * variables.
     * 
     * @param str The string representing the number.
     * @return true if the number's last digit is either 0 or 5, otherwise false.
     */
    public static boolean isDivisibleBy5(String str) {
        int n = str.length();

        // Extract the last digit of the string and check if it's 0 or 5
        int last = str.charAt(n - 1) - '0';
        return last == 0 || last == 5;
    }
}
