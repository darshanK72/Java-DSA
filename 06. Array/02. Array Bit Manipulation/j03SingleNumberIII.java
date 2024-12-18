/**
 * Problem Statement:
 * 
 *     Given an array of integers where every element appears twice except for two elements that appear only once.
 *     Find the two elements that appear only once in the array.
 * 
 * Input:
 *     - An integer `s` representing the size of the array.
 *     - An array `arr` of size `s` where every element appears twice except for two elements which appear only once.
 * 
 * Output:
 *     - Return an array containing the two elements that appear only once.
 * 
 * Example:
 *     Input:
 *     6
 *     1 2 1 3 2 5
 *     
 *     Output:
 *     3 5
 * 
 *     Explanation:
 *     In the given array, all elements appear twice except for 3 and 5, which appear only once.
 */

import java.util.Scanner;

public class j03SingleNumberIII {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int s = in.nextInt(); // Input: size of the array
        int[] arr = new int[s]; // Array to hold the elements
        for (int i = 0; i < s; i++) {
            arr[i] = in.nextInt(); // Input: the elements of the array
        }

        // Call the solution method
        int[] result = singleNumber(arr); // Output: two single numbers
        System.out.println(result[0] + " " + result[1]); // Output: the two single numbers
        in.close();
    }

    /**
     * Approach: XOR and Partition (O(n))
     * 
     * Intuition:
     * - XOR of all the elements results in a number that is the XOR of the two unique numbers. This happens because pairs cancel out (since `a ^ a = 0`).
     * - The result of XORing all elements (`r`) will have the two unique numbers encoded in the bits that are different between them.
     * - To isolate these two numbers, we find the rightmost bit where `r` is 1 (using `r & ~(r - 1)`), which ensures that this bit is different between the two unique numbers.
     * - We can then partition the array into two groups based on this bit (one group with the bit set and the other with the bit unset) and XOR each group separately to find the two unique numbers.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, because we need to iterate through the array twice.
     * 
     * Space Complexity:
     * - O(1), as the extra space used does not depend on the size of the input.
     * 
     * @param nums The input array of integers.
     * @return An array containing the two unique numbers.
     */
    public static int[] singleNumber(int[] nums) {
        int r = 0; // XOR all numbers together
        // XOR all elements in the array to get the result
        for (int num : nums) {
            r ^= num;
        }

        // Find the rightmost bit where r is 1 (this bit is different between the two
        // unique numbers)
        int rb = r & ~(r - 1);

        // Initialize two variables to store the two unique numbers
        int r1 = 0, r2 = 0;

        // Partition the numbers into two groups and XOR them separately
        for (int num : nums) {
            if ((num & rb) == 0) { // Numbers with the bit unset
                r1 ^= num;
            } else { // Numbers with the bit set
                r2 ^= num;
            }
        }

        // Return the two unique numbers
        return new int[] { r1, r2 };
    }
}
