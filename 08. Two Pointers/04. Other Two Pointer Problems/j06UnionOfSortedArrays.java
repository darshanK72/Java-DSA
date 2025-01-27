/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2` of sizes `n` and `m` respectively,
 *     find their union. The union of two arrays is a list of all unique elements
 *     present in both arrays, sorted in non-decreasing order. The input arrays
 *     may contain duplicates.
 * 
 * Input:
 *     - Two integers `n` and `m`, representing the sizes of the arrays.
 *     - Two sorted arrays `arr1` and `arr2` of sizes `n` and `m` respectively,
 *       where each element satisfies (1 <= arr1[i], arr2[i] <= 10^5).
 * 
 * Output:
 *     - A list of integers representing the union of the two arrays.
 * 
 * Example:
 *     Input:
 *         n = 5, m = 3
 *         arr1 = [1, 2, 4, 5, 6]
 *         arr2 = [2, 3, 5]
 *     Output:
 *         [1, 2, 3, 4, 5, 6]
 * 
 *     Explanation:
 *         The union of arr1 and arr2 includes all unique elements sorted in 
 *         non-decreasing order: 1, 2, 3, 4, 5, 6.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class j06UnionOfSortedArrays {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];

        // Read the first array
        for (int i = 0; i < n; i++) {
            arr1[i] = in.nextInt();
        }

        // Read the second array
        for (int i = 0; i < m; i++) {
            arr2[i] = in.nextInt();
        }

        // Call the optimized solution method
        System.out.println(unionOfArrayEfficient(arr1, arr2));

        in.close();
    }

    /**
     * Approach: Efficient Two-pointer Technique
     * 
     * Intuition:
     * - Since the arrays are sorted, we can leverage the two-pointer technique
     *   to traverse both arrays and merge them into a single list, avoiding duplicates.
     * - We maintain pointers for both arrays and compare the current elements.
     *   The smaller element is added to the result. If the elements are equal, 
     *   it is added only once, and both pointers are advanced.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the sizes of the input arrays.
     * 
     * Space Complexity:
     * - O(n + m), for storing the output list.
     * 
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @return A list of integers representing the union of the two arrays.
     */
    public static ArrayList<Integer> unionOfArrayEfficient(int[] arr1, int[] arr2) {
        ArrayList<Integer> output = new ArrayList<>();
        int i = 0;
        int j = 0;

        // Merge both arrays while handling duplicates
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                if (output.isEmpty() || output.get(output.size() - 1) != arr1[i]) {
                    output.add(arr1[i]);
                }
                i++;
            } else if (arr1[i] > arr2[j]) {
                if (output.isEmpty() || output.get(output.size() - 1) != arr2[j]) {
                    output.add(arr2[j]);
                }
                j++;
            } else {
                if (output.isEmpty() || output.get(output.size() - 1) != arr1[i]) {
                    output.add(arr1[i]);
                }
                i++;
                j++;
            }
        }

        // Add remaining elements from arr1 if any
        while (i < arr1.length) {
            if (output.isEmpty() || output.get(output.size() - 1) != arr1[i]) {
                output.add(arr1[i]);
            }
            i++;
        }

        // Add remaining elements from arr2 if any
        while (j < arr2.length) {
            if (output.isEmpty() || output.get(output.size() - 1) != arr2[j]) {
                output.add(arr2[j]);
            }
            j++;
        }

        return output;
    }

    /**
     * Approach 2: Using LinkedHashSet
     * 
     * Intuition:
     * - Use a LinkedHashSet to store unique elements while preserving the order.
     * - Traverse both arrays, adding each element to the set. Convert the set to a list.
     * 
     * Time Complexity:
     * - O(n + m), for traversing both arrays and inserting into the LinkedHashSet.
     * 
     * Space Complexity:
     * - O(n + m), for storing the set and output list.
     * 
     * @param arr1 The first sorted array.
     * @param arr2 The second sorted array.
     * @return A list of integers representing the union of the two arrays.
     */
    public static ArrayList<Integer> unionOfArrayLinkedHashSet(int[] arr1, int[] arr2) {
        LinkedHashSet<Integer> out = new LinkedHashSet<>();
        int i = 0;
        int j = 0;
        int m = arr1.length;
        int n = arr2.length;

        // Traverse both arrays
        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                out.add(arr1[i++]);
            } else {
                out.add(arr2[j++]);
            }
        }

        // Add remaining elements from arr1
        while (i < m) {
            out.add(arr1[i++]);
        }

        // Add remaining elements from arr2
        while (j < n) {
            out.add(arr2[j++]);
        }

        return new ArrayList<>(out);
    }
}
