/**
 * LeetCode 1345. Jump Game IV
 *
 * Problem Statement:
 *     Given an array of integers arr, you are initially positioned at the first index.
 *     In one step, you can jump from index i to:
 *         - i + 1 (if within bounds)
 *         - i - 1 (if within bounds)
 *         - any index j where arr[i] == arr[j] and i != j
 *     Return the minimum number of steps to reach the last index of the array.
 *
 * Input:
 *     - arr (int[]): Array of integers
 *
 * Output:
 *     - int: Minimum number of steps to reach the last index
 *
 * Constraints:
 *     1 <= arr.length <= 5 * 10^4
 *     -10^8 <= arr[i] <= 10^8
 *
 * Example:
 *     Input: arr = [100,-23,100,100,100,23,23,23,3,100,1,100,100,100,100]
 *     Output: 3
 *     Explanation: Jump from 0 -> 9 -> 1 -> 11 -> 14 (last index)
 *
 *     Input: arr = [7]
 *     Output: 0
 *     Explanation: Already at the last index.
 */

import java.util.*;

public class j12JumpGameIV {
    /**
     * Approach: Breadth-First Search (BFS) with value-to-indices mapping
     *
     * Intuition:
     * - Treat each index as a node; edges exist to i+1, i-1, and all indices with the same value.
     * - Use BFS to find the shortest path from index 0 to the last index.
     * - Use a map to quickly find all indices with the same value.
     * - Mark visited indices to avoid cycles and redundant work.
     * - Clear the list for a value after processing to avoid TLE.
     *
     * Explanation:
     * - Step 1: Build a map from value to all indices with that value.
     * - Step 2: Start BFS from index 0, marking visited.
     * - Step 3: For each index, try jumps to i+1, i-1, and all same-value indices.
     * - Step 4: If last index is reached, return steps.
     * - Step 5: Clear the value's list after processing to avoid revisiting.
     *
     * Time Complexity: O(N) where N = arr.length (each index processed at most once)
     * Space Complexity: O(N) for visited array, queue, and value-to-indices map
     *
     * @param arr Array of integers
     * @return Minimum number of steps to reach the last index
     */
    public static int minJumps(int[] arr) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); // Map value to all indices
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        boolean[] visited = new boolean[arr.length]; // Track visited indices
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS
        queue.add(0); // Start BFS from index 0

        int steps = 0; // Number of jumps taken
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of indices to process at current BFS level
            for (int i = 0; i < size; i++) {
                int cell = queue.poll(); // Get current index
                if (visited[cell])
                    continue; // Skip if already visited
                if (cell == arr.length - 1)
                    return steps; // Reached last index
                visited[cell] = true; // Mark current index as visited
                int left = cell - 1;
                int right = cell + 1;
                // Try jump to left neighbor
                if (left >= 0 && !visited[left]) {
                    queue.add(left);
                }
                // Try jump to right neighbor
                if (right < arr.length && !visited[right]) {
                    queue.add(right);
                }
                // Try jump to all indices with the same value
                for (int neb : map.get(arr[cell])) {
                    if (!visited[neb])
                        queue.add(neb);
                }
                map.get(arr[cell]).clear(); // Clear list to avoid redundant work
            }
            steps++; // Increment jump count after processing current level
        }
        return steps; // Should never reach here if input is valid
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] arr1 = {100,-23,100,100,100,23,23,23,3,100,1,100,100,100,100};
        System.out.println("Basic Test Case:");
        // Should return 3
        System.out.println("Expected: 3, Output: " + minJumps(arr1));

        // Test Case 2: Single element
        int[] arr2 = {7};
        System.out.println("\nSingle Element:");
        // Should return 0
        System.out.println("Expected: 0, Output: " + minJumps(arr2));

        // Test Case 3: All unique values
        int[] arr3 = {1,2,3,4,5,6,7,8,9};
        System.out.println("\nAll Unique Values:");
        // Should return arr.length - 1
        System.out.println("Expected: 8, Output: " + minJumps(arr3));

        // Test Case 4: All same values
        int[] arr4 = {5,5,5,5,5};
        System.out.println("\nAll Same Values:");
        // Should return 1
        System.out.println("Expected: 1, Output: " + minJumps(arr4));
    }
}
