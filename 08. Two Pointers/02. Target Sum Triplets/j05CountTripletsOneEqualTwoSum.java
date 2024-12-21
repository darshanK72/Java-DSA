/**
 * Problem Statement:
 * 
 *     Given an array of integers, the task is to count how many triplets (i, j, k) exist in the array 
 *     such that arr[i] + arr[j] = arr[k] where i < j < k. The solution needs to return the total number 
 *     of such triplets in the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 1000).
 * 
 * Output:
 *     - An integer representing the number of triplets (i, j, k) such that arr[i] + arr[j] = arr[k].
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The two valid triplets are:
 *     (1, 2, 3) and (2, 3, 5), both satisfy the condition arr[i] + arr[j] = arr[k].
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class j05CountTripletsOneEqualTwoSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(countTriplet(arr, n)); // Brute force solution
        System.out.println(countTripletEfficient(arr, n)); // Efficient solution using two pointers
        in.close();
    }

    /**
     * Approach 1: Brute Force (Using HashSet)
     * 
     * Intuition:
     * - We iterate through every pair of elements arr[i] and arr[j], and check if their sum 
     *   exists in the set of all elements in the array (arr[i] + arr[j] = arr[k]).
     * - This approach uses a HashSet to store all elements of the array to perform constant time 
     *   lookups to check if the sum exists in the set.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the array. We iterate through all pairs and perform a 
     *   constant time lookup in the set.
     * 
     * Space Complexity:
     * - O(n), for storing the elements in the HashSet.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return The number of triplets that satisfy arr[i] + arr[j] = arr[k].
     */
    public static int countTriplet(int arr[], int n) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        // Add all elements to the set
        for (int i = 0; i < n; i++) {
            set.add(arr[i]);
        }

        // Iterate through all pairs and check if the sum exists in the set
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (set.contains(arr[i] + arr[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Efficient Solution using Two Pointers after Sorting the Array
     * 
     * Intuition:
     * - After sorting the array, we fix one element arr[k] and try to find pairs (arr[i], arr[j]) 
     *   where arr[i] + arr[j] = arr[k] using a two-pointer approach.
     * - The array is sorted, so we can efficiently search for valid pairs by adjusting the two pointers 
     *   based on the sum compared to arr[k].
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the array. The outer loop iterates over the array, and the 
     *   inner loop uses the two-pointer technique which also runs in linear time.
     * 
     * Space Complexity:
     * - O(1), since the space used is constant aside from the input array.
     * 
     * @param arr The input array of numbers.
     * @param n The size of the array.
     * @return The number of triplets that satisfy arr[i] + arr[j] = arr[k].
     */
    public static int countTripletEfficient(int arr[], int n) {
        Arrays.sort(arr); // Sort the array
        int count = 0;
        int i = n - 1;

        // Iterate over the array from the end (fix arr[k])
        while (i >= 0) {
            int s = 0;
            int e = i - 1;

            // Use two-pointer technique to find pairs arr[i] + arr[j] = arr[k]
            while (s < e) {
                int sum = arr[s] + arr[e];
                if (sum == arr[i]) {
                    count++; // Found a valid triplet
                    s++;
                    e--;
                } else if (sum > arr[i]) {
                    e--; // Move the right pointer to the left
                } else {
                    s++; // Move the left pointer to the right
                }
            }
            i--; // Move to the next element as arr[k]
        }
        return count;
    }
}
