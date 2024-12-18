/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to compute the sum of XOR of all possible unique pairs from the array.
 *     Specifically, for each bit position, we calculate how many numbers have that bit set to 1, and how many numbers have that bit set to 0. 
 *     Then, the contribution to the XOR sum for that bit is calculated as the product of these counts, multiplied by the value of the bit.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A long integer representing the sum of XOR of all unique pairs (arr[i], arr[j]) where i < j.
 * 
 * Example:
 *     Input:
 *     [1, 2, 3]
 *     Output:
 *     6
 * 
 *     Explanation:
 *     The unique pairs and their XORs:
 *     - 1 ^ 2 = 3
 *     - 1 ^ 3 = 2
 *     - 2 ^ 3 = 1
 *     Total sum = 3 + 2 + 1 = 6
 */

import java.util.Scanner;

public class j06UniqueXorPairSum {

    public static void main(String[] args) {
        // Reading input from the user
        Scanner in = new Scanner(System.in);

        // Read the size of the array
        int n = in.nextInt();

        // Read the array elements
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Calculate the XOR sum of all unique pairs
        long result = sumXOR(arr, n);

        // Output the result
        System.out.println(result);

        in.close();
    }

    /**
     * Approach: Bitwise Counting Method
     * 
     * Intuition:
     * - Instead of calculating the XOR of every pair directly, we can exploit the properties of XOR at the bit level.
     * - For each bit position (from 0 to 31), we count how many numbers have that bit set to 1 (`ones`) and how many have it set to 0 (`n - ones`).
     * - The XOR of two numbers contributes 1 to the sum at a particular bit position if one number has that bit set to 1 and the other has it set to 0.
     * - Therefore, for each bit position, the contribution to the total sum is calculated as: 
     *     `ones * (n - ones) * (1 << i)`
     *     where `i` is the bit position.
     * 
     * Time Complexity:
     * - O(n * 32), as we loop over each bit position (32 iterations) and count how many numbers have each bit set to 1.
     *     This results in O(n) work per bit, leading to a total time complexity of O(n * 32) or O(n).
     * 
     * Space Complexity:
     * - O(1), since we are only using a constant amount of extra space for the variables `ones`, `ans`, and the bit position iteration.
     * 
     * @param arr The input array of integers.
     * @param n The size of the array.
     * @return The sum of XOR of all unique pairs.
     */
    public static long sumXOR(int arr[], int n) {
        long ans = 0; // Initialize result to 0
        for (int i = 0; i < 32; i++) { // Iterate over each bit position (32 bits for the numbers <= 10^5)
            long ones = 0; // Count of numbers with the i-th bit set to 1
            for (int num : arr) { // Loop through the array to check each number
                ones += (((num & (1 << i)) > 0) ? 1 : 0); // Check if the i-th bit is set to 1
            }
            // Calculate the contribution of the current bit position to the total XOR sum
            ans += ((long) (ones * ((long) n - ones)) * (1 << i));
        }
        return ans; // Return the total XOR sum
    }
}
