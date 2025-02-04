/*-
 * Problem Statement:
 * 
 *     Given an array arr[] of size N where every element is in the range 
 *     from 0 to N-1, rearrange the given array so that the transformed 
 *     array arrT[i] becomes arr[arr[i]].
 * 
 *     NOTE: arr and arrT are both the same variables, representing the 
 *     array before and after transformation, respectively.
 * 
 * Input:
 *     - An integer `N` (1 <= N <= 10^5), representing the size of the array.
 *     - An array `arr` of size `N` where each element satisfies 
 *       (0 <= arr[i] <= N-1).
 * 
 * Output:
 *     - The modified array `arr` where arr[i] = arr[arr[i]].
 * 
 * Example 1:
 *     Input:
 *         N = 2
 *         arr[] = [1, 0]
 *     Output:
 *         0 1
 *     Explanation: 
 *         arr[arr[0]] = arr[1] = 0
 *         arr[arr[1]] = arr[0] = 1
 *         So, arrT becomes {0, 1}
 * 
 * Example 2:
 *     Input:
 *         N = 5
 *         arr[] = [4, 0, 2, 1, 3]
 *     Output:
 *         3 4 2 0 1
 *     Explanation: 
 *         arr[arr[0]] = arr[4] = 3
 *         arr[arr[1]] = arr[0] = 4
 *         arr[arr[2]] = arr[2] = 2
 *         arr[arr[3]] = arr[1] = 0
 *         arr[arr[4]] = arr[3] = 1
 *         So, arrT becomes {3, 4, 2, 0, 1}
 */

import java.util.Scanner;

public class j05InversePermutationII {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong(); // Input: elements of the array
        }

        // Call your solution
        arrangeEfficient(arr, n);

        // Print the modified array
        for (long num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        in.close();
    }

    /*-
     * Approach 1: Using Extra Space
     * 
     * Intuition:
     * - We create a new array `inverse` to store the rearranged values.
     * - We iterate through the given array and store `arr[arr[i]]` in `inverse[i]`.
     * - Finally, we copy back the values from `inverse` to `arr`.
     * 
     * Time Complexity:
     * - O(N) because we iterate through the array twice.
     * 
     * Space Complexity:
     * - O(N) due to the extra array used for storing the results.
     * 
     * @param arr The input array.
     * @param n   The size of the array.
     */
    static void arrange(long arr[], int n) {
        long[] inverse = new long[n];
        for (int i = 0; i < n; i++) {
            inverse[i] = arr[(int) arr[i]];
        }
        for (int i = 0; i < n; i++) {
            arr[i] = inverse[i];
        }
    }

    /*-
     * Approach 2: Optimized In-Place Transformation (O(1) Extra Space)
     * 
     * Intuition:
     * - Since each element is in the range [0, N-1], we can encode two numbers 
     *   in a single index using modular arithmetic.
     * - We store both old and new values in arr[i] using:
     *     arr[i] = oldValue + (newValue % N) * N
     *   - `oldValue` is preserved since (newValue % N) * N ensures that oldValue 
     *     remains when we take arr[i] % N.
     * - In the second pass, we extract the new values by performing `arr[i] / N`.
     * 
     * Time Complexity:
     * - O(N) because we iterate through the array twice.
     * 
     * Space Complexity:
     * - O(1) since we modify the array in-place.
     * 
     * @param arr The input array.
     * @param n   The size of the array.
     */
    static void arrangeEfficient(long arr[], int n) {
        // First pass: Encode the new values in arr[i]
        for (int i = 0; i < n; i++) {
            long oldVal = arr[i];
            long newVal = arr[(int) arr[i]];
            arr[i] = oldVal + (newVal % n) * n;
        }

        // Second pass: Extract the new values
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / n;
        }
    }
}
