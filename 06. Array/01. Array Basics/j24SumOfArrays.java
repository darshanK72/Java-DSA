/**
 * Problem Statement:
 * 
 *     Given two non-negative integers represented as arrays `arr1` and `arr2`, where each digit of the number
 *     is stored as an element in the arrays, compute the sum of these two numbers. The output should be 
 *     represented as an array where each element is a digit of the result.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^4), representing the size of the first array.
 *     - An array `arr1` of size `n1` where each element satisfies (0 <= arr1[i] <= 9).
 *     - An integer `n2` (1 <= n2 <= 10^4), representing the size of the second array.
 *     - An array `arr2` of size `n2` where each element satisfies (0 <= arr2[i] <= 9).
 * 
 * Output:
 *     - An array representing the sum of the two input arrays as a single number, where each digit 
 *       is stored in a separate index.
 * 
 * Example:
 *     Input:
 *         3
 *         2 4 3
 *         3
 *         5 6 4
 *     Output:
 *         [8, 0, 7]
 * 
 *     Explanation:
 *         The input arrays represent the numbers 243 and 564. Their sum is 807, which is represented
 *         as [8, 0, 7].
 */

import java.util.Arrays;
import java.util.Scanner;

public class j24SumOfArrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: size of the first array
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt(); // Input: elements of the first array
        }

        int n2 = in.nextInt(); // Input: size of the second array
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt(); // Input: elements of the second array
        }

        // Call the solution method and print the result
        System.out.println("Sum of Arrays: " + Arrays.toString(sumOfArrays(arr1, arr2)));

        in.close();
    }

    /**
     * Approach: Digit-wise Summation with Carry
     * 
     * Intuition:
     *     - We add the two arrays starting from the least significant digit (end of the arrays).
     *     - We calculate the sum of corresponding digits along with any carry from the previous step.
     *     - If one array is shorter, we assume the missing digits are zero.
     *     - Finally, we handle any remaining carry and construct the result array.
     * 
     * Explanation:
     *     - Iterate through both arrays from the last element to the first element.
     *     - Calculate the sum of digits from both arrays, including any carry.
     *     - Store the least significant digit of the sum in the result array and carry forward the rest.
     *     - Reverse the result array to get the final output.
     * 
     * Time Complexity:
     *     - O(max(n1, n2)), where n1 and n2 are the lengths of the input arrays.
     * 
     * Space Complexity:
     *     - O(max(n1, n2)), for storing the result array.
     * 
     * @param arr1 The first input array of digits.
     * @param arr2 The second input array of digits.
     * @return An array representing the sum of the two input arrays.
     */
    public static int[] sumOfArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int m = Math.max(n1, n2); // Size of the output array
        int[] output = new int[m];

        int carry = 0; // Initialize carry
        for (int i = 0; i < m; i++) {
            int sum = 0;
            // Add corresponding digits from both arrays if they exist
            if (n1 - 1 - i >= 0)
                sum += arr1[n1 - 1 - i];
            if (n2 - 1 - i >= 0)
                sum += arr2[n2 - 1 - i];
            if (carry > 0)
                sum += carry;

            // Store the current digit in the result array
            output[m - 1 - i] = sum % 10;
            carry = sum / 10; // Update carry
        }

        // If there is still a carry left, expand the result array
        if (carry > 0) {
            int[] expandedOutput = new int[m + 1];
            System.arraycopy(output, 0, expandedOutput, 1, m);
            expandedOutput[0] = carry;
            return expandedOutput;
        }

        return output;
    }
}
