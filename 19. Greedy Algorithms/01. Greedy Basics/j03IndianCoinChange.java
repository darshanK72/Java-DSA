/**
 * GeeksForGeeks - Indian Coin Change
 * 
 * Problem Statement:
 *     Given an amount N, find the minimum number of coins required to make up 
 *     that amount using Indian currency denominations. The denominations are:
 *     1, 2, 5, 10, 20, 50, 100, 200, 500, 2000.
 * 
 * Input:
 *     - N (int): Amount to be made up using coins
 * 
 * Output:
 *     - List of coins that sum up to N with minimum number of coins
 * 
 * Example:
 *     Input: N = 43
 *     Output: [20, 20, 2, 1]
 * 
 *     Explanation:
 *     - Use 20 rupee coin twice (40)
 *     - Use 2 rupee coin once (42)
 *     - Use 1 rupee coin once (43)
 *     - Total coins used = 4
 */

import java.util.ArrayList;
import java.util.List;

public class j03IndianCoinChange {
    
    /**
     * Approach: Greedy
     * 
     * Intuition:
     * - Since we want minimum number of coins, we should use the largest
     *   denomination possible at each step
     * - Indian currency denominations are designed such that greedy approach
     *   works (each denomination is a multiple of smaller denominations)
     * - Start with largest denomination and keep subtracting until we can't
     *   use that denomination anymore
     * 
     * Explanation:
     * 1. Initialize array of coin denominations in descending order
     * 2. For each denomination:
     *    - While we can use current denomination (N >= denomination)
     *      - Add it to result list
     *      - Subtract from remaining amount
     *    - Move to next smaller denomination
     * 
     * Time Complexity: O(N) where N is the amount
     *                  - In worst case, we might need N coins of value 1
     * Space Complexity: O(1) for fixed denominations array
     *                  - O(N) for result list in worst case
     * 
     * @param N    Amount to be made up using coins
     * @return    List of coins that sum up to N with minimum number of coins
     */
    public static List<Integer> minPartition(int N) {
        // Initialize denominations in descending order
        int[] coins = new int[] {
                2000, 500, 200, 100, 50, 20, 10, 5, 2, 1
        };

        ArrayList<Integer> out = new ArrayList<>();
        int i = 0;
        // Process each denomination
        while (N > 0 && i < coins.length) {
            // Use current denomination as many times as possible
            while (N - coins[i] >= 0) {
                out.add(coins[i]);
                N -= coins[i];
            }
            i++;
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int N1 = 43;
        System.out.println("Input: N=" + N1);
        System.out.println("Output: " + minPartition(N1));

        // Test Case 2: Large amount
        System.out.println("\nLarge Amount:");
        int N2 = 2000;
        System.out.println("Input: N=" + N2);
        System.out.println("Output: " + minPartition(N2));

        // Test Case 3: Small amount
        System.out.println("\nSmall Amount:");
        int N3 = 7;
        System.out.println("Input: N=" + N3);
        System.out.println("Output: " + minPartition(N3));

        // Test Case 4: Amount requiring all denominations
        System.out.println("\nAll Denominations:");
        int N4 = 2888;
        System.out.println("Input: N=" + N4);
        System.out.println("Output: " + minPartition(N4));

        // Test Case 5: Edge case - zero amount
        System.out.println("\nEdge Case - Zero Amount:");
        int N5 = 0;
        System.out.println("Input: N=" + N5);
        System.out.println("Output: " + minPartition(N5));
    }
}
