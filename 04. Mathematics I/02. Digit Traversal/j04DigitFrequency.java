/**
 * Problem Statement:
 * 
 *     Given a number n and a digit d, determine how many times the digit d appears in the number n.
 * 
 * Input:
 *     - A long integer n (1 <= n <= 10^18), representing the number.
 *     - An integer d (0 <= d <= 9), representing the digit whose frequency in n is to be calculated.
 * 
 * Output:
 *     - An integer representing the frequency of digit d in the number n.
 * 
 * Example:
 *     Input:
 *     12345123 1
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The digit '1' appears twice in the number '12345123'.
 */

import java.util.Scanner;

public class j04DigitFrequency {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        long n = in.nextLong(); // Input: the number n
        int d = in.nextInt(); // Input: the digit d
        // Call the solution method and print the result
        System.out.print("Frequency of Digit " + d + " in " + n + " is " + digitFrequency(n, d));
        // Close the scanner
        in.close();
    }

    /**
     * Approach: Brute Force - Iterative Check
     * 
     * Intuition:
     * - This approach involves iterating through each digit of the number n by repeatedly dividing it by 10.
     * - We check if the current digit is equal to d, and if so, increment a counter.
     * - This process continues until all digits of n have been checked.
     * 
     * Time Complexity:
     * - O(log(n)), since we're iterating over each digit of n (the number of digits in n is log(n) in base 10).
     * 
     * Space Complexity:
     * - O(1), as we're using only a constant amount of space for variables like count and temp.
     * 
     * @param n The input number.
     * @param d The digit whose frequency is to be counted.
     * @return The frequency of the digit d in the number n.
     */
    public static int digitFrequency(long n, int d) {
        int count = 0; // Initialize count of digit frequency
        // Iterate through all digits of the number n
        while (n > 0) {
            int t = (int) (n % 10); // Get the last digit of n
            if (d == t) { // Check if the current digit matches d
                count++; // Increment count if there's a match
            }
            n = n / 10; // Remove the last digit from n
        }
        return count; // Return the final count
    }
}
