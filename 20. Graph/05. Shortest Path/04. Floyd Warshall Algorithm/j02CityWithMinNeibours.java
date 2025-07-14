/**
 * LeetCode 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 *
 * Problem Statement:
 *     There are n cities numbered from 0 to n-1. You are given edges where each edge
 *     is [from, to, weight] representing a bidirectional road. Given a distanceThreshold,
 *     find the city with the smallest number of cities that are reachable within the
 *     threshold distance. If there are multiple such cities, return the city with the
 *     greatest number.
 *
 * Input:
 *     - n (2 <= n <= 100): Number of cities
 *     - edges: 2D array of edges [from, to, weight]
 *     - distanceThreshold (1 <= distanceThreshold <= 10^4): Maximum allowed distance
 *
 * Output:
 *     - The city with the smallest number of reachable cities within the threshold
 *       (if tie, return the city with the greatest number)
 *
 * Example:
 *     Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 *     Output: 3
 *
 *     Explanation:
 *     City 3 can reach cities 2 and 1 with distances 1 and 4, respectively. It has the
 *     smallest number of reachable cities (2), and in case of tie, the largest index.
 */

public class j02CityWithMinNeibours {

    /**
     * Approach: Floyd-Warshall Algorithm for All-Pairs Shortest Paths
     *
     * Intuition:
     * - We need to find, for each city, how many other cities are reachable within
     *   the given distance threshold.
     * - Floyd-Warshall computes shortest paths between all pairs efficiently.
     * - After computing all-pairs shortest paths, count for each city the number of
     *   cities within the threshold.
     *
     * Explanation:
     * - Step 1: Initialize adjacency matrix with direct edge weights (inf if no edge).
     * - Step 2: Run Floyd-Warshall to compute shortest paths between all pairs.
     * - Step 3: For each city, count how many cities are reachable within threshold.
     * - Step 4: Track the city with the smallest count (break ties by largest index).
     *
     * Time Complexity: O(n^3) for Floyd-Warshall, O(n^2) for counting
     * Space Complexity: O(n^2) for adjacency matrix
     *
     * @param n      Number of cities
     * @param edges  2D array of edges [from, to, weight]
     * @param distanceThreshold  Maximum allowed distance
     * @return       City with the smallest number of reachable cities (break ties by largest index)
     */
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int inf = (int) 1e8; // Use a large value to represent infinity (no edge)
        int[][] adjM = new int[n][n];
        // Initialize adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    adjM[i][j] = 0; // Distance to self is 0
                else
                    adjM[i][j] = inf; // No direct edge
            }
        }
        // Fill adjacency matrix with edge weights
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int weight = edges[i][2];
            adjM[from][to] = weight;
            adjM[to][from] = weight; // Bidirectional
        }
        // Floyd-Warshall: compute all-pairs shortest paths
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjM[i][k] == inf || adjM[k][j] == inf)
                        continue;
                    adjM[i][j] = Math.min(adjM[i][j],adjM[i][k] + adjM[k][j]);
                }
            }
        }
        // Find the city with the smallest number of reachable cities (break ties by largest index)
        int node = -1;
        int reachableCities = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && adjM[i][j] <= distanceThreshold)
                    count++;
            }
            // If count is smaller, or equal but index is larger, update
            if (count <= reachableCities) {
                node = i;
                reachableCities = count;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] edges1 = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println("Input: n=4, threshold=4");
        System.out.println("Expected: 3, Output: " + findTheCity(4, edges1, 4));

        // Test Case 2: Edge case - all cities connected
        System.out.println("\nEdge Cases:");
        int[][] edges2 = {{0,1,1},{1,2,1},{2,3,1},{3,0,1}};
        System.out.println("Input: n=4, threshold=2");
        System.out.println("Expected: 3, Output: " + findTheCity(4, edges2, 2));

        // Test Case 3: Boundary case - single city
        System.out.println("\nBoundary Cases:");
        int[][] edges3 = {};
        System.out.println("Input: n=1, threshold=1");
        System.out.println("Expected: 0, Output: " + findTheCity(1, edges3, 1));

        // Test Case 4: Special case - disconnected graph
        System.out.println("\nSpecial Cases:");
        int[][] edges4 = {{0,1,10},{2,3,10}};
        System.out.println("Input: n=4, threshold=5");
        System.out.println("Expected: 3, Output: " + findTheCity(4, edges4, 5));
    }
}
