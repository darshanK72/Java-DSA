/**
 * Problem Statement:
 * 
 *     Given an array of integers, find all the leaders in the array. A leader is an element that is greater than or equal to 
 *     all the elements to its right. The rightmost element is always considered a leader.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A list of integers representing the leaders in the array. The leaders should be in the order they appear in the array, 
 *       starting from the leftmost one.
 * 
 * Example:
 *     Input:
 *     6
 *     16 17 4 3 5 2
 *     Output:
 *     [17, 5, 2]
 * 
 *     Explanation:
 *     The leaders are 17, 5, and 2 because:
 *     - 17 is greater than or equal to all elements to its right.
 *     - 5 is greater than or equal to all elements to its right.
 *     - 2 is the rightmost element, so it is always a leader.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j17LeaderInArray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution method and print the result
        System.out.println(leadersInArray(arr));

        in.close();
    }

    /**
     * Approach: Find leaders by iterating from right to left
     * 
     * Intuition:
     * - The problem is asking us to find elements in the array that are greater than or equal to all elements to their right. 
     * - We can do this by iterating the array from right to left, keeping track of the maximum value encountered so far.
     * - If the current element is greater than or equal to this maximum value, it is a leader.
     * - This ensures that we can efficiently check if the element is a leader and add it to the result.
     * - Starting from the rightmost element ensures that we don't miss any potential leaders.
     * 
     * Time Complexity:
     * - O(n), where n is the number of elements in the array. We are iterating through the array once.
     * 
     * Space Complexity:
     * - O(n), as we are storing the leaders in an ArrayList.
     * 
     * @param arr The input array of integers.
     * @return An ArrayList containing the leaders of the array.
     */
    public static ArrayList<Integer> leadersInArray(int[] arr) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        int leader = Integer.MIN_VALUE; // Initialize the leader with the minimum possible value
        for (int i = arr.length - 1; i >= 0; i--) { // Iterate from right to left
            if (arr[i] >= leader) { // Check if the current element is greater than or equal to the current leader
                out.add(0, arr[i]); // Add the leader to the front of the list
                leader = arr[i]; // Update the leader
            }
        }
        return out; // Return the list of leaders
    }
}
