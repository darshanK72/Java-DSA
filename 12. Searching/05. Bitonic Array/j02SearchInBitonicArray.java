/**
 * Problem Statement:
 * 
 *     You are given a bitonic array, which is an array that first increases and then decreases. 
 *     A bitonic array has the following properties:
 *     - It first strictly increases to a maximum element (the peak) and then strictly decreases after that element.
 * 
 *     Given a bitonic array `A` and a target value `B`, the task is to find the index of `B` in the array. 
 *     If the target is not found, return -1.
 * 
 * Input:
 *     - An integer `n` (3 <= n <= 10^5), representing the size of the array.
 *     - A list `A` of size `n`, where each element satisfies (1 <= A[i] <= 10^6).
 *     - An integer `B` (1 <= B <= 10^6), the target value to search for.
 * 
 * Output:
 *     - An integer representing the index of the target element `B` if found, otherwise return -1.
 * 
 * Example:
 *     Input:
 *     5
 *     1 3 8 12 4
 *     4
 *     Output:
 *     4
 *     Explanation: The target `4` is found at index 4.
 * 
 *     Input:
 *     7
 *     1 3 8 12 9 5 2
 *     5
 *     Output:
 *     5
 *     Explanation: The target `5` is found at index 5.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j02SearchInBitonicArray {

    public static void main(String args[]) {
        // Reading the input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the array
        ArrayList<Integer> arr = new ArrayList<>();

        // Reading the array elements
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt()); // Input each element of the array
        }

        int target = in.nextInt(); // The target element to search for
        System.out.println(search(arr, target)); // Call the search method to find the target
        in.close();
    }

    /**
     * Approach 1: Find the Pivot, then Perform Binary Search
     * 
     * Intuition:
     * - The bitonic array has a single peak. First, we need to find the peak (pivot) element, 
     *   which divides the array into two parts: an increasing part and a decreasing part.
     * - Once we find the pivot, we can perform binary search on the increasing part and binary 
     *   search on the decreasing part to find the target element.
     * - Finding the peak element is done using a variant of binary search.
     * - Once the pivot is found, we perform two separate binary searches: one in the increasing part
     *   (from 0 to the pivot) and one in the decreasing part (from pivot + 1 to the end).
     * 
     * Time Complexity:
     * - O(log n) to find the pivot element, and O(log n) for two binary searches.
     * - Overall time complexity: O(log n).
     * 
     * Space Complexity:
     * - O(1), using constant space for the operations.
     * 
     * @param A The input bitonic array.
     * @param B The target element to search for.
     * @return The index of the target element, or -1 if not found.
     */
    public static int search(ArrayList<Integer> A, int B) {
        int s = 0;
        int e = A.size() - 1;
        int pivot = -1;

        // Find the pivot (peak) element using binary search
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (A.get(mid) < A.get(mid + 1)) {
                s = mid + 1; // If the mid element is less than the next, peak is on the right
            } else if (A.get(mid) < A.get(mid - 1)) {
                e = mid - 1; // If the mid element is less than the previous, peak is on the left
            } else {
                pivot = mid; // The mid element is the peak
                break;
            }
        }

        // Search for the target in the increasing part (left of the pivot)
        int index = binarySearch(A, B, 0, pivot);

        // If not found in the increasing part, search in the decreasing part (right of
        // the pivot)
        if (index == -1) {
            return binarySearch(A, B, pivot + 1, A.size() - 1);
        } else {
            return index;
        }
    }

    /**
     * Helper Method: Binary Search
     * 
     * Intuition:
     * - A normal binary search is performed here. Depending on whether the array part is increasing 
     *   or decreasing, the comparison is done differently.
     * - For the increasing part, we do normal binary search. For the decreasing part, we reverse the 
     *   comparison to search the array in descending order.
     * 
     * Time Complexity:
     * - O(log n), as it's a standard binary search.
     * 
     * Space Complexity:
     * - O(1), using constant space for the operations.
     * 
     * @param A The input bitonic array.
     * @param B The target element to search for.
     * @param s The starting index for the search.
     * @param e The ending index for the search.
     * @return The index of the target element, or -1 if not found.
     */
    public static int binarySearch(ArrayList<Integer> A, int B, int s, int e) {
        boolean isAscending = A.get(s) < A.get(e); // Check if the array is ascending or descending

        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (A.get(mid) == B) {
                return mid; // Target found
            }

            if (isAscending) {
                // Normal binary search for ascending part
                if (A.get(mid) > B) {
                    e = mid - 1;
                } else {
                    s = mid + 1;
                }
            } else {
                // Reverse binary search for descending part
                if (A.get(mid) > B) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }
        }
        return -1; // Target not found
    }
}
