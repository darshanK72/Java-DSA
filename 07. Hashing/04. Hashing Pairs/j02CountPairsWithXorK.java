/**
 * Problem Statement:
 * 
 *     Given an array of integers `arr` and an integer `k`, count the number of pairs
 *     (i, j) such that `arr[i] ^ arr[j] == k`, where ^ represents the XOR operation.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (0 <= arr[i] <= 10^5).
 *     - An integer `k` (0 <= k <= 10^5).
 * 
 * Output:
 *     - An integer representing the number of pairs (i, j) where `arr[i] ^ arr[j] == k`.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     3
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The pairs that satisfy the condition are (1, 2) and (3, 0), as:
 *     - 1 ^ 2 = 3
 *     - 3 ^ 0 = 3
 */

import java.util.HashSet;
import java.util.Scanner;

public class j02CountPairsWithXorK {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the value of k

        // Call the solution
        System.out.println(countPairsWithXorK(arr, k));

        in.close();
    }

    /**
     * Approach 1: Using HashSet (O(N))
     * 
     * Intuition:
     * - We can use a HashSet to store the elements as we iterate through the array.
     * - For each element `a` in the array, we check if the value `a ^ k` exists in the HashSet.
     *   If it does, it means we found a pair (a, b) such that `a ^ b == k`, where `b` is an element
     *   we encountered before.
     * - The key idea here is that if `a ^ b == k`, then `a = b ^ k` (rearranging the equation), which
     *   means for each element `a`, we need to check if `a ^ k` exists in the set.
     * 
     * Time Complexity:
     * - O(N), as we are iterating through the array once and performing constant-time operations using the HashSet.
     * 
     * Space Complexity:
     * - O(N), as we are storing elements in a HashSet.
     * 
     * @param arr The input array of integers.
     * @param k The value of k.
     * @return The number of pairs (i, j) such that arr[i] ^ arr[j] == k.
     */
    public static int countPairsWithXorK(int[] arr, int k) {
        HashSet<Integer> set = new HashSet<Integer>(); // HashSet to store elements
        int count = 0; // To store the number of valid pairs

        for (int a : arr) {
            // If the pair (a, a^k) exists, we found a valid pair
            if (set.contains(a ^ k)) {
                count++; // Increment count if a^k is found in the set
            }
            set.add(a); // Add the current element to the set
        }

        return count; // Return the total count of pairs
    }
}
