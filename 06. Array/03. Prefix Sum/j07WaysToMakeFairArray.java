
/**
 * Problem Statement:
 * 
 *     Given an array of integers, we need to determine how many ways we can remove one element from 
 *     the array such that the sum of the elements at even indices equals the sum of the elements at odd 
 *     indices in the remaining array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the number of ways to make the array fair by removing one element.
 * 
 * Example:
 *     Input:
 *     4
 *     2 1 6 4
 *     Output:
 *     1
 * 
 *     Explanation:
 *     - If we remove the element at index 2 (i.e., 6), the resulting array becomes [2, 1, 4], which has an equal sum of elements at even and odd indices: 2 + 4 = 1.
 */

import java.util.Scanner;

public class j07WaysToMakeFairArray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Output: Call the solution method and print the result
        System.out.println(waysToMakeFair(arr));
        in.close();
    }

    /**
     * Approach 1: Brute Force with Prefix Sum
     * 
     * Intuition:
     * - We can calculate the prefix sum of the even-indexed and odd-indexed elements separately.
     * - Then, for each element, we calculate if removing that element would make the sum of even and odd indexed elements equal.
     * - This is done by using the precomputed prefix sums for even and odd indexed elements.
     * 
     * Time Complexity:
     * - O(n) for prefix sum calculation and checking for each element.
     * 
     * Space Complexity:
     * - O(n) for storing the prefix sums.
     * 
     * @param nums The input array of numbers.
     * @return The number of ways to make the array fair by removing one element.
     */
    public static int waysToMakeFair(int[] nums) {
        int n = nums.length;

        // Prefix sum arrays for even and odd indexed positions
        int[] evenSum = new int[n];
        int[] oddSum = new int[n];

        // Initialize the prefix sums for the first element
        evenSum[0] = nums[0];
        oddSum[0] = 0;

        // Calculate prefix sums for even and odd indices separately
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                evenSum[i] = nums[i] + evenSum[i - 1];
                oddSum[i] = oddSum[i - 1];
            } else {
                oddSum[i] = nums[i] + oddSum[i - 1];
                evenSum[i] = evenSum[i - 1];
            }
        }

        // Variable to store the number of ways to make the array fair
        int ans = 0;

        // Iterate through the array and check if removing each element results in a
        // fair array
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (oddSum[n - 1] == evenSum[n - 1] - nums[i])
                    ans++;
            } else {
                int currentEvenSum = evenSum[i - 1] + oddSum[n - 1] - oddSum[i];
                int currentOddSum = oddSum[i - 1] + evenSum[n - 1] - evenSum[i];
                if (currentEvenSum == currentOddSum)
                    ans++;
            }
        }

        // Return the final count of ways
        return ans;
    }

    /**
     * Approach 2: Optimized Prefix Sum Calculation
     * 
     * Intuition:
     * - Instead of calculating prefix sums for every possible combination, we can maintain running sums 
     *   of odd and even indexed elements and update them as we iterate through the array.
     * - This will reduce redundant calculations.
     * 
     * Time Complexity:
     * - O(n), as we only need to iterate over the array once.
     * 
     * Space Complexity:
     * - O(1), since we do not need additional space other than a few variables for running sums.
     * 
     * @param nums The input array of numbers.
     * @return The number of ways to make the array fair by removing one element.
     */
    public static int optimizedWaysToMakeFair(int[] nums) {
        int n = nums.length;
        int evenSum = 0, oddSum = 0;
        int totalEvenSum = 0, totalOddSum = 0;

        // Calculate the total sum of even and odd indexed elements
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalEvenSum += nums[i];
            } else {
                totalOddSum += nums[i];
            }
        }

        int ans = 0;

        // Iterate through the array and check if removing each element results in a
        // fair array
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalEvenSum -= nums[i];
            } else {
                totalOddSum -= nums[i];
            }

            if (evenSum + totalOddSum == oddSum + totalEvenSum) {
                ans++;
            }

            if (i % 2 == 0) {
                evenSum += nums[i];
            } else {
                oddSum += nums[i];
            }
        }

        return ans;
    }
}
