/**
 * Problem Statement:
 * 
 *     Given an array `arr`, find and print all possible subarrays of the array.
 *     A subarray is a contiguous portion of the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - Print all subarrays of the array.
 *     - Return all subarrays as a list of lists.
 * 
 * Example:
 *     Input:
 *         3
 *         1 2 3
 *     Output:
 *         1 
 *         1 2 
 *         1 2 3 
 *         2 
 *         2 3 
 *         3
 * 
 *     Explanation:
 *         The subarrays of the array `[1, 2, 3]` are:
 *         - [1]
 *         - [1, 2]
 *         - [1, 2, 3]
 *         - [2]
 *         - [2, 3]
 *         - [3]
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j29GetSubarrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call and print results for different methods
        printSubarraysNive(arr);
        printAllSubarrays(arr);
        System.out.println();
        System.out.println(getAllSubarrays(arr));

        in.close();
    }

    /**
     * Approach 1: Print Subarrays (Naive Approach)
     * 
     * Intuition:
     *     - We iterate over all possible starting points of the subarray.
     *     - For each starting point, we iterate through all possible ending points and print the subarray elements.
     *     - This results in a cubic time complexity because we perform 3 nested loops: one for the start index, one for the end index, and one for printing the elements of the subarray.
     * 
     * Time Complexity:
     *     - O(n^3), where `n` is the size of the array. The nested loops result in cubic complexity.
     * 
     * Space Complexity:
     *     - O(1), as we do not use any additional data structures to store subarrays.
     * 
     * @param arr The input array.
     */
    public static void printSubarraysNive(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Approach 2: Print All Subarrays (Optimized Approach)
     * 
     * Intuition:
     *     - For each starting index, we build the subarray by appending elements one by one.
     *     - This approach uses fewer operations compared to the naive approach by directly printing the subarray incrementally.
     * 
     * Time Complexity:
     *     - O(n^2), where `n` is the size of the array. We loop through the array twice (once for the start and once for the end).
     * 
     * Space Complexity:
     *     - O(1), as we do not use any extra space except for the string variable that stores the current subarray.
     * 
     * @param arr The input array.
     */
    public static void printAllSubarrays(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String subarr = "";
            for (int j = i; j < arr.length; j++) {
                subarr += arr[j] + " ";
                System.out.print(subarr + " ");
            }
        }
    }

    /**
     * Approach 3: Get All Subarrays (Using ArrayLists)
     * 
     * Intuition:
     *     - This approach collects all subarrays into a list of lists.
     *     - We use two loops: the outer loop for the start index and the inner loop for the end index.
     *     - For each subarray, we create a new ArrayList and add it to the output list.
     *     - This approach is more efficient in terms of data storage, as it returns the subarrays in the form of a list.
     * 
     * Time Complexity:
     *     - O(n^2), where `n` is the size of the array. We loop twice to generate all subarrays.
     * 
     * Space Complexity:
     *     - O(n^2), as we store all subarrays in the output list, where the total number of subarrays is O(n^2).
     * 
     * @param arr The input array.
     * @return A list containing all subarrays.
     */
    public static ArrayList<ArrayList<Integer>> getAllSubarrays(int[] arr) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = i; j < arr.length; j++) {
                lst.add(arr[j]);
                out.add(new ArrayList<>(lst)); // Store the subarray in the output list
            }
        }
        return out;
    }
}
