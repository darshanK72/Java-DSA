/**
 * LeetCode 1168. Optimize Water Distribution in a Village
 *
 * Problem Statement:
 *     There are n houses in a village, each house can either build a well at a
 *     certain cost or connect to another house via a pipe at a certain cost. Each
 *     well can supply water to its own house only. Each pipe connects two houses
 *     and can supply water to both. Find the minimum total cost to supply water to
 *     all houses.
 *
 * Input:
 *     - n (1 <= n <= 10^4): Number of houses
 *     - k (ignored, for compatibility)
 *     - wells: int[n], wells[i] is the cost to build a well at house i+1
 *     - pipes: int[m][3], each [house1, house2, cost] (1 <= house1, house2 <= n)
 *
 * Output:
 *     - int: Minimum total cost to supply water to all houses
 *
 * Example:
 *     Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 *     Output: 3
 *
 *     Explanation:
 *         Build well at house 1 (cost 1), connect 2-3 (cost 1), connect 1-2 (cost 1)
 *         Total cost = 1 + 1 + 1 = 3
 */

import java.util.*;

public class j03WaterSupplyInVillage {
    /**
     * Helper class to represent an edge in the graph.
     *
     * Intuition:
     * - Encapsulates destination and weight for each edge.
     * - Used for adjacency list representation.
     *
     * Explanation:
     * - Each Edge object stores the 'to' vertex and the edge 'weight'.
     * - Used in adjacency list for efficient traversal.
     *
     * Time Complexity: O(1) per edge
     * Space Complexity: O(1) per edge
     */
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Approach: Prim's Algorithm with Virtual Node
     *
     * Intuition:
     * - Treat each well as a virtual edge from a super node (0) to each house.
     * - Build a graph with n+1 nodes (0 = virtual well node, 1..n = houses).
     * - Use Prim's algorithm to find the MST, which gives the minimum cost.
     *
     * Explanation:
     * - Add all pipes as bidirectional edges.
     * - Add an edge from node 0 to each house with cost = well cost.
     * - Run Prim's algorithm from node 0 to connect all houses with minimum cost.
     *
     * Time Complexity: O((n+m) log n) (heap operations for all edges)
     * Space Complexity: O(n+m) (adjacency list, visited array, heap)
     *
     * @param n      Number of houses (1 <= n <= 10^4)
     * @param k      (ignored, for compatibility)
     * @param wells  int[n]: Cost to build a well at each house
     * @param pipes  int[m][3]: Each [house1, house2, cost]
     * @return       int: Minimum total cost to supply water to all houses
     */
    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        // Build adjacency list for n+1 nodes (0 = virtual well node)
        ArrayList<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < pipes.length; i++) {
            int from = pipes[i][0];
            int to = pipes[i][1];
            int weight = pipes[i][2];
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }
        // Add virtual edges from node 0 to each house for well costs
        for (int i = 0; i < wells.length; i++) {
            adj[0].add(new Edge(i + 1, wells[i]));
            adj[i + 1].add(new Edge(0, wells[i]));
        }
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { 0, 0 }); // Start from virtual node 0
        int cost = 0;
        int count = 0;
        while (!pq.isEmpty() && count < n + 1) {
            int[] node = pq.poll();
            if (visited[node[0]])
                continue; // Skip already included nodes
            visited[node[0]] = true;
            cost += node[1];
            count++;
            for (Edge neb : adj[node[0]]) {
                if (!visited[neb.to])
                    pq.add(new int[] { neb.to, neb.weight });
            }
        }
        // If not all nodes are included, return -1 (should not happen for valid input)
        if (count < n + 1) return -1;
        return cost;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int n1 = 3;
        int[] wells1 = {1,2,2};
        int[][] pipes1 = {{1,2,1},{2,3,1}};
        System.out.println("Input: n=3, wells=[1,2,2], pipes=[[1,2,1],[2,3,1]]");
        System.out.println("Expected: 3, Output: " + supplyWater(n1, 0, wells1, pipes1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int n2 = 1;
        int[] wells2 = {5};
        int[][] pipes2 = {};
        System.out.println("Input: n=1, wells=[5], pipes=[]");
        System.out.println("Expected: 5, Output: " + supplyWater(n2, 0, wells2, pipes2));
        int n3 = 2;
        int[] wells3 = {1,100};
        int[][] pipes3 = {};
        System.out.println("Input: n=2, wells=[1,100], pipes=[]");
        System.out.println("Expected: 101, Output: " + supplyWater(n3, 0, wells3, pipes3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int n4 = 10000;
        int[] wells4 = new int[10000];
        Arrays.fill(wells4, 1);
        int[][] pipes4 = {};
        System.out.println("Input: n=10000, wells=[1,...], pipes=[]");
        System.out.println("Expected: 10000, Output: " + supplyWater(n4, 0, wells4, pipes4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int n5 = 4;
        int[] wells5 = {1,2,2,3};
        int[][] pipes5 = {{1,2,1},{2,3,1},{3,4,1}};
        System.out.println("Input: n=4, wells=[1,2,2,3], pipes=[[1,2,1],[2,3,1],[3,4,1]]");
        System.out.println("Expected: 4, Output: " + supplyWater(n5, 0, wells5, pipes5));
    }
}
