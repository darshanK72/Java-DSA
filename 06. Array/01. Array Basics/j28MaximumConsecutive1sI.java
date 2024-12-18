/**
 * Problem Statement:
 * 
 *     Given a binary array `arr`, find the maximum number of consecutive `1`s in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element is either 0 or 1.
 * 
 * Output:
 *     - An integer representing the maximum number of consecutive `1`s in the array.
 * 
 * Example:
 *     Input:
 *         6
 *         1 1 0 1 1 1
 *     Output:
 *         3
 * 
 *     Explanation:
 *         The maximum number of consecutive `1`s is `3` (from the last three elements).
 */

 import java.util.Scanner;

 public class j28MaximumConsecutive1sI {
 
     public static void main(String args[]) {
         // Reading input
         Scanner in = new Scanner(System.in);
         int n = in.nextInt(); // Input: size of the array
         int[] arr = new int[n];
         for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt(); // Input: elements of the array
         }
 
         // Output the maximum number of consecutive 1s
         System.out.println("Maximum Consecutive 1s: " + maxConsOnes(arr));
 
         in.close();
     }
 
     /**
      * Approach: Iterative Count of Consecutive 1s
      * 
      * Intuition:
      *     - We can iterate through the array and keep a count of consecutive `1`s.
      *     - Whenever a `1` is encountered, increment the `count`. If a `0` is encountered, reset the `count`.
      *     - During this process, keep track of the maximum count of consecutive `1`s found.
      *     - Finally, return the maximum count after iterating through the array.
      * 
      * Time Complexity:
      *     - O(n), where `n` is the size of the array. We traverse the array once, which is the main operation.
      * 
      * Space Complexity:
      *     - O(1), as we use only a few variables to store intermediate results, regardless of the size of the input array.
      * 
      * @param arr The input binary array.
      * @return The maximum number of consecutive 1s.
      */
     public static int maxConsOnes(int[] arr) {
         int count = 0;   // Initialize the count of consecutive 1s
         int result = 0;  // Initialize the result to track the maximum count
 
         // Iterate through the array to count consecutive 1s
         for (int i = 0; i < arr.length; i++) {
             if (arr[i] == 1) {
                 count++;  // Increment the count if the current element is 1
             } else {
                 result = Math.max(result, count);  // Update result if count is greater
                 count = 0;  // Reset the count if a 0 is encountered
             }
         }
 
         // Final check in case the longest sequence ends at the last element
         return Math.max(result, count);
     }
 }
 