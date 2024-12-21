/**
 * Problem Statement:
 *     Given two arrays `arr1[]` and `arr2[]`, find the intersection of the two arrays. Each element in the result should appear as many times as it shows in both arrays.
 *     The result can be in any order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the first array.
 *     - An integer `m` (1 <= m <= 10^5), representing the size of the second array.
 *     - Two integer arrays `arr1[]` of size `n` and `arr2[]` of size `m`.
 * 
 * Output:
 *     - An integer array representing the intersection of `arr1[]` and `arr2[]`.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 2 1
 *     3
 *     2 2
 *     Output:
 *     [2, 2]
 * 
 *     Input:
 *     6
 *     4 9 5 8 2 4
 *     5
 *     9 4 4 8 3
 *     Output:
 *     [9, 4, 4, 8]
 * 
 *     Explanation:
 *     In the first example, the intersection of `arr1[]` and `arr2[]` is `[2, 2]`.
 *     In the second example, the intersection is `[9, 4, 4, 8]`.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j10IntersectionOfArraysII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input size of first array
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt(); // Input elements of first array
        }
        int m = in.nextInt(); // Input size of second array
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = in.nextInt(); // Input elements of second array
        }

        int[] common = intersection(arr1, arr2); // Call method to find intersection
        System.out.println(Arrays.toString(common)); // Print the intersection
        in.close();
    }

    /**
     * Approach: Using HashMap to find the intersection with frequency handling
     * 
     * Intuition:
     * - We store the elements of the first array `arr1[]` in a HashMap to track their frequency.
     * - We then iterate through the second array `arr2[]`. If an element from `arr2[]` is found in the HashMap with a positive frequency,
     *   we add it to the result and decrement its frequency in the HashMap.
     * - This ensures that each element from the intersection is added as many times as it appears in both arrays.
     * 
     * Time Complexity:
     * - O(n + m), where `n` is the size of the first array and `m` is the size of the second array. 
     *   The time complexity is linear in the total size of the two arrays because we process each element at most once.
     * 
     * Space Complexity:
     * - O(n), where `n` is the size of the first array, as we store the frequency of elements in the HashMap.
     * 
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @return An array containing the intersection of `arr1[]` and `arr2[]`.
     */
    public static int[] intersection(int[] arr1, int[] arr2) {
        ArrayList<Integer> common = new ArrayList<Integer>(); // List to store the intersection elements
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // HashMap to store elements of arr1 with their
                                                                         // frequencies

        // Fill the HashMap with elements of arr1 and their frequencies
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }

        // Iterate through arr2, and check for intersection with elements in the HashMap
        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i])) { // If element in arr2 is found in map
                if (map.get(arr2[i]) > 0) { // Check if frequency is greater than 0
                    common.add(arr2[i]); // Add to the result
                }
                map.put(arr2[i], map.get(arr2[i]) - 1); // Decrement the frequency of the element in the map
            }
        }

        // Convert the ArrayList to an array
        int[] out = new int[common.size()];
        for (int i = 0; i < common.size(); i++) {
            out[i] = common.get(i); // Fill the output array with intersection elements
        }

        return out; // Return the intersection array
    }
}
