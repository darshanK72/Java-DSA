/**
 * Problem Statement:
 * 
 *     Given an array `arr` containing `n` distinct numbers in the range [0, n], find the missing number.
 *     The array contains all the numbers in this range except for one number.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An integer array `arr` of size `n` containing distinct integers in the range [0, n].
 * 
 * Output:
 *     - A single integer, the missing number in the range [0, n].
 * 
 * Example:
 *     Input:
 *         n = 3, arr = [3, 0, 1]
 *     Output:
 *         2
 * 
 *     Explanation:
 *         - The range [0, 3] is {0, 1, 2, 3}.
 *         - The number 2 is missing from the array.
 */

import java.util.Scanner;

public class j01MissingNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Calling approaches
        System.out.println(missingNumber(arr)); // Using Summation Formula
        System.out.println(missingNumberArrayHashing(arr)); // Using Array Hashing
        System.out.println(missingNumberIndexBasedHashing(arr)); // Using Index-Based Hashing
        System.out.println(missingNumberEfficient(arr)); // Using XOR

        in.close();
    }

    /**
     * Approach: Summation Formula
     * 
     * Intuition:
     * - The sum of the first `n` natural numbers can be computed using the formula `n * (n + 1) / 2`.
     * - The total sum of numbers in the array should equal this formula if no numbers are missing.
     * - The missing number is the difference between the expected sum and the sum of elements in the array.
     * 
     * Explanation:
     * - The arithmetic sum formula guarantees the expected total when all numbers are present. Subtracting the actual sum from it gives the missing value.
     * 
     * Time Complexity:
     * - O(n), as the array is traversed once to calculate its sum.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param arr The input array.
     * @return The missing number.
     */
    public static int missingNumber(int[] arr) {
        int sum = (arr.length * (arr.length + 1)) / 2;
        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
        }
        return sum;
    }

    /**
     * Approach: Array Hashing
     * 
     * Intuition:
     * - Create a temporary array of size `n + 1` to track the presence of numbers from the original array.
     * - Each index of the temporary array corresponds to a number in the range [0, n].
     * - By marking the presence of numbers in the original array, the index that remains unmarked indicates the missing number.
     * 
     * Explanation:
     * - By mapping numbers to indices, we utilize the range constraints [0, n] to track missing elements systematically.
     * 
     * Time Complexity:
     * - O(n), as the original array is traversed to populate the temporary array.
     * 
     * Space Complexity:
     * - O(n), due to the creation of an additional array of size `n + 1`.
     * 
     * @param arr The input array.
     * @return The missing number.
     */
    public static int missingNumberArrayHashing(int[] arr) {
        int[] temp = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]]++;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0)
                return i;
        }
        return -1;
    }

    /**
     * Approach: Index-Based Hashing
     * 
     * Intuition:
     * - Instead of creating an additional array, use the original array to track the presence of elements by marking indices.
     * - For each number in the array, increment the value at the index corresponding to the number.
     * - Use modular arithmetic to ensure the array retains its original values while marking indices.
     * - In the second traversal, the index with the unmodified value corresponds to the missing number.
     * 
     * Explanation:
     * - By leveraging the array as a hashmap, we avoid the need for extra space while maintaining a constant-time lookup for each number.
     * 
     * Time Complexity:
     * - O(n), as the array is traversed twice (marking and checking).
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param arr The input array.
     * @return The missing number.
     */
    public static int missingNumberIndexBasedHashing(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int original = arr[i] % (arr.length + 1);
            if (original < arr.length)
                arr[original] += (arr.length + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] / (arr.length + 1) == 0)
                return i;
        }
        return arr.length;
    }

    /**
     * Approach: XOR Method
     * 
     * Intuition:
     * - XOR has the property that `x ^ x = 0` and `x ^ 0 = x`.
     * - By XORing all numbers in the array and all numbers in the range [0, n], every number cancels out except the missing number.
     * 
     * Explanation:
     * - The XOR operation effectively cancels out duplicate occurrences of numbers, leaving only the missing value.
     * 
     * Time Complexity:
     * - O(n), as the array and range are traversed once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param arr The input array.
     * @return The missing number.
     */
    public static int missingNumberEfficient(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i]; // XOR all array elements
            ans ^= (i + 1); // XOR with numbers in range [1, n]
        }
        return ans; // Remaining number is the missing one
    }
}
