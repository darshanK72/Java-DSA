/**
 * LeetCode 453. Minimum Moves to Equal Array Elements
 * 
 * Problem Statement:
 *     Given an integer array nums of size n, return the minimum number of moves
 *     required to make all array elements equal. In one move, you can increment
 *     n - 1 elements of the array by 1.
 * 
 * Input:
 *     - nums: integer array (1 <= nums.length <= 10^5)
 *     - -10^9 <= nums[i] <= 10^9
 * 
 * Output:
 *     - Return the minimum number of moves required to make all elements equal
 * 
 * Example:
 *     Input: nums = [1,2,3]
 *     Output: 3
 * 
 *     Explanation:
 *     [1,2,3]  =>  [2,3,3]  =>  [3,3,3]
 *     - First move: increment 1 and 2
 *     - Second move: increment 2 and 3
 *     - Third move: increment 2 and 3
 */

public class j07MinMovesToEqualArrayElementsI {
   
    /**
     * Approach: Greedy - Find Minimum Element
     * 
     * Intuition:
     * - Instead of incrementing n-1 elements, we can think of it as decrementing
     *   one element by 1
     * - The minimum number of moves will be the sum of differences between each
     *   element and the minimum element
     * - This works because we can always make all elements equal to the minimum
     *   element by decrementing other elements
     * 
     * Explanation:
     * - Step 1: Find the minimum element in the array
     * - Step 2: Calculate the sum of differences between each element and min
     * - Step 3: Return the sum as it represents the minimum moves needed
     * 
     * Time Complexity: O(n) where n is the length of nums array
     * Space Complexity: O(1) as we only use constant extra space
     * 
     * @param nums    Array of integers to make equal
     * @return        Minimum number of moves required
     */
    public static int minMoves(int[] nums) {
        // Find the minimum element in the array
        int min = nums[0];
        for(int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        
        // Calculate sum of differences from minimum element
        int moves = 0;
        for(int i = 0; i < nums.length; i++) {
            moves += (nums[i] - min);
        }
        
        return moves;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {1, 2, 3};
        System.out.println("Input: [1,2,3], Expected: 3, Output: " + minMoves(nums1));

        // Test Case 2: All elements equal
        System.out.println("\nAll Elements Equal:");
        int[] nums2 = {2, 2, 2};
        System.out.println("Input: [2,2,2], Expected: 0, Output: " + minMoves(nums2));

        // Test Case 3: Large numbers
        System.out.println("\nLarge Numbers:");
        int[] nums3 = {1000000, 1000001, 1000002};
        System.out.println("Input: [1000000,1000001,1000002], Expected: 2, Output: " + minMoves(nums3));

        // Test Case 4: Negative numbers
        System.out.println("\nNegative Numbers:");
        int[] nums4 = {-1, -2, -3};
        System.out.println("Input: [-1,-2,-3], Expected: 3, Output: " + minMoves(nums4));
    }
}
