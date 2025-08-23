/**
 * Coding Ninjas/GFG. Ninja Training
 * 
 * Problem Statement:
 *     Ninja is planing this 'N' days-long training schedule. Each day, he can
 *     perform one of these three activities: (Running, Fighting Practice, or
 *     Learning New Moves). Each activity has some merit points on each day.
 *     As Ninja has to improve all his skills, he can't do the same activity in
 *     two consecutive days. You are given a 2D array 'POINTS' with the points
 *     of all activities on those days. Your task is to return the maximum merit
 *     points you can earn.
 * 
 * Input:
 *     - n (1 <= n <= 10^5) - number of days
 *     - points (n x 3 matrix, 1 <= points[i][j] <= 10^4) - merit points for
 *       each activity on each day
 * 
 * Output:
 *     - Integer representing the maximum merit points that can be earned
 * 
 * Example:
 *     Input: n = 3, points = [[1,2,5], [3,1,1], [3,3,3]]
 *     Output: 11
 * 
 *     Explanation:
 *     Day 1: Choose activity 2 (points = 5)
 *     Day 2: Choose activity 0 (points = 3)
 *     Day 3: Choose activity 1 (points = 3)
 *     Total = 5 + 3 + 3 = 11
 */
import java.util.Arrays;

public class j03NinjaTraining {

    /**
     * Approach: Top-Down DP with Memoization (State = day, lastTask)
     * 
     * Intuition:
     * - At each day, we have 3 choices (activities 0, 1, 2) but cannot choose
     *   the same activity as the previous day. We model this as a DP over the
     *   day and the last task performed.
     * - Memoization ensures each state is computed once.
     * 
     * Explanation:
     * - Use dp[lastTask][day] where lastTask in {0,1,2,3} (3 means no previous task)
     * - For each day, try all activities except the one from previous day
     * - Base case: day == 0, choose best activity (excluding lastTask)
     * - Recurse to previous day with current activity as lastTask
     * 
     * Time Complexity: O(n * 4 * 3) = O(n) - Each state computed once
     * Space Complexity: O(n * 4) - DP table and recursion stack
     * 
     * @param n       Number of days for training
     * @param points  2D array where points[i][j] = points for activity j on day i
     * @return        Maximum merit points that can be earned
     */
    public static int ninjaTraining(int n, int points[][]) {
        // Handle edge cases: invalid input
        if (n <= 0 || points == null || points.length == 0) {
            return 0; // No training possible
        }

        // Initialize DP table: 4 rows for lastTask (0,1,2,3), columns for days
        int[][] dp = new int[4][n];
        Arrays.fill(dp[0], -1); // Mark unseen states for lastTask = 0
        Arrays.fill(dp[1], -1); // Mark unseen states for lastTask = 1
        Arrays.fill(dp[2], -1); // Mark unseen states for lastTask = 2
        Arrays.fill(dp[3], -1); // Mark unseen states for lastTask = 3 (no previous)

        // Start from the last day (n-1) with no previous task (lastTask = 3)
        return getMaxPoints(points, dp, n - 1, 3);
    }

    /**
     * Helper Method: getMaxPoints
     * 
     * Intuition:
     * - Recursive DP function that finds the maximum points for days [0, day]
     *   given that the last task performed was 'lastTask'.
     * 
     * Explanation:
     * - Base case: day == 0, choose best activity excluding lastTask
     * - For day > 0:
     *   - Try all activities (0,1,2) except lastTask
     *   - For each valid activity, add current points and recurse to day-1
     *   - Take maximum over all valid choices
     * - Memoize result in dp[lastTask][day]
     * 
     * Time Complexity: O(1) per state transition, overall O(n)
     * Space Complexity: O(n) due to recursion depth and memo table
     * 
     * @param points    2D array of merit points for each activity on each day
     * @param dp        Memo table where dp[lastTask][day] stores best answer
     * @param day       Current day under consideration
     * @param lastTask  Last task performed (0,1,2) or 3 if no previous task
     * @return          Maximum points obtainable from days [0, day]
     */
    public static int getMaxPoints(int[][] points, int[][] dp, int day, int lastTask) {
        // Return memoized answer if this state was computed before
        if (dp[lastTask][day] != -1) {
            return dp[lastTask][day];
        }

        // Base case: first day (day == 0)
        if (day == 0) {
            int maxPoints = 0;
            // Try all activities except the one from previous day
            for (int i = 0; i < 3; i++) {
                if (lastTask != i) {
                    maxPoints = Math.max(maxPoints, points[day][i]);
                }
            }
            return dp[lastTask][day] = maxPoints;
        }

        // For other days, try all activities except the one from previous day
        int maxPoints = 0;
        for (int i = 0; i < 3; i++) {
            if (lastTask != i) {
                // Choose activity i on current day and recurse to previous day
                maxPoints = Math.max(maxPoints, 
                    points[day][i] + getMaxPoints(points, dp, day - 1, i));
            }
        }

        // Memoize and return the best result
        return dp[lastTask][day] = maxPoints;
    }

    /**
     * Approach 2: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - Convert the top-down recurrence into an iterative DP where we build the
     *   answer from smaller subproblems. We fill the DP table from day 0 to n-1.
     * - For each day and last task, we compute the maximum points by trying all
     *   valid activities and adding the result from the previous day.
     * 
     * Explanation:
     * - Initialize base case for day 0: dp[0][last] = max points excluding last task
     * - For each day from 1 to n-1:
     *   - For each last task (0,1,2,3):
     *     - Try all activities except the last task
     *     - Add current day points to previous day's result
     *     - Take maximum over all valid choices
     * - Return dp[n-1][3] (last day with no previous task)
     * 
     * Time Complexity: O(n * 4 * 3) = O(n) - Each state computed once
     * Space Complexity: O(n * 4) - DP table of size n x 4
     * 
     * @param n       Number of days for training
     * @param points  2D array where points[i][j] = points for activity j on day i
     * @return        Maximum merit points that can be earned
     */
    public static int ninjaTrainingTabulation(int n, int points[][]) {
        // Handle edge cases: invalid input
        if (n <= 0 || points == null || points.length == 0) {
            return 0; // No training possible
        }

        // Initialize DP table: n rows for days, 4 columns for lastTask (0,1,2,3)
        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1); // Mark all states as uncomputed
        }

        // Base case: day 0 - compute maximum points for each last task
        dp[0][0] = Math.max(points[0][1], points[0][2]); // Last task was 0, choose from 1,2
        dp[0][1] = Math.max(points[0][0], points[0][2]); // Last task was 1, choose from 0,2
        dp[0][2] = Math.max(points[0][0], points[0][1]); // Last task was 2, choose from 0,1
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2])); // No previous task

        // Fill DP table from day 1 to n-1
        for (int day = 1; day < n; day++) {
            for (int last = 0; last <= 3; last++) {
                int maxCost = 0;
                // Try all activities except the one from previous day
                for (int i = 0; i <= 2; i++) {
                    if (i != last) {
                        // Add current day points to previous day's result
                        maxCost = Math.max(maxCost, points[day][i] + dp[day - 1][i]);
                    }
                }
                dp[day][last] = maxCost;
            }
        }

        // Return the result for the last day with no previous task
        return dp[n - 1][3];
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] points1 = {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
        System.out.println("Input: n=3, points=[[1,2,5],[3,1,1],[3,3,3]], Expected: 11, Output: " +
                ninjaTraining(3, points1));

        int[][] points2 = {{10, 40, 70}, {20, 50, 80}, {30, 60, 90}};
        System.out.println("Input: n=3, points=[[10,40,70],[20,50,80],[30,60,90]], Expected: 210, Output: " +
                ninjaTraining(3, points2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: n=0, points=[], Expected: 0, Output: " +
                ninjaTraining(0, new int[][]{}));
        System.out.println("Input: n=1, points=[[1,2,3]], Expected: 3, Output: " +
                ninjaTraining(1, new int[][]{{1, 2, 3}}));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] points3 = {{1, 1, 1}};
        System.out.println("Input: n=1, points=[[1,1,1]], Expected: 1, Output: " +
                ninjaTraining(1, points3));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] points4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        System.out.println("Input: n=4, points=[[1,2,3],[4,5,6],[7,8,9],[10,11,12]], Expected: 33, Output: " +
                ninjaTraining(4, points4));

        // Complex/Large Input
        System.out.println("\nComplex/Large Input:");
        int[][] points5 = {{100, 50, 25}, {50, 100, 25}, {25, 50, 100}};
        System.out.println("Input: n=3, points=[[100,50,25],[50,100,25],[25,50,100]], Expected: 250, Output: " +
                ninjaTraining(3, points5));

        // Tabulation Method Tests
        System.out.println("\nTabulation Method Tests:");
        System.out.println("Input: n=3, points=[[1,2,5],[3,1,1],[3,3,3]], Expected: 11, Output: " +
                ninjaTrainingTabulation(3, points1));
        System.out.println("Input: n=3, points=[[10,40,70],[20,50,80],[30,60,90]], Expected: 210, Output: " +
                ninjaTrainingTabulation(3, points2));
        System.out.println("Input: n=0, points=[], Expected: 0, Output: " +
                ninjaTrainingTabulation(0, new int[][]{}));
        System.out.println("Input: n=1, points=[[1,2,3]], Expected: 3, Output: " +
                ninjaTrainingTabulation(1, new int[][]{{1, 2, 3}}));
        System.out.println("Input: n=4, points=[[1,2,3],[4,5,6],[7,8,9],[10,11,12]], Expected: 33, Output: " +
                ninjaTrainingTabulation(4, points4));
    }
}
