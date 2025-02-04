/*-
 * Problem Statement:
 * 
 *     Given an array of positive integers, rearrange the array elements 
 *     alternatively such that:
 *     
 *     - The first element is the maximum value in the array.
 *     - The second element is the minimum value in the array.
 *     - The third element is the second maximum value.
 *     - The fourth element is the second minimum value.
 *     - And so on.
 *     
 *     The rearranged array should be modified in-place without using any extra space.
 *     
 * Input:
 *     - An integer array `arr[]` of size `n` (1 <= n <= 10^4).
 *     - Each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - Modify the original array `arr[]` in-place to follow the pattern described above.
 * 
 * Example:
 *     Input:
 *         arr = [3, 5, 2, 1, 6, 4]
 *     Output:
 *         arr = [6, 1, 5, 2, 4, 3]
 * 
 *     Explanation:
 *         The array is rearranged to follow the zigzag pattern where 
 *         the largest elements appear at even indices and the smallest 
 *         elements appear at odd indices.
 */

import java.util.Arrays;

public class j03RearrangeArrayAlternatively {

    public static void main(String[] args) {
        // Test cases for rearrange method
        int[] arr1 = { 3, 5, 2, 1, 6, 4 };
        rearrange(arr1);
        System.out.println("Rearrange Test 1: " + Arrays.toString(arr1));

        int[] arr2 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        rearrange(arr2);
        System.out.println("Rearrange Test 2: " + Arrays.toString(arr2));

        int[] arr3 = { 3, 5, 2, 1, 6, 4 };
        rearrange(arr3);
        System.out.println("Rearrange Test 3: " + Arrays.toString(arr3));

        int[] arr4 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        rearrange(arr4);
        System.out.println("Rearrange Test 4: " + Arrays.toString(arr4));
    }

    /*-
     * Approach: Sorting with Two Pointers
     * 
     * Intuition:
     * - First, we sort the array in ascending order.
     * - Use two pointers: one pointing to the smallest element (`s`) and 
     *   one pointing to the largest element (`e`).
     * - Fill the array alternatively by placing the largest element at 
     *   even indices and the smallest element at odd indices.
     * 
     * Explanation:
     * - Sorting the array gives a clear order of the elements from smallest 
     *   to largest.
     * - The two pointers method ensures we select elements alternately 
     *   from the largest and smallest remaining values.
     * - This approach is not optimal because of the sorting step.
     * 
     * Time Complexity:
     * - O(n log n) due to sorting.
     * 
     * Space Complexity:
     * - O(n) for the temporary array used to store sorted elements.
     * 
     * @param arr The input array of numbers.
     */
    public static void rearrange(int arr[]) {
        Arrays.sort(arr); // Sort the array first
        int s = 0; // Pointer for smallest element
        int e = arr.length - 1; // Pointer for largest element
        int[] temp = new int[arr.length]; // Temporary array to store sorted elements

        for (int i = 0; i < arr.length; i++)
            temp[i] = arr[i]; // Copy sorted elements into temp array

        int k = 0;
        while (s <= e) {
            if (k % 2 == 0) { // Even indices
                arr[k++] = temp[e--]; // Place largest element at even index
            } else { // Odd indices
                arr[k++] = temp[s++]; // Place smallest element at odd index
            }
        }
    }

}
