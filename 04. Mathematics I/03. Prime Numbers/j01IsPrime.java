/*- 
 * Problem Statement:
 * 
 *     Determine whether a given number is a prime number.
 *     A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 * 
 * Input:
 *     - A single integer `n` (1 <= n <= 1,000,000).
 * 
 * Output:
 *     - Print whether the number is "Prime" or "Composite".
 * 
 * Example:
 *     Input:
 *     7
 *     Output:
 *     Prime
 * 
 *     Explanation:
 *     - 7 is a prime number because its only divisors are 1 and 7.
 * 
 *     Input:
 *     10
 *     Output:
 *     Composite
 * 
 *     Explanation:
 *     - 10 is not a prime number because it has divisors 1, 2, 5, and 10.
 */

import java.util.Scanner;

public class j01IsPrime {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        // Read the input number
        int n = in.nextInt();

        // Determine if the number is prime using the naive method and print the result
        System.out.println("Number " + n + " is " + (isPrimeNaive(n) ? "Prime" : "Composite"));

        // Close the scanner
        in.close();
    }

    /*- 
     * Approach: Naive method to check if a number is prime.
     * 
     * Intuition:
     * - Iterate from 2 to n-1 and check if the number is divisible by any number.
     * - If divisible by any number, it is not a prime number.
     * - If no divisors found, it is a prime number.
     * 
     * Time Complexity:
     * - O(n), where n is the input number.
     * 
     * Space Complexity:
     * - O(1), no extra space used.
     * 
     * @param number The input number to check.
     * @return True if the number is prime, otherwise false.
     */
    public static boolean isPrimeNaive(int number) {
        for (int i = 2; i < number; i++) { // Iterate from 2 to number-1
            if (number % i == 0) { // Check if divisible by any number
                return false; // Not a prime number
            }
        }
        return true; // Prime number
    }

    /*- 
     * Approach: Efficient method to check if a number is prime using square root optimization.
     * 
     * Intuition:
     * - Only check divisors up to the square root of the number.
     * - If the number is divisible by any divisor in this range, it is not a prime number.
     * 
     * Time Complexity:
     * - O(sqrt(n)), where n is the input number.
     * 
     * Space Complexity:
     * - O(1), no extra space used.
     * 
     * @param number The input number to check.
     * @return True if the number is prime, otherwise false.
     */
    public static boolean isPrimeEfficient(int number) {
        for (int i = 2; i * i <= number; i++) { // Iterate only up to the square root of the number
            if (number % i == 0) { // Check if divisible
                return false; // Not a prime number
            }
        }
        return true; // Prime number
    }

    /*- 
     * Approach: More efficient method to check primality using properties of primes.
     * 
     * Intuition:
     * - Every prime number greater than 3 can be expressed as 6n ± 1.
     * - First check if number is divisible by 2 or 3, then check divisors of the form 6n ± 1.
     * 
     * Time Complexity:
     * - O(sqrt(n)), where n is the input number.
     * 
     * Space Complexity:
     * - O(1), no extra space used.
     * 
     * @param number The input number to check.
     * @return True if the number is prime, otherwise false.
     */
    public static boolean isPrimeMoreEfficient(int number) {
        if (number == 1)
            return false; // 1 is not a prime number
        else if (number == 2 || number == 3)
            return true; // 2 and 3 are prime numbers
        else if (number % 2 == 0 || number % 3 == 0)
            return false; // Eliminate multiples of 2 and 3

        // Check divisors of the form 6k ± 1
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false; // Not a prime number
            }
        }
        return true; // Prime number
    }
}
