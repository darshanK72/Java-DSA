/**
 * Problem Statement:
 *     LeetCode 901. Online Stock Span
 * 
 *     Design an algorithm that collects daily price quotes for some stock and 
 *     returns the span of that stock's price for the current day. The span of 
 *     the stock's price today is defined as the maximum number of consecutive 
 *     days (starting from today and going backward) for which the stock price 
 *     was less than or equal to today's price.
 * 
 * Input:
 *     - Stream of daily stock prices
 *     - 1 <= price <= 10^5
 * 
 * Output:
 *     - For each price, return its span
 * 
 * Example:
 *     Input: ["StockSpanner", "next", "next", "next", "next", "next"]
 *            [[], [100], [80], [60], [70], [75]]
 *     Output: [null, 1, 1, 1, 2, 4]
 *     
 *     Explanation:
 *     - For price 100, span is 1 (first day)
 *     - For price 80, span is 1 (no greater previous prices)
 *     - For price 60, span is 1 (no greater previous prices)
 *     - For price 70, span is 2 (includes 60)
 *     - For price 75, span is 4 (includes 70, 60, 80)
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class j09OnlineStackSpan {

    public static void main(String[] args) {
        // Test cases
        int[] testPrices = {100, 80, 60, 70, 75, 85};
        
        System.out.println("Testing Array Implementation:");
        StockSpannerUsingArray spannerArray = new StockSpannerUsingArray();
        testImplementation(spannerArray, testPrices);
        
        System.out.println("\nTesting Stack Implementation:");
        StockSpannerUsingStack spannerStack = new StockSpannerUsingStack();
        testImplementation(spannerStack, testPrices);
    }

    private static void testImplementation(Object spanner, int[] prices) {
        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.print("Spans:  [");
        
        for (int i = 0; i < prices.length; i++) {
            int span = (spanner instanceof StockSpannerUsingArray) ?
                      ((StockSpannerUsingArray)spanner).next(prices[i]) :
                      ((StockSpannerUsingStack)spanner).next(prices[i]);
            System.out.print(span + (i < prices.length-1 ? ", " : ""));
        }
        System.out.println("]");
    }

    /**
     * Approach 1: Using Array (Brute Force)
     * 
     * Intuition:
     * - Store all prices in ArrayList
     * - For each new price, scan backwards until finding a higher price
     * - Count days with prices <= current price
     * 
     * Time Complexity: O(n) per call
     * - Each next() call may need to scan all previous prices
     * 
     * Space Complexity: O(n)
     * - Stores all prices in ArrayList
     */
    static class StockSpannerUsingArray {
        ArrayList<Integer> arr;

        public StockSpannerUsingArray() {
            this.arr = new ArrayList<>();
        }

        public int next(int price) {
            int i = this.arr.size() - 1;  // Start from most recent price
            int span = 1;                 // Include current day
            
            // Count consecutive days with prices <= current
            while (i >= 0) {
                if (this.arr.get(i) <= price)
                    span++;
                else
                    break;
                i--;
            }
            
            this.arr.add(price);  // Store current price
            return span;
        }
    }

    /**
     * Approach 2: Using Stack (Optimized)
     * 
     * Intuition:
     * - Use stack to maintain indices of prices in decreasing order
     * - When new price comes, pop smaller prices as they won't be needed
     * - Stack top will have index of next greater price
     * - Difference between current size and top index gives span
     * 
     * Time Complexity: O(1) amortized
     * - Each price is pushed and popped at most once
     * - Total operations over n calls is O(n)
     * 
     * Space Complexity: O(n)
     * - Stack and ArrayList each store at most n elements
     */
    static class StockSpannerUsingStack {
        ArrayList<Integer> arr;    // Store prices
        Stack<Integer> stack;      // Store indices of prices

        public StockSpannerUsingStack() {
            this.arr = new ArrayList<>();
            this.stack = new Stack<>();
        }

        public int next(int price) {
            // Remove indices of smaller prices
            while (!stack.isEmpty() && this.arr.get(stack.peek()) <= price) {
                stack.pop();
            }
            
            // Calculate span using stack top
            int span = (this.stack.isEmpty()) ? 
                      this.arr.size() + 1 : 
                      this.arr.size() - this.stack.peek();
            
            // Update data structures
            this.stack.push(this.arr.size());
            this.arr.add(price);
            
            return span;
        }
    }
}
