/**
 * Problem Statement:
 * 
 *     Given two sorted arrays `arr1` and `arr2`, return the intersection of both arrays. 
 *     The result should contain all the common elements between the arrays, and each element should appear as many times as it appears in both arrays.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), the number of elements in array `arr1`.
 *     - An integer `m` (1 <= m <= 10^5), the number of elements in array `arr2`.
 *     - Two arrays `arr1` and `arr2` of size `n` and `m` respectively, where each element in both arrays is an integer.
 * 
 * Output:
 *     - A new array containing the intersection of `arr1` and `arr2`.
 * 
 * Example:
 *     Input:
 *     arr1 = [1, 2, 4, 5, 6]
 *     arr2 = [2, 3, 5, 7]
 *     
 *     Output:
 *     [2, 5]
 * 
 * Explanation:
 *     The common elements between the two arrays are 2 and 5, so the output is [2, 5].
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j07IntersectionOfSortedArrays {

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

        // Print the intersection using brute force approach
        System.out.println(intersectionOfArrays(arr1, arr2));

        // Print the intersection using an efficient approach
        System.out.println(intersectionOfArraysEfficient(arr1, arr2));

        // Print the intersection considering duplicates
        System.out.println(intersectionOfArrayTwoPointers1(arr1, arr2));

        // Print the intersection without duplicates
        System.out.println(intersectionOfArrayTwoPointers2(arr1, arr2));

        in.close();
    }

    /**
     * Approach 1: Brute Force with Nested Loops
     * 
     * The idea is to check each element of arr1 and compare it with each element of arr2.
     * If they are equal and the element in arr2 hasn't been visited, add it to the output.
     * 
     * Time Complexity: O(n * m), where n is the size of arr1 and m is the size of arr2.
     * Space Complexity: O(m), for storing the visited elements.
     */
    public static ArrayList<Integer> intersectionOfArrays(int[] arr1, int[] arr2) {
        ArrayList<Integer> output = new ArrayList<>();
        boolean[] visited = new boolean[arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j] && !visited[j]) {
                    output.add(arr1[i]);
                    visited[j] = true;
                    break;
                }
            }
        }
        return output;
    }

    /**
     * Approach 2: Efficient Approach with Linear Traversal
     * 
     * We traverse both arrays once, and compare elements from both. If we find a match, we add it to the output.
     * This approach also uses a `visited` array to ensure that we do not consider duplicates from `arr2`.
     * 
     * Time Complexity: O(n + m), where n is the size of arr1 and m is the size of arr2.
     * Space Complexity: O(m), for the visited array.
     */
    public static ArrayList<Integer> intersectionOfArraysEfficient(int[] arr1, int[] arr2) {
        ArrayList<Integer> output = new ArrayList<>();
        boolean[] visited = new boolean[arr2.length];
        int j = 0;
        for (int i = 0; i < arr1.length; i++) {
            while (j < arr2.length) {
                if (arr2[j] <= arr1[i]) {
                    if (arr2[j] == arr1[i] && !visited[j]) {
                        output.add(arr1[i]);
                        visited[j] = true;
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
        }
        return output;
    }

    /**
     * Approach 3: Using Two Pointers (Considering Duplicates)
     * 
     * This approach uses two pointers to traverse both arrays, considering duplicates in the arrays. 
     * Whenever an element in arr1 matches an element in arr2, it is added to the result.
     * 
     * Time Complexity: O(n + m), where n is the size of arr1 and m is the size of arr2.
     * Space Complexity: O(min(n, m)) for the output list.
     */
    public static ArrayList<Integer> intersectionOfArrayTwoPointers1(int[] arr1, int[] arr2) {
        ArrayList<Integer> output = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                output.add(arr1[i]);
                i++;
                j++;
            }
        }
        return output;
    }

    /**
     * Approach 4: Using Two Pointers (Ignoring Duplicates)
     * 
     * This approach also uses two pointers, but it avoids adding duplicate elements in the output list.
     * When a match is found, we skip any subsequent duplicates from both arrays.
     * 
     * Time Complexity: O(n + m), where n is the size of arr1 and m is the size of arr2.
     * Space Complexity: O(min(n, m)) for the output list.
     */
    public static ArrayList<Integer> intersectionOfArrayTwoPointers2(int[] arr1, int[] arr2) {
        ArrayList<Integer> output = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                output.add(arr1[i]);
                // Skip duplicates in arr1
                while (i + 1 < arr1.length && arr1[i + 1] == arr1[i]) {
                    i++;
                }
                // Skip duplicates in arr2
                while (j + 1 < arr2.length && arr2[j + 1] == arr2[j]) {
                    j++;
                }
                i++;
                j++;
            }
        }
        return output;
    }
}
