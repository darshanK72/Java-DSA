/**
 * Problem Statement:
 * 
 *     Given an array of integers, and a target integer, find the number of unique triplets 
 *     in the array that sum up to the target. The solution should count each distinct triplet 
 *     only once, and the triplets can be formed by any combination of indices (i, j, k) where 
 *     i, j, and k are distinct. We need to implement an efficient algorithm for this problem.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 3000), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (1 <= arr[i] <= 1000).
 *     - An integer `target` representing the target sum for the triplets.
 * 
 * Output:
 *     - An integer representing the number of distinct triplets in the array that sum up to the 
 *       target, modulo 10^9 + 7.
 * 
 * Example:
 *     Input:
 *     6
 *     1 1 2 2 3 3
 *     6
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The four valid triplets are:
 *     (1, 2, 3), (1, 2, 3), (2, 2, 2), (1, 2, 3).
 *     Each triplet sums up to 6, and we count each distinct triplet once.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j04ThreeSumWithMultiplicity {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int target = in.nextInt();
        System.out.println(threeSumMultiHashMap(arr, target)); // Solution using HashMap
        System.out.println(threeSumMultiTwoPointer(arr, target)); // Solution using Two-pointer approach
        in.close();
    }

    /**
     * Approach 1: Using HashMap (Frequency Count)
     * 
     * Intuition:
     * - We count the frequency of each number in the array using a HashMap. For each pair of 
     *   numbers (i, j), we compute the third number k = target - i - j, and check if k exists in the 
     *   map. The result is incremented based on the number of occurrences of i, j, and k.
     * - Special care is taken for cases where i, j, and k are equal, or two of them are equal.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the array. We iterate through each pair of numbers and look up 
     *   the third number in the HashMap.
     * 
     * Space Complexity:
     * - O(n), for storing the frequency of each number in the HashMap.
     * 
     * @param arr The input array of numbers.
     * @param target The target sum for the triplet.
     * @return The number of triplets modulo 10^9 + 7.
     */
    public static int threeSumMultiHashMap(int[] arr, int target) {
        int MOD = 1_000_000_007;
        Map<Integer, Long> countMap = new HashMap<>();

        // Fill the frequency map
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0L) + 1);
        }

        long result = 0;

        // Iterate through all unique combinations of i, j, k
        for (Integer i : countMap.keySet()) {
            for (Integer j : countMap.keySet()) {
                int k = target - i - j;

                if (countMap.containsKey(k)) {
                    long countI = countMap.get(i);
                    long countJ = countMap.get(j);
                    long countK = countMap.get(k);

                    if (i == j && j == k) {
                        // Case 1: All three are the same (i == j == k)
                        result += countI * (countI - 1) * (countI - 2) / 6;
                    } else if (i == j && j != k) {
                        // Case 2: i == j != k
                        result += countI * (countI - 1) / 2 * countK;
                    } else if (i < j && j < k) {
                        // Case 3: i != j != k
                        result += countI * countJ * countK;
                    }
                }

                result %= MOD;
            }
        }

        return (int) result;
    }

    /**
     * Approach 2: Using Two-Pointer Technique after Sorting the Array
     * 
     * Intuition:
     * - After sorting the array, we can iterate through each element and use a two-pointer approach 
     *   to find pairs that sum up to the remaining target (i.e., target - arr[i]). This reduces the 
     *   problem to finding pairs efficiently.
     * - We handle duplicate elements carefully, ensuring we count distinct triplets only.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of the array. Sorting takes O(n log n) and for each element, 
     *   we use the two-pointer technique to find pairs.
     * 
     * Space Complexity:
     * - O(1), since we are using constant extra space after sorting.
     * 
     * @param arr The input array of numbers.
     * @param target The target sum for the triplet.
     * @return The number of triplets modulo 10^9 + 7.
     */
    public static int threeSumMultiTwoPointer(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int MOD = 1_000_000_007;
        int i = 0;
        long count = 0;

        while (i < n) {
            int s = i + 1;
            int e = n - 1;
            int tar = target - nums[i];

            while (s < e) {
                int sum = nums[s] + nums[e];
                if (sum == tar) {
                    if (nums[s] == nums[e]) {
                        int c = e - s + 1;
                        count += (c * (c - 1)) / 2;
                        count %= MOD;
                        break;
                    } else {
                        int c1 = 1;
                        int c2 = 1;
                        while (s + 1 < e && nums[s] == nums[s + 1]) {
                            s++;
                            c1++;
                        }
                        while (e - 1 > s && nums[e] == nums[e - 1]) {
                            e--;
                            c2++;
                        }
                        count += (c1 * c2);
                        count %= MOD;
                        s++;
                        e--;
                    }
                } else if (sum > tar) {
                    e--;
                } else {
                    s++;
                }
            }
            i++;
        }

        return (int) count;
    }
}
