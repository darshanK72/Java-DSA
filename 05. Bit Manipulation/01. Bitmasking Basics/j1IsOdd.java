/**
 * Problem Statement:
 * 
 *     Determine if a given integer `n` is odd. The function should return `true` if `n` is odd, and `false` otherwise.
 * 
 * Input:
 *     - An integer `n` (-10^9 <= n <= 10^9), representing the number to check.
 * 
 * Output:
 *     - A boolean value: `true` if `n` is odd, otherwise `false`.
 * 
 * Example:
 *     Input:
 *         3
 *     Output:
 *         true
 * 
 *     Explanation:
 *         3 is an odd number, so the output is `true`.
 * 
 *     Input:
 *         4
 *     Output:
 *         false
 * 
 *     Explanation:
 *         4 is an even number, so the output is `false`.
 */

 import java.util.Scanner;

 public class j1IsOdd {
 
     public static void main(String args[]) {
         // Reading input
         Scanner in = new Scanner(System.in);
         int n = in.nextInt(); // Input: the number to check
         System.out.println(isOdd(n)); // Output: result of the isOdd function
         in.close();
     }
 
     /**
      * Approach: Bitwise Check for Odd
      * 
      * Intuition:
      * - A number is odd if the least significant bit (LSB) is `1`. Using bitwise AND (`&`),
      *   we can check if the LSB is `1` by performing `(n & 1)`.
      * 
      * Time Complexity:
      * - O(1), as the operation involves a single bitwise check.
      * 
      * Space Complexity:
      * - O(1), no additional space is used.
      * 
      * @param n The number to check.
      * @return `true` if `n` is odd, otherwise `false`.
      */
     public static boolean isOdd(int n) {
         return (n & 1) == 1; // Bitwise operation to check if `n` is odd
     }
 }
 