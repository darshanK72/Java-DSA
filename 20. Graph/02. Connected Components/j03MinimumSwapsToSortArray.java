/**
 * Geeks For Geeks - Minimum Swaps to Sort an Array
 *
 * Problem Statement:
 *     Given an array of n distinct integers, find the minimum number of swaps required to sort the array in ascending order.
 *
 * Input:
 *     - arr (1 <= arr.length <= 10^5): Array of distinct integers
 *
 * Output:
 *     - Integer: Minimum number of swaps required to sort the array
 *
 * Example:
 *     Input: arr = [4, 3, 2, 1]
 *     Output: 2
 *
 *     Explanation:
 *     Swap 4 and 1, then swap 3 and 2. Array becomes [1,2,3,4].
 */

import java.util.*;

public class j03MinimumSwapsToSortArray {

    /**
     * Approach: Graph Cycle Decomposition
     *
     * Intuition:
     * - Each element's position in the sorted array can be mapped to its original index.
     * - The problem reduces to finding cycles in this mapping; each cycle of size k requires (k-1) swaps.
     *
     * Explanation:
     * - Step 1: Map each value to its original index.
     * - Step 2: Sort the array to get the target positions.
     * - Step 3: Build a graph where each node points to its original and sorted positions.
     * - Step 4: For each unvisited node, use DFS to find the size of the cycle and add (size-1) to the swap count.
     *
     * Time Complexity: O(n log n) (for sorting and cycle detection)
     * Space Complexity: O(n) (for visited array and graph)
     *
     * @param arr   Array of distinct integers
     * @return      Minimum number of swaps to sort the array
     */
    public static int minSwaps(int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Map value to original index
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i); // Store original index for each value
        }

        int[] sorted = arr.clone(); // Clone array to sort
        Arrays.sort(sorted); // Sort the array

        ArrayList<Integer>[] adj = new ArrayList[arr.length]; // Adjacency list for cycles
        for (int i = 0; i < arr.length; i++)
            adj[i] = new ArrayList<>(); // Initialize adjacency list

        // Build the graph: connect sorted index to original index and vice versa
        for (int i = 0; i < arr.length; i++) {
            int oldIndex = map.get(sorted[i]); // Original index of sorted value
            adj[i].add(oldIndex); // Add edge from sorted index to original
            adj[oldIndex].add(i); // Add edge from original index to sorted
        }

        boolean[] visited = new boolean[arr.length]; // Track visited nodes
        int swaps = 0; // Initialize swap count
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) { // If node not visited
                swaps += getGraphSize(adj, visited, i) - 1; // Each cycle of size k needs (k-1) swaps
            }
        }
        return swaps; // Return total swaps
    }

    /**
     * Helper Method: DFS to find cycle size
     *
     * Intuition:
     * - Traverse the cycle, counting the number of nodes.
     *
     * Explanation:
     * - For each unvisited neighbor, recurse and accumulate size.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param adj      Adjacency list
     * @param visited  Visited array
     * @param node     Current node
     * @return         Size of the cycle
     */
    public static int getGraphSize(ArrayList<Integer>[] adj, boolean[] visited, int node) {
        if (visited[node]) return 0; // Already visited
        int size = 1; // Start with current node
        visited[node] = true; // Mark as visited
        for (int neb : adj[node]) {
            size += getGraphSize(adj, visited, neb); // Recurse on neighbors
        }
        return size; // Return total size of cycle
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[] arr1 = {4, 3, 2, 1};
        System.out.println("Input: [4,3,2,1], Expected: 2, Output: " + minSwaps(arr1));
        int[] arr2 = {1, 5, 4, 3, 2};
        System.out.println("Input: [1,5,4,3,2], Expected: 2, Output: " + minSwaps(arr2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[] arr3 = {1};
        System.out.println("Input: [1], Expected: 0, Output: " + minSwaps(arr3));
        int[] arr4 = {2, 1};
        System.out.println("Input: [2,1], Expected: 1, Output: " + minSwaps(arr4));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[] arr5 = new int[10];
        for (int i = 0; i < 10; i++) arr5[i] = 10 - i;
        System.out.println("Input: [10..1], Output: " + minSwaps(arr5));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[] arr6 = {2, 3, 4, 1, 5};
        System.out.println("Input: [2,3,4,1,5], Output: " + minSwaps(arr6));
    }
}
