/**
 * LeetCode 406. Queue Reconstruction by Height
 * 
 * Problem Statement:
 *     You are given an array of people, where people[i] = [hi, ki] represents the ith person of
 *     height hi with exactly ki other people in front who have a height greater than or equal to hi.
 *     Reconstruct and return the queue that is represented by the input array people.
 * 
 * Input:
 *     - people (int[][]): Array of people where people[i] = [hi, ki]
 *     - hi: height of the ith person
 *     - ki: number of people with height >= hi that should be in front
 * 
 * Output:
 *     - int[][]: The reconstructed queue satisfying the height requirements
 * 
 * Example:
 *     Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 *     Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 
 *     Explanation:
 *     - Person with height 5 is first as no one is taller
 *     - Person with height 7 is second as no one is taller
 *     - Person with height 5 is third as two people (7,7) are taller
 *     - And so on...
 */

import java.util.ArrayList;
import java.util.Arrays;

public class j07QueueReconstructionByHeight {

    /**
     * Approach: Greedy with Custom Sorting
     * 
     * Intuition:
     * - Sort people by height in descending order
     * - For same height, sort by k in ascending order
     * - Insert each person at their k position
     * - This works because taller people don't affect shorter people's k value
     * 
     * Explanation:
     * - Step 1: Sort people array by height (descending) and k (ascending)
     * - Step 2: Create ArrayList to allow efficient insertions
     * - Step 3: Insert each person at their k position
     * - Step 4: Convert ArrayList back to array
     * 
     * Time Complexity: O(n²) where n is number of people
     *                  - O(n log n) for sorting
     *                  - O(n²) for ArrayList insertions
     * Space Complexity: O(n) for storing the result
     * 
     * @param people    Array of people where people[i] = [hi, ki]
     * @return         Reconstructed queue satisfying height requirements
     */
    public static int[][] reconstructQueue(int[][] people) {
        // Sort by height (descending) and k (ascending)
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0])
                return b[0] - a[0];
            return a[1] - b[1];
        });

        // Use ArrayList for efficient insertions
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }

        // Convert ArrayList back to array
        int[][] out = new int[people.length][2];
        int i = 0;
        for (int[] pair : list) {
            out[i++] = pair;
        }

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[][] input1 = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] expected1 = {{5,0},{7,0},{5,2},{6,1},{4,4},{7,1}};
        System.out.println("Input: " + Arrays.deepToString(input1));
        System.out.println("Expected: " + Arrays.deepToString(expected1));
        System.out.println("Output: " + Arrays.deepToString(reconstructQueue(input1)));

        // Test Case 2: Empty array
        System.out.println("\nEdge Case - Empty Array:");
        int[][] input2 = {};
        System.out.println("Input: " + Arrays.deepToString(input2));
        System.out.println("Expected: [], Output: " + 
            Arrays.deepToString(reconstructQueue(input2)));

        // Test Case 3: Single person
        System.out.println("\nEdge Case - Single Person:");
        int[][] input3 = {{5,0}};
        System.out.println("Input: " + Arrays.deepToString(input3));
        System.out.println("Expected: [[5,0]], Output: " + 
            Arrays.deepToString(reconstructQueue(input3)));

        // Test Case 4: All same height
        System.out.println("\nSpecial Case - Same Height:");
        int[][] input4 = {{5,0},{5,1},{5,2}};
        System.out.println("Input: " + Arrays.deepToString(input4));
        System.out.println("Expected: [[5,0],[5,1],[5,2]], Output: " + 
            Arrays.deepToString(reconstructQueue(input4)));
    }
}
