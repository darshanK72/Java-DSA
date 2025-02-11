/**
 * Problem Statement:
 * 
 *      Given an array of integers, find all possible subset sums. A subset sum is 
 *      the sum of elements from any subset (including the empty subset). The output 
 *      should be a list containing all possible sums, including duplicates.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A list of integers containing all possible subset sums.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 * 
 *     Output:
 *     [0, 1, 2, 3, 3, 4, 5, 6]
 * 
 *     Explanation:
 *     - The subsets are: {}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}.
 *     - Their sums are: 0, 1, 2, 3, 3, 4, 5, 6.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j05SubsetSums {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Approach 1: Brute Force Recursive Approach
        ArrayList<Integer> set1 = new ArrayList<>();
        set1.add(0);
        generateSubsetSum(arr, 0, 0, set1);
        System.out.println(set1);

        // Approach 2: Optimized Recursive Approach
        ArrayList<Integer> set2 = new ArrayList<>();
        generateSubsetSums(arr, 0, 0, set2);
        System.out.println(set2);

        in.close();
    }

    /**
     * Approach 1: Brute Force Recursive Approach
     * 
     * Intuition:
     * - The problem requires us to find the sum of all subsets of a given array.
     * - A subset can be formed by either including or excluding each element in the array.
     * - This suggests a recursive solution, where we make a choice at each step:
     *   1. Include the current element in the sum.
     *   2. Exclude the current element and move to the next.
     * - By applying these choices recursively, we explore all possible subsets.
     * 
     * Explanation:
     * - We start with an empty subset and sum = 0.
     * - At each index `i`, we have two options:
     *   1. Add `arr[i]` to the sum and recurse on the remaining elements.
     *   2. Ignore `arr[i]` and recurse on the remaining elements.
     * - The base case is when we reach the end of the array, at which point we store the sum.
     * - This method generates all possible subset sums but may produce duplicate sums.
     * 
     * Time Complexity:
     * - O(2^n), as we explore all subsets.
     * 
     * Space Complexity:
     * - O(2^n), due to recursion stack and subset storage.
     * 
     * @param arr The input array.
     * @param index The current index in recursion.
     * @param currSum The current subset sum.
     * @param set The list storing subset sums.
     */
    public static void generateSubsetSum(int[] arr, int index, int currSum, ArrayList<Integer> set) {
        if (index == arr.length)
            return;

        // Include current element in sum
        currSum += arr[index];
        set.add(currSum);

        // Recurse to next index
        generateSubsetSum(arr, index + 1, currSum, set);

        // Backtrack: Remove the current element before moving to the next case
        currSum -= arr[index];

        // Exclude current element and recurse
        generateSubsetSum(arr, index + 1, currSum, set);
    }

    /**
     * Approach 2: Optimized Recursive Approach (Backtracking)
     * 
     * Intuition:
     * - The brute force approach generates subsets in an inefficient way.
     * - Instead, we can use a cleaner recursion that systematically explores:
     *   1. Including the current element in the subset.
     *   2. Excluding the current element in the subset.
     * - Instead of handling backtracking manually, we pass the current sum directly.
     * - This avoids unnecessary sum recalculations and simplifies the logic.
     * 
     * Explanation:
     * - We use a recursive function that starts with sum = 0.
     * - At each index `i`, we:
     *   1. Include `arr[i]` in the sum and recurse.
     *   2. Exclude `arr[i]` and recurse.
     * - The base case is when `index == arr.length`, where we add the sum to the list.
     * - This approach generates all valid subset sums more efficiently.
     * 
     * Time Complexity:
     * - O(2^n), as we generate all subsets.
     * 
     * Space Complexity:
     * - O(2^n), due to recursion depth and storing results.
     * 
     * @param arr The input array.
     * @param index The current index in recursion.
     * @param currSum The current subset sum.
     * @param set The list storing subset sums.
     */
    public static void generateSubsetSums(int[] arr, int index, int currSum, ArrayList<Integer> set) {
        if (index == arr.length) {
            set.add(currSum);
            return;
        }

        // Include the current element
        generateSubsetSums(arr, index + 1, currSum + arr[index], set);

        // Exclude the current element
        generateSubsetSums(arr, index + 1, currSum, set);
    }
}
