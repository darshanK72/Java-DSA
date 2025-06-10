/*-
 * GeeksForGeeks - Minimum Cost of Ropes
 * 
 * Problem Statement:
 *     Given an array of rope lengths, connect all ropes into one rope. The cost
 *     of connecting two ropes is equal to the sum of their lengths. Find the
 *     minimum cost to connect all ropes.
 * 
 * Input:
 *     - arr[]: Array of integers representing lengths of ropes
 *     - Constraints: 1 ≤ N ≤ 10^5, 1 ≤ arr[i] ≤ 10^5
 * 
 * Output:
 *     - Integer representing minimum cost to connect all ropes
 * 
 * Example:
 *     Input: arr[] = {4, 3, 2, 6}
 *     Output: 29
 * 
 *     Explanation:
 *     Step 1: Connect ropes of length 2 and 3 (cost = 5)
 *     Step 2: Connect ropes of length 4 and 5 (cost = 9)
 *     Step 3: Connect ropes of length 6 and 9 (cost = 15)
 *     Total cost = 5 + 9 + 15 = 29
 */

import java.util.PriorityQueue;

public class j03MinimumRopeCost {
    /*-
     * Approach: Greedy Algorithm using Min-Heap
     * 
     * Intuition:
     * - To minimize total cost, always connect the two smallest ropes first
     * - This ensures that larger ropes are connected fewer times
     * - Using a min-heap to efficiently get the two smallest ropes
     * 
     * Explanation:
     * 1. Create a min-heap and add all rope lengths
     * 2. While heap has more than one rope:
     *    - Remove two smallest ropes (a and b)
     *    - Add their sum to total cost
     *    - Add the new rope (a + b) back to heap
     * 3. Return total cost
     * 
     * Time Complexity: O(N log N) where N is number of ropes
     * - Building heap: O(N)
     * - Each heap operation: O(log N)
     * - Total operations: O(N log N)
     * 
     * Space Complexity: O(N)
     * - PriorityQueue: O(N)
     * 
     * @param arr    Array of rope lengths
     * @return      Minimum cost to connect all ropes
     */
    public static int minCost(int[] arr) {
        // Initialize total cost and min-heap
        int totalCost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Add all rope lengths to min-heap
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        // Connect ropes until only one remains
        while (pq.size() > 1) {
            // Get two smallest ropes
            int a = pq.remove();
            int b = pq.remove();

            // Add connection cost and new rope to heap
            totalCost += (a + b);
            pq.add(a + b);
        }
        return totalCost;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {4, 3, 2, 6};
        System.out.println("Input: " + java.util.Arrays.toString(arr1));
        System.out.println("Minimum cost: " + minCost(arr1));

        // Test Case 2: Single rope
        System.out.println("\nSingle Rope Test:");
        int[] arr2 = {5};
        System.out.println("Input: " + java.util.Arrays.toString(arr2));
        System.out.println("Minimum cost: " + minCost(arr2));

        // Test Case 3: Two ropes
        System.out.println("\nTwo Ropes Test:");
        int[] arr3 = {3, 4};
        System.out.println("Input: " + java.util.Arrays.toString(arr3));
        System.out.println("Minimum cost: " + minCost(arr3));

        // Test Case 4: Equal length ropes
        System.out.println("\nEqual Length Ropes Test:");
        int[] arr4 = {2, 2, 2, 2};
        System.out.println("Input: " + java.util.Arrays.toString(arr4));
        System.out.println("Minimum cost: " + minCost(arr4));
    }
}
