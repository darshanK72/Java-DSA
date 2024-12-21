/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2`, return a new sorted array that contains the union of both arrays without duplicates.
 *     The result should maintain the order of elements as they appear in the original arrays.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the number of elements in array `arr1`.
 *     - An integer `m` (1 <= m <= 10^5), the number of elements in array `arr2`.
 *     - Two arrays `arr1` and `arr2` of size `n` and `m` respectively, where each element in both arrays is an integer.
 * 
 * Output:
 *     - A new sorted array that contains the union of `arr1` and `arr2`, without duplicates.
 * 
 * Example:
 *     Input:
 *     arr1 = [1, 2, 4, 5, 6]
 *     arr2 = [2, 3, 5, 7]
 *     
 *     Output:
 *     [1, 2, 3, 4, 5, 6, 7]
 * 
 * Explanation:
 *     The union of the two arrays is [1, 2, 3, 4, 5, 6, 7], with no duplicates.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class j06UnionOfSortedArrays {

    public static void main(String args[]) {
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

        // Print the union using HashSet
        System.out.println(unionOfArrays(arr1, arr2));

        // Print the union using the efficient approach
        System.out.println(unionOfArrayEfficient(arr1, arr2));

        in.close();
    }

    /**
     * Approach 1: Using HashSet
     * 
     * The idea is to add all elements from both arrays into a HashSet. Since HashSet
     * does not allow duplicates, only unique elements will be stored.
     * Finally, the elements of the HashSet are added to the result list.
     * 
     * Time Complexity: O(n + m), where n is the size of arr1 and m is the size of arr2.
     * Space Complexity: O(n + m), as we store elements in the HashSet.
     */
    public static ArrayList<Integer> unionOfArrays(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();

        // Add elements of arr1 to the set
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }

        // Add elements of arr2 to the set
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }

        // Convert the set to an ArrayList and return
        ArrayList<Integer> output = new ArrayList<>();
        for (Integer i : set) {
            output.add(i);
        }
        return output;
    }

    /**
     * Approach 2: Efficient Approach using Two Pointers
     * 
     * We take advantage of the fact that both arrays are already sorted. We use two pointers, one for each array, 
     * and merge them while skipping duplicate elements.
     * 
     * Time Complexity: O(n + m), where n is the size of arr1 and m is the size of arr2.
     * Space Complexity: O(n + m), as we store the result in an ArrayList.
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
}
