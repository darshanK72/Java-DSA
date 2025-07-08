/**
 * LeetCode 1306. Jump Game III
 *
 * Problem Statement:
 *     Given an array of non-negative integers arr, you are initially positioned at start index.
 *     In one step, you can jump from index i to i + arr[i] or i - arr[i], provided you stay within bounds.
 *     Return true if you can reach any index with value 0, otherwise false.
 *
 * Input:
 *     - arr (int[]): Array of non-negative integers
 *     - start (int): Starting index
 *
 * Output:
 *     - boolean: True if you can reach any index with value 0, else false
 *
 * Constraints:
 *     1 <= arr.length <= 5 * 10^4
 *     0 <= arr[i] < arr.length
 *     0 <= start < arr.length
 *
 * Example:
 *     Input: arr = [4,2,3,0,3,1,2], start = 5
 *     Output: true
 *     Explanation: Jump from 5 -> 4 -> 1 -> 3 (arr[3] == 0)
 *
 *     Input: arr = [4,2,3,0,3,1,2], start = 0
 *     Output: true
 *     Explanation: Jump from 0 -> 4 -> 1 -> 3 (arr[3] == 0)
 *
 *     Input: arr = [3,0,2,1,2], start = 2
 *     Output: false
 *     Explanation: You cannot reach any index with value 0.
 */

import java.util.*;

public class j11JumpGameIII {
    /**
     * Approach: Breadth-First Search (BFS)
     *
     * Intuition:
     * - Treat each index as a node; edges exist to i + arr[i] and i - arr[i] if within bounds.
     * - Use BFS to explore all reachable indices from start, marking visited to avoid cycles.
     * - If any index with value 0 is reached, return true.
     *
     * Explanation:
     * - Step 1: Initialize queue with start index and visited array.
     * - Step 2: While queue is not empty, pop index and check if arr[index] == 0.
     * - Step 3: For each possible jump (left/right), if within bounds and not visited, enqueue.
     * - Step 4: If queue is exhausted without finding 0, return false.
     *
     * Time Complexity: O(N) where N = arr.length (each index visited at most once)
     * Space Complexity: O(N) for visited array and queue
     *
     * @param arr   Array of non-negative integers
     * @param start Starting index
     * @return True if can reach any index with value 0, else false
     */
    public static boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length]; // Track visited indices
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS
        queue.add(start); // Start BFS from 'start' index
        while (!queue.isEmpty()) {
            int cell = queue.poll(); // Get current index
            if (arr[cell] == 0)
                return true; // Found a zero, return true
            visited[cell] = true; // Mark current index as visited
            int right = cell + arr[cell]; // Calculate right jump
            int left = cell - arr[cell]; // Calculate left jump
            // Enqueue right jump if within bounds and not visited
            if (right < arr.length && !visited[right]) {
                queue.add(right);
            }
            // Enqueue left jump if within bounds and not visited
            if (left >= 0 && !visited[left]) {
                queue.add(left);
            }
        }
        return false; // No path to zero found
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] arr1 = {4,2,3,0,3,1,2};
        int start1 = 5;
        System.out.println("Basic Test Case:");
        // Should return true (can reach index 3)
        System.out.println("Expected: true, Output: " + canReach(arr1, start1));

        // Test Case 2: Start at index 0
        int[] arr2 = {4,2,3,0,3,1,2};
        int start2 = 0;
        System.out.println("\nStart at index 0:");
        // Should return true (can reach index 3)
        System.out.println("Expected: true, Output: " + canReach(arr2, start2));

        // Test Case 3: No path to zero
        int[] arr3 = {3,0,2,1,2};
        int start3 = 2;
        System.out.println("\nNo path to zero:");
        // Should return false
        System.out.println("Expected: false, Output: " + canReach(arr3, start3));

        // Test Case 4: Edge case (zero at start)
        int[] arr4 = {0,1,2,3};
        int start4 = 0;
        System.out.println("\nZero at start:");
        // Should return true
        System.out.println("Expected: true, Output: " + canReach(arr4, start4));
    }
}
