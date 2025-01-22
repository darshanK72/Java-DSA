/**
 * Problem Statement:
 * 
 *     Given an array `A` where `A[i]` represents the height of the i-th tree,
 *     and an integer `B` representing the amount of wood required, find the
 *     maximum height at which to cut the trees such that the amount of wood
 *     collected is at least `B`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `A` of size `n` where each element is a positive integer
 *       representing the height of a tree.
 *     - An integer `B` (1 <= B <= 10^9), representing the amount of wood required.
 * 
 * Output:
 *     - An integer representing the maximum height at which to cut the trees
 *       such that the amount of wood collected is at least `B`.
 * 
 * Example:
 *     Input:
 *     n = 5
 *     A = [4, 42, 40, 26, 46]
 *     B = 20
 *     Output:
 *     36
 * 
 *     Explanation:
 *     The maximum height at which to cut the trees such that the amount of wood
 *     collected is at least 20 is 36.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j12WoodCuttingMadeEasy {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(in.nextInt()); // Input: elements of the array
        }
        int B = in.nextInt(); // Required wood

        // Call your solution
        System.out.printf("Maximum Height: %d\n", calculateMaxHeight(A, B));

        in.close();
    }

    /**
     * Approach: Binary Search on Answer
     * 
     * Intuition:
     * - The problem can be solved using binary search on the answer. The maximum
     *   height at which to cut the trees must be at least 0, and at most the
     *   height of the tallest tree.
     * - We perform binary search within this range to find the maximum height that
     *   allows us to collect at least the required amount of wood.
     * 
     * Time Complexity:
     * - O(n log(max(A))). This is because we perform binary search on the range
     *   [0, max(A)], and for each mid value, we iterate through the array to
     *   calculate the amount of wood collected.
     * 
     * Space Complexity:
     * - O(1). We only use a few extra variables for the binary search and the
     *   feasibility check.
     * 
     * @param A The input array of tree heights.
     * @param B The amount of wood required.
     * @return The maximum height at which to cut the trees such that the amount of
     *         wood collected is at least `B`.
     */
    public static int calculateMaxHeight(ArrayList<Integer> A, int B) {
        long max = -1;
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(A.get(i), max);
        }
        long min = 0;
        while (min <= max) {
            long mid = min + (max - min) / 2;
            if (isPossible(A, B, mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return (int) max;
    }

    /**
     * Helper method to check if it is possible to collect at least the required
     * amount of wood by cutting the trees at the given height.
     * 
     * @param A The input array of tree heights.
     * @param B The amount of wood required.
     * @param height The height at which to cut the trees.
     * @return True if it is possible to collect at least the required amount of
     *         wood by cutting the trees at the given height, otherwise false.
     */
    public static boolean isPossible(ArrayList<Integer> A, int B, long height) {
        long woodCollected = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) > height) {
                woodCollected += A.get(i) - height;
            }
        }
        return woodCollected >= B;
    }
}