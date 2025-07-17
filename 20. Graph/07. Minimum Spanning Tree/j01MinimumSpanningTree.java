/**
 * GFG Practice. Minimum Spanning Tree (MST) - Prim's Algorithm
 *
 * Problem Statement:
 *     Given a connected, undirected, weighted graph with V vertices and E edges,
 *     find the sum of weights of the edges of the Minimum Spanning Tree (MST).
 *
 * Input:
 *     - V (1 <= V <= 10^4): Number of vertices
 *     - E (0 <= E <= 10^5): Number of edges
 *     - adj: List<List<int[]>> adjacency list, where adj.get(u) contains int[]{v, w}
 *       representing an edge from u to v with weight w (0 <= u, v < V, 1 <= w <= 10^4)
 *
 * Output:
 *     - int: Sum of weights of the MST
 *
 * Example:
 *     Input: V = 4, E = 5, edges = [[0,1,1],[0,2,2],[0,3,2],[1,2,3],[2,3,3]]
 *     Output: 5
 *
 *     Explanation:
 *         MST edges: (0-1, weight 1), (0-2, weight 2), (0-3, weight 2)
 *         Total weight = 1 + 2 + 2 = 5
 */

import java.util.*;

public class j01MinimumSpanningTree {

    /**
     * Approach: Prim's Algorithm using Min-Heap (PriorityQueue)
     *
     * Intuition:
     * - Always pick the minimum weight edge that connects a new vertex to the MST.
     * - Use a min-heap to efficiently get the next minimum edge.
     *
     * Explanation:
     * - Start from vertex 0, push (0,0) to the priority queue.
     * - While the queue is not empty, pop the smallest edge.
     * - If the vertex is already visited, skip.
     * - Otherwise, add its weight to the MST sum and mark as visited.
     * - For all adjacent edges, push them to the queue.
     * - Repeat until all vertices are included.
     *
     * Time Complexity: O((V+E) log V) (each edge/vertex heap operation)
     * Space Complexity: O(V+E) (adjacency list, visited array, heap)
     *
     * @param V    Number of vertices (1 <= V <= 10^4)
     * @param E    Number of edges (0 <= E <= 10^5)
     * @param adj  Adjacency list: List<List<int[]>>, each int[]{v, w}
     * @return     int: Sum of weights of the MST
     */
    public static int spanningTreePrims(int V, int E, List<List<int[]>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { 0, 0 }); // Start from vertex 0 with weight 0
        int ans = 0;
        int count = 0;
        while (!pq.isEmpty() && count < V) {
            int[] node = pq.poll();
            if (visited[node[0]])
                continue; // Skip already included vertices
            visited[node[0]] = true;
            ans += node[1];
            count++;
            for (int[] neb : adj.get(node[0])) {
                if (!visited[neb[0]]) {
                    pq.add(new int[]{neb[0], neb[1]});
                }
            }
        }
        // If not all vertices are included, the graph is not connected
        if (count < V) return -1;
        return ans;
    }

    /**
     * Disjoint Set Union (Union-Find) for Kruskal's Algorithm
     *
     * Intuition:
     * - DSU efficiently tracks which vertices are in the same component.
     * - Path compression and union by size keep operations nearly constant time.
     *
     * Explanation:
     * - parents[i]: If -1, i is a root; otherwise, points to its parent.
     * - ranks[i]: Size of the component rooted at i.
     * - find(x): Recursively finds the root of x, compressing the path.
     * - union(x, y): Merges the smaller component under the larger.
     *
     * Time Complexity: O(α(V)) per operation
     * Space Complexity: O(V)
     *
     * @param n    Number of nodes
     */
    public static class DisjointSetUnion {
        int[] parents;
        int[] ranks;
        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(parents, -1); // Each node is initially its own root
            Arrays.fill(ranks, 1);    // Each component starts with size 1
        }
        /**
         * Finds the root of the node with path compression.
         * If node is not a root, recursively finds and sets its parent to the root.
         * This flattens the tree for future queries.
         */
        public int find(int node) {
            // If node is a root (parent is -1), return node itself
            if (parents[node] == -1)
                return node;
            // Path compression: recursively find root and set parent directly to root
            // This flattens the tree, making future finds faster
            parents[node] = find(parents[node]);
            return parents[node]; // Return the root of the component
        }
        /**
         * Unites the sets containing node1 and node2 using union by size.
         * The smaller tree is merged under the larger to keep the tree shallow.
         */
        public void union(int node1, int node2) {
            // Find the root of node1 (with path compression)
            int node1Parent = find(node1);
            // Find the root of node2 (with path compression)
            int node2Parent = find(node2);
            // If both nodes have the same root, they are already in the same set
            if (node1Parent == node2Parent)
                return;
            // Union by size: attach the smaller tree to the root of the larger tree
            if (ranks[node1Parent] < ranks[node2Parent]) {
                // Make node2Parent the parent of node1Parent
                parents[node1Parent] = node2Parent;
                // Update the size of node2Parent's component
                ranks[node2Parent] += ranks[node1Parent];
            } else {
                // Make node1Parent the parent of node2Parent
                parents[node2Parent] = node1Parent;
                // Update the size of node1Parent's component
                ranks[node1Parent] += ranks[node2Parent];
            }
        }
    }

    /**
     * Approach 2: Kruskal's Algorithm (with Disjoint Set Union)
     *
     * Intuition:
     * - Kruskal's algorithm builds the MST by always picking the smallest edge
     *   that doesn't form a cycle. DSU is used to efficiently check if two nodes
     *   are already connected (i.e., in the same component).
     * - By sorting all edges and greedily adding the smallest, we ensure the MST
     *   has minimal total weight.
     *
     * Explanation:
     * - Collect all edges from the adjacency list into a single list.
     * - Sort the edges by weight in ascending order.
     * - Initialize DSU for V nodes to track connected components.
     * - For each edge, if the endpoints are in different components, add the edge
     *   to the MST and union their components.
     * - Repeat until all nodes are connected (MST complete).
     *
     * Time Complexity: O(E log E + E α(V)) (sorting + DSU ops)
     * Space Complexity: O(E + V) (edge list + DSU arrays)
     *
     * @param V    Number of vertices
     * @param E    Number of edges
     * @param adj  Adjacency list: List<List<int[]>>, each int[]{v, w}
     * @return     int: Sum of weights of the MST
     */
    static int spanningTreeKruksal(int V, int E, List<List<int[]>> adj) {
        // Collect all edges as [from, to, weight]
        ArrayList<int[]> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int[] edge : adj.get(i)) {
                edges.add(new int[]{
                    i,         // from vertex
                    edge[0],   // to vertex
                    edge[1]    // edge weight
                });
            }
        }
        // Sort all edges by weight (ascending)
        Collections.sort(edges, (a, b) -> a[2] - b[2]);
        DisjointSetUnion dsu = new DisjointSetUnion(V); // DSU for cycle detection
        int ans = 0;
        // Iterate through sorted edges
        for (int i = 0; i < edges.size(); i++) {
            int from = edges.get(i)[0];
            int to = edges.get(i)[1];
            int weight = edges.get(i)[2];
            // If from and to are in different components, add edge to MST
            if (dsu.find(from) != dsu.find(to)) {
                ans += weight;      // Add edge weight to MST total
                dsu.union(from, to); // Merge the two components
            }
            // If already connected, skip to avoid cycle
        }
        return ans;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int V1 = 4, E1 = 5;
        int[][] edges1 = {{0,1,1},{0,2,2},{0,3,2},{1,2,3},{2,3,3}};
        List<List<int[]>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());
        for (int[] e : edges1) {
            adj1.get(e[0]).add(new int[]{e[1], e[2]});
            adj1.get(e[1]).add(new int[]{e[0], e[2]});
        }
        System.out.println("Input: V=4, E=5, edges=" + Arrays.deepToString(edges1));
        System.out.println("Expected: 5, Output: " + spanningTreePrims(V1, E1, adj1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int V2 = 1, E2 = 0;
        List<List<int[]>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>());
        System.out.println("Input: V=1, E=0, edges=[]");
        System.out.println("Expected: 0, Output: " + spanningTreePrims(V2, E2, adj2));
        int V3 = 2, E3 = 0;
        List<List<int[]>> adj3 = new ArrayList<>();
        adj3.add(new ArrayList<>()); adj3.add(new ArrayList<>());
        System.out.println("Input: V=2, E=0, edges=[]");
        System.out.println("Expected: -1, Output: " + spanningTreeKruksal(V3, E3, adj3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int V4 = 10000, E4 = 0;
        List<List<int[]>> adj4 = new ArrayList<>();
        for (int i = 0; i < V4; i++) adj4.add(new ArrayList<>());
        System.out.println("Input: V=10000, E=0, edges=[]");
        System.out.println("Expected: -1, Output: " + spanningTreeKruksal(V4, E4, adj4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int V5 = 3, E5 = 3;
        int[][] edges5 = {{0,1,1},{1,2,1},{0,2,2}};
        List<List<int[]>> adj5 = new ArrayList<>();
        for (int i = 0; i < V5; i++) adj5.add(new ArrayList<>());
        for (int[] e : edges5) {
            adj5.get(e[0]).add(new int[]{e[1], e[2]});
            adj5.get(e[1]).add(new int[]{e[0], e[2]});
        }
        System.out.println("Input: V=3, E=3, edges=" + Arrays.deepToString(edges5));
        System.out.println("Expected: 2, Output: " + spanningTreePrims(V5, E5, adj5));
    }
}
