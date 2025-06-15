/**
 * LeetCode: Candy Distribution
 * 
 * Problem Statement:
 *     There are n children standing in a line. Each child is assigned a rating value
 *     given in the integer array ratings. You are giving candies to these children
 *     subjected to the following requirements:
 *     1. Each child must have at least one candy
 *     2. Children with a higher rating get more candies than their neighbors
 * 
 *     Return the minimum number of candies you need to have to distribute the candies
 *     to the children.
 * 
 * Input:
 *     - ratings[] (int[]): Array of children's ratings
 *     - 1 <= n <= 2 * 10^4
 *     - 0 <= ratings[i] <= 2 * 10^4
 * 
 * Output:
 *     - Minimum number of candies needed
 * 
 * Example:
 *     Input: 
 *     ratings = [1, 0, 2]
 * 
 *     Output: 5
 * 
 *     Explanation:
 *     You can allocate to the first, second and third child with 2, 1, 2 candies
 *     respectively. The minimum number of candies needed is 5.
 */

public class j01CandyDistrubution {
    /**
     * Approach: Two-Pass Greedy
     * 
     * Intuition:
     * - We need to ensure that a child with higher rating gets more candies
     *   than both neighbors
     * - We can solve this by making two passes:
     *   1. Left to right: Ensure each child has more candies than left neighbor
     *   2. Right to left: Ensure each child has more candies than right neighbor
     * - Take maximum of both passes for each child
     * 
     * Explanation:
     * 1. Initialize two arrays: left[] and right[]
     * 2. First pass (left to right):
     *    - If current rating > previous rating, increment candies
     *    - Otherwise, reset to 1
     * 3. Second pass (right to left):
     *    - If current rating > next rating, increment candies
     *    - Otherwise, reset to 1
     * 4. Sum up maximum of left and right for each child
     * 
     * Time Complexity: O(n) where n is the number of children
     *                  We make two passes through the array
     * Space Complexity: O(n) for storing left and right arrays
     * 
     * @param ratings Array of children's ratings
     * @return Minimum number of candies needed
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // First pass: left to right
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Second pass: right to left
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // Calculate total candies needed
        int totalCandies = 0;
        for (int i = 0; i < n; i++) {
            totalCandies += Math.max(left[i], right[i]);
        }

        return totalCandies;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nTest Case 1: Basic case");
        int[] ratings1 = {1, 0, 2};
        System.out.println("Input: ratings = " + java.util.Arrays.toString(ratings1));
        System.out.println("Output: " + candy(ratings1));
        System.out.println("Expected: 5");

        // Test Case 2: All same ratings
        System.out.println("\nTest Case 2: All same ratings");
        int[] ratings2 = {1, 1, 1};
        System.out.println("Input: ratings = " + java.util.Arrays.toString(ratings2));
        System.out.println("Output: " + candy(ratings2));
        System.out.println("Expected: 3");

        // Test Case 3: Increasing ratings
        System.out.println("\nTest Case 3: Increasing ratings");
        int[] ratings3 = {1, 2, 3, 4, 5};
        System.out.println("Input: ratings = " + java.util.Arrays.toString(ratings3));
        System.out.println("Output: " + candy(ratings3));
        System.out.println("Expected: 15");

        // Test Case 4: Decreasing ratings
        System.out.println("\nTest Case 4: Decreasing ratings");
        int[] ratings4 = {5, 4, 3, 2, 1};
        System.out.println("Input: ratings = " + java.util.Arrays.toString(ratings4));
        System.out.println("Output: " + candy(ratings4));
        System.out.println("Expected: 15");

        // Test Case 5: Edge case - single child
        System.out.println("\nTest Case 5: Edge case - single child");
        int[] ratings5 = {1};
        System.out.println("Input: ratings = " + java.util.Arrays.toString(ratings5));
        System.out.println("Output: " + candy(ratings5));
        System.out.println("Expected: 1");
    }
}
