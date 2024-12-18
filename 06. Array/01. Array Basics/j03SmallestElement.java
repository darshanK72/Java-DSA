/**
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, find the index of the smallest element in the array.
 *     Additionally, implement a recursive function to find the smallest element in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The index of the smallest element in the array.
 *     - The smallest element in the array using recursion.
 * 
 * Example:
 *     Input:
 *     5
 *     3 1 4 5 2
 *     Output:
 *     1
 *     1
 *     Explanation:
 *     - The smallest element in the array is 1, which is located at index 1.
 *     - The smallest element is 1, and the recursive function returns it.
 * 
 */

import java.util.Scanner;

public class j03SmallestElement {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call solution methods
        System.out.println(minElementIndex(arr)); // Find index of the smallest element
        System.out.println(minElementOfArray(arr, 0)); // Find the smallest element using recursion

        in.close();
    }

    /**
     * Approach 1: Linear Search (Iterative) for Index of Minimum Element
     * 
     * Intuition:
     * - Traverse the array to find the index of the smallest element.
     * - Track the current minimum value and its index during the traversal.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array.
     * 
     * Space Complexity:
     * - O(1), since only a constant amount of space is used.
     * 
     * @param arr The input array of numbers.
     * @return The index of the smallest element in the array.
     */
    public static int minElementIndex(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[index]) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Approach 2: Recursive Search for Minimum Element
     * 
     * Intuition:
     * - Use recursion to find the smallest element in the array by comparing the current element with the smallest element found in the rest of the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, since the function must visit each element once.
     * 
     * Space Complexity:
     * - O(n), due to recursive call stack.
     * 
     * @param arr The input array of numbers.
     * @param index The current index to check.
     * @return The smallest element in the array starting from the given index.
     */
    public static int minElementOfArray(int[] arr, int index) {
        if (index == arr.length - 1)
            return arr[index]; // Base case: last element
        int min = minElementOfArray(arr, index + 1); // Recurse to find min in the rest of the array
        if (min < arr[index])
            return min; // Return the smaller of current element and min from recursion
        return arr[index]; // Return the current element if it's smaller
    }
}
