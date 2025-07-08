/**
 * Problem Source: Custom (Graph Edge Reversal Problem)
 *
 * Problem Statement:
 *     You are given a directed graph of ‘N’ nodes and ‘M’ edges. Also, you have two nodes ‘A’ and ‘B’.
 *     Your task is to make at least one valid path from ‘A’ to ‘B’ by doing the below operations a minimum number of times:
 *         1. Choose two nodes ‘X’ and ‘Y’, such that there exists an edge from ‘X’ to ‘Y’.
 *         2. Delete edge ‘X’ to ‘Y’.
 *         3. Add edge ‘Y’ to ‘X’.
 *     You need to print the minimum operations that you have done.
 *
 * Input:
 *     - T (int): Number of test cases
 *     - For each test case:
 *         - N (int): Number of nodes
 *         - M (int): Number of edges
 *         - A (int), B (int): Start and end nodes
 *         - M lines: Each line has two integers U, V (edge from U to V)
 *
 * Output:
 *     - For each test case, print the minimum number of edge reversals required to make a path from A to B
 *
 * Constraints:
 *     1 <= T <= 5
 *     1 <= N <= 3000
 *     1 <= M <= min(3000, N*(N-1)/2)
 *     0 <= X, Y, U, V <= N - 1
 *
 * Example:
 *     Input:
 *     2
 *     4 3
 *     0 3
 *     0 1
 *     0 2
 *     3 2
 *     5 4
 *     0 4
 *     0 1
 *     1 3
 *     4 1
 *     2 0
 *     Output:
 *     1
 *     1
 *
 *     Explanation:
 *     See problem description for detailed explanation and diagrams.
 */

import java.util.*;

public class j10ReverseEdges {
    /**
     * Edge class to represent a directed edge with a reversal cost
     */
    static class Edge {
        int to;
        int weight; // 0 if original direction, 1 if reversed
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Approach: 0-1 BFS (Double-ended queue)
     *
     * Intuition:
     * - Model the problem as a shortest path where traversing an original edge costs 0,
     *   and traversing a reversed edge costs 1 (i.e., you must reverse it).
     * - Use 0-1 BFS to efficiently find the minimum number of reversals needed from start to end.
     *
     * Explanation:
     * - For each edge U->V, add (U->V, 0) and (V->U, 1) to the adjacency list.
     * - Use a deque to process nodes: push to front if using a 0-cost edge, back if 1-cost.
     * - Track minimum cost to reach each node.
     * - The answer is the minimum cost to reach the end node.
     *
     * Time Complexity: O(N + M)
     * Space Complexity: O(N + M)
     *
     * @param n       Number of nodes
     * @param start   Start node
     * @param end     End node
     * @param edgeList List of edges (each edge is [from, to])
     * @return Minimum number of edge reversals to make a path from start to end
     */
    public static int reverseEdges(int n, int start, int end, ArrayList<ArrayList<Integer>> edgeList) {
        ArrayList<Edge>[] adj = new ArrayList[n]; // Adjacency list
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (ArrayList<Integer> edge : edgeList) {
            int from = edge.get(0);
            int to = edge.get(1);
            adj[from].add(new Edge(to, 0)); // Original direction, no reversal needed
            adj[to].add(new Edge(from, 1)); // Reversed direction, reversal needed
        }

        Deque<int[]> deque = new ArrayDeque<>(); // [node, cost]
        deque.addLast(new int[] { start, 0 }); // Start from 'start' node with 0 reversals
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity

        while (!deque.isEmpty()) {
            int[] node = deque.removeFirst(); // Get current node and cost
            if (node[1] >= dist[node[0]])
                continue; // Skip if already found a better path
            dist[node[0]] = node[1]; // Update minimum cost to reach this node
            for (Edge neb : adj[node[0]]) {
                if (neb.weight == 0) {
                    // If edge is original direction, push to front (0-cost)
                    deque.addFirst(new int[] { neb.to, node[1] });
                } else {
                    // If edge is reversed, push to back (1-cost)
                    deque.addLast(new int[] { neb.to, node[1] + 1 });
                }
            }
        }
        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end]; // Return -1 if unreachable
    }

    public static void main(String[] args) {
        // Test Case 1: Sample Input 1
        int n1 = 4, start1 = 0, end1 = 3;
        ArrayList<ArrayList<Integer>> edgeList1 = new ArrayList<>();
        edgeList1.add(new ArrayList<>(Arrays.asList(0, 1)));
        edgeList1.add(new ArrayList<>(Arrays.asList(0, 2)));
        edgeList1.add(new ArrayList<>(Arrays.asList(3, 2)));
        System.out.println("Sample Test Case 1:");
        // Should return 1
        System.out.println("Expected: 1, Output: " + reverseEdges(n1, start1, end1, edgeList1));

        // Test Case 2: Sample Input 2
        int n2 = 5, start2 = 0, end2 = 4;
        ArrayList<ArrayList<Integer>> edgeList2 = new ArrayList<>();
        edgeList2.add(new ArrayList<>(Arrays.asList(0, 1)));
        edgeList2.add(new ArrayList<>(Arrays.asList(1, 3)));
        edgeList2.add(new ArrayList<>(Arrays.asList(4, 1)));
        edgeList2.add(new ArrayList<>(Arrays.asList(2, 0)));
        System.out.println("\nSample Test Case 2:");
        // Should return 1
        System.out.println("Expected: 1, Output: " + reverseEdges(n2, start2, end2, edgeList2));

        // Test Case 3: No reversal needed
        int n3 = 6, start3 = 0, end3 = 5;
        ArrayList<ArrayList<Integer>> edgeList3 = new ArrayList<>();
        edgeList3.add(new ArrayList<>(Arrays.asList(0, 1)));
        edgeList3.add(new ArrayList<>(Arrays.asList(0, 2)));
        edgeList3.add(new ArrayList<>(Arrays.asList(2, 3)));
        edgeList3.add(new ArrayList<>(Arrays.asList(2, 4)));
        edgeList3.add(new ArrayList<>(Arrays.asList(4, 5)));
        edgeList3.add(new ArrayList<>(Arrays.asList(5, 3)));
        System.out.println("\nNo Reversal Needed:");
        // Should return 0
        System.out.println("Expected: 0, Output: " + reverseEdges(n3, start3, end3, edgeList3));

        // Test Case 4: Unreachable
        int n4 = 3, start4 = 0, end4 = 2;
        ArrayList<ArrayList<Integer>> edgeList4 = new ArrayList<>();
        edgeList4.add(new ArrayList<>(Arrays.asList(0, 1)));
        System.out.println("\nUnreachable Case:");
        // Should return -1
        System.out.println("Expected: -1, Output: " + reverseEdges(n4, start4, end4, edgeList4));
    }
}
