/*-
 * Problem Statement:
 * 
 *     Given two non-empty arrays of digits representing two numbers in base -2, 
 *     return their sum as a new array of digits in base -2. 
 *     The digits are stored in reverse order, and each of their digits is either 0 or 1.
 *     Add the two numbers and return the sum as an array in the same format.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^4), representing the size of the first array `num1`.
 *     - An integer `n2` (1 <= n2 <= 10^4), representing the size of the second array `num2`.
 *     - Two arrays `num1` of size `n1` and `num2` of size `n2` where each element is either 0 or 1.
 * 
 * Output:
 *     - An array representing the sum of the two input arrays in base -2, stored in reverse order.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 0 1
 *     1 1 1
 *     Output:
 *     [1, 0, 0, 1]
 * 
 *     Explanation:
 *     - The binary number 101 in base -2 is equal to 1*(-2)^0 + 0*(-2)^1 + 1*(-2)^2 = 1 + 4 = 5.
 *     - The binary number 111 in base -2 is equal to 1*(-2)^0 + 1*(-2)^1 + 1*(-2)^2 = 1 - 2 + 4 = 3.
 *     - The sum of 5 and 3 is 8, which is 1000 in base -2, represented as [1, 0, 0, 1] in reverse order.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j08AddNegBinary {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: size of first array
        int n2 = in.nextInt(); // Input: size of second array
        int[] num1 = new int[n1];
        int[] num2 = new int[n2];
        for (int i = 0; i < n1; i++) {
            num1[i] = in.nextInt(); // Input: elements of first array
        }
        for (int i = 0; i < n2; i++) {
            num2[i] = in.nextInt(); // Input: elements of second array
        }

        // Call your solution and output result
        System.out.println(Arrays.toString(addNegabinary(num1, num2)));

        in.close();
    }

    /*-
     * Approach: Adding two numbers in base -2.
     * 
     * Intuition:
     * - The approach uses the concept of adding two binary numbers with a base of -2 instead of 2.
     * - This requires keeping track of carries, which can either be 0 or -1 in this base.
     * - The result is constructed from the least significant bit to the most significant bit.
     * 
     * Time Complexity:
     * - O(max(n1, n2)), where n1 and n2 are the lengths of the two input arrays. The loop iterates through each digit once.
     * 
     * Space Complexity:
     * - O(max(n1, n2)), as we are storing the result in an ArrayList before converting it to an array.
     * 
     * @param arr1 The first input array of digits.
     * @param arr2 The second input array of digits.
     * @return The resulting array of digits representing the sum in base -2.
     */
    public static int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1; // Pointer to the last element of arr1
        int j = arr2.length - 1; // Pointer to the last element of arr2
        ArrayList<Integer> output = new ArrayList<Integer>();
        int carry = 0; // The carry value can either be 0 or -1
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) {
                sum += arr1[i];
                i--;
            }
            if (j >= 0) {
                sum += arr2[j];
                j--;
            }

            output.add(0, sum & 1); // Add the least significant bit of sum to the result
            carry = -(sum >> 1); // Carry is either 0 or -1 based on the bitwise shift of sum
        }

        // Remove leading zeros
        while (output.size() > 1 && output.get(0) == 0) {
            output.remove(0);
        }

        // Convert ArrayList to array and return
        int[] array = new int[output.size()];
        for (int m = 0; m < output.size(); m++) {
            array[m] = output.get(m);
        }
        return array;
    }
}
