/**
 * Problem Statement:
 * 
 *     A digit string is considered "good" if the digits at even indices are even
 *     (0, 2, 4, 6, 8) and the digits at odd indices are prime numbers (2, 3, 5, or 7).
 *     Given an integer `n`, return the total number of good digit strings of length `n`.
 *     Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^15), representing the length of the digit string.
 * 
 * Output:
 *     - An integer representing the count of good digit strings of length `n`, modulo 10^9 + 7.
 * 
 * Example:
 *     Input:
 *     1
 *     Output:
 *     5
 * 
 *     Explanation:
 *     There are 5 good digit strings of length 1: "0", "2", "4", "6", "8".
 */

public class j15CountGoodNumbers {

    public static void main(String[] args) {
        // Example usage
        long n = 1;
        System.out.println(countGoodNumbers(n)); // Output: 5
    }

    /**
     * Approach: Efficient Calculation Using Modular Exponentiation
     * 
     * Intuition:
     * - For a digit string of length `n`, positions are indexed from 0 to `n-1`.
     * - Even indices (0, 2, 4, ...) must contain even digits: 0, 2, 4, 6, 8 (5
     * choices).
     * - Odd indices (1, 3, 5, ...) must contain prime digits: 2, 3, 5, 7 (4
     * choices).
     * - Calculate the number of even and odd positions:
     * - Number of even positions = (n + 1) / 2
     * - Number of odd positions = n / 2
     * - The total number of good digit strings is:
     * - 5^(number of even positions) * 4^(number of odd positions)
     * - Given the potentially large result, compute the answer modulo 10^9 + 7.
     * - Use modular exponentiation to efficiently compute large powers under a
     * modulus.
     * 
     * Time Complexity:
     * - O(log n), due to the use of modular exponentiation.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space.
     * 
     * @param n The length of the digit string.
     * @return The count of good digit strings of length `n`, modulo 10^9 + 7.
     */
    public static int countGoodNumbers(long n) {
        final int MOD = 1_000_000_007;
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;
        long evenChoices = modExp(5, evenCount, MOD);
        long oddChoices = modExp(4, oddCount, MOD);
        return (int) (evenChoices * oddChoices % MOD);
    }

    /**
     * Helper method to perform modular exponentiation.
     * Computes (base^exp) % mod efficiently.
     * 
     * @param base The base number.
     * @param exp  The exponent.
     * @param mod  The modulus.
     * @return The result of (base^exp) % mod.
     */
    private static long modExp(long base, long exp, int mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
