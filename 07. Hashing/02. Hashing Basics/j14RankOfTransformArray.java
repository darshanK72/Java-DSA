/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to transform the array such that 
 *     each element is replaced by its rank in the sorted array (1-based indexing). 
 *     The rank of an element is its position in the sorted version of the array, 
 *     with duplicate elements receiving the same rank.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An array of integers where each element is replaced by its rank in the sorted array.
 * 
 * Example:
 *     Input:
 *     5
 *     40 10 20 30 10
 *     
 *     Output:
 *     4 1 2 3 1
 * 
 *     Explanation:
 *     The sorted version of the array is [10, 10, 20, 30, 40], and the rank 
 *     for each element in the original array is [4, 1, 2, 3, 1].
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class j14RankOfTransformArray {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n]; // Array to hold the input elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the array rank transformation method
        System.out.println(arrayRankTransform(arr));
        in.close(); // Close the scanner
    }

    /**
     * Approach 1: Using HashMap and Sorting
     * 
     * Intuition:
     * - First, create a HashMap to store each element of the array and initialize 
     *   its value as 0 (the rank placeholder).
     * - Then, convert the keys (unique elements) to a list, sort the list, and assign 
     *   each element in the sorted list its rank based on its position in the sorted array.
     * - Finally, iterate through the original array, replacing each element with its 
     *   rank using the HashMap.
     * 
     * Time Complexity:
     * - O(n log n), where n is the length of the array. The most time-consuming 
     *   operation is sorting the array of unique elements.
     * 
     * Space Complexity:
     * - O(n), since we store the array elements and their corresponding ranks in a HashMap.
     * 
     * @param arr The input array of numbers.
     * @return The transformed array with ranks.
     */
    public static int[] arrayRankTransform(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store elements and their ranks
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], 0); // Initialize the rank of each element to 0
        }

        // Sort the unique elements to assign ranks
        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys); // Sort elements in ascending order

        // Assign rank to each element based on its position in the sorted list
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), i + 1); // Rank starts from 1
        }

        // Replace each element in the original array with its rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]); // Get the rank from the map and update the array
        }
        return arr; // Return the transformed array
    }
}
