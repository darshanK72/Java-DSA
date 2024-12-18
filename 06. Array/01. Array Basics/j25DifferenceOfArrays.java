/**
 * Problem Statement:
 * 
 *     Given two non-negative integers represented as arrays `arr1` and `arr2`, where each digit of the number
 *     is stored as an element in the arrays, compute the difference of these two numbers. The number 
 *     represented by `arr1` is always greater than or equal to the number represented by `arr2`. The 
 *     output should be represented as an array where each element is a digit of the result.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^4), representing the size of the first array.
 *     - An array `arr1` of size `n1` where each element satisfies (0 <= arr1[i] <= 9).
 *     - An integer `n2` (1 <= n2 <= 10^4), representing the size of the second array.
 *     - An array `arr2` of size `n2` where each element satisfies (0 <= arr2[i] <= 9).
 * 
 * Output:
 *     - An array representing the difference of the two input arrays as a single number, where each digit 
 *       is stored in a separate index.
 * 
 * Example:
 *     Input:
 *         4
 *         1 2 3 4
 *         3
 *         5 6 7
 *     Output:
 *         [0, 6, 6, 7]
 * 
 *     Explanation:
 *         The input arrays represent the numbers 1234 and 567. Their difference is 667, which is represented
 *         as [0, 6, 6, 7].
 */

 import java.util.Scanner;
 import java.util.Arrays;
 
 public class j25DifferenceOfArrays {
 
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
         System.out.println("Difference of Arrays: " + Arrays.toString(diffOfArrays(arr1, arr2)));
 
         in.close();
     }
 
     /**
      * Approach: Digit-wise Subtraction with Borrow
      * 
      * Intuition:
      *     - Subtract the two arrays starting from the least significant digit (rightmost side).
      *     - If the current digit of `arr1` is smaller than the corresponding digit of `arr2`, borrow 10 
      *       from the next higher digit and set a carry (borrow).
      *     - Repeat this process for all digits. Since `arr1` >= `arr2`, there will always be a valid 
      *       result without negative numbers.
      * 
      * Explanation:
      *     - Traverse both arrays from right to left.
      *     - For each pair of digits:
      *         1. Check if the current digit of `arr1` is greater than or equal to the current digit 
      *            of `arr2` (including any carry).
      *         2. If not, borrow 10 from the next digit of `arr1`.
      *     - Store the difference in the output array.
      *     - The result array might have leading zeroes, which can be trimmed if required.
      * 
      * Time Complexity:
      *     - O(max(n1, n2)), where n1 and n2 are the lengths of the input arrays.
      * 
      * Space Complexity:
      *     - O(max(n1, n2)), for storing the result array.
      * 
      * @param arr1 The first input array of digits (larger number).
      * @param arr2 The second input array of digits (smaller number).
      * @return An array representing the difference of the two input arrays.
      */
     public static int[] diffOfArrays(int[] arr1, int[] arr2) {
         // Getting lengths of both arrays
         int n1 = arr1.length;
         int n2 = arr2.length;
 
         // Determine the size of the output array
         int m = Math.max(n1, n2);
         int[] output = new int[m];
 
         int carry = 0; // Initialize carry (borrow)
 
         // Loop through the arrays from right to left
         for (int i = 0; i < m; i++) {
             // Extract digits from the arrays (default to 0 if index out of bounds)
             int d1 = (n1 - 1 - i >= 0) ? arr1[n1 - 1 - i] : 0;
             int d2 = (n2 - 1 - i >= 0) ? arr2[n2 - 1 - i] : 0;
 
             // Check if borrowing is needed
             if (d1 >= (d2 + carry)) {
                 output[m - 1 - i] = d1 - d2 - carry;
                 carry = 0;
             } else {
                 output[m - 1 - i] = d1 + 10 - d2 - carry;
                 carry = 1;
             }
         }
 
         // Remove leading zeroes, if necessary
         int start = 0;
         while (start < output.length - 1 && output[start] == 0) {
             start++;
         }
 
         return Arrays.copyOfRange(output, start, output.length);
     }
 }
 