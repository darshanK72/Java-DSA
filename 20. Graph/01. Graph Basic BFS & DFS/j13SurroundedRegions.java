/**
 * LeetCode 130. Surrounded Regions
 *
 * Problem Statement:
 *     Given an m x n matrix board containing 'X' and 'O', capture all regions that are surrounded by 'X'.
 *     A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *     An 'O' region is considered surrounded if it is not connected to any border 'O'.
 *
 * Input:
 *     - board (m x n matrix, 1 <= m, n <= 200): Each cell is 'X' or 'O'
 *
 * Output:
 *     - The board is modified in-place. All surrounded 'O' regions are flipped to 'X'.
 *
 * Example:
 *     Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *     Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 *     Explanation:
 *     The region on the second row is surrounded by 'X' on all sides. The 'O' on the bottom border is not captured.
 */

public class j13SurroundedRegions {
    /**
     * Approach: DFS from Border 'O's
     *
     * Intuition:
     * - Any 'O' connected to the border cannot be captured. Mark all such 'O's using DFS.
     * - All other 'O's are surrounded and should be flipped to 'X'.
     *
     * Explanation:
     * - Step 1: For each border cell, if it is 'O', start DFS to mark all connected 'O's as visited.
     * - Step 2: After marking, iterate through the board and flip all unvisited 'O's to 'X'.
     * - Step 3: Leave visited 'O's unchanged (they are not surrounded).
     *
     * Time Complexity: O(m*n) (each cell visited at most once)
     * Space Complexity: O(m*n) for visited array and recursion stack
     *
     * @param board   m x n matrix of 'X' and 'O'
     * @return        void (board is modified in-place)
     */
    public static void solve(char[][] board) {
        int m = board.length; // Number of rows
        int n = board[0].length; // Number of columns
        boolean[][] visited = new boolean[m][n]; // Track visited cells

        int[] rowDir = new int[] { -1, 0, 1, 0 }; // Row direction for 4 neighbors
        int[] colDir = new int[] { 0, 1, 0, -1 }; // Column direction for 4 neighbors

        // Mark all 'O's connected to the border as visited
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) // Left border
                dfs(board, visited, i, 0, rowDir, colDir);
            if (board[i][n - 1] == 'O' && !visited[i][n - 1]) // Right border
                dfs(board, visited, i, n - 1, rowDir, colDir);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visited[0][j]) // Top border
                dfs(board, visited, 0, j, rowDir, colDir);
            if (board[m - 1][j] == 'O' && !visited[m - 1][j]) // Bottom border
                dfs(board, visited, m - 1, j, rowDir, colDir);
        }

        // Flip all unvisited 'O's to 'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X'; // Surrounded region, flip to 'X'
                }
            }
        }
    }

    /**
     * Helper Method: DFS to mark border-connected 'O's
     *
     * Intuition:
     * - Mark all 'O's connected to the border as visited so they are not flipped.
     *
     * Explanation:
     * - For each direction, recursively visit all connected 'O's.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     *
     * @param board   The board
     * @param visited Visited array
     * @param i       Current row
     * @param j       Current column
     * @param rowDir  Row direction array
     * @param colDir  Column direction array
     * @return        void
     */
    public static void dfs(char[][] board, boolean[][] visited, int i, int j, int[] rowDir, int[] colDir) {
        visited[i][j] = true; // Mark current cell as visited
        for (int d = 0; d < 4; d++) { // Explore all 4 directions
            int row = i + rowDir[d]; // Compute neighbor row
            int col = j + colDir[d]; // Compute neighbor column
            // Check bounds and if neighbor is 'O' and not visited
            if (row < board.length && col < board[0].length && row >= 0 && col >= 0 && board[row][col] == 'O'
                    && !visited[row][col]) {
                dfs(board, visited, row, col, rowDir, colDir); // Recurse on neighbor
            }
        }
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        char[][] board1 = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        solve(board1);
        System.out.println("Input: [[X,X,X,X],[X,O,O,X],[X,X,O,X],[X,O,X,X]], Output: ");
        printBoard(board1);

        // Edge Cases
        System.out.println("\nEdge Cases:");
        char[][] board2 = {{'O'}};
        solve(board2);
        System.out.println("Input: [[O]], Output: ");
        printBoard(board2);
        char[][] board3 = {{'X'}};
        solve(board3);
        System.out.println("Input: [[X]], Output: ");
        printBoard(board3);

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        char[][] board4 = new char[1][10];
        for (int i = 0; i < 10; i++) board4[0][i] = 'O'; // Fill row with 'O'
        solve(board4);
        System.out.println("Input: 1x10 all O, Output: ");
        printBoard(board4);

        // Special Cases
        System.out.println("\nSpecial Cases:");
        char[][] board5 = {
            {'O','O','O','O'},
            {'O','X','X','O'},
            {'O','X','O','O'},
            {'O','O','O','O'}
        };
        solve(board5);
        System.out.println("Input: [[O,O,O,O],[O,X,X,O],[O,X,O,O],[O,O,O,O]], Output: ");
        printBoard(board5);
    }

    // Helper method to print the board
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
