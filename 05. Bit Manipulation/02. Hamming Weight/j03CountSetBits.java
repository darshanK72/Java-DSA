/**
 * Problem Statement:
 * 
 *     Given a positive integer `n`, count the number of set bits (1's) in its binary representation.
 *     A set bit is a bit that is equal to 1 in the binary form of the number.
 * 
 * Input:
 *     - A positive integer `n` (1 <= n <= 10^9).
 * 
 * Output:
 *     - The number of set bits (1's) in the binary representation of `n`.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     2
 * 
 *     Input:
 *     15
 *     Output:
 *     4
 * 
 * Explanation:
 *     In the first example, the binary representation of 5 is `101`, which has 2 set bits.
 *     In the second example, the binary representation of 15 is `1111`, which has 4 set bits.
 */

import java.util.Scanner;

public class j03CountSetBits {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsNive(n));
        System.out.println(countSetBitsEfficient1(n));
        System.out.println(countSetBitsEfficient2(n));

        in.close();
    }

    /**
     * Approach: Brute Force (Iterative Method)
     * 
     * Intuition:
     * - This method iterates through each bit of `n` and checks whether the bit is set (1) or not. 
     * - If the bit is set, increment the count.
     * 
     * Time Complexity:
     * - O(log n), as we are iterating through each bit of `n`.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The number of set bits in the binary representation of `n`.
     */
    public static int countSetBitsNive(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    /**
     * Approach: Efficient Method (Using n - n & -n)
     * 
     * Intuition:
     * - This method uses a bitwise trick to reduce the number of bits we need to check.
     * - In each iteration, we subtract the rightmost set bit from `n` and increment the count.
     * 
     * Time Complexity:
     * - O(log n), as the number of iterations is proportional to the number of set bits.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The number of set bits in the binary representation of `n`.
     */
    public static int countSetBitsEfficient1(int n) {
        int count = 0;
        while (n > 0) {
            n -= (n & -n);
            count++;
        }
        return count;
    }

    /**
     * Approach: Efficient Method (Using n & (n - 1))
     * 
     * Intuition:
     * - This method uses another bitwise trick, where `n & (n - 1)` removes the rightmost set bit in `n`.
     * - We repeat this process until `n` becomes zero.
     * 
     * Time Complexity:
     * - O(log n), as the number of iterations is proportional to the number of set bits.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The number of set bits in the binary representation of `n`.
     */
    public static int countSetBitsEfficient2(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
