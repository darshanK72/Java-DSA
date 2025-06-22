/**
 * LeetCode 547. Number of Provinces
 * 
 * Problem Statement:
 *     There are n cities. Some of them are connected, while some are not. If 
 *     city a is connected directly with city b, and city b is connected 
 *     directly with city c, then city a is connected indirectly with city c.
 *     A province is a group of directly or indirectly connected cities and no 
 *     other cities outside of the group. You are given an n x n matrix 
 *     isConnected where isConnected[i][j] = 1 if the ith city and the jth 
 *     city are directly connected, and isConnected[i][j] = 0 otherwise.
 *     Return the total number of provinces.
 * 
 * Input:
 *     - isConnected: n x n matrix where isConnected[i][j] = 1 if cities i and 
 *       j are directly connected, 0 otherwise (1 <= n <= 200)
 * 
 * Output:
 *     - Integer representing the total number of provinces
 * 
 * Example:
 *     Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 *     Output: 2
 * 
 *     Explanation:
 *     Matrix representation:
 *     [1,1,0]  City 0 is connected to City 1
 *     [1,1,0]  City 1 is connected to City 0  
 *     [0,0,1]  City 2 is isolated
 *     
 *     Cities 0 and 1 form one province, City 2 forms another province.
 *     Total provinces = 2
 * 
 *     Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 *     Output: 3
 * 
 *     Explanation:
 *     All cities are isolated, so each city is its own province.
 */

import java.util.*;

public class j01NumberOfProvinces {

    /**
     * Approach: Depth-First Search (DFS)
     * 
     * Intuition:
     * - A province is essentially a connected component in the graph
     * - We need to find all connected components using DFS
     * - Each DFS traversal will mark all cities in one province as visited
     * - The number of DFS calls needed equals the number of provinces
     * 
     * Explanation:
     * - Step 1: Convert adjacency matrix to adjacency list for efficient 
     *   traversal
     * - Step 2: Initialize visited array to track processed cities
     * - Step 3: Iterate through all cities, if unvisited, start DFS to mark 
     *   all cities in that province
     * - Step 4: Count each DFS call as one province
     * 
     * Time Complexity: O(n²) where n is the number of cities
     * - Converting matrix to adjacency list: O(n²)
     * - DFS traversal: O(n) since each city is visited once
     * - Total: O(n²)
     * 
     * Space Complexity: O(n²) 
     * - Adjacency list: O(n²) in worst case (fully connected graph)
     * - Visited array: O(n)
     * - Recursion stack: O(n) in worst case
     * 
     * @param isConnected    n x n adjacency matrix representing city 
     *                       connections (1 <= n <= 200)
     * @return              Total number of provinces
     */
    public static int findCircleNum(int[][] isConnected) {
        // Handle edge case: null or empty input
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        
        int n = isConnected.length;
        
        // Convert adjacency matrix to adjacency list for efficient traversal
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // Build adjacency list from matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    adj[i].add(j);
                }
            }
        }

        // Initialize visited array to track processed cities
        boolean[] visited = new boolean[n];
        int provinces = 0;
        
        // Iterate through all cities to find connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Start DFS from unvisited city to mark all cities in province
                markCitiesInProvince(adj, visited, i);
                provinces++; // Increment province count for each DFS call
            }
        }

        return provinces;
    }

    /**
     * Helper Method: DFS to mark all cities in a province
     * 
     * Intuition:
     * - Use DFS to explore all cities connected to the source city
     * - Mark each visited city to avoid revisiting
     * - Recursively visit all neighbors of current city
     * 
     * Explanation:
     * - Base case: If city is already visited, return
     * - Mark current city as visited
     * - Recursively visit all unvisited neighbors
     * - This ensures all cities in the same province are marked
     * 
     * Time Complexity: O(n) where n is the number of cities
     * Space Complexity: O(n) for recursion stack depth
     * 
     * @param adj       Adjacency list representation of the graph
     * @param visited   Boolean array to track visited cities
     * @param src       Source city to start DFS from
     */
    private static void markCitiesInProvince(ArrayList<Integer>[] adj, 
                                           boolean[] visited, int src) {
        // Base case: if city is already visited, return
        if (visited[src]) {
            return;
        }
        
        // Mark current city as visited
        visited[src] = true;
        
        // Recursively visit all unvisited neighbors
        for (int neighbor : adj[src]) {
            if (!visited[neighbor]) {
                markCitiesInProvince(adj, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        int[][] test1 = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println("Input: [[1,1,0],[1,1,0],[0,0,1]], Expected: 2, Output: " + 
                          findCircleNum(test1));
        
        int[][] test2 = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println("Input: [[1,0,0],[0,1,0],[0,0,1]], Expected: 3, Output: " + 
                          findCircleNum(test2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: null, Expected: 0, Output: " + 
                          findCircleNum(null));
        
        int[][] empty = {};
        System.out.println("Input: [], Expected: 0, Output: " + 
                          findCircleNum(empty));
        
        int[][] single = {{1}};
        System.out.println("Input: [[1]], Expected: 1, Output: " + 
                          findCircleNum(single));

        // Test Case 3: All cities connected
        System.out.println("\nAll Cities Connected:");
        int[][] allConnected = {{1,1,1},{1,1,1},{1,1,1}};
        System.out.println("Input: [[1,1,1],[1,1,1],[1,1,1]], Expected: 1, Output: " + 
                          findCircleNum(allConnected));

        // Test Case 4: Larger test case
        System.out.println("\nLarger Test Case:");
        int[][] largeTest = {
            {1,0,0,1},
            {0,1,1,0},
            {0,1,1,1},
            {1,0,1,1}
        };
        System.out.println("Input: 4x4 matrix, Expected: 1, Output: " + 
                          findCircleNum(largeTest));

        // Test Case 5: Complex disconnected graph
        System.out.println("\nComplex Disconnected Graph:");
        int[][] complex = {
            {1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},
            {0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},
            {0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},
            {1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},
            {0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},
            {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}
        };
        System.out.println("Input: 15x15 complex matrix, Expected: 3, Output: " + 
                          findCircleNum(complex));
    }
}
