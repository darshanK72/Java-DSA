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
		return countWaysMemoizationBackwordHelper(jumps, 0, dp);
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
	public static int countWaysMemoizationBackwordHelper(int[] jumps,int currIndex,int[] dp){
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
				numberOfWaysFromHere += countWaysMemoizationBackwordHelper(jumps, nextIndex, dp);
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
	 * Approach [3]: Top-Down (Forward) Memoization — Ways to Reach Index
	 *
	 * Intuition:
	 * - Let dp[i] denote the number of ways to REACH index i from start (0).
	 * - Then dp[i] = sum of dp[j] for all j < i such that j + k == i for some
	 *   1 <= k <= jumps[j]. Compute this top-down by asking "how many ways to
	 *   reach i?" and recursively summing valid predecessors.
	 *
	 * Explanation:
	 * - Handle invalid input. Initialize dp with -1, and set base dp[0] = 1.
	 * - To compute dp[target], look at all prior indices that can jump to target
	 *   and sum their memoized ways. This contrasts with the backward approach
	 *   which counts ways FROM an index to the end.
	 *
	 * Time Complexity: O(n^2) in worst case (checking all predecessors per i).
	 * Space Complexity: O(n) for memo and recursion depth.
	 *
	 * @param jumps  jumps[i] is max steps forward from index i
	 * @param n      size bound used for index checks
	 * @return       number of ways to reach last index from index 0
	 */
	public static int countWaysMemonizationForward(int[] jumps, int n){
		// Validate input; if invalid or empty, there are 0 ways
		if (jumps == null || jumps.length == 0) return 0;
		final int size = jumps.length;
		
		// Initialize memo with -1 (unknown). Base: one way to be at index 0
		int[] dp = new int[size];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		
		// Compute number of ways to REACH the last index
		return countWaysMemonizationForwardHelper(jumps, n - 1, dp);
	}

	/**
	 * Helper: compute ways to REACH a given index from start using memoization.
	 *
	 * @param jumps   jumps array
	 * @param target  index whose reach-count we need
	 * @param dp      memo array where dp[i] = ways to reach i (or -1 if unknown)
	 * @return        number of ways to reach target from index 0
	 */
	public static int countWaysMemonizationForwardHelper(int[] jumps, int target, int[] dp){
		// Base already initialized for 0, and cached values return immediately
		if (dp[target] != -1) return dp[target];
		
		int totalWaysToTarget = 0;
		// Consider all possible predecessors 'prev' that can jump to 'target'
		for (int prev = 0; prev < target; prev++){
			// If from 'prev' we can reach 'target' in one allowed jump
			if (prev + jumps[prev] >= target){
				totalWaysToTarget += countWaysMemonizationForwardHelper(jumps, prev, dp);
			}
		}
		return dp[target] = totalWaysToTarget;
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
