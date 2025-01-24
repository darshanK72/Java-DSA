/**
 * Problem Statement:
 * 
 *      Search for an element `k` in an "infinite sorted array."
 *      Since the array size is unknown, we must find a valid range containing `k` and then perform a binary search within that range.
 * 
 * Approaches:
 *      - Approach 1: Double the range `r` until the target is within the range `[l, r]`.
 *      - Approach 2: Gradually expand the range `[l, r]` by calculating a new range size.
 * 
 * Input:
 *     - `arr`: Infinite sorted array (simulated here as a large array).
 *     - `k`: Target element to search for.
 * 
 * Output:
 *     - Index of the target `k` in the array, or -1 if not found.
 */

public class j03SearchInInfiniteSortedArray {
    public static void main(String[] args) {
        // Driver Code: Simulate an infinite sorted array.
        int[] arr = new int[1000]; // Simulated "infinite" array.
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1; // Fill the array with sequential values [1, 2, ..., 1000].
        }

        int target = 250; // Target to find.

        // Approach 1: Search using the first range expansion strategy.
        int result1 = searchInInfiniteArray1(arr, target);
        System.out.println("Approach 1: Target found at index " + result1);

        // Approach 2: Search using the second range expansion strategy.
        int result2 = searchInInfiniteArray2(arr, target);
        System.out.println("Approach 2: Target found at index " + result2);
    }

    /**
     * Helper Method: Binary Search
     * 
     * Performs binary search on a given range `[l, r]` in the array to find the target `k`.
     * 
     * @param arr Array in which to search.
     * @param l Left boundary of the search range.
     * @param r Right boundary of the search range.
     * @param k Target element to search for.
     * @return Index of `k` if found, or -1 if not found.
     */
    public static int binarySearch(int[] arr, int l, int r, int k) {
        while (l <= r) {
            int mid = l + (r - l) / 2; // Avoid overflow with `l + r` by using `(r - l) / 2`.
            if (arr[mid] == k) {
                return mid; // Target found.
            } else if (arr[mid] > k) {
                r = mid - 1; // Search in the left half.
            } else {
                l = mid + 1; // Search in the right half.
            }
        }
        return -1; // Target not found.
    }

    /**
     * Approach 1: Range Expansion by Doubling
     * 
     * Intuition:
     * - Start with a small range `[l, r]` and double the size of the range until the target is within it.
     * - Once the range `[l, r]` is found, perform binary search within that range.
     * 
     * Steps:
     * 1. Start with `l = 0` and `r = 1`.
     * 2. Double the right boundary `r` until `arr[r] >= k`.
     * 3. Perform binary search in the range `[l, r]`.
     * 
     * Time Complexity:
     * - O(log n) to find the range, where `n` is the index of the target.
     * - O(log n) for binary search.
     * 
     * @param arr Infinite sorted array.
     * @param k Target element to search for.
     * @return Index of `k` if found, or -1 if not found.
     */
    public static int searchInInfiniteArray1(int[] arr, int k) {
        int l = 0;
        int r = 1;

        // Expand the range until the target is within `[l, r]`.
        while (arr[r] < k) {
            l = r; // Update the left boundary.
            r *= 2; // Double the right boundary.
        }

        // Perform binary search in the identified range.
        return binarySearch(arr, l, r, k);
    }

    /**
     * Approach 2: Gradual Range Expansion
     * 
     * Intuition:
     * - Expand the range `[l, r]` more gradually by increasing its size based on the previous range size.
     * - This allows for finer control over the range expansion compared to doubling.
     * 
     * Steps:
     * 1. Start with `l = 0` and `r = 1`.
     * 2. Incrementally expand the range `[l, r]` by calculating a new range size.
     * 3. Perform binary search in the final range `[l, r]`.
     * 
     * Time Complexity:
     * - O(log n) to find the range, where `n` is the index of the target.
     * - O(log n) for binary search.
     * 
     * @param arr Infinite sorted array.
     * @param k Target element to search for.
     * @return Index of `k` if found, or -1 if not found.
     */
    public static int searchInInfiniteArray2(int[] arr, int k) {
        int l = 0;
        int r = 1;

        // Expand the range until the target is within `[l, r]`.
        while (arr[r] < k) {
            int newL = r + 1; // Update left boundary.
            r = r + (r - l + 1) * 2; // Gradually expand the range.
            l = newL; // Update left boundary to the previous right + 1.
        }

        // Perform binary search in the identified range.
        return binarySearch(arr, l, r, k);
    }
}
