/**
 * Problem Statement:
 *
 *     Given an array `nums` of integers, rearrange the array such that positive numbers and negative 
 *     numbers are alternatively placed. The rearranged array should start with a positive number, followed 
 *     by a negative number, and so on.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums[]` of size `n` where each element is an integer.
 *
 * Output:
 *     - A new array `out[]` where positive and negative numbers alternate.
 *     - The relative order of positive and negative numbers should be preserved.
 *
 * Example:
 *     Input:
 *     6
 *     1 -2 3 -4 5 -6
 *     Output:
 *     1 -2 3 -4 5 -6
 *
 *     Explanation:
 *     The rearranged array contains positive and negative numbers alternating.
 */

import java.util.Scanner;

public class j05RearrangeArrayBySign {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the rearrangement methods
        int[] rearranged = rearrangeArray(arr);
        for (int num : rearranged) {
            System.out.print(num + " ");
        }
        System.out.println();

        rearranged = rearrangeArrayEfficient(arr);
        for (int num : rearranged) {
            System.out.print(num + " ");
        }

        in.close();
    }

    /**
     * Approach 1: Naive Approach (O(n))
     *
     * Intuition:
     * - We separate the positive and negative numbers into two parts.
     * - First, we fill the even indices with positive numbers and then the odd indices with negative numbers.
     * - This is done by iterating through the array twice: once for positive numbers and once for negative numbers.
     *
     * Time Complexity:
     * - O(n), where n is the size of the array, because we iterate through the array twice.
     *
     * Space Complexity:
     * - O(n), as we use an additional array to store the rearranged numbers.
     *
     * @param nums The input array of integers.
     * @return The rearranged array with alternating positive and negative numbers.
     */
    public static int[] rearrangeArray(int[] nums) {
        int[] out = new int[nums.length]; // Output array to store the result
        int k = 0; // Pointer for positive numbers
        // Place positive numbers at even indices
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                out[k] = nums[i];
                k += 2;
            }
        }

        k = 1; // Pointer for negative numbers
        // Place negative numbers at odd indices
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                out[k] = nums[i];
                k += 2;
            }
        }

        return out;
    }

    /**
     * Approach 2: Efficient Approach (O(n))
     *
     * Intuition:
     * - This approach optimizes the arrangement process by using two pointers: `k` for positive numbers 
     *   and `l` for negative numbers.
     * - We place positive numbers at even indices and negative numbers at odd indices while iterating 
     *   through the array once.
     * - This minimizes the number of iterations and avoids the need for a second pass through the array.
     *
     * Time Complexity:
     * - O(n), where n is the size of the array, as we only iterate through the array once.
     *
     * Space Complexity:
     * - O(n), as we still use an additional array for storing the result.
     *
     * @param nums The input array of integers.
     * @return The rearranged array with alternating positive and negative numbers.
     */
    public static int[] rearrangeArrayEfficient(int[] nums) {
        int[] out = new int[nums.length]; // Output array to store the result
        int k = 0; // Pointer for positive numbers
        int l = 1; // Pointer for negative numbers
        // Place positive and negative numbers at alternating positions
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                out[k] = nums[i];
                k += 2; // Increment the pointer for positive numbers
            } else {
                out[l] = nums[i];
                l += 2; // Increment the pointer for negative numbers
            }
        }

        return out;
    }
}
