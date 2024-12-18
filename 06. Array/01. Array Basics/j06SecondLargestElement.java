/**
 * Problem Statement:
 * 
 *     Given an array of integers, `arr[]`, find the index of the second largest element in the array.
 *     If there is no second largest element (i.e., the array has less than two distinct elements), return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr[]` of size `n`, where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The index of the second largest element in the array. If no such element exists, return -1.
 * 
 * Example:
 *     Input:
 *     5
 *     2 3 1 4 4
 *     Output:
 *     1
 *     Explanation:
 *     - The largest element is 4, and the second largest element is 3 (at index 1).
 * 
 *     Input:
 *     3
 *     5 5 5
 *     Output:
 *     -1
 *     Explanation:
 *     - Since all elements are the same, there is no second largest element.
 * 
 */

import java.util.Scanner;

public class j06SecondLargestElement {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Call the solution method
        System.out.println(maxElement(arr)); // Find the index of the second largest element

        in.close();
    }

    /**
     * Approach 1: Find the Second Largest Element (Iterative Approach)
     * 
     * Intuition:
     * - Traverse the array to keep track of the largest and second largest elements.
     * - If the current element is greater than the largest element, update the second largest element.
     * - If it is smaller than the largest but greater than the second largest, update the second largest element.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array. We scan the array once.
     * 
     * Space Complexity:
     * - O(1), since only a constant amount of space is used for the `maxIndex` and `secMaxIndex` variables.
     * 
     * @param arr The input array of numbers.
     * @return The index of the second largest element in the array. If no such element exists, return -1.
     */
    public static int maxElement(int[] arr) {
        int maxIndex = 0;
        int secMaxIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                secMaxIndex = maxIndex; // Update second largest
                maxIndex = i; // Update largest
            } else if (arr[i] < arr[maxIndex]) {
                if (secMaxIndex == -1 || arr[i] > arr[secMaxIndex]) {
                    secMaxIndex = i; // Update second largest
                }
            }
        }
        return secMaxIndex; // Return index of second largest, or -1 if no second largest exists
    }
}
