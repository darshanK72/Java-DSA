/**
 * LeetCode/GeeksForGeeks. Cycle Detection in Undirected Graph
 * 
 * Problem Statement:
 *     Given an undirected graph with V vertices and a list of edges, determine
 *     if the graph contains a cycle. The graph can be disconnected. Each edge
 *     is represented as a pair of vertices [u, v].
 * 
 * Input:
 *     - V (1 <= V <= 10^5): Number of vertices in the graph
 *     - edges (0 <= edges.length <= 2*10^5): List of undirected edges
 *       Each edge is a pair [u, v] (0 <= u, v < V)
 * 
 * Output:
 *     - true if the graph contains a cycle, false otherwise
 * 
 * Example:
 *     Input: V = 4, edges = [[0,1],[1,2],[2,3],[3,1]]
 *     Output: true
 *     Explanation:
 *         0 - 1
 *             | \
 *             2--3
 *         There is a cycle: 1-2-3-1
 * 
 *     Input: V = 4, edges = [[0,1],[1,2],[2,3]]
 *     Output: false
 *     Explanation:
 *         0-1-2-3 (no cycle)
 */

import java.util.*;

public class j01CycleInUndirectedGraph {

    /**
     * Approach 1: DFS Based Cycle Detection
     * 
     * Intuition:
     * - Traverse each component using DFS. If we revisit a node that is not the
     *   parent, a cycle exists.
     * - Use a parent parameter to avoid trivial backtracking.
     * 
     * Explanation:
     * - Build adjacency list from edge list.
     * - For each unvisited node, start DFS.
     * - If DFS finds a back edge (visited node not parent), return true.
     * - If all components checked and no cycle, return false.
     * 
     * Time Complexity: O(V + E) where V = vertices, E = edges
     * Space Complexity: O(V + E) for adjacency list and visited array
     * 
     * @param V      Number of vertices (1 <= V <= 10^5)
     * @param edges  Edge list (0 <= edges.length <= 2*10^5)
     * @return       true if cycle exists, false otherwise
     */
    public static boolean isCycleDFS(int V, int[][] edges) {
        // Initialize visited array
        boolean[] visited = new boolean[V];
        // Build adjacency list
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Add edge both ways (undirected)
            adj[to].add(from);
        }
        // Check each component
        for (int i = 0; i < V; i++) {
            if (!visited[i] && dfs(adj, visited, i, -1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper Method: DFS for Cycle Detection
     * 
     * Intuition:
     * - Recursively visit neighbors. If a visited neighbor is not the parent,
     *   a cycle is found.
     * 
     * Explanation:
     * - Mark current node as visited.
     * - For each neighbor, skip parent. If neighbor is visited, cycle exists.
     * - Otherwise, recurse on neighbor.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) recursion stack
     * 
     * @param adj     Adjacency list
     * @param visited Visited array
     * @param src     Current node
     * @param pre     Parent node
     * @return        true if cycle found, false otherwise
     */
    public static boolean dfs(ArrayList<Integer>[] adj, boolean[] visited, int src, int pre) {
        if (visited[src]) // Node already visited: cycle detected
            return true;
        visited[src] = true; // Mark node as visited
        for (int neb : adj[src]) {
            if (neb == pre)
                continue; // Skip parent
            else if (dfs(adj, visited, neb, src)) {
                return true; // Cycle found in recursion
            }
        }
        return false; // No cycle found
    }

    /**
     * Helper Class: Pair
     * Used for BFS traversal to store node and its parent
     */
    static class Pair {
        int node;
        int parent;
        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    /**
     * Approach 2: BFS Based Cycle Detection
     * 
     * Intuition:
     * - Use BFS to traverse each component. Track parent for each node.
     * - If a visited neighbor is not the parent, a cycle exists.
     * 
     * Explanation:
     * - Build adjacency list from edge list.
     * - For each unvisited node, start BFS.
     * - Use a queue to process nodes and track their parent.
     * - If a visited node is found that is not the parent, return true.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     * 
     * @param V      Number of vertices
     * @param edges  Edge list
     * @return       true if cycle exists, false otherwise
     */
    public static boolean isCycleBFS(int V, int[][] edges) {
        // Build adjacency list
        ArrayList<Integer>[] adj = new ArrayList[V];
        for(int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to);
            adj[to].add(from);
        }
        // Track visited nodes
        boolean[] visited = new boolean[V];
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                if(bfs(adj,visited,i)) return true;
            }
        }
        return false;
    }

    /**
     * Helper Method: BFS for Cycle Detection
     * 
     * Intuition:
     * - Use a queue to traverse nodes. If a visited node is found that is not
     *   the parent, a cycle exists.
     * 
     * Explanation:
     * - Enqueue source node with parent -1.
     * - For each node, check neighbors. If neighbor is visited and not parent,
     *   cycle exists. Otherwise, enqueue neighbor.
     * 
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     * 
     * @param adj     Adjacency list
     * @param visited Visited array
     * @param src     Source node
     * @return        true if cycle found, false otherwise
     */
    public static boolean bfs(ArrayList<Integer>[] adj,boolean[] visited,int src){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src,-1)); // Start BFS from src
        while(!queue.isEmpty()){
            Pair curr = queue.poll(); 
            visited[curr.node] = true;
            for(int neb : adj[curr.node]){
                if(curr.parent == neb) continue; // Skip parent
                else if(visited[neb]) return true; // // Cycle detected
                else queue.add(new Pair(neb,curr.node));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] edges1 = {{0,1},{1,2},{2,3},{3,1}};
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3],[3,1]], Expected: true, Output: " + isCycleDFS(4, edges1));
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3],[3,1]], Expected: true, Output: " + isCycleBFS(4, edges1));

        int[][] edges2 = {{0,1},{1,2},{2,3}};
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3]], Expected: false, Output: " + isCycleDFS(4, edges2));
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3]], Expected: false, Output: " + isCycleBFS(4, edges2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] edges3 = {};
        System.out.println("Input: V=1, edges=[], Expected: false, Output: " + isCycleDFS(1, edges3));
        System.out.println("Input: V=1, edges=[], Expected: false, Output: " + isCycleBFS(1, edges3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] edges4 = {{0,0}};
        System.out.println("Input: V=1, edges=[[0,0]], Expected: true, Output: " + isCycleDFS(1, edges4));
        System.out.println("Input: V=1, edges=[[0,0]], Expected: true, Output: " + isCycleBFS(1, edges4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] edges5 = {{0,1},{1,2},{2,0}};
        System.out.println("Input: V=3, edges=[[0,1],[1,2],[2,0]], Expected: true, Output: " + isCycleDFS(3, edges5));
        System.out.println("Input: V=3, edges=[[0,1],[1,2],[2,0]], Expected: true, Output: " + isCycleBFS(3, edges5));
    }
}
