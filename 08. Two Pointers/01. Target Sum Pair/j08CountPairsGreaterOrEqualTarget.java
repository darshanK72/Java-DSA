/**
 * Problem Statement:
 * 
 *     Given an integer array `nums` and a target value `k`, the task is to count the number of pairs of elements 
 *     in the array such that the sum of each pair is greater than or equal to the target `k`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (1 <= k <= 10^5), the target sum.
 * 
 * Output:
 *     - An integer representing the count of pairs whose sum is greater than or equal to the target `k`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     6
 *     Output:
 *     4
 *     
 *     Explanation:
 *     The valid pairs whose sum is greater than or equal to 6 are:
 *     (1, 5), (2, 4), (3, 3), and (2, 5)
 */

import java.util.Arrays;
import java.util.Scanner;

public class j08CountPairsGreaterOrEqualTarget {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the target sum

        // Call the naive solution
        System.out.printf("Naive Solution: %d\n", twoSumGreaterOrEqualTarget(arr, k));

        // Call the efficient solution
        System.out.printf("Optimized Solution: %d\n", twoSumGreaterOrEqualTargetEfficient(arr, k));

        in.close();
    }

    /**
     * Approach 1: Naive Approach
     * 
     * Intuition:
     * - In this approach, we use two nested loops to iterate through all pairs of elements in the array 
     *   and check if their sum is greater than or equal to the target `k`.
     * - This solution has a simple brute force logic but its time complexity can become very large for large inputs.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the array.
     * - The nested loops iterate over all pairs, making it inefficient for larger arrays.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few extra variables.
     * 
     * @param nums The input array of numbers.
     * @param target The target value.
     * @return The count of pairs whose sum is greater than or equal to the target.
     */
    public static int twoSumGreaterOrEqualTarget(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] >= target)
                    count++;
            }
        }
        return count;
    }

    /**
     * Approach 2: Optimized Approach
     * 
     * Intuition:
     * - In this optimized approach, we first sort the array and then use the two-pointer technique.
     * - After sorting, the two pointers start at the beginning and end of the array.
     * - If the sum of the elements at the two pointers is greater than or equal to the target, all pairs 
     *   from the start pointer to the end pointer will satisfy the condition, and we add their count.
     * - We move the end pointer leftward if the sum is valid, otherwise, we move the start pointer rightward.
     * 
     * Time Complexity:
     * - O(n log n), where n is the size of the array due to the sorting step. After sorting, the two-pointer 
     *   technique runs in O(n) time.
     * 
     * Space Complexity:
     * - O(1), since we're only using a few extra variables and modifying the input array in place.
     * 
     * @param nums The input array of numbers.
     * @param target The target value.
     * @return The count of pairs whose sum is greater than or equal to the target.
     */
    public static int twoSumGreaterOrEqualTargetEfficient(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array first
        int count = 0;
        int s = 0;
        int e = nums.length - 1;

        // Use two-pointer technique
        while (s < e) {
            int sum = nums[s] + nums[e];
            if (sum >= target) {
                count += (e - s); // All pairs from s to e are valid
                e--; // Decrease the end pointer
            } else {
                s++; // Increase the start pointer
            }
        }
        return count;
    }
}
