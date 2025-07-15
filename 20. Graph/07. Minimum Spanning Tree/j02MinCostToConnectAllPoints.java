/**
 * LeetCode 1584. Min Cost to Connect All Points
 *
 * Problem Statement:
 *     Given an array points where points[i] = [xi, yi] represents a point on the
 *     2D plane, return the minimum cost to make all points connected such that
 *     there is exactly one simple path between any two points (i.e., the cost of
 *     the Minimum Spanning Tree). The cost to connect two points is the Manhattan
 *     distance between them: |xi - xj| + |yi - yj|.
 *
 * Input:
 *     - points: int[n][2], 1 <= n <= 1000, -10^6 <= xi, yi <= 10^6
 *
 * Output:
 *     - int: Minimum total cost to connect all points
 *
 * Example:
 *     Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 *     Output: 20
 *
 *     Explanation:
 *         The minimum spanning tree connects points as follows:
 *         0-1 (cost 4), 1-3 (cost 3), 3-4 (cost 5), 1-2 (cost 8)
 *         Total cost = 4 + 3 + 5 + 8 = 20
 */

import java.util.*;

public class j02MinCostToConnectAllPoints {

    /**
     * Helper class to represent an edge to a point with its cost.
     *
     * Intuition:
     * - Encapsulates the index, coordinates, and cost for each candidate edge.
     *
     * Explanation:
     * - Used in the priority queue to always pick the next minimum cost edge.
     *
     * Time Complexity: O(1) per edge
     * Space Complexity: O(1) per edge
     */
    static class Edge {
        int index;
        int x;
        int y;
        int weight;
        Edge(int index, int x, int y, int weight) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    /**
     * Approach: Prim's Algorithm (Min-Heap)
     *
     * Intuition:
     * - Treat each point as a node, and the cost to connect any two points as the
     *   Manhattan distance. Use Prim's algorithm to build the MST.
     *
     * Explanation:
     * - Start from any point (here, 0), push it to the priority queue with cost 0.
     * - While the queue is not empty, pick the minimum cost edge to a new point.
     * - For each newly added point, push all edges to unvisited points into the queue.
     * - Repeat until all points are included in the MST.
     *
     * Time Complexity: O(n^2 log n) (each point can push up to n edges, heap ops)
     * Space Complexity: O(n^2) (priority queue can hold up to n^2 edges in worst case)
     *
     * @param points   int[n][2]: Array of points on the 2D plane
     * @return        int: Minimum total cost to connect all points
     */
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(0, points[0][0], points[0][1], 0)); // Start from point 0
        int cost = 0;
        int count = 0;
        while (!pq.isEmpty() && count < n) {
            Edge edge = pq.poll();
            if (visited[edge.index])
                continue; // Skip already included points
            visited[edge.index] = true;
            cost += edge.weight;
            count++;
            for (int i = 0; i < n; i++) {
                if (visited[i])
                    continue;
                int dist = Math.abs(points[i][0] - edge.x) + Math.abs(points[i][1] - edge.y);
                pq.add(new Edge(i, points[i][0], points[i][1], dist));
            }
        }
        // If not all points are included, return -1 (should not happen for valid input)
        if (count < n) return -1;
        return cost;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] points1 = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println("Input: " + Arrays.deepToString(points1));
        System.out.println("Expected: 20, Output: " + minCostConnectPoints(points1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] points2 = {{0,0}};
        System.out.println("Input: [[0,0]]");
        System.out.println("Expected: 0, Output: " + minCostConnectPoints(points2));
        int[][] points3 = {{0,0},{0,0}};
        System.out.println("Input: [[0,0],[0,0]]");
        System.out.println("Expected: 0, Output: " + minCostConnectPoints(points3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] points4 = new int[1000][2];
        for (int i = 0; i < 1000; i++) points4[i][0] = i; // All y=0
        System.out.println("Input: 1000 points on x-axis");
        System.out.println("Output: " + minCostConnectPoints(points4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] points5 = {{1,1},{1,6},{8,6},{8,1}};
        System.out.println("Input: [[1,1],[1,6],[8,6],[8,1]]");
        System.out.println("Expected: 21, Output: " + minCostConnectPoints(points5));
    }
}
