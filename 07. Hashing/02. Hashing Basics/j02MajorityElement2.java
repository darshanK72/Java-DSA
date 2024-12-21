/**
 * Problem Statement:
 *     Given an integer array, find all elements that appear more than n/3 times. These elements are called "majority elements."
 *     There can be at most two majority elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A list of all majority elements that appear more than n/3 times.
 * 
 * Example:
 *     Input:
 *     6
 *     3 3 4 2 4 4
 *     Output:
 *     [4]
 * 
 *     Explanation:
 *     The majority element is 4 because it appears more than n/3 = 6/3 = 2 times. 
 *     Therefore, the output is [4].
 */

import java.util.*;

public class j02MajorityElement2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Call your solution methods
        System.out.println(majorityElement2BruitForce(arr));
        System.out.println(majorityElement2UsingHashing(arr));
        System.out.println(majorityElement2Efficient(arr));
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - For each element, count how many times it occurs. If it occurs more than n/3 times, it is a majority element.
     * 
     * Time Complexity:
     * - O(n^2), as for each element, we need to check all other elements for counting occurrences.
     * 
     * Space Complexity:
     * - O(n), as we use a boolean array to track visited elements and store potential majority elements.
     * 
     * @param arr The input array of numbers.
     * @return A list of majority elements that appear more than n/3 times.
     */
    public static ArrayList<Integer> majorityElement2BruitForce(int[] arr) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        boolean[] visited = new boolean[arr.length]; // Track visited elements to avoid recounting

        for (int i = 0; i < arr.length; i++) {
            if (visited[i])
                continue; // Skip already visited elements

            int tempCount = 0; // Count occurrences of arr[i]
            for (int j = 0; j < arr.length; j++) {
                if (!visited[j] && arr[j] == arr[i]) {
                    tempCount++; // Increment count for matching element
                    visited[j] = true; // Mark element as visited
                }
            }
            if (tempCount > arr.length / 3) { // If count > n/3, it is a majority element
                output.add(arr[i]); // Add to output list
            }
        }
        return output; // Return the list of majority elements
    }

    /**
     * Approach 2: Hashing Approach
     * 
     * Intuition:
     * - Use a hash map to count the occurrences of each element.
     * - Then, check which elements have a count greater than n/3.
     * 
     * Time Complexity:
     * - O(n), as we traverse the array once to build the hash map, and then traverse the map to check counts.
     * 
     * Space Complexity:
     * - O(n), due to the space required for the hash map to store the element frequencies.
     * 
     * @param arr The input array of numbers.
     * @return A list of majority elements that appear more than n/3 times.
     */
    public static ArrayList<Integer> majorityElement2UsingHashing(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // Hash map to store element frequencies

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1); // Increment frequency count for each element
        }

        ArrayList<Integer> output = new ArrayList<Integer>();

        // Check which elements have a count greater than n/3
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > arr.length / 3) {
                output.add(entry.getKey()); // Add to output list if majority element
            }
        }
        return output; // Return the list of majority elements
    }

    /**
     * Approach 3: Boyer-Moore Voting Algorithm (Efficient)
     * 
     * Intuition:
     * - Use a modified version of the Boyer-Moore voting algorithm to track at most two majority candidates.
     * - After identifying candidates, we verify if they appear more than n/3 times.
     * 
     * Time Complexity:
     * - O(n), as we traverse the array twice: once to find candidates and once to verify them.
     * 
     * Space Complexity:
     * - O(1), since we only need a few variables to track the two candidates and their counts.
     * 
     * @param arr The input array of numbers.
     * @return A list of majority elements that appear more than n/3 times.
     */
    public static ArrayList<Integer> majorityElement2Efficient(int[] arr) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        int majorityA = Integer.MIN_VALUE, leadA = 0;
        int majorityB = Integer.MIN_VALUE, leadB = 0;

        // First pass: Find the two potential majority candidates
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == majorityA) {
                leadA++; // Increment lead for candidate A
            } else if (arr[i] == majorityB) {
                leadB++; // Increment lead for candidate B
            } else if (leadA == 0) {
                majorityA = arr[i]; // Set new candidate A
                leadA = 1; // Reset lead count for candidate A
            } else if (leadB == 0) {
                majorityB = arr[i]; // Set new candidate B
                leadB = 1; // Reset lead count for candidate B
            } else {
                leadA--; // Decrement lead for candidate A
                leadB--; // Decrement lead for candidate B
            }
        }

        // Second pass: Verify the candidates and check if they are majority elements
        int ca = 0, cb = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == majorityA)
                ca++; // Count occurrences of candidate A
            if (arr[i] == majorityB)
                cb++; // Count occurrences of candidate B
        }

        // Add the candidates to the output list if they occur more than n/3 times
        if (ca > arr.length / 3)
            output.add(majorityA);
        if (cb > arr.length / 3)
            output.add(majorityB);

        return output; // Return the list of majority elements
    }
}
