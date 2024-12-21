/**
 * Problem Statement:
 * 
 *     Given two arrays `arr1` and `arr2`, and an integer `target`, find the total number of 
 *     unique pairs such that one element comes from `arr1` and the other comes from `arr2` whose 
 *     sum is equal to the target.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the first array.
 *     - An array `arr1` of size `n` where each element satisfies (1 <= arr1[i] <= 10^5).
 *     - An integer `m` (1 <= m <= 10^5), representing the size of the second array.
 *     - An array `arr2` of size `m` where each element satisfies (1 <= arr2[i] <= 10^5).
 *     - An integer `target` (2 <= target <= 2 * 10^5) representing the target sum.
 * 
 * Output:
 *     - The number of pairs (i, j) such that `arr1[i] + arr2[j] == target`.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     4
 *     5 6 7 8
 *     9
 *     Output:
 *     2
 *     
 *     Explanation:
 *     The valid pairs are (1, 8) and (3, 6), both of which sum to 9.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03CountKSumPairsIn2Arrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the first array
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt(); // Input: elements of the first array
        }
        int m = in.nextInt(); // Input: size of the second array
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = in.nextInt(); // Input: elements of the second array
        }
        int target = in.nextInt(); // Input: the target sum

        // Call your solution
        System.out.println(countPairs(arr1, arr2, target));

        // Call optimized solution (if applicable)
        System.out.println(countPairsEfficient(arr1, arr2, target));

        in.close();
    }

    /**
     * Approach: Brute Force Solution
     * 
     * Intuition:
     * - The brute force approach checks every pair of elements from `arr1` and `arr2`.
     * - If the sum of a pair equals the target, it increments the count.
     * - This approach does not take advantage of any sorted order in the arrays.
     * 
     * Time Complexity:
     * - O(n * m), where `n` and `m` are the sizes of `arr1` and `arr2`. We check every possible 
     *   pair from both arrays.
     * 
     * Space Complexity:
     * - O(1), since no extra space is used apart from a few variables.
     * 
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @param x The target sum.
     * @return The number of valid pairs whose sum equals `x`.
     */
    public static int countPairs(int[] arr1, int[] arr2, int x) {
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] + arr2[j] == x)
                    count++; // Increment count if a valid pair is found
            }
        }
        return count;
    }

    /**
     * Approach: Optimized Two-Pointer Solution
     * 
     * Intuition:
     * - First, we sort both arrays to allow efficient pairing.
     * - We initialize two pointers: one at the start of `arr1` (i) and one at the end of `arr2` (j).
     * - If the sum of the elements at the two pointers equals `x`, we have found a valid pair.
     * - If the sum is less than `x`, we move the pointer in `arr1` forward to increase the sum.
     * - If the sum is greater than `x`, we move the pointer in `arr2` backward to decrease the sum.
     * - This avoids checking every possible pair and reduces the time complexity.
     * 
     * Time Complexity:
     * - O(n log n + m log m), where `n` and `m` are the sizes of `arr1` and `arr2`. The sorting 
     *   step takes O(n log n + m log m), and the two-pointer traversal takes O(n + m).
     * 
     * Space Complexity:
     * - O(1), since we are using constant extra space apart from the input arrays.
     * 
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @param x The target sum.
     * @return The number of valid pairs whose sum equals `x`.
     */
    public static int countPairsEfficient(int arr1[], int arr2[], int x) {
        Arrays.sort(arr1); // Sort the first array
        Arrays.sort(arr2); // Sort the second array
        int count = 0;
        int i = 0; // Pointer for arr1
        int j = arr2.length - 1; // Pointer for arr2

        // Use two pointers to find valid pairs
        while (i < arr1.length && j >= 0) {
            if (arr1[i] + arr2[j] == x) {
                count++; // Increment the count if the pair sums to x
                i++; // Move the left pointer to the right
                j--; // Move the right pointer to the left
            } else if (arr1[i] + arr2[j] > x) {
                j--; // Decrease the sum by moving the right pointer to the left
            } else {
                i++; // Increase the sum by moving the left pointer to the right
            }
        }

        return count;
    }
}
