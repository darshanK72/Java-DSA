/**
 * LeetCode 1514. Path with Maximum Probability
 * 
 * Problem Statement:
 *     You are given an undirected weighted graph of n nodes (0-indexed), 
 *     represented by an edge list where edges[i] = [a, b] is an undirected 
 *     edge connecting the nodes a and b with a probability of success of 
 *     traversing that edge succProb[i]. Given two nodes start and end, 
 *     find the path with the maximum probability of success to go from 
 *     start to end and return its success probability. If there is no 
 *     path from start to end, return 0.
 * 
 * Input:
 *     - n (1 <= n <= 10^4): number of nodes in the graph
 *     - edges (0 <= edges.length <= 4*10^4): array of undirected edges
 *     - succProb (0 < succProb[i] <= 1): probability of success for each edge
 *     - start_node (0 <= start_node < n): starting node
 *     - end_node (0 <= end_node < n): destination node
 * 
 * Output:
 *     - Maximum probability of success from start to end node
 *     - Return 0.0 if no path exists
 * 
 * Example:
 *     Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], 
 *            start_node = 0, end_node = 2
 *     Output: 0.25
 * 
 *     Explanation:
 *     There are two paths from node 0 to node 2:
 *     - 0 → 1 → 2: probability = 0.5 * 0.5 = 0.25
 *     - 0 → 2: probability = 0.2
 *     The maximum probability is 0.25.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class j04PathWithMaxProbability {

    /**
     * Edge class to represent graph edges with destination and probability
     * Used for building adjacency list and priority queue operations
     */
    static class Edge {
        int to;
        double weight;

        Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Approach: Modified Dijkstra's Algorithm for Maximum Probability Path
     * 
     * Intuition:
     * - Since we need to find the path with maximum probability (not minimum 
     *   distance), we modify Dijkstra's algorithm to work with probabilities
     * - Probability of a path is the product of individual edge probabilities
     * - We use a max-heap (priority queue) to always process the node with 
     *   highest current probability first
     * - This ensures we find the optimal path when we reach the destination
     * 
     * Explanation:
     * - Step 1: Build adjacency list representation of the undirected graph
     * - Step 2: Initialize probability array with minimum values and set 
     *   start node probability to 1 (100% chance of being at start)
     * - Step 3: Use max-heap to process nodes in order of highest probability
     * - Step 4: For each neighbor, calculate new probability as product of 
     *   current path probability and edge probability
     * - Step 5: Update neighbor's probability if new path has higher probability
     * - Step 6: Return the maximum probability found for end node
     * 
     * Time Complexity: O((V + E) * log V) where V is number of nodes and E is 
     *                  number of edges, due to priority queue operations
     * Space Complexity: O(V + E) for adjacency list and priority queue storage
     * 
     * @param n           Number of nodes in the graph (1 <= n <= 10^4)
     * @param edges       Array of undirected edges [from, to]
     * @param succProb    Probability of success for each edge (0 < p <= 1)
     * @param start_node  Starting node index (0 <= start < n)
     * @param end_node    Destination node index (0 <= end < n)
     * @return           Maximum probability of success from start to end
     */
    public static double maxProbability(int n, int[][] edges, double[] succProb, 
                                       int start_node, int end_node) {
        // Build adjacency list representation of the undirected graph
        ArrayList<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // Add edges to adjacency list (undirected graph - add both directions)
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }

        // Initialize max-heap to process nodes with highest probability first
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
            if (a.weight > b.weight) return -1;  // Higher probability first
            else if (b.weight > a.weight) return 1;
            else return 0;
        });
        
        // Initialize probability array with minimum values
        double[] prob = new double[n];
        Arrays.fill(prob, Double.MIN_VALUE);
        
        // Start with start node having 100% probability
        pq.add(new Edge(start_node, 1.0));
        prob[start_node] = 1.0;

        // Process nodes in order of highest probability
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            
            // Explore all neighbors of current node
            for (Edge neighbor : adj[current.to]) {
                // Calculate new probability as product of current path and edge
                double newProbability = current.weight * neighbor.weight;
                
                // Update if we found a better path to this neighbor
                if (prob[neighbor.to] < newProbability) {
                    prob[neighbor.to] = newProbability;
                    pq.add(new Edge(neighbor.to, newProbability));
                }
            }
        }

        // Return 0 if no path exists, otherwise return maximum probability
        if (prob[end_node] == Double.MIN_VALUE) {
            return 0.0;
        }
        return prob[end_node];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        int[][] edges1 = {{0,1}, {1,2}, {0,2}};
        double[] prob1 = {0.5, 0.5, 0.2};
        System.out.println("Input: n=3, edges=[[0,1],[1,2],[0,2]], prob=[0.5,0.5,0.2], start=0, end=2");
        System.out.println("Expected: 0.25, Output: " + maxProbability(3, edges1, prob1, 0, 2));
        
        int[][] edges2 = {{0,1}, {1,2}, {0,2}};
        double[] prob2 = {0.5, 0.5, 0.3};
        System.out.println("Input: n=3, edges=[[0,1],[1,2],[0,2]], prob=[0.5,0.5,0.3], start=0, end=2");
        System.out.println("Expected: 0.3, Output: " + maxProbability(3, edges2, prob2, 0, 2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] edges3 = {};
        double[] prob3 = {};
        System.out.println("Input: n=2, edges=[], prob=[], start=0, end=1");
        System.out.println("Expected: 0.0, Output: " + maxProbability(2, edges3, prob3, 0, 1));
        
        int[][] edges4 = {{0,1}};
        double[] prob4 = {0.5};
        System.out.println("Input: n=2, edges=[[0,1]], prob=[0.5], start=0, end=0");
        System.out.println("Expected: 1.0, Output: " + maxProbability(2, edges4, prob4, 0, 0));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] edges5 = {{0,1}};
        double[] prob5 = {1.0};
        System.out.println("Input: n=2, edges=[[0,1]], prob=[1.0], start=0, end=1");
        System.out.println("Expected: 1.0, Output: " + maxProbability(2, edges5, prob5, 0, 1));
        
        int[][] edges6 = {{0,1}};
        double[] prob6 = {0.0};
        System.out.println("Input: n=2, edges=[[0,1]], prob=[0.0], start=0, end=1");
        System.out.println("Expected: 0.0, Output: " + maxProbability(2, edges6, prob6, 0, 1));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] edges7 = {{0,1}, {1,2}, {2,3}, {0,3}};
        double[] prob7 = {0.1, 0.1, 0.1, 0.9};
        System.out.println("Input: n=4, edges=[[0,1],[1,2],[2,3],[0,3]], prob=[0.1,0.1,0.1,0.9], start=0, end=3");
        System.out.println("Expected: 0.9, Output: " + maxProbability(4, edges7, prob7, 0, 3));
        
        int[][] edges8 = {{0,1}, {1,2}, {2,3}, {3,4}, {0,4}};
        double[] prob8 = {0.9, 0.9, 0.9, 0.9, 0.1};
        System.out.println("Input: n=5, edges=[[0,1],[1,2],[2,3],[3,4],[0,4]], prob=[0.9,0.9,0.9,0.9,0.1], start=0, end=4");
        System.out.println("Expected: 0.6561, Output: " + maxProbability(5, edges8, prob8, 0, 4));
    }
}
