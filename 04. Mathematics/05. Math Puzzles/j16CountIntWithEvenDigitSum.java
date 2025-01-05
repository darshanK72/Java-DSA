/**
 * Problem Statement:
 * 
 *     You are given an integer `num`. Your task is to count how many numbers from 1 to `num` have an even sum of digits. 
 *     Specifically, for each number in the range from 1 to `num`, you need to calculate the sum of its digits. If the sum is even, increment a counter.
 * 
 * Input:
 *     - An integer `num` (1 <= num <= 10^5).
 * 
 * Output:
 *     - An integer representing the count of numbers from 1 to `num` that have an even sum of digits.
 * 
 * Example:
 *     Input:
 *     10
 *     Output:
 *     5
 * 
 *     Explanation:
 *     - The numbers from 1 to 10 and their digit sums:
 *       - 1: sum = 1 (odd)
 *       - 2: sum = 2 (even)
 *       - 3: sum = 3 (odd)
 *       - 4: sum = 4 (even)
 *       - 5: sum = 5 (odd)
 *       - 6: sum = 6 (even)
 *       - 7: sum = 7 (odd)
 *       - 8: sum = 8 (even)
 *       - 9: sum = 9 (odd)
 *       - 10: sum = 1 (odd)
 *     - There are 5 numbers (2, 4, 6, 8, 10) with an even sum of digits.
 */

import java.util.Scanner;

public class j16CountIntWithEvenDigitSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt(); // Input: the integer num
        System.out.println(countEven(num1)); // Output the result of the countEven method
        in.close();
    }

    /**
     * Approach: Iterative Digit Sum Calculation
     * 
     * Intuition:
     * - For each number from 1 to num, we calculate the sum of its digits.
     * - To calculate the sum of digits, we extract each digit using modulo and integer division.
     * - After calculating the sum of digits for a number, we check if the sum is even.
     * - If it is even, we increment a counter.
     * 
     * Time Complexity:
     * - O(num * d), where num is the input number, and d is the average number of digits in the numbers from 1 to num. 
     *   The digit extraction operation takes O(d) time for each number.
     * - Since d is logarithmic in the size of the number, this can be considered approximately O(num * log(num)).
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space.
     * 
     * @param num The upper limit of numbers to check.
     * @return The count of numbers from 1 to num with an even sum of digits.
     */
    public static int countEven(int num) {
        int count = 0; // Initialize the count of numbers with an even digit sum
        // Loop through all numbers from 2 to num
        for (int i = 2; i <= num; i++) {
            int x = i; // Current number
            int s = 0; // Initialize sum of digits
            // Calculate sum of digits for the current number
            while (x > 0) {
                s += x % 10; // Add the last digit of x to sum
                x /= 10; // Remove the last digit from x
            }
            // If the sum of digits is even, increment the count
            if (s % 2 == 1)
                count++;
        }
        return count; // Return the total count
    }
}
