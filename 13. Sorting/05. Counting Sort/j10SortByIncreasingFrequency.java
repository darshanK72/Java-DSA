/**
 * Problem Statement:
 * 
 *     Given an array of integers, sort the array by increasing frequency of elements.
 *     If two elements have the same frequency, the element with a smaller value comes first.
 * 
 * Input:
 *     - An array of integers `nums` (1 <= nums.length <= 10^5, -100 <= nums[i] <= 100).
 * 
 * Output:
 *     - The array sorted by increasing frequency of elements.
 * 
 * Example:
 *     Input:
 *         nums = [1, 1, 2, 2, 2, 3]
 *     Output:
 *         [3, 1, 1, 2, 2, 2]
 * 
 * Explanation:
 *     The frequency of 1 is 2, the frequency of 2 is 3, and the frequency of 3 is 1.
 *     Therefore, the array is sorted by increasing frequency: [3, 1, 1, 2, 2, 2].
 */

 import java.util.ArrayList;
 import java.util.Collections;
 
 public class j10SortByIncreasingFrequency {
 
     public static void main(String[] args) {
         // Test case 1
         int[] nums1 = {1, 1, 2, 2, 2, 3};
         int[] sortedNums1 = frequencySort(nums1);
         System.out.print("Sorted array for nums1: ");
         for (int num : sortedNums1) {
             System.out.print(num + " ");
         }
         System.out.println();
 
         // Test case 2
         int[] nums2 = {4, 4, 4, 2, 2, 1};
         int[] sortedNums2 = frequencySort(nums2);
         System.out.print("Sorted array for nums2: ");
         for (int num : sortedNums2) {
             System.out.print(num + " ");
         }
         System.out.println();
         
         // Test case 3 (edge case with single element)
         int[] nums3 = {7};
         int[] sortedNums3 = frequencySort(nums3);
         System.out.print("Sorted array for nums3: ");
         for (int num : sortedNums3) {
             System.out.print(num + " ");
         }
         System.out.println();
     }
 
     /**
      * Approach: Sorting by Increasing Frequency
      * 
      * Intuition:
      * - First, we calculate the frequency of each number in the array.
      * - We then use the frequency counts to group the numbers by their frequency in buckets.
      * - Finally, we collect the numbers from these buckets in order of increasing frequency.
      * 
      * Time Complexity:
      * - O(n + k), where `n` is the number of elements in the array and `k` is the number of distinct elements.
      *   The time complexity is linear because we perform a couple of linear passes over the data.
      * 
      * Space Complexity:
      * - O(n + k), due to the extra space used for storing the frequency array and the buckets.
      * 
      * @param nums The array of integers to be sorted by frequency.
      * @return The array sorted by increasing frequency of elements.
      */
     public static int[] frequencySort(int[] nums) {
         int min = -100;
         int max = 100;
         
         // Step 1: Calculate the frequency of each element
         int[] freq = new int[max - min + 1];
         for (int n : nums) {
             freq[n - min]++;
         }
 
         // Step 2: Bucket sort based on frequency
         @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];
         for (int i = 0; i < nums.length + 1; i++) {
             buckets[i] = new ArrayList<>();
         }
 
         // Step 3: Fill the buckets with numbers based on frequency
         for (int i = min; i <= max; i++) {
             buckets[freq[i - min]].add(i);
         }
 
         // Step 4: Build the output array by collecting numbers from the buckets
         int[] out = new int[nums.length];
         int idx = 0;
         for (int frq = 1; frq <= nums.length; frq++) {
             Collections.reverse(buckets[frq]); // Reverse to ensure smaller numbers come first
             for (int ele : buckets[frq]) {
                 for (int j = 0; j < frq; j++) {
                     out[idx++] = ele;
                 }
             }
         }
         
         return out;
     }
 }
 