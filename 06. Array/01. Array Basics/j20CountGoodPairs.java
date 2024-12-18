/**
 * Problem Statement:
 * 
 *     Given an array `nums`, return the number of good pairs in the array. 
 *     A pair `(i, j)` is called good if `nums[i] == nums[j]` and `i < j`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 100).
 * 
 * Output:
 *     - An integer representing the count of good pairs in the array.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 1 1
 * 
 *     Output:
 *     3
 * 
 *     Explanation:
 *     - The good pairs are (0, 3), (0, 4), and (3, 4).
 *       Therefore, the total count of good pairs is 3.
 */

import java.util.Scanner;

public class j20CountGoodPairs {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        // Brute Force Solution
        System.out.println("Brute Force Solution (Count of Good Pairs): " + countIdenticalPairs(arr));

        // Optimized Solution
        System.out.println("Optimized Solution (Count of Good Pairs): " + countIdenticalPairsEfficient(arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force (Nested Loops)
     * 
     * Intuition:
     * - In this approach, we use two nested loops to compare all pairs `(i, j)` where `i < j`.
     * - If the two elements `nums[i]` and `nums[j]` are equal, we increment the count.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the array. This is because we compare all pairs.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param nums The input array of integers.
     * @return The count of good pairs.
     */
    public static int countIdenticalPairs(int[] nums) {
        int count = 0; // Initialize the count of good pairs
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Optimized Counting Using Frequency Array
     * 
     * Intuition:
     * - Instead of comparing all pairs, we can leverage the frequency of each number.
     * - For each element `nums[i]`, the count of good pairs involving `nums[i]` is equal 
     *   to the number of occurrences of `nums[i]` seen so far.
     * - We use a frequency array to keep track of how many times each number has appeared.
     * - If a number `x` has appeared `f` times before, then adding another `x` contributes
     *   `f` new good pairs.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We process each element once.
     * 
     * Space Complexity:
     * - O(101) ~ O(1), as we use a frequency array of fixed size (max value of nums[i] is 100).
     * 
     * @param nums The input array of integers.
     * @return The count of good pairs.
     */
    public static int countIdenticalPairsEfficient(int[] nums) {
        int count = 0; // Initialize the count of good pairs
        int[] frequency = new int[101]; // Frequency array to count occurrences of numbers
        for (int i = 0; i < nums.length; i++) {
            count += frequency[nums[i]]; // Add the count of occurrences of nums[i] seen so far
            frequency[nums[i]]++; // Update the frequency of nums[i]
        }
        return count;
    }
}
