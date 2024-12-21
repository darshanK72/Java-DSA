/**
 * Problem Statement:
 *
 *     Given an array of integers `arr` and an integer `k`, you are required to find the number of subarrays whose XOR is equal to `k`.
 *     A subarray is defined as a contiguous part of the array, and the XOR of a subarray is calculated by taking the XOR of all the elements in that subarray.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (0 <= k <= 10^5), representing the target XOR value for the subarrays.
 *
 * Output:
 *     - An integer representing the number of subarrays whose XOR is equal to `k`.
 *
 * Example:
 *     Input:
 *     4
 *     4 2 2 6
 *     6
 *
 *     Output:
 *     2
 *
 *     Explanation:
 *     The two subarrays whose XOR is 6 are:
 *     - Subarray [4, 2] from index 0 to 1, XOR = 4 ^ 2 = 6.
 *     - Subarray [6] from index 3 to 3, XOR = 6.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j10SubarraysWithGivenXOR {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target XOR value

        // Call the solution method
        System.out.println(subarrrayWithGivenXOR(arr, k));

        in.close();
    }

    /**
     * Approach: Optimized Approach Using Prefix XOR and HashMap
     * 
     * Intuition:
     * - We use the prefix XOR technique, where we maintain a running XOR of the elements of the array.
     * - For each element in the array, we calculate the current cumulative XOR and check if there is a subarray 
     *   ending at the current index whose XOR is equal to `k`. This is done by checking if `currentXOR ^ k` 
     *   has appeared previously in the prefix XORs.
     * - If `currentXOR ^ k` has been seen before, it means that there is a subarray with the required XOR.
     * - The key observation here is that for any two indices `i` and `j`, the XOR of the subarray from index `i` to `j` 
     *   can be computed as `prefixXOR[j] ^ prefixXOR[i-1]`. Therefore, to find subarrays with the target XOR `k`, 
     *   we look for previously seen `prefixXOR[i]` such that `prefixXOR[i] == prefixXOR[j] ^ k`.
     * 
     * Time Complexity:
     * - O(n): We traverse the array once, and each operation involving the HashMap (insert and lookup) is O(1) on average.
     * 
     * Space Complexity:
     * - O(n): We store the prefix XOR values in the HashMap, so the space complexity is proportional to the number of unique XOR values.
     * 
     * Explanation of Key Step:
     * - We calculate the current cumulative XOR (`xor`), and we check if `xor ^ k` has been encountered in the map.
     * - If it has, it means there exists a subarray ending at the current index whose XOR is `k`, so we increment the count.
     * - We also store the current cumulative XOR (`xor`) in the map, keeping track of how many times each XOR value has been seen.
     * 
     * @param arr The input array of numbers.
     * @param k The target XOR value for the subarrays.
     * @return The number of subarrays whose XOR is equal to `k`.
     */
    public static int subarrrayWithGivenXOR(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0; // Initialize the count of subarrays with XOR equal to k
        int xor = 0; // Initialize the cumulative XOR

        // Initialize the map with the prefix XOR 0 occurring once to handle cases where
        // the XOR of a subarray is exactly `k`
        map.put(0, 1);

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; // Update the cumulative XOR

            // Check if xor ^ k has been encountered in the map
            count += map.getOrDefault(xor ^ k, 0);

            // Store the current cumulative XOR in the map
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }

        return count;
    }
}
