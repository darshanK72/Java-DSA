/*-
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, find the maximum product of two elements such that the product is 
 *     calculated as `(arr[i] - 1) * (arr[j] - 1)` for some pair `(i, j)` where `i != j`.
 * 
 * Input:
 *     - An integer `n` (2 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The maximum product of the form `(arr[i] - 1) * (arr[j] - 1)` for some pair of distinct indices `i` and `j`.
 * 
 * Example:
 *     Input:
 *     5
 *     3 1 5 6 2
 *     Output:
 *     20
 *     Explanation:
 *     - The maximum product is (5-1) * (6-1) = 4 * 5 = 20.
 * 
 *     Input:
 *     3
 *     1 1 1
 *     Output:
 *     0
 *     Explanation:
 *     - The product will always be zero since (1-1) = 0.
 * 
 */

import java.util.Scanner;

public class j07MaxProductOfPair {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution methods
        System.out.println(maxProduct(arr)); // Brute force approach for maximum product
        System.out.println(maxProductEfficient(arr)); // Optimized approach for maximum product

        in.close();
    }

    /*-
     * Approach 1: Brute Force Approach (Nested Loops)
     * 
     * Intuition:
     * - We check all possible pairs `(i, j)` with `i != j` and compute `(arr[i] - 1) * (arr[j] - 1)` for each pair.
     * - Keep track of the maximum product found.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the array. We need to check all pairs.
     * 
     * Space Complexity:
     * - O(1), since we only use a constant amount of extra space.
     * 
     * @param nums The input array of numbers.
     * @return The maximum product of two elements in the array.
     */
    public static int maxProduct(int[] nums) {
        int maxProd = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int prod = (nums[i] - 1) * (nums[j] - 1);
                if (prod > maxProd) {
                    maxProd = prod;
                }
            }
        }
        return maxProd; // Return the maximum product
    }

    /*-
     * Approach 2: Optimized Approach (Find Maximum and Second Maximum)
     * 
     * Intuition:
     * - Instead of checking all pairs, we can find the two largest and two smallest numbers in the array.
     * - The maximum product can either come from the two largest numbers or the two smallest numbers (if they are negative).
     * - Compute the product of the two largest and the two smallest numbers, then return the larger of the two products.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We traverse the array once to find the maximum and second maximum, 
     *   and minimum and second minimum values.
     * 
     * Space Complexity:
     * - O(1), since only a constant amount of space is used for storing the maximum and minimum values.
     * 
     * @param nums The input array of numbers.
     * @return The maximum product of two elements in the array.
     */
    public static int maxProductEfficient(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        // Traverse the array to find the maximum, second maximum, minimum, and second
        // minimum values
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
            } else if (nums[i] <= max && nums[i] > secondMax) {
                secondMax = nums[i];
            }
            if (nums[i] < min) {
                secondMin = min;
                min = nums[i];
            } else if (nums[i] >= max && nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }

        // Calculate the product for the two largest and two smallest values
        int res = (max - 1) * (secondMax - 1);
        int res2 = (min - 1) * (secondMin - 1);

        // Return the maximum of the two products
        return (res > res2) ? res : res2;
    }
}
