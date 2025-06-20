/**
 * GeeksForGeeks. Graph BFS Traversal
 *
 * Problem Statement:
 *     Given an adjacency list representation of an undirected graph with N nodes
 *     (numbered from 0 to N-1), perform a Breadth-First Search (BFS) traversal
 *     starting from node 0 and return the order of nodes visited.
 *
 * Input:
 *     - adj: ArrayList<ArrayList<Integer>> adjacency list of the graph
 *       (0 <= node < N, 1 <= N <= 10^4)
 *
 * Output:
 *     - ArrayList<Integer>: List of nodes in the order they are visited in BFS
 *
 * Example:
 *     Input: adj = [[1,2,3],[0],[0,4],[0],[2]]
 *     Output: [0,1,2,3,4]
 *
 *     Explanation:
 *     BFS starts at node 0, visits its neighbors 1, 2, 3, then visits 4 from 2.
 */

import java.util.*;

public class j07GraphBFSTraversal {
    /**
     * Approach: Standard BFS using Queue
     *
     * Intuition:
     * - Use a queue to explore nodes level by level.
     * - Mark nodes as visited to avoid revisiting.
     *
     * Explanation:
     * - Start from node 0, enqueue it, and mark as visited.
     * - While the queue is not empty, dequeue a node, add to output.
     * - For each unvisited neighbor, enqueue and mark as visited.
     * - Continue until all reachable nodes are visited.
     *
     * Time Complexity: O(N + E), where N is number of nodes, E is number of edges.
     * Space Complexity: O(N) for visited array and queue.
     *
     * @param adj    Adjacency list of the graph (0 <= node < N)
     * @return       List of nodes in BFS order
     */
    public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> out = new ArrayList<>(); // Stores BFS traversal order
        Queue<Integer> queue = new LinkedList<>();  // Queue for BFS
        boolean[] visited = new boolean[adj.size()]; // Track visited nodes
        queue.add(0); // Start BFS from node 0
        while (!queue.isEmpty()) {
            int node = queue.poll(); // Dequeue node
            if (!visited[node])
                out.add(node); // Add node to output if not visited
            visited[node] = true; // Mark node as visited
            for (int neb : adj.get(node)) {
                if (!visited[neb]) {
                    queue.add(neb); // Enqueue unvisited neighbors
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        adj1.add(new ArrayList<>(Arrays.asList(1,2,3)));
        adj1.add(new ArrayList<>(Arrays.asList(0)));
        adj1.add(new ArrayList<>(Arrays.asList(0,4)));
        adj1.add(new ArrayList<>(Arrays.asList(0)));
        adj1.add(new ArrayList<>(Arrays.asList(2)));
        System.out.println("Input: [[1,2,3],[0],[0,4],[0],[2]], Expected: [0,1,2,3,4], Output: " + bfs(adj1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>()); // Single node, no edges
        System.out.println("Input: [[]], Expected: [0], Output: " + bfs(adj2));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) adj3.add(new ArrayList<>()); // Disconnected nodes
        System.out.println("Input: [[],[],[],[],[]], Expected: [0], Output: " + bfs(adj3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        adj4.add(new ArrayList<>(Arrays.asList(1)));
        adj4.add(new ArrayList<>(Arrays.asList(2)));
        adj4.add(new ArrayList<>(Arrays.asList(3)));
        adj4.add(new ArrayList<>(Arrays.asList(4)));
        adj4.add(new ArrayList<>());
        System.out.println("Input: [[1],[2],[3],[4],[]], Expected: [0,1,2,3,4], Output: " + bfs(adj4));
    }
}
