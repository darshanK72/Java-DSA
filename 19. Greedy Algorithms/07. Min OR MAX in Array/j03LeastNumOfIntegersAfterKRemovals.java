/**
 * LeetCode 1481. Least Number of Unique Integers after K Removals
 * 
 * Problem Statement:
 *     Given an array of integers arr and an integer k. Find the least number of
 *     unique integers after removing exactly k elements. The order of the remaining
 *     elements does not matter.
 * 
 * Input:
 *     - arr (int[]): Array of integers
 *     - k (int): Number of elements to remove
 *     - 1 <= arr.length <= 10^5
 *     - 0 <= arr[i] <= 10^9
 *     - 0 <= k <= arr.length
 * 
 * Output:
 *     - Minimum number of unique integers after removing exactly k elements
 * 
 * Example:
 *     Input: arr = [5,5,4], k = 1
 *     Output: 1
 * 
 *     Explanation:
 *     Remove the single 4, and we are left with [5,5] which has 1 unique integer.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class j03LeastNumOfIntegersAfterKRemovals {
    /**
     * Approach: Greedy with Frequency Counting
     * 
     * Intuition:
     * - To minimize the number of unique integers, we should remove elements
     *   that appear least frequently first
     * - We can count frequencies of each number and sort them
     * - Then remove k elements starting from the least frequent numbers
     * - This ensures we eliminate as many unique numbers as possible
     * 
     * Explanation:
     * 1. Sort array to group same numbers together
     * 2. Count frequency of each number
     * 3. Sort frequencies in ascending order
     * 4. Remove k elements starting from least frequent numbers:
     *    - If k >= frequency of current number, remove all occurrences
     *    - If k < frequency, stop as we can't remove more
     * 5. Return remaining number of unique integers
     * 
     * Time Complexity: O(n log n) where n is the length of arr
     *                  Due to sorting operations
     * Space Complexity: O(n) to store frequencies
     * 
     * @param arr Array of integers
     * @param k   Number of elements to remove
     * @return   Minimum number of unique integers after k removals
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // Sort array to group same numbers together
        Arrays.sort(arr);
        
        // Count frequency of each number
        ArrayList<Integer> freq = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) 
                count++;
            else {
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        // Sort frequencies to process least frequent numbers first
        Collections.sort(freq);
        
        // Remove k elements starting from least frequent numbers
        int i = 0;
        for (; i < freq.size(); i++) {
            if (k < freq.get(i)) 
                break;
            k -= freq.get(i);
        }

        // Return remaining number of unique integers
        return freq.size() - i;
    }

    public static void main(String[] args) {
        j03LeastNumOfIntegersAfterKRemovals solution = new j03LeastNumOfIntegersAfterKRemovals();

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {5, 5, 4};
        int k1 = 1;
        System.out.println("Input: arr=" + Arrays.toString(arr1) + ", k=" + k1);
        System.out.println("Expected: 1, Output: " + solution.findLeastNumOfUniqueInts(arr1, k1));

        // Test Case 2: Multiple frequencies
        System.out.println("\nMultiple Frequencies Test Case:");
        int[] arr2 = {4, 3, 1, 1, 3, 3, 2};
        int k2 = 3;
        System.out.println("Input: arr=" + Arrays.toString(arr2) + ", k=" + k2);
        System.out.println("Expected: 2, Output: " + solution.findLeastNumOfUniqueInts(arr2, k2));

        // Test Case 3: Remove all elements
        System.out.println("\nRemove All Test Case:");
        int[] arr3 = {1, 2, 3};
        int k3 = 3;
        System.out.println("Input: arr=" + Arrays.toString(arr3) + ", k=" + k3);
        System.out.println("Expected: 0, Output: " + solution.findLeastNumOfUniqueInts(arr3, k3));

        // Test Case 4: No removals
        System.out.println("\nNo Removals Test Case:");
        int[] arr4 = {1, 1, 2, 2, 3, 3};
        int k4 = 0;
        System.out.println("Input: arr=" + Arrays.toString(arr4) + ", k=" + k4);
        System.out.println("Expected: 3, Output: " + solution.findLeastNumOfUniqueInts(arr4, k4));
    }
}
