/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums`, we need to find the number of distinct tuples `(i, j, k, l)` such that:
 * 
 *         - `nums[i] * nums[j] == nums[k] * nums[l]` and
 *         - `i < j < k < l`
 * 
 *     Return the number of such tuples. The answer is guaranteed to be divisible by 8.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array `nums`.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 1000).
 * 
 * Output:
 *     - The number of distinct tuples `(i, j, k, l)` such that `nums[i] * nums[j] == nums[k] * nums[l]`.
 * 
 * Example:
 *     Input:
 *         4
 *         2 3 4 6
 *     Output:
 *         2
 * 
 *     Explanation:
 *         The pairs of indices `(i, j, k, l)` are:
 *         - (0, 1, 2, 3) as `nums[0] * nums[1] == nums[2] * nums[3]`, i.e., `2 * 3 == 4 * 6`.
 *         So, the result is 2.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j12TuplesWithSameProduct {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Output the result
        System.out.println(tupleSameProduct(arr));
        in.close();
    }

    /**
     * Approach: HashMap for Pair Product Count
     * 
     * Intuition:
     * - We need to find pairs `(i, j)` and `(k, l)` such that `nums[i] * nums[j] == nums[k] * nums[l]`.
     * - For every pair `(i, j)`, we can compute the product `nums[i] * nums[j]`.
     * - We use a HashMap to store the frequency of each product.
     * - If the same product occurs multiple times, it indicates that there are multiple valid tuples that can be formed with that product.
     * - The number of valid tuples for a product `p` that appears `v` times is `v * (v - 1) / 2` because we can select two distinct pairs from `v` occurrences.
     * - Multiply the total count by 8, since the order of the tuple matters and the number of distinct permutations of 4 elements is `4! = 24`, and the result is divided by 3 due to the fact that the order of the pairs doesn't matter.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the array, since we are computing the product for each pair `(i, j)`.
     * 
     * Space Complexity:
     * - O(n^2), in the worst case, as we are storing the product frequencies in a HashMap.
     * 
     * @param nums The input array of numbers.
     * @return The number of distinct tuples whose products are equal.
     */
    public static int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Calculate the product for every pair and store it in the map
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int p = nums[i] * nums[j];
                map.put(p, map.getOrDefault(p, 0) + 1);
            }
        }

        // Count valid tuples based on the frequency of each product
        int count = 0;
        for (Integer key : map.keySet()) {
            int v = map.get(key);
            count += (v * (v - 1)) / 2; // Calculate combinations of pairs with the same product
        }

        // Multiply by 8 since there are 8 permutations for each valid tuple
        return 8 * count;
    }
}
