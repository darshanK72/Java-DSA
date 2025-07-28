/**
 * GeeksForGeeks: Check for Euler Circuit
 * 
 * Problem Statement:
 *     Given an undirected graph, determine if it has an Euler Circuit.
 *     An Euler Circuit is a path that visits every edge exactly once and 
 *     returns to the starting vertex.
 * 
 * Input:
 *     - v: Number of vertices (1 ≤ v ≤ 10^4)
 *     - adj: ArrayList of adjacency lists representing the graph
 * 
 * Output:
 *     - boolean: true if Euler Circuit exists, false otherwise
 * 
 * Example:
 *     Input: v = 3, adj = [[1,2], [0,2], [0,1]]
 *     Output: true
 * 
 *     Explanation:
 *     Graph has 3 vertices, all with even degree (degree 2).
 *     Euler Circuit exists: 0 -> 1 -> 2 -> 0
 */

import java.util.ArrayList;

public class j01CheckEulerCircuit {
    /**
     * Approach: Degree Counting
     * 
     * Intuition:
     * - For Euler Circuit to exist, all vertices must have even degree
     * - This is because each vertex must be entered and exited equal times
     * 
     * Explanation:
     * - Step 1: Count vertices with odd degree
     * - Step 2: If count is 0, Euler Circuit exists
     * - Step 3: Otherwise, no Euler Circuit possible
     * 
     * Time Complexity: O(V) where V = number of vertices
     * Space Complexity: O(1) only using counter variable
     * 
     * @param v    Number of vertices (1 ≤ v ≤ 10^4)
     * @param adj  ArrayList of adjacency lists
     * @return    true if Euler Circuit exists, false otherwise
     */
    public static boolean isEularCircuitExist(int v, ArrayList<ArrayList<Integer>> adj) {
        // Count vertices with odd degrees
        int oddDegrees = 0;
        for (int i = 0; i < v; i++) {
            // If vertex has odd number of edges
            if (adj.get(i).size() % 2 == 1)
                oddDegrees++;
        }

        // Euler Circuit exists only if all vertices have even degree
        return oddDegrees == 0;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case - Circuit exists
        System.out.println("\nBasic Test Case (Circuit exists):");
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            adj1.add(new ArrayList<>());
        }
        // Add edges: 0-1, 1-2, 2-0
        adj1.get(0).add(1); adj1.get(1).add(0);
        adj1.get(1).add(2); adj1.get(2).add(1);
        adj1.get(2).add(0); adj1.get(0).add(2);
        System.out.println("Expected: true");
        System.out.println("Output: " + isEularCircuitExist(3, adj1));

        // Test Case 2: No circuit - Odd degrees
        System.out.println("\nOdd Degrees Test Case:");
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            adj2.add(new ArrayList<>());
        }
        // Add edges: 0-1, 1-2
        adj2.get(0).add(1); adj2.get(1).add(0);
        adj2.get(1).add(2); adj2.get(2).add(1);
        System.out.println("Expected: false");
        System.out.println("Output: " + isEularCircuitExist(3, adj2));

        // Test Case 3: Single vertex
        System.out.println("\nSingle Vertex Test Case:");
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        adj3.add(new ArrayList<>());
        System.out.println("Expected: true");
        System.out.println("Output: " + isEularCircuitExist(1, adj3));
    }
}
