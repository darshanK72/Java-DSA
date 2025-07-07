/**
 * LeetCode 542. 01 Matrix
 *
 * Problem Statement:
 *     Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *     The distance between two adjacent cells is 1.
 *
 *     This problem is similar to LeetCode 1765. Map of Highest Peak:
 *     - In "Map of Highest Peak", you are given a binary matrix isWater where 1 represents water and 0 represents land. You must assign a height to each cell such that:
 *         1. The height of each water cell is 0.
 *         2. The height difference between any two adjacent cells is at most 1.
 *         3. The goal is to maximize the height of the land cells, which is achieved by assigning each land cell the minimum distance to any water cell.
 *     - The core algorithm for both problems is a multi-source BFS starting from all 0s (or all water cells in the variation).
 *
 * Input:
 *     - mat (1 <= m, n <= 10^4): m x n binary matrix (0 or 1)
 *
 * Output:
 *     - m x n integer matrix: Each cell contains the distance to the nearest 0
 *
 * Example:
 *     Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 *     Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 *     Explanation:
 *     For each cell, compute the minimum distance to a cell containing 0.
 *
 * Variation (LeetCode 1765):
 *     Input: isWater = [[0,1],[0,0]]
 *     Output: [[1,0],[2,1]]
 *     Explanation: Heights are assigned such that water cells are 0 and each land cell's height is the minimum distance to water.
 */

import java.util.*;

public class j03ZeroOneMatrix {
    /**
     * Approach: Multi-Source BFS
     *
     * Intuition:
     * - All cells with 0 are sources; start BFS from all 0s simultaneously.
     * - For each 1, the shortest path to a 0 is found by expanding from the nearest 0.
     *
     * Explanation:
     * - Step 1: Add all 0-cells to the queue and mark as visited.
     * - Step 2: For each cell in the queue, expand to its 4 neighbors.
     * - Step 3: If a neighbor is unvisited, set its distance and add to the queue.
     * - Step 4: Continue until all cells are processed.
     *
     * Time Complexity: O(m*n) (each cell visited at most once)
     * Space Complexity: O(m*n) (for output, visited, and queue)
     *
     * @param mat    m x n binary matrix
     * @return       m x n matrix of minimum distances to nearest 0
     */
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] out = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        // Add all 0-cells to the queue as BFS sources
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) { // use mat[i][j] == 1 for other varation
                    queue.add(new int[] { i, j, 0 });
                    visited[i][j] = true;
                }
            }
        }

        int[] rowDir = new int[] { -1, 0, 1, 0 };
        int[] colDir = new int[] { 0, 1, 0, -1 };

        // BFS from all 0-cells
        while (!queue.isEmpty()) {
            int[] pos = queue.remove();
            int i = pos[0];
            int j = pos[1];
            int dist = pos[2];

            out[i][j] = dist;

            for (int d = 0; d < 4; d++) {
                int row = i + rowDir[d];
                int col = j + colDir[d];
                // Check bounds and if not visited
                if (row < m && col < n && row >= 0 && col >= 0 && !visited[row][col]) {
                    visited[row][col] = true;
                    queue.add(new int[] { row, col, dist + 1 });
                }
            }
        }

        return out;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] mat1 = {{0,0,0},{0,1,0},{1,1,1}};
        System.out.println("Input: [[0,0,0],[0,1,0],[1,1,1]], Expected: [[0,0,0],[0,1,0],[1,2,1]], Output: " + Arrays.deepToString(updateMatrix(mat1)));
        int[][] mat2 = {{0,1,1},{1,1,1},{1,1,0}};
        System.out.println("Input: [[0,1,1],[1,1,1],[1,1,0]], Output: " + Arrays.deepToString(updateMatrix(mat2)));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] mat3 = {{0}};
        System.out.println("Input: [[0]], Expected: [[0]], Output: " + Arrays.deepToString(updateMatrix(mat3)));
        int[][] mat4 = {{1}};
        System.out.println("Input: [[1]], Expected: [[0]], Output: " + Arrays.deepToString(updateMatrix(mat4)));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int m = 1, n = 10;
        int[][] mat5 = new int[m][n];
        Arrays.fill(mat5[0], 1);
        mat5[0][5] = 0;
        System.out.println("Input: 1x10 row with one 0, Output: " + Arrays.deepToString(updateMatrix(mat5)));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] mat6 = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println("Input: [[1,1,1],[1,0,1],[1,1,1]], Output: " + Arrays.deepToString(updateMatrix(mat6)));
    }
}
