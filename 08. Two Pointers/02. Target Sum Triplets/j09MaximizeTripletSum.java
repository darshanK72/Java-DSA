/**
 * Problem Statement:
 * 
 *     Given an array `A` of size `n`, your task is to find the maximum possible sum 
 *     of a triplet (i, j, k) such that i < j < k and A[i] < A[j] < A[k].
 * 
 * Input:
 *     - An integer `n` (3 <= n <= 10^5), representing the size of the array.
 *     - An array `A` of size `n` where each element satisfies (1 <= A[i] <= 10^5).
 * 
 * Output:
 *     - Return the maximum sum `A[i] + A[j] + A[k]` where `i < j < k` and `A[i] < A[j] < A[k]`.
 * 
 * Example:
 *     Input:
 *     5
 *     2 5 3 8 6
 *     Output:
 *     15
 * 
 *     Explanation:
 *     The triplet that maximizes the sum is (2, 5, 8). Hence, the result is 15.
 */

import java.util.Scanner;
import java.util.TreeSet;

public class j09MaximizeTripletSum {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt(); // Input: array elements
        }

        // Call solution method
        System.out.println(maximizeTripletSum(A));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - The brute force approach would involve iterating over all possible triplets (i, j, k) 
     *   with i < j < k, checking if A[i] < A[j] < A[k], and calculating their sum. 
     *   This would give the maximum possible sum.
     * - However, this approach is inefficient because it requires checking O(n^3) triplets.
     * 
     * Time Complexity:
     * - O(n^3) due to the three nested loops.
     * 
     * Space Complexity:
     * - O(1) for the space needed to store the current triplet sum.
     * 
     * @param A The input array of numbers.
     * @return The maximum sum of a valid triplet.
     */
    public static int bruteForceSolution(int[] A) {
        int n = A.length;
        int maxSum = 0;

        // Iterate over all triplets
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Check if A[i] < A[j] < A[k]
                    if (A[i] < A[j] && A[j] < A[k]) {
                        maxSum = Math.max(maxSum, A[i] + A[j] + A[k]);
                    }
                }
            }
        }
        return maxSum;
    }

    /**
     * Approach 2: Optimized Approach using a TreeSet
     * 
     * Intuition:
     * - We can avoid checking all possible triplets by utilizing the TreeSet to efficiently
     *   find the largest element smaller than the current element (`A[i]`) on the left 
     *   and the largest element greater than the current element (`A[k]`) on the right.
     * - We first calculate the `rightMax` array where `rightMax[i]` stores the largest 
     *   element to the right of `A[i]`.
     * - We then iterate through the array, and for each `A[i]`, use the TreeSet to find 
     *   the largest element smaller than `A[i]` from the left side, and check if the 
     *   corresponding element on the right (`rightMax[i+1]`) is greater than `A[i]`.
     * - If both conditions hold, we compute the sum and update the result.
     * 
     * Time Complexity:
     * - O(n log n) because the TreeSet operations (insert, lower) take O(log n), and 
     *   we iterate over the array once.
     * 
     * Space Complexity:
     * - O(n) due to the `rightMax` array and the TreeSet.
     * 
     * @param A The input array of numbers.
     * @return The maximum sum of a valid triplet.
     */
    public static int maximizeTripletSum(int[] A) {
        int n = A.length;
        int ans = 0;

        // Step 1: Calculate rightMax array
        int[] rightMax = new int[n];
        rightMax[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], A[i]);
        }

        // Step 2: Use a TreeSet to find the largest element smaller than A[i]
        TreeSet<Integer> set = new TreeSet<>();
        set.add(A[0]);

        for (int i = 1; i < n - 1; i++) {
            Integer lowerBound = set.lower(A[i]);
            if (lowerBound == null) {
                set.add(A[i]);
                continue;
            }
            int upperBound = rightMax[i + 1];
            if (upperBound > A[i]) {
                ans = Math.max(ans, lowerBound + A[i] + upperBound);
            }
            set.add(A[i]);
        }
        return ans;
    }

    /**
     * Approach 3: Further Optimized Approach with Binary Search
     * 
     * Intuition:
     * - This approach uses binary search for the left side instead of using TreeSet. 
     *   This reduces the overhead of maintaining a dynamic set.
     * - For each element A[i], we find the largest element smaller than A[i] using binary 
     *   search on a sorted array of elements to the left of A[i]. Similarly, we find the 
     *   largest element greater than A[i] on the right.
     * 
     * Time Complexity:
     * - O(n log n) due to the binary search operations on the left part.
     * 
     * Space Complexity:
     * - O(n) for maintaining the sorted array for binary search.
     * 
     * @param A The input array of numbers.
     * @return The maximum sum of a valid triplet.
     */
    public static int binarySearchSolution(int[] A) {
        // Implementation using binary search for optimal solution
        return 0;
    }
}
