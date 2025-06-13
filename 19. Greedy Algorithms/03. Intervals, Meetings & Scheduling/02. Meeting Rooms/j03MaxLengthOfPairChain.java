/**
 * LeetCode 646. Maximum Length of Pair Chain
 * 
 * Problem Statement:
 *     You are given an array of n pairs where pairs[i] = [lefti, righti] and lefti < righti.
 *     A pair p2 = [c, d] follows a pair p1 = [a, b] if and only if b < c. A chain of pairs can
 *     be formed in this fashion. Return the length of the longest chain which can be formed.
 *     You do not need to use up all the given pairs. You can select pairs in any order.
 * 
 * Input:
 *     - pairs: 2D array where pairs[i] = [lefti, righti]
 *     - 1 <= pairs.length <= 1000
 *     - -1000 <= lefti < righti <= 1000
 * 
 * Output:
 *     - Length of the longest chain of pairs
 * 
 * Example:
 *     Input: pairs = [[1,2],[2,3],[3,4]]
 *     Output: 2
 * 
 *     Explanation:
 *     The longest chain is [1,2] -> [3,4]
 *     Note: [2,3] cannot be included as it overlaps with [1,2]
 */

import java.util.Arrays;

public class j03MaxLengthOfPairChain {
    /**
     * Approach: Greedy with Sorting by End Time
     * 
     * Intuition:
     * - The key insight is to prioritize pairs that end earlier
     * - By sorting pairs by end time, we can maximize the chain length
     * - This ensures we don't block potential pairs that could be added later
     * 
     * Explanation:
     * 1. Sort pairs by end time in ascending order
     * 2. Keep track of the end time of the last pair in chain
     * 3. For each pair:
     *    - If current pair starts after last pair ends, add it to chain
     *    - Update last end time and increment chain length
     *    - Skip pairs that overlap with the last pair in chain
     * 
     * Time Complexity: O(n log n) where:
     *                  - n is the number of pairs
     *                  - Sorting takes O(n log n)
     *                  - Single pass through array takes O(n)
     * 
     * Space Complexity: O(1) as we only use a few variables
     * 
     * @param pairs  2D array of pairs [left, right]
     * @return      Length of the longest chain
     */
    public static int findLongestChain(int[][] pairs) {
        // Sort pairs by end time in ascending order
        Arrays.sort(pairs,(a,b) -> a[1] - b[1]);
        
        int length = 0;  // Length of the current chain
        int prevEnd = Integer.MIN_VALUE;  // End time of last pair in chain
        
        // Process each pair
        for(int i = 0; i < pairs.length; i++){
            // If current pair starts after last pair ends, add it to chain
            if(prevEnd < pairs[i][0]){
                length++;  // Increment chain length
                prevEnd = pairs[i][1];  // Update last end time
            }
        }

        return length;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with non-overlapping pairs
        System.out.println("\nTest Case 1:");
        int[][] pairs1 = {{1,2}, {2,3}, {3,4}};
        System.out.println("Input: " + Arrays.deepToString(pairs1));
        System.out.println("Output: " + findLongestChain(pairs1));
        System.out.println("Expected: 2");

        // Test Case 2: Overlapping pairs
        System.out.println("\nTest Case 2:");
        int[][] pairs2 = {{1,2}, {1,3}, {2,3}, {3,4}};
        System.out.println("Input: " + Arrays.deepToString(pairs2));
        System.out.println("Output: " + findLongestChain(pairs2));
        System.out.println("Expected: 2");

        // Test Case 3: Single pair
        System.out.println("\nTest Case 3:");
        int[][] pairs3 = {{1,2}};
        System.out.println("Input: " + Arrays.deepToString(pairs3));
        System.out.println("Output: " + findLongestChain(pairs3));
        System.out.println("Expected: 1");
    }
}
