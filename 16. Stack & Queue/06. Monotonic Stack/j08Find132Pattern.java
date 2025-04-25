/*-
 * Problem Statement:
 *     LeetCode 456. 132 Pattern
 * 
 *     Given an array of n integers nums, a 132 pattern is a subsequence of three
 *     integers nums[i], nums[j] and nums[k] such that i < j < k and
 *     nums[i] < nums[k] < nums[j].
 * 
 * Input:
 *     - Array nums where 1 <= nums.length <= 2 * 10^5
 *     - -10^9 <= nums[i] <= 10^9
 * 
 * Output:
 *     - Return true if there is a 132 pattern in nums, otherwise return false
 * 
 * Example:
 *     Input: nums = [3,1,4,2]
 *     Output: true
 *     
 *     Explanation:
 *     - nums[1] = 1, nums[2] = 4, nums[3] = 2 forms a 132 pattern
 *     - Here: 1 < 2 < 4 and indices satisfy i < j < k (1 < 2 < 3)
 */

import java.util.Stack;
import java.util.Arrays;

public class j08Find132Pattern {

    public static void main(String args[]) {
        // Test cases
        int[][] testCases = {
                { 3, 1, 4, 2 }, // Basic case with pattern
                { 1, 2, 3, 4 }, // Increasing sequence
                { 3, 5, 0, 3, 4 }, // Complex case
                { 1, 1, 1 }, // Same numbers
                { -1, 3, 2, 0 }, // Negative numbers
                { 3, 5, 0, 3, 4 } // Multiple possible patterns
        };

        // Test both implementations
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Array: " + Arrays.toString(testCases[i]));

            System.out.println("Brute Force: " +
                    find132patternBruteForce(testCases[i].clone()));
            System.out.println("Efficient: " +
                    find132patternEfficient(testCases[i].clone()));
            System.out.println();
        }
    }

    /*-
     * Approach 1: Brute Force (Three Nested Loops)
     * 
     * Intuition:
     * - Try every possible combination of three indices (i,j,k)
     * - Check if they form a valid 132 pattern:
     *   * nums[i] < nums[k] < nums[j]
     *   * indices satisfy i < j < k
     * 
     * Time Complexity: O(n³)
     * - Three nested loops iterating through array
     * - Each loop can go up to n iterations
     * 
     * Space Complexity: O(1)
     * - Only using constant extra space
     */
    public static boolean find132patternBruteForce(int[] nums) {
        if (nums.length < 3)
            return false;

        // Try all possible combinations of i, j, k
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[j] > nums[i]) {
                    for (int k = j + 1; k < nums.length; k++) {
                        // Check if pattern is found
                        if (nums[k] > nums[i] && nums[k] < nums[j]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*-
     * Approach 2: Monotonic Stack (Single Pass)
     * 
     * Intuition:
     * - Scan array from right to left
     * - Maintain stack of potential "second" elements (nums[j])
     * - Keep track of largest "third" element (nums[k])
     * - Current element becomes potential "first" element (nums[i])
     * - If we find nums[i] < third < stack elements, pattern exists
     * 
     * Time Complexity: O(n)
     * - Single pass through array
     * - Each element pushed/popped at most once
     * 
     * Space Complexity: O(n)
     * - Stack can store up to n elements in worst case
     */
    public static boolean find132patternEfficient(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int thirdElement = Integer.MIN_VALUE; // Track potential k value

        // Scan array from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            // If current element is smaller than k, pattern found
            if (nums[i] < thirdElement)
                return true;

            // Update k to be the largest possible value < nums[i]
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                thirdElement = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}