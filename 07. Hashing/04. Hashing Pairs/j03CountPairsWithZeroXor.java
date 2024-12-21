/**
 * Problem Statement:
 * 
 *     Given an array of integers, count the number of pairs (i, j) such that the XOR of the two elements 
 *     `arr[i] ^ arr[j] == 0`, i.e., the two elements are the same. The pair should not include the same element twice.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (0 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A long integer representing the number of pairs (i, j) where `arr[i] ^ arr[j] == 0`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 1 1 2
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The pairs that satisfy the condition are (1, 1), (1, 1), (2, 2), and (1, 1).
 *     So, the total number of pairs is 4.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j03CountPairsWithZeroXor {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        System.out.println(countPairsWithZeroXor(arr, n));

        in.close();
    }

    /**
     * Approach 1: Using HashMap (O(N))
     * 
     * Intuition:
     * - The key observation is that XOR of two identical numbers is always 0. Therefore, we need to count how many
     *   times each element appears in the array.
     * - For each element `arr[i]`, if the element `arr[i]` has appeared `x` times before in the array, 
     *   then it can form `x` pairs with previously seen occurrences of the same element (since `arr[i] ^ arr[i] == 0`).
     * - We store the frequency of each element in a HashMap. For every element `arr[i]`, we check how many times 
     *   it has occurred before, which directly contributes to the count of pairs.
     * 
     * Time Complexity:
     * - O(N), as we only iterate through the array once and perform constant-time operations for each element using the HashMap.
     * 
     * Space Complexity:
     * - O(N), as we are storing the frequency of elements in the HashMap.
     * 
     * @param arr The input array of integers.
     * @param n The size of the array.
     * @return The number of pairs (i, j) such that arr[i] ^ arr[j] == 0.
     */
    public static long countPairsWithZeroXor(int arr[], int n) {
        int count = 0; // To store the number of pairs
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store the frequency of elements

        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                count += map.get(arr[i]); // Increment count by the number of times arr[i] appeared before
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1); // Update the frequency of arr[i] in the map
        }

        return count; // Return the total count of pairs
    }
}
