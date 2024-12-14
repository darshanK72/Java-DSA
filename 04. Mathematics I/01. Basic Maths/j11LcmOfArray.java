
/**
 * Problem Statement:
 * 
 *     Given an array of integers, calculate the LCM (Least Common Multiple) of all the elements in the array.
 * 
 * Input:
 *     - An integer `n`, the number of elements in the array.
 *     - `n` integers representing the elements of the array.
 * 
 * Output:
 *     - The Least Common Multiple (LCM) of all the elements in the array.
 * 
 * Example:
 *     Input:
 *     4
 *     12 15 21 30
 *     Output:
 *     LCM of array = 420
 * 
 * Constraints:
 *     - `n` is a positive integer.
 *     - The array elements are non-negative integers.
 */

import java.util.Scanner;

public class j11LcmOfArray {

    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Read the number of elements in the array
        int[] arr = new int[n]; // Initialize the array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        // Print the LCM of the entire array
        System.out.println(lcmOfArray(arr));

        // Close the input scanner
        in.close();
    }

    /**
     * Approach 1: LCM of Array (without modulo)
     * 
     * Intuition:
     * - The least common multiple (LCM) of two numbers can be derived from their
     * GCD (Greatest Common Divisor) using the formula:
     * - `LCM(a, b) = (a * b) / GCD(a, b)`.
     * - To find the LCM of an array, we compute the LCM of the first two elements,
     * then compute the LCM of that result with the next element, and so on.
     * - This way, we iteratively calculate the LCM for all elements of the array.
     * 
     * Time Complexity: O(n * log(min(a, b))), where `n` is the number of elements
     * in the array and `a, b` are the elements being compared.
     * Space Complexity: O(1), only integer and long variables are used.
     * 
     * @param arr The array of integers.
     * @return The LCM of all elements in the array.
     */
    static long lcmOfArray(int arr[]) {
        long ans = 1; // Initialize the result with 1
        for (int i = 0; i < arr.length; i++) {
            ans = lcm(ans, (long) arr[i]); // Compute the LCM iteratively
        }
        return ans; // Return the LCM of the entire array
    }

    /**
     * Approach 2: LCM of Array with Modulo 10^9 + 7
     * 
     * Intuition:
     * - This method is similar to the previous one but ensures that the result is
     * printed modulo 10^9 + 7.
     * - We compute the LCM of the array elements, but during each iteration, we
     * apply the modulo operation.
     * - The modulo operation is performed after the LCM is calculated to avoid
     * overflow during multiplication.
     * 
     * Time Complexity: O(n * log(min(a, b))), where `n` is the number of elements
     * in the array and `a, b` are the elements being compared.
     * Space Complexity: O(1), only integer and long variables are used.
     * 
     * @param arr The array of integers.
     * @return The LCM of all elements in the array modulo 10^9 + 7.
     */
    static long lcmOfArrayMod(int arr[]) {
        long ans = 1; // Initialize the result with 1
        for (int i = 0; i < arr.length; i++) {
            ans = (ans * (long) arr[i]) / gcd(ans, (long) arr[i]) % 1000000007; // Calculate LCM and apply modulo
        }
        return ans; // Return the result modulo 10^9 + 7
    }

    /**
     * Helper Method: Calculate LCM of two numbers.
     * 
     * Intuition:
     * - The LCM of two numbers can be calculated using the formula:
     * - `LCM(a, b) = (a * b) / GCD(a, b)`.
     * - This method uses the previously defined `gcd` function to find the GCD of
     * `a` and `b`, then computes the LCM.
     * 
     * Time Complexity: O(log(min(a, b))) for GCD computation.
     * Space Complexity: O(1).
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The LCM of a and b.
     */
    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b); // Calculate LCM using the formula
    }

    /**
     * Helper Method: Calculate GCD of two numbers using Euclidean Algorithm.
     * 
     * Intuition:
     * - The GCD of two numbers `a` and `b` can be calculated using the Euclidean
     * algorithm.
     * - The key idea is that `GCD(a, b)` is the same as `GCD(b, a % b)`. This
     * process repeats until `b` becomes 0, and then `a` is the GCD.
     * 
     * Time Complexity: O(log(min(a, b))), due to the logarithmic nature of the
     * Euclidean algorithm.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a; // If b is 0, a is the GCD
        }
        return gcd(b, a % b); // Recursively compute GCD
    }
}
