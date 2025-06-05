/*-
 * LeetCode 632. Smallest Range Covering Elements from K Lists
 * 
 * Problem Statement:
 *     You have k lists of sorted integers in non-decreasing order. Find the smallest
 *     range that includes at least one number from each of the k lists. We define the
 *     range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 * 
 * Input:
 *     - nums: List of k sorted lists of integers
 *     - Each list is sorted in non-decreasing order
 *     - 1 <= nums.length <= 3500
 *     - -10^5 <= nums[i][j] <= 10^5
 * 
 * Output:
 *     - An array of size 2 representing the smallest range [a,b]
 * 
 * Example:
 *     Input: nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 *     Output: [20,24]
 * 
 *     Explanation:
 *         List 1: [4, 10, 15, 24, 26]
 *         List 2: [0, 9, 12, 20]
 *         List 3: [5, 18, 22, 30]
 *         The smallest range is [20,24] which contains 24 from list 1,
 *         20 from list 2, and 22 from list 3
 */

import java.util.List;
import java.util.PriorityQueue;

public class j16SmallestRangeCoveringElementInKList {

    /*-
     * Approach 1: Brute Force with Pointers
     * 
     * Intuition:
     * - Keep track of current position in each list using pointers
     * - For each iteration, find the minimum element and its list
     * - Move the pointer in the list containing minimum element
     * - Track the minimum range found so far
     * 
     * Explanation:
     * - Step 1: Initialize pointers for each list at index 0
     * - Step 2: Find minimum and maximum elements in current window
     * - Step 3: Update result if current range is smaller
     * - Step 4: Move pointer in list containing minimum element
     * - Step 5: Repeat until we can't move any pointer
     * 
     * Time Complexity: O(n * k) where n is total number of elements and k is number of lists
     * Space Complexity: O(k) for storing pointers
     * 
     * @param nums    List of k sorted lists
     * @return        Array containing smallest range [min, max]
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        int[] arr = new int[k];  // Pointers for each list

        int[] out = new int[2];
        out[0] = 0;
        out[1] = Integer.MAX_VALUE;

        while (true) {
            // Find minimum element and its list index
            int minElement = Integer.MAX_VALUE;
            int maxElement = Integer.MIN_VALUE;
            int minListIndex = -1;
            for (int i = 0; i < k; i++) {
                int element = nums.get(i).get(arr[i]);
                if (element < minElement) {
                    minElement = element;
                    minListIndex = i;
                }

                if (element > maxElement) {
                    maxElement = element;
                }
            }

            // Update result if current range is smaller
            if (maxElement - minElement < out[1] - out[0]) {
                out[0] = minElement;
                out[1] = maxElement;
            }

            // Move pointer in list containing minimum element
            int nextIndex = arr[minListIndex] + 1;
            if (nextIndex >= nums.get(minListIndex).size()) {
                break;
            }

            arr[minListIndex] = nextIndex;
        }
        return out;
    }

    /*-
     * Approach 2: Priority Queue (More Efficient)
     * 
     * Intuition:
     * - Use min heap to efficiently track minimum element
     * - Keep track of maximum element separately
     * - Store list index and element index in heap
     * - Update range when new minimum is found
     * 
     * Explanation:
     * - Step 1: Initialize min heap with first element from each list
     * - Step 2: Track maximum element in current window
     * - Step 3: Remove minimum element and update range if needed
     * - Step 4: Add next element from same list to heap
     * - Step 5: Update maximum element if needed
     * 
     * Time Complexity: O(n log k) where n is total number of elements and k is number of lists
     * Space Complexity: O(k) for the priority queue
     * 
     * @param nums    List of k sorted lists
     * @return        Array containing smallest range [min, max]
     */
    public static int[] smallestRangeEfficient(List<List<Integer>> nums) {
        int k = nums.size();
        int[] out = new int[2];

        out[0] = 0;
        out[1] = Integer.MAX_VALUE;

        // Min heap to track minimum element
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        // Initialize heap with first element from each list
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            int element = nums.get(i).get(0);
            if (element > maxElement) {
                maxElement = element;
            }
            pq.add(new int[] {
                    element,
                    i, 0
            });
        }

        while (!pq.isEmpty()) {
            int[] res = pq.remove();
            int minElement = res[0];

            // Update result if current range is smaller
            if (maxElement - minElement < out[1] - out[0]) {
                out[0] = minElement;
                out[1] = maxElement;
            }

            // Add next element from same list
            int nextIndex = res[2] + 1;
            if (nextIndex < nums.get(res[1]).size()) {
                int nextElement = nums.get(res[1]).get(nextIndex);
                pq.add(new int[] {
                        nextElement,
                        res[1],
                        nextIndex
                });
                maxElement = Math.max(maxElement, nextElement);
            } else
                break;
        }
        return out;
    }
}
