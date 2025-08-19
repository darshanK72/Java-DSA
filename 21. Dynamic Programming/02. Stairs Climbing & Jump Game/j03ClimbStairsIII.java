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
	 * Approach [1]: Top-Down (Backward) Memoization
	 *
	 * Intuition:
	 * - From any index i, total ways to reach the last index equals the sum of
	 *   ways from all reachable indices i + step where 1 <= step <= jumps[i].
	 * - Cache results per index to avoid recomputation of overlapping problems.
	 *
	 * Explanation:
	 * - Validate inputs; if array is null/empty, answer is 0.
	 * - Allocate dp initialized to -1 representing unknown states.
	 * - Recurse from index 0; for each index, try all valid forward steps and
	 *   sum their results. Memoize before returning.
	 * - Base case: index == n - 1 returns 1.
	 *
	 * Time Complexity: O(S) where S is total considered edges; worst O(n^2).
	 * Space Complexity: O(n) for memo plus recursion stack in worst case.
	 *
	 * @param jumps  jumps[i] is max forward steps from index i (>= 0)
	 * @param n      array size hint; implementation prefers jumps.length
	 * @return       number of distinct ways to reach last index from 0
	 */
	public static int countWaysMemonizationBackword(int[] jumps, int n){
		// Validate input; if invalid or empty, there are 0 ways
		if (jumps == null || jumps.length == 0) return 0;
		// Prefer the actual array length to avoid mismatch issues
		final int size = jumps.length;
		
		// Initialize memo array with -1 indicating uncomputed states
		int[] dp = new int[size];
		Arrays.fill(dp, -1);
		
		// Compute number of ways starting from index 0
		return countWaysMemoizationHelper(jumps, 0, dp);
	}

	/**
	 * Helper: Backward Memoization
	 *
	 * Intuition:
	 * - Sum the ways reachable from currIndex by exploring all valid steps.
	 *
	 * Explanation:
	 * - If at last index, return 1.
	 * - If cached, return dp[currIndex]. Otherwise, iterate steps within bounds,
	 *   recurse, accumulate, store in dp, and return.
	 *
	 * Time Complexity: Same as parent approach; each state solved once.
	 * Space Complexity: O(n) due to memo and recursion depth.
	 *
	 * @param jumps       jumps array
	 * @param currIndex   current index
	 * @param dp          memoization array (-1 = unknown)
	 * @return            number of ways from currIndex to last index
	 */
	public static int countWaysMemoizationHelper(int[] jumps,int currIndex,int[] dp){
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
				numberOfWaysFromHere += countWaysMemoizationHelper(jumps, nextIndex, dp);
			}
		}

		// Memoize and return the computed number of ways
		return dp[currIndex] = numberOfWaysFromHere;
	}

	/**
	 * Approach [2]: Bottom-Up (Backward) Tabulation
	 *
	 * Intuition:
	 * - dp[i] = sum of dp[i + step] for all valid steps from i. Start from the
	 *   end where dp[n-1] = 1, move backward ensuring dependencies are ready.
	 *
	 * Explanation:
	 * - Initialize dp with -1 and set dp[n-1] = 1 (one way to be at destination).
	 * - For i from n-2 downto 0, aggregate ways from forward-reachable indices.
	 * - Return dp[0].
	 *
	 * Time Complexity: O(S) edges processed; worst-case O(n^2).
	 * Space Complexity: O(n) for the dp array.
	 *
	 * @param jumps  jumps[i] is max steps forward from index i
	 * @param n      size of the array (expected n == jumps.length)
	 * @return       number of ways from index 0 to last index
	 */
	public static int countWaysTabulationBackword(int[] jumps, int n){
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

	/**
	 * Approach [3]: Top-Down (Forward) Memoization
	 *
	 * Intuition:
	 * - Starting at index 0, push forward to all i + step within bounds and
	 *   count the ways from those indices; memoize per index to avoid repeats.
	 *
	 * Explanation:
	 * - Handle null/empty input. Initialize dp with -1. Recurse forward and
	 *   cache results to ensure each index is solved once.
	 *
	 * Time Complexity: O(S) edges considered; worst-case O(n^2).
	 * Space Complexity: O(n) for memo plus recursion stack.
	 *
	 * @param jumps  jumps[i] is max steps forward from index i
	 * @param n      size bound used for index checks
	 * @return       number of ways to reach last index from index 0
	 */
	public static int countWaysMemonizationForward(int[] jumps, int n){
		// Validate input; if invalid or empty, there are 0 ways
		if (jumps == null || jumps.length == 0) return 0;
		// Prefer the actual array length to avoid mismatch issues
		final int size = jumps.length;
		
		// Initialize memo array with -1 indicating uncomputed states
		int[] dp = new int[size];
		Arrays.fill(dp, -1);
		
		// Compute number of ways starting from index 0
		return countWaysMemonizationForwardHelper(jumps,n, 0, dp);
	}

	/**
	 * Helper: Forward Memoization
	 *
	 * Intuition:
	 * - Try all steps from current index and sum the memoized answers ahead.
	 *
	 * Explanation:
	 * - If at last index, return 1. If cached, return dp[index]. Otherwise loop
	 *   all valid steps, recurse, accumulate, memoize, and return.
	 *
	 * Time Complexity: Mirrors the parent forward memoization.
	 * Space Complexity: O(n) for memo and recursion depth.
	 *
	 * @param jumps  jumps array
	 * @param n      size bound
	 * @param index  current index
	 * @param dp     memo array storing ways from each index
	 * @return       number of ways from index to last index
	 */
	public static int countWaysMemonizationForwardHelper(int[] jumps, int n, int index, int[] dp){
		// If we are already at the last index, count this as one valid way
		if(index == n - 1){
			return 1;
		}

		// Return cached result if this subproblem was solved earlier
		if(dp[index] != -1){
			return dp[index];
		}

		int ways = 0; // Accumulate number of ways from current index
		// Try all jump sizes allowed from current index
		for(int step = 1; step <= jumps[index]; step++){
			// Ensure the next index stays within array bounds
			if(index + step < n){
				// Recurse to the next index and add the number of ways from there
				ways += countWaysMemonizationForwardHelper(jumps, n, index + step, dp);
			}
		}
		// Memoize the computed number of ways for current index
		return dp[index] = ways;
	}

	/**
	 * Approach [4]: Bottom-Up (Forward) Tabulation
	 *
	 * Intuition:
	 * - dp[i] = number of ways to reach i from 0. For each i, distribute dp[i]
	 *   to i + k for 1 <= k <= jumps[i], bounded within n.
	 *
	 * Explanation:
	 * - Initialize dp[0] = 1 and propagate forward. The answer is dp[n-1].
	 *
	 * Time Complexity: O(S) where S is the number of considered edges; worst
	 *                  O(n^2).
	 * Space Complexity: O(n) for the dp array.
	 *
	 * @param jumps  jumps[i] is max steps forward from index i
	 * @param n      array size
	 * @return       number of ways to reach last index from 0
	 */
	public static int countWaysTabulationForward(int[] jumps, int n){
        if (n == 0) return 0; // Empty array, no ways to move
        if (n == 1) return 1; // Single index is trivially reachable in exactly 1 way
        
        int[] dp = new int[n]; // dp[i] = number of ways to reach index i from 0
        dp[0] = 1; // Start position: there is exactly one way to be at index 0
        
        // Iterate through each index and distribute its ways forward
        for (int i = 0; i < n; i++) {
            // Only proceed if index i is actually reachable
            if (dp[i] > 0) {
                // Push the ways at i to all reachable indices i + k
                for (int k = 1; k <= jumps[i] && i + k < n; k++) {
                    // Add all ways to reach i to the target index i+k
                    dp[i + k] += dp[i];
                }
            }
        }
        
        // The number of ways to reach the last index
        return dp[n - 1];
	}
	public static void main(String[] args) {
		// Basic Test Cases
		System.out.println("Basic Test Cases:");
		System.out.println("Input: [1], Expected: 1, Output: " + countWaysMemonizationBackword(new int[]{1}, 1));
		System.out.println("Input: [1,0], Expected: 1, Output: " + countWaysMemonizationBackword(new int[]{1,0}, 2));
		System.out.println("Input: [2,1,0], Expected: 2, Output: " + countWaysMemonizationBackword(new int[]{2,1,0}, 3));
		System.out.println("Input: [3,2,0,1,2], Expected: 2, Output: " + countWaysMemonizationBackword(new int[]{3,2,0,1,2}, 5));

		// Edge Cases
		System.out.println("\nEdge Cases:");
		System.out.println("Input: [], Expected: 0, Output: " + countWaysTabulationBackword(new int[]{}, 0));
		System.out.println("Input: null, Expected: 0, Output: " + countWaysTabulationBackword(null, 0));
		System.out.println("Input: [0,0,0], Expected: 0, Output: " + countWaysTabulationBackword(new int[]{0,0,0}, 3));

		// Boundary/Complex Cases
		System.out.println("\nBoundary/Complex Cases:");
		System.out.println("Input: [2,2,2,2], Expected: 8, Output: " + countWaysMemonizationBackword(new int[]{2,2,2,2}, 4));
		System.out.println("Input: [3,3,0,2,2,3], Expected: 5, Output: " + countWaysMemonizationBackword(new int[]{3,3,0,2,2,3}, 6));
		System.out.println("Input: [4,0,0,0,0], Expected: 1, Output: " + countWaysMemonizationBackword(new int[]{4,0,0,0,0}, 5));

        // Boundary/Complex Cases
		System.out.println("\nBoundary/Complex Cases:");
		System.out.println("Input: [1, 3, 5, 8, 9, 1, 0, 7, 6, 8, 9], Expected: 52 , Output: " + countWaysTabulationForward(new int[]{1, 3, 5, 8, 9, 1, 0, 7, 6, 8, 9}, 11));
		// Special Cases
		System.out.println("\nSpecial Cases:");
		System.out.println("Input: [0], Expected: 1, Output: " + countWaysMemonizationBackword(new int[]{0}, 1)); // already at destination
		System.out.println("Input: [2,0,2,0], Expected: 1, Output: " + countWaysMemonizationBackword(new int[]{2,0,2,0}, 4));

	}
}
