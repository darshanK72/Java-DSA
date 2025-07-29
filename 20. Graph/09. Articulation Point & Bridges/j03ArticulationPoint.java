/**
 * GeeksForGeeks: Articulation Point - I
 * 
 * Problem Statement:
 *     Given an undirected connected graph, find all articulation points. 
 *     An articulation point (or cut vertex) is a vertex which, when removed, 
 *     makes the graph disconnected (or increases the number of connected components).
 * 
 * Input:
 *     - V: Number of vertices (1 ≤ V ≤ 10^4)
 *     - adj: ArrayList of adjacency lists representing the graph
 * 
 * Output:
 *     - ArrayList<Integer>: List of articulation points in sorted order
 *     - Return [-1] if no articulation points exist
 * 
 * Example:
 *     Input: V = 5, adj = [[2,3,1], [0], [0,4], [0], [2]]
 *     Output: [0,2]
 * 
 *     Explanation:
 *     Removing vertex 0 or 2 disconnects the graph
 *     0 splits graph into components: [1] and [2,3,4]
 *     2 splits graph into components: [0,1,3] and [4]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class j03ArticulationPoint {
    /**
     * Approach: Tarjan's Algorithm for Articulation Points
     * 
     * Intuition:
     * - Use DFS to identify vertices that split graph when removed
     * - Track insertion times and lowest reachable insertion times
     * - Special handling needed for root node
     * 
     * Explanation:
     * - Step 1: Initialize tracking arrays for insertion and low times
     * - Step 2: Use HashSet to store articulation points (avoid duplicates)
     * - Step 3: Run DFS from each unvisited vertex
     * - Step 4: Return sorted list of articulation points
     * 
     * Time Complexity: O(V + E) where V = vertices, E = edges
     * Space Complexity: O(V) for tracking arrays
     * 
     * @param V    Number of vertices (1 ≤ V ≤ 10^4)
     * @param adj  ArrayList of adjacency lists
     * @return    Sorted list of articulation points
     */
    public static ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        // Initialize tracking arrays
        int[] ins = new int[V];         // Insertion times
        Arrays.fill(ins, -1);           // -1 indicates unvisited
        int[] lowIns = new int[V];      // Lowest reachable insertion times
        
        // Track articulation points (HashSet for uniqueness)
        HashSet<Integer> articulationPoints = new HashSet<>();
        
        // Track insertion time (wrapped in array for reference passing)
        int[] insTime = {0};
        
        // Find articulation points from each unvisited vertex
        for (int i = 0; i < V; i++) {
            if (ins[i] == -1) {
                findArticulationPoint(adj, ins, lowIns, articulationPoints, i, -1, insTime);
            }
        }
        
        // Prepare sorted output list
        ArrayList<Integer> out = new ArrayList<>();
        if (articulationPoints.size() == 0) {
            out.add(-1);                // No articulation points found
        } else {
            out.addAll(articulationPoints);
            Collections.sort(out);       // Return points in sorted order
        }
        return out;
    }

    /**
     * Helper Method: DFS to find articulation points
     * 
     * Intuition:
     * - Process each vertex and its children in DFS tree
     * - Track number of children for root vertex
     * - Use low values to detect back edges
     * 
     * Explanation:
     * - Step 1: Set insertion and low times for current vertex
     * - Step 2: Process each neighbor:
     *     - Skip parent to avoid false back edge
     *     - Count children for root vertex
     *     - Update low values through tree and back edges
     * - Step 3: Check articulation point conditions:
     *     - Non-root: lowIns[child] >= ins[vertex]
     *     - Root: children > 1
     * 
     * @param adj                Adjacency list representation
     * @param ins               Insertion times array
     * @param lowIns            Lowest reachable insertion times
     * @param articulationPoints Set to store articulation points
     * @param src               Current vertex being processed
     * @param parent            Parent of current vertex
     * @param insTime           Current insertion time
     */
    private static void findArticulationPoint(
            ArrayList<ArrayList<Integer>> adj, 
            int[] ins, 
            int[] lowIns,
            HashSet<Integer> articulationPoints, 
            int src, 
            int parent, 
            int[] insTime) {
            
        int children = 0;                // Count children in DFS tree
        
        // Set insertion and low times
        ins[src] = insTime[0];
        lowIns[src] = insTime[0];
        insTime[0]++;
        
        // Process all neighbors
        for (int neighbor : adj.get(src)) {
            if (neighbor == parent)      // Skip parent
                continue;
                
            if (ins[neighbor] == -1) {   // Unvisited neighbor
                children++;              // Increment children count
                
                // Recursively process neighbor
                findArticulationPoint(adj, ins, lowIns, articulationPoints, 
                                    neighbor, src, insTime);
                
                // Update low value from child
                lowIns[src] = Math.min(lowIns[src], lowIns[neighbor]);
                
                // Check articulation point conditions
                if (parent != -1 && lowIns[neighbor] >= ins[src]) {
                    articulationPoints.add(src);  // Non-root articulation point
                }
                if (parent == -1 && children > 1) {
                    articulationPoints.add(src);  // Root articulation point
                }
            } else {
                // Update low value through back edge
                lowIns[src] = Math.min(lowIns[src], ins[neighbor]);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with articulation points
        System.out.println("\nBasic Test Case:");
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj1.add(new ArrayList<>());
        }
        // Add edges: 0-1, 0-2, 0-3, 2-4
        adj1.get(0).add(1); adj1.get(1).add(0);
        adj1.get(0).add(2); adj1.get(2).add(0);
        adj1.get(0).add(3); adj1.get(3).add(0);
        adj1.get(2).add(4); adj1.get(4).add(2);
        System.out.println("Input: V=5, edges=[[0,1],[0,2],[0,3],[2,4]]");
        System.out.println("Output: " + articulationPoints(5, adj1));
        System.out.println("Expected: [0,2]");

        // Test Case 2: Cycle with no articulation points
        System.out.println("\nCycle Test Case:");
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            adj2.add(new ArrayList<>());
        }
        // Add edges: 0-1, 1-2, 2-0
        adj2.get(0).add(1); adj2.get(1).add(0);
        adj2.get(1).add(2); adj2.get(2).add(1);
        adj2.get(2).add(0); adj2.get(0).add(2);
        System.out.println("Input: V=3, edges=[[0,1],[1,2],[2,0]]");
        System.out.println("Output: " + articulationPoints(3, adj2));
        System.out.println("Expected: [-1]");

        // Test Case 3: Line graph (all internal vertices are articulation points)
        System.out.println("\nLine Graph Test Case:");
        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj3.add(new ArrayList<>());
        }
        // Add edges: 0-1, 1-2, 2-3
        adj3.get(0).add(1); adj3.get(1).add(0);
        adj3.get(1).add(2); adj3.get(2).add(1);
        adj3.get(2).add(3); adj3.get(3).add(2);
        System.out.println("Input: V=4, edges=[[0,1],[1,2],[2,3]]");
        System.out.println("Output: " + articulationPoints(4, adj3));
        System.out.println("Expected: [1,2]");
    }
}
