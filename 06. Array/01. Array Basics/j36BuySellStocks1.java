/**
 * Problem Statement:
 * 
 *     You are given an array `arr` of integers where each element represents the price of a stock on a given day. 
 *     You can buy the stock on one day and sell it on another later day. You need to find the maximum profit that can be achieved 
 *     by buying and selling the stock exactly once.
 * 
 *     The result should be the difference between the buying price and the selling price, ensuring that the selling price comes 
 *     after the buying price in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the number of days.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5), representing the stock prices.
 * 
 * Output:
 *     - The maximum profit you can achieve from the given stock prices.
 * 
 * Example:
 *     Input:
 *     6
 *     7 1 5 3 6 4
 *     
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The maximum profit is achieved by buying at price 1 and selling at price 6, which gives a profit of 6 - 1 = 5.
 */

import java.util.Scanner;

public class j36BuySellStocks1 {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution method
        System.out.println(getMaxProfit(arr));

        in.close();
    }

    /**
     * Approach 1: Single Pass (O(n))
     * 
     * Intuition:
     * - To find the maximum profit, we can iterate through the array once.
     * - We keep track of the minimum price encountered so far, and at each step, we compute the profit by selling at the current price.
     * - The profit is the difference between the current price and the minimum price seen so far.
     * - If this profit is greater than the previously recorded maximum profit, we update the maximum profit.
     * - The idea is that at each point, we calculate the best possible profit if we sell at that price, considering the lowest price we've seen up to that point.
     * 
     * Time Complexity:
     * - O(n), as we are iterating through the array just once.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space.
     * 
     * @param arr The input array of stock prices.
     * @return The maximum profit from a single buy and sell transaction.
     */
    public static int getMaxProfit(int[] arr) {
        int min = arr[0]; // Initialize minimum price to the first element
        int ans = 0; // Initialize the result to 0 (no profit initially)

        for (int i = 1; i < arr.length; i++) {
            int d = arr[i] - min; // Calculate the profit if sold at current price
            ans = Math.max(ans, d); // Update the maximum profit
            min = Math.min(min, arr[i]); // Update the minimum price
        }

        return ans; // Return the maximum profit
    }

    /*-
     * Approach 2: Reverse Traversal (O(n))
     * 
     * Intuition:
     * - Instead of tracking minimum price from left to right, we track maximum 
     *   price from right to left
     * - At each position, we know the maximum price available to the right of 
     *   current position
     * - We calculate profit by selling at the maximum price and buying at current 
     *   price
     * - This approach works because we're looking for the best selling price 
     *   available after each potential buying price
     * 
     * Explanation:
     * - Step 1: Initialize max price to the last element (rightmost price)
     * - Step 2: Traverse array from right to left, calculating profit at each 
     *   position
     * - Step 3: Update maximum price seen so far as we move left
     * - Step 4: Keep track of maximum profit encountered during traversal
     * 
     * Time Complexity: O(n) where n is the length of prices array
     * Space Complexity: O(1) using only constant extra space
     * 
     * @param prices    Array of stock prices for each day (1 <= prices.length <= 10^5)
     * @return         Maximum profit from buying and selling exactly once
     */
    public int maxProfit(int[] prices) {
        // Get array length for boundary calculations
        int n = prices.length;
        
        // Initialize maximum price to last element (rightmost selling price)
        int max = prices[n-1];
        
        // Initialize answer to track maximum profit found
        int ans = 0; 
        
        // Traverse array from right to left to find optimal buy-sell pair
        for(int i = n-1; i >= 0; i--){
            // Calculate profit if we buy at current price and sell at max price
            int profit = max - prices[i];
            
            // Update maximum price seen so far as we move leftward
            max = Math.max(prices[i], max);
            
            // Update maximum profit if current profit is better
            ans = Math.max(ans, profit);
        }
        
        // Return the maximum profit achievable
        return ans;
    }
}
