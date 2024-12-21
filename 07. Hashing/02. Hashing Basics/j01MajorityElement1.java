/**
 * Problem Statement:
 *     Given an integer array, find the majority element. The majority element is an element that appears more than n/2 times.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The majority element, if it exists; otherwise, return -1.
 * 
 * Example:
 *     Input:
 *     6
 *     3 3 4 2 4 4
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The majority element is 4 because it appears more than 6/2 = 3 times.
 */

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class j01MajorityElement1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Call your solution methods
        System.out.println(majorityElement1BruitForce(arr));
        System.out.println(majorityElement1UsingHashing(arr));
        System.out.println(majorityElement1Efficient(arr));
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - For each element in the array, count how many times it occurs. 
     * - If it occurs more than n/2 times, it is the majority element.
     * 
     * Time Complexity:
     * - O(n²) because we iterate over each element and compare it with every other element to count occurrences.
     * 
     * Space Complexity:
     * - O(n) due to the `visited[]` boolean array to keep track of counted elements.
     * 
     * @param arr The input array of numbers.
     * @return The majority element if found, otherwise -1.
     */
    public static int majorityElement1BruitForce(int[] arr) {
        boolean[] visited = new boolean[arr.length]; // To track visited elements
        int majorityElement = -1; // Initialize majority element as -1

        for (int i = 0; i < arr.length; i++) {
            int tempCount = 0; // Count occurrences of arr[i]

            for (int j = 0; j < arr.length; j++) {
                if (!visited[j] && arr[j] == arr[i]) {
                    tempCount++; // Increment count
                    visited[j] = true; // Mark element as visited
                }
            }

            if (tempCount > arr.length / 2) { // If count > n/2, it's the majority element
                majorityElement = arr[i];
                break; // Exit loop once majority element is found
            }
        }
        return majorityElement;
    }

    /**
     * Approach 2: Hashing Approach
     * 
     * Intuition:
     * - Use a hash map to count the occurrences of each element.
     * - Traverse the map and return the element that appears more than n/2 times.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array, since we traverse the array once to build the hash map.
     * 
     * Space Complexity:
     * - O(n), because we store all elements and their frequencies in the hash map.
     * 
     * @param arr The input array of numbers.
     * @return The majority element if found, otherwise -1.
     */
    public static int majorityElement1UsingHashing(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // Hash map to store element frequencies

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0); // Initialize frequency count
            }
            map.put(arr[i], map.get(arr[i]) + 1); // Increment frequency count
        }

        int out = -1; // Initialize result

        // Check for the majority element
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / 2) {
                out = entry.getKey();
                break; // Majority element found, exit loop
            }
        }
        return out;
    }

    /**
     * Approach 3: Boyer-Moore Voting Algorithm (Efficient)
     * 
     * Intuition:
     * - The algorithm maintains a candidate and a lead count. The candidate changes if the lead reaches zero.
     * - After finding the candidate, we verify if it appears more than n/2 times.
     * 
     * Time Complexity:
     * - O(n), because we make two passes over the array: one for finding the candidate and one for verification.
     * 
     * Space Complexity:
     * - O(1), since we only use a few variables to track the candidate and lead count.
     * 
     * @param arr The input array of numbers.
     * @return The majority element if found, otherwise -1.
     */
    public static int majorityElement1Efficient(int[] arr) {
        int majority = 0; // Candidate element
        int lead = 0; // Lead count for candidate

        // First pass: Find the candidate
        for (int i = 0; i < arr.length; i++) {
            if (lead == 0) {
                majority = arr[i]; // Set new candidate
                lead = 1; // Initialize lead count
            } else if (arr[i] == majority) {
                lead++; // Increment lead if the element matches candidate
            } else {
                lead--; // Decrement lead if the element does not match
            }
        }

        // Second pass: Verify if the candidate is truly the majority element
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == majority) {
                count++; // Count occurrences of the candidate
            }
        }

        if (count > arr.length / 2) { // Majority element condition
            return majority;
        }
        return -1; // No majority element
    }
}
