/**
 * Problem Statement:
 *     Given an integer array, find the number of elements that appear more than n/k times, where k is a given parameter.
 *     The task is to find all elements that occur more than n/k times in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 *     - An integer `k` (2 <= k <= 10^5), where you need to find elements that appear more than n/k times.
 * 
 * Output:
 *     - The number of elements in the array that appear more than n/k times.
 * 
 * Example:
 *     Input:
 *     8
 *     3 1 2 2 3 3 2 4
 *     3
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The majority elements are 2 and 3 because they both appear more than n/k = 8/3 = 2 times. 
 *     Therefore, the output is 2.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class j03MajorityElement3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }
        int k = in.nextInt(); // Input: the value of k

        // Call your solution methods
        System.out.println(majorityElement3BruitForce(arr, k)); // Brute force solution
        System.out.println(majorityElement3UsingHashing(arr, k)); // Hashing solution
        System.out.println(majorityElement3Efficient(arr, k)); // Efficient sorting solution
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - For each element, count how many times it occurs. If it occurs more than n/k times, it is considered a majority element.
     * 
     * Time Complexity:
     * - O(n^2), as for each element we need to check all other elements to count occurrences.
     * 
     * Space Complexity:
     * - O(n), as we use a boolean array to track visited elements and store potential majority elements.
     * 
     * @param arr The input array of numbers.
     * @param k The integer value representing the division factor n/k.
     * @return The count of elements that appear more than n/k times.
     */
    public static int majorityElement3BruitForce(int[] arr, int k) {
        boolean[] visited = new boolean[arr.length]; // Track visited elements to avoid recounting
        int count = 0; // To store the number of majority elements

        // Traverse through each element of the array
        for (int i = 0; i < arr.length; i++) {
            int tempCount = 0; // To count occurrences of arr[i]

            // Compare arr[i] with all other elements
            for (int j = 0; j < arr.length; j++) {
                if (!visited[j] && arr[j] == arr[i]) {
                    tempCount++; // Increment count for matching element
                    visited[j] = true; // Mark element as visited
                }
            }

            // If count > n/k, it is a majority element
            if (tempCount > arr.length / k) {
                count++; // Increment the count of majority elements
            }
        }
        return count; // Return the total count of majority elements
    }

    /**
     * Approach 2: Hashing Approach
     * 
     * Intuition:
     * - Use a hash map to count the occurrences of each element.
     * - Then, check which elements have a count greater than n/k.
     * 
     * Time Complexity:
     * - O(n), as we traverse the array once to build the hash map, and then traverse the map to check counts.
     * 
     * Space Complexity:
     * - O(n), due to the space required for the hash map to store the element frequencies.
     * 
     * @param arr The input array of numbers.
     * @param k The integer value representing the division factor n/k.
     * @return A list of majority elements that appear more than n/k times.
     */
    public static ArrayList<Integer> majorityElement3UsingHashing(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // Hash map to store element frequencies

        // Traverse the array to populate the hash map with frequency counts
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1); // Increment frequency for each element
        }

        ArrayList<Integer> output = new ArrayList<Integer>(); // List to store majority elements

        // Traverse the hash map and find elements with frequency > n/k
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / k) {
                output.add(entry.getKey()); // Add majority elements to the output list
            }
        }
        return output; // Return the list of majority elements
    }

    /**
     * Approach 3: Efficient Sorting Approach
     * 
     * Intuition:
     * - Sort the array first. Once the array is sorted, elements that appear more than n/k times will be adjacent.
     * - Count the occurrences of each element, and check if it exceeds n/k.
     * 
     * Time Complexity:
     * - O(n * log n), as sorting the array takes O(n * log n) time.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used except for a few variables to store counts.
     * 
     * @param arr The input array of numbers.
     * @param k The integer value representing the division factor n/k.
     * @return The count of elements that appear more than n/k times.
     */
    public static int majorityElement3Efficient(int[] arr, int k) {
        Arrays.sort(arr); // Sort the array

        int count = 0; // To store the number of majority elements
        int i = 0; // Pointer to traverse the array

        // Traverse the sorted array
        while (i < arr.length) {
            int c = 0; // Count occurrences of arr[i]
            int ele = arr[i]; // Current element
            while (i < arr.length && arr[i] == ele) { // Count occurrences of the same element
                c++;
                i++;
            }

            // If count > n/k, it is a majority element
            if (c > (arr.length / k)) {
                count++; // Increment the count of majority elements
            }
        }
        return count; // Return the total count of majority elements
    }
}
