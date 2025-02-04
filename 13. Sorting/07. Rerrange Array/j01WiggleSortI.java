/*-
 * Problem Statement:
 * 
 *     Given an array of integers, rearrange the elements such that they follow a 
 *     wiggle pattern. Specifically, for an array `nums`, the rearranged array 
 *     should satisfy:
 *     
 *     nums[0] <= nums[1] >= nums[2] <= nums[3] >= nums[4] ...
 * 
 * Input:
 *     - An integer array `nums` of size `n` (1 <= n <= 10^4)
 *     - Each element satisfies (1 <= nums[i] <= 10^5)
 * 
 * Output:
 *     - The array should be modified in place to satisfy the wiggle property.
 * 
 * Example:
 *     Input:
 *         nums = [3, 5, 2, 1, 6, 4]
 *     Output:
 *         [3, 5, 1, 6, 2, 4] (or any valid wiggle pattern)
 * 
 *     Explanation:
 *         The elements are rearranged such that they follow the required pattern:
 *         nums[0] <= nums[1] >= nums[2] <= nums[3] >= nums[4] ...
 */

import java.util.Arrays;

public class j01WiggleSortI {

    public static void main(String[] args) {
        // Test cases for wiggleSortTwoPointers
        int[] arr1 = { 3, 5, 2, 1, 6, 4 };
        wiggleSortTwoPointers(arr1);
        System.out.println("wiggleSortTwoPointers Test 1: " + Arrays.toString(arr1));

        int[] arr2 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        wiggleSortTwoPointers(arr2);
        System.out.println("wiggleSortTwoPointers Test 2: " + Arrays.toString(arr2));

        // Test cases for wiggleSortEfficient
        int[] arr3 = { 3, 5, 2, 1, 6, 4 };
        wiggleSortEfficient(arr3);
        System.out.println("wiggleSortEfficient Test 1: " + Arrays.toString(arr3));

        int[] arr4 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        wiggleSortEfficient(arr4);
        System.out.println("wiggleSortEfficient Test 2: " + Arrays.toString(arr4));

        // Test cases for wiggleSortDivideReminderForLongs
        long[] arr5 = { 3, 5, 2, 1, 6, 4 };
        wiggleSortDivideReminderForLongs(arr5);
        System.out.println("wiggleSortDivideReminder Test 1: " + Arrays.toString(arr5));

        long[] arr6 = { 9, 1, 8, 2, 7, 3, 6, 4, 5 };
        wiggleSortDivideReminderForLongs(arr6);
        System.out.println("wiggleSortDivideReminder Test 2: " + Arrays.toString(arr6));
    }

    /*-
     * Approach 1: Two Pointers with Sorting
     * 
     * Intuition:
     * - Sort the array in ascending order.
     * - Use two pointers: one at the smallest element and one at the largest.
     * - Place elements alternatively from both ends to form the wiggle pattern.
     * 
     * Explanation:
     * - Sorting ensures that smaller elements appear before larger elements.
     * - By selecting elements alternatively from both ends, we achieve the required
     *   wiggle property without needing additional swaps.
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
    public static void wiggleSortTwoPointers(int[] arr) {
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

    /*-
     * Approach 2: In-Place Efficient Swap
     * 
     * Intuition:
     * - Instead of sorting, we iterate through the array once.
     * - If `i` is even, ensure nums[i] <= nums[i+1].
     * - If `i` is odd, ensure nums[i] >= nums[i+1].
     * - Swap elements when they are not in the correct position.
     * 
     * Explanation:
     * - We traverse the array and fix each element in a single pass.
     * - Swapping adjacent elements ensures that the entire array follows the 
     *   required pattern.
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
    public static void wiggleSortEfficient(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0 && arr[i] > arr[i + 1] || i % 2 == 1 && arr[i] < arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }

    /*-
     * Approach 3: Encoding Method (For Large Numbers)
     * 
     * Intuition:
     * - Uses modular encoding to store the new value alongside the original value.
     * - Sort the array, then rearrange elements using modular encoding to keep 
     *   track of old values.
     * - Extract the final values by division.
     * 
     * Explanation:
     * - Instead of storing elements in a separate array, we encode both the original
     *   and new values into the same variable using modular arithmetic.
     * - This prevents overwriting values while still using constant extra space.
     * - After all assignments, we extract the modified values by division.
     * 
     * Time Complexity:
     * - O(n log n) due to sorting.
     * 
     * Space Complexity:
     * - O(1) since modifications are in-place.
     * 
     * @param arr The input array of numbers.
     */
    public static void wiggleSortDivideReminderForLongs(long[] arr) {
        Arrays.sort(arr);
        int s = 0;
        int e = arr.length - 1;
        long mod = (long) 10e8 + 1;
        for (int i = 0; i < arr.length; i++) {
            long oldVal = arr[i];
            long newVal = (i % 2 == 0) ? arr[s++] : arr[e--];
            arr[i] = oldVal + (newVal % mod) * mod;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / mod;
        }
    }

}
