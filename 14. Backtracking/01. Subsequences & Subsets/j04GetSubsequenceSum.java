/**
 * Problem Statement:
 * 
 *     Given an array `arr[]` of `n` integers, and an integer `k`, find all the subsequences of the array whose sum equals `k`.
 *     A subsequence is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100), representing the size of the array.
 *     - An array `arr[]` of size `n` where each element satisfies (1 <= arr[i] <= 100).
 *     - An integer `k` (0 <= k <= 1000), representing the sum to match.
 * 
 * Output:
 *     - The count of subsequences whose sum equals `k`.
 *     - A list of all subsequences whose sum equals `k`.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     3
 *     
 *     Output:
 *     2
 *     [[1, 2], [3]]
 * 
 *     Explanation:
 *     - The subsequences whose sum equals 3 are [1, 2] and [3].
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j04GetSubsequenceSum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: elements of the array
        }
        int k = in.nextInt(); // Input: target sum of subsequences
        
        // Checks if there is subset or sobsequence with sum K
        System.out.println("Is Subset With Sum " + k + " Present : " + checkIsThereSubsetWithSum(k, n, k, arr));

        // Call to count the subsequences whose sum equals k
        System.out.println(countSubsequencesWithSum(arr, 0, 0, k));

        // Call to get all subsequences whose sum equals k
        System.out.println(getArraySubseqencesWithSum(arr, 0, new ArrayList<Integer>(), 0, k));

        in.close();
    }

    /**
     * Approach 1: Check if there is a subset with the given sum
     * 
     * Intuition:
     * - We use a recursive function to explore all subsets of the array.
     * - At each index, we can either include the current element in the subset or exclude it.
     * - When we reach the end of the array, we check if the sum of the current subset equals the target sum `k`.
     * 
     * Time Complexity:
     * - O(2^n), where n is the length of the array. This is because for each element, we have two choices (include or exclude).
     * 
     * Space Complexity:
     * - O(n), due to the recursion stack.
     * 
     * @param k The target sum to be matched.
     * @param idx The current index in the array.
     * @param sum The current sum of the subset.
     * @param arr The input array of numbers.
     * @return True if there is a subset with the given sum, otherwise false.
     */
    public static boolean checkIsThereSubsetWithSum(int k, int idx, int sum, int[] arr) {
        if (idx == arr.length) {
            if (sum == k) {
                return true; // If sum matches k, return true
            }
            return false; // Otherwise, return false
        }

        // Include current element
        if (checkIsThereSubsetWithSum(k, idx + 1, sum + arr[idx], arr)) return true;

        // Exclude current element
        if (checkIsThereSubsetWithSum(k, idx + 1, sum, arr)) return true;

        return false; // Return false if no subset matches the sum
    }

    /**
     * Approach 2: Brute Force Approach (Recursive)
     * 
     * Intuition:
     * - We use a recursive function to explore all subsequences of the array.
     * - At each index, we can either include the current element in the subsequence or exclude it.
     * - When we reach the end of the array, we check if the sum of the current subsequence equals the target sum `k`.
     * 
     * Time Complexity:
     * - O(2^n), where n is the length of the array. This is because for each element, we have two choices (include or exclude).
     * 
     * Space Complexity:
     * - O(n), due to the recursion stack.
     * 
     * @param arr The input array of numbers.
     * @param index The current index in the array.
     * @param sum The current sum of the subsequence.
     * @param k The target sum to be matched.
     * @return The number of subsequences whose sum equals k.
     */
    public static int countSubsequencesWithSum(int[] arr, int index, int sum, int k) {
        if (index == arr.length) {
            if (sum == k) {
                return 1; // If sum matches k, return 1
            }
            return 0; // Otherwise, return 0
        }

        // Include current element
        int count1 = countSubsequencesWithSum(arr, index + 1, sum + arr[index], k);

        // Exclude current element
        int count2 = countSubsequencesWithSum(arr, index + 1, sum, k);

        return count1 + count2; // Return total count by adding both counts
    }

    /**
     * Approach 3: Brute Force Approach (Recursive with storing subsequences)
     * 
     * Intuition:
     * - Similar to the first approach, but instead of just counting subsequences, we store each subsequence that meets the condition.
     * - This approach recursively builds the subsequences, adding them to the result if their sum equals `k`.
     * 
     * Time Complexity:
     * - O(2^n), since we generate all subsequences.
     * 
     * Space Complexity:
     * - O(n), due to the recursion stack and the storage of subsequences.
     * 
     * @param arr The input array of numbers.
     * @param index The current index in the array.
     * @param set The current subsequence being constructed.
     * @param sum The current sum of the subsequence.
     * @param k The target sum to be matched.
     * @return A list of all subsequences whose sum equals k.
     */
    public static ArrayList<ArrayList<Integer>> getArraySubseqencesWithSum(int[] arr, int index, ArrayList<Integer> set,
            int sum, int k) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();

        if (index == arr.length) {
            if (sum == k) {
                output.add(new ArrayList<>(set)); // If sum matches k, add the subsequence to the output
            }
            return output;
        }

        // Include current element in the subsequence
        set.add(arr[index]);
        output.addAll(getArraySubseqencesWithSum(arr, index + 1, set, sum + arr[index], k));

        // Exclude current element from the subsequence
        set.remove(set.size() - 1);
        output.addAll(getArraySubseqencesWithSum(arr, index + 1, set, sum, k));

        return output;
    }
}
