/**
 * LeetCode 1376. Time Needed to Inform All Employees
 *
 * Problem Statement:
 *     A company has n employees with unique IDs from 0 to n-1. The head of the
 *     company has headID. Each employee i has a direct manager manager[i] and
 *     informTime[i] minutes to inform their direct subordinates. When an employee
 *     receives the news, they inform all their direct subordinates simultaneously.
 *     Return the total time needed to inform all employees.
 *
 * Input:
 *     - n: int, number of employees (1 <= n <= 10^5)
 *     - headID: int, ID of the head of the company (0 <= headID < n)
 *     - manager: int[], manager[i] is the direct manager of i (or -1 for head)
 *     - informTime: int[], time for i to inform subordinates (0 <= informTime[i] <= 1000)
 *
 * Output:
 *     - int: Total time needed to inform all employees
 *
 * Example:
 *     Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
 *     Output: 1
 *
 *     Explanation:
 *     Head (2) informs all subordinates in 1 minute. No further time needed.
 */

import java.util.*;

public class j09TimeToInformEmployees {
    /**
     * Approach 1: DFS with Accumulated Time (Backtracking)
     *
     * Intuition:
     * - Build an adjacency list to represent the management tree.
     * - Use DFS to traverse from head, accumulating inform time.
     * - Track the maximum time taken to reach any employee.
     *
     * Explanation:
     * - For each employee, add them to their manager's list in the adjacency list.
     * - Start DFS from headID, at each step add informTime of current manager.
     * - Update maxTime if a longer path is found.
     *
     * Time Complexity: O(n), each employee is visited once.
     * Space Complexity: O(n), for adjacency list and recursion stack.
     *
     * @param n           Number of employees
     * @param headID      Head of the company
     * @param manager     Manager array
     * @param informTime  Inform time array
     * @return            Total time to inform all employees
     */
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<Integer>[] adj = new ArrayList[n + 1]; // Adjacency list
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (manager[i] == -1)
                continue;
            adj[manager[i]].add(i); // Add employee to manager's list
        }
        int[] maxTime = new int[1];
        maxTime[0] = Integer.MIN_VALUE;
        findMaxTimeToInform(adj, informTime, headID, maxTime, 0);
        return maxTime[0];
    }

    /**
     * Helper Method: DFS to find max inform time
     *
     * Intuition:
     * - Recursively traverse subordinates, accumulating inform time.
     *
     * Explanation:
     * - For each subordinate, call DFS with updated time.
     * - Update maxTime if a longer path is found.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param adj         Adjacency list
     * @param informTime  Inform time array
     * @param index       Current employee
     * @param maxTime     Array holding max time found
     * @param currTime    Current accumulated time
     */
    private static void findMaxTimeToInform(ArrayList<Integer>[] adj, int[] informTime, int index, int[] maxTime,
            int currTime) {
        maxTime[0] = Math.max(maxTime[0], currTime + informTime[index]);
        for (int neb : adj[index]) {
            findMaxTimeToInform(adj, informTime, neb, maxTime, currTime + informTime[index]);
        }
    }

    /**
     * Approach 2: DFS Returning Max Path (Cleaner)
     *
     * Intuition:
     * - For each manager, recursively find the max time among all subordinates.
     * - Add current manager's inform time to the max of subordinates.
     *
     * Explanation:
     * - Build adjacency list, then DFS from headID.
     * - For each subordinate, get max time recursively.
     * - Return informTime[curr] + max(subordinate times).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param n           Number of employees
     * @param headID      Head of the company
     * @param manager     Manager array
     * @param informTime  Inform time array
     * @return            Total time to inform all employees
     */
    public static int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adj[manager[i]].add(i);
            }
        }
        return findMaxTimeToInform2(adj, informTime, headID);
    }

    /**
     * Helper Method: DFS returning max path
     *
     * Intuition:
     * - For each subordinate, recursively get max inform time.
     *
     * Explanation:
     * - For each subordinate, call DFS and track max.
     * - Return informTime[curr] + max(subordinate times).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param adj         Adjacency list
     * @param informTime  Inform time array
     * @param curr        Current employee
     * @return            Max time to inform all subordinates
     */
    private static int findMaxTimeToInform2(List<Integer>[] adj, int[] informTime, int curr) {
        int max = 0;
        for (int sub : adj[curr]) {
            max = Math.max(max, findMaxTimeToInform2(adj, informTime, sub));
        }
        return informTime[curr] + max;
    }

    public static void main(String[] args) {
        // Basic Test Case
        System.out.println("\nBasic Test Case:");
        int n1 = 6, headID1 = 2;
        int[] manager1 = {2,2,-1,2,2,2};
        int[] informTime1 = {0,0,1,0,0,0};
        System.out.println("Input: n=6, headID=2, manager=[2,2,-1,2,2,2], informTime=[0,0,1,0,0,0], Expected: 1, Output: " + numOfMinutes(n1, headID1, manager1, informTime1));
        System.out.println("Output (Approach 2): " + numOfMinutes2(n1, headID1, manager1, informTime1));

        // Edge Case: Only head
        System.out.println("\nEdge Case:");
        int n2 = 1, headID2 = 0;
        int[] manager2 = {-1};
        int[] informTime2 = {0};
        System.out.println("Input: n=1, headID=0, manager=[-1], informTime=[0], Expected: 0, Output: " + numOfMinutes(n2, headID2, manager2, informTime2));

        // Boundary Case: Linear chain
        System.out.println("\nBoundary Case:");
        int n3 = 4, headID3 = 0;
        int[] manager3 = {-1,0,1,2};
        int[] informTime3 = {1,2,3,4};
        System.out.println("Input: n=4, headID=0, manager=[-1,0,1,2], informTime=[1,2,3,4], Expected: 10, Output: " + numOfMinutes(n3, headID3, manager3, informTime3));

        // Special Case: Star structure
        System.out.println("\nSpecial Case:");
        int n4 = 5, headID4 = 0;
        int[] manager4 = {-1,0,0,0,0};
        int[] informTime4 = {2,1,1,1,1};
        System.out.println("Input: n=5, headID=0, manager=[-1,0,0,0,0], informTime=[2,1,1,1,1], Expected: 3, Output: " + numOfMinutes(n4, headID4, manager4, informTime4));
    }
}
