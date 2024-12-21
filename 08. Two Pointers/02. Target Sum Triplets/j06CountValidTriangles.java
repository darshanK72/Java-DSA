/**
 * Problem Statement:
 * 
 *     Given an array of integers representing the lengths of rods, the task is to count how many valid 
 *     triangles can be formed using three different lengths from the array. For any triplet (i, j, k), 
 *     the sides can form a valid triangle if and only if the sum of any two sides is greater than the third side.
 *     Specifically, for three sides a, b, and c, the triangle inequality theorem must hold:
 *     a + b > c, a + c > b, b + c > a.
 * 
 * Input:
 *     - An integer `n` (3 <= n <= 1000), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 1000).
 * 
 * Output:
 *     - An integer representing the number of valid triangles that can be formed.
 * 
 * Example:
 *     Input:
 *     5
 *     2 2 3 4 5
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The three valid triangles that can be formed are:
 *     (2, 3, 4), (2, 4, 5), and (3, 4, 5).
 */

import java.util.Arrays;
import java.util.Scanner;

public class j06CountValidTriangles {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(triangleNumber(arr)); // Solution for counting valid triangles
        in.close();
    }

    /**
     * Approach: Efficient Solution using Sorting and Two Pointers
     * 
     * Intuition:
     * - To form a valid triangle, for three sides a, b, and c, the following conditions must hold:
     *   a + b > c, a + c > b, and b + c > a.
     * - By sorting the array, we can simplify the checking process. For any given third side `nums[i]`, 
     *   we only need to find pairs (nums[s], nums[e]) such that nums[s] + nums[e] > nums[i].
     * - If nums[s] + nums[e] > nums[i], all pairs between s and e will satisfy the condition because 
     *   the array is sorted.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the array. Sorting the array takes O(n log n), and the two-pointer 
     *   technique iterates over the array in O(n^2) time.
     * 
     * Space Complexity:
     * - O(1), since we are only using a few extra variables to track the pointers and the count.
     * 
     * @param nums The input array of numbers.
     * @return The number of valid triangles that can be formed.
     */
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums); // Sort the array first
        int n = nums.length;
        int count = 0;

        // Iterate from the largest element to the smallest
        for (int i = n - 1; i >= 2; i--) {
            int s = 0;
            int e = i - 1;
            // Use two-pointer technique to find valid pairs for the current nums[i]
            while (s < e) {
                int sum = nums[s] + nums[e];
                if (sum > nums[i]) {
                    count += e - s; // All pairs between s and e are valid
                    e--; // Move the right pointer to the left
                } else {
                    s++; // Move the left pointer to the right
                }
            }
        }
        return count;
    }
}
