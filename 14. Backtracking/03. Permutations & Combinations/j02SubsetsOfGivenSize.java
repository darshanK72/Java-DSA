/**
 * Problem Statement:
 * 
 *     Given an array of `n` distinct integers and an integer `k`, generate all possible 
 *     subsets of size `k` from the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of `n` distinct integers.
 *     - An integer `k` (1 <= k <= n), representing the subset size.
 * 
 * Output:
 *     - A list of lists, where each list contains a valid subset of `k` elements.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     2
 * 
 *     Output:
 *     [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
 * 
 *     Explanation:
 *     - The subsets of size `2` chosen from {1, 2, 3, 4} are listed above.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j02SubsetsOfGivenSize {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int k = in.nextInt();

        List<List<Integer>> set1 = new ArrayList<>();
        generateCombinations1(arr, 0, k, new ArrayList<>(), set1);
        System.out.println("Approach 1 Output: " + set1);

        List<List<Integer>> set2 = new ArrayList<>();
        generateCombinations2(arr, 0, arr.length, k, new ArrayList<>(), set2);
        System.out.println("Approach 2 Output: " + set2);

        in.close();
    }

    /**
     * Approach 1: Backtracking using Index Traversal
     * 
     * Intuition:
     * - We explore subsets by either including or excluding the current element at index `i`.
     * - If we include `arr[i]`, we decrease `k` and move to the next index.
     * - If we exclude `arr[i]`, we simply move to the next index.
     * 
     * Explanation:
     * - If `k == 0`, we have selected `k` elements and add the subset to the result.
     * - If `i == arr.length`, we stop as we have exhausted all elements.
     * - We make two recursive calls:
     *   1. Include `arr[i]` in the subset and recurse with `i+1, k-1`.
     *   2. Exclude `arr[i]` and recurse with `i+1, k`.
     * 
     * Time Complexity:
     * - O(2^n) in the worst case.
     * 
     * Space Complexity:
     * - O(k) due to recursion depth.
     */
    public static void generateCombinations1(int[] arr, int i, int k, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }
        if (i == arr.length) {
            return;
        }

        // Include `arr[i]`
        curr.add(arr[i]);
        generateCombinations1(arr, i + 1, k - 1, curr, set);
        curr.remove(curr.size() - 1);

        // Exclude `arr[i]`
        generateCombinations1(arr, i + 1, k, curr, set);
    }

    /**
     * Approach 2: Backtracking using Increasing Start Index
     * 
     * Intuition:
     * - Instead of making explicit include/exclude calls, we iterate through the array.
     * - We start from index `s` and generate subsets by choosing `k` elements.
     * - This ensures that we only move forward, preventing duplicate subsets.
     * 
     * Explanation:
     * - If `k == 0`, we have found a valid subset.
     * - We iterate from `s` to `n`, adding each number to the current subset.
     * - We recursively explore the next elements with `i+1` to maintain order.
     * - After recursion, we remove the last element to backtrack.
     * 
     * Time Complexity:
     * - O(C(n, k)) = O(n! / (k!(n-k)!)) (binomial coefficient).
     * 
     * Space Complexity:
     * - O(k) due to recursion depth.
     */
    public static void generateCombinations2(int[] arr, int s, int n, int k, ArrayList<Integer> curr,
            List<List<Integer>> set) {
        if (k == 0) {
            set.add(new ArrayList<>(curr));
            return;
        }

        for (int i = s; i < n; i++) {
            curr.add(arr[i]);
            generateCombinations2(arr, i + 1, n, k - 1, curr, set);
            curr.remove(curr.size() - 1);
        }
    }
}
