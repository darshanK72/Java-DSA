/**
 * Problem Statement:
 * 
 *     Given an array of integers, check if it is possible to reorder the array such that 
 *     for every element in the array, there is another element that is double its value.
 *     The elements must be paired in such a way that for every number `x`, there is a number `2 * x`.
 *     Numbers can be negative as well, and the negative values should still follow the same rule of pairing with 
 *     half of their value (for example, -2 pairs with -1).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A boolean indicating whether it is possible to reorder the array to form the pairs.
 * 
 * Example:
 *     Input:
 *     6
 *     4 2 2 4 8 8
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - The array can be reordered as [2, 4, 2, 4, 8, 8], where every number is paired with its double.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class j05ArrayOfDoubledPairs {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        // Calling both solution methods and printing the result
        System.out.println(canReorderDoubled1(arr));
        System.out.println(canReorderDoubled2(arr));
        in.close();
    }

    /**
     * Approach 1: Using HashMap and sorting the array
     * 
     * Intuition:
     * - The strategy here is to sort the array first. This ensures that when processing each element, 
     *   we encounter smaller elements first, which helps in pairing them with their corresponding doubles.
     * - We use a HashMap to count the occurrences of each number. For each element, we check if the target (either 
     *   double or half of the current number) is available to form the pair. If we encounter any number that cannot 
     *   be paired, we return `false`.
     * 
     * Time Complexity:
     * - O(n log n) due to sorting the array.
     * - O(n) for iterating through the array and updating the map.
     * Overall: O(n log n)
     * 
     * Space Complexity:
     * - O(n) for the HashMap used to store the counts of elements.
     * 
     * @param arr The input array of integers.
     * @return true if the array can be reordered to form the required pairs, false otherwise.
     */
    public static boolean canReorderDoubled1(int[] arr) {
        Arrays.sort(arr); // Sort the array to handle smaller elements first
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store the count of each number
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        // Iterate through the sorted array
        for (int num : arr) {
            if (map.get(num) == 0) // If the number is already paired, continue
                continue;
            int target = num < 0 ? num / 2 : num * 2; // For negative numbers, check for half
            // If it's impossible to pair the number with its target, return false
            if (num < 0 && num % 2 != 0 || map.getOrDefault(target, 0) == 0) {
                return false;
            }
            // Update the map after pairing the current number and its double
            map.put(num, map.get(num) - 1);
            map.put(target, map.get(target) - 1);
        }
        return true;
    }

    /**
     * Approach 2: Using TreeMap and counting elements
     * 
     * Intuition:
     * - In this approach, we use a TreeMap to keep the elements in sorted order automatically. 
     * - The idea is to iterate through the map keys (which are sorted in ascending order), and for each key, 
     *   we try to pair it with its double. If there are more occurrences of the number than its double, 
     *   it's not possible to form pairs, so we return `false`.
     * 
     * Time Complexity:
     * - O(n log n) because TreeMap maintains order during insertion and traversal.
     * 
     * Space Complexity:
     * - O(n) for the TreeMap that stores the counts of each element.
     * 
     * @param arr The input array of integers.
     * @return true if the array can be reordered to form the required pairs, false otherwise.
     */
    public static boolean canReorderDoubled2(int[] arr) {
        Map<Integer, Integer> map = new TreeMap<>(); // TreeMap keeps the keys sorted automatically
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        // Iterate through the TreeMap
        for (int num : map.keySet()) {
            if (map.get(num) == 0) // If the number is already paired, continue
                continue;
            int target = num < 0 ? num / 2 : num * 2; // Handle negative numbers by dividing by 2
            // If a number can't be paired with its double, return false
            if (num < 0 && num % 2 != 0) {
                return false;
            }
            if (map.get(num) > map.getOrDefault(target, 0)) {
                return false;
            }
            // Update the TreeMap after pairing
            map.put(target, map.get(target) - map.get(num));
        }
        return true;
    }
}
