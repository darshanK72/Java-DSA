/**
 * LeetCode 261. Graph Valid Tree
 * 
 * Problem Statement:
 *     Given n nodes labeled from 0 to n - 1 and a list of undirected edges, write a function to check whether these edges make up a valid tree.
 *     A valid tree must be connected and acyclic.
 * 
 * Input:
 *     - edges (int[][]): List of undirected edges, where each edge is [u, v]
 *     - n (1 <= n <= 2000): Number of nodes
 *     - m (0 <= m <= n*(n-1)/2): Number of edges
 * 
 * Output:
 *     - boolean: true if the graph is a valid tree, false otherwise
 * 
 * Example:
 *     Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 *     Output: true
 *     
 *     Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 *     Output: false
 *     Explanation: The second example contains a cycle.
 */

import java.util.*;

public class j02IsGraphValidTree {
    /**
     * Approach: DFS for Cycle Detection and Connected Components
     * 
     * Intuition:
     * - A valid tree must be connected (one component) and acyclic (no cycles).
     * - Use DFS to check for cycles and count connected components.
     * 
     * Explanation:
     * - Build adjacency list for the undirected graph.
     * - For each unvisited node, start DFS:
     *   - If a cycle is detected, return false.
     *   - Count the number of connected components; if more than one, return false.
     * - If no cycles and only one component, return true.
     * 
     * Time Complexity: O(N + M), where N = number of nodes, M = number of edges
     * Space Complexity: O(N + M), for adjacency list and visited array
     * 
     * @param edges      List of undirected edges
     * @param n          Number of nodes
     * @param m          Number of edges
     * @return           True if the graph is a valid tree, false otherwise
     */
    public static Boolean checkGraph(int[][] edges, int n, int m) {
        ArrayList<Integer>[] adj = new ArrayList[n]; // Adjacency list for graph
        for(int i = 0; i < n; i++) adj[i] = new ArrayList<>(); // Initialize adjacency list
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Add undirected edge
            adj[to].add(from); // Add undirected edge
        }
        boolean[] visited = new boolean[n]; // Visited array for DFS
        int components = 0; // Count of connected components
        for(int i = 0; i < n; i++){
            if(visited[i]) continue; // Skip already visited nodes
            components++; // New component found
            if(checkCycle(adj,visited,i,-1)){
                return false; // Cycle detected, not a tree
            }
            if(components > 1) return false; // More than one component, not a tree
        }
        return true; // Connected and acyclic, valid tree
    }

    /**
     * Helper Method: DFS Cycle Detection
     * 
     * Intuition:
     * - Recursively visit nodes, marking them as visited.
     * - If a visited node is encountered (other than the parent), a cycle exists.
     * 
     * Explanation:
     * - For each neighbor, skip the parent node.
     * - If a neighbor is already visited, a cycle is detected.
     * 
     * Time Complexity: O(N + M)
     * Space Complexity: O(N)
     * 
     * @param adj      Adjacency list
     * @param visited  Visited array
     * @param src      Current node
     * @param pre      Parent node
     * @return         True if cycle detected, false otherwise
     */
    public static boolean checkCycle(ArrayList<Integer>[] adj, boolean[] visited, int src, int pre){
        if(visited[src]) return true; // Node already visited, cycle detected
        visited[src] = true; // Mark node as visited
        for(int neb : adj[src]){
            if(neb == pre) continue; // Skip parent node
            else if(checkCycle(adj,visited,neb,src)) return true; // Recurse for neighbors
        }
        return false; // No cycle detected from this node
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=5, edges=[[0,1],[0,2],[0,3],[1,4]], Output: " + checkGraph(new int[][]{{0,1},{0,2},{0,3},{1,4}}, 5, 4));
        System.out.println("Input: n=5, edges=[[0,1],[1,2],[2,3],[1,3],[1,4]], Output: " + checkGraph(new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}, 5, 5));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=1, edges=[], Output: " + checkGraph(new int[][]{}, 1, 0));
        System.out.println("Input: n=2, edges=[], Output: " + checkGraph(new int[][]{}, 2, 0));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=2000, edges=[], Output: " + checkGraph(new int[][]{}, 2000, 0));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: n=4, edges=[[0,1],[2,3]], Output: " + checkGraph(new int[][]{{0,1},{2,3}}, 4, 2));
        System.out.println("Input: n=3, edges=[[0,1],[1,2],[2,0]], Output: " + checkGraph(new int[][]{{0,1},{1,2},{2,0}}, 3, 3));
    }
}
