/**
 * Problem Statement:
 * 
 *     Given two arrays, arr1 and arr2, of size n1 and n2 respectively, 
 *     the task is to find the union of these two arrays. The union of two arrays 
 *     is the set of distinct elements that appear in either of the arrays. 
 *     Your solution should return:
 *     - The count of the distinct elements in the union.
 *     - The list of distinct elements in the union.
 * 
 * Input:
 *     - An integer `n1` (1 <= n1 <= 10^5), the size of the first array.
 *     - An integer `n2` (1 <= n2 <= 10^5), the size of the second array.
 *     - An array `arr1` of size `n1` where each element satisfies (1 <= arr1[i] <= 10^5).
 *     - An array `arr2` of size `n2` where each element satisfies (1 <= arr2[i] <= 10^5).
 * 
 * Output:
 *     - An integer representing the count of distinct elements in the union.
 *     - A list of distinct elements that form the union.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     4
 *     3 4 5 6
 *     
 *     Output:
 *     6
 *     [1, 2, 3, 4, 5, 6]
 * 
 *     Explanation:
 *     The union of the two arrays is {1, 2, 3, 4, 5, 6}, 
 *     and the count of distinct elements is 6. The union array is [1, 2, 3, 4, 5, 6].
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class j11UnionOfTwoArrays {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt(); // Input: size of the first array
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = in.nextInt(); // Input: elements of the first array
        }
        int n2 = in.nextInt(); // Input: size of the second array
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = in.nextInt(); // Input: elements of the second array
        }

        // Call solution methods and print the results
        System.out.println(doUnion(arr1, arr2)); // Output the count of distinct elements in union
        System.out.println(unionOfTowArrays(arr1, arr2)); // Output the list of distinct elements in union

        in.close(); // Close the scanner
    }

    /**
     * Approach 1: Using HashSet to Count Distinct Elements
     * 
     * Intuition:
     * - A HashSet automatically handles duplicates, so by adding elements 
     *   from both arrays to the set, we can ensure only distinct elements 
     *   are counted.
     * - After adding all elements from both arrays, the size of the HashSet 
     *   gives the count of distinct elements in the union.
     * 
     * Time Complexity:
     * - O(n1 + n2), where n1 is the size of the first array and n2 is the size 
     *   of the second array. Each element is inserted into the HashSet once, 
     *   and insertion into a HashSet is O(1) on average.
     * 
     * Space Complexity:
     * - O(n1 + n2), because we are storing all distinct elements in the HashSet.
     * 
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @return The count of distinct elements in the union.
     */
    public static int doUnion(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<Integer>(); // Initialize the HashSet
        // Add all elements from the first array
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        // Add all elements from the second array
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        return set.size(); // Return the size of the set, which is the count of distinct elements
    }

    /**
     * Approach 2: Using HashSet to Return the Union Array
     * 
     * Intuition:
     * - We again use a HashSet to store distinct elements from both arrays.
     * - Instead of just returning the count, we convert the HashSet to an 
     *   ArrayList to return the actual list of distinct elements in the union.
     * 
     * Time Complexity:
     * - O(n1 + n2), where n1 is the size of the first array and n2 is the size 
     *   of the second array. Insertion into a HashSet takes O(1) on average.
     * 
     * Space Complexity:
     * - O(n1 + n2), because we are storing all distinct elements in the HashSet 
     *   and then converting it into an ArrayList.
     * 
     * @param arr1 The first input array.
     * @param arr2 The second input array.
     * @return A list of distinct elements in the union of the two arrays.
     */
    public static ArrayList<Integer> unionOfTowArrays(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<Integer>(); // Initialize the HashSet
        // Add all elements from the first array
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        // Add all elements from the second array
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        // Convert the HashSet to an ArrayList and return it
        return new ArrayList<Integer>(set);
    }
}
