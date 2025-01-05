
/**
 * Problem Statement:
 * 
 *     Given a string representing a non-negative integer, determine if the number is divisible by 7.
 * 
 * Input:
 *     - A string `n` representing a non-negative integer.
 * 
 * Output:
 *     - Return `true` if the number is divisible by 7, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     371
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

public class j05DivisibleBy7 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the input number as a string
        String n = in.next();

        // Checking if the number represented by the string is divisible by 7
        System.out.println(isDivisible7Efficient(n));

        in.close();
    }

    /**
     * Intuition:
     * - Approach 1 (Naive): Use a recursive method based on the rule that a number
     * is divisible by 7 if
     * subtracting twice the last digit from the number formed by the remaining
     * digits results in a
     * number divisible by 7.
     * - Approach 2 (Simple modulo check): Convert the number into an integer and
     * check divisibility
     * using the modulo operator.
     * - Approach 3 (Efficient): Use modular arithmetic to check divisibility while
     * iterating through the
     * digits of the number without needing to convert it fully into an integer.
     */

    /**
     * Approach 1: Recursive Method (Naive)
     * 
     * Intuition:
     * - A number is divisible by 7 if it satisfies the rule: subtract twice the
     * last digit from the number
     * formed by the remaining digits. Repeat this process until a small number.
     * - If the result is divisible by 7, the original number is divisible by 7.
     * 
     * Time Complexity: O(log(n)), where n is the number represented by the string.
     * This is due to the recursive
     * process of reducing the number.
     * Space Complexity: O(log(n)) due to recursion stack.
     * 
     * @param num The number to check.
     * @return true if divisible by 7, false otherwise.
     */
    static boolean isDivisibleBy7(int num) {
        if (num < 0)
            return isDivisibleBy7(-num);
        if (num == 0 || num == 7)
            return true;
        if (num < 10)
            return false;
        return isDivisibleBy7(num / 10 - 2 * (num - num / 10 * 10));
    }

    /**
     * Approach 2: Simple Modulo Check (Less Efficient)
     * 
     * Intuition:
     * - Convert the number represented as a string into a long integer, then use
     * the modulo operation
     * to check divisibility by 7.
     * - This approach works for small numbers but can be inefficient for very large
     * numbers because of
     * the conversion to an integer.
     * 
     * Time Complexity: O(n), where n is the number of digits in the number, as
     * converting the string to
     * a number takes linear time.
     * Space Complexity: O(1), constant space used for storing the number.
     * 
     * @param numStr The string representing the number.
     * @return true if divisible by 7, false otherwise.
     */
    public static boolean isDivisibleBy7(String numStr) {
        long num = Long.parseLong(numStr);
        return num % 7 == 0;
    }

    /**
     * Approach 3: Efficient Modular Arithmetic (Most Efficient)
     * 
     * Intuition:
     * - Use modular arithmetic to check divisibility by 7 without converting the
     * whole number into an integer.
     * - While iterating through the digits, we maintain a running result, applying
     * the modulo operator to
     * prevent overflow and efficiently check divisibility.
     *
     * - Divisibility by 7 can be checked by a recursive method. A number of the
     * form 10a + b is divisible
     * by 7 if and only if a – 2b is divisible by 7. In other words, subtract twice
     * the last digit from
     * the number formed by the remaining digits. Continue to do this until a small
     * number.
     * Example: the number 371: 37 – (2×1) = 37 – 2 = 35; 3 – (2 × 5) = 3 – 10 = -7;
     * thus, since -7 is divisible by 7, 371 is divisible by 7.
     * 
     * Time Complexity: O(n), where n is the number of digits in the number. This is
     * because we iterate
     * through the string and apply modular arithmetic on each digit.
     * Space Complexity: O(1), constant space used for the result variable.
     * 
     * @param num The string representing the number.
     * @return true if divisible by 7, false otherwise.
     */
    public static boolean isDivisible7Efficient(String num) {
        int result = 0;

        // Loop through the string and compute the remainder modulo 7
        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';
            result = (result * 10 + digit) % 7;
        }

        // If the result is divisible by 7, return true
        return (result == 0);
    }
}
