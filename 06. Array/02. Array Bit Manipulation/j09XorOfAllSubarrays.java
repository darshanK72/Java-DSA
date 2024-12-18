/**
 * Problem Statement:
 * 
 *     Given an array `A` of integers, the task is to find the XOR of all subarrays of `A`. A subarray is a contiguous part of the array.
 *     The goal is to compute the XOR of all elements that appear in any of the subarrays, considering each element's frequency of occurrence.
 * 
 * Input:
 *     - An integer `N` (1 <= N <= 10^5), representing the size of the array.
 *     - An array `A` of size `N` where each element satisfies (1 <= A[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the XOR of all subarrays of `A`.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     Output:
 *     0
 * 
 *     Explanation:
 *     The subarrays are:
 *     [1], [2], [3], [1, 2], [2, 3], [1, 2, 3]
 *     The XOR of all the elements that appear in the subarrays is: 1 ^ 2 ^ 3 ^ 1 ^ 2 ^ 3 = 0
 */

import java.util.Scanner;

public class j09XorOfAllSubarrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Size of the array
        int[] A = new int[N]; // Array to store the input numbers
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt(); // Read each element into the array
        }

        // Call the method to calculate XOR of all subarrays
        System.out.println(gameOfXor(N, A));

        // Close the scanner to avoid resource leaks
        in.close();
    }

    /**
     * Approach: Efficient XOR Calculation Based on Frequency of Occurrence
     * 
     * Intuition:
     * - Each element `A[i]` appears in multiple subarrays, and the number of times it appears is based on its position in the array.
     * - Specifically, element `A[i]` appears in exactly `i * (N - i + 1)` subarrays. This is because:
     *     - The number of subarrays starting at or before index `i` is `i`.
     *     - The number of subarrays ending at or after index `i` is `(N - i + 1)`.
     * - If the number of times an element appears in subarrays is odd, it contributes to the final XOR, otherwise it does not.
     * - Therefore, we XOR an element `A[i]` only if the number of times it appears in the subarrays is odd.
     * 
     * Time Complexity:
     * - O(N), since we iterate through the array once to calculate the frequency of occurrence for each element and compute the XOR accordingly.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space for the result and temporary variables.
     * 
     * @param N The size of the array.
     * @param A The input array of integers.
     * @return The XOR of all subarrays.
     */
    public static int gameOfXor(int N, int[] A) {
        int ans = 0; // Initialize the result variable for XOR
        for (int i = 1; i <= N; i++) {
            // Calculate the frequency of occurrence for element A[i-1]
            int occ = i * (N - i + 1);
            // If the occurrence count is odd, XOR the element
            if (occ % 2 == 1) {
                ans ^= A[i - 1];
            }
        }
        return ans; // Return the final XOR value
    }
}
