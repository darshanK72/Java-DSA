/**
 * GeeksForGeeks. Count Ways to Partition Array Into K Subsets
 * 
 * Problem Statement:
 *     Given n distinct elements, count the number of ways to partition them 
 *     into exactly k non-empty subsets. This is the Stirling number of the 
 *     second kind problem.
 * 
 * Input:
 *     - n (1 <= n <= 20): number of distinct elements
 *     - k (1 <= k <= n): number of subsets to partition into
 * 
 * Output:
 *     - Integer representing number of ways to partition n elements into k subsets
 * 
 * Example:
 *     Input: n = 3, k = 2
 *     Output: 3
 * 
 *     Explanation:
 *     For 3 elements {1, 2, 3} partitioned into 2 subsets:
 *     - {1}, {2, 3}
 *     - {2}, {1, 3}  
 *     - {3}, {1, 2}
 *     Total ways = 3
 */

import java.util.Arrays;

public class j08CountWaysToPartitionArrayIntoKSubsets {

    /**
     * Approach: Dynamic Programming with Memoization (Stirling Numbers)
     * 
     * Intuition:
     * - This is a classic combinatorics problem known as Stirling numbers of the 
     *   second kind
     * - For each element, we have two choices: either put it in an existing 
     *   subset or create a new subset
     * - If we put element n in existing subset: we have k choices (any of k 
     *   subsets), so multiply by k
     * - If we put element n in new subset: we create exactly one new subset
     * - Base cases: can't partition if n < k, exactly 1 way if k=1 or n=k
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to track uncomputed states
     * - Step 2: Handle base cases where partitioning is impossible or trivial
     * - Step 3: For each element, calculate ways by considering both choices
     * - Step 4: Store result in DP table to avoid recomputation
     * - The recurrence: S(n,k) = k*S(n-1,k) + S(n-1,k-1)
     * 
     * Time Complexity: O(n*k) where n is number of elements, k is number of subsets
     * Space Complexity: O(n*k) for DP table storage
     * 
     * @param n    Number of distinct elements (1 <= n <= 20)
     * @param k    Number of subsets to partition into (1 <= k <= n)
     * @return     Number of ways to partition n elements into k subsets
     */
    public static int countWays(int n, int k) {
        // Initialize DP table with -1 to mark uncomputed states
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Call memoized recursive function to compute result
        return countWaysOfPartitionMemo(dp, n, k);
    }

    /**
     * Helper Method: Memoized Recursive Solution
     * 
     * Intuition:
     * - Uses top-down DP approach to compute Stirling numbers recursively
     * - Avoids recomputation by storing results in DP table
     * - Implements the mathematical recurrence relation for Stirling numbers
     * 
     * Explanation:
     * - Base case 1: If n=0 or k=0 or n<k, no valid partitioning exists
     * - Base case 2: If k=1 (all elements in one subset) or n=k (each element 
     *   in its own subset), exactly one way exists
     * - Recursive case: For element n, either add to existing subset (k choices) 
     *   or create new subset (1 choice)
     * - Store computed result to avoid redundant calculations
     * 
     * Time Complexity: O(n*k) due to memoization preventing redundant calls
     * Space Complexity: O(n*k) for DP table plus O(n) recursion stack
     * 
     * @param dp    Memoization table to store computed results
     * @param n     Number of elements remaining to partition
     * @param k     Number of subsets remaining to create
     * @return      Number of ways to partition n elements into k subsets
     */
    public static int countWaysOfPartitionMemo(int[][] dp, int n, int k) {
        // Base case: invalid partitioning scenarios
        if (n == 0 || k == 0 || n < k)
            return 0;
        
        // Base case: trivial partitioning scenarios
        if (k == 1 || n == k)
            return 1;
        
        // Return cached result if already computed
        if (dp[n][k] != -1)
            return dp[n][k];

        // Calculate ways by considering both choices for element n
        int share = countWaysOfPartitionMemo(dp, n - 1, k) * k;        // Add to existing subset
        int individual = countWaysOfPartitionMemo(dp, n - 1, k - 1);   // Create new subset

        // Store and return the computed result
        return dp[n][k] = share + individual;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=3, k=2, Expected: 3, Output: " + countWays(3, 2));
        System.out.println("Input: n=4, k=2, Expected: 7, Output: " + countWays(4, 2));
        System.out.println("Input: n=4, k=3, Expected: 6, Output: " + countWays(4, 3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, k=1, Expected: 1, Output: " + countWays(1, 1));
        System.out.println("Input: n=2, k=1, Expected: 1, Output: " + countWays(2, 1));
        System.out.println("Input: n=2, k=2, Expected: 1, Output: " + countWays(2, 2));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=5, k=5, Expected: 1, Output: " + countWays(5, 5));
        System.out.println("Input: n=5, k=1, Expected: 1, Output: " + countWays(5, 1));
        System.out.println("Input: n=3, k=4, Expected: 0, Output: " + countWays(3, 4));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=6, k=3, Expected: 90, Output: " + countWays(6, 3));
        System.out.println("Input: n=5, k=2, Expected: 15, Output: " + countWays(5, 2));
        System.out.println("Input: n=0, k=0, Expected: 0, Output: " + countWays(0, 0));
    }
}