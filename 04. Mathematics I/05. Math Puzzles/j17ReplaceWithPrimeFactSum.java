/*-
 * Problem Statement:
 * 
 *     You are given an integer `n`. The task is to repeatedly replace `n` with the sum of its prime 
 *     factors (including repeated factors) until `n` becomes a prime number or the sum of prime factors 
 *     no longer changes. The goal is to return the final value of `n` once it reaches a stable state.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5).
 * 
 * Output:
 *     - An integer representing the final value of `n` after repeatedly replacing it with the sum of its prime factors.
 * 
 * Example:
 *     Input:
 *     12
 *     Output:
 *     5
 * 
 *     Explanation:
 *     - Step 1: The prime factors of 12 are 2, 2, and 3. The sum is 2 + 2 + 3 = 7.
 *     - Step 2: The prime factors of 7 are 7 itself. The sum is 7.
 *     - The final value is 7.
 */

import java.util.Scanner;

public class j17ReplaceWithPrimeFactSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(); // Input: the integer num
        System.out.println(smallestValue(num)); // Output the result of the smallestValue method
        in.close();
    }

    /*-
     * Approach: Repeated Prime Factorization and Summing
     * 
     * Intuition:
     * - The main idea is to repeatedly find the prime factors of the current number and replace the number
     *   with the sum of its prime factors.
     * - This process continues until the number remains unchanged or becomes a prime number.
     * 
     * Time Complexity:
     * - O(sqrt(n) * log(n)), where n is the current number. For each iteration, we factor the number using
     *   trial division up to sqrt(n), and the number of iterations is logarithmic in nature.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space.
     * 
     * @param n The input number.
     * @return The final value after replacing the number with the sum of its prime factors.
     */
    public static int smallestValue(int n) {
        int ans = n; // Initialize the answer as the input number
        while (true) {
            int temp = reduce(ans); // Get the sum of prime factors of the current number
            if (temp == ans) // If the sum doesn't change, we have reached a stable state
                return ans;
            else
                ans = temp; // Update the number to the new sum
        }
    }

    /*-
     * Helper Method: reduce
     * 
     * Intuition:
     * - This method computes the sum of prime factors of a number, including repeated factors.
     * - We iterate from 2 to sqrt(n) and divide out each prime factor while adding it to the sum.
     * 
     * Time Complexity:
     * - O(sqrt(n)), as we check all potential divisors from 2 to sqrt(n) to find the prime factors.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space.
     * 
     * @param n The input number to be reduced by summing its prime factors.
     * @return The sum of prime factors of n.
     */
    public static int reduce(int n) {
        int s = 0; // Initialize the sum of prime factors
        // Check for each number from 2 to sqrt(n) if it's a divisor
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) { // If i is a divisor
                while (n % i == 0) { // Divide n by i repeatedly
                    s += i; // Add i to the sum of factors
                    n /= i; // Update n by dividing by i
                }
            }
        }
        if (n > 1) { // If n is greater than 1, it must be prime
            s += n; // Add n to the sum of prime factors
        }
        return s; // Return the sum of prime factors
    }
}
