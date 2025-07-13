/**
 * LeetCode 1631. Path With Minimum Effort
 * 
 * Problem Statement:
 *     You are a hiker preparing for an upcoming hike. You are given heights, 
 *     a 2D array of size rows x columns, where heights[row][col] represents 
 *     the height of cell (row, col). You are situated in the top-left cell, 
 *     (0, 0), and you hope to travel to the bottom-right cell, (rows-1, 
 *     columns-1) (i.e., 0-indexed). You can move up, down, left, or right, 
 *     and you wish to find a route that requires the minimum effort. A 
 *     route's effort is the maximum absolute difference in heights between 
 *     two consecutive cells of the route. Return the minimum effort required 
 *     to travel from the top-left cell to the bottom-right cell.
 * 
 * Input:
 *     - heights (1 <= rows, cols <= 100): 2D array representing grid heights
 *     - heights[i][j] (1 <= heights[i][j] <= 10^6): height at each cell
 * 
 * Output:
 *     - Minimum effort required to reach bottom-right from top-left
 *     - Effort is defined as maximum height difference between consecutive cells
 * 
 * Example:
 *     Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 *     Output: 2
 * 
 *     Explanation:
 *     The route of [1,3,5,3,5] has a maximum absolute difference of 2 in 
 *     consecutive cells. This is better than the route of [1,2,2,2,5], 
 *     where the maximum absolute difference is 3.
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class j05PathWithMinimumEffort {

    /**
     * Approach: Modified Dijkstra's Algorithm for Minimum Maximum Effort
     * 
     * Intuition:
     * - We need to find the path where the maximum height difference between 
     *   any two consecutive cells is minimized
     * - This is similar to finding the "bottleneck" path - the path where 
     *   the worst step (highest effort) is as small as possible
     * - We use Dijkstra's algorithm but instead of minimizing total distance, 
     *   we minimize the maximum effort encountered so far
     * - Priority queue processes cells with minimum current effort first
     * 
     * Explanation:
     * - Step 1: Initialize distance array with maximum values and set start 
     *   cell effort to 0
     * - Step 2: Use min-heap to process cells in order of minimum effort
     * - Step 3: For each neighbor, calculate effort as maximum of current 
     *   path effort and height difference to neighbor
     * - Step 4: Update neighbor's effort if we found a better path (lower 
     *   maximum effort)
     * - Step 5: Return the minimum effort required to reach destination
     * 
     * Time Complexity: O(m*n * log(m*n)) where m and n are grid dimensions, 
     *                  due to priority queue operations for each cell
     * Space Complexity: O(m*n) for distance array and priority queue storage
     * 
     * @param heights  2D array representing grid heights (1 <= rows,cols <= 100)
     * @return        Minimum effort required to reach bottom-right from top-left
     */
    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        
        // Direction arrays for 4-directional movement (right, down, left, up)
        int[] rowDir = new int[] { 0, 1, 0, -1 };
        int[] colDir = new int[] { 1, 0, -1, 0 };
        
        // Initialize min-heap to process cells with minimum effort first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        // Initialize distance array with maximum values
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        // Start with top-left cell having 0 effort
        pq.add(new int[] { 0, 0, 0 });
        dist[0][0] = 0;
        
        // Process cells in order of minimum effort
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int row = current[0];
            int col = current[1];
            int currentEffort = current[2];
            
            // Explore all 4 directions from current cell
            for (int d = 0; d < 4; d++) {
                int newRow = row + rowDir[d];
                int newCol = col + colDir[d];
                
                // Check if new position is within grid boundaries
                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n) {
                    // Calculate height difference between current and neighbor
                    int heightDiff = Math.abs(heights[row][col] - heights[newRow][newCol]);
                    
                    // Effort for this path is maximum of current path effort and height difference
                    int newEffort = Math.max(currentEffort, heightDiff);
                    
                    // Update if we found a better path to this neighbor
                    if (dist[newRow][newCol] > newEffort) {
                        dist[newRow][newCol] = newEffort;
                        pq.add(new int[] { newRow, newCol, newEffort });
                    }
                }
            }
        }

        // Return minimum effort required to reach bottom-right cell
        return dist[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        int[][] heights1 = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println("Input: [[1,2,2],[3,8,2],[5,3,5]]");
        System.out.println("Expected: 2, Output: " + minimumEffortPath(heights1));
        
        int[][] heights2 = {{1,2,3},{3,8,4},{5,3,5}};
        System.out.println("Input: [[1,2,3],[3,8,4],[5,3,5]]");
        System.out.println("Expected: 1, Output: " + minimumEffortPath(heights2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] heights3 = {{1}};
        System.out.println("Input: [[1]] (single cell)");
        System.out.println("Expected: 0, Output: " + minimumEffortPath(heights3));
        
        int[][] heights4 = {{1,2},{3,4}};
        System.out.println("Input: [[1,2],[3,4]] (2x2 grid)");
        System.out.println("Expected: 1, Output: " + minimumEffortPath(heights4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] heights5 = {{1,1,1},{1,1,1},{1,1,1}};
        System.out.println("Input: [[1,1,1],[1,1,1],[1,1,1]] (all same height)");
        System.out.println("Expected: 0, Output: " + minimumEffortPath(heights5));
        
        int[][] heights6 = {{1,1000000},{1000000,1}};
        System.out.println("Input: [[1,1000000],[1000000,1]] (max height difference)");
        System.out.println("Expected: 999999, Output: " + minimumEffortPath(heights6));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] heights7 = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        System.out.println("Input: 5x5 grid with alternating heights");
        System.out.println("Expected: 0, Output: " + minimumEffortPath(heights7));
        
        int[][] heights8 = {{1,10,6,7,9,10,4,9}};
        System.out.println("Input: [[1,10,6,7,9,10,4,9]] (single row)");
        System.out.println("Expected: 9, Output: " + minimumEffortPath(heights8));
        
        int[][] heights9 = {{1},{10},{6},{7},{9},{10},{4},{9}};
        System.out.println("Input: [[1],[10],[6],[7],[9],[10],[4],[9]] (single column)");
        System.out.println("Expected: 9, Output: " + minimumEffortPath(heights9));
    }
}
