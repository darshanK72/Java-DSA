/**
 * Problem Statement:
 * 
 *     Given an unsorted integer array `nums`, find the smallest missing positive integer.
 *     Your algorithm should run in O(n) time and use constant space.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (−2^31 <= nums[i] <= 2^31 − 1).
 * 
 * Output:
 *     - The smallest missing positive integer.
 * 
 * Example:
 *     Input:
 *         n = 5, arr = [1, 2, 0]
 *     Output:
 *         3
 * 
 *     Explanation:
 *         - The numbers in the range [1, 3] are {1, 2, 3}.
 *         - The smallest missing positive integer is 3.
 */

import java.util.Scanner;

public class j03FirstMissingPositive {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Calling the method to find the first missing positive integer
        System.out.println(firstMissingPositive(arr)); // Calling the method and printing the result
        in.close();
    }

    /**
     * Approach: Index-Based Hashing
     * 
     * Intuition:
     * - We need to find the smallest missing positive integer, and the array elements are within the range [1, n].
     * - The idea is to use the array itself to track which positive numbers are present by placing each number in its correct position.
     * - This can be done by treating the array indices as "buckets" to place the numbers in the range [1, n].
     * - If a number is out of range or already placed in its correct position, it will be ignored.
     * 
     * Explanation:
     * - First, we ensure all elements in the array are within the range [1, n] and set invalid numbers (those outside this range) to 0.
     * - Next, we iterate through the array, and for each valid number `nums[i]`, we place it in the position `nums[nums[i] - 1]` by marking it with a modification.
     * - Finally, we iterate over the array again and check for the first index that does not have its correct value, which corresponds to the smallest missing positive integer.
     * - If all numbers are correctly placed, then the missing number is `n + 1`.
     * 
     * Time Complexity:
     * - O(n): We traverse the array a few times, and each traversal is O(n).
     * 
     * Space Complexity:
     * - O(1): We modify the input array in-place and use no extra space apart from the output.
     * 
     * @param nums The input array.
     * @return The smallest missing positive integer.
     */
    public static int firstMissingPositive(int[] nums) {
        // Step 1: Replace all out-of-range numbers with 0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 || nums[i] > nums.length) {
                nums[i] = 0; // Invalid values are set to 0
            }
        }

        // Step 2: Mark the presence of numbers in the correct index positions
        for (int i = 0; i < nums.length; i++) {
            int original = nums[i] % (nums.length + 1); // Find the value in a range-safe way
            if (original > 0) {
                nums[original - 1] += (nums.length + 1); // Mark the index corresponding to the value
            }
        }

        // Step 3: Identify the first missing positive integer
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] / (nums.length + 1) == 0) {
                return (i + 1); // The first index with an unchanged value is the missing number
            }
        }

        // If all numbers from 1 to n are present, return n + 1
        return nums.length + 1;
    }
}
