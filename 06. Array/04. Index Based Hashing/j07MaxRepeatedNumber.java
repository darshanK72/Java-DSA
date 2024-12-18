/**
 * Problem Statement:
 * 
 *     Given an array `arr` of size `n` where elements are in the range [0, k-1],
 *     find the maximum repeated number. That is, find the number that occurs
 *     the maximum number of times in the array. If there are multiple such numbers,
 *     return any one of them.
 * 
 * Input:
 *     - An integer `k` (1 <= k <= n), representing the range of elements [0, k-1].
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (0 <= arr[i] < k).
 * 
 * Output:
 *     - An integer representing the number that occurs the maximum number of times.
 * 
 * Example:
 *     Input:
 *         k = 5
 *         n = 6
 *         arr = [2, 3, 2, 3, 5, 2]
 *     Output:
 *         2
 * 
 *     Explanation:
 *         The number 2 occurs 3 times, which is the highest count.
 */

import java.util.Scanner;

public class j07MaxRepeatedNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int k = in.nextInt(); // Input: range of elements [0, k-1]
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution
        System.out.println("Maximum Repeated Number: " + maxRepeating(arr, k));
        in.close();
    }

    /**
     * Approach:
     * 
     * Intuition:
     * - To find the maximum repeated number efficiently, we can use a counting 
     *   technique by modifying the input array. Since the numbers are in the 
     *   range [0, k-1], we can use the array indices as markers.
     * - For each number in the array, we increment the value at the index 
     *   corresponding to that number. By dividing each value by `n`, we can 
     *   determine the count of occurrences of that index.
     * 
     * Explanation:
     * - We iterate through the array, adding `n` to the value at the index 
     *   corresponding to the current element. This allows us to store both 
     *   the original value and the count at each index.
     * - After updating, the count for each number can be found as 
     *   `arr[i] / n` where `n` is the size of the array.
     * - Finally, we iterate through the array to find the index with the 
     *   maximum count, which represents the most frequently occurring number.
     * 
     * Time Complexity:
     * - O(n): We traverse the array twice - once for marking counts and once 
     *   for finding the maximum count.
     * 
     * Space Complexity:
     * - O(1): We perform the operations in-place using the input array.
     * 
     * @param arr The input array of numbers.
     * @param k   The range of elements [0, k-1].
     * @return The maximum repeated number.
     */
    public static int maxRepeating(int[] arr, int k) {
        int n = arr.length;

        // Step 1: Mark counts using in-place indexing
        for (int i = 0; i < n; i++) {
            int original = arr[i] % n; // Retrieve original value
            arr[original] += n; // Add n to the corresponding index
        }

        // Step 2: Find the index with the maximum count
        int maxRepeatingNum = -1;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int count = arr[i] / n; // Extract count for index i
            if (count > maxCount) {
                maxRepeatingNum = i; // Update maximum repeating number
                maxCount = count; // Update maximum count
            }
        }

        return maxRepeatingNum; // Return the result
    }
}
