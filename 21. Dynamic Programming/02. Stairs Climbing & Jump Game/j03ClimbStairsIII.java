/**
 * Climbing Stairs III - Variable Jumps (Count Ways)
 * 
 * Problem Statement:
 *     You are given an integer array jumps where jumps[i] denotes the maximum
 *     number of steps you can jump forward from index i on a staircase of size
 *     n = jumps.length. Starting at index 0, in how many distinct ways can you
 *     reach the last index (n-1)? You may take any number of steps between 1
 *     and jumps[i] inclusive from position i, provided you do not go out of
 *     bounds.
 * 
 * Input:
 *     - jumps: int[] (length >= 1, jumps[i] >= 0)
 * 
 * Output:
 *     - The number of distinct ways to reach the last index from index 0
 * 
 * Example:
 *     jumps = [2,1,0]
 *     Output = 2
 * 
 *     Explanation:
 *     From index 0 you can jump:
 *     - 1 step to index 1, then 1 step to index 2 (destination)
 *     - 2 steps directly to index 2 (destination)
 */

import java.util.Arrays;

public class j03ClimbStairsIII {
	/**
	 * Approach: DFS + Memoization (Top-Down Dynamic Programming)
	 * 
	 * Intuition:
	 * - From any index i, you may jump to i+1, i+2, ..., up to i+jumps[i]. The
	 *   total ways from i is the sum of ways from all reachable next indices.
	 * - Let ways(i) denote number of ways to reach the last index from i.
	 *   Then: ways(i) = sum_{k=1..jumps[i]} ways(i+k), with base ways(n-1) = 1.
	 * - We memoize results to avoid recomputation of overlapping subproblems.
	 * 
	 * Explanation:
	 * - Step 1: Validate input and initialize a DP array (memo) with -1 values.
	 * - Step 2: Recursively compute from index 0 using memoization.
	 * - Step 3: Base case returns 1 when we reach the destination index n-1.
	 * - Step 4: For index i, iterate over all legal jumps and sum ways of next
	 *           positions, storing the result in dp[i].
	 * 
	 * Time Complexity: O(n^2) in the worst case (for each i we may explore up to
	 *                  jumps[i] next positions; with memoization each i is solved
	 *                  once, summing over all outgoing edges).
	 * Space Complexity: O(n) for the memo array and recursion depth in the worst
	 *                   case.
	 * 
	 * @param jumps   Array where jumps[i] is the maximum jump length from i
	 * @param n       Length of the staircase (should be jumps.length)
	 * @return        Number of distinct ways to reach the last index
	 */
	public static int countWays(int[] jumps, int n){
		// Validate input; if invalid or empty, there are 0 ways
		if (jumps == null || jumps.length == 0) return 0;
		// Prefer the actual array length to avoid mismatch issues
		final int size = jumps.length;
		
		// Initialize memo array with -1 indicating uncomputed states
		int[] dp = new int[size];
		Arrays.fill(dp, -1);
		
		// Compute number of ways starting from index 0
		return CountWaysDP(jumps, 0, dp);
	}

	/**
	 * Helper Method: DFS + Memoization
	 * 
	 * Intuition:
	 * - From current index currIndex, sum the ways of all reachable next indices
	 *   within bounds (currIndex + 1) to (currIndex + jumps[currIndex]).
	 * - Use dp[currIndex] to cache the computed number of ways.
	 * 
	 * Explanation:
	 * - Step 1: If at the last index, return 1 (we found one valid way).
	 * - Step 2: If already computed (dp[currIndex] != -1), return memoized value.
	 * - Step 3: Otherwise, iterate all valid jump sizes and accumulate answers.
	 * - Step 4: Store the computed result in dp[currIndex] and return it.
	 * 
	 * Time Complexity: O(outdegree(currIndex)) per uncached state; total across
	 *                  all states is O(n^2) in the worst case.
	 * Space Complexity: O(n) recursion depth in the worst case.
	 * 
	 * @param jumps      Array of maximum jump lengths
	 * @param currIndex  Current staircase index
	 * @param dp         Memoization array storing ways from each index
	 * @return           Number of ways to reach the last index from currIndex
	 */
	public static int CountWaysDP(int[] jumps,int currIndex,int[] dp){
		// If we are at the destination (last index), count this as 1 way
		if(currIndex == jumps.length - 1){
			return 1;
		}

		// Return memoized result if available
		if(dp[currIndex] != -1){
			return dp[currIndex];
		}

		int numberOfWaysFromHere = 0; // Accumulator for ways from currIndex

		// Try all jump sizes from 1 up to jumps[currIndex]
		for(int step = 1; step <= jumps[currIndex]; step++){
			int nextIndex = currIndex + step; // Next index after jumping 'step' steps
			// Ensure next index stays within staircase bounds
			if(nextIndex < jumps.length){
				numberOfWaysFromHere += CountWaysDP(jumps, nextIndex, dp);
			}
		}

		// Memoize and return the computed number of ways
		return dp[currIndex] = numberOfWaysFromHere;
	}

	/**
	 * Approach 2: Dynamic Programming with Tabulation (Bottom-Up)
	 * 
	 * Intuition:
	 * - Instead of using recursion with memoization, we use iterative approach
	 * - We build the solution from bottom-up, starting from the destination
	 * - Each index i is computed using previously computed values from indices
	 *   i+1, i+2, ..., i+jumps[i] (within bounds)
	 * - This eliminates the overhead of recursive function calls
	 * - We iterate backwards from n-2 to 0, ensuring all dependencies are computed
	 * 
	 * Explanation:
	 * - Step 1: Initialize DP array with size jumps.length + 1 (extra space for safety)
	 * - Step 2: Fill array with -1 to mark uncomputed values
	 * - Step 3: Set base case: dp[n-1] = 1 (one way to reach destination from destination)
	 * - Step 4: Iterate backwards from n-2 to 0, computing each index using forward dependencies
	 * - Step 5: For each index i, sum up ways from all reachable next indices
	 * - Step 6: Return the final result dp[0] (ways to reach destination from start)
	 * 
	 * Time Complexity: O(n^2) - For each index i, we may explore up to jumps[i] next positions
	 * Space Complexity: O(n) - DP array to store results for each index
	 * 
	 * @param jumps   Array where jumps[i] is the maximum jump length from i
	 * @param n       Length of the staircase (should be jumps.length)
	 * @return        Number of distinct ways to reach the last index
	 */
	public static int countWaysTabulation(int[] jumps, int n){
		// Validate input; if invalid or empty, there are 0 ways
		if (jumps == null || jumps.length == 0) return 0;
		
		// Initialize DP array with size jumps.length + 1 for safety
		int[] dp = new int[jumps.length + 1];
		
		// Fill array with -1 to mark uncomputed values
		Arrays.fill(dp, -1);

		// Set base case: one way to reach destination from destination
		dp[jumps.length - 1] = 1;

		// Iterate backwards from second-to-last index to first index
		// This ensures all dependencies are computed before we need them
		for(int i = jumps.length - 2; i >= 0; i--){
			int waysFromCurrentIndex = 0; // Accumulator for ways from index i
			
			// Try all possible jump sizes from 1 to jumps[i]
			for(int jumpSize = 1; jumpSize <= jumps[i]; jumpSize++){
				int nextIndex = i + jumpSize; // Next index after jumping
				
				// Ensure next index stays within bounds
				if(nextIndex < jumps.length){
					waysFromCurrentIndex += dp[nextIndex]; // Add ways from next index
				}
			}
			
			// Store computed ways for index i
			dp[i] = waysFromCurrentIndex;
		}
		
		// Return ways to reach destination from starting index 0
		return dp[0];
	}



	public static void main(String[] args) {
		// Basic Test Cases
		System.out.println("Basic Test Cases:");
		System.out.println("Input: [1], Expected: 1, Output: " + countWays(new int[]{1}, 1));
		System.out.println("Input: [1,0], Expected: 1, Output: " + countWays(new int[]{1,0}, 2));
		System.out.println("Input: [2,1,0], Expected: 2, Output: " + countWays(new int[]{2,1,0}, 3));
		System.out.println("Input: [3,2,0,1,2], Expected: 2, Output: " + countWays(new int[]{3,2,0,1,2}, 5));

		// Edge Cases
		System.out.println("\nEdge Cases:");
		System.out.println("Input: [], Expected: 0, Output: " + countWays(new int[]{}, 0));
		System.out.println("Input: null, Expected: 0, Output: " + countWays(null, 0));
		System.out.println("Input: [0,0,0], Expected: 0, Output: " + countWays(new int[]{0,0,0}, 3));

		// Boundary/Complex Cases
		System.out.println("\nBoundary/Complex Cases:");
		System.out.println("Input: [2,2,2,2], Expected: 8, Output: " + countWays(new int[]{2,2,2,2}, 4));
		System.out.println("Input: [3,3,0,2,2,3], Expected: 5, Output: " + countWays(new int[]{3,3,0,2,2,3}, 6));
		System.out.println("Input: [4,0,0,0,0], Expected: 1, Output: " + countWays(new int[]{4,0,0,0,0}, 5));

        // Boundary/Complex Cases
		System.out.println("\nBoundary/Complex Cases:");
		System.out.println("Input: [1, 3, 5, 8, 9, 1, 0, 7, 6, 8, 9], Expected: 52 , Output: " + countWays(new int[]{1, 3, 5, 8, 9, 1, 0, 7, 6, 8, 9}, 11));
		// Special Cases
		System.out.println("\nSpecial Cases:");
		System.out.println("Input: [0], Expected: 1, Output: " + countWays(new int[]{0}, 1)); // already at destination
		System.out.println("Input: [2,0,2,0], Expected: 1, Output: " + countWays(new int[]{2,0,2,0}, 4));
	}
}
