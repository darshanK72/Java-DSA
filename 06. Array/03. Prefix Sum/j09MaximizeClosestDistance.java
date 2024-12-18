/**
 * Problem Statement:
 * 
 *     Given an array of integers representing seats on a bus, where each seat is either occupied (1) or unoccupied (0),
 *     find the maximum distance between two people sitting on the bus. You need to calculate this distance in such a way that
 *     the seat is empty (0), and the distance is the closest to the person sitting at either end of the unoccupied seat.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of seats on the bus.
 *     - An array `seats` of size `n`, where each element is either 0 (empty) or 1 (occupied).
 * 
 * Output:
 *     - An integer representing the maximum distance between a person and the closest seat.
 * 
 * Example:
 *     Input:
 *     5
 *     [1, 0, 0, 0, 1]
 *     Output:
 *     2
 * 
 *     Explanation:
 *     In this case, the maximum distance between a person and the closest seat is 2.
 *     The unoccupied seat in the middle has the longest distance from either end (index 2).
 */

import java.util.Scanner;

public class j09MaximizeClosestDistance {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        // Call your solution
        System.out.println(maxDistToClosest(arr)); // Call first approach
        System.out.println(maxDistToClosestEfficient(arr)); // Call efficient solution
        System.out.println(maxDistToClosestTwoPointers(arr)); // Call two-pointer solution
        in.close();
    }

    /**
     * Approach 1: Using Left and Right Distance Arrays
     * 
     * Intuition:
     * - We can calculate the maximum distance to the nearest occupied seat by first calculating the distance to the closest
     *   occupied seat from the left and right for each unoccupied seat. Then, the closest occupied seat for any position
     *   is the minimum of the two distances (left and right).
     * 
     * Time Complexity:
     * - O(n), where n is the number of seats. We go through the array twice, once for the left distances and once for the
     *   right distances.
     * 
     * Space Complexity:
     * - O(n), because we use two additional arrays (`leftDist` and `rightDist`) to store the distances.
     * 
     * @param seats The input array of seats.
     * @return The maximum distance between a person and the closest occupied seat.
     */
    public static int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] leftDist = new int[n];
        int[] rightDist = new int[n];

        int d = n;
        // Calculate the left distance for each seat
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                d = 0; // Reset the distance when we encounter an occupied seat
            } else {
                d++; // Increase distance for empty seats
            }
            leftDist[i] = d; // Store the left distance for each seat
        }

        d = n;
        // Calculate the right distance for each seat
        for (int i = n - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                d = 0; // Reset the distance when we encounter an occupied seat
            } else {
                d++; // Increase distance for empty seats
            }
            rightDist[i] = d; // Store the right distance for each seat
        }

        int maxDist = 0;
        // Find the maximum distance to the closest seat
        for (int i = 0; i < n; i++) {
            maxDist = Math.max(maxDist, Math.min(leftDist[i], rightDist[i])); // Take the minimum of left and right
                                                                              // distances
        }
        return maxDist;
    }

    /**
     * Approach 2: Efficient Solution (Single Pass)
     * 
     * Intuition:
     * - Instead of using two arrays for left and right distances, we can solve the problem with a single pass.
     *   We first find the first occupied seat, and then calculate the distance to the closest seat by considering gaps between
     *   consecutive occupied seats.
     * - This reduces the space complexity.
     * 
     * Time Complexity:
     * - O(n), where n is the number of seats. We make a single pass through the array to find the first and last occupied seats,
     *   and then another pass to calculate the maximum distance.
     * 
     * Space Complexity:
     * - O(1), because we use only a constant amount of extra space.
     * 
     * @param seats The input array of seats.
     * @return The maximum distance between a person and the closest occupied seat.
     */
    public static int maxDistToClosestEfficient(int[] seats) {
        int n = seats.length;
        int first = -1;
        int maxDist = 0;

        // Find the first occupied seat
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                first = i;
                break;
            }
        }

        // Handle the case when the first seat is unoccupied
        if (first != -1) {
            maxDist = first;
        }

        int last = first;
        // Calculate the distances between consecutive occupied seats
        for (int i = first + 1; i < seats.length; i++) {
            if (seats[i] == 1) {
                maxDist = Math.max(maxDist, (i - last) / 2); // Find the maximum distance between two occupied seats
                last = i; // Update the last occupied seat
            }
        }

        // Handle the case when the last seat is unoccupied
        if (last != n - 1) {
            maxDist = Math.max(maxDist, n - 1 - last); // Calculate the distance from the last occupied seat to the end
        }
        return maxDist;
    }

    /**
     * Approach 3: Using Two Pointers
     * 
     * Intuition:
     * - We use two pointers to track the closest occupied seats from both directions. As we traverse the array, we maintain
     *   two pointers, one for the left and one for the right, to track the closest occupied seats and compute the distance
     *   to the closest seat at each step.
     * 
     * Time Complexity:
     * - O(n), where n is the number of seats. We traverse the array once using two pointers.
     * 
     * Space Complexity:
     * - O(1), because we only use a constant amount of extra space.
     * 
     * @param seats The input array of seats.
     * @return The maximum distance between a person and the closest occupied seat.
     */
    public static int maxDistToClosestTwoPointers(int[] seats) {
        int i = -1;
        int j = -1;
        int maxDist = 0;
        // Traverse through the seats array
        for (int k = 0; k < seats.length; k++) {
            if (seats[k] == 1) {
                if (i != -1) {
                    maxDist = Math.max(maxDist, k - i); // Calculate the distance between consecutive occupied seats
                }
                if (j == -1) {
                    j = k; // Initialize the first occupied seat
                }
                i = k; // Update the last occupied seat
            }
        }

        // Return the maximum distance between the closest seat
        return Math.max(maxDist / 2, Math.max(j, seats.length - i - 1));
    }
}
