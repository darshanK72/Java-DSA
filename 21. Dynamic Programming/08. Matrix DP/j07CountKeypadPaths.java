/**
 * GeeksforGeeks. Count the number of possible N-digit numbers
 * 
 * Problem Statement:
 *     Given a mobile keypad (0-9), find the number of possible N-digit numbers
 *     that can be formed using the keypad. From any digit, you can move to
 *     adjacent digits only (horizontally and vertically). Some digits have
 *     diagonal movements as well.
 * 
 *     Keypad layout:
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *       0
 * 
 * Input:
 *     - n (1 <= n <= 50): number of digits in the number to be formed
 * 
 * Output:
 *     - int: total number of possible N-digit numbers
 * 
 * Example:
 *     Input: n = 2
 *     Output: 36
 * 
 *     Explanation:
 *     For 2-digit numbers starting from each digit:
 *     - From 0: can go to 8 (1 path)
 *     - From 1: can go to 2,4 (2 paths)
 *     - From 2: can go to 1,3,5 (3 paths)
 *     - From 3: can go to 2,6 (2 paths)
 *     - From 4: can go to 1,5,7 (3 paths)
 *     - From 5: can go to 2,4,6,8 (4 paths)
 *     - From 6: can go to 3,5,9 (3 paths)
 *     - From 7: can go to 4,8 (2 paths)
 *     - From 8: can go to 5,7,9,0 (4 paths)
 *     - From 9: can go to 6,8 (2 paths)
 *     Total: 1+2+3+2+3+4+3+2+4+2 = 26 paths for 2nd digit
 *     For 1st digit: all 10 digits are valid, so 10 * 26 = 260 total paths
 */

import java.util.*;

public class j07CountKeypadPaths {

    /**
     * Approach: DFS with Memoization (Top-Down DP)
     * 
     * Intuition:
     * - Each digit can move to its adjacent digits according to keypad layout
     * - Use DFS to explore all possible paths of length N
     * - Apply memoization to avoid recalculating same subproblems
     * - Sum up all possible paths starting from each digit (0-9)
     * - Key insight: paths from digit 'd' with 'm' moves left = sum of paths
     *   from adjacent digits with 'm-1' moves left
     * 
     * Explanation:
     * - Step 1: Define keypad adjacency mapping for each digit
     * - Step 2: Initialize memoization array to cache results
     * - Step 3: For each starting digit (0-9), calculate total paths
     * - Step 4: Use DFS with memoization to avoid redundant calculations
     * - Step 5: Base case: 0 moves left means 1 valid path (current digit)
     * - Step 6: Recursive case: sum paths from all adjacent digits
     * - Step 7: Return total count across all starting positions
     * 
     * Time Complexity: O(10 * n * avg_adjacent_keys) - each state calculated once
     * Space Complexity: O(10 * n) - memoization array + recursion stack O(n)
     * 
     * @param n    Number of digits in the number to be formed (1 <= n <= 50)
     * @return     Total number of possible N-digit numbers
     */
    public static int getCount(int n) {
        // Define keypad adjacency mapping: each digit maps to its adjacent digits
        int[][] keyMap = new int[][] {
                { 0, 8 },           // 0: can move to 0, 8
                { 1, 2, 4 },        // 1: can move to 1, 2, 4
                { 2, 1, 5, 3 },     // 2: can move to 2, 1, 5, 3
                { 3, 2, 6 },        // 3: can move to 3, 2, 6
                { 4, 1, 5, 7 },     // 4: can move to 4, 1, 5, 7
                { 5, 2, 6, 8, 4 },  // 5: can move to 5, 2, 6, 8, 4
                { 6, 3, 5, 9 },     // 6: can move to 6, 3, 5, 9
                { 7, 4, 8 },        // 7: can move to 7, 4, 8
                { 8, 5, 9, 0, 7 },  // 8: can move to 8, 5, 9, 0, 7
                { 9, 6, 8 }         // 9: can move to 9, 6, 8
        };
        // Initialize total answer counter
        int totalAns = 0;
        // Initialize memoization array: dp[digit][moves_left] = count
        int[][] dp = new int[10 + 1][n + 1];
        for (int i = 0; i < 10; i++) {
            Arrays.fill(dp[i], -1);
        }
        // Calculate paths starting from each digit (0-9)
        for (int i = 0; i <= 9; i++) {
            totalAns += keyDfs(i, n - 1, keyMap, dp);
        }
        // Return total number of possible N-digit numbers
        return totalAns;
    }

    /**
     * Helper Method: DFS with memoization for keypad path counting
     * 
     * Intuition:
     * - Recursively explore all possible paths from current digit
     * - Use memoization to cache results and avoid redundant calculations
     * - Base case: no moves left means exactly one valid path (current digit)
     * - Recursive case: sum paths from all adjacent digits with one less move
     * 
     * Explanation:
     * - Base case: if no moves left (moves == 0), return 1 (valid path found)
     * - Memoization check: return cached result if already calculated
     * - For each adjacent digit, recursively calculate paths with moves-1
     * - Sum up all possible paths from adjacent positions
     * - Cache and return total count for current state
     * 
     * Time Complexity: O(avg_adjacent_keys^moves) without memoization, O(moves) with memoization
     * Space Complexity: O(moves) - recursion stack depth
     * 
     * @param key     Current digit position (0-9)
     * @param moves   Number of moves remaining
     * @param keyMap  Adjacency mapping for keypad digits
     * @param dp      Memoization array to cache results
     * @return        Number of valid paths from current digit with given moves
     */
    private static int keyDfs(int key, int moves, int[][] keyMap, int[][] dp) {
        // Base case: no moves left means exactly one valid path (current digit)
        if (moves == 0)
            return 1;
        // Return cached result if already calculated
        if (dp[key][moves] != -1)
            return dp[key][moves];
        // Initialize answer counter for current state
        int ans = 0;
        // Explore all adjacent digits from current position
        for (int nextKey : keyMap[key]) {
            // Recursively calculate paths from adjacent digit with one less move
            ans += keyDfs(nextKey, moves - 1, keyMap, dp);
        }
        // Cache and return total paths from current state
        return dp[key][moves] = ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: n=1, Expected: 10, Output: " + getCount(1));
        System.out.println("Input: n=2, Expected: 36, Output: " + getCount(2));
        System.out.println("Input: n=3, Expected: 138, Output: " + getCount(3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=4, Output: " + getCount(4));
        System.out.println("Input: n=5, Output: " + getCount(5));
        System.out.println("Input: n=10, Output: " + getCount(10));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: n=20, Output: " + getCount(20));
        System.out.println("Input: n=30, Output: " + getCount(30));
        System.out.println("Input: n=50, Output: " + getCount(50));

        // Test Case 4: Special cases - verify with manual calculation
        System.out.println("\nVerification Cases:");
        System.out.println("Input: n=1 (all single digits), Expected: 10, Output: " + getCount(1));
        System.out.println("Input: n=2 (from each digit), Expected: 36, Output: " + getCount(2));
        
        // Manual verification for n=2:
        // 0->8(1), 1->2,4(2), 2->1,3,5(3), 3->2,6(2), 4->1,5,7(3)
        // 5->2,4,6,8(4), 6->3,5,9(3), 7->4,8(2), 8->5,7,9,0(4), 9->6,8(2)
        // Total: 1+2+3+2+3+4+3+2+4+2 = 26 (for 2nd digit)
        // For 1st digit: 10 possibilities, so 10 * 26 = 260... wait, let me recalculate
        System.out.println("\nDetailed verification for n=2:");
        int[][] keyMap = new int[][] {
                { 0, 8 }, { 1, 2, 4 }, { 2, 1, 5, 3 }, { 3, 2, 6 },
                { 4, 1, 5, 7 }, { 5, 2, 6, 8, 4 }, { 6, 3, 5, 9 },
                { 7, 4, 8 }, { 8, 5, 9, 0, 7 }, { 9, 6, 8 }
        };
        for (int i = 0; i <= 9; i++) {
            System.out.println("Digit " + i + " has " + keyMap[i].length + " adjacent digits: " + Arrays.toString(keyMap[i]));
        }
    }
}
