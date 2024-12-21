/**
 * Problem Statement:
 * 
 *     Given an array of integers, we need to find the number of "good triplets" in the array. A triplet (i, j, k) is 
 *     considered good if it satisfies the following conditions:
 *     - i < j < k
 *     - |arr[i] - arr[j]| <= a
 *     - |arr[j] - arr[k]| <= b
 *     - |arr[i] - arr[k]| <= c
 *     
 *     Here, a, b, and c are given integer values, and the array `arr` has `n` elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 1000).
 *     - Three integers `a`, `b`, and `c` that represent the bounds for the differences between the elements.
 * 
 * Output:
 *     - An integer representing the number of good triplets in the array.
 * 
 * Example:
 *     Input:
 *     5
 *     3 1 4 6 5
 *     2 2 2
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The three good triplets are:
 *     (0, 1, 2), (1, 2, 3), and (2, 3, 4), which satisfy the conditions for a, b, and c respectively.
 */

import java.util.Scanner;

public class j07CountGoodTriplets {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        // Call the methods and print the result
        System.out.println(countGoodTriplets(arr, a, b, c)); // Brute force solution
        System.out.println(countGoodTripletsTwoPointers(arr, a, b, c)); // Optimized solution using two pointers

        in.close();
    }

    /**
     * Approach 1: Brute Force Method (Triple Nested Loop)
     * 
     * Intuition:
     * - We iterate over all possible triplets (i, j, k) where i < j < k.
     * - For each triplet, we check if the absolute differences between the elements 
     *   satisfy the conditions for a, b, and c.
     * - If the triplet satisfies the condition, we increment the count.
     * 
     * Time Complexity:
     * - O(n^3), where n is the size of the array. This is because we use three nested loops 
     *   to check all possible triplets.
     * 
     * Space Complexity:
     * - O(1), since we are only using a few extra variables to count the triplets.
     * 
     * @param arr The input array of numbers.
     * @param a The first bound for the differences.
     * @param b The second bound for the differences.
     * @param c The third bound for the differences.
     * @return The number of good triplets.
     */
    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        // Iterate over all possible triplets
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    // Check if the triplet satisfies the conditions
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Optimized Method using Two Pointers
     * 
     * Intuition:
     * - Instead of using three nested loops, we use a more efficient approach by applying two pointers.
     * - We first iterate over the array and fix the first element `i`. For each fixed `i`, we iterate over the 
     *   remaining elements `j` and `k` (with `j > i` and `k > j`).
     * - We check if the triplet satisfies the conditions for a, b, and c using a helper method `check`.
     * - This solution reduces the number of iterations compared to the brute force approach.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the array. We reduce the problem from O(n^3) by eliminating the third nested loop.
     * 
     * Space Complexity:
     * - O(1), as we are using only a constant amount of extra space for the pointers and the count.
     * 
     * @param arr The input array of numbers.
     * @param a The first bound for the differences.
     * @param b The second bound for the differences.
     * @param c The third bound for the differences.
     * @return The number of good triplets.
     */
    public static int countGoodTripletsTwoPointers(int[] arr, int a, int b, int c) {
        int count = 0;
        int i = 0, j = i + 1, k = j + 1;

        // Iterate over the array with two pointers
        while (i + 2 < arr.length) {
            if (check(arr, i, j, k, a, b, c)) {
                count++;
            }
            if (k < arr.length) {
                k++;
            }
            if (k == arr.length) {
                j++;
                k = j + 1;
            }
            if (j + 1 == arr.length) {
                i++;
                j = i + 1;
                k = j + 1;
            }
        }
        return count;
    }

    /**
     * Helper Method: check
     * 
     * This method checks if the current triplet (i, j, k) satisfies the given conditions for a, b, and c.
     * 
     * @param arr The input array of numbers.
     * @param i The index of the first element.
     * @param j The index of the second element.
     * @param k The index of the third element.
     * @param a The first bound for the differences.
     * @param b The second bound for the differences.
     * @param c The third bound for the differences.
     * @return True if the triplet satisfies the conditions; otherwise, false.
     */
    public static boolean check(int[] arr, int i, int j, int k, int a, int b, int c) {
        return (Math.abs(arr[i] - arr[j]) <= a
                && Math.abs(arr[j] - arr[k]) <= b
                && Math.abs(arr[i] - arr[k]) <= c);
    }
}
