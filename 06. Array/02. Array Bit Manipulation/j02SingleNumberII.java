/**
 * Problem Statement:
 * 
 *     Given an array of integers, every element appears three times except for one element that appears exactly once. 
 *     Find the element that appears only once.
 * 
 * Input:
 *     - An integer `s` representing the size of the array.
 *     - An array `arr` of size `s` where each element appears three times except for one element which appears only once.
 * 
 * Output:
 *     - Return the element that appears only once in the array.
 * 
 * Example:
 *     Input:
 *     9
 *     2 2 3 2 3 3 4 4 4
 *     
 *     Output:
 *     5
 * 
 *     Explanation:
 *     In the given array, all numbers except 5 appear three times. The number 5 is the only number that appears once.
 */

import java.util.Scanner;

public class j02SingleNumberII {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int s = in.nextInt(); // Input: size of the array
        int[] arr = new int[s]; // Array to hold the elements
        for (int i = 0; i < s; i++) {
            arr[i] = in.nextInt(); // Input: the elements of the array
        }

        // Call the solution methods
        System.out.println(singleNumber(arr)); // Output: single number using brute force method
        System.out.println(singleNumberEfficient(arr)); // Output: single number using efficient method
        in.close();
    }

    /**
     * Approach 1: Brute Force (O(n * 32))
     * 
     * Intuition:
     * - Iterate through each bit position (0 to 31) and count the number of set bits at each position across all elements.
     * - If the number of set bits at any position is not divisible by 3, that bit belongs to the number that appears only once.
     * - The result is constructed by setting the corresponding bits at each position where the count is not divisible by 3.
     * 
     * Time Complexity:
     * - O(n * 32), where `n` is the size of the array, as we check each bit position for every element.
     * 
     * Space Complexity:
     * - O(1), as we are using a constant amount of extra space.
     * 
     * @param nums The input array of integers.
     * @return The element that appears only once.
     */
    public static int singleNumber(int[] nums) {
        int ans = 0; // Result variable
        // Iterate over each bit position (0 to 31)
        for (int i = 0; i < 32; i++) {
            int count = 0;
            // Count the number of elements with the i-th bit set
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & (1 << i)) != 0)
                    count++;
            }
            // If count is not divisible by 3, the i-th bit belongs to the single number
            if (count % 3 == 1) {
                ans = ans | (1 << i);
            }
        }
        return ans;
    }

    /**
     * Approach 2: Efficient Method using Bit Manipulation (O(n))
     * 
     * Intuition:
     * - Instead of checking each bit position individually, we use three variables to track bits that appear 1, 2, and 3 times respectively.
     * - For each element, we update these variables to reflect the bitwise operations.
     * - Finally, `tnp1` holds the number that appears exactly once in the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We iterate over the array once and perform constant-time operations for each element.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space (three variables).
     * 
     * @param nums The input array of integers.
     * @return The element that appears only once.
     */
    public static int singleNumberEfficient(int[] nums) {
        int tn = Integer.MAX_VALUE; // Stores bits that appear 3 times
        int tnp1 = 0; // Stores bits that appear 1 time
        int tnp2 = 0; // Stores bits that appear 2 times

        // Iterate over all elements in the array
        for (int num : nums) {
            // Calculate the bits that are common between num and the current states of tn,
            // tnp1, tnp2
            int ctn = num & tn;
            int ctn1 = num & tnp1;
            int ctn2 = num & tnp2;

            // Update the states based on bitwise operations
            tn = tn & ~(ctn); // Remove bits that appeared 3 times
            tnp1 = tnp1 | ctn; // Set bits that appear 1 time

            tnp1 = tnp1 & ~(ctn1); // Remove bits that appear 2 times
            tnp2 = tnp2 | ctn1; // Set bits that appear 2 times

            tnp2 = tnp2 & ~(ctn2); // Remove bits that appear 3 times
            tn = tn | ctn2; // Set bits that appear 3 times
        }

        // Return the bits that appear only 1 time
        return tnp1;
    }
}
