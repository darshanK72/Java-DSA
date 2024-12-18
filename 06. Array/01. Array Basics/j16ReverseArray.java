/**
 * Problem Statement:
 *     Given an integer array `arr` of size `n`, reverse the array.
 *     Implement this in three different ways:
 *       1. Using extra space (naive approach).
 *       2. In-place using an iterative approach.
 *       3. In-place using a recursive approach.
 *
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the size of the array.
 *     - An integer array `arr` of size `n` where (-10^5 <= arr[i] <= 10^5).
 *
 * Output:
 *     - Print the reversed array.
 *
 * Example:
 *     Input:
 *         n = 5
 *         arr = [1, 2, 3, 4, 5]
 *     Output:
 *         Original Array: [1, 2, 3, 4, 5]
 *         Reversed (Naive): [5, 4, 3, 2, 1]
 *         Reversed (In-Place Iterative): [5, 4, 3, 2, 1]
 *         Reversed (In-Place Recursive): [5, 4, 3, 2, 1]
 *
 *     Explanation:
 *         The array is reversed such that the first element becomes the last, and so on.
 */

import java.util.Scanner;
import java.util.Arrays;

public class j16ReverseArray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Input: size of the array
        int n = in.nextInt();
        int[] arr = new int[n];

        // Input: array elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println("Original Array: " + Arrays.toString(arr));

        // Naive Approach: Using extra space
        int[] reversedNaive = reverseArray(arr);
        System.out.println("Reversed (Naive): " + Arrays.toString(reversedNaive));

        // Optimized Approach 1: In-place using iteration
        reverseArrayInplace(arr); // In-place modifies the array
        System.out.println("Reversed (In-Place Iterative): " + Arrays.toString(arr));

        // Reset array to original order for recursion test
        reverseArrayInplace(arr); // Reversing back to original
        reverseArrayInplaceRec(arr, 0);
        System.out.println("Reversed (In-Place Recursive): " + Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach 1: Naive Solution (Using Extra Space)
     * 
     * Intuition:
     *     Create a new array and fill it by iterating the input array in reverse order.
     * 
     * Steps:
     *     - Create an empty array `output` of the same size as the input.
     *     - Copy the elements from the input array to `output` in reverse order.
     * 
     * Time Complexity:
     *     O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     *     O(n), as we use an extra array to store the reversed result.
     * 
     * @param arr The input array.
     * @return A new array containing the reversed elements.
     */
    public static int[] reverseArray(int[] arr) {
        int[] output = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            output[i] = arr[arr.length - i - 1];
        }
        return output;
    }

    /**
     * Approach 2: In-place Solution (Iterative Approach)
     * 
     * Intuition:
     *     Use two pointers, one starting from the beginning and the other from the end.
     *     Swap elements until the pointers meet at the center.
     * 
     * Steps:
     *     - Initialize two pointers: `s` at the start and `e` at the end of the array.
     *     - Swap elements at `s` and `e` and move the pointers toward the center.
     *     - Stop when `s` >= `e`.
     * 
     * Time Complexity:
     *     O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     *     O(1), as the reversal is done in place using constant space.
     * 
     * @param arr The input array to reverse in place.
     */
    public static void reverseArrayInplace(int[] arr) {
        int s = 0; // Start pointer
        int e = arr.length - 1; // End pointer
        while (s < e) {
            swap(arr, s, e);
            s++;
            e--;
        }
    }

    /**
     * Approach 3: In-place Solution (Recursive Approach)
     * 
     * Intuition:
     *     Reverse the array by breaking the problem into subproblems:
     *     - Swap the first and last elements.
     *     - Recursively reverse the remaining array excluding the first and last elements.
     * 
     * Steps:
     *     - Base Case: Stop when the recursion index `i` exceeds half the array length.
     *     - Recursive Step: Swap the elements at `i` and `n - i - 1` and recurse for the next index.
     * 
     * Time Complexity:
     *     O(n), where `n` is the size of the array. Each recursive call processes one pair of elements.
     * 
     * Space Complexity:
     *     O(n) due to the recursion stack space.
     * 
     * @param arr The input array to reverse in place.
     * @param i   The current index being processed in the recursion.
     */
    public static void reverseArrayInplaceRec(int[] arr, int i) {
        if (i >= arr.length / 2)
            return; // Base case: stop when `i` reaches halfway
        swap(arr, i, arr.length - i - 1);
        reverseArrayInplaceRec(arr, i + 1); // Recursive call for the next index
    }

    /**
     * Helper Method: Swap two elements in the array.
     *
     * @param arr The input array.
     * @param i   Index of the first element.
     * @param j   Index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
