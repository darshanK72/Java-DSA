/**
 * LeetCode 1192. Critical Connections in a Network
 * 
 * Problem Statement:
 *     Given an undirected graph with n nodes labeled from 0 to n-1, find all critical
 *     connections. A critical connection is an edge that, if removed, will make some
 *     nodes unable to reach some other nodes.
 * 
 * Input:
 *     - n: Number of nodes (2 ≤ n ≤ 10^5)
 *     - connections: List of edges [node1, node2]
 *     - All edges are undirected
 * 
 * Output:
 *     - List<List<Integer>>: List of critical connections [node1, node2]
 * 
 * Example:
 *     Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 *     Output: [[1,3]]
 * 
 *     Explanation:
 *     [[0,1], [1,2], [2,0]] forms a cycle
 *     [1,3] is the only critical connection
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class j02CriticalConnections {
    /**
     * Approach: Tarjan's Algorithm
     * 
     * Intuition:
     * - Use DFS to find bridges (critical connections)
     * - Track insertion time and lowest reachable insertion time
     * - Bridge exists if no back edge to ancestors
     * 
     * Explanation:
     * - Step 1: Build adjacency list from connections
     * - Step 2: Initialize tracking arrays:
     *     - ins[]: Insertion time for each node
     *     - lowIns[]: Lowest insertion time reachable
     * - Step 3: Run DFS from each unvisited node:
     *     - Update insertion and low times
     *     - Check for bridges using condition: lowIns[child] > ins[parent]
     * 
     * Time Complexity: O(V + E) where V = nodes, E = edges
     * Space Complexity: O(V) for tracking arrays
     * 
     * @param n           Number of nodes (2 ≤ n ≤ 10^5)
     * @param connections List of undirected edges
     * @return           List of critical connections
     */
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Build adjacency list
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Add edges to adjacency list
        for (List<Integer> conn : connections) {
            int from = conn.get(0);
            int to = conn.get(1);
            adj[from].add(to);    // Add both directions
            adj[to].add(from);    // since graph is undirected
        }

        // Initialize tracking arrays
        int[] insTime = {0};      // Wrapped in array for reference passing
        int[] ins = new int[n];   // Insertion times
        Arrays.fill(ins, -1);     // -1 indicates unvisited
        int[] lowIns = new int[n];// Lowest reachable insertion times

        // Find critical connections using DFS
        List<List<Integer>> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ins[i] == -1) {   // If node not visited
                findCriticalConnections(adj, ins, lowIns, out, insTime, i, -1);
            }
        }
        return out;
    }

    /**
     * Helper Method: DFS to find critical connections
     * 
     * Intuition:
     * - Process each node and its neighbors
     * - Update low values through back edges
     * - Identify bridges when no back path exists
     * 
     * Explanation:
     * - Step 1: Assign insertion and low times
     * - Step 2: Process each neighbor:
     *     - Skip parent to avoid false back edge
     *     - Recursively process unvisited neighbors
     *     - Update low values through back edges
     *     - Check bridge condition
     * 
     * @param adj     Adjacency list representation
     * @param ins     Insertion times array
     * @param lowIns  Lowest reachable insertion times
     * @param out     Result list for critical connections
     * @param insTime Current insertion time (wrapped in array)
     * @param src     Current node being processed
     * @param par     Parent of current node
     */
    private static void findCriticalConnections(ArrayList<Integer>[] adj, int[] ins, int[] lowIns,
            List<List<Integer>> out, int[] insTime, int src, int par) {
        // Assign insertion and low times
        ins[src] = insTime[0];          // Set current insertion time
        lowIns[src] = insTime[0];       // Initialize low value
        insTime[0]++;                   // Increment time for next node

        // Process all neighbors
        for (int neb : adj[src]) {
            if (neb == par)             // Skip parent
                continue;
            else if (ins[neb] == -1) {  // Unvisited neighbor
                // Recursively process neighbor
                findCriticalConnections(adj, ins, lowIns, out, insTime, neb, src);
                // Update low value from neighbor
                lowIns[src] = Math.min(lowIns[src], lowIns[neb]);
                // Check for bridge
                if (lowIns[neb] > ins[src]) {
                    out.add(Arrays.asList(src, neb));
                }
            } else {                    // Back edge to visited node
                // Update low value through back edge
                lowIns[src] = Math.min(lowIns[src], lowIns[neb]);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with one bridge
        System.out.println("\nBasic Test Case:");
        List<List<Integer>> conn1 = Arrays.asList(
            Arrays.asList(0,1),
            Arrays.asList(1,2),
            Arrays.asList(2,0),
            Arrays.asList(1,3)
        );
        System.out.println("Input: n=4, connections=" + conn1);
        System.out.println("Output: " + criticalConnections(4, conn1));
        System.out.println("Expected: [[1,3]]");

        // Test Case 2: No bridges
        System.out.println("\nNo Bridges Test Case:");
        List<List<Integer>> conn2 = Arrays.asList(
            Arrays.asList(0,1),
            Arrays.asList(1,2),
            Arrays.asList(2,0)
        );
        System.out.println("Input: n=3, connections=" + conn2);
        System.out.println("Output: " + criticalConnections(3, conn2));
        System.out.println("Expected: []");

        // Test Case 3: Multiple bridges
        System.out.println("\nMultiple Bridges Test Case:");
        List<List<Integer>> conn3 = Arrays.asList(
            Arrays.asList(0,1),
            Arrays.asList(1,2),
            Arrays.asList(2,3),
            Arrays.asList(3,4),
            Arrays.asList(4,5)
        );
        System.out.println("Input: n=6, connections=" + conn3);
        System.out.println("Output: " + criticalConnections(6, conn3));
        System.out.println("Expected: [[0,1],[1,2],[2,3],[3,4],[4,5]]");
    }
}
