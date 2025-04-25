/**
 * Problem Statement:
 *     LeetCode 1475. Final Prices With a Special Discount in a Shop
 * 
 *     Given an array prices where prices[i] is the price of the ith item in a shop,
 *     you are given a special discount rule: if you buy the ith item, you will
 *     receive a discount equivalent to prices[j] where j is the minimum index such
 *     that j > i and prices[j] <= prices[i]. If there is no such j, no discount.
 * 
 * Input:
 *     - Array prices where 1 <= prices.length <= 500
 *     - 1 <= prices[i] <= 10^3
 * 
 * Output:
 *     - Array where prices[i] is the final price after applying the discount
 * 
 * Example:
 *     Input: prices = [8,4,6,2,3]
 *     Output: [4,2,4,2,3]
 *     
 *     Explanation:
 *     - For item 0: price=8, discount=4, final=4
 *     - For item 1: price=4, discount=2, final=2
 *     - For item 2: price=6, discount=2, final=4
 *     - For item 3: price=2, discount=0, final=2
 *     - For item 4: price=3, discount=0, final=3
 */

import java.util.Stack;
import java.util.Arrays;

public class j05FinalPricesWithDiscount {

    public static void main(String args[]) {
        // Test cases with different scenarios
        int[][] testCases = {
            {8, 4, 6, 2, 3},        // Basic case
            {1, 2, 3, 4, 5},        // Increasing sequence
            {5, 4, 3, 2, 1},        // Decreasing sequence
            {2, 2, 2, 2},           // All same prices
            {1},                     // Single item
            {10, 1, 1, 6}           // Multiple discounts
        };

        // Test each case with both implementations
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            int[] prices = testCases[i].clone();
            System.out.println("Original Prices: " + Arrays.toString(prices));
            System.out.println("Two-Pass Result: " + 
                Arrays.toString(finalPrices(prices.clone())));
            System.out.println("Efficient Result: " + 
                Arrays.toString(finalPricesEfficient(prices.clone())));
            System.out.println();
        }
    }

    /**
     * Approach 1: Using Helper Method (Two-Pass)
     * 
     * Intuition:
     * - First find next lesser or equal element for each price
     * - Then subtract these values from original prices
     * - Uses separate array for storing discounts
     * 
     * Time Complexity: O(n)
     * - One pass to find discounts: O(n)
     * - One pass to apply discounts: O(n)
     * 
     * Space Complexity: O(n)
     * - Discount array: O(n)
     * - Stack space: O(n)
     */
    public static int[] finalPrices(int[] prices) {
        int[] discounts = nextLesserEqualElement(prices);
        for (int i = 0; i < prices.length; i++) {
            prices[i] = prices[i] - discounts[i];
        }
        return prices;
    }

    /**
     * Helper Method: Next Lesser or Equal Element
     * 
     * Intuition:
     * - Use monotonic stack to track indices
     * - When finding a lesser element, update all waiting greater elements
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private static int[] nextLesserEqualElement(int[] arr) {
        int[] out = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                out[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return out;
    }

    /**
     * Approach 2: Single Pass (Space-Efficient)
     * 
     * Intuition:
     * - Modify prices array in-place while finding next lesser elements
     * - Eliminates need for separate discount array
     * - Uses stack only to track indices
     * 
     * Time Complexity: O(n)
     * - Single pass through array: O(n)
     * 
     * Space Complexity: O(n)
     * - Only stack space: O(n)
     * - No additional arrays
     */
    public static int[] finalPricesEfficient(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                prices[stack.peek()] = prices[stack.peek()] - prices[i];
                stack.pop();
            }
            stack.push(i);
        }
        return prices;
    }
}
