/**
 * Problem: Advantage Shuffle
 * 
 * Problem Statement:
 * Given two arrays nums1 and nums2 of equal length, return the maximum number of positions where 
 * nums1[i] > nums2[i] after rearranging nums1. In other words, we want to maximize the number of 
 * positions where nums1 has an advantage over nums2.
 * 
 * Example:
 * Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * Output: [2,11,7,15]
 * Explanation:
 * nums1[0] = 2 > nums2[0] = 1
 * nums1[1] = 11 > nums2[1] = 10
 * nums1[2] = 7 > nums2[2] = 4
 * nums1[3] = 15 > nums2[3] = 11
 * 
 * Constraints:
 * - 1 <= nums1.length = nums2.length <= 105
 * - 0 <= nums1[i], nums2[i] <= 109
 * 
 * Approach:
 * 1. Sort nums1 in ascending order
 * 2. Create an array of indices for nums2 and sort them based on nums2 values
 * 3. Use two pointers to match elements:
 *    - If nums1[i] > nums2[j], place nums1[i] at position j
 *    - Otherwise, store nums1[i] for later use
 * 4. Fill remaining positions with unused nums1 elements
 * 
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(n) for storing indices and result
 */

import java.util.ArrayList;
import java.util.Arrays;

public class j05AdvantageShuffle {
    /**
     * Rearranges nums1 to maximize the number of positions where nums1[i] > nums2[i]
     * 
     * @param nums1 First array to be rearranged
     * @param nums2 Second array (reference)
     * @return Rearranged version of nums1 that maximizes advantage over nums2
     */
    public static int[] advantageCount(int[] nums1, int[] nums2) {
        // Sort nums1 for optimal matching
        Arrays.sort(nums1);
        
        // Create and sort indices based on nums2 values
        Integer[] indices = new Integer[nums2.length];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, (a, b) -> nums2[a] - nums2[b]);

        int i = 0;  // Pointer for nums1
        int j = 0;  // Pointer for sorted indices
        int[] out = new int[nums1.length];
        ArrayList<Integer> list = new ArrayList<>();
        
        // Match elements optimally
        while (i < nums1.length) {
            if (nums1[i] > nums2[indices[j]]) {
                // Place nums1[i] at the optimal position
                out[indices[j]] = nums1[i];
                j++;
            } else {
                // Store index for later use
                list.add(i);
            }
            i++;
        }

        // Fill remaining positions with unused elements
        for (int idx : list) {
            out[indices[j++]] = nums1[idx];
        }
        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] nums1_1 = {2, 7, 11, 15};
        int[] nums2_1 = {1, 10, 4, 11};
        System.out.println("Test Case 1:");
        System.out.println("Input nums1: " + Arrays.toString(nums1_1));
        System.out.println("Input nums2: " + Arrays.toString(nums2_1));
        System.out.println("Output: " + Arrays.toString(advantageCount(nums1_1, nums2_1)));
        System.out.println();

        // Test Case 2: All elements in nums1 are greater
        int[] nums1_2 = {12, 24, 8, 32};
        int[] nums2_2 = {13, 25, 32, 11};
        System.out.println("Test Case 2:");
        System.out.println("Input nums1: " + Arrays.toString(nums1_2));
        System.out.println("Input nums2: " + Arrays.toString(nums2_2));
        System.out.println("Output: " + Arrays.toString(advantageCount(nums1_2, nums2_2)));
        System.out.println();

        // Test Case 3: Equal arrays
        int[] nums1_3 = {1, 2, 3, 4};
        int[] nums2_3 = {1, 2, 3, 4};
        System.out.println("Test Case 3:");
        System.out.println("Input nums1: " + Arrays.toString(nums1_3));
        System.out.println("Input nums2: " + Arrays.toString(nums2_3));
        System.out.println("Output: " + Arrays.toString(advantageCount(nums1_3, nums2_3)));
        System.out.println();

        // Test Case 4: Single element
        int[] nums1_4 = {2};
        int[] nums2_4 = {1};
        System.out.println("Test Case 4:");
        System.out.println("Input nums1: " + Arrays.toString(nums1_4));
        System.out.println("Input nums2: " + Arrays.toString(nums2_4));
        System.out.println("Output: " + Arrays.toString(advantageCount(nums1_4, nums2_4)));
        System.out.println();

        // Test Case 5: No advantage possible
        int[] nums1_5 = {1, 2, 3, 4};
        int[] nums2_5 = {5, 6, 7, 8};
        System.out.println("Test Case 5:");
        System.out.println("Input nums1: " + Arrays.toString(nums1_5));
        System.out.println("Input nums2: " + Arrays.toString(nums2_5));
        System.out.println("Output: " + Arrays.toString(advantageCount(nums1_5, nums2_5)));
    }
}
