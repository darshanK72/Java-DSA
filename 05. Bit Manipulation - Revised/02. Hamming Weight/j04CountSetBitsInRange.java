/**
 * Problem Statement:
 * 
 *     Given an integer `n`, count the total number of set bits (1's) in the binary representation of all numbers from 1 to `n`.
 *     A set bit is a bit that is equal to 1 in the binary form of the number.
 * 
 * Input:
 *     - A positive integer `n` (1 <= n <= 10^9).
 * 
 * Output:
 *     - The total number of set bits (1's) in the binary representation of all integers from 1 to `n`.
 * 
 * Example:
 *     Input:
 *     4
 *     Output:
 *     5
 * 
 *     Explanation:
 *     In the range from 1 to 4, the binary representations of the numbers are:
 *     1: 0001 (1 set bit)
 *     2: 0010 (1 set bit)
 *     3: 0011 (2 set bits)
 *     4: 0100 (1 set bit)
 *     So, the total count of set bits is 5.
 */

import java.util.Scanner;

public class j04CountSetBitsInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(countSetBitsInRangeNive(n));
        System.out.println(countSetBitsInRangeEfficient1(n));
        System.out.println(countSetBitsInRangeEfficient2(n));
        in.close();
    }

    /**
     * Approach 1: Brute Force (Iterative Method)
     * 
     * Intuition:
     * - Iterate through each number from 1 to `n`, and for each number, count the number of set bits.
     * 
     * Time Complexity:
     * - O(n * 32), as for each number, we are checking up to 32 bits.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The total number of set bits in the binary representation of all numbers from 1 to `n`.
     */
    public static int countSetBitsInRangeNive(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int x = i;
            while (x != 0) {
                x &= (x - 1);
                count++;
            }
        }
        return count;
    }

    /**
     * Approach 2: Efficient Method using Integer.bitCount
     * 
     * Intuition:
     * - Use the built-in `Integer.bitCount()` method to count set bits for each number from 1 to `n`.
     * 
     * Time Complexity:
     * - O(n), as we are counting set bits for each number.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The total number of set bits in the binary representation of all numbers from 1 to `n`.
     */
    public static int countSetBitsInRangeEfficient1(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += Integer.bitCount(i);
        }
        return count;
    }

    /**
     * Approach 3: Optimized Method using Blocks
     * 
     * Intuition:
     * - Use the block structure of numbers to efficiently count set bits by calculating how many set bits are in complete blocks of size `2^i`.
     * 
     * Time Complexity:
     * - O(sqrt(n)), as we are iterating over the bits positions and calculating the set bits in blocks.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The total number of set bits in the binary representation of all numbers from 1 to `n`.
     */
    public static int countSetBitsInRangeEfficient2(int n) {
        int count = 0;
        for (int i = 0; (1 << i) <= n; i++) {
            // Calculate the size of the block where the bit at position i is set
            int blockSize = 1 << (i + 1);
            int completeBlocks = n / blockSize;

            // Count set bits from the complete blocks
            count += completeBlocks * (blockSize >> 1);

            // Handle remaining bits after the last complete block
            int remainder = n % blockSize;
            count += Math.max(0, remainder - (blockSize >> 1) + 1);
        }
        return count;
    }

    /**
     * Approach 4: DP Approach (Using Precomputed Results)
     * 
     * Intuition:
     * - Precompute the number of set bits for each number up to `n` using dynamic programming and store the results.
     * 
     * Time Complexity:
     * - O(n), as we are precomputing results for all numbers up to `n`.
     * 
     * Space Complexity:
     * - O(n), as we store the results in an array.
     * 
     * @param n The input number.
     * @return An array containing the number of set bits for each number from 0 to `n`.
     */
    public static int[] countSetBitsInRagneEfficient(int n) {
        int[] out = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            out[i] = out[(i >> 1)] + (i & 1);
        }
        return out;
    }

    /**
     * Approach 5: Recursive Method (Using Bit Manipulation)
     * 
     * Intuition:
     * - Recursively count set bits by calculating the contributions of the most significant bit and then reducing the problem size.
     * 
     * Time Complexity:
     * - O(log n), as the problem size reduces exponentially in each recursive step.
     * 
     * Space Complexity:
     * - O(log n), due to the recursion stack.
     * 
     * @param n The input number.
     * @return The total number of set bits in the binary representation of all numbers from 1 to `n`.
     */
    public static int countSetBitsInRangeEfficientRecursive(int n) {
        if (n == 0)
            return 0;

        int x = 0;
        while ((1 << x) <= n) {
            x++;
        }
        x = x - 1;

        int btill2x = x * (1 << (x - 1));
        int msb2xton = n - (1 << x) + 1;
        int rest = n - (1 << x);
        int ans = btill2x + msb2xton + countSetBitsInRangeEfficientRecursive(rest);

        return ans;
    }

    /**
     * Approach 6: Lookup Table Method
     * 
     * Intuition:
     * - Precompute the number of set bits for each byte using a lookup table and then use this table to count set bits for the entire number.
     * 
     * Time Complexity:
     * - O(1) for each lookup, and O(1) for processing each byte of the number.
     * 
     * Space Complexity:
     * - O(1), as the lookup table is of fixed size (256 entries).
     * 
     * @param num The input number.
     * @return The total number of set bits in the binary representation of `num`.
     */
    private static final int[] lookupTable = new int[256];

    static {
        for (int i = 0; i < 256; i++) {
            lookupTable[i] = (i & 1) + lookupTable[i / 2];
        }
    }

    public static int countSetBitsLookupTable(int num) {
        int count = 0;
        count += lookupTable[num & 0xFF]; // Count set bits in the least significant byte
        count += lookupTable[(num >>> 8) & 0xFF]; // Count set bits in the second byte
        count += lookupTable[(num >>> 16) & 0xFF]; // Count set bits in the third byte
        count += lookupTable[(num >>> 24) & 0xFF]; // Count set bits in the most significant byte
        return count;
    }
}
