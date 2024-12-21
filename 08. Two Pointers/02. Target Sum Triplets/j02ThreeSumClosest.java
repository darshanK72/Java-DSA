/**
 * Problem Statement:
 * 
 *     Given an array of integers, find three integers in the array such that the sum is closest to a given target.
 *     Return the sum of the three integers.
 *     You may assume that each input would have exactly one solution.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `target` (1 <= target <= 10^5).
 * 
 * Output:
 *     - An integer representing the sum of three integers that is closest to the target.
 * 
 * Example:
 *     Input:
 *     4
 *     -1 2 1 -4
 *     1
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The sum that is closest to the target 1 is -1 + 2 + 1 = 2.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j02ThreeSumClosest {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }
        int target = in.nextInt(); // Input: target value

        // Call your solution
        System.out.printf("Your Solution: %d\n", threeSumClosest(nums, target));

        // Call optimized solution
        System.out.printf("Optimized Solution: %d\n", threeSumClosestEfficient(nums, target));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - We check all possible triplets in the array and calculate their sum. 
     * - For each sum, we calculate its absolute difference with the target.
     * - The triplet with the smallest difference is chosen.
     * 
     * Time Complexity:
     * - O(n^3). We iterate through all triplets in the array.
     * 
     * Space Complexity:
     * - O(1). We are using only a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @param target The target value to find the closest sum to.
     * @return The closest sum of three integers to the target.
     */
    public static int threeSumClosest(int[] nums, int target) {
        int ans = 0;
        int maxDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int d = Math.abs(target - sum);
                    if (d < maxDiff) {
                        maxDiff = d;
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Approach 2: Optimized Approach with Sorting and Two-Pointer Technique
     * 
     * Intuition:
     * - First, we sort the array to allow the use of the two-pointer technique.
     * - We iterate through the array, and for each number, use two pointers to find the other two numbers that
     *   give the sum closest to the target.
     * - By moving the pointers based on the current sum, we avoid checking all triplets.
     * 
     * Time Complexity:
     * - O(n^2). Sorting takes O(n log n), and the two-pointer search takes O(n) for each iteration.
     * 
     * Space Complexity:
     * - O(1). We use a constant amount of space, aside from the input array.
     * 
     * @param nums The input array of numbers.
     * @param target The target value to find the closest sum to.
     * @return The closest sum of three integers to the target.
     */
    public static int threeSumClosestEfficient(int[] nums, int target) {
        Arrays.sort(nums); // Sort the array for two-pointer technique
        int dist = Integer.MAX_VALUE;
        int ans = 0;
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            int k = nums.length - 1;
            int tar = target - nums[i];
            while (j < k) {
                int sum = nums[j] + nums[k];
                int d = Math.abs(tar - sum);
                if (d < dist) {
                    ans = sum + nums[i];
                    dist = d;
                } else if (sum > tar) {
                    k--;
                } else {
                    j++;
                }
            }
            i++;
        }
        return ans;
    }
}
