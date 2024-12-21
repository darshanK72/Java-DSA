/**
 * Problem Statement:
 * 
 *     Given an array of integers `nums` and a target integer `target`, your task is to 
 *     find all unique quadruplets (i, j, k, l) such that:
 *     - i < j < k < l
 *     - nums[i] + nums[j] + nums[k] + nums[l] == target
 * 
 * Input:
 *     - An integer `n` (4 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *     - An integer `target` representing the target sum for the quadruplets.
 * 
 * Output:
 *     - Return a list of all unique quadruplets [nums[i], nums[j], nums[k], nums[l]] 
 *       such that `i < j < k < l` and the sum of the quadruplet is equal to `target`.
 * 
 * Example:
 *     Input:
 *     5
 *     1 0 -1 0 -2 2
 *     0
 *     Output:
 *     [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
 * 
 *     Explanation:
 *     The unique quadruplets that sum up to 0 are:
 *     [-2, -1, 1, 2], [-2, 0, 0, 2], and [-1, 0, 0, 1].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

public class j01FourSumI {

    public static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }
        int target = in.nextInt(); // Input: target sum

        // Call different approaches
        System.out.println(fourSum(arr, target));
        System.out.println(fourSumHashMap(arr, target));
        System.out.println(fourSumEfficient(arr, target));
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach (O(n^4))
     * 
     * Intuition:
     * - This approach checks all possible quadruplets by iterating over four nested loops.
     * - While it guarantees finding all quadruplets, it has a time complexity of O(n^4), which is 
     *   inefficient for large inputs.
     * 
     * Time Complexity:
     * - O(n^4) due to four nested loops iterating through the array.
     * 
     * Space Complexity:
     * - O(n^2) for storing the result set.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @return The list of unique quadruplets that sum up to the target.
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                for (int k = j + 1; k < nums.length - 1; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1])
                        continue;
                    for (int l = k + 1; l < nums.length; l++) {
                        if (l > k + 1 && nums[l] == nums[l - 1])
                            continue;
                        long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == (long) target) {
                            out.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }
        return out;
    }

    /**
     * Approach 2: Using HashSet (O(n^3))
     * 
     * Intuition:
     * - In this approach, we use a HashSet to avoid duplicate quadruplets.
     * - We iterate over pairs of elements and for each pair, use another set to check if the 
     *   complement of the current sum exists.
     * - The HashSet helps ensure that only unique quadruplets are added to the result.
     * 
     * Time Complexity:
     * - O(n^3) due to three nested loops with HashSet operations that take O(n).
     * 
     * Space Complexity:
     * - O(n^2) for storing unique quadruplets in the set.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @return The list of unique quadruplets that sum up to the target.
     */
    public static List<List<Integer>> fourSumHashSet(int[] nums, int target) {
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                HashSet<Long> tSet = new HashSet<>();
                for (int k = j + 1; k < nums.length; k++) {
                    long tar = (long) target - ((long) nums[i] + (long) nums[j] + (long) nums[k]);
                    if (tSet.contains(tar)) {
                        List<Integer> lst = Arrays.asList(nums[i], nums[j], nums[k], (int) tar);
                        Collections.sort(lst);
                        set.add(lst);
                    }
                    tSet.add((long) nums[k]);
                }
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * Approach 3: Using HashMap (O(n^2))
     * 
     * Intuition:
     * - In this approach, we store all pairs of sums in a HashMap.
     * - We then iterate again to find pairs whose sum complements the current sum to the target.
     * - The HashMap allows us to efficiently find the pairs that sum up to the required complement.
     * 
     * Time Complexity:
     * - O(n^2) due to the double iteration to form pairs and look for complements in the map.
     * 
     * Space Complexity:
     * - O(n^2) for storing all pairs in the map.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @return The list of unique quadruplets that sum up to the target.
     */
    public static List<List<Integer>> fourSumHashMap(int[] nums, int target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        HashMap<Integer, List<Pair>> map = new HashMap<>();
        int n = nums.length;

        // Store all pairs of sums in the map
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                map.putIfAbsent(sum, new ArrayList<>());
                map.get(sum).add(new Pair(i, j));
            }
        }

        // Set to store unique quadruplets
        Set<List<Integer>> resultSet = new HashSet<>();

        // Iterate again to find quadruplets
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentSum = nums[i] + nums[j];
                int complement = target - currentSum;

                if (map.containsKey(complement)) {
                    for (Pair p : map.get(complement)) {
                        if (p.first != i && p.first != j && p.second != i && p.second != j) {
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[p.first], nums[p.second]);
                            Collections.sort(temp);
                            resultSet.add(temp);
                        }
                    }
                }
            }
        }

        out.addAll(resultSet);
        return out;
    }

    /**
     * Approach 4: Efficient Approach (O(n^2))
     * 
     * Intuition:
     * - This approach uses sorting and the two-pointer technique to find the quadruplets.
     * - We fix the first two numbers and use two pointers to find the remaining two numbers.
     * - Sorting ensures that we can skip duplicate numbers efficiently.
     * 
     * Time Complexity:
     * - O(n^2) due to sorting and the two-pointer traversal for each pair.
     * 
     * Space Complexity:
     * - O(n) for storing the result list.
     * 
     * @param nums The input array of numbers.
     * @param target The target sum.
     * @return The list of unique quadruplets that sum up to the target.
     */
    public static List<List<Integer>> fourSumEfficient(int[] nums, int target) {
        ArrayList<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int j = i + 1;
            long t1 = (long) target - nums[i];
            while (j < n) {
                int s = j + 1;
                int e = n - 1;
                long t = (long) t1 - nums[j];
                while (s < e) {
                    long sum = (long) nums[s] + nums[e];
                    if (sum == t) {
                        out.add(Arrays.asList(nums[i], nums[j], nums[s], nums[e]));
                        s++;
                        e--;

                        while (s < e && nums[s] == nums[s - 1])
                            s++;
                        while (s < e && nums[e] == nums[e + 1])
                            e--;
                    } else if (sum > t) {
                        e--;
                    } else {
                        s++;
                    }
                }
                while (j < n - 1 && nums[j] == nums[j + 1])
                    j++;
                j++;
            }
            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
            i++;
        }

        return out;
    }
}
