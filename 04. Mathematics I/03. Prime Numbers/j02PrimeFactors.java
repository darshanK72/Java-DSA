
/**
 * Problem Statement:
 *   Given an integer `n`, find the prime factors of `n`.
 *   A prime factor is a factor of a number that is a prime number.
 *   The output should include:
 *   - All prime factors of the number, including their multiplicities.
 *   - The unique prime factors of the number.
 *
 * Input:
 *   - An integer `n` (1 <= n <= 10^6).
 *
 * Output:
 *   - A list of all prime factors with multiplicity.
 *   - A list of distinct prime factors of the number.
 *
 * Example:
 *   Input:
 *       28
 *   Output:
 *       [2, 2, 7] (All prime factors with multiplicity)
 *       [2, 7] (Unique prime factors)
 *
 * Explanation:
 *   - The prime factors of 28 are 2 and 7. Since 28 = 2 * 2 * 7,
 *     the output includes [2, 2, 7] for all prime factors and [2, 7] for unique factors.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j02PrimeFactors {

    /**
     * Main method to handle input and output.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read the input number
        int n = in.nextInt();

        // Print all prime factors using the naive approach
        System.out.println(getAllPrimeFactorsNaive(n));

        // Print all prime factors using the efficient approach
        System.out.println(getAllPrimeFactorsEfficient(n));

        // Print the unique prime factors of the number
        System.out.println(getUniquePrimeFactors(n));

        in.close();
    }

    /**
     * Finds all prime factors of a number using a naive approach.
     *
     * @param number The input number.
     * @return A list of all prime factors, including multiplicity.
     */
    public static ArrayList<Integer> getAllPrimeFactorsNaive(int number) {
        ArrayList<Integer> out = new ArrayList<>();

        // Iterate through all numbers up to the input number
        for (int i = 2; i <= number; i++) {
            // Check if the number is prime
            if (isPrime(i)) {
                int x = i;

                // Add the prime factor multiple times based on divisibility
                while (number % x == 0) {
                    out.add(i);
                    x *= i;
                }
            }
        }

        return out;
    }

    /**
     * Checks if a number is prime.
     *
     * @param number The input number.
     * @return True if the number is prime, otherwise false.
     */
    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds all prime factors of a number using an efficient approach.
     *
     * @param number The input number.
     * @return A list of all prime factors, including multiplicity.
     */
    public static ArrayList<Integer> getAllPrimeFactorsEfficient(int number) {
        ArrayList<Integer> out = new ArrayList<>();

        // Handle edge case for numbers less than or equal to 1
        if (number <= 1)
            return out;

        // Check divisibility by each number up to the square root of the input
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                // Add the prime factor multiple times based on divisibility
                while (number % i == 0) {
                    out.add(i);
                    number /= i;
                }
            }
        }

        // If the remaining number is greater than 1, it is a prime factor
        if (number > 1)
            out.add(number);

        return out;
    }

    /**
     * Finds the unique prime factors of a number.
     *
     * @param n The input number.
     * @return A list of distinct prime factors.
     */
    public static ArrayList<Integer> getUniquePrimeFactors(int n) {
        ArrayList<Integer> out = new ArrayList<>();

        // Handle edge case for numbers less than or equal to 1
        if (n <= 1)
            return out;

        // Check divisibility by each number up to the square root of the input
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                out.add(i);

                // Divide the number by the prime factor until it is no longer divisible
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        // If the remaining number is greater than 1, it is a unique prime factor
        if (n > 1)
            out.add(n);

        return out;
    }
}
