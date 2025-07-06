/**
 * GeeksForGeeks. Minimum steps to reach target by a Knight
 * 
 * Problem Statement:
 *     Given a square chessboard of N x N size, the position of Knight and 
 *     position of a target are given. We need to find out the minimum steps 
 *     a Knight will take to reach the target position.
 * 
 * Input:
 *     - knightPos[] (int[]): Initial position of knight [row, col] (1-indexed)
 *     - targetPos[] (int[]): Target position [row, col] (1-indexed)
 *     - n (int): Size of chessboard (1 ≤ n ≤ 100)
 * 
 * Output:
 *     - int: Minimum number of steps required to reach target, or -1 if 
 *       impossible
 * 
 * Example:
 *     Input: knightPos = [1,1], targetPos = [8,8], n = 8
 *     Output: 6
 * 
 *     Explanation:
 *     Knight starts at (1,1) and needs to reach (8,8)
 *     One possible path: (1,1) → (3,2) → (5,3) → (6,5) → (7,7) → (8,8)
 *     Total steps: 6
 * 
 *     Input: knightPos = [1,1], targetPos = [1,1], n = 8
 *     Output: 0
 * 
 *     Explanation:
 *     Knight is already at target position, so 0 steps needed
 */

import java.util.*;

public class j03StepByKnight {

    /**
     * Cell class to store position and steps information for BFS
     */
    static class Cell {
        int row;
        int col;
        int steps;

        Cell(int row, int col, int steps) {
            this.row = row;
            this.col = col;
            this.steps = steps;
        }
    }

    /**
     * Approach: Breadth-First Search (BFS)
     * 
     * Intuition:
     * - Since we need to find minimum steps, BFS is optimal as it explores 
     *   all positions at current distance before moving to next level
     * - Knight moves in L-shape pattern: 2 steps in one direction + 1 step 
     *   perpendicular
     * - Each level in BFS represents one step, so when we reach target, 
     *   we have minimum steps
     * - Use visited array to avoid revisiting same positions
     * 
     * Explanation:
     * - Step 1: Initialize BFS queue with starting position and 0 steps
     * - Step 2: For each position, explore all 8 possible knight moves
     * - Step 3: Mark visited positions to avoid cycles
     * - Step 4: When target is reached, return current step count
     * - Step 5: If queue becomes empty without reaching target, return -1
     * 
     * Time Complexity: O(N²) where N is board size - in worst case we visit 
     *                  all positions on board
     * Space Complexity: O(N²) for visited array and queue storage
     * 
     * @param knightPos    Initial knight position [row, col] (1-indexed)
     * @param targetPos    Target position [row, col] (1-indexed)
     * @param n            Size of chessboard
     * @return            Minimum steps to reach target, -1 if impossible
     */
    public static int minStepToReachTarget(int[] knightPos, int[] targetPos, int n) {
        // Validate input parameters
        if (knightPos == null || targetPos == null || n <= 0) {
            return -1;
        }
        
        // Convert 1-indexed to 0-indexed coordinates
        int startRow = knightPos[0] - 1;
        int startCol = knightPos[1] - 1;
        int targetRow = targetPos[0] - 1;
        int targetCol = targetPos[1] - 1;
        
        // Validate positions are within board boundaries
        if (!isValidPosition(startRow, startCol, n) || 
            !isValidPosition(targetRow, targetCol, n)) {
            return -1;
        }
        
        // If knight is already at target, return 0
        if (startRow == targetRow && startCol == targetCol) {
            return 0;
        }
        
        // Initialize BFS data structures
        boolean[][] visited = new boolean[n][n];
        Queue<Cell> queue = new LinkedList<>();
        
        // Define all 8 possible knight moves (L-shape pattern)
        int[] rowDir = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] colDir = {1, -1, 1, -1, 2, -2, 2, -2};
        
        // Start BFS from knight's initial position
        visited[startRow][startCol] = true;
        queue.offer(new Cell(startRow, startCol, 0));
        
        // Process queue level by level (each level = one step)
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            
            // Check if we reached target position
            if (current.row == targetRow && current.col == targetCol) {
                return current.steps;
            }
            
            // Explore all 8 possible knight moves from current position
            for (int i = 0; i < 8; i++) {
                int newRow = current.row + rowDir[i];
                int newCol = current.col + colDir[i];
                
                // Check if new position is valid and unvisited
                if (isValidPosition(newRow, newCol, n) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.offer(new Cell(newRow, newCol, current.steps + 1));
                }
            }
        }
        
        // If we can't reach target, return -1
        return -1;
    }

    /**
     * Helper Method: Validate Position
     * 
     * Intuition:
     * - Check if given position is within the chessboard boundaries
     * - Prevents array index out of bounds errors
     * 
     * Explanation:
     * - Row and column must be >= 0 and < board size
     * - Returns true if position is valid, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param row    Row position to validate
     * @param col    Column position to validate
     * @param n      Board size
     * @return      True if position is valid, false otherwise
     */
    private static boolean isValidPosition(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    /**
     * Alternative Approach: Bidirectional BFS (for optimization)
     * 
     * Intuition:
     * - Instead of searching from knight to target, search from both ends
     * - Reduces search space significantly in many cases
     * - When searches meet, we have found optimal path
     * 
     * Explanation:
     * - Use two queues: one from knight position, one from target
     * - Alternate between queues to balance search
     * - When a position is visited by both searches, we have solution
     * - More complex but can be faster for large boards
     * 
     * Time Complexity: O(N²) but with smaller constant factor
     * Space Complexity: O(N²) for two visited arrays and queues
     */
    public static int minStepToReachTargetBidirectional(int[] knightPos, int[] targetPos, int n) {
        // Implementation would be more complex but follows same BFS principle
        // with two simultaneous searches from start and target
        return minStepToReachTarget(knightPos, targetPos, n); // Fallback to simple BFS
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("Basic Test Cases:");
        System.out.println("Input: knightPos=[1,1], targetPos=[8,8], n=8");
        System.out.println("Expected: 6, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{8,8}, 8));
        
        System.out.println("Input: knightPos=[1,1], targetPos=[1,1], n=8");
        System.out.println("Expected: 0, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{1,1}, 8));
        
        System.out.println("Input: knightPos=[1,1], targetPos=[3,3], n=8");
        System.out.println("Expected: 2, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{3,3}, 8));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: knightPos=[1,1], targetPos=[2,2], n=2");
        System.out.println("Expected: -1, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{2,2}, 2));
        
        System.out.println("Input: knightPos=[1,1], targetPos=[1,2], n=8");
        System.out.println("Expected: 3, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{1,2}, 8));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: knightPos=[1,1], targetPos=[8,1], n=8");
        System.out.println("Expected: 5, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{8,1}, 8));
        
        System.out.println("Input: knightPos=[1,1], targetPos=[1,8], n=8");
        System.out.println("Expected: 5, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{1,8}, 8));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: knightPos=[4,4], targetPos=[4,4], n=8");
        System.out.println("Expected: 0, Output: " + 
            minStepToReachTarget(new int[]{4,4}, new int[]{4,4}, 8));
        
        System.out.println("Input: knightPos=[1,1], targetPos=[2,3], n=8");
        System.out.println("Expected: 1, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{2,3}, 8));
        
        System.out.println("Input: knightPos=[1,1], targetPos=[3,2], n=8");
        System.out.println("Expected: 1, Output: " + 
            minStepToReachTarget(new int[]{1,1}, new int[]{3,2}, 8));
    }
}
