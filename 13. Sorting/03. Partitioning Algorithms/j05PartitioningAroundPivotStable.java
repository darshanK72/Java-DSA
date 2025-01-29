/**
 * Problem Statement:
 * 
 *     Given an array `nums` and an integer `pivot`, partition the array around 
 *     the pivot in a stable manner. This means:
 *     - All elements less than the pivot appear before elements equal to the pivot.
 *     - All elements equal to the pivot appear before elements greater than the pivot.
 *     - The relative order of the elements in each partition is maintained.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `pivot` to partition the array around.
 * 
 * Output:
 *     - The partitioned array where elements are arranged in a stable manner around `pivot`.
 * 
 * Example:
 *     Input:
 *     6
 *     9 12 5 10 14 10
 *     10
 * 
 *     Output:
 *     [9, 5, 10, 10, 12, 14]
 * 
 *     Explanation:
 *     - Elements less than `10`: [9, 5] (in original order)
 *     - Elements equal to `10`: [10, 10] (in original order)
 *     - Elements greater than `10`: [12, 14] (in original order)
 */

import java.util.Arrays;
import java.util.Scanner;

public class j05PartitioningAroundPivotStable {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt(); // Input: size of the array
        int[] nums = new int[n];

        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }

        System.out.print("Enter the pivot value: ");
        int pivot = in.nextInt(); // Input: pivot value

        // Applying stable partitioning
        int[] result = pivotArray(nums, pivot);
        System.out.println("Partitioned array (Stable Approach): " + Arrays.toString(result));

        // Applying optimized partitioning
        int[] optimizedResult = pivotArrayEfficient(nums, pivot);
        System.out.println("Partitioned array (Optimized Approach): " + Arrays.toString(optimizedResult));

        in.close();
    }

    /**
     * Approach 1: Stable Partitioning (Using Extra Arrays)
     * 
     * Intuition:
     * - We divide the array into three different sections:
     *   1. Elements smaller than the pivot.
     *   2. Elements equal to the pivot.
     *   3. Elements greater than the pivot.
     * - We maintain three separate arrays and populate them while keeping the 
     *   relative order of elements intact.
     * - Finally, we merge all three arrays back into the original array.
     * 
     * Why This Works:
     * - Since we maintain separate arrays and merge them in the original order, 
     *   the approach remains stable.
     * 
     * Time Complexity:
     * - O(n): We traverse the array twice (once for separation, once for merging).
     * 
     * Space Complexity:
     * - O(n): We use additional arrays of size `n` to store the elements separately.
     * 
     * @param nums The input array.
     * @param pivot The pivot value.
     * @return The stable partitioned array.
     */
    public static int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] less = new int[n];
        int[] middle = new int[n];
        int[] more = new int[n];
        int i = 0, j = 0, k = 0;

        // Partition elements into three separate arrays
        for (int idx = 0; idx < n; idx++) {
            if (nums[idx] < pivot) {
                less[i++] = nums[idx];
            } else if (nums[idx] > pivot) {
                more[k++] = nums[idx];
            } else {
                middle[j++] = nums[idx];
            }
        }

        // Merge back into original array
        int l = 0;
        for (int idx = 0; idx < i; idx++) {
            nums[l++] = less[idx];
        }
        for (int idx = 0; idx < j; idx++) {
            nums[l++] = middle[idx];
        }
        for (int idx = 0; idx < k; idx++) {
            nums[l++] = more[idx];
        }

        return nums;
    }

    /**
     * Approach 2: Optimized Stable Partitioning (Using Single Array)
     * 
     * Intuition:
     * - Instead of using three separate arrays, we utilize a single output array.
     * - We first iterate through `nums` and fill elements smaller than the pivot 
     *   at the start.
     * - Simultaneously, we fill elements greater than the pivot from the end.
     * - Finally, we place pivot elements in the middle.
     * 
     * Why This Works:
     * - Since we maintain order while placing elements, this approach remains stable.
     * - Using a single auxiliary array reduces the space complexity significantly.
     * 
     * Time Complexity:
     * - O(n): We traverse the array once and rearrange elements in a single pass.
     * 
     * Space Complexity:
     * - O(n): We use a single extra array of size `n`.
     * 
     * @param nums The input array.
     * @param pivot The pivot value.
     * @return The partitioned array.
     */
    public static int[] pivotArrayEfficient(int[] nums, int pivot) {
        int n = nums.length;
        int[] out = new int[n];

        int s = 0; // Pointer for elements smaller than pivot
        int e = n - 1; // Pointer for elements greater than pivot

        // First pass: Place smaller and greater elements in the output array
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                out[s++] = nums[i];
            }
            if (nums[n - i - 1] > pivot) {
                out[e--] = nums[n - i - 1];
            }
        }

        // Fill pivot elements in the middle
        while (s <= e) {
            out[s++] = pivot;
            out[e--] = pivot;
        }

        return out;
    }
}
