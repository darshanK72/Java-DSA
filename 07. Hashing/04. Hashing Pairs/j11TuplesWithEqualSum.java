/**
 * Problem Statement:
 * 
 *     You are given an array of integers. You need to determine if there exist two distinct pairs of indices `(i, j)` and `(k, l)` such that:
 * 
 *         - `arr[i] + arr[j] == arr[k] + arr[l]`, where `i != j` and `k != l`.
 * 
 *     If such pairs exist, return `true`, otherwise return `false`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array `arr`.
 *     - An array `arr` of size `n` where each element satisfies (-10^9 <= arr[i] <= 10^9).
 * 
 * Output:
 *     - A boolean value indicating whether there exist two distinct pairs `(i, j)` and `(k, l)` such that `arr[i] + arr[j] == arr[k] + arr[l]`.
 * 
 * Example:
 *     Input:
 *         4
 *         1 2 3 4
 *     Output:
 *         true
 * 
 *     Explanation:
 *         The pairs `(i, j)` and `(k, l)` are:
 *         - `arr[0] + arr[3] = 1 + 4 = 5`
 *         - `arr[1] + arr[2] = 2 + 3 = 5`
 *         Since both pairs sum up to 5, the output is `true`.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j11TuplesWithEqualSum {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Output the result
        System.out.println(findPairs(arr));
        in.close();
    }

    /**
     * Approach: HashMap for Pair Sum Check
     * 
     * Intuition:
     * - The goal is to find if there exist two distinct pairs `(i, j)` and `(k, l)` such that the sums of their elements are equal.
     * - We can utilize a HashMap to store the sum of every possible pair of elements in the array.
     * - If at any point, we find a sum that already exists in the HashMap, it means we have encountered two distinct pairs with the same sum, and we can return `true`.
     * - If no such pairs are found after checking all pairs, return `false`.
     * 
     * Explanation:
     * - Loop through all pairs `(i, j)` where `i < j` and calculate the sum `arr[i] + arr[j]`.
     * - Store each sum in the HashMap as the key. If the sum already exists in the map, return `true` immediately.
     * - If we finish iterating through all pairs without finding any matching sums, return `false`.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the array. This is due to the nested loops that check all pairs.
     * 
     * Space Complexity:
     * - O(n^2), in the worst case, as we store every unique pair sum in the HashMap.
     * 
     * @param arr The input array of numbers.
     * @return `true` if there are two distinct pairs whose sums are equal, otherwise `false`.
     */
    public static boolean findPairs(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Check all pairs of numbers
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if (map.containsKey(sum)) {
                    return true; // Found matching sum, return true
                } else {
                    map.put(sum, 1); // Store the sum in the map
                }
            }
        }

        return false; // No matching sums found
    }
}
