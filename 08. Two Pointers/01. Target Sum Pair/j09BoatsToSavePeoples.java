/**
 * Problem Statement:
 * 
 *     In a situation where a boat can only carry a certain weight limit and each person has a weight, 
 *     you need to determine the minimum number of boats required to save all people. 
 *     You can pair two people in a boat if their combined weight does not exceed the boat's weight limit.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the number of people.
 *     - An array `people` of size `n` where each element represents the weight of a person (1 <= people[i] <= 10^3).
 *     - An integer `limit` (1 <= limit <= 10^3), the maximum weight the boat can carry.
 * 
 * Output:
 *     - An integer representing the minimum number of boats required to rescue all people.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     5
 *     Output:
 *     3
 *     
 *     Explanation:
 *     The optimal way to pair people is as follows:
 *     Boat 1: (1, 4) → weight = 5
 *     Boat 2: (2, 3) → weight = 5
 *     Boat 3: (5) → weight = 5
 */

import java.util.Arrays;
import java.util.Scanner;

public class j09BoatsToSavePeoples {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of people
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: the weights of the people
        }
        int limit = in.nextInt(); // Input: the boat's weight limit

        // Call the naive solution
        System.out.printf("Naive Solution: %d\n", countRescueBoats(arr, limit));

        // Call the optimized solution
        System.out.printf("Optimized Solution: %d\n", countRescueBoatsEfficient(arr, limit));

        in.close();
    }

    /**
     * Approach 1: Naive Approach
     * 
     * Intuition:
     * - In this approach, we first sort the array of people's weights. 
     * - We then attempt to pair the lightest person with the heaviest person that has not been assigned a boat.
     * - For each person, we try to find another person such that their combined weight does not exceed the boat's limit.
     * - If no pair is found, the current person is assigned a separate boat.
     * 
     * Time Complexity:
     * - O(n^2), where n is the number of people, due to the nested loop for checking possible pairings.
     * 
     * Space Complexity:
     * - O(n), as we use an array to track which people have been assigned boats.
     * 
     * @param people The input array of people's weights.
     * @param limit The boat's weight limit.
     * @return The minimum number of boats required.
     */
    public static int countRescueBoats(int[] people, int limit) {
        boolean[] used = new boolean[people.length]; // Tracks people who have been assigned a boat
        int boats = 0; // Count of boats used
        Arrays.sort(people); // Sort the array of weights

        for (int i = 0; i < people.length; i++) {
            if (!used[i]) { // If the person hasn't been assigned a boat
                boats++; // Assign a boat
                used[i] = true; // Mark this person as assigned
                int maxFit = -1;

                // Try to pair the current person with someone else
                for (int j = 0; j < people.length; j++) {
                    if (!used[j] && people[i] + people[j] <= limit) {
                        maxFit = j; // Found a pair
                    }
                }

                // Mark the paired person as assigned
                if (maxFit != -1) {
                    used[maxFit] = true;
                }
            }
        }
        return boats;
    }

    /**
     * Approach 2: Optimized Approach (Two-pointer technique)
     * 
     * Intuition:
     * - In this optimized approach, we first sort the array of people's weights.
     * - We use two pointers: one starting at the lightest person and one at the heaviest.
     * - If the sum of their weights is within the boat's limit, we pair them and move both pointers.
     * - If the sum exceeds the limit, the heaviest person is assigned a boat on their own.
     * - This approach efficiently minimizes the number of boats required by always attempting to pair people optimally.
     * 
     * Time Complexity:
     * - O(n log n), where n is the number of people, due to the sorting step. The two-pointer iteration takes O(n).
     * 
     * Space Complexity:
     * - O(1), as no extra space is used apart from a few variables.
     * 
     * @param people The input array of people's weights.
     * @param limit The boat's weight limit.
     * @return The minimum number of boats required.
     */
    public static int countRescueBoatsEfficient(int[] people, int limit) {
        Arrays.sort(people); // Sort the array of weights
        int s = 0; // Pointer to the lightest person
        int e = people.length - 1; // Pointer to the heaviest person
        int count = 0; // Count of boats used

        // Use two-pointer technique
        while (s <= e) {
            int sum = people[s] + people[e];
            if (sum <= limit) {
                s++; // Pair the lightest and heaviest
            }
            e--; // Always move the heaviest person pointer
            count++; // A boat is used for either pairing or solo
        }
        return count;
    }
}
