/**
 * GeeksForGeeks - Fractional Knapsack
 * 
 * Problem Statement:
 *     Given weights and values of N items, we need to put these items in a 
 *     knapsack of capacity W to get the maximum total value in the knapsack.
 *     Note: Unlike 0/1 knapsack, you are allowed to break the items.
 * 
 * Input:
 *     - values[] (int[]): Array of values of items
 *     - weights[] (int[]): Array of weights of items
 *     - W (int): Maximum capacity of knapsack
 * 
 * Output:
 *     - Maximum value that can be obtained
 * 
 * Example:
 *     Input: values[] = {60, 100, 120}, weights[] = {10, 20, 30}, W = 50
 *     Output: 240.0
 * 
 *     Explanation:
 *     - Item 1: 60/10 = 6 value per unit weight
 *     - Item 2: 100/20 = 5 value per unit weight
 *     - Item 3: 120/30 = 4 value per unit weight
 *     - Take 10 units of item 1 (60)
 *     - Take 20 units of item 2 (100)
 *     - Take 20 units of item 3 (80)
 *     - Total value = 60 + 100 + 80 = 240
 */

import java.util.PriorityQueue;

public class j01FractionalKnapsack {
    
    /**
     * Approach: Greedy
     * 
     * Intuition:
     * - Since we can take fractional parts of items, we should prioritize
     *   items with higher value-to-weight ratio
     * - This ensures we get maximum value for each unit of weight we add
     * - Using a max heap (PriorityQueue) to always pick the item with
     *   highest value-to-weight ratio
     * 
     * Explanation:
     * 1. Create a max heap based on value-to-weight ratio
     * 2. Add all items to the heap
     * 3. While we have capacity and items:
     *    - Take the item with highest value-to-weight ratio
     *    - If we can take the whole item, take it
     *    - Otherwise, take a fraction of it
     * 
     * Time Complexity: O(N log N) where N is number of items
     *                  - Building heap: O(N)
     *                  - Each heap operation: O(log N)
     * Space Complexity: O(N) for storing items in heap
     * 
     * @param values    Array of values of items
     * @param weights   Array of weights of items
     * @param W         Maximum capacity of knapsack
     * @return         Maximum value that can be obtained
     */
    public static double fractionalKnapsack(int[] values, int[] weights, int W) {
        // Initialize max heap based on value-to-weight ratio
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare((double) b[0] / b[1], (double) a[0] / a[1]);
        });
        
        // Add all items to the heap
        for (int i = 0; i < values.length; i++) {
            pq.add(new int[] { values[i], weights[i] });
        }

        double ans = 0.0;
        // Process items while we have capacity and items
        while (W > 0 && !pq.isEmpty()) {
            int[] item = pq.remove();
            if (item[1] <= W) {
                // Take the whole item if possible
                ans += item[0];
                W -= item[1];
            } else {
                // Take a fraction of the item
                double val = ((double) item[0] / item[1]) * W;
                ans += val;
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] values1 = {60, 100, 120};
        int[] weights1 = {10, 20, 30};
        int W1 = 50;
        System.out.println("Input: values=" + java.util.Arrays.toString(values1) + 
                         ", weights=" + java.util.Arrays.toString(weights1) + 
                         ", W=" + W1);
        System.out.println("Output: " + fractionalKnapsack(values1, weights1, W1));

        // Test Case 2: Edge case - empty arrays
        System.out.println("\nEdge Case - Empty Arrays:");
        int[] values2 = {};
        int[] weights2 = {};
        int W2 = 10;
        System.out.println("Input: values=" + java.util.Arrays.toString(values2) + 
                         ", weights=" + java.util.Arrays.toString(weights2) + 
                         ", W=" + W2);
        System.out.println("Output: " + fractionalKnapsack(values2, weights2, W2));

        // Test Case 3: All items can be taken
        System.out.println("\nAll Items Can Be Taken:");
        int[] values3 = {10, 20, 30};
        int[] weights3 = {5, 5, 5};
        int W3 = 20;
        System.out.println("Input: values=" + java.util.Arrays.toString(values3) + 
                         ", weights=" + java.util.Arrays.toString(weights3) + 
                         ", W=" + W3);
        System.out.println("Output: " + fractionalKnapsack(values3, weights3, W3));
    }
}
