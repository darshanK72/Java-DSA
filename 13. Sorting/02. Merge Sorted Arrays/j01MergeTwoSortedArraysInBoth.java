/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2` of sizes `n` and `m` respectively,
 *     merge them such that:
 *     - After merging, the first `n` smallest elements are in `arr1`.
 *     - The remaining `m` largest elements are in `arr2`.
 *     The arrays should still be sorted after merging.
 * 
 * Input:
 *     - Two integers `n` and `m`, representing the sizes of the arrays.
 *     - Two sorted arrays `arr1` and `arr2` of sizes `n` and `m` respectively,
 *       where each element satisfies (1 <= arr1[i], arr2[i] <= 10^9).
 * 
 * Output:
 *     - The modified arrays `arr1` and `arr2`, with the merged elements distributed
 *       as described above.
 * 
 * Example:
 *     Input:
 *         n = 4, m = 5
 *         arr1 = [1, 3, 5, 7]
 *         arr2 = [2, 4, 6, 8, 9]
 *     Output:
 *         arr1 = [1, 2, 3, 4]
 *         arr2 = [5, 6, 7, 8, 9]
 * 
 *     Explanation:
 *         The merged arrays are distributed such that `arr1` contains the 4 smallest
 *         elements and `arr2` contains the 5 largest elements, both sorted.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j01MergeTwoSortedArraysInBoth {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        long[] arr1 = new long[m];
        long[] arr2 = new long[n];

        // Read elements for arr1
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextLong();
        }

        // Read elements for arr2
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextLong();
        }

        // Merge arrays and print the results
        mergeArraysEfficient(arr1, arr2, m, n);
        System.out.println("Merged arr1: " + Arrays.toString(arr1));
        System.out.println("Merged arr2: " + Arrays.toString(arr2));

        in.close();
    }

    /**
     * Approach 1: Naive Method (Using Extra Array)
     * 
     * Intuition:
     * - Combine both arrays into a temporary array and sort it.
     * - Distribute the first `n` elements back to `arr1` and the remaining `m` elements to `arr2`.
     * - This is simple to implement but not space or time-efficient.
     * 
     * Time Complexity:
     * - O((m + n) log(m + n)), for sorting the combined array.
     * 
     * Space Complexity:
     * - O(m + n), for the temporary array.
     * 
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @param n The size of the first array.
     * @param m The size of the second array.
     */
    public static void mergeArrays(long arr1[], long arr2[], int n, int m) {
        long[] out = new long[n + m];
        int i = 0, j = 0, k = 0;

        // Merge arr1 and arr2 into the output array `out`
        while (i < n && j < m) {
            if (arr1[i] < arr2[j]) {
                out[k] = arr1[i];
                i++;
            } else {
                out[k] = arr2[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from arr1
        while (i < n) {
            out[k] = arr1[i];
            k++;
            i++;
        }

        // Copy remaining elements from arr2
        while (j < m) {
            out[k] = arr2[j];
            k++;
            j++;
        }

        // Split the merged array back into arr1 and arr2
        k = 0;
        for (i = 0; i < n; i++) {
            arr1[i] = out[k++];
        }
        for (j = 0; j < m; j++) {
            arr2[j] = out[k++];
        }
    }

    /**
     * Approach 2: Efficient Method (In-place Merging)
     * 
     * Intuition:
     * - Start from the largest elements in `arr1` and smallest elements in `arr2`.
     * - Compare the largest element in `arr1` with the smallest in `arr2`, and
     *   swap if necessary. Continue this process while maintaining the order.
     * - Once the swaps are complete, sort both arrays to ensure the final result
     *   is correct.
     * 
     * Time Complexity:
     * - O(min(n, m) + n log n + m log m), for in-place swaps and sorting.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used for merging.
     * 
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @param n The size of the first array.
     * @param m The size of the second array.
     */
    public static void mergeArraysEfficient(long arr1[], long arr2[], int n, int m) {
        int i = n - 1;
        int j = 0;

        // Swap elements between arr1 and arr2 where necessary
        while (i >= 0 && j < m) {
            if (arr1[i] > arr2[j]) {
                long temp = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = temp;
                i--;
                j++;
            } else {
                break;
            }
        }

        // Sort both arrays after the in-place swaps
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

        /**
     * Approach 3: Insertion Sort-like Technique
     * 
     * Intuition:
     * - This method views the arrays `arr1` and `arr2` as parts of a combined sequence
     *   and uses an insertion sort approach to maintain sorted order.
     * - Starting from the end of `arr1` and the beginning of `arr2`, compare and 
     *   "insert" elements into their correct positions by swapping them backwards.
     * - For each element in the combined sequence, ensure the elements before it 
     *   remain sorted. If an element is out of place, repeatedly swap it backward 
     *   until it is positioned correctly.
     * 
     * Time Complexity:
     * - O((n1 + n2)^2) in the worst case, as each insertion operation may take 
     *   linear time, and this is repeated for all elements.
     * 
     * Space Complexity:
     * - O(1), as the sorting is done in place without using extra memory.
     * 
     * @param a The first sorted array.
     * @param b The second sorted array.
     */
    public static void mergeArraysInsertionSort(int a[], int b[]) {
        int n1 = a.length; // Size of arr1
        int n2 = b.length; // Size of arr2
        
        // Iterate over all elements in the combined sequence
        for (int i = n1; i < n1 + n2; i++) {
            // Perform backward insertion for each element
            for (int j = i; j > 0; j--) {
                // Get values to compare
                int val1 = (j - 1 < n1) ? a[j - 1] : b[j - 1 - n1];
                int val2 = (j < n1) ? a[j] : b[j - n1];
                
                // Swap if the previous value is greater
                if (val1 > val2) {
                    swap(a, b, j - 1, j);
                } else {
                    break; // Stop if sorted
                }
            }
        }
    }

   /**
     * Approach 4: Shell Sort-based Merging
     * 
     * Intuition:
     * - This method uses the concept of Shell Sort to merge the two arrays efficiently.
     * - Instead of comparing adjacent elements (as in Insertion Sort), elements at a 
     *   certain "gap" distance are compared and swapped if needed. The gap is 
     *   progressively reduced until it becomes 1.
     * - The final pass with a gap of 1 ensures that the entire sequence is sorted.
     * - This approach reduces the total number of comparisons in the early passes,
     *   improving performance compared to a standard Insertion Sort.
     * 
     * Time Complexity:
     * - O((n1 + n2) * log(n1 + n2)), as the gap is reduced logarithmically, and each pass
     *   requires traversing the combined array.
     * 
     * Space Complexity:
     * - O(1), as the sorting is performed in place.
     * 
     * @param a The first sorted array.
     * @param b The second sorted array.
     */
    public static void mergeArraysShellSort(int a[], int b[]) {
        int n1 = a.length; // Size of the first array
        int n2 = b.length; // Size of the second array
        int gap = n1 + n2; // Initial gap size (total size of both arrays)

        // Perform the shell sort-like operation
        while (gap >= 1) {
            // Compare elements at the current gap distance
            for (int j = 0; j + gap < n1 + n2; j++) {
                // Get the two elements to compare
                int val1 = (j < n1) ? a[j] : b[j - n1];
                int val2 = (j + gap < n1) ? a[j + gap] : b[j + gap - n1];
                
                // Swap if the elements are out of order
                if (val1 > val2) {
                    swap(a, b, j, j + gap);
                }
            }
            
            // Reduce the gap size
            if (gap == 1) break; // Exit if the gap is 1
            gap = gap / 2 + gap % 2; // Reduce gap, rounding up for odd values
        }
    }
    
    /**
     * Utility Method: Swap
     * 
     * Intuition:
     * - The swap method ensures elements are exchanged correctly between `arr1` 
     *   and `arr2`, as they are treated as a single combined array.
     * - Depending on the indices being swapped, the elements might both belong 
     *   to `arr1`, both belong to `arr2`, or one in each.
     * - The logic accounts for the index ranges of both arrays and swaps the elements
     *   accordingly.
     * 
     * Time Complexity:
     * - O(1), as swapping is a constant-time operation.
     * 
     * Space Complexity:
     * - O(1), as no extra memory is used.
     * 
     * @param a The first array.
     * @param b The second array.
     * @param i The index of the first element to swap.
     * @param j The index of the second element to swap.
     */
    public static void swap(int[] a, int[] b, int i, int j) {
        int n1 = a.length; // Size of arr1
        
        // Case 1: Both indices are in arr1
        if (i < n1 && j < n1) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        // Case 2: One index is in arr1, the other in arr2
        else if (i < n1 && j >= n1) {
            int temp = a[i];
            a[i] = b[j - n1];
            b[j - n1] = temp;
        }
        // Case 3: Both indices are in arr2
        else {
            int temp = b[i - n1];
            b[i - n1] = b[j - n1];
            b[j - n1] = temp;
        }
    }
}
