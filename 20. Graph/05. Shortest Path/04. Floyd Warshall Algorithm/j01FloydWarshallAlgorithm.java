/**
 * GFG/LeetCode. Floyd-Warshall Algorithm (All-Pairs Shortest Path)
 *
 * Problem Statement:
 *     Given a directed weighted graph represented as an adjacency matrix, find the
 *     shortest distances between every pair of vertices. The graph may contain
 *     negative weight edges but no negative weight cycles.
 *
 * Input:
 *     - dist: 2D array (n x n) where dist[i][j] is the weight of the edge from i to j
 *       (use a large value, e.g., 1e8, to represent no direct edge)
 *
 * Output:
 *     - The input matrix dist is updated in-place to contain the shortest distances
 *       between every pair of vertices.
 *
 * Example:
 *     Input: dist = [
 *         [0, 3, 1e8, 7],
 *         [8, 0, 2, 1e8],
 *         [5, 1e8, 0, 1],
 *         [2, 1e8, 1e8, 0]
 *     ]
 *     Output: dist = [
 *         [0, 3, 5, 6],
 *         [5, 0, 2, 3],
 *         [3, 4, 0, 1],
 *         [2, 5, 7, 0]
 *     ]
 *
 *     Explanation:
 *     The shortest path from i to j is computed for all pairs using intermediate nodes.
 */

public class j01FloydWarshallAlgorithm {

    /**
     * Approach: Floyd-Warshall Algorithm (Dynamic Programming)
     *
     * Intuition:
     * - The Floyd-Warshall algorithm computes shortest paths between all pairs of
     *   vertices in a weighted graph (with positive or negative weights, but no
     *   negative cycles).
     * - The core idea is to iteratively improve the shortest path between every
     *   pair (i, j) by considering each vertex k as an intermediate node.
     * - For each pair (i, j), we check if the path i -> k -> j is shorter than the
     *   current known path i -> j, and update accordingly.
     *
     * Explanation:
     * - Step 1: Initialize the dist matrix with direct edge weights (or inf if no edge).
     * - Step 2: For each possible intermediate node k (0 to n-1):
     *     - For each source node i (0 to n-1):
     *         - For each destination node j (0 to n-1):
     *             - If dist[i][k] + dist[k][j] < dist[i][j], update dist[i][j].
     *             - This means the shortest path from i to j can be improved by going through k.
     * - Step 3: After all iterations, dist[i][j] contains the shortest distance from i to j.
     *
     * Time Complexity: O(n^3), where n is the number of vertices (triple nested loop)
     * Space Complexity: O(1) extra (in-place), O(n^2) for the dist matrix
     *
     * @param dist  2D array (n x n) representing edge weights; updated in-place
     * @return      None (dist is updated in-place)
     */
    public static void floydWarshall(int[][] dist) {
        int n = dist.length;
        int inf = (int) 1e8; // Use a large value to represent infinity (no edge)
        // For each possible intermediate node 'k'
        for (int k = 0; k < n; k++) {
            // For each possible source node 'i'
            for (int i = 0; i < n; i++) {
                // For each possible destination node 'j'
                for (int j = 0; j < n; j++) {
                    // If either i->k or k->j is unreachable, skip
                    if (dist[i][k] == inf || dist[k][j] == inf)
                        continue;
                    // If going through k offers a shorter path from i to j, update it
                    dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                if (val == (int)1e8) System.out.print("INF ");
                else System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int inf = (int) 1e8;
        int[][] dist1 = {
            {0, 3, inf, 7},
            {8, 0, 2, inf},
            {5, inf, 0, 1},
            {2, inf, inf, 0}
        };
        floydWarshall(dist1);
        System.out.println("Expected: 0 3 5 6 | 5 0 2 3 | 3 4 0 1 | 2 5 7 0");
        printMatrix(dist1);

        // Test Case 2: Edge case - disconnected graph
        System.out.println("\nEdge Cases:");
        int[][] dist2 = {
            {0, inf, inf},
            {inf, 0, inf},
            {inf, inf, 0}
        };
        floydWarshall(dist2);
        System.out.println("Expected: diagonal 0s, rest INF");
        printMatrix(dist2);

        // Test Case 3: Boundary case - single node
        System.out.println("\nBoundary Cases:");
        int[][] dist3 = {
            {0}
        };
        floydWarshall(dist3);
        System.out.println("Expected: 0");
        printMatrix(dist3);

        // Test Case 4: Special case - negative edge weights (no negative cycle)
        System.out.println("\nSpecial Cases:");
        int[][] dist4 = {
            {0, 1, inf, inf},
            {inf, 0, -2, inf},
            {inf, inf, 0, -1},
            {4, inf, inf, 0}
        };
        floydWarshall(dist4);
        System.out.println("Expected: shortest paths with negative edges");
        printMatrix(dist4);
    }
}