/**
 * Problem Statement:
 * 
 *     Given an array `nums` of integers, the task is to count the number of quadruplets
 *     (i, j, k, l) such that:
 *     - 0 <= i < j < k < l < nums.length
 *     - nums[i] + nums[j] + nums[k] == nums[l]
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100), the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (-100 <= nums[i] <= 100).
 * 
 * Output:
 *     - An integer `count` representing the number of quadruplets that satisfy the condition.
 * 
 * Example:
 *     Input:
 *     nums = {1, 2, 3, 6}
 *     
 *     Output:
 *     1
 * 
 *     Explanation:
 *     There is one valid quadruplet (0, 1, 2, 3), as:
 *     nums[0] + nums[1] + nums[2] == nums[3], i.e., 1 + 2 + 3 == 6.
 */

import java.util.Scanner;

public class j04CountSpecialQuadruplets {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        // Read the input array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Call and print the result of the brute force method
        System.out.println(countQuadruplets(arr));

        // Uncomment the line below for the efficient method (if implemented)
        // System.out.println(countQuadrupletsEfficient(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force Solution
     * 
     * Intuition:
     * - The brute force approach checks all possible combinations of four distinct indices 
     *   i, j, k, l, where i < j < k < l.
     * - For each combination, it calculates the sum of nums[i], nums[j], and nums[k] and 
     *   checks if the sum equals nums[l].
     * - While this solution is easy to implement, it has a time complexity of O(n^4), which 
     *   may be inefficient for larger input sizes.
     * 
     * Time Complexity:
     * - O(n^4), where `n` is the length of the array. This is due to the four nested loops 
     *   used to check all combinations of indices.
     * 
     * Space Complexity:
     * - O(1), as we only use a few integer variables for counting.
     * 
     * @param nums The input array of numbers.
     * @return The number of quadruplets that satisfy the condition.
     */
    public static int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        // Iterate over all combinations of four distinct indices
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        int sum = nums[i] + nums[j] + nums[k];
                        // Check if the sum of three elements equals the fourth element
                        if (sum == nums[l])
                            count++;
                    }
                }
            }
        }
        return count;
    }
}
