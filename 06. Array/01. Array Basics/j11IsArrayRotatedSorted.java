/*-
 * Problem Statement:
 * 
 *     Determine whether a given array is a rotated version of a non-decreasing sorted array. 
 *     A rotated sorted array is obtained by taking a sorted array and shifting some of its 
 *     leading elements to the end, while maintaining the order of elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^5 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - A boolean value `true` if the array is a rotated sorted array, and `false` otherwise.
 * 
 * Example:
 *     Input:
 *         n = 5
 *         arr = [3, 4, 5, 1, 2]
 *     Output:
 *         true
 * 
 *     Explanation:
 *         - The array [3, 4, 5, 1, 2] can be obtained by rotating the sorted array [1, 2, 3, 4, 5].
 * 
 *     Input:
 *         n = 5
 *         arr = [3, 4, 1, 5, 2]
 *     Output:
 *         false
 * 
 *     Explanation:
 *         - The array [3, 4, 1, 5, 2] cannot be obtained by rotating any sorted array.
 */

import java.util.Scanner;

public class j11IsArrayRotatedSorted {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        // Check if the array is rotated and sorted
        System.out.println(isArrayRotatedSorted(arr));

        in.close();
    }

    /*-
     * Approach: Check Rotated Sorted Array
     * 
     * Intuition:
     * - A rotated sorted array has at most one "rotation point" where the order of elements breaks 
     *   (i.e., an element is greater than the next one).
     * - The key idea is that after the rotation, all elements should be ordered except for one transition 
     *   from the last element back to the first element.
     * - By counting such "rotation points", we can determine if the array is rotated sorted:
     *   - If there are no rotation points, the array is sorted.
     *   - If there is exactly one rotation point, the array is rotated sorted.
     *   - If there are more than one rotation points, the array is neither sorted nor rotated sorted.
     * 
     * Explanation:
     * 1. Traverse the array, and for each element `arr[i]`, compare it with `arr[(i + 1) % n]`.
     * 2. If `arr[i] > arr[(i + 1) % n]`, increment the `rotate` counter.
     * 3. After traversal, check the value of `rotate`:
     *    - If `rotate <= 1`, the array is rotated sorted.
     *    - Otherwise, it is not rotated sorted.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the array, as we traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param arr The input array to check.
     * @return true if the array is a rotated sorted array, false otherwise.
     */
    public static boolean isArrayRotatedSorted(int[] arr) {
        int rotate = 0; // Counter for rotation points

        // Traverse the array to count rotation points
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[(i + 1) % arr.length]) { // Compare current with next (circular)
                rotate++;
            }
        }

        // A rotated sorted array has at most one rotation point
        return rotate <= 1;
    }
}
