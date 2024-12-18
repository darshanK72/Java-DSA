/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to compute the sum of XOR of each element with its double (2 * element) for each element in the array. 
 *     The XOR operation should be applied for each pair (arr[i], 2 * arr[i]), and the results should be XORed together.
 * 
 * Input:
 *     - An integer array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the XOR of the pair (arr[i], 2 * arr[i]) for each element in the array.
 * 
 * Example:
 *     Input:
 *     [10, 20, 30, 40]
 *     Output:
 *     42
 * 
 *     Explanation:
 *     XOR of the pairs:
 *     - 10 ^ (2 * 10) = 10 ^ 20 = 30
 *     - 20 ^ (2 * 20) = 20 ^ 40 = 60
 *     - 30 ^ (2 * 30) = 30 ^ 60 = 50
 *     - 40 ^ (2 * 40) = 40 ^ 80 = 120
 *     Final XOR of all results: 30 ^ 60 ^ 50 ^ 120 = 42
 */

public class j05XorPairSum {

    public static void main(String args[]) {
        // Example input
        int[] arr = new int[] { 10, 20, 30, 40, 50, 60, 70, 80 };

        // Call the solution and print the result
        System.out.println(getXorPairSum(arr));
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - For each element in the array, we compute the XOR between the element and its double (2 * element).
     * - We then accumulate the results by XORing them together.
     * - The XOR operation ensures that each bit position behaves independently, so it is efficient for large numbers.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the array. We iterate through the array once, performing constant-time operations (XOR and multiplication).
     * 
     * Space Complexity:
     * - O(1), as we only need a constant amount of extra space to store the sum.
     * 
     * @param arr The input array of integers.
     * @return The XOR sum of each pair (arr[i], 2 * arr[i]).
     */
    public static int getXorPairSum(int[] arr) {
        int sum = 0; // Initialize sum to 0
        for (int i = 0; i < arr.length; i++) {
            sum ^= 2 * arr[i]; // XOR each element with its double
        }
        return sum;
    }
}
