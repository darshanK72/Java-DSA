/**
 * Problem Statement:
 * 
 *     Given an integer array `citations` where `citations[i]` represents the number 
 *     of citations a researcher has received for their `i-th` paper, return the 
 *     researcher's H-Index.
 * 
 *     The H-Index is defined as the maximum value `h` such that the researcher 
 *     has at least `h` papers with at least `h` citations each.
 * 
 * Input:
 *     - `citations`: An integer array (1 <= citations.length <= 5000, 0 <= citations[i] <= 1000).
 * 
 * Output:
 *     - An integer representing the H-Index.
 * 
 * Example:
 *     Input:
 *         citations = [3, 0, 6, 1, 5]
 *     Output:
 *         3
 * 
 *     Explanation:
 *         - The researcher has 5 papers with citations [3,0,6,1,5].
 *         - Sorting gives [0,1,3,5,6].
 *         - The H-Index is 3 because the researcher has 3 papers with at least 3 citations.
 */

import java.util.Arrays;

public class j06HIndexI {

    public static void main(String[] args) {
        int[][] testCases = {
                { 3, 0, 6, 1, 5 },
                { 1, 3, 1 },
                { 0, 0, 0, 0 },
                { 10, 8, 5, 4, 3 },
                { 25, 8, 5, 3, 3 },
                { 4, 4, 0, 0 }
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("  Sorting Approach H-Index: " + hIndexSorting(testCases[i]));
            System.out.println("  Counting Sort Approach H-Index: " + hIndexCountingSort(testCases[i]));
            System.out.println();
        }
    }

    /**
     * Approach 1: Sorting-Based Approach
     * 
     * Intuition:
     * - Sort the citations in ascending order.
     * - Iterate from the highest citation downward and count how many papers 
     *   have at least `h` citations.
     * - The first time we find a paper where `citations[i] < h`, we stop.
     * 
     * Explanation:
     * - Sorting allows us to directly count how many papers have `h` or more citations.
     * - Since we iterate from the highest value downwards, we ensure the H-Index constraint.
     * 
     * Time Complexity:
     * - O(n log n) due to sorting.
     * 
     * Space Complexity:
     * - O(1) since sorting is done in place.
     * 
     * @param citations The array of citation counts.
     * @return The computed H-Index.
     */
    public static int hIndexSorting(int[] citations) {
        Arrays.sort(citations);
        int h = 1;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= h) {
                h++;
            } else {
                break;
            }
        }
        return h - 1;
    }

    /**
     * Approach 2: Counting Sort-Based Approach (Optimized)
     * 
     * Intuition:
     * - Instead of sorting, use a counting array to track citation frequencies.
     * - The key observation is that citations beyond `n` can be treated as `n` (upper bound).
     * - Iterate from `n` downward to find the maximum `h` where at least `h` papers exist.
     * 
     * Explanation:
     * - Construct a `count` array of size `n + 1`, where `count[i]` stores the number 
     *   of papers with exactly `i` citations (or more for `count[n]`).
     * - Traverse from `n` to 0, maintaining a cumulative count of papers.
     * - The first index where cumulative count `>= h` gives the H-Index.
     * 
     * Time Complexity:
     * - O(n), since we iterate through `citations` and `count[]` at most twice.
     * 
     * Space Complexity:
     * - O(n) due to the `count` array.
     * 
     * @param citations The array of citation counts.
     * @return The computed H-Index.
     */
    public static int hIndexCountingSort(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];

        for (int cit : citations) {
            count[Math.min(cit, n)]++;
        }

        int cumulativeH = 0;
        for (int h = n; h >= 0; h--) {
            cumulativeH += count[h];
            if (cumulativeH >= h) {
                return h;
            }
        }
        return 0;
    }
}
