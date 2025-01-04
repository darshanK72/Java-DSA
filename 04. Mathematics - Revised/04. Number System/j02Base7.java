/**
 * Problem Statement:
 * 
 *     Given an integer `n`, convert it to a base 7 representation and return the result as a string.
 * 
 * Input:
 *     - An integer `n` (-10^7 <= n <= 10^7) representing the number to be converted.
 * 
 * Output:
 *     - A string representing the number `n` in base 7.
 * 
 * Example:
 *     Input:
 *     100
 *     Output:
 *     202
 * 
 *     Explanation:
 *     The number 100 in base 7 is represented as "202".
 */

import java.util.Scanner;

public class j02Base7 {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to be converted

        // Print the result of both methods
        System.out.println(convertToBase7(n)); // Output using custom method
        System.out.println(Integer.toString(n, 7)); // Output using built-in Java method

        in.close(); // Close the scanner to release resources
    }

    /**
     * Approach: Convert Decimal to Base 7 (Custom Implementation)
     * 
     * Intuition:
     * - This approach converts the decimal number to base 7 by repeatedly dividing the number by 7 and collecting the remainders.
     * - We handle negative numbers by checking if the input is negative, and adjust the output string accordingly.
     * 
     * Time Complexity:
     * - O(log n) since each division reduces the number by a factor of 7.
     * 
     * Space Complexity:
     * - O(log n) due to the space needed to store the result string.
     * 
     * @param num The decimal number to convert.
     * @return The number in base 7 as a string.
     */
    public static String convertToBase7(int num) {
        if (num == 0)
            return "0"; // Special case for 0
        boolean flag = num < 0 ? true : false; // Check if the number is negative
        num = Math.abs(num); // Take the absolute value for the conversion
        String out = ""; // Initialize the result string
        while (num > 0) { // Repeat until the number becomes 0
            out = (char) ('0' + num % 7) + out; // Add the remainder (base 7 digit) to the result string
            num /= 7; // Divide the number by 7
        }
        return flag ? "-" + out : out; // Return the result, adding a negative sign if necessary
    }
}
