/** 
 * Problem Statement:
 * 
 *     The inverse of a number is obtained by reversing the positions of its digits. 
 *     For example, the inverse of 12345 is  54321. The task is to find the inverse 
 *     of a given number where each digit is placed at the position equal to its value.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6) representing the number.
 * 
 * Output:
 *     - The inverse of the number `n`.
 * 
 * Example:
 *     Input:
 *     321
 *     Output:
 *     132
 * 
 *     Explanation:
 *     - The inverse of 321 is 132, as the digit 3 is placed at the 3rd position, 
 *       the digit 2 is placed at the 2nd position, and the digit 1 at the 1st position.
 * 
 *     Input:
 *     2143
 *     Output:
 *     3412
 * 
 *     Explanation:
 *     - The inverse of 2143 is 3412, where the digit 2 is placed at the 2nd position, 1 at the 1st,
 *       4 at the 4th, and 3 at the 3rd.
 */

import java.util.Scanner;

public class j11InverseNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // Calling the method to find inverse and print the result
        System.out.println(findInverse(n));
        // Close the scanner
        in.close();
    }

    /** 
     * Approach: Reverse the digits of the number based on the position of each digit.
     * 
     * Intuition:
     * - For each digit in the number, place it at the position equal to its value (digit-1).
     * - This effectively 'inverses' the number by reversing the positions of its digits.
     * 
     * Time Complexity:
     * - O(log10(N)), where N is the given number. The time complexity is dependent on the number of digits in the input.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few integer variables for computation.
     * 
     * @param number The input number.
     * @return The inverse of the input number.
     */
    public static int findInverse(int number) {
        int inverse = 0;
        int position = 1;

        // Process each digit in the number
        while (number != 0) {
            int digit = number % 10; // Extract the last digit
            inverse += position * Math.pow(10, digit - 1); // Place the digit at the correct position
            number /= 10; // Remove the last digit
            position++; // Move to the next position
        }

        return inverse;
    }
}
