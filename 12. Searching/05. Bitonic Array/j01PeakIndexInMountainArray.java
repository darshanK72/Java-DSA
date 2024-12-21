/*-
 * Problem Statement:
 * 
 *     Given a mountain array `arr`, where:
 *     - arr[i] < arr[i+1] for 0 <= i < peakIndex
 *     - arr[i] > arr[i+1] for peakIndex < i < arr.length - 1
 *     - There is exactly one peak in the array.
 * 
 *     The task is to find the index of the peak element.
 * 
 * Input:
 *     - An integer `n` (3 <= n <= 10^5), representing the size of the array.
 *     - A list `arr` of size `n`, where 3 <= arr[i] <= 10^6.
 * 
 * Output:
 *     - An integer representing the index of the peak element.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 5 4 2
 *     Output:
 *     2
 *     Explanation: The peak element is at index 2 (value 5).
 * 
 *     Input:
 *     7
 *     0 2 3 4 5 3 1
 *     Output:
 *     4
 *     Explanation: The peak element is at index 4 (value 5).
 */

import java.util.Scanner;

public class j01PeakIndexInMountainArray {

    public static void main(String args[]) {
        // Reading the input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the array
        int[] arr = new int[n];

        // Reading the array elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input each element of the array
        }

        // Call both solution methods
        System.out.println(peakIndexInMountainArray(arr));
        System.out.println(peakIndexInMountainArrayEfficient(arr));
        in.close();
    }

    /*-
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - In this approach, we iterate over the array from the beginning to the second last element.
     * - The first index where `arr[i] > arr[i + 1]` will be the peak, as it indicates that the 
     *   element at `i` is larger than the next one, which satisfies the condition of the peak element.
     * 
     * Time Complexity:
     * - O(n), as we are performing a linear scan of the array.
     * 
     * Space Complexity:
     * - O(1), using constant space.
     * 
     * @param arr The input array of numbers.
     * @return The index of the peak element.
     */
    public static int peakIndexInMountainArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1; // Return -1 if no peak is found (this case won't happen as per problem
                   // statement).
    }

    /*-
     * Approach 2: Binary Search Approach (Efficient)
     * 
     * Intuition:
     * - The array is guaranteed to have exactly one peak. This suggests that we can use binary 
     *   search to find the peak element efficiently.
     * - In this approach, we maintain two pointers `s` (start) and `e` (end) and find the middle element `mid`.
     * - If `arr[mid] < arr[mid + 1]`, it means the peak lies on the right side, so we move `s` to `mid + 1`.
     * - If `arr[mid] > arr[mid - 1]`, it means the peak lies on the left side, so we move `e` to `mid - 1`.
     * - If neither of the conditions holds, we have found the peak element at index `mid`.
     * 
     * Time Complexity:
     * - O(log n), as we are halving the search space in each step.
     * 
     * Space Complexity:
     * - O(1), using constant space.
     * 
     * @param arr The input array of numbers.
     * @return The index of the peak element.
     */
    public static int peakIndexInMountainArrayEfficient(int[] arr) {
        int s = 0;
        int e = arr.length - 1;

        // Perform binary search
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] < arr[mid + 1]) {
                // If the middle element is less than the next, the peak is on the right side
                s = mid + 1;
            } else if (arr[mid] < arr[mid - 1]) {
                // If the middle element is less than the previous, the peak is on the left side
                e = mid - 1;
            } else {
                // If neither of the above, mid is the peak
                return mid;
            }
        }
        return e; // Return the peak index when the binary search concludes
    }
}
