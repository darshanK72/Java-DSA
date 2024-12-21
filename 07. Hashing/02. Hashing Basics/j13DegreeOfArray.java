/**
 * Problem Statement:
 * 
 *     Given a non-empty array of integers, the degree of an array is defined as 
 *     the maximum frequency of any one of its elements. The task is to find the 
 *     length of the shortest subarray that contains all the elements with the 
 *     same degree as the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the length of the shortest subarray that contains 
 *       all the elements with the same degree as the array.
 * 
 * Example:
 *     Input:
 *     7
 *     1 2 2 3 1 4 2
 *     
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The degree of the array is 3, as the element `2` appears three times. 
 *     The shortest subarray that contains all occurrences of `2` is [2, 2, 3, 1, 4, 2], 
 *     and its length is 6.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j13DegreeOfArray {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n]; // Array to hold the input elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call both methods to get the results
        System.out.println(findShortestSubArray(arr));
        System.out.println(findShortestSubArrayEfficient(arr));
        in.close(); // Close the scanner
    }

    /**
     * Approach 1: Brute-Force Approach Using HashMap and ArrayList
     * 
     * Intuition:
     * - First, we iterate through the array to store the frequency of each element 
     *   in a HashMap.
     * - We then check for each element, find its first and last occurrence using 
     *   ArrayList, and compute the length of the subarray that contains all occurrences 
     *   of the element with the maximum frequency.
     * - The shortest such subarray is then returned.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the array. Finding the first and last occurrence 
     *   of an element can take O(n) for each element, leading to O(n^2) in total.
     * 
     * Space Complexity:
     * - O(n), since we are storing the array and the HashMap for frequency counts.
     * 
     * @param nums The input array of numbers.
     * @return The length of the shortest subarray with the same degree.
     */
    public static int findShortestSubArray(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>(); // ArrayList to track all elements
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store frequency counts
        for (int i = 0; i < nums.length; i++) {
            arr.add(nums[i]); // Add elements to ArrayList
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1); // Update frequency count in HashMap
        }

        int max = 0;
        int ans = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            // If the frequency of current element is greater than max, update max and ans
            if (map.get(key) > max) {
                max = map.get(key);
                ans = arr.lastIndexOf(key) - arr.indexOf(key) + 1;
            }
            // If frequency is equal to max, choose the smaller subarray length
            else if (map.get(key) == max) {
                ans = Math.min(ans, arr.lastIndexOf(key) - arr.indexOf(key) + 1);
            }
        }
        return ans;
    }

    /**
     * Approach 2: Optimized Approach Using HashMap and Array to Track Indices
     * 
     * Intuition:
     * - We maintain a HashMap where each element stores its frequency as well as its 
     *   first and last occurrence indices.
     * - This allows us to efficiently calculate the length of the subarray containing 
     *   all occurrences of an element, and the overall degree of the array.
     * - By directly storing indices, we avoid repeated computation of first and last 
     *   indices, making this approach more efficient.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the array. We traverse the array once and compute 
     *   the result in linear time.
     * 
     * Space Complexity:
     * - O(n), since we store information (frequency and indices) for each unique element.
     * 
     * @param nums The input array of numbers.
     * @return The length of the shortest subarray with the same degree.
     */
    public static int findShortestSubArrayEfficient(int[] nums) {
        HashMap<Integer, Integer[]> map = new HashMap<>(); // HashMap to store frequency and indices
        for (int i = 0; i < nums.length; i++) {
            Integer[] v = map.getOrDefault(nums[i], new Integer[] { 0, -1, -1 }); // Get or initialize array for
                                                                                  // [frequency, firstIndex, lastIndex]
            if (v[1] == -1)
                v[1] = i; // First occurrence
            v[2] = i; // Last occurrence
            v[0]++; // Increment frequency
            map.put(nums[i], v); // Update HashMap
        }

        int max = 0;
        int ans = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            Integer[] v = map.get(key);
            // If frequency is higher, update max and ans
            if (v[0] > max) {
                max = v[0];
                ans = v[2] - v[1] + 1; // Calculate subarray length
            }
            // If frequency is the same, choose the smaller subarray length
            else if (v[0] == max) {
                ans = Math.min(ans, v[2] - v[1] + 1);
            }
        }
        return ans;
    }
}
