/**
 * Problem Statement:
 * 
 *     Given an array of integers representing fruits in baskets, where each fruit is represented by an integer, 
 *     find the maximum number of fruits you can collect in two baskets. You can only pick fruits from two types of 
 *     fruits at most. Return the maximum number of fruits you can collect in this way.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - Return an integer representing the maximum number of fruits you can collect in two baskets.
 * 
 * Example:
 *     Input:
 *     7
 *     1 2 1 2 1 1 2
 *     Output:
 *     5
 * 
 *     Explanation:
 *     We can pick fruits from baskets of type 1 and 2, and we can collect 5 fruits in total (from indices 0 to 4).
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j09CutFruitsInBaskets {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution methods
        System.out.println(totalFruit(nums)); // Brute force solution
        System.out.println(totalFruitEfficient(nums)); // Optimized solution

        in.close();
    }

    /**
     * Approach: Brute Force
     * 
     * Intuition:
     * - In this approach, we attempt to check every possible subarray starting at index `i` and ending at index `j`.
     * - For each subarray, we count the distinct types of fruits, and if there are more than 2 distinct types, we stop.
     * - We continue this process for every possible subarray and keep track of the maximum length that meets the criteria.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the number of fruits. For each starting index `i`, we explore all subarrays up to `j`, 
     *   making the approach inefficient for larger inputs.
     * 
     * Space Complexity:
     * - O(n), due to the usage of a HashSet to store distinct fruit types in the subarray.
     * 
     * @param fruits The input array of fruit types.
     * @return The maximum number of fruits that can be collected in two baskets.
     */
    public static int totalFruit(int[] fruits) {
        int maxL = 0; // Initialize the maximum length
        for (int i = 0; i < fruits.length; i++) {
            HashSet<Integer> set = new HashSet<>(); // Set to store distinct fruit types
            for (int j = i; j < fruits.length; j++) {
                set.add(fruits[j]); // Add fruit type to the set
                if (set.size() <= 2) { // If we have more than 2 types, break out of the loop
                    maxL = Math.max(maxL, j - i + 1); // Update the maximum length
                } else {
                    break; // If there are more than 2 distinct types, break
                }
            }
        }
        return maxL; // Return the result
    }

    /**
     * Approach: Optimized Sliding Window
     * 
     * Intuition:
     * - This approach utilizes a sliding window technique with two pointers `i` and `j` and a HashMap to store the 
     *   count of each fruit type within the window.
     * - The window expands as we add fruits and shrinks from the left when the number of distinct types exceeds two.
     * - This ensures that we are always working with a valid window containing at most two types of fruits.
     * - The sliding window ensures that we visit each element at most twice (once when expanding the window and once 
     *   when shrinking it).
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of fruits, because both the left and right pointers only move forward and each 
     *   element is processed once.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of distinct fruit types in the window (at most 2).
     * 
     * @param fruits The input array of fruit types.
     * @return The maximum number of fruits that can be collected in two baskets.
     */
    public static int totalFruitEfficient(int[] fruits) {
        int maxL = 0; // Initialize the maximum length
        int j = 0; // Left pointer of the sliding window
        HashMap<Integer, Integer> map = new HashMap<>(); // Map to store the count of fruit types in the window

        for (int i = 0; i < fruits.length; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1); // Add fruit to the map and update the count
            while (map.size() > 2) { // If there are more than two distinct fruit types, shrink the window
                map.put(fruits[j], map.get(fruits[j]) - 1);
                if (map.get(fruits[j]) == 0) {
                    map.remove(fruits[j]); // Remove the fruit type if its count becomes zero
                }
                j++; // Shrink the window by moving the left pointer
            }
            maxL = Math.max(maxL, i - j + 1); // Update the maximum length
        }
        return maxL; // Return the result
    }
}
