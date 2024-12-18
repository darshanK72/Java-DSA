/**
 * Problem Statement:
 * 
 *     Given a non-negative integer represented as an array `digits` where each element 
 *     represents a single digit of the number, add 1 to the number. The most significant 
 *     digit is at the beginning of the array. Return the resulting array after the addition.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of digits in the array.
 *     - An array `digits` of size `n` where each element satisfies (0 <= digits[i] <= 9).
 * 
 * Output:
 *     - An array representing the number after adding 1.
 * 
 * Example:
 *     Input:
 *     4
 *     1 2 3 9
 * 
 *     Output:
 *     [1, 2, 4, 0]
 * 
 *     Explanation:
 *     - The number represented by the array is `1239`.
 *     - Adding 1 results in `1240`, which is returned as an array `[1, 2, 4, 0]`.
 * 
 * Example:
 *     Input:
 *     3
 *     9 9 9
 * 
 *     Output:
 *     [1, 0, 0, 0]
 * 
 *     Explanation:
 *     - The number represented by the array is `999`.
 *     - Adding 1 results in `1000`, which is returned as an array `[1, 0, 0, 0]`.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j22PlusOne {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of digits
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: digits
        }

        // Output the result
        System.out.println("Resulting Array After Adding One: " + Arrays.toString(plusOne(arr)));

        in.close();
    }

    /**
     * Approach: Simulate Adding 1 Digit-by-Digit
     * 
     * Intuition:
     * - Starting from the least significant digit (last element), simulate adding 1.
     * - If the current digit becomes 10 after addition, set it to 0 and move to the next more
     *   significant digit. Continue this process.
     * - If all digits are processed and still carry remains (like `999` becoming `1000`), 
     *   create a new array of size `n+1` with the first element set to 1.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of digits in the input array. We iterate through all the digits once.
     * 
     * Space Complexity:
     * - O(n), in the worst case, we need an additional array of size `n+1` when all digits are `9`.
     * 
     * @param digits The input array of digits.
     * @return The resulting array after adding 1.
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) { // If the current digit is 9, it becomes 0
                digits[i] = 0;
            } else {
                digits[i]++; // Increment the current digit and return as carry is resolved
                return digits;
            }
        }

        // If all digits are 9, we need an extra digit at the start (e.g., 999 -> 1000)
        int[] out = new int[digits.length + 1];
        out[0] = 1; // Set the most significant digit to 1
        return out;
    }
}
