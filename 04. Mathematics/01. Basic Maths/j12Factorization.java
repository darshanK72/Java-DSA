/**
 * Problem Statement:
 * 
 *     Given a number `n`, find and print all the factors of `n`.
 *     The factors of a number are all the numbers that divide `n` without leaving a remainder.
 * 
 * Input:
 *     - An integer `n`.
 * 
 * Output:
 *     - A list of all factors of `n`.
 * 
 * Example:
 *     Input:
 *     12
 *     Output:
 *     Factors of 12 are [1, 2, 3, 4, 6, 12]
 * 
 * Constraints:
 *     - `n` is a positive integer.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j12Factorization {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Read the integer n

        // Displaying the factors of the number
        System.out.printf("Factors of %d are", n);
        factors(n);
        factorsEfficient1(n); // Call efficient method to find factors

        in.close();
    }

    /**
     * Approach 1: Brute Force Method to find Factors
     * 
     * Intuition:
     * - The brute force method checks all numbers from 1 to `n` to see if they
     * divide `n` without a remainder.
     * - It collects all such numbers that are factors and prints them.
     * - This approach is simple but inefficient for large `n` as it checks all
     * numbers from 1 to `n`.
     * 
     * Time Complexity: O(n), because we loop through all numbers from 1 to `n` to
     * check if they are factors.
     * Space Complexity: O(f), where `f` is the number of factors of `n`, as we
     * store the factors in an ArrayList.
     * 
     * @param n The integer whose factors are to be found.
     */
    public static void factors(int n) {
        // Initialize a list to store the factors
        ArrayList<Integer> factors = new ArrayList<Integer>();

        // Check for factors from 1 to n
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }

        // Print the factors
        System.out.print(factors.toString());
    }

    /**
     * Approach 2: Efficient Method to find Factors
     * 
     * Intuition:
     * - The efficient method leverages the property that factors come in pairs.
     * - We iterate from 1 to sqrt(n), and for each divisor `i` of `n`, both `i` and
     * `n / i` are factors.
     * - By iterating only up to sqrt(n), we reduce the number of checks and improve
     * efficiency.
     * 
     * Time Complexity: O(sqrt(n)), because we only need to check divisors up to the
     * square root of `n`.
     * Space Complexity: O(f), where `f` is the number of factors of `n`, as we
     * store the factors in an ArrayList.
     * 
     * @param n The integer whose factors are to be found.
     */
    public static void factorsEfficient1(int n) {
        // Initialize a list to store the factors
        ArrayList<Integer> factors = new ArrayList<Integer>();

        // Always include 1 and n as factors
        int left = 1;
        int right = 1;
        factors.add(1);
        factors.add(n);

        // Check for factors from 2 to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i == i) { // If i equals n / i, it's a perfect square
                    factors.add(left, i);
                    left++;
                } else { // Otherwise, add both i and n / i as factors
                    factors.add(left, i);
                    left++;
                    right++;
                    factors.add(factors.size() - (right - 1), n / i);
                }
            }
        }

        // Print the factors
        System.out.print(factors.toString());
    }

    public static void factorsEfficient2(int n) {
        // Initialize a list to store the factors
        ArrayList<Integer> factors = new ArrayList<Integer>();

        // First loop to collect factors from 1 to sqrt(n)
        int i;
        for (i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i); // If i is a factor, add it to the list
            }
        }

        // Second loop to add the corresponding pair factors n / i
        for (; i > 0; i--) {
            if (n % i == 0) {
                factors.add(n / i); // Add the corresponding pair factor
            }
        }

        // Print the factors
        System.out.print(factors.toString());
    }
}
