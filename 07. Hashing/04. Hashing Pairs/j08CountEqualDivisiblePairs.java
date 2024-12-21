/**
 * Problem Statement:
 * 
 *     Given a 0-indexed integer array `nums` of length `n` and an integer `k`, 
 *     return the number of pairs `(i, j)` where `0 <= i < j < n`, such that:
 * 
 *         - `nums[i] == nums[j]`
 *         - `(i * j)` is divisible by `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 100).
 *     - An integer `k` (1 <= k <= 100).
 * 
 * Output:
 *     - An integer representing the count of pairs satisfying the conditions.
 * 
 * Example:
 *     Input:
 *         4
 *         3 1 2 3
 *         3
 *     Output:
 *         1
 * 
 *     Explanation:
 *         - The only valid pair is `(0, 3)` since `nums[0] == nums[3]` and `(0 * 3) % 3 == 0`.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j08CountEqualDivisiblePairs {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        // Output the result
        System.out.println(countEqualDivisiblePairs(arr, k));
        in.close();
    }

    /**
     * Approach: HashMap to Group Indices by Value
     * 
     * Intuition:
     * - The condition `nums[i] == nums[j]` means we need to group indices of identical values.
     * - The condition `(i * j) % k == 0` can then be checked for all pairs of indices in the same group.
     * - By grouping indices for identical numbers using a HashMap, we can efficiently calculate pairs.
     * 
     * Explanation:
     * - Use a HashMap to group indices based on the corresponding value in `nums`.
     * - For every value in `nums`, store its indices in the HashMap.
     * - For every new index added to the group, iterate through the existing indices of the same value.
     * - Check if `(i * j) % k == 0` for each pair `(i, j)` and increment the count accordingly.
     * - This approach avoids unnecessary comparisons with numbers that are not equal, reducing complexity.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the size of the array and `m` is the average size of groups in the HashMap.
     *   In the worst case, when all values are identical, `m = n`, leading to O(n^2) complexity.
     * 
     * Space Complexity:
     * - O(n), where `n` is the size of the array (for storing indices in the HashMap).
     * 
     * @param nums The input array of numbers.
     * @param k The divisor for the condition `(i * j) % k == 0`.
     * @return The count of pairs satisfying the conditions.
     */
    public static int countEqualDivisiblePairs(int[] nums, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            // Check pairs with the same value
            if (map.containsKey(nums[i])) {
                for (int j : map.get(nums[i])) {
                    if ((i * j) % k == 0) {
                        count++;
                    }
                }
            }
            // Add current index to the group for this value
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        return count;
    }
}
