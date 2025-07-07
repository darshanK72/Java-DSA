/**
 * LeetCode 733. Flood Fill
 *
 * Problem Statement:
 *     An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *     You are also given three integers sr, sc, and color. Perform a flood fill starting from the pixel (sr, sc).
 *     Replace the color of all pixels connected 4-directionally to (sr, sc) and having the same original color with the new color.
 *     Return the modified image after performing the flood fill.
 *
 * Input:
 *     - image (1 <= m, n <= 50): m x n integer grid
 *     - sr, sc (0 <= sr < m, 0 <= sc < n): Starting pixel
 *     - color (0 <= color < 2^16): New color
 *
 * Output:
 *     - m x n integer grid: The image after flood fill
 *
 * Example:
 *     Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 *     Output: [[2,2,2],[2,2,0],[2,0,1]]
 *
 *     Explanation:
 *     All pixels connected to (1,1) with original color 1 are changed to 2.
 */

public class j14FloodFill {
    /**
     * Approach: DFS Flood Fill
     *
     * Intuition:
     * - Use DFS to visit all pixels connected 4-directionally to the starting pixel with the same original color.
     * - Change their color to the new color.
     *
     * Explanation:
     * - Step 1: Start DFS from (sr, sc). If the color is already the new color, return image.
     * - Step 2: For each pixel, recursively visit all 4 neighbors with the same original color.
     * - Step 3: Mark visited to avoid cycles.
     *
     * Time Complexity: O(m*n) (each cell visited at most once)
     * Space Complexity: O(m*n) for visited array and recursion stack
     *
     * @param image   m x n integer grid
     * @param sr      Starting row
     * @param sc      Starting column
     * @param color   New color
     * @return        Modified image after flood fill
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length; // Number of rows
        int n = image[0].length; // Number of columns
        boolean[][] visited = new boolean[m][n]; // Track visited pixels
        int[] rowDir = new int[] { -1, 0, 1, 0 }; // Row direction for 4 neighbors
        int[] colDir = new int[] { 0, 1, 0, -1 }; // Column direction for 4 neighbors
        if (image[sr][sc] == color) return image; // If starting pixel already has new color, return
        dfs(image, visited, sr, sc, image[sr][sc], color, rowDir, colDir); // Start DFS
        return image; // Return modified image
    }

    /**
     * Helper Method: DFS for flood fill
     *
     * Intuition:
     * - Recursively visit all 4-directionally connected pixels with the same original color.
     *
     * Explanation:
     * - For each direction, if neighbor is valid and has the original color, change its color and recurse.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     *
     * @param image         The image grid
     * @param visited       Visited array
     * @param i             Current row
     * @param j             Current column
     * @param originalColor The color to be replaced
     * @param color         New color
     * @param rowDir        Row direction array
     * @param colDir        Column direction array
     * @return              void
     */
    public static void dfs(int[][] image, boolean[][] visited, int i, int j, int originalColor, int color, int[] rowDir,
            int[] colDir) {
        visited[i][j] = true; // Mark current pixel as visited
        image[i][j] = color; // Change color of current pixel
        for (int d = 0; d < 4; d++) { // Explore all 4 directions
            int row = i + rowDir[d]; // Compute neighbor row
            int col = j + colDir[d]; // Compute neighbor column
            // Check bounds, not visited, and has original color
            if (row < image.length && col < image[0].length && row >= 0 && col >= 0 && !visited[row][col]
                    && image[row][col] == originalColor) {
                dfs(image, visited, row, col, originalColor, color, rowDir, colDir); // Recurse on neighbor
            }
        }
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] image1 = {{1,1,1},{1,1,0},{1,0,1}};
        printImage(floodFill(copy(image1), 1, 1, 2));
        int[][] image2 = {{0,0,0},{0,1,1}};
        printImage(floodFill(copy(image2), 1, 1, 1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] image3 = {{0}};
        printImage(floodFill(copy(image3), 0, 0, 1));
        int[][] image4 = {{1}};
        printImage(floodFill(copy(image4), 0, 0, 1));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] image5 = new int[1][10];
        for (int i = 0; i < 10; i++) image5[0][i] = 1; // Fill row with 1s
        printImage(floodFill(copy(image5), 0, 5, 2));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] image6 = {{1,2,2},{2,2,2},{2,2,1}};
        printImage(floodFill(copy(image6), 0, 0, 3));
    }

    // Helper method to print the image
    private static void printImage(int[][] image) {
        for (int[] row : image) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Helper method to deep copy a 2D array
    private static int[][] copy(int[][] image) {
        int[][] res = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            System.arraycopy(image[i], 0, res[i], 0, image[0].length);
        }
        return res;
    }
}
