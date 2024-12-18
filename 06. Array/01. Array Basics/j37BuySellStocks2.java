/**
 * Problem Statement:
 * 
 *     You are given an array `prices` where `prices[i]` represents the price of a stock on the i-th day.
 *     You are allowed to buy and sell the stock multiple times. The task is to calculate the maximum profit 
 *     that can be achieved by performing multiple buy and sell transactions, where you must sell the stock 
 *     before you can buy it again. 
 * 
 *     Additionally, you need to output a list of the day indices for each transaction, where each transaction 
 *     represents a buy and sell pair of days (buy before sell).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the number of days.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5), representing the stock prices.
 * 
 * Output:
 *     - The maximum profit that can be made by making as many transactions as you want.
 *     - A list of all the buy and sell day pairs for each transaction.
 * 
 * Example:
 *     Input:
 *     6
 *     7 1 5 3 6 4
 *     
 *     Output:
 *     7
 *     [[1, 2], [3, 4]]
 * 
 *     Explanation:
 *     The maximum profit is achieved by:
 *     - Buying on day 1 (price 1) and selling on day 2 (price 5), profit = 5 - 1 = 4
 *     - Buying on day 3 (price 3) and selling on day 4 (price 6), profit = 6 - 3 = 3
 *     Total profit = 4 + 3 = 7.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j37BuySellStocks2 {
    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution methods
        System.out.println(maxProfit(arr)); // Print maximum profit
        System.out.println(stockBuySellPricesList(arr, n)); // Print list of buy-sell pairs

        in.close();
    }

    /**
     * Approach 1: Greedy Approach (O(n))
     * 
     * Intuition:
     * - The goal is to make as much profit as possible by buying and selling on different days. 
     * - For each day, if the next day's price is higher than the current day's price, we assume a "buy" on the current day 
     *   and a "sell" on the next day.
     * - We sum up all such profits where the next day's price is greater than the current day's price.
     * - This greedy approach works because buying at any local minimum and selling at any local maximum ensures we are getting the most profit.
     * 
     * Time Complexity:
     * - O(n), as we are iterating through the array once.
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of extra space.
     * 
     * @param prices The input array of stock prices.
     * @return The maximum profit that can be obtained by performing multiple transactions.
     */
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // If the next price is higher, we sell at this price
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit; // Return the total accumulated profit
    }

    /**
     * Approach 2: Recording Buy-Sell Days (O(n))
     * 
     * Intuition:
     * - In addition to calculating the maximum profit, we also need to record the days on which buying and selling occur.
     * - As we traverse the price array, we look for pairs of days where the price increases from one day to the next.
     * - We record the days when a stock is bought and sold.
     * - Each pair of buy-sell days corresponds to a valid transaction.
     * 
     * Time Complexity:
     * - O(n), as we are iterating through the array once.
     * 
     * Space Complexity:
     * - O(n), for storing the list of buy-sell day pairs.
     * 
     * @param arr The input array of stock prices.
     * @param n The size of the array.
     * @return A list of buy-sell day pairs for each transaction.
     */
    public static ArrayList<ArrayList<Integer>> stockBuySellPricesList(int arr[], int n) {
        int i = 0;
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        while (i < (n - 1)) {
            ArrayList<Integer> l = new ArrayList<>();
            // If the price of the next day is higher than the current, we record a buy-sell
            // transaction
            if (arr[i + 1] > arr[i]) {
                l.add(i); // Add the buying day
                // Keep moving to the next day until the price starts decreasing
                while ((i < (n - 1)) && arr[i + 1] > arr[i]) {
                    i++;
                }
                l.add(i); // Add the selling day
            }
            i++;
            // If a valid transaction (buy-sell pair) is found, we add it to the list
            if (l.size() > 0)
                out.add(l);
        }
        return out; // Return the list of transactions
    }
}
