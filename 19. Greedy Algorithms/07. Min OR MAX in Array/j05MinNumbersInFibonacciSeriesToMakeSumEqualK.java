/**
 * LeetCode 1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
 * 
 * Problem Statement:
 *     Given an integer k, return the minimum number of Fibonacci numbers whose sum is
 *     equal to k, where each Fibonacci number can be used only once. The Fibonacci
 *     sequence is defined as: F(1) = 1, F(2) = 1, F(n) = F(n-1) + F(n-2) for n > 2.
 * 
 * Input:
 *     - k (1 <= k <= 10^9)
 * 
 * Output:
 *     - Return the minimum number of Fibonacci numbers needed to sum to k
 * 
 * Example:
 *     Input: k = 7
 *     Output: 2
 * 
 *     Explanation:
 *     The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 *     For k = 7 we can use 2 + 5 = 7.
 *     Since 2 and 5 are Fibonacci numbers, the answer is 2.
 */

import java.util.ArrayList;

public class j05MinNumbersInFibonacciSeriesToMakeSumEqualK {

    /**
     * Approach 1: Greedy with Fibonacci List
     * 
     * Intuition:
     * - Generate Fibonacci numbers up to k
     * - Use the largest possible Fibonacci number at each step
     * - This greedy approach works because of the Fibonacci property
     * 
     * Explanation:
     * - Step 1: Generate all Fibonacci numbers less than or equal to k
     * - Step 2: Start from the largest Fibonacci number
     * - Step 3: For each number:
     *   * If it can be subtracted from k, do so and increment count
     *   * Move to the next smaller Fibonacci number
     * 
     * Time Complexity: O(log k) where k is the input number
     *                  - O(log k) to generate Fibonacci numbers
     *                  - O(log k) to find the minimum numbers
     * Space Complexity: O(log k) to store Fibonacci numbers
     * 
     * @param k    Target sum to achieve
     * @return     Minimum number of Fibonacci numbers needed
     */
    public static int findMinFibonacciNumbers(int k) {
        // Initialize ArrayList to store Fibonacci numbers
        ArrayList<Integer> fib = new ArrayList<>();
        
        // Initialize first three Fibonacci numbers
        int t1 = 0;  // First number
        int t2 = 1;  // Second number
        int t3 = t1 + t2;  // Third number
        
        // Generate Fibonacci numbers until we exceed k
        while(t1 <= k) {
            fib.add(t1);  // Add current number to list
            t1 = t2;      // Update first number
            t2 = t3;      // Update second number
            t3 = t1 + t2; // Calculate next Fibonacci number
        }

        // Start from the largest Fibonacci number
        int i = fib.size() - 1;
        int ans = 0;  // Count of Fibonacci numbers used
        
        // Try to use largest possible Fibonacci numbers
        while(i > 0) {
            if(fib.get(i) <= k) {
                ans++;  // Increment count as we use this number
                k -= fib.get(i);  // Subtract from remaining sum
            }
            i--;  // Move to next smaller Fibonacci number
        }

        return ans;
    }

    /**
     * Approach 2: Greedy without Storing Fibonacci Numbers
     * 
     * Intuition:
     * - We can find the largest Fibonacci number less than or equal to k
     * - Subtract it from k and repeat
     * - No need to store all Fibonacci numbers
     * 
     * Explanation:
     * - Step 1: Find the largest Fibonacci number less than or equal to k
     * - Step 2: Subtract it from k and increment count
     * - Step 3: Repeat until k becomes 0
     * 
     * Time Complexity: O(log k) where k is the input number
     *                  - Each iteration reduces k by at least half
     * Space Complexity: O(1) as we don't store Fibonacci numbers
     * 
     * @param k    Target sum to achieve
     * @return     Minimum number of Fibonacci numbers needed
     */
    public static int findMinFibonacciNumbersEfficient(int k) {
        // Base case: if k is 0, we need 0 numbers
        if (k == 0) return 0;
        
        // Initialize two consecutive Fibonacci numbers
        int a = 1;  // First Fibonacci number
        int b = 1;  // Second Fibonacci number
        
        // Find the largest Fibonacci number less than or equal to k
        while (b <= k) {
            int temp = a + b;  // Calculate next Fibonacci number
            a = b;             // Update first number
            b = temp;          // Update second number
        }
        
        // Recursively find minimum numbers for remaining sum
        // Add 1 for the current Fibonacci number we used
        return 1 + findMinFibonacciNumbersEfficient(k - a);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int k1 = 7;
        System.out.println("Input: k = 7");
        System.out.println("Expected: 2, Output: " + findMinFibonacciNumbers(k1));
        System.out.println("Efficient Approach Output: " + findMinFibonacciNumbersEfficient(k1));

        // Test Case 2: Power of 2
        System.out.println("\nPower of 2 Case:");
        int k2 = 8;
        System.out.println("Input: k = 8");
        System.out.println("Expected: 1, Output: " + findMinFibonacciNumbers(k2));
        System.out.println("Efficient Approach Output: " + findMinFibonacciNumbersEfficient(k2));

        // Test Case 3: Large number
        System.out.println("\nLarge Number Case:");
        int k3 = 19;
        System.out.println("Input: k = 19");
        System.out.println("Expected: 3, Output: " + findMinFibonacciNumbers(k3));
        System.out.println("Efficient Approach Output: " + findMinFibonacciNumbersEfficient(k3));

        // Test Case 4: Small number
        System.out.println("\nSmall Number Case:");
        int k4 = 1;
        System.out.println("Input: k = 1");
        System.out.println("Expected: 1, Output: " + findMinFibonacciNumbers(k4));
        System.out.println("Efficient Approach Output: " + findMinFibonacciNumbersEfficient(k4));
    }
}
