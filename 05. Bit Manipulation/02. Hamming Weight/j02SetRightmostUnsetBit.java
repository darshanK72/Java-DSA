/**
 * Problem Statement:
 * 
 *     Given an integer `n`, set the rightmost unset (0) bit in its binary representation to 1. 
 *     The rightmost unset bit is the first occurrence of 0 starting from the rightmost side of the number. 
 *     If no unset bit is found, return the number as it is.
 * 
 * Input:
 *     - A positive integer `n` (1 <= n <= 10^9).
 * 
 * Output:
 *     - The integer after setting the rightmost unset bit.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     7
 * 
 *     Input:
 *     15
 *     Output:
 *     15
 * 
 * Explanation:
 *     In the first example, the binary representation of 5 is `101`. 
 *     The rightmost unset bit is at index 1, so the number becomes `111`, which is 7.
 *     In the second example, the binary representation of 15 is `1111`, and there are 
 *     no unset bits, so the number remains 15.
 */

 import java.util.Scanner;

 public class j02SetRightmostUnsetBit {
     public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         int n = in.nextInt();
         System.out.println(setRightmostUnsetBit(n));
         in.close();
     }
 
     /**
      * Approach: Brute Force (Iterative Method)
      * 
      * Intuition:
      * - This method iterates through all 32 bits and checks if the bit at position `i` is unset (0). 
      * - If the bit is unset, it sets the bit at that position to 1 and returns the modified number.
      * 
      * Time Complexity:
      * - O(32) = O(1), as there are at most 32 bits to check.
      * 
      * Space Complexity:
      * - O(1), as no additional space is used.
      * 
      * @param n The input number.
      * @return The modified number with the rightmost unset bit set to 1.
      */
     public static int setRightmostUnsetBit(int n) {
         for (int i = 0; i < 32; i++) {
             if ((n & (1 << i)) == 0) {
                 n |= (1 << i);
                 break;
             }
         }
         return n;
     }
 }
 