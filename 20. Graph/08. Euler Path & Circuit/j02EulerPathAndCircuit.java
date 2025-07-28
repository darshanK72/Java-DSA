/*-
 * GeeksForGeeks: Euler Path and Circuit
 * 
 * Problem Statement:
 *     Given an undirected graph, determine if it has an Euler Path or Circuit.
 *     - Euler Circuit: Path that visits every edge exactly once and returns to start
 *     - Euler Path: Path that visits every edge exactly once (may not return to start)
 * 
 * Input:
 *     - V: Number of vertices (1 ≤ V ≤ 10^4)
 *     - adj[]: Array of adjacency lists representing the graph
 * 
 * Output:
 *     - 2: Graph has Euler Circuit
 *     - 1: Graph has Euler Path but no Circuit
 *     - 0: Graph has neither Euler Path nor Circuit
 * 
 * Example:
 *     Input: V = 3, adj = [[1,2], [0,2], [0,1]]
 *     Output: 2
 * 
 *     Explanation:
 *     Graph is connected and all vertices have even degree.
 *     Possible Euler Circuit: 0 -> 1 -> 2 -> 0
 */

import java.util.List;

public class j02EulerPathAndCircuit {
    
    /*-
     * Approach: Degree Counting with DFS
     * 
     * Intuition:
     * - For Euler Circuit: All vertices must have even degree
     * - For Euler Path: Exactly 2 vertices can have odd degree
     * - Graph must be connected (single component)
     * 
     * Explanation:
     * - Step 1: Count vertices with odd degrees
     * - Step 2: Check graph connectivity using DFS
     * - Step 3: Based on odd degree count:
     *     - 0 odd degrees + connected = Euler Circuit
     *     - 2 odd degrees = Euler Path
     *     - Otherwise = Neither
     * 
     * Time Complexity: O(V + E) where V = vertices, E = edges
     * Space Complexity: O(V) for visited array
     * 
     * @param V      Number of vertices (1 ≤ V ≤ 10^4)
     * @param adj    Array of adjacency lists
     * @return      2 for Circuit, 1 for Path, 0 for neither
     */
    public static int isEulerCircuit(int V, List<Integer>[] adj) {
        // Count vertices with odd degrees
        int oddDegrees = 0;
        for (int i = 0; i < V; i++) {
            if (adj[i].size() % 2 == 1)
                oddDegrees++;
        }

        // Check for Euler Circuit
        if (oddDegrees == 0) {
            // Verify graph connectivity using DFS
            boolean[] visited = new boolean[V];
            dfs(adj, visited, 0);
            
            // Check if all vertices are reachable
            for (int i = 0; i < V; i++) {
                if (!visited[i])
                    return 0;  // Graph is not connected
            }
            return 2;  // Euler Circuit exists
        } 
        // Check for Euler Path
        else if (oddDegrees == 2) {
            return 1;  // Euler Path exists
        } 
        // Neither exists
        else {
            return 0;
        }
    }

    /*-
     * Helper Method: DFS traversal
     * 
     * Intuition:
     * - Use DFS to check if graph is connected
     * - Mark visited vertices to track reachability
     * 
     * Explanation:
     * - Step 1: Mark current vertex as visited
     * - Step 2: Recursively visit all unvisited neighbors
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for recursion stack
     */
    private static void dfs(List<Integer>[] adj, boolean[] visited, int src) {
        // Mark current vertex as visited
        visited[src] = true;
        
        // Visit all unvisited neighbors
        for (int neb : adj[src]) {
            if (!visited[neb]) {
                dfs(adj, visited, neb);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Euler Circuit
        System.out.println("\nEuler Circuit Test Case:");
        List<Integer>[] adj1 = new List[3];
        // Initialize adjacency lists and add edges...
        System.out.println("Expected: 2");
        System.out.println("Output: " + isEulerCircuit(3, adj1));

        // Test Case 2: Euler Path
        System.out.println("\nEuler Path Test Case:");
        List<Integer>[] adj2 = new List[4];
        // Initialize adjacency lists and add edges...
        System.out.println("Expected: 1");
        System.out.println("Output: " + isEulerCircuit(4, adj2));

        // Test Case 3: Neither
        System.out.println("\nNeither Path nor Circuit Test Case:");
        List<Integer>[] adj3 = new List[3];
        // Initialize adjacency lists and add edges...
        System.out.println("Expected: 0");
        System.out.println("Output: " + isEulerCircuit(3, adj3));
    }
}