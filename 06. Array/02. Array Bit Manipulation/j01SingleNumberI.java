/**
 * Problem Statement:
 * 
 *     Given an array of integers, where every element appears twice except for one element that appears only once.
 *     Find the element that appears only once.
 * 
 * Input:
 *     - An integer `s` representing the size of the array.
 *     - An array `arr` of size `s` where every element appears twice except for one element which appears only once.
 * 
 * Output:
 *     - Return the element that appears only once in the array.
 * 
 * Example:
 *     Input:
 *     5
 *     4 1 2 1 2
 *     
 *     Output:
 *     4
 * 
 *     Explanation:
 *     In the given array, all elements appear twice except for 4, which appears only once.
 */

import java.util.Scanner;

public class j01SingleNumberI {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int s = in.nextInt(); // Input: size of the array
        int[] arr = new int[s]; // Array to hold the elements
        for (int i = 0; i < s; i++) {
            arr[i] = in.nextInt(); // Input: the elements of the array
        }

        // Call the solution method
        System.out.println(singleNumber(arr)); // Output: single number using XOR method
        in.close();
    }

    /**
     * Approach: XOR Method (O(n))
     * 
     * Intuition:
     * - The XOR operation has a special property: a number XORed with itself is 0, and a number XORed with 0 is the number itself.
     * - As every number except one appears twice, XORing all elements together will cancel out the numbers that appear twice (since `a ^ a = 0`).
     * - The result will be the number that appears only once.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, as we need to iterate over all elements once.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of extra space.
     * 
     * @param nums The input array of integers.
     * @return The element that appears only once.
     */
    public static int singleNumber(int[] nums) {
        int result = 0; // Initialize result to 0
        // XOR all elements in the array
        for (int num : nums) {
            result ^= num; // XOR each number with the result
        }
        return result; // Return the element that appeared only once
    }
}
