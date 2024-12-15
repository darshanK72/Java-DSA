/**
 * Problem Statement:
 * 
 *     Given two integers `left` and `right`, find how many numbers in the range [left, right] (inclusive)
 *     have a number of set bits (1's in their binary representation) that is a prime number.
 * 
 * Input:
 *     - Two integers, `left` and `right` (1 <= left <= right <= 10^6).
 * 
 * Output:
 *     - The number of integers between `left` and `right` (inclusive) that have a prime number of set bits.
 * 
 * Example:
 *     Input:
 *     6 10
 *     Output:
 *     4
 *     
 *     Explanation:
 *     The binary representations of the numbers in the range [6, 10] are:
 *     6  -> 110 (2 set bits, which is prime)
 *     7  -> 111 (3 set bits, which is prime)
 *     8  -> 1000 (1 set bit, which is not prime)
 *     9  -> 1001 (2 set bits, which is prime)
 *     10 -> 1010 (2 set bits, which is prime)
 *     
 *     The numbers with a prime number of set bits are 6, 7, 9, and 10, which gives the result 4.
 */

import java.util.Scanner;

public class j08PrimeSetBitsInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int left = in.nextInt(); // Input: left bound of the range
        int right = in.nextInt(); // Input: right bound of the range
        System.out.println(countPrimeSetBits(left, right)); // Output: number of integers with prime set bits in range
        in.close();
    }

    /**
     * Approach:
     * 
     * 1. First, generate a sieve of primes up to 32 (since the maximum number of set bits a number can have
     *    within the given constraints is 32 bits).
     * 2. Count the number of set bits (1's) in the binary representation of each number in the range [left, right].
     * 3. If the count of set bits is prime, increment the answer.
     * 
     * Time Complexity:
     * - O(n * log(n)), where n is the size of the range, as we check each number for prime set bit count and count set bits.
     * 
     * Space Complexity:
     * - O(1), as we only need a fixed-size array for sieve and set bits counting.
     * 
     * @param left The left bound of the range.
     * @param right The right bound of the range.
     * @return The count of numbers in the range with a prime number of set bits.
     */
    public static int countPrimeSetBits(int left, int right) {
        boolean[] seive = new boolean[33]; // Sieve to mark prime numbers up to 32
        seive[0] = true; // 0 is not prime
        seive[1] = true; // 1 is not prime
        for (int i = 2; i * i < 32; i++) {
            if (!seive[i]) {
                for (int j = i * i; j < 32; j += i) {
                    seive[j] = true; // Mark non-prime numbers
                }
            }
        }

        int count = 0; // Variable to store the result
        for (int i = left; i <= right; i++) {
            if (!seive[countSetBits(i)]) { // If the count of set bits is prime
                count++;
            }
        }
        return count; // Return the result
    }

    /**
     * Helper function to count the number of set bits in a given number.
     * 
     * Time Complexity: O(log(n)), where n is the number.
     * 
     * @param n The integer whose set bits need to be counted.
     * @return The number of set bits in n.
     */
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count++; // Increment the count for each set bit
            n &= (n - 1); // Remove the rightmost set bit
        }
        return count; // Return the total count of set bits
    }
}
