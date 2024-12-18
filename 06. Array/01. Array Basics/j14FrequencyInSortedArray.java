/**
 * Problem Statement:
 *     Given a sorted integer array `arr` of size `n`, print the frequency of each unique element in the array.
 *     The array is sorted in non-decreasing order.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An integer array `arr` of size `n` where (-10^5 <= arr[i] <= 10^5).
 *
 * Output:
 *     - For each unique element in the array, print the element followed by its frequency.
 *       Each element-frequency pair should be printed on a new line.
 *
 * Example:
 *     Input:
 *         n = 9
 *         arr = [10, 10, 10, 20, 20, 30, 30, 30, 30]
 *     Output:
 *         10 3
 *         20 2
 *         30 4
 *
 *     Explanation:
 *         The frequency of `10` is 3, the frequency of `20` is 2, and the frequency of `30` is 4.
 *
 *     Input:
 *         n = 5
 *         arr = [1, 1, 1, 1, 1]
 *     Output:
 *         1 5
 *
 *     Explanation:
 *         The frequency of `1` is 5.
 */

import java.util.Scanner;

public class j14FrequencyInSortedArray {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        System.out.println("Approach 1:");
        frequency(arr); // Call method to calculate and print frequencies

        System.out.println("Approach 2 (Alternative):");
        frequencyAlternative(arr); // Call alternative approach
        in.close();
    }

    /**
     * Approach 1:
     *     This approach uses a single traversal of the array to compute the frequency of each unique element.
     *     - Start from the first element and initialize a frequency counter `f` to 1.
     *     - Traverse the array, and if the current element is equal to the previous one, increment the counter.
     *     - When a different element is encountered, print the previous element and its frequency,
     *       reset the counter, and continue.
     *     - Handle the edge case for the last unique element after the loop terminates.
     *
     * Intuition:
     *     Since the array is sorted, all duplicates of an element will appear consecutively.
     *     This allows us to use a single loop to efficiently count and print frequencies.
     *
     * Time Complexity:
     *     O(n), where `n` is the size of the array. We traverse the array once.
     *
     * Space Complexity:
     *     O(1), as we use only a constant amount of extra space for counters and variables.
     *
     * @param arr The input sorted array.
     */
    public static void frequency(int[] arr) {
        int f = 1; // Frequency counter
        int i = 1; // Pointer to traverse the array
        while (i < arr.length) {
            // Count frequency of consecutive duplicate elements
            while (i < arr.length && arr[i] == arr[i - 1]) {
                f++;
                i++;
            }
            // Print the element and its frequency
            System.out.println(arr[i - 1] + " " + f);
            f = 1; // Reset frequency counter
            i++;
        }
        // Handle the last unique element (or single-element edge case)
        if (arr.length == 1 || arr[arr.length - 1] != arr[arr.length - 2]) {
            System.out.println(arr[arr.length - 1] + " " + f);
        }
    }

    /**
     * Approach 2 (Alternative):
     *     In this approach, we use a for loop to traverse the array and detect changes in elements.
     *     - Maintain a `start` pointer to track the first position of each unique element.
     *     - When a change in element is encountered, calculate the frequency as `i - start`.
     *     - Print the element and its frequency, then update `start` to the current position.
     *
     * Intuition:
     *     By tracking the `start` position of each unique element, we can directly compute the frequency
     *     as the difference between the current index and the `start` index.
     *
     * Time Complexity:
     *     O(n), where `n` is the size of the array. We traverse the array once.
     *
     * Space Complexity:
     *     O(1), as we use a constant amount of extra space.
     *
     * @param arr The input sorted array.
     */
    public static void frequencyAlternative(int[] arr) {
        int start = 0; // Start pointer for each unique element
        for (int i = 1; i <= arr.length; i++) {
            if (i == arr.length || arr[i] != arr[start]) {
                System.out.println(arr[start] + " " + (i - start));
                start = i; // Update start to the current position
            }
        }
    }
}
