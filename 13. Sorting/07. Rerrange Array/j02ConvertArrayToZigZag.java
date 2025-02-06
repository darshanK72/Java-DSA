/**
 * Problem Statement:
 * 
 *     Given an array of integers, rearrange the elements such that they follow a 
 *     zigzag pattern. Specifically, for an array `nums`, the rearranged array 
 *     should satisfy:
 *     
 *     nums[0] < nums[1] > nums[2] < nums[3] > nums[4] ...
 * 
 * Input:
 *     - An integer array `nums` of size `n` (1 <= n <= 10^4)
 *     - Each element satisfies (1 <= nums[i] <= 10^5)
 * 
 * Output:
 *     - The array should be modified in place to satisfy the zigzag property.
 * 
 * Example:
 *     Input:
 *         nums = [3, 5, 2, 1, 6, 4]
 *     Output:
 *         [3, 5, 1, 6, 2, 4] (or any valid zigzag pattern)
 * 
 *     Explanation:
 *         The elements are rearranged such that they follow the required pattern:
 *         nums[0] < nums[1] > nums[2] < nums[3] > nums[4] ...
 */

import java.util.Arrays;

public class j02ConvertArrayToZigZag {

    public static void main(String[] args) {
        // Test cases for zigZagTwoPointers
        int[] arr1 = { 3, 5, 2, 1, 6, 4 };
        zigZagTwoPointers(arr1);
        System.out.println("zigZagTwoPointers Test 1: " + Arrays.toString(arr1));

        int[] arr2 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        zigZagTwoPointers(arr2);
        System.out.println("zigZagTwoPointers Test 2: " + Arrays.toString(arr2));

        // Test cases for zigZagEfficient
        int[] arr3 = { 3, 5, 2, 1, 6, 4 };
        zigZagEfficient1(arr3);
        System.out.println("zigZagEfficient Test 1: " + Arrays.toString(arr3));

        int[] arr4 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        zigZagEfficient2(arr4);
        System.out.println("zigZagEfficient Test 2: " + Arrays.toString(arr4));
    }

    /**
     * Approach 1: Two Pointers with Sorting
     * 
     * Intuition:
     * - Sort the array in ascending order.
     * - Use two pointers: one at the smallest element and one at the largest.
     * - Place elements alternatively from both ends to form the zigzag pattern.
     * 
     * Explanation:
     * - Sorting ensures that smaller elements appear before larger elements.
     * - By selecting elements alternatively from both ends, we achieve the required
     *   zigzag property without needing additional swaps.
     * - However, sorting is expensive and requires extra space.
     * 
     * Time Complexity:
     * - O(n log n) due to sorting.
     * 
     * Space Complexity:
     * - O(n) for the temporary array.
     * 
     * @param arr The input array of numbers.
     */
    public static void zigZagTwoPointers(int[] arr) {
        Arrays.sort(arr);
        int s = 0;
        int e = arr.length - 1;
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            temp[i] = arr[i];
        int k = 0;
        while (s <= e) {
            if (k % 2 == 0)
                arr[k++] = temp[s++];
            else
                arr[k++] = temp[e--];
        }
    }

    /**
     * Approach 2: In-Place Swap (Method 1)
     * 
     * Intuition:
     * - Instead of sorting, we iterate through the array once.
     * - If `i` is even, ensure nums[i] < nums[i+1].
     * - If `i` is odd, ensure nums[i] > nums[i+1].
     * - Swap elements when they are not in the correct position.
     * 
     * Explanation:
     * - We traverse the array and fix each element in a single pass.
     * - Swapping adjacent elements ensures that the entire array follows the 
     *   required zigzag pattern.
     * - This method is much faster since it does not require sorting.
     * 
     * Time Complexity:
     * - O(n) as we traverse the array once.
     * 
     * Space Complexity:
     * - O(1) since sorting is done in-place.
     * 
     * @param arr The input array of numbers.
     */
    public static void zigZagEfficient1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0 && arr[i] > arr[i + 1] || i % 2 == 1 && arr[i] < arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }

    /**
     * Approach 3: In-Place Swap (Method 2) - Optimized
     * 
     * Intuition:
     * - Iterate through the array with a step of 2.
     * - For each element at index `i`, check its left and right neighbors.
     * - If the element at `i` is greater than its left neighbor, swap them.
     * - Similarly, if the element at `i` is smaller than its right neighbor, swap them.
     * 
     * Explanation:
     * - This approach works by optimizing the swap conditions based on the surrounding neighbors.
     * - By iterating in steps of 2, we ensure that each pair is fixed independently.
     * - This is more efficient in handling corner cases like the first or last element of the array.
     * 
     * Time Complexity:
     * - O(n) as we traverse the array once.
     * 
     * Space Complexity:
     * - O(1) as all changes are made in-place.
     * 
     * @param arr The input array of numbers.
     */
    public static void zigZagEfficient2(int[] arr) {
        for (int i = 0; i < arr.length; i += 2) {
            if (i - 1 >= 0 && arr[i - 1] < arr[i]) {
                int temp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = temp;
            }
            if (i + 1 < arr.length && arr[i + 1] < arr[i]) {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
    }

}
