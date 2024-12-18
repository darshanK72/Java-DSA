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
     * Approach: Single Pass (O(n))
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
}
