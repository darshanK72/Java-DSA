/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to compute the sum of the bit differences of all pairs (arr[i], arr[j]) where i < j.
 *     The bit difference between two integers is the number of positions at which the corresponding bits are different. 
 *     For each pair, we need to calculate the bit difference and sum it for all possible pairs.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the sum of bit differences for all pairs (arr[i], arr[j]) where i < j.
 * 
 * Example:
 *     Input:
 *     [1, 2, 3]
 *     Output:
 *     8
 * 
 *     Explanation:
 *     The bit differences for each pair are:
 *     - Bit difference between 1 and 2: 2
 *     - Bit difference between 1 and 3: 2
 *     - Bit difference between 2 and 3: 4
 *     Total sum = 2 + 2 + 4 = 8
 */

import java.util.Scanner;

public class j07BitDifferencePairSum {

    public static void main(String args[]) {
        // Reading input from user
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Size of the array
        int[] arr = new int[n]; // Array to store the input numbers
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Read each element into the array
        }

        // Call both the naive and efficient methods
        System.out.println(sumOfBitDifferenceOfAllPairsNive(arr));
        System.out.println(sumOfBitDifferenceOfAllPairsEfficient(arr));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Naive Method
     * 
     * Intuition:
     * - In the naive approach, we calculate the bit difference for every pair of elements in the array.
     * - We XOR the two numbers to find positions where the bits differ, then count how many bits are set to 1 in the result of the XOR operation.
     * - This approach is simple but inefficient as it requires examining every pair.
     * 
     * Time Complexity:
     * - O(n^2), where n is the size of the array. We are examining every pair of elements, which takes O(n^2) time.
     * 
     * Space Complexity:
     * - O(1), since we are using a constant amount of extra space for the sum and temporary variables.
     * 
     * @param arr The input array of integers.
     * @return The sum of bit differences of all pairs.
     */
    public static int sumOfBitDifferenceOfAllPairsNive(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                sum += 2 * Integer.bitCount(arr[i] ^ arr[j]); // XOR and count differing bits
            }
        }
        return sum;
    }

    /**
     * Approach 2: Efficient Method
     * 
     * Intuition:
     * - Instead of comparing each pair of elements, we optimize by considering the contribution of each bit position independently.
     * - For each bit position (0 to 31), count how many numbers have that bit set to 1 (`onBits`). 
     * - The total contribution to the bit difference from that bit position is given by:
     *     `2 * (onBits * (n - onBits))`
     * - This counts how many pairs of elements differ at the current bit position.
     * 
     * Time Complexity:
     * - O(32 * n), which simplifies to O(n). We loop over each of the 32 bit positions and count how many numbers have the bit set to 1.
     * 
     * Space Complexity:
     * - O(1), since we only need a constant amount of space for temporary variables (`onBits` and `sum`).
     * 
     * @param arr The input array of integers.
     * @return The sum of bit differences of all pairs.
     */
    public static int sumOfBitDifferenceOfAllPairsEfficient(int[] arr) {
        int sum = 0;
        for (int i = 0; i < 32; i++) { // Iterate over each bit position (32 bits for numbers <= 10^5)
            int onBits = 0; // Count of numbers with the i-th bit set to 1
            for (int j = 0; j < arr.length; j++) {
                if ((arr[j] & (1 << i)) != 0) { // Check if the i-th bit is set to 1
                    onBits++;
                }
            }
            // Calculate the contribution of the i-th bit to the sum of bit differences
            sum += 2 * (onBits * (arr.length - onBits));
        }
        return sum;
    }
}
