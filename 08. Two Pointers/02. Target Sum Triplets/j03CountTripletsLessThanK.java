/**
 * Problem Statement:
 *     Given an array of distinct integers and a value K, count all distinct triplets 
 *     (i, j, k) such that the sum of elements at indices (i, j, k) is less than K. 
 *     The triplets should satisfy the condition i < j < k.
 * 
 * Input:
 *     - An integer n (1 <= n <= 10^3), representing the size of the array
 *     - An array arr of size n containing long integers
 *     - An integer K (1 <= K <= 10^5), the target sum
 * 
 * Output:
 *     - Return a long integer representing the count of triplets whose sum is less than K
 * 
 * Example:
 *     Input: 
 *     n = 4
 *     arr[] = {5, 1, 3, 4}
 *     K = 9
 *     
 *     Output: 
 *     2
 *     
 *     Explanation:
 *     Triplets with sum less than 9 are {1,3,4} and {1,3,5}
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03CountTripletsLessThanK {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        int target = in.nextInt();

        // Call brute force solution
        System.out.printf("Brute Force Solution: %d\n", countTriplets(n, target, arr));

        // Call optimized solution
        System.out.printf("Optimized Solution: %d\n", countTripletsEfficient(n, target, arr));

        in.close();
    }

    /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - The simplest approach is to consider all possible triplets using three nested loops
     * - For each triplet (i,j,k), we check if their sum is less than the target K
     * - This approach guarantees finding all valid triplets as we check every possibility
     * 
     * Time Complexity:
     * - O(n^3) as we use three nested loops to generate all possible triplets
     * 
     * Space Complexity:
     * - O(1) as we only use a few variables regardless of input size
     * 
     * @param n The size of the array
     * @param sum The target sum K
     * @param arr The input array
     * @return Count of triplets with sum less than K
     */
    public static long countTriplets(int n, int sum, long arr[]) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long s = arr[i] + arr[j] + arr[k];
                    if (s < sum) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Two Pointer Technique
     * 
     * Intuition:
     * - We can optimize by first sorting the array and then using two pointers
     * - For each element i, we use two pointers (s,e) to find pairs in remaining array
     * - If current sum is less than (target - arr[i]), all elements between s and e 
     *   will form valid triplets with arr[i] due to sorting
     * - This helps avoid checking each element individually in the third loop
     * 
     * Time Complexity:
     * - O(n^2) after sorting, as we use two pointers to find pairs for each element
     * - O(n log n) for initial sorting
     * - Overall: O(n^2)
     * 
     * Space Complexity:
     * - O(1) excluding the space used for sorting
     * 
     * @param n The size of the array
     * @param sum The target sum K
     * @param arr The input array
     * @return Count of triplets with sum less than K
     */
    public static long countTripletsEfficient(int n, int sum, long arr[]) {
        Arrays.sort(arr);
        long count = 0;
        int i = 0;
        while (i < n) {
            int s = i + 1;
            int e = n - 1;
            long tar = sum - arr[i];
            while (s < e) {
                long ss = arr[s] + arr[e];
                if (ss < tar) {
                    count += e - s;
                    s++;
                } else {
                    e--;
                }
            }
            i++;
        }
        return count;
    }
}