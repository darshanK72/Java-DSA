/*-
 * LeetCode 55 - Jump Game I
 * 
 * Problem Statement:
 *     You are given an integer array nums. You are initially positioned at the 
 *     array's first index, and each element in the array represents your 
 *     maximum jump length at that position. Return true if you can reach the 
 *     last index, or false otherwise.
 * 
 * Input:
 *     - nums[] (int[]): Array where nums[i] represents maximum jump length from 
 *       position i
 * 
 * Output:
 *     - boolean: true if last index can be reached, false otherwise
 * 
 * Example:
 *     Input: nums = [2,3,1,1,4]
 *     Output: true
 * 
 *     Explanation:
 *     - Start at index 0
 *     - Jump 1 step to index 1
 *     - Jump 3 steps to index 4 (last index)
 *     - Can reach the last index
 */

public class j06JumpGameI {
    
    /*-
     * Approach: Greedy
     * 
     * Intuition:
     * - We don't need to know which jumps to take, only if we can reach the end
     * - Keep track of the maximum index we can reach from current position
     * - If at any point we can't reach current index, we can't reach the end
     * - If we can reach or go beyond the last index, we can reach the end
     * 
     * Explanation:
     * 1. Initialize maxReach to track furthest index we can reach
     * 2. For each position i:
     *    - If we can't reach current position (maxReach < i), return false
     *    - Update maxReach to maximum of:
     *      * Current maxReach
     *      * Current position + maximum jump from here
     * 3. If we can reach the end, return true
     * 
     * Time Complexity: O(N) where N is length of array
     *                  - Single pass through the array
     * Space Complexity: O(1) as we only use one variable
     * 
     * @param nums    Array where nums[i] represents maximum jump length from 
     *                position i
     * @return       true if last index can be reached, false otherwise
     */
    public static boolean canJump(int[] nums) {
        int maxReach = 0;  // Furthest index we can reach
        
        // Process each position
        for(int i = 0; i < nums.length; i++){
            // If we can't reach current position, we can't reach the end
            if(maxReach < i) return false;
            
            // Update maximum reachable index
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums1));
        System.out.println("Output: " + canJump(nums1));

        // Test Case 2: Cannot reach end
        System.out.println("\nCannot Reach End:");
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums2));
        System.out.println("Output: " + canJump(nums2));

        // Test Case 3: Single element
        System.out.println("\nSingle Element:");
        int[] nums3 = {0};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums3));
        System.out.println("Output: " + canJump(nums3));

        // Test Case 4: All zeros
        System.out.println("\nAll Zeros:");
        int[] nums4 = {0, 0, 0, 0};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums4));
        System.out.println("Output: " + canJump(nums4));

        // Test Case 5: Large jumps
        System.out.println("\nLarge Jumps:");
        int[] nums5 = {5, 0, 0, 0, 0};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums5));
        System.out.println("Output: " + canJump(nums5));

        // Test Case 6: Edge case - empty array
        System.out.println("\nEdge Case - Empty Array:");
        int[] nums6 = {};
        System.out.println("Input: nums=" + java.util.Arrays.toString(nums6));
        System.out.println("Output: " + canJump(nums6));
    }
}
