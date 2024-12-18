/**
 * Problem Statement:
 * 
 *     Given an array `nums` of integers and an integer `k`, modify each element of the array in operations, 
 *     In one operation, you can choose any index i where 0 <= i < nums.length and change nums[i] to nums[i] + x 
 *     where x is an integer from the range [-k, k]. You can apply this operation at most once for each index i. 
 *     Return the smallest possible difference between the maximum.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `k` (0 <= k <= 10^4), representing the modification factor.
 * 
 * Output:
 *     - An integer representing the smallest possible difference between the maximum and minimum 
 *       values of the array after the modification.
 * 
 * Example:
 *     Input:
 *         n = 3
 *         nums = [1, 3, 6]
 *         k = 3
 *     Output:
 *         0
 * 
 *     Explanation:
 *         After modifying the array elements by adding or subtracting `k`, the array can become:
 *         4, 4, 4],[4, 6, 3], [2, 3, 3], or other variations.
 *         In all cases, the maximum difference between the maximum and minimum is 0.
 */

import java.util.Scanner;

public class j08SmallestRangeI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: the modification factor

        // Call the solution
        System.out.printf("Smallest Range (Approach): %d\n", smallestRangeI(arr, k));

        in.close();
    }

    /**
     * Approach: Simple Min-Max Adjustment
     * 
     * Intuition:
     * - To minimize the difference between the maximum and minimum values of the array,
     *   we reduce the difference by modifying each element by at most `k`.
     * - This means the new maximum of the array can be reduced by `k`, and the new minimum 
     *   can be increased by `k`. The potential new difference between the maximum and minimum 
     *   would then be:
     *      new_diff = (max - k) - (min + k) = (max - min) - 2 * k
     * - If `max - min <= 2 * k`, the difference becomes zero because the gap can be completely closed.
     * 
     * Explanation:
     * 1. Find the initial maximum (`max`) and minimum (`min`) of the array.
     * 2. Compute the initial difference, `diff = max - min`.
     * 3. Modify both `max` and `min` to account for the maximum possible reduction:
     *    - The maximum can be reduced by `k`, and the minimum can be increased by `k`.
     *    - The reduced difference becomes `diff - 2 * k`.
     * 4. If the initial difference (`diff`) is smaller than or equal to `2 * k`, we can completely 
     *    close the gap by modifying the elements appropriately. Otherwise, we return the reduced difference.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array, as we traverse the array once to find the min and max.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @param k The modification factor.
     * @return The smallest possible difference between the maximum and minimum values of the array.
     */
    public static int smallestRangeI(int[] nums, int k) {
        int max = 0;
        int min = 100000;

        // Step 1: Find the maximum and minimum elements in the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
            if (nums[i] < min)
                min = nums[i];
        }

        // Step 2: Calculate the initial difference
        int diff = max - min;

        // Step 3: Calculate the reduced difference
        // If the difference can be fully eliminated
        if (2 * k >= diff)
            return 0;
        else
            return diff - 2 * k; // Reduced difference after accounting for modifications
    }
}
