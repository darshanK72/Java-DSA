/**
 * Problem Statement:
 * 
 *     Given an array of size `n` containing integers in the range [1, n], exactly one element is missing, and one element is duplicated. 
 *     Find the missing and duplicate numbers.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= n).
 * 
 * Output:
 *     - An integer array of size 2 where:
 *         - The first element is the missing number.
 *         - The second element is the duplicated number.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [4, 3, 6, 2, 1, 1]
 *     Output:
 *         Missing : 5
 *         Duplicate : 1
 * 
 *     Explanation:
 *         The number 5 is missing, and 1 appears twice in the array.
 */

 import java.util.Scanner;

 public class j06MissingAndDuplicate {
 
     public static void main(String args[]) {
         // Reading input
         Scanner in = new Scanner(System.in);
         int n = in.nextInt();
         int[] arr = new int[n];
         for (int i = 0; i < n; i++) {
             arr[i] = in.nextInt();
         }
 
         // Approach 1: Index-Based Hashing
         int[] ans1 = repeatAndMissingIndexBasedHashing(arr);
         System.out.println("Index-Based Hashing:");
         System.out.println("Missing : " + ans1[0] + "\nDuplicate : " + ans1[1]);
 
         // Approach 2: Bit Manipulation
         int[] ans2 = repeatAndMissingBitManipulation(arr);
         System.out.println("Bit Manipulation:");
         System.out.println("Missing : " + ans2[0] + "\nDuplicate : " + ans2[1]);
 
         in.close();
     }
 
     /**
      * Approach 1: Index-Based Hashing
      * 
      * Intuition:
      * - Use the array itself as a hash table to track occurrences of each number.
      * - Increment the value at the index corresponding to each number.
      * - If a number is missing, its index will remain unchanged.
      * - If a number is duplicated, its index will have a value incremented more than once.
      * 
      * Explanation:
      * - For every element in the array, calculate the original number using modulo operation.
      * - Increment the value at the corresponding index.
      * - Traverse the array again to check values:
      *     - If a value is less than the array size, the number is missing.
      *     - If a value is greater than twice the array size, the number is duplicated.
      * 
      * Time Complexity:
      * - O(n): Single traversal for marking and another for identifying results.
      * 
      * Space Complexity:
      * - O(1): No extra space used apart from input.
      * 
      * @param arr The input array.
      * @return An array containing [missing number, duplicate number].
      */
     public static int[] repeatAndMissingIndexBasedHashing(int[] arr) {
         for (int i = 0; i < arr.length; i++) {
             int original = arr[i] % (arr.length + 1);
             arr[original - 1] += (arr.length + 1);
         }
         int[] out = new int[2];
         for (int i = 0; i < arr.length; i++) {
             if (arr[i] / (arr.length + 1) == 0)
                 out[1] = i + 1; // Duplicate
             if (arr[i] / (arr.length + 1) > 1)
                 out[0] = i + 1; // Missing
         }
         return out;
     }
 
     /**
      * Approach 2: Bit Manipulation
      * 
      * Intuition:
      * - XOR can be used to isolate mismatched numbers.
      * - XORing all array elements and numbers from 1 to n will cancel out duplicates, leaving the XOR of the missing and duplicate numbers.
      * - Use the rightmost differing bit to segregate the numbers into two groups.
      * 
      * Explanation:
      * - Compute the XOR of all numbers in the array and from 1 to n.
      * - Identify the rightmost differing bit (isolates at least one mismatch).
      * - Partition the numbers into two groups based on this differing bit.
      * - XOR the groups separately to find the missing and duplicate numbers.
      * 
      * Time Complexity:
      * - O(n): Two traversals of the array and numbers.
      * 
      * Space Complexity:
      * - O(1): No additional space used.
      * 
      * @param arr The input array.
      * @return An array containing [missing number, duplicate number].
      */
     public static int[] repeatAndMissingBitManipulation(int[] arr) {
         int xor = 0;
         for (int i = 0; i < arr.length; i++) {
             xor ^= arr[i];
         }
 
         for (int i = 1; i <= arr.length; i++) {
             xor ^= i;
         }
 
         int rd = xor & -xor; // Rightmost differing bit
         int first = 0;
         int second = 0;
         for (int i = 0; i < arr.length; i++) {
             if ((arr[i] & rd) == 0) {
                 first ^= arr[i];
             } else {
                 second ^= arr[i];
             }
         }
         for (int i = 1; i <= arr.length; i++) {
             if ((i & rd) == 0) {
                 first ^= i;
             } else {
                 second ^= i;
             }
         }
 
         return new int[] { first, second };
     }
 }
 