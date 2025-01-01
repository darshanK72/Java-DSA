/**
 * Problem Statement:
 * 
 *     Given a positive integer `num`, calculate its one's complement.
 * 
 * Input:
 *     - An integer `num` (1 <= num <= 10^5), the number to calculate the complement for.
 * 
 * Output:
 *     - A string showing the binary representation of `num` and its complement.
 * 
 * Example:
 *     Input:
 *         5
 *     Output:
 *         101
 *         11111111111111111111111111111010
 *         10
 *         10
 * 
 *     Explanation:
 *         The binary representation of 5 is "101".
 *         The one's complement of 5 is "11111111111111111111111111111010".
 *         The result of the bitwise complement (inverted bits) for 5 is 10.
 *         The efficient approach yields the same result 10.
 */

 import java.util.Scanner;

 public class j06OnesComplement {
 
     public static void main(String args[]) {
         // Reading input
         Scanner in = new Scanner(System.in);
         int num = in.nextInt(); // Input: the number to find the complement of
         System.out.println(Integer.toBinaryString(num)); // Print binary of num
         System.out.println(Integer.toBinaryString(~num)); // Print 32-bit ones complement of num
         System.out.println(Integer.toBinaryString(findComplement(num))); // Print complement using bitwise approach
         System.out.println(Integer.toBinaryString(findComplementEfficient(num))); // Print complement using efficient approach
         in.close();
     }
 
     /**
      * Approach 1: Bitwise Approach for Complement
      * 
      * Intuition:
      * - Traverse each bit of `num`, and if the bit is 0, set it to 1 in the output.
      * - If the bit is 1, leave it unchanged. This can be achieved using bitwise operations.
      * 
      * Time Complexity:
      * - O(log(num)), as we need to traverse the binary representation of `num`.
      * 
      * Space Complexity:
      * - O(1), as the output is calculated using constant space.
      * 
      * @param num The input integer.
      * @return The complement of `num` using bitwise operations.
      */
     public static int findComplement(int num) {
         int output = 0; // Initialize output
         int i = 0; // Initialize bit index
         while (num > 0) {
             if ((num & 1) == 0) { // If the current bit is 0
                 output = output | (1 << i); // Set the corresponding bit in output
             }
             num >>= 1; // Right shift to check the next bit
             i++; // Move to the next bit position
         }
         return output; // Return the complement
     }
 
     /**
      * Approach 2: Efficient Bitwise Approach for Complement
      * 
      * Intuition:
      * - Find the number of bits in `num`. Using this information, create a mask where all bits are 1.
      * - XOR the mask with `num` to flip all bits.
      * 
      * Time Complexity:
      * - O(1), as the logarithmic operation is constant time for 32-bit integers.
      * 
      * Space Complexity:
      * - O(1), as only a constant number of variables are used.
      * 
      * @param num The input integer.
      * @return The complement of `num` using the efficient approach.
      */
     public static int findComplementEfficient(int num) {
         int noOfBits = (int) (Math.log(num) / Math.log(2) + 1); // Calculate the number of bits in num
         return num ^ ((1 << noOfBits) - 1); // XOR num with a mask to flip bits
     }
 }
 