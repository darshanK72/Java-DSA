/**
 * Problem Statement:
 *
 *     Given an integer array `arr`, we are tasked with finding all circular subarrays of the array.
 *     A circular subarray is a subarray that wraps around the end of the array and continues from the beginning. 
 *     We need to print all circular subarrays of different lengths, as well as return them in different formats.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^3 <= arr[i] <= 10^3).
 *
 * Output:
 *     - Print all circular subarrays.
 *     - Return all circular subarrays as a list of lists.
 *     - Efficient implementation of circular subarrays.
 *
 * Example:
 *     Input:
 *         5
 *         1 2 3 4 5
 *     Output:
 *         [[1], [1, 2], [1, 2, 3], [1, 2, 3, 4], [1, 2, 3, 4, 5]]
 *         [[2], [2, 3], [2, 3, 4], [2, 3, 4, 5], [2, 3, 4, 5, 1]]
 *         [[3], [3, 4], [3, 4, 5], [3, 4, 5, 1], [3, 4, 5, 1, 2]]
 *         ...
 *     (Printed for all circular subarrays)
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j33CircularSubarrays {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the different methods to compute the circular subarrays
        printCircularSubarrays(arr); // Print circular subarrays
        System.out.println(getCircularSubarrays(arr)); // Get all circular subarrays
        System.out.println(getCircularSubarraysEfficient(arr)); // Efficient circular subarrays

        in.close();
    }

    /**
     * Approach: Naive Approach (O(n^3))
     * 
     * Intuition:
     *     - We iterate through each starting index of the array.
     *     - For each start index, we generate subarrays of different lengths by iterating over the array and applying modulo operation
     *       to handle circular wrap-arounds.
     *     - Each subarray is printed as a list of integers.
     * 
     * Time Complexity: O(n^3), due to the nested loops for generating subarrays.
     * 
     * Space Complexity: O(n), for storing the subarrays.
     *
     * @param arr The input array.
     */
    public static void printCircularSubarrays(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int len = 1; len <= arr.length; len++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                // Generate subarray using modulo to handle circular indexing
                for (int j = 0; j < len; j++) {
                    list.add(arr[(i + j) % arr.length]);
                }
                System.out.println(list); // Print the subarray
            }
        }
    }

    /**
     * Approach: Naive Approach (O(n^3)) - Return circular subarrays as a list of lists
     * 
     * Intuition:
     *     - Similar to the `printCircularSubarrays` method, but instead of printing, we add each subarray to the output list.
     *     - This method returns a list of all circular subarrays of the array.
     * 
     * Time Complexity: O(n^3), as we generate and store all subarrays in nested loops.
     * 
     * Space Complexity: O(n^3), as we store all subarrays.
     *
     * @param arr The input array.
     * @return List of lists containing all circular subarrays.
     */
    public static ArrayList<ArrayList<Integer>> getCircularSubarrays(int[] arr) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            for (int len = 1; len <= arr.length; len++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                // Generate subarray using modulo to handle circular indexing
                for (int j = 0; j < len; j++) {
                    list.add(arr[(i + j) % arr.length]);
                }
                out.add(list); // Add the subarray to the output list
            }
        }
        return out; // Return the list of all circular subarrays
    }

    /**
     * Approach: Efficient Approach (O(n^2)) - Return circular subarrays as a list of lists
     * 
     * Intuition:
     *     - This method improves on the previous two by avoiding unnecessary iterations.
     *     - We generate each subarray and store it incrementally, extending it as we go.
     *     - The subarray is built up one element at a time, and when the next element is added, we append it to the subarray.
     * 
     * Time Complexity: O(n^2), as we generate each subarray and add one element at a time.
     * 
     * Space Complexity: O(n^2), as we store all the subarrays.
     *
     * @param arr The input array.
     * @return List of lists containing all circular subarrays.
     */
    public static ArrayList<ArrayList<Integer>> getCircularSubarraysEfficient(int[] arr) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < arr.length; j++) {
                // Build the subarray by adding elements one by one and wrapping around
                // circularly
                list.add(arr[(i + j) % arr.length]);
                out.add(new ArrayList<>(list)); // Add the current subarray to the output list
            }
        }
        return out; // Return the list of all circular subarrays
    }
}
