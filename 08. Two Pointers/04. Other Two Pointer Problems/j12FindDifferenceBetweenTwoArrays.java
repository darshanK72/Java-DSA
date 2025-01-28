/**
 * Problem Statement:
 * 
 *     Given two integer arrays `nums1` and `nums2`, return a list of two lists:
 *     1. The first list contains all the elements in `nums1` that are not in `nums2`.
 *     2. The second list contains all the elements in `nums2` that are not in `nums1`.
 *     The solution should not contain duplicates in the result lists.
 * 
 * Input:
 *     - Two integer arrays `nums1` and `nums2`, each containing `n` integers.
 * 
 * Output:
 *     - A list of two lists:
 *         1. The first list containing elements in `nums1` but not in `nums2`.
 *         2. The second list containing elements in `nums2` but not in `nums1`.
 * 
 * Example:
 *     Input:
 *     nums1 = [1, 2, 3]
 *     nums2 = [2, 4, 6]
 * 
 *     Output:
 *     [[1, 3], [4, 6]]
 * 
 *     Explanation:
 *     - Elements in `nums1` but not in `nums2` are 1 and 3.
 *     - Elements in `nums2` but not in `nums1` are 4 and 6.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class j12FindDifferenceBetweenTwoArrays {

    public static void main(String[] args) {
        // Example input arrays
        int[] nums1 = { 1, 2, 3 };
        int[] nums2 = { 2, 4, 6 };

        // Calling the first approach using HashSets
        List<List<Integer>> result1 = findDifferenceHashMap(nums1, nums2);
        System.out.println("Using HashMap Approach: " + result1);

        // Calling the second approach using two pointers after sorting
        List<List<Integer>> result2 = findDifferenceTwoPointers(nums1, nums2);
        System.out.println("Using Two Pointers Approach: " + result2);
    }

    /**
     * Approach 1: Using HashSets
     * 
     * Intuition:
     * - We first convert the arrays into HashSets to remove duplicates and allow for fast lookups.
     * - By iterating through both sets, we can find the elements in `nums1` that are not in `nums2` and vice versa.
     * 
     * Time Complexity:
     * - O(n1 + n2), where `n1` and `n2` are the sizes of the input arrays. The conversion to HashSets takes O(n), and
     *   iterating over both sets also takes O(n).
     * 
     * Space Complexity:
     * - O(n1 + n2), due to the space required for the HashSets.
     * 
     * @param nums1 The first input array.
     * @param nums2 The second input array.
     * @return A list of two lists: first list contains elements in `nums1` but not in `nums2`, and second list contains 
     *         elements in `nums2` but not in `nums1`.
     */
    public static List<List<Integer>> findDifferenceHashMap(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();

        // Add elements of nums1 to set1
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }

        // Add elements of nums2 to set2
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        ArrayList<Integer> diff1 = new ArrayList<>();
        ArrayList<Integer> diff2 = new ArrayList<>();

        // Elements in set1 but not in set2
        for (int num : set1) {
            if (!set2.contains(num)) {
                diff1.add(num);
            }
        }

        // Elements in set2 but not in set1
        for (int num : set2) {
            if (!set1.contains(num)) {
                diff2.add(num);
            }
        }

        List<List<Integer>> out = new ArrayList<>();
        out.add(diff1);
        out.add(diff2);
        return out;
    }

    /**
     * Approach 2: Using Two Pointers After Sorting
     * 
     * Intuition:
     * - First, we sort both arrays. Then, using two pointers, we iterate through both arrays to find elements that
     *   are unique to each array.
     * - By skipping over duplicate elements during the iteration, we ensure that the results do not contain duplicates.
     * 
     * Time Complexity:
     * - O(n1 log n1 + n2 log n2), where `n1` and `n2` are the sizes of the two input arrays due to sorting.
     *   After sorting, the two-pointer iteration takes O(n1 + n2).
     * 
     * Space Complexity:
     * - O(n1 + n2), as we need additional space for the result arrays.
     * 
     * @param nums1 The first input array.
     * @param nums2 The second input array.
     * @return A list of two lists: first list contains elements in `nums1` but not in `nums2`, and second list contains 
     *         elements in `nums2` but not in `nums1`.
     */
    public static List<List<Integer>> findDifferenceTwoPointers(int[] nums1, int[] nums2) {
        // Sort both arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0;
        int j = 0;
        ArrayList<Integer> diff1 = new ArrayList<>();
        ArrayList<Integer> diff2 = new ArrayList<>();

        // Traverse both sorted arrays using two pointers
        while (i < n1 && j < n2) {
            if (nums1[i] == nums2[j]) {
                // Skip duplicate elements in both arrays
                while (i + 1 < n1 && nums1[i] == nums1[i + 1])
                    i++;
                while (j + 1 < n2 && nums2[j] == nums2[j + 1])
                    j++;
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                if (diff1.isEmpty() || diff1.get(diff1.size() - 1) != nums1[i])
                    diff1.add(nums1[i]);
                i++;
            } else {
                if (diff2.isEmpty() || diff2.get(diff2.size() - 1) != nums2[j])
                    diff2.add(nums2[j]);
                j++;
            }
        }

        // Add remaining elements in nums1
        while (i < n1) {
            if (diff1.isEmpty() || diff1.get(diff1.size() - 1) != nums1[i])
                diff1.add(nums1[i]);
            i++;
        }

        // Add remaining elements in nums2
        while (j < n2) {
            if (diff2.isEmpty() || diff2.get(diff2.size() - 1) != nums2[j])
                diff2.add(nums2[j]);
            j++;
        }

        List<List<Integer>> out = new ArrayList<>();
        out.add(diff1);
        out.add(diff2);
        return out;
    }
}
