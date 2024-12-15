/**
 * Problem Statement:
 * 
 *     Given an integer `n`, determine if it is a power of three. A number is considered a power 
 *     of three if it can be written as 3^k, where `k` is a non-negative integer.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number to check if it is a power of three.
 * 
 * Output:
 *     - `true` if `n` is a power of three, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     27
 *     Output:
 *     true
 * 
 *     Explanation:
 *     27 is a power of three because 3^3 = 27.
 * 
 *     Input:
 *     45
 *     Output:
 *     false
 * 
 *     Explanation:
 *     45 is not a power of three.
 * 
 */

import java.util.Scanner;

public class j07IsPowerOfThree {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to check if it is a power of three

        // Output: Checking using three different approaches
        System.out.println(isPowerOfThree(n)); // Using binary string and sums
        System.out.println(isPowerOfThreeEfficient(n)); // Using mathematical approach
        System.out.println(isPowerOfThreeRec(n)); // Using recursive approach

        in.close();
    }

    /**
     * Approach 1: Binary String and Sum of Bits
     * 
     * Intuition:
     * - This approach checks if the binary representation of `n` has a certain property. 
     *   If the sum of bits at even positions is equal to the sum of bits at odd positions, 
     *   or if their difference is divisible by 3, then the number is considered a power of three.
     * 
     * Time Complexity:
     * - O(log n), as we process each bit of the binary representation of `n`.
     * 
     * Space Complexity:
     * - O(log n), as we store the binary representation of `n`.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of three, otherwise `false`.
     */
    public static boolean isPowerOfThree(int n) {
        String binary = Integer.toBinaryString(n);
        int evenSum = 0;
        int oddSum = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (i % 2 == 0) {
                oddSum += binary.charAt(i) - '0';
            } else {
                evenSum += binary.charAt(i) - '0';
            }
        }

        if (evenSum == oddSum || Math.abs(evenSum - oddSum) % 3 == 0)
            return true;
        return false;
    }

    /**
     * Approach 2: Efficient Mathematical Approach
     * 
     * Intuition:
     * - This approach checks if `n` is a divisor of 3^19. Since 3^19 is the largest power of 3 that 
     *   is less than or equal to 10^9, if `n` divides this number evenly, it must be a power of three.
     * 
     * Time Complexity:
     * - O(1), as this approach uses constant-time arithmetic operations.
     * 
     * Space Complexity:
     * - O(1), no extra space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of three, otherwise `false`.
     */
    public static boolean isPowerOfThreeEfficient(int n) {
        return n > 0 && (int) Math.pow(3, 19) % n == 0;
    }

    /**
     * Approach 3: Recursive Approach
     * 
     * Intuition:
     * - This approach checks if `n` is divisible by 3, and recursively divides it by 3. 
     *   If at any point `n` becomes 1, it is a power of three. If it is divisible by 3, 
     *   it continues the recursion. If not, it returns `false`.
     * 
     * Time Complexity:
     * - O(log n), since we divide `n` by 3 in each recursive step.
     * 
     * Space Complexity:
     * - O(log n), due to recursion stack space.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of three, otherwise `false`.
     */
    public static boolean isPowerOfThreeRec(int n) {
        if (n == 0)
            return false;
        if (n == 1)
            return true;

        return (n % 3 == 1) ? false : isPowerOfThreeRec(n / 3);
    }
}
