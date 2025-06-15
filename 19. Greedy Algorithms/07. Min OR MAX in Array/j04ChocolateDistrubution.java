/**
 * GeeksForGeeks: Chocolate Distribution Problem
 * 
 * Problem Statement:
 *     Given an array of n packets where each packet contains a certain number of chocolates.
 *     There are m students, the task is to distribute chocolate packets such that:
 *     1. Each student gets exactly one packet
 *     2. The difference between the number of chocolates given to the students
 *        who get packets with maximum and minimum chocolates is minimum
 * 
 * Input:
 *     - arr (ArrayList<Integer>): Array of chocolate packets
 *     - m (int): Number of students
 *     - 1 <= m <= n <= 10^5
 *     - 1 <= arr[i] <= 10^9
 * 
 * Output:
 *     - Minimum difference between maximum and minimum chocolates
 * 
 * Example:
 *     Input: 
 *     arr = [7, 3, 2, 4, 9, 12, 56]
 *     m = 3
 * 
 *     Output: 2
 * 
 *     Explanation:
 *     We need to pick 3 packets. The minimum difference between maximum and
 *     minimum packets is 2 (4-2) when we pick packets [2, 3, 4].
 */

import java.util.ArrayList;
import java.util.Collections;

public class j04ChocolateDistrubution {
    /**
     * Approach: Sliding Window with Sorting
     * 
     * Intuition:
     * - To minimize the difference between max and min chocolates,
     *   we should pick consecutive packets after sorting
     * - After sorting, we can use a sliding window of size m to find
     *   the minimum difference between first and last element in window
     * 
     * Explanation:
     * 1. Sort the array of chocolate packets
     * 2. Use a sliding window of size m
     * 3. For each window, calculate difference between first and last element
     * 4. Keep track of minimum difference found
     * 
     * Time Complexity: O(n log n) where n is the number of packets
     *                  Due to sorting operation
     * Space Complexity: O(1) as we only use a few variables
     * 
     * @param arr Array of chocolate packets
     * @param m   Number of students
     * @return Minimum difference between maximum and minimum chocolates
     */
    public int findMinDiff(ArrayList<Integer> arr, int m) {
        // If number of students is 0 or greater than packets, return 0
        if (m == 0 || arr.size() == 0 || m > arr.size())
            return 0;

        // Sort the array
        Collections.sort(arr);

        // Initialize minimum difference
        int minDiff = Integer.MAX_VALUE;

        // Find minimum difference using sliding window
        for (int i = 0; i <= arr.size() - m; i++) {
            int currentDiff = arr.get(i + m - 1) - arr.get(i);
            minDiff = Math.min(minDiff, currentDiff);
        }

        return minDiff;
    }

    public static void main(String[] args) {
        j04ChocolateDistrubution solution = new j04ChocolateDistrubution();

        // Test Case 1: Basic case
        System.out.println("\nTest Case 1: Basic case");
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(7);
        arr1.add(3);
        arr1.add(2);
        arr1.add(4);
        arr1.add(9);
        arr1.add(12);
        arr1.add(56);
        int m1 = 3;
        System.out.println("Input: arr = " + arr1 + ", m = " + m1);
        System.out.println("Output: " + solution.findMinDiff(arr1, m1));
        System.out.println("Expected: 2");

        // Test Case 2: All same chocolates
        System.out.println("\nTest Case 2: All same chocolates");
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr2.add(3);
        arr2.add(3);
        arr2.add(3);
        arr2.add(3);
        int m2 = 2;
        System.out.println("Input: arr = " + arr2 + ", m = " + m2);
        System.out.println("Output: " + solution.findMinDiff(arr2, m2));
        System.out.println("Expected: 0");

        // Test Case 3: Minimum possible difference
        System.out.println("\nTest Case 3: Minimum possible difference");
        ArrayList<Integer> arr3 = new ArrayList<>();
        arr3.add(1);
        arr3.add(2);
        arr3.add(3);
        arr3.add(4);
        arr3.add(5);
        int m3 = 2;
        System.out.println("Input: arr = " + arr3 + ", m = " + m3);
        System.out.println("Output: " + solution.findMinDiff(arr3, m3));
        System.out.println("Expected: 1");

        // Test Case 4: Edge case - m equals array size
        System.out.println("\nTest Case 4: Edge case - m equals array size");
        ArrayList<Integer> arr4 = new ArrayList<>();
        arr4.add(1);
        arr4.add(5);
        arr4.add(9);
        int m4 = 3;
        System.out.println("Input: arr = " + arr4 + ", m = " + m4);
        System.out.println("Output: " + solution.findMinDiff(arr4, m4));
        System.out.println("Expected: 8");
    }
}