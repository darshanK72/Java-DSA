/**
 * GFG. Mother Vertex in a Graph
 * 
 * Problem Statement:
 *     Given a directed graph with V vertices and adjacency list adj, find a mother vertex. A mother vertex is a vertex from which all other vertices are reachable. If there are multiple possible answers, return any one of them. If no such vertex exists, return -1.
 * 
 * Input:
 *     - V (1 <= V <= 10^4): Number of vertices
 *     - adj: ArrayList<ArrayList<Integer>> adjacency list representing the graph
 * 
 * Output:
 *     - An integer representing the mother vertex index (0-based), or -1 if none exists
 * 
 * Example:
 *     Input: V = 4, adj = [[1, 2], [3], [3], []]
 *     Output: 0
 * 
 *     Explanation:
 *     - Starting from vertex 0, we can reach all other vertices (1, 2, 3).
 *     - No other vertex can reach all others.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class j11MotherVortex {
    /**
     * Approach: DFS + Topological Sort
     * 
     * Intuition:
     * - The last finished vertex in a complete DFS traversal could be a mother vertex.
     * - If such a vertex exists, a DFS from it should visit all vertices.
     * 
     * Explanation:
     * - Step 1: Do a DFS traversal for all unvisited nodes, recording the finish order.
     * - Step 2: The last finished node is a candidate mother vertex.
     * - Step 3: Do a DFS from this candidate. If all nodes are visited, it's a mother vertex; else, return -1.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param V    Number of vertices (1 <= V <= 10^4)
     * @param adj  Adjacency list
     * @return     Index of a mother vertex, or -1 if none exists
     */
    public static int findMotherVertex(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V]; // Track visited nodes
        ArrayList<Integer> out = new ArrayList<>(); // Store finish order
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSort(adj, visited, i, out); // DFS for unvisited node
            }
        }

        if (out.size() < adj.size())
            return -1; // Not all nodes are reachable

        visited = new boolean[V]; // Reset visited for second DFS
        int ans = out.get(out.size() - 1); // Candidate mother vertex
        topoSort(adj, visited, ans, new ArrayList<>()); // DFS from candidate
        for (int i = 0; i < V; i++)
            if (!visited[i])
                return -1; // Not all nodes are reachable from candidate
        return ans;
    }

    /**
     * Helper Method: DFS for Topological Sort
     * 
     * Intuition:
     * - Standard DFS to visit all reachable nodes from a source.
     * - Appends nodes to output list after visiting all descendants (postorder).
     * 
     * Explanation:
     * - Mark node as visited.
     * - Recursively visit all unvisited neighbors.
     * - After all neighbors, add node to output list.
     * 
     * Time Complexity: O(V + E) for a single DFS
     * Space Complexity: O(V) for recursion stack
     * 
     * @param adj      Adjacency list
     * @param visited  Visited array
     * @param src      Current source node
     * @param out      Output list for finish order
     */
    public static void topoSort(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int src, ArrayList<Integer> out) {
        if (visited[src])
            return; // Node already visited
        visited[src] = true; // Mark as visited
        for (int neb : adj.get(src)) {
            if (!visited[neb]) {
                topoSort(adj, visited, neb, out); // Visit all unvisited neighbors
            }
        }
        out.add(src); // Add to output after all descendants
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Cases:");
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        adj1.add(new ArrayList<>(Arrays.asList(1, 2)));
        adj1.add(new ArrayList<>(Arrays.asList(3)));
        adj1.add(new ArrayList<>(Arrays.asList(3)));
        adj1.add(new ArrayList<>());
        System.out.println("Input: V=4, adj=" + adj1 + ", Expected: 0, Output: " + findMotherVertex(4, adj1));

        // Test Case 2: Edge case (no mother vertex)
        System.out.println("\nEdge Cases:");
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>(Arrays.asList(1)));
        adj2.add(new ArrayList<>());
        adj2.add(new ArrayList<>(Arrays.asList(0)));
        System.out.println("Input: V=3, adj=" + adj2 + ", Expected: -1, Output: " + findMotherVertex(3, adj2));

        // Test Case 3: Boundary case (single node)
        System.out.println("\nBoundary Cases:");
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        adj3.add(new ArrayList<>());
        System.out.println("Input: V=1, adj=" + adj3 + ", Expected: 0, Output: " + findMotherVertex(1, adj3));

        // Test Case 4: Special case (cycle)
        System.out.println("\nSpecial Cases:");
        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        adj4.add(new ArrayList<>(Arrays.asList(1)));
        adj4.add(new ArrayList<>(Arrays.asList(2)));
        adj4.add(new ArrayList<>(Arrays.asList(0)));
        System.out.println("Input: V=3, adj=" + adj4 + ", Expected: 2 or 1 or 0, Output: " + findMotherVertex(3, adj4));
    }
}
