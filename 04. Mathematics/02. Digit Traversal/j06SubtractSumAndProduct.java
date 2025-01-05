/**
 * Problem Statement:
 * 
 *     Given a number n, calculate the difference between the product and the sum of its digits.
 *     Specifically, for a given number n, return the value of: product_of_digits(n) - sum_of_digits(n)
 * 
 * Input:
 *     - An integer n (1 <= n <= 10^5), representing the number whose digits are to be used for calculations.
 * 
 * Output:
 *     - An integer representing the result of product_of_digits(n) - sum_of_digits(n).
 * 
 * Example:
 *     Input:
 *     234
 *     Output:
 *     15
 * 
 *     Explanation:
 *     For the number 234, the sum of its digits is 2 + 3 + 4 = 9, and the product of its digits is 2 * 3 * 4 = 24.
 *     The result is 24 - 9 = 15.
 */

import java.util.Scanner;

public class j06SubtractSumAndProduct {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number n
        // Call the solution method and print the result
        System.out.println(subtractProductAndSum(n));
        // Close the scanner
        in.close();
    }

    /**
     * Approach: Iterative Calculation of Product and Sum of Digits
     * 
     * Intuition:
     * - We initialize two variables: `sum` to 0 and `product` to 1.
     * - We iterate over each digit of the number n by using modulo and integer division.
     * - For each digit, we add it to `sum` and multiply it with `product`.
     * - Once all digits are processed, we return the result of `product - sum`.
     * 
     * Time Complexity:
     * - O(d), where d is the number of digits in the number n. We process each digit of the number once.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space to store the variables `sum` and `product`.
     * 
     * @param n The input number.
     * @return The result of product_of_digits(n) - sum_of_digits(n).
     */
    public static int subtractProductAndSum(int n) {
        int sum = 0; // Initialize sum of digits
        int product = 1; // Initialize product of digits
        // Process each digit of n
        while (n > 0) {
            sum += n % 10; // Add the last digit to sum
            product *= (n % 10); // Multiply the last digit with product
            n /= 10; // Remove the last digit
        }
        // Return the difference between product and sum
        return product - sum;
    }

    /**
     * Alternative Approach: Recursive Calculation of Sum of Digits (Optional)
     * 
     * Intuition:
     * - This method calculates the sum of digits of the number recursively. For
     * each step, the last digit is added to the sum of digits
     * calculated for the remaining number (n / 10).
     * - This can be used as an alternative method to calculate the sum of digits if
     * needed.
     * 
     * Time Complexity:
     * - O(d), where d is the number of digits in n. We recursively process each
     * digit.
     * 
     * Space Complexity:
     * - O(d), as recursion uses space on the call stack for each recursive call
     * (depth of the recursion is equal to the number of digits).
     * 
     * @param n The input number.
     * @return The sum of digits of n.
     */
    public static int sumOfDigits(int n) {
        if (n <= 0)
            return 0;
        return sumOfDigits(n / 10) + n % 10;
    }
}
