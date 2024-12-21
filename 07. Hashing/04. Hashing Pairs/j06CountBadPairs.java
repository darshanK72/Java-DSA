/**
 * Problem Statement:
 * 
 *     You are given a 0-indexed integer array `nums`. A pair of indices `(i, j)` is 
 *     considered a "bad pair" if `i < j` and `j - i != nums[j] - nums[i]`.
 *     Your task is to return the total number of bad pairs in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^9).
 * 
 * Output:
 *     - A long integer representing the total number of bad pairs in `nums`.
 * 
 * Example:
 *     Input:
 *     5
 *     4 1 3 3 6
 *     Output:
 *     5
 * 
 *     Explanation:
 *     - The bad pairs are: (0, 1), (0, 2), (0, 3), (1, 3), and (2, 4).
 *       Total count = 5.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j06CountBadPairs {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        System.out.println(countBadPairs(arr));

        in.close();
    }

    /**
     * Approach: Difference Mapping
     * 
     * Intuition:
     * - For the condition `j - i != nums[j] - nums[i]`, we can rearrange it to:
     *   `nums[j] - j != nums[i] - i`.
     * - Define `key = nums[x] - x` for any index `x`. For two indices `(i, j)` to 
     *   form a good pair, the `key` values must match (`key[i] == key[j]`).
     * - If the total number of pairs in the array is `n * (n - 1) / 2`, then:
     *   Total bad pairs = Total pairs - Good pairs.
     * 
     * Explanation:
     * - Use a HashMap to count the frequency of each `key = nums[x] - x` as we 
     *   traverse the array.
     * - For each index, the count of good pairs contributed by the current index 
     *   is equal to the frequency of the corresponding `key` in the map.
     * - Subtract the total good pairs from the total pairs to get the count of bad pairs.
     * 
     * Time Complexity:
     * - O(N), where `N` is the size of the array. We iterate through the array once.
     * 
     * Space Complexity:
     * - O(N), for storing the frequencies in the HashMap.
     * 
     * @param nums The input array of integers.
     * @return The total number of bad pairs.
     */
    public static long countBadPairs(int[] nums) {
        long count = 0;
        long n = nums.length;

        // Map to store frequencies of the keys (nums[i] - i)
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i] - i;

            // Add good pairs count for the current index
            if (map.containsKey(key)) {
                count += map.get(key);
            }

            // Update the frequency of the current key
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // Total pairs - good pairs = bad pairs
        return (n * (n - 1)) / 2 - count;
    }
}
