/**
 * Problem Statement:
 * 
 *     Given a number n, determine if the number is an Armstrong number or not.
 *     An Armstrong number (also known as a narcissistic number) is a number that is equal to the sum of its own digits, each raised to the power of the number of digits.
 *     For example:
 *     - 153 is an Armstrong number because: 1^3 + 5^3 + 3^3 = 153.
 * 
 * Input:
 *     - An integer n (1 <= n <= 10^9), representing the number to be checked.
 * 
 * Output:
 *     - A string "Yes" if n is an Armstrong number, and "No" otherwise.
 * 
 * Example:
 *     Input:
 *     153
 *     Output:
 *     Yes
 * 
 *     Explanation:
 *     The number 153 is an Armstrong number because: 1^3 + 5^3 + 3^3 = 153.
 */

import java.util.Scanner;

public class j05ArmstrongNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number n
        // Call the solution method and print the result
        System.out.println(armstrongNumber(n));
        // Close the scanner
        in.close();
    }

    /**
     * Approach: Brute Force - Checking Armstrong Property
     * 
     * Intuition:
     * - First, we find the number of digits in the number n. We then calculate the sum of each digit raised to the power of the number of digits.
     * - If the sum is equal to the original number, then it is an Armstrong number.
     * 
     * Time Complexity:
     * - O(d), where d is the number of digits in n. We need to iterate over each digit of n to calculate the sum of its cubes.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of extra space for variables like `ans` and `temp`.
     * 
     * @param n The input number.
     * @return "Yes" if n is an Armstrong number, "No" otherwise.
     */
    public static String armstrongNumber(int n) {
        int ans = 0; // Initialize the sum of cubes of digits
        int temp = n; // Store the original number to compare later
        // Iterate over each digit of the number n
        while (temp > 0) {
            int d = temp % 10; // Get the last digit
            temp /= 10; // Remove the last digit from temp
            ans += d * d * d; // Add the cube of the digit to the sum
        }
        // Check if the sum of cubes equals the original number
        if (ans == n) {
            return "Yes"; // If it matches, it's an Armstrong number
        }
        return "No"; // If it doesn't match, it's not an Armstrong number
    }
}
