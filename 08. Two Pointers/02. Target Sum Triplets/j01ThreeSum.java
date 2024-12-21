/**
 * Problem Statement:
 *
 *     Given an integer array `nums`, return all the unique triplets 
 *     that sum up to zero.
 *     
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the size of the array.
 *     - An array `nums` of size `n` where each element satisfies (1 <= nums[i] <= 10^5).
 *
 * Output:
 *     - A list of lists, where each list contains three integers, representing the triplets 
 *       that sum to zero. The triplets must be unique and sorted in ascending order.
 *
 * Example:
 *     Input:
 *     6
 *     -1 0 1 2 -1 -4
 *     Output:
 *     [[-1, -1, 2], [-1, 0, 1]]
 *
 *     Explanation:
 *     The only unique triplets that sum to zero are [-1, -1, 2] and [-1, 0, 1].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class j01ThreeSum {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt(); // Input: elements of the array
        }

        // Call all possible solutions
        System.out.println(threeSum(nums));
        System.out.println(threeSumHashSet(nums));
        System.out.println(threeSumEfficient1(nums));
        System.out.println(threeSumEfficient2(nums));

        in.close();
    }

    /**
     * Approach 1: Brute Force Approach
     * 
     * Intuition:
     * - In this approach, we check all triplets using three nested loops.
     * - For each triplet, we check if their sum is zero. If it is, we add it to the output.
     * - To avoid duplicates, we use a HashSet to store the triplets in a sorted form.
     * 
     * Time Complexity:
     * - O(n^3) where `n` is the size of the array. We have three nested loops, and for each 
     *   triplet, we check if it's already in the set.
     * 
     * Space Complexity:
     * - O(n^2) due to storing the triplets in a HashSet.
     * 
     * @param nums The input array of numbers.
     * @return A list of unique triplets that sum up to zero.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> out = new ArrayList<>();
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        // Three nested loops to generate all possible triplets
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        Collections.sort(temp); // Sort the triplet to avoid duplicates
                        if (!set.contains(temp)) {
                            out.add(temp);
                            set.add(temp); // Store the triplet in the set to avoid duplicates
                        }
                    }
                }
            }
        }
        return out;
    }

    /**
     * Approach 2: HashSet Optimization
     * 
     * Intuition:
     * - Instead of using three nested loops, we reduce the number of loops by using a HashSet.
     * - For each element, we try to find two other elements that sum up to its negative value.
     * - This reduces unnecessary computations and avoids duplicates by using a set.
     * 
     * Time Complexity:
     * - O(n^2) where `n` is the size of the array. We iterate through the array and use a HashSet 
     *   for checking the complement.
     * 
     * Space Complexity:
     * - O(n^2) for storing the triplets in the HashSet.
     * 
     * @param nums The input array of numbers.
     * @return A list of unique triplets that sum up to zero.
     */
    public static List<List<Integer>> threeSumHashSet(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        // Loop through the array and find two numbers whose sum is the negative of the
        // current element
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> tSet = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int tar = -(nums[i] + nums[j]); // Target the negative sum
                if (tSet.contains(tar)) {
                    ArrayList<Integer> lst = new ArrayList<>(Arrays.asList(nums[i], nums[j], tar));
                    Collections.sort(lst); // Sort to ensure unique triplets
                    set.add(lst); // Add the sorted triplet to the set
                }
                tSet.add(nums[j]); // Add the current element to the set
            }
        }
        return new ArrayList<>(set); // Convert the set to a list
    }

    /**
     * Approach 3: Efficient Two-Pointer Technique (First Optimization)
     * 
     * Intuition:
     * - We first sort the array and then use the two-pointer technique to find pairs that sum to 
     *   a target (0 - nums[i]).
     * - The two-pointer technique ensures that we check each pair only once, reducing unnecessary 
     *   checks and improving performance.
     * - We skip duplicate elements to avoid counting the same triplet multiple times.
     * 
     * Time Complexity:
     * - O(n^2), as we loop through the array once and use the two-pointer technique for each element.
     * 
     * Space Complexity:
     * - O(1) since no additional space is used apart from the output list.
     * 
     * @param nums The input array of numbers.
     * @return A list of unique triplets that sum up to zero.
     */
    public static List<List<Integer>> threeSumEfficient1(int[] nums) {
        int n = nums.length;
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums); // Sort the array first
        int i = 0;
        // Iterate through the array
        while (i < n) {
            int j = i + 1;
            int k = n - 1;
            int target = 0 - nums[i]; // The target sum
            // Use two-pointer technique to find pairs
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    ArrayList<Integer> lst = new ArrayList<>();
                    lst.add(nums[i]);
                    lst.add(nums[j]);
                    lst.add(nums[k]);
                    out.add(lst);
                    j++;
                    k--;
                    // Skip duplicates for the second and third elements
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
            i++;
            // Skip duplicate elements for the first element
            while (i < n && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return out;
    }

    /**
     * Approach 4: Optimized Two-Pointer Approach (Second Optimization)
     * 
     * Intuition:
     * - This approach is similar to the previous one but with better handling of duplicates by 
     *   skipping them at an earlier stage.
     * - The array is sorted, and then we apply the two-pointer technique to find triplets that 
     *   sum to zero, skipping duplicate triplets more efficiently.
     * 
     * Time Complexity:
     * - O(n^2) where `n` is the size of the array. The outer loop runs `n` times and the inner 
     *   loop performs a two-pointer search for each element.
     * 
     * Space Complexity:
     * - O(1) as we don't use extra space apart from the output.
     * 
     * @param nums The input array of numbers.
     * @return A list of unique triplets that sum up to zero.
     */
    public static List<List<Integer>> threeSumEfficient2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // Sort the array first
        ArrayList<List<Integer>> out = new ArrayList<>();
        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Skip duplicate elements for the first element
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1;
            int k = n - 1;
            // Use two-pointer technique to find pairs
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    out.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    // Skip duplicate elements for the second and third elements
                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return out;
    }

    /**
     * Approach 5: Modular Approach (Further Optimization)
     * 
     * Intuition:
     * - In this approach, we modularize the logic by creating a separate function `twoSum` to 
     *   handle the pair-finding logic. This makes the code more readable and reusable.
     * 
     * Time Complexity:
     * - O(n^2) where `n` is the size of the array. The outer loop runs `n` times, and for each 
     *   iteration, we find pairs using the `twoSum` method.
     * 
     * Space Complexity:
     * - O(1) as we do not use extra space apart from the output.
     * 
     * @param nums The input array of numbers.
     * @return A list of unique triplets that sum up to zero.
     */
    public List<List<Integer>> threeSumModular(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums); // Sort the array first
        int i = 0;
        // Iterate through the array
        while (i < nums.length) {
            twoSum(nums, i, out); // Use the helper function to find pairs
            i++;
            // Skip duplicates for the first element
            while (i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return out;
    }

    /**
     * Helper function: twoSum
     * 
     * Intuition:
     * - This function finds pairs of numbers that sum to the negative value of the current 
     *   element `nums[i]`.
     * 
     * Time Complexity:
     * - O(n) where `n` is the length of the remaining array.
     * 
     * Space Complexity:
     * - O(1)
     * 
     * @param nums The input array of numbers.
     * @param i The current index in the array.
     * @param out The output list to store the results.
     */
    public static void twoSum(int[] nums, int i, List<List<Integer>> out) {
        int j = i + 1;
        int k = nums.length - 1;
        int target = 0 - nums[i];
        // Use two-pointer technique to find pairs
        while (j < k) {
            int sum = nums[j] + nums[k];
            if (sum == target) {
                ArrayList<Integer> lst = new ArrayList<>();
                lst.add(nums[i]);
                lst.add(nums[j]);
                lst.add(nums[k]);
                out.add(lst);
                j++;
                k--;
                // Skip duplicates for the second and third elements
                while (j < k && nums[j] == nums[j - 1]) {
                    j++;
                }
                while (j < k && nums[k] == nums[k + 1]) {
                    k--;
                }
            } else if (sum > target) {
                k--;
            } else {
                j++;
            }
        }
    }
}
