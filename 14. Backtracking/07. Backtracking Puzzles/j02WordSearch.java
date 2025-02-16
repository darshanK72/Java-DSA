/**
 * Problem Statement:
 * 
 *     Given an `m x n` board and a word, find if the word exists in the grid.
 *     The word can be constructed from sequentially adjacent cells, where
 *     "adjacent" cells are horizontally or vertically neighboring.
 *     The same letter cell may not be used more than once in a word.
 * 
 * Input:
 *     - A 2D character array `board` of size `m x n` representing the grid.
 *     - A string `word` representing the word to be searched.
 * 
 * Output:
 *     - A boolean value `true` if the word exists in the grid, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     board = [['A','B','C','E'],
 *              ['S','F','C','S'],
 *              ['A','D','E','E']]
 *     word = "ABCCED"
 * 
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - The word "ABCCED" can be found following the path (0,0) → (0,1) → (0,2) → (1,2) → (2,2) → (2,1).
 * 
 *     Input:
 *     board = [['A','B','C','E'],
 *              ['S','F','C','S'],
 *              ['A','D','E','E']]
 *     word = "SEE"
 * 
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - The word "SEE" can be found following the path (2,1) → (2,2) → (1,3).
 */

public class j02WordSearch {

    /**
     * Approach: Backtracking with DFS (Depth-First Search)
     * 
     * Intuition:
     * - We iterate over each cell in the grid, treating it as a potential starting
     *   point for the word.
     * - If the first character matches, we start a DFS search to check if the 
     *   remaining characters can be found in adjacent cells.
     * - We maintain a `visited` boolean array to avoid revisiting the same cell
     *   within the current search path.
     * - If we find a path that matches the word, we return `true`.
     * - If no valid path is found, we return `false`.
     * 
     * Explanation:
     * - Iterate through each cell in the board.
     * - If the cell matches the first character of the word, initiate a DFS search.
     * - The `isWordExists` method performs DFS:
     *   - If the entire word is matched (`index == word.length()`), return `true`.
     *   - If out of bounds or already visited, return `false`.
     *   - If the current character matches, mark it as visited and recursively 
     *     check the four possible directions (right, down, left, up).
     *   - If any path returns `true`, propagate the result.
     *   - Backtrack by unmarking the visited cell if no path works.
     * 
     * Time Complexity:
     * - O(m * n * 4^k) in the worst case, where `m x n` is the grid size and `k`
     *   is the word length. Each cell initiates DFS, which explores up to four directions.
     * 
     * Space Complexity:
     * - O(m * n) due to the recursion depth in the worst case and the `visited` array.
     * 
     * @param board The input 2D character board.
     * @param word  The target word to be searched.
     * @return `true` if the word exists in the board, otherwise `false`.
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWordExists(board, new boolean[m][n], 0, i, j, word))
                    return true;
            }
        }
        return false;
    }

    /**
     * Helper Method: DFS Search for the Word
     * 
     * Intuition:
     * - The method recursively explores all possible paths starting from a given cell.
     * - If the current character matches, we proceed to check its neighbors.
     * - Backtracking is applied to restore the state after each exploration.
     * 
     * Explanation:
     * - Base case: If `index == word.length()`, return `true` (word is fully matched).
     * - Out of bounds check or already visited check returns `false`.
     * - If current character matches the word, mark it visited.
     * - Recursively check the right, down, left, and up directions.
     * - If any recursive call returns `true`, propagate the result.
     * - Otherwise, backtrack by marking the cell as unvisited before returning `false`.
     * 
     * Time Complexity:
     * - O(4^k) for each call, where `k` is the word length.
     * 
     * Space Complexity:
     * - O(k) due to recursion stack depth.
     * 
     * @param board   The input 2D character board.
     * @param visited Boolean matrix tracking visited cells.
     * @param index   Current index in the word.
     * @param row     Current row index.
     * @param col     Current column index.
     * @param word    The target word.
     * @return `true` if the word can be constructed, otherwise `false`.
     */
    public static boolean isWordExists(char[][] board, boolean[][] visited, int index, int row, int col, String word) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col])
            return false;
        if (board[row][col] == word.charAt(index)) {
            visited[row][col] = true;
            if (isWordExists(board, visited, index + 1, row, col + 1, word))
                return true;
            if (isWordExists(board, visited, index + 1, row + 1, col, word))
                return true;
            if (isWordExists(board, visited, index + 1, row, col - 1, word))
                return true;
            if (isWordExists(board, visited, index + 1, row - 1, col, word))
                return true;
            visited[row][col] = false;
        }
        return false;
    }
}
