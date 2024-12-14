/*- 
 * Problem Statement:
 * 
 *     Given an integer `n` and a number `k`, the task is to rotate the digits of `n` by `k` positions to the right.
 *     If `k` is negative, the rotation should be towards the left. The function should handle cases where `k` is larger than 
 *     the number of digits in `n`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9) representing the number.
 *     - An integer `k` (-10^6 <= k <= 10^6) representing the number of rotations.
 * 
 * Output:
 *     - The rotated number.
 * 
 * Example:
 *     Input:
 *     12345 2
 *     Output:
 *     45123
 * 
 *     Explanation:
 *     - The number 12345 is rotated to the right by 2 positions, resulting in 45123.
 * 
 *     Input:
 *     12345 -2
 *     Output:
 *     34512
 * 
 *     Explanation:
 *     - The number 12345 is rotated to the left by 2 positions, resulting in 34512.
 */

import java.util.Scanner;
 
 public class j12RotateNumber {
     public static void main(String args[]){
         // Reading input
         Scanner in = new Scanner(System.in);
         int n = in.nextInt();
         int k = in.nextInt();
 
         // Print original number and its rotated result
         System.out.println("Original : " + n);
         System.out.println("Rotated : " + rotateNumber(n, k));
 
         // Close the scanner
         in.close();
     }
 
     /*- 
      * Approach: Rotate the digits of the number based on `k`.
      * 
      * Intuition:
      * - The length of the number (l) is first determined by computing the number of digits.
      * - The number of rotations is adjusted by taking the modulus of `k` with `l` to avoid unnecessary rotations.
      * - If `k` is negative, it's adjusted to perform a left rotation.
      * - The number is split into two parts: the last `k` digits and the remaining digits, which are then swapped.
      * 
      * Time Complexity:
      * - O(1), as the operations involved (calculating log10, modulus, division) are constant-time operations.
      * 
      * Space Complexity:
      * - O(1), as the space used for the variables is constant.
      * 
      * @param n The input number to be rotated.
      * @param k The number of rotations.
      * @return The rotated number.
      */
     public static int rotateNumber(int n, int k){
         int l = (int)(Math.log10(n)) + 1; // Length of the number
         k = k % l; // Handle rotations greater than the length of the number
         if(k < 0){ // Adjust for negative rotations (left rotation)
             k += l;
         }
 
         // Get the last k digits and the first part of the number
         int last = n % (int)Math.pow(10, k);
         int first = n / (int)Math.pow(10, k);
 
         // Combine the two parts in the rotated order
         return last * (int)Math.pow(10, l - k) + first;
     }
 }
 