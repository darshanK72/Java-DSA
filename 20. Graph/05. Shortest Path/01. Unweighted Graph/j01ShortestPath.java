/*-
 * Shortest Path in Unweighted Graph (BFS)
 *
 * Problem Statement:
 *     Given an undirected graph with n nodes and m edges, find the shortest path
 *     from source node s to target node t. Since the graph is unweighted, the
 *     shortest path is the path with the minimum number of edges.
 *
 * Input:
 *     - int[][] edges: List of edges (each edge as [from, to])
 *     - int n: Number of nodes (1-indexed)
 *     - int m: Number of edges
 *     - int s: Source node
 *     - int t: Target node
 *
 * Output:
 *     - LinkedList<Integer>: Shortest path from s to t (empty if no path exists)
 *
 * Example:
 *     Input: edges = [[1,2],[2,3],[3,4],[1,4]], n = 4, m = 4, s = 1, t = 4
 *     Output: [1, 4]
 *
 *     Explanation:
 *     The shortest path from 1 to 4 is directly via edge [1,4].
 */

import java.util.*;

public class j01ShortestPath {
    /*-
     * Helper Class: Pair
     *
     * Intuition:
     * - Used to store a node, its parent, and distance for BFS traversal.
     *   This allows us to keep track of the path and distance as we traverse.
     *
     */
    static class Pair {
        int node;
        int parent;
        int dist;

        Pair(int n, int p, int d) {
            this.node = n;
            this.parent = p;
            this.dist = d;
        }
    }

    /*-
     * Approach: Breadth-First Search (BFS)
     *
     * Intuition:
     * - Use BFS to find the shortest path in an unweighted graph. BFS guarantees
     *   that the first time we reach a node, we have found the shortest path to it.
     * - Keep track of the parent of each node to reconstruct the path.
     *
     * Explanation:
     * - Build the adjacency list for the graph from the edge list.
     * - Use a queue to perform BFS starting from the source node.
     * - For each node, store its parent and mark it as visited.
     * - When we reach the target node, reconstruct the path using the parent array.
     * - If the target is not reached, return an empty list.
     *
     * Time Complexity: O(N + M), where N = number of nodes, M = number of edges
     * Space Complexity: O(N + M) for adjacency list and O(N) for queue
     *
     * @param edges  List of edges
     * @param n      Number of nodes
     * @param m      Number of edges
     * @param s      Source node
     * @param t      Target node
     * @return       Shortest path from s to t
     */
    public static LinkedList<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
        // Build adjacency list for the graph
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            adj[from].add(to); // Add edge from 'from' to 'to'
            adj[to].add(from); // Add edge from 'to' to 'from' (undirected)
        }

        LinkedList<Integer> out = new LinkedList<>();
        int[] parents = new int[n + 1]; // Track parent of each node
        boolean[] visited = new boolean[n + 1]; // Track visited nodes

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(s, -1, 0)); // Start from source with no parent
        parents[s] = -1; // Source has no parent
        visited[s] = true; // Mark source as visited

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (curr.node == t) {
                // Target reached, generate path
                out = generateOutput(parents, curr.node);
                break;
            }
            // Explore all neighbors
            for (int neb : adj[curr.node]) {
                if (!visited[neb]) {
                    parents[neb] = curr.node; // Set parent
                    visited[neb] = true; // Mark as visited
                    queue.add(new Pair(neb, curr.node, curr.dist + 1)); // Add to queue
                }
            }
        }

        return out; // Return shortest path (empty if no path exists)
    }

    /*-
     * Helper Method: Generate Output Path
     *
     * Intuition:
     * - Reconstruct the shortest path from the parent array by following
     *   the chain of parents from target to source.
     *
     * Explanation:
     * - Start from the target node and follow the parent chain.
     * - Add each node to the front of the list to maintain the correct order.
     * - Stop when we reach the source node (parent = -1).
     *
     * Time Complexity: O(L), where L = length of the path
     * Space Complexity: O(L) for the output list
     *
     * @param parents  Parent array
     * @param src      Target node
     * @return         Shortest path from source to target
     */
    private static LinkedList<Integer> generateOutput(int[] parents, int src) {
        LinkedList<Integer> out = new LinkedList<>();
        int curr = src;
        while (curr != -1) {
            out.addFirst(curr); // Add to front to maintain order
            curr = parents[curr]; // Move to parent
        }
        return out; // Return the path
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] e1 = {{1,2},{2,3},{3,4},{1,4}};
        System.out.println("edges=[[1,2],[2,3],[3,4],[1,4]], n=4, m=4, s=1, t=4, Expected: [1,4], Output: " + shortestPath(e1, 4, 4, 1, 4));

        int[][] e2 = {{1,2},{2,3},{3,4}};
        System.out.println("edges=[[1,2],[2,3],[3,4]], n=4, m=3, s=1, t=4, Expected: [1,2,3,4], Output: " + shortestPath(e2, 4, 3, 1, 4));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] e3 = {};
        System.out.println("edges=[], n=2, m=0, s=1, t=2, Expected: [], Output: " + shortestPath(e3, 2, 0, 1, 2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] e4 = {{1,2}};
        System.out.println("edges=[[1,2]], n=2, m=1, s=1, t=2, Expected: [1,2], Output: " + shortestPath(e4, 2, 1, 1, 2));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] e5 = {{1,2},{2,3},{3,1}};
        System.out.println("edges=[[1,2],[2,3],[3,1]], n=3, m=3, s=1, t=3, Expected: [1,2,3], Output: " + shortestPath(e5, 3, 3, 1, 3));
    }
}
