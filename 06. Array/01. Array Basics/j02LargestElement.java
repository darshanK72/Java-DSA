/*-
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, find the index of the largest element in the array.
 *     Additionally, implement a recursive function to find the largest element in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The index of the largest element in the array.
 *     - The largest element in the array using recursion.
 * 
 * Example:
 *     Input:
 *     5
 *     3 1 4 5 2
 *     Output:
 *     3
 *     5
 *     Explanation:
 *     - The largest element in the array is 5, which is located at index 3.
 *     - The largest element is 5, and the recursive function returns it.
 * 
 */

import java.util.Scanner;

public class j02LargestElement {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call solution methods
        System.out.println(maxElementIndex(arr)); // Find index of the largest element
        System.out.println(maxElementOfArray(arr, 0)); // Find the largest element using recursion

        in.close();
    }

    /*-
     * Approach 1: Linear Search (Iterative) for Index of Maximum Element
     * 
     * Intuition:
     * - Traverse the array to find the index of the largest element.
     * - Track the current maximum value and its index during the traversal.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(1), since only a constant amount of space is used.
     * 
     * @param arr The input array of numbers.
     * @return The index of the largest element in the array.
     */
    public static int maxElementIndex(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[index]) {
                index = i;
            }
        }
        return index;
    }

    /*-
     * Approach 2: Recursive Search for Maximum Element
     * 
     * Intuition:
     * - Use recursion to find the largest element in the array by comparing the current element with the largest element found in the rest of the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, since the function must visit each element once.
     * 
     * Space Complexity:
     * - O(n), due to recursive call stack.
     * 
     * @param arr The input array of numbers.
     * @param index The current index to check.
     * @return The largest element in the array starting from the given index.
     */
    public static int maxElementOfArray(int[] arr, int index) {
        if (index == arr.length - 1)
            return arr[index]; // Base case: last element
        int max = maxElementOfArray(arr, index + 1); // Recurse to find max in the rest of the array
        if (max > arr[index])
            return max; // Return the larger of current element and max from recursion
        return arr[index]; // Return the current element if it's larger
    }
}
