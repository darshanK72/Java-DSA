/**
 * Problem Statement:
 * 
 *     Given an array of integers, calculate the total Hamming distance between all pairs of integers in the array.
 *     The Hamming distance between two integers is the number of bit positions at which the corresponding bits are different.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^9).
 * 
 * Output:
 *     - An integer representing the total Hamming distance between all pairs of integers in the array.
 * 
 * Example:
 *     Input:
 *     4
 *     4 14 2 8
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The binary representation of the numbers are:
 *     4  -> 100
 *     14 -> 1110
 *     2  -> 0010
 *     8  -> 1000
 *     
 *     The total Hamming distance is calculated by checking the number of differing bits for all pairs of numbers.
 *     Hamming distance between (4, 14) is 3, between (4, 2) is 2, between (4, 8) is 1, and so on.
 *     The sum of all Hamming distances is 6.
 */

import java.util.Scanner;

public class j07TotalHammingDistance {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] nums = new int[n]; // Array to hold numbers
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: each number in the array
        }

        // Call your solution
        System.out.println(totalHammingDistance(nums)); // Output the total Hamming distance

        in.close();
    }

    /**
     * Approach:
     * 
     * Intuition:
     * - We calculate the Hamming distance for all pairs by checking each bit position from 0 to 31 (for 32-bit integers).
     * - For each bit position, count how many numbers have a 1 at that bit position and how many have a 0.
     * - The total number of differing bits for that bit position will be the product of countOnes and countZeros.
     * 
     * Time Complexity:
     * - O(n * 32), where n is the number of elements in the array. We are iterating through all the numbers and checking 32 bits for each.
     * 
     * Space Complexity:
     * - O(1), as we are using only a few variables to store intermediate results.
     * 
     * @param nums The input array of integers.
     * @return The total Hamming distance between all pairs of integers.
     */
    public static int totalHammingDistance(int[] nums) {
        int ans = 0; // Variable to store the result
        for (int i = 0; i < 32; i++) { // Check each bit position
            int countOnes = 0; // Count of 1's at current bit position
            int countZeros = 0; // Count of 0's at current bit position
            for (int n : nums) { // Iterate through each number in the array
                if (((n >> i) & 1) == 1) {
                    countOnes++; // Increment countOnes if the current bit is 1
                } else {
                    countZeros++; // Increment countZeros if the current bit is 0
                }
            }
            ans += countOnes * countZeros; // Add the Hamming distance for the current bit position
        }
        return ans; // Return the total Hamming distance
    }
}
