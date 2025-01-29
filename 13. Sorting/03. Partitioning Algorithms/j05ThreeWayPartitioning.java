/**
 * Problem Statement:
 * 
 *     Given an array `arr` of size `n` and two integers `a` and `b`, partition 
 *     the array into three parts:
 *     - Elements smaller than `a` should come first.
 *     - Elements in the range `[a, b]` should come next.
 *     - Elements greater than `b` should come at the end.
 *     - The relative order of elements within each partition does not matter.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6), representing the size of the array.
 *     - An array `arr` of size `n`, where each element satisfies (1 <= arr[i] <= 10^6).
 *     - Two integers `a` and `b`, defining the partitioning range.
 * 
 * Output:
 *     - The modified array after performing three-way partitioning.
 * 
 * Example:
 *     Input:
 *     7
 *     1 4 3 2 5 7 6
 *     3 5
 * 
 *     Output:
 *     1 2 3 4 5 7 6
 * 
 *     Explanation:
 *     - Elements < `3`: [1, 2]
 *     - Elements in range [3, 5]: [3, 4, 5]
 *     - Elements > `5`: [7, 6]
 */

import java.util.Scanner;

public class j05ThreeWayPartitioning {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];

        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }

        System.out.print("Enter values of a and b: ");
        int a = in.nextInt(); // Lower bound of range
        int b = in.nextInt(); // Upper bound of range

        // Call the efficient partitioning method
        threeWayPartition(arr, a, b);

        // Output the partitioned array
        System.out.print("Partitioned array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        in.close();
    }

    /**
     * Approach: Three-Way Partitioning (Dutch National Flag Algorithm)
     * 
     * Intuition:
     * - The idea is to use three pointers (`i`, `j`, `k`) to categorize elements:
     *   - `i`: Marks the boundary for elements less than `a`.
     *   - `j`: Marks the boundary for elements greater than `b`.
     *   - `k`: Iterates through the array and places elements in the correct partition.
     * - The process works as follows:
     *   - If `arr[k] < a`, swap it with `arr[i]` and move both `i` and `k` forward.
     *   - If `arr[k] > b`, swap it with `arr[j]` and move `j` backward.
     *   - Otherwise, move `k` forward.
     * - This ensures that elements are divided into three partitions in a single traversal.
     * 
     * Why This Works:
     * - By using a single pass and in-place swaps, we efficiently group elements.
     * - This approach ensures an optimal time complexity of O(n).
     * 
     * Time Complexity:
     * - O(n): We traverse the array once, performing swaps in constant time.
     * 
     * Space Complexity:
     * - O(1): In-place partitioning without extra memory usage.
     * 
     * @param arr The input array to be partitioned.
     * @param a The lower bound of the partitioning range.
     * @param b The upper bound of the partitioning range.
     */
    public static void threeWayPartition(int arr[], int a, int b) {
        int i = 0;
        int j = arr.length - 1;
        int k = 0;

        while (k <= j) {
            if (arr[k] < a) {
                swap(arr, k, i);
                i++;
                k++;
            } else if (arr[k] > b) {
                swap(arr, k, j);
                j--;
            } else {
                k++;
            }
        }
    }

    /**
     * Helper Function: Swap
     * 
     * Intuition:
     * - This function swaps two elements in the given array.
     * - It is used to rearrange elements during the partitioning process.
     * 
     * Time Complexity:
     * - O(1): Single operation to swap two elements.
     * 
     * @param arr The input array.
     * @param i The index of the first element to be swapped.
     * @param j The index of the second element to be swapped.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
