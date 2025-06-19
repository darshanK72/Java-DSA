/**
 * Custom Example. Graph Representation (Adjacency List)
 * 
 * Problem Statement:
 *     Implement a graph using adjacency list representation. Provide methods
 *     to add edges and print the graph. The graph is undirected and nodes are
 *     labeled from 1 to N.
 * 
 * Input:
 *     - n (1 <= n <= 10^5): Number of nodes
 *     - edges (0 <= edges <= n*(n-1)/2): Number of edges
 *     - Next 'edges' lines: Each line contains two integers 'from' and 'to'
 *       (1 <= from, to <= n) representing an undirected edge
 * 
 * Output:
 *     - Print the adjacency list for each node in the format:
 *       node->{neighbor1,neighbor2,...}
 * 
 * Example:
 *     Input: 5 4
 *            1 2
 *            1 3
 *            2 4
 *            5 2
 *     Output:
 *         1->{2,3,}
 *         2->{1,4,5,}
 *         3->{1,}
 *         4->{2,}
 *         5->{2,}
 * 
 *     Explanation:
 *     - Node 1 is connected to 2 and 3
 *     - Node 2 is connected to 1, 4, and 5
 *     - Node 3 is connected to 1
 *     - Node 4 is connected to 2
 *     - Node 5 is connected to 2
 */

import java.util.*;

public class j01GraphRepresentation {

    /**
     * Graph class: Adjacency List Representation
     * 
     * Intuition:
     * - Use an array of ArrayLists to store neighbors for each node.
     * - Each index represents a node, and its list contains all adjacent nodes.
     * 
     * Explanation:
     * - The constructor initializes the adjacency list for all nodes.
     * - addEdge adds an undirected edge by updating both nodes' lists.
     * - printGraph prints the adjacency list for each node.
     * 
     * Time Complexity:
     * - addEdge: O(1) per edge
     * - printGraph: O(n + e), n = nodes, e = edges
     * Space Complexity: O(n + e)
     */
    static class Graph {
        int nodes; // Number of nodes in the graph
        ArrayList<Integer>[] adj; // Adjacency list

        /**
         * @param n Number of nodes (1-based indexing)
         */
        public Graph(int n) {
            this.nodes = n;
            adj = new ArrayList[this.nodes + 1];
            for (int i = 0; i <= this.nodes; i++) {
                adj[i] = new ArrayList<Integer>();
            }
        }

        /**
         * Adds an undirected edge between 'from' and 'to'.
         * @param from Source node (1-based)
         * @param to   Destination node (1-based)
         */
        public void addEdge(int from, int to) {
            // Add 'to' to 'from's adjacency list
            adj[from].add(to);
            // Add 'from' to 'to's adjacency list (undirected)
            adj[to].add(from);
        }

        /**
         * Prints the adjacency list of the graph.
         */
        public void printGraph() {
            for (int i = 1; i <= this.nodes; i++) {
                System.out.print(i + "->{");
                for (int node : this.adj[i]) {
                    System.out.print(node + ",");
                }
                System.out.println("}");
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        Graph g1 = new Graph(5);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 4);
        g1.addEdge(5, 2);
        g1.printGraph();

        // Test Case 2: Edge case (no edges)
        System.out.println("\nEdge Case (No edges):");
        Graph g2 = new Graph(3);
        g2.printGraph();

        // Test Case 3: Boundary case (single node)
        System.out.println("\nBoundary Case (Single node):");
        Graph g3 = new Graph(1);
        g3.printGraph();

        // Test Case 4: Special case (fully connected small graph)
        System.out.println("\nSpecial Case (Fully connected):");
        Graph g4 = new Graph(3);
        g4.addEdge(1, 2);
        g4.addEdge(1, 3);
        g4.addEdge(2, 3);
        g4.printGraph();
    }
}