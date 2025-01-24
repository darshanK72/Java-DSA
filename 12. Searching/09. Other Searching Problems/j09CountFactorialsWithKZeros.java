/**
 * Problem Statement:
 * 
 *     Given an integer `k`, determine how many non-negative integers `x` exist such that
 *     the number of trailing zeros in `x!` (the factorial of `x`) is exactly `k`.
 * 
 * Input:
 *     - An integer `k` (0 <= k <= 10^9), representing the desired number of trailing zeros.
 * 
 * Output:
 *     - An integer representing the count of non-negative integers `x` such that `x!` 
 *       has exactly `k` trailing zeros. If no such integers exist, return 0.
 * 
 * Example:
 *     Input:
 *     8
 * 
 *     Output:
 *     5
 * 
 *     Explanation:
 *     - There are exactly 5 integers whose factorials have exactly 8 trailing zeros.
 */

public class j09CountFactorialsWithKZeros {

    public static void main(String[] args) {
        // Example Input
        int k = 8; // Input: desired number of trailing zeros
        System.out.println("Number of integers whose factorial has exactly " + k + " trailing zeros: "
                + preimageSizeFZF(k));
    }

    /**
     * Approach: Binary Search for Factorials with Exactly `k` Trailing Zeros
     * 
     * Intuition:
     * - The number of trailing zeros in a factorial is determined by the number of factors of 5.
     * - For a given number `k`, we need to find all integers `x` such that the number of trailing
     *   zeros in `x!` is exactly `k`.
     * - The trailing zero function increases monotonically but can have flat regions. For example:
     *   - If `k = 8`, there might be multiple values of `x` where the factorial has exactly 8 trailing zeros.
     * - The key insight is that the size of any such range is always 5, or it does not exist.
     * - Use binary search to determine if any such range exists.
     * 
     * Steps:
     * 1. Define a binary search space `[0, 10^11]` (based on factorial growth rates).
     * 2. For the midpoint `mid`, calculate the trailing zeros in `mid!`.
     * 3. If the number of trailing zeros equals `k`, return 5.
     * 4. Adjust the search space based on whether the number of trailing zeros is greater or less than `k`.
     * 5. If no such range is found, return 0.
     * 
     * Time Complexity:
     * - O(log(10^11) * log(mid)), where `mid` is the midpoint of the search space, due to repeated binary search
     *   and division in the trailing zeros calculation.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of additional space.
     * 
     * @param k The desired number of trailing zeros.
     * @return The count of integers whose factorial has exactly `k` trailing zeros.
     */
    public static int preimageSizeFZF(int k) {
        long s = 0; // Start of the search space
        long e = (long) 1e11; // End of the search space (upper bound)

        while (s <= e) {
            long mid = s + (e - s) / 2; // Calculate the midpoint
            long zeros = trailingZeros(mid); // Calculate trailing zeros for `mid!`

            if (zeros == k) {
                return 5; // If we find a range, its size is always 5
            } else if (zeros > k) {
                e = mid - 1; // Narrow the search to the left half
            } else {
                s = mid + 1; // Narrow the search to the right half
            }
        }

        return 0; // If no range with exactly `k` trailing zeros is found
    }

    /**
     * Helper Function: Calculate Trailing Zeros in Factorial
     * 
     * Intuition:
     * - Trailing zeros in a factorial are caused by factors of `10`, which are products of `2` and `5`.
     * - Since there are typically more factors of `2` than `5` in a factorial, the number of trailing zeros
     *   is determined by the number of factors of `5`.
     * - To count factors of `5` in numbers from `1` to `x`, repeatedly divide `x` by powers of `5`
     *   (i.e., `5`, `25`, `125`, ...) and sum the quotients.
     * 
     * Time Complexity:
     * - O(log(x)), where `x` is the input number, due to repeated division by `5`.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The number of trailing zeros in `n!`.
     */
    public static long trailingZeros(long n) {
        long count = 0; // Initialize the count of trailing zeros
        for (long i = 5; (n / i) > 0; i *= 5) {
            count += (n / i); // Add the count of factors of `5`
        }
        return count;
    }
}
