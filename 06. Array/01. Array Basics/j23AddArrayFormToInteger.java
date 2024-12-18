/**
 * Problem Statement:
 * 
 *     Given an array `num` representing a non-negative integer (each element is a digit) 
 *     and an integer `k`, add `k` to the integer represented by `num` and return 
 *     the result in the form of an ArrayList.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An array `num` of size `n` where each element is a digit (0 <= num[i] <= 9).
 *     - An integer `k` (0 <= k <= 10^9), the number to add.
 * 
 * Output:
 *     - An ArrayList of integers representing the resulting number after addition.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 4
 *     456
 * 
 *     Output:
 *     [1, 6, 9, 0]
 * 
 *     Explanation:
 *     - The number represented by the array is `1234`.
 *     - Adding 456 gives `1690`, returned as `[1, 6, 9, 0]`.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j23AddArrayFormToInteger {

    public static void main(String args[]) {
        // Input Scanner
        Scanner in = new Scanner(System.in);

        // Input array size
        int n = in.nextInt();

        // Input array representing a number
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Input integer to add
        int k = in.nextInt();

        // Function call and output
        System.out.println("Resulting Array: " + addToArrayForm(arr, k));

        in.close();
    }

    /**
     * Function to add an integer `k` to the number represented by the array `num`.
     * 
     * Approach:
     * - Process the digits of `num` starting from the least significant digit (rightmost).
     * - Add the corresponding digits of `k` along with a carry value.
     * - Continue until all digits of the array and `k` are processed, and no carry remains.
     * - Use an `ArrayList` to hold the resulting digits in reverse order.
     * - Insert the result at the beginning of the `ArrayList` for correct positioning.
     * 
     * Time Complexity: O(n + log(k))
     *     - `O(n)` for processing the array `num` where `n` is the size of `num`.
     *     - `O(log(k))` for processing the integer `k` as we divide `k` by 10.
     * 
     * Space Complexity: O(n + log(k))
     *     - For the result stored in the `ArrayList`.
     */
    public static ArrayList<Integer> addToArrayForm(int[] num, int k) {
        int i = num.length - 1; // Pointer to traverse the array from the rightmost digit
        int carry = 0; // Carry to store values > 10
        ArrayList<Integer> out = new ArrayList<Integer>(); // Resulting ArrayList

        // Process the array and integer k
        while (i >= 0 || carry > 0 || k > 0) {
            int d = carry; // Start with any carry from the previous addition

            if (i >= 0) { // Add the current digit from the array if available
                d += num[i];
            }
            if (k > 0) { // Add the current digit from integer `k`
                d += k % 10;
            }

            out.add(0, (d % 10)); // Add the least significant digit to the front of the list
            carry = d / 10; // Compute carry for the next step

            k /= 10; // Remove the last digit of k
            i--; // Move to the next digit in the array
        }

        return out; // Return the resulting ArrayList
    }
}
