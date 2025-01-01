
/**
 * Problem Statement:
 * 
 *     Given an array of integers, calculate the GCD (Greatest Common Divisor) of all the elements in the array.
 * 
 * Input:
 *     - An integer `n`, the number of elements in the array.
 *     - `n` integers representing the elements of the array.
 * 
 * Output:
 *     - The Greatest Common Divisor (GCD) of all the elements in the array.
 * 
 * Example:
 *     Input:
 *     4
 *     12 15 21 30
 *     Output:
 *     GCD of array = 3
 * 
 * Constraints:
 *     - `n` is a positive integer.
 *     - The array elements are non-negative integers.
 */

import java.util.Scanner;

public class j10GcdOfArray {
    public static void main(String args[]) {
        // Input reading
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Read number of elements in the array
        int[] arr = new int[n]; // Initialize the array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        // Print the GCD of the entire array
        System.out.println(gcdOfArray(arr));

        in.close();
    }

    /**
     * GCD of Two Numbers using Euclidean Algorithm
     * 
     * Intuition:
     * - The Euclidean algorithm is a method to efficiently compute the GCD of two
     * numbers `a` and `b`.
     * - The key idea is that `gcd(a, b)` is the same as `gcd(b, a % b)`. This
     * process repeats until `b` becomes 0, at which point `a` will be the GCD.
     * - For an array, we compute the GCD of the first two elements, then compute
     * the GCD of that result with the next element, and so on until all elements
     * are processed.
     * 
     * Time Complexity: O(n * log(min(a, b))), where `n` is the number of elements
     * in the array and `a, b` are the numbers being compared in each iteration.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param a The first integer.
     * @param b The second integer.
     * @return The GCD of a and b.
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Approach 1: Compute GCD of Array
     * 
     * Intuition:
     * - To compute the GCD of an entire array, we can iteratively compute the GCD
     * of the array elements.
     * - The approach uses the previously defined `gcd` method and applies it on the
     * array. Starting with the first element, it computes the GCD of that element
     * and the next, then continues this for all elements in the array.
     * - This method is efficient, as it reduces the problem of finding the GCD of
     * an array to a series of pairwise GCD computations.
     * 
     * Time Complexity: O(n * log(min(a, b))), where `n` is the number of elements
     * in the array and `a, b` are the numbers being compared in each iteration.
     * Space Complexity: O(1), only integer variables are used.
     * 
     * @param arr The array of integers.
     * @return The GCD of all elements in the array.
     */
    static int gcdOfArray(int[] arr) {
        int gcd = arr[0]; // Start with the first element as the initial GCD.
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]); // Compute the GCD with the next element.
        }
        return gcd; // Final GCD after processing all elements.
    }
}
