/**
 * Problem Statement:
 * 
 *     You are given an array `arr[]` of size `n` where each element is an integer. The task is to compute the minimum 
 *     cost of sorting the array such that all the elements are in increasing order. 
 *     In a tricky sorting operation, you can select an integer `i` and add it to every element in the array. 
 *     This operation can be repeated any number of times. 
 *     We are tasked to find the minimum number of operations required to sort the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array `arr`.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 10^5).
 * 
 * Output:
 *     - The minimum number of operations required to sort the array.
 * 
 * Example:
 *     Input:
 *         5
 *         1 2 3 2 1
 *     Output:
 *         5
 * 
 *     Explanation:
 *         The array `arr = [1, 2, 3, 2, 1]` needs to be sorted. We can perform the sorting operations as follows:
 *         - First, the array is already partially sorted, and we find the longest increasing subsequence of elements.
 *         - The number of operations depends on the number of elements we can sort in one go.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j01TrickySortingCost {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Output the result
        System.out.println(sortingCost(n, arr));
        in.close();
    }

    /**
     * Approach: HashMap for Longest Increasing Subsequence
     * 
     * Intuition:
     * - The task is to compute the minimum sorting cost, which depends on finding the longest increasing subsequence 
     *   of elements in the array. By performing the sorting operation, the longer the subsequence of already sorted 
     *   elements, the fewer operations are required.
     * - The logic uses a HashMap to keep track of the longest subsequence ending with each element.
     * - By adding 1 to each element in the subsequence, we can extend the length of the subsequence, which minimizes 
     *   the number of operations required.
     * - The key insight is to maximize the length of the subsequence that is already sorted, thus minimizing the 
     *   operations needed to reorder the rest of the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array, since we are iterating over the array and performing constant 
     *   time operations with the HashMap.
     * 
     * Space Complexity:
     * - O(n), in the worst case, for storing the length of subsequences in the HashMap.
     * 
     * @param N The size of the array.
     * @param arr The input array of numbers.
     * @return The minimum sorting cost.
     */
    public static int sortingCost(int N, int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxL = -1;

        // Traverse the array and track the longest increasing subsequence
        for (int i = 0; i < N; i++) {
            // For each element, check if we can extend a subsequence ending at the previous
            // element
            if (map.containsKey(arr[i] - 1)) {
                map.put(arr[i], map.get(arr[i] - 1) + 1);
            } else {
                map.put(arr[i], 1); // If not, start a new subsequence with length 1
            }
            maxL = Math.max(maxL, map.get(arr[i])); // Update the maximum length of any subsequence found
        }

        // The minimum cost is the number of elements minus the length of the longest
        // increasing subsequence
        return N - maxL;
    }
}
