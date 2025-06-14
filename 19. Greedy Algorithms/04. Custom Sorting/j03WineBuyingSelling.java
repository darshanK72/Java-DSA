/**
 * GeeksForGeeks: Wine Buying and Selling
 * 
 * Problem Statement:
 *     In a row of N houses, each house can either buy or sell wine. The amount of
 *     wine each house wants to buy or sell is given in array Arr[]. A positive
 *     value in Arr[i] indicates that the ith house wants to buy Arr[i] bottles of
 *     wine, and a negative value indicates that the ith house wants to sell
 *     |Arr[i]| bottles of wine. Find the minimum amount of work needed to satisfy
 *     all the demands. The work is defined as the distance between houses
 *     multiplied by the number of bottles transferred.
 * 
 * Input:
 *     - Arr[] (int[]): Array of N integers where:
 *       - Arr[i] > 0 means house i wants to buy Arr[i] bottles
 *       - Arr[i] < 0 means house i wants to sell |Arr[i]| bottles
 *       - Arr[i] = 0 means house i is neutral
 *     - N (int): Number of houses (1 <= N <= 10^5)
 *     - -10^5 <= Arr[i] <= 10^5
 * 
 * Output:
 *     - Minimum amount of work needed to satisfy all demands
 *     - Work = distance × number of bottles transferred
 * 
 * Example:
 *     Input: Arr[] = {5, -4, 1, -3, 2}
 *     Output: 9
 * 
 *     Explanation:
 *     House 0 (buy 5) ← House 1 (sell 4): Work = 1 × 4 = 4
 *     House 2 (buy 1) ← House 3 (sell 3): Work = 1 × 1 = 1
 *     House 4 (buy 2) ← House 3 (sell 2): Work = 1 × 2 = 2
 *     House 0 (buy 1) ← House 3 (sell 1): Work = 3 × 1 = 3
 *     Total work = 4 + 1 + 2 + 3 = 10
 */

import java.util.ArrayList;

public class j03WineBuyingSelling {
    /**
     * Approach: Greedy with Two Pointers
     * 
     * Intuition:
     * - We need to match buyers with sellers to minimize the total work
     * - Work is calculated as distance × number of bottles
     * - To minimize work, we should match nearby buyers and sellers first
     * - We can use two pointers to track current buyer and seller
     * - Process transactions in order of house positions
     * 
     * Explanation:
     * 1. Separate buyers and sellers into two lists, maintaining their positions
     * 2. Use two pointers to track current buyer and seller
     * 3. For each pair:
     *    - Calculate maximum bottles that can be transferred
     *    - Update work done based on distance and bottles
     *    - Update remaining wine amounts
     *    - Move pointers if current buyer/seller is satisfied
     * 4. Continue until all buyers or sellers are processed
     * 
     * Time Complexity: O(N) where N is the number of houses
     *                  We process each house exactly once
     * Space Complexity: O(N) to store buyers and sellers lists
     * 
     * @param Arr Array representing wine demands of houses
     * @param N   Number of houses
     * @return   Minimum work needed to satisfy all demands
     */
    public long wineSelling(int Arr[], int N) {
        // Separate buyers and sellers
        ArrayList<int[]> buyers = new ArrayList<>();
        ArrayList<int[]> sellers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (Arr[i] < 0) {
                sellers.add(new int[] { Arr[i], i });
            } else {
                buyers.add(new int[] { Arr[i], i });
            }
        }

        // Process transactions using two pointers
        int i = 0;  // Pointer for buyers
        int j = 0;  // Pointer for sellers
        long work = 0;
        while (i < buyers.size() && j < sellers.size()) {
            int[] buy = buyers.get(i);
            int[] sell = sellers.get(j);

            // Calculate how many bottles can be transferred
            int bottles = Math.min(buy[0], -sell[0]);

            // Update the work done (distance × bottles)
            work += (Math.abs(buy[1] - sell[1])) * bottles;

            // Update remaining wine amounts
            buy[0] -= bottles;
            sell[0] += bottles;

            // Move to next buyer/seller if their requirements are met
            if (buy[0] == 0)
                i++;
            if (sell[0] == 0)
                j++;
        }

        return work;
    }

    public static void main(String[] args) {
        j03WineBuyingSelling solution = new j03WineBuyingSelling();

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {5, -4, 1, -3, 2};
        System.out.println("Input: " + java.util.Arrays.toString(arr1));
        System.out.println("Expected: 10, Output: " + solution.wineSelling(arr1, arr1.length));

        // Test Case 2: All buyers
        System.out.println("\nAll Buyers Test Case:");
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + java.util.Arrays.toString(arr2));
        System.out.println("Expected: 0, Output: " + solution.wineSelling(arr2, arr2.length));

        // Test Case 3: All sellers
        System.out.println("\nAll Sellers Test Case:");
        int[] arr3 = {-1, -2, -3, -4, -5};
        System.out.println("Input: " + java.util.Arrays.toString(arr3));
        System.out.println("Expected: 0, Output: " + solution.wineSelling(arr3, arr3.length));

        // Test Case 4: Alternating buyers and sellers
        System.out.println("\nAlternating Test Case:");
        int[] arr4 = {1, -1, 1, -1, 1};
        System.out.println("Input: " + java.util.Arrays.toString(arr4));
        System.out.println("Expected: 2, Output: " + solution.wineSelling(arr4, arr4.length));
    }
}
