/**
 * Problem Statement:
 *     Given an integer array `arr[]`, find the first element that does not repeat in the array.
 *     If all elements repeat, return 0.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr[]` of integers of size `n`, where each element satisfies (-10^9 <= arr[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the first non-repeated element, or `0` if no such element exists.
 * 
 * Example:
 *     Input:
 *     6
 *     4 5 1 2 1 2
 *     Output:
 *     4
 * 
 *     Input:
 *     4
 *     3 3 3 3
 *     Output:
 *     0
 * 
 *     Explanation:
 *     In the first example, `4` is the first non-repeated element.
 *     In the second example, all elements repeat, so the output is `0`.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j08FirstNonRepeated {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        System.out.println(firstNonRepeating(arr)); // Output the result
        in.close();
    }

    /**
     * Approach: Using a HashMap to count the frequency of each element
     * 
     * Intuition:
     * - We use a HashMap to store the frequency of each element in the array.
     * - After populating the HashMap, we iterate through the array again to find the first element that appears only once.
     * - If we find such an element, we return it. If no such element exists, we return `0`.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We traverse the array twice: once to populate the map and once to find the non-repeated element.
     * 
     * Space Complexity:
     * - O(n), as we store the frequency of each element in the HashMap.
     * 
     * @param arr The input array of integers.
     * @return The first non-repeated element, or `0` if no such element exists.
     */
    public static int firstNonRepeating(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(); // Create a HashMap to store element frequencies

        // Populate the map with frequencies of each element
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // Find and return the first element with frequency 1 (non-repeated element)
        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == 1) {
                return arr[i]; // Return the first non-repeating element
            }
        }

        return 0; // If no non-repeating element exists, return 0
    }
}
