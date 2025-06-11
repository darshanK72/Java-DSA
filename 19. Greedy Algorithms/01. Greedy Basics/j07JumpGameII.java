/**
 * LeetCode 45 - Jump Game II
 * 
 * Problem Statement:
 *     Given an array of non-negative integers nums, you are initially positioned 
 *     at the first index of the array. Each element in the array represents 
 *     your maximum jump length at that position. Your goal is to reach the last 
 *     index in the minimum number of jumps. You can assume that you can always 
 *     reach the last index.
 * 
 * Input:
 *     - nums[] (int[]): Array where nums[i] represents maximum jump length from 
 *       position i
 * 
 * Output:
 *     - Minimum number of jumps to reach the last index
 * 
 * Example:
 *     Input: nums = [2,3,1,1,4]
 *     Output: 2
 * 
 *     Explanation:
 *     - Start at index 0
 *     - Jump 1 step to index 1
 *     - Jump 3 steps to index 4 (last index)
 *     - Minimum jumps = 2
 */

public class j07JumpGameII {
    
    /**
     * Approach 1: Recursive with Backtracking
     * 
     * Intuition:
     * - Try all possible jump combinations from each position
     * - Keep track of minimum jumps needed to reach the end
     * - Use backtracking to explore all possible paths
     * - Update minimum jumps when a valid path is found
     * 
     * Explanation:
     * 1. Start from index 0 with 0 jumps
     * 2. For each position:
     *    - If we've reached or passed the end, update minimum jumps
     *    - Try all possible jump lengths from current position
     *    - Recursively explore each possible path
     *    - Backtrack to try other combinations
     * 
     * Time Complexity: O(N^N) where N is length of array
     *                  - At each position, we try up to N different jumps
     *                  - This leads to exponential growth
     * Space Complexity: O(N) for recursion stack
     * 
     * @param nums    Array where nums[i] represents maximum jump length from 
     *                position i
     * @return       Minimum number of jumps to reach the last index
     */
    public static int jumpRecursion(int[] nums) {
        int[] min = new int[1];  // Array to store minimum jumps (using array for pass by reference)
        min[0] = Integer.MAX_VALUE;  // Initialize with maximum possible value
        findMinJumps(nums, 0, min, 0);  // Start recursion from index 0 with 0 jumps
        return min[0];
    }

    /**
     * Helper Method: Recursive Backtracking
     * 
     * Intuition:
     * - Explore all possible paths from current position
     * - Update minimum jumps when a valid path is found
     * - Use array to maintain minimum across recursive calls
     * 
     * Explanation:
     * 1. Base case: If we've reached or passed the end
     *    - Update minimum jumps if current path uses fewer jumps
     * 2. For each possible jump length from current position:
     *    - Make the jump
     *    - Recursively explore the new position
     *    - Backtrack to try other jump lengths
     * 
     * @param nums    Array where nums[i] represents maximum jump length
     * @param index   Current position in array
     * @param min     Array storing minimum jumps found so far
     * @param jumps   Number of jumps made in current path
     */
    public static void findMinJumps(int[] nums, int index, int[] min, int jumps) {
        // Base case: reached or passed the end
        if (index >= nums.length - 1) {
            min[0] = Math.min(min[0], jumps);  // Update minimum if current path is better
            return;
        }
        
        // Try all possible jump lengths from current position
        for (int i = 1; i <= nums[index]; i++) {
            findMinJumps(nums, index + i, min, jumps + 1);
        }
    }

    /**
     * Approach 2: Greedy with BFS-like Window
     * 
     * Intuition:
     * - Think of the problem as a BFS where each level represents one jump
     * - For each level, we find the furthest position we can reach
     * - The minimum number of jumps is the number of levels needed to reach end
     * - Use a sliding window [l,r] to represent current level's reachable positions
     * 
     * Explanation:
     * 1. Initialize window [l,r] to [0,0] (start position)
     * 2. While right boundary hasn't reached end:
     *    - For each position in current window:
     *      * Calculate furthest position reachable from here
     *      * Update maximum reachable position
     *    - Move window to next level:
     *      * Left boundary = right boundary + 1
     *      * Right boundary = maximum reachable position
     *    - Increment jump count
     * 
     * Time Complexity: O(N) where N is length of array
     *                  - Each position is processed at most once
     * Space Complexity: O(1) as we only use three variables
     * 
     * @param nums    Array where nums[i] represents maximum jump length from 
     *                position i
     * @return       Minimum number of jumps to reach the last index
     */
    public static int jump(int[] nums) {
        int l = 0;      // Left boundary of current window
        int r = 0;      // Right boundary of current window
        int jumps = 0;  // Count of jumps made
        
        // Continue until we can reach the end
        while (r < nums.length - 1) {
            int farRight = 0;  // Furthest position reachable from current window
            
            // Process each position in current window
            for (int i = l; i <= r; i++) {
                farRight = Math.max(i + nums[i], farRight);
            }
            
            // Move to next level
            l = r + 1;          // New left boundary
            r = farRight;       // New right boundary
            jumps++;            // Increment jump count
        }
        return jumps;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums1));
        System.out.println("Output (Greedy): " + jump(nums1));
        System.out.println("Output (Recursive): " + jumpRecursion(nums1));

        // Test Case 2: Single jump needed
        System.out.println("\nSingle Jump:");
        int[] nums2 = {4, 0, 0, 0, 0};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums2));
        System.out.println("Output (Greedy): " + jump(nums2));
        System.out.println("Output (Recursive): " + jumpRecursion(nums2));

        // Test Case 3: Multiple jumps needed
        System.out.println("\nMultiple Jumps:");
        int[] nums3 = {1, 1, 1, 1, 1};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums3));
        System.out.println("Output (Greedy): " + jump(nums3));
        System.out.println("Output (Recursive): " + jumpRecursion(nums3));

        // Test Case 4: Large jumps
        System.out.println("\nLarge Jumps:");
        int[] nums4 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums4));
        System.out.println("Output (Greedy): " + jump(nums4));
        System.out.println("Output (Recursive): " + jumpRecursion(nums4));

        // Test Case 5: Single element
        System.out.println("\nSingle Element:");
        int[] nums5 = {0};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums5));
        System.out.println("Output (Greedy): " + jump(nums5));
        System.out.println("Output (Recursive): " + jumpRecursion(nums5));

        // Test Case 6: Edge case - empty array
        System.out.println("\nEdge Case - Empty Array:");
        int[] nums6 = {};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums6));
        System.out.println("Output (Greedy): " + jump(nums6));
        System.out.println("Output (Recursive): " + jumpRecursion(nums6));
    }
}
