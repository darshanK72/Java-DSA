/**
 * LeetCode 845. Longest Mountain in Array
 * 
 * Problem Statement:
 *     You may recall that an array arr is a mountain array if and only if:
 *     - arr.length >= 3
 *     - There exists some index i (0-indexed) with 0 < i < arr.length - 1 
 *       such that:
 *       * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *       * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *     Given an integer array arr, return the length of the longest subarray, 
 *     which is a mountain. Return 0 if there is no mountain subarray.
 * 
 * Input:
 *     - arr (1 <= arr.length <= 10^4, 0 <= arr[i] <= 10^4)
 * 
 * Output:
 *     - Integer representing the length of longest mountain subarray, or 0 if 
 *       no mountain exists
 * 
 * Example:
 *     Input: arr = [2,1,4,7,3,2,5]
 *     Output: 5
 * 
 *     Explanation:
 *     The largest mountain is [1,4,7,3,2] which has length 5.
 * 
 *     Input: arr = [2,2,2]
 *     Output: 0
 * 
 *     Explanation:
 *     There is no mountain.
 */

import java.util.Arrays;

public class j10LargestMountainArray {

    /**
     * Approach 1: Dynamic Programming with Two-Pass
     * 
     * Intuition:
     * - A mountain subarray is a contiguous subarray that first strictly 
     *   increases to a peak, then strictly decreases
     * - For each position, we need to know:
     *   - How many consecutive increasing elements are to the left (ending at 
     *     this position)
     *   - How many consecutive decreasing elements are to the right (starting 
     *     at this position)
     * - A valid peak must have at least one element on the left (increasing) 
     *   and one element on the right (decreasing)
     * - The mountain length at position i = left[i] + right[i] - 1 (peak 
     *   counted twice)
     * - We use two arrays: left[] for increasing sequence lengths, right[] 
     *   for decreasing sequence lengths
     * 
     * Explanation:
     * - Step 1: Calculate left[] array from left to right
     *   - left[i] stores the length of longest increasing contiguous sequence 
     *     ending at index i
     *   - If nums[i-1] < nums[i], extend the sequence: left[i] = left[i-1] + 1
     *   - Otherwise, reset to 1 (single element sequence)
     * - Step 2: Calculate right[] array from right to left
     *   - right[i] stores the length of longest decreasing contiguous sequence 
     *     starting at index i
     *   - If nums[i+1] < nums[i], extend the sequence: right[i] = right[i+1] + 1
     *   - Otherwise, reset to 1 (single element sequence)
     * - Step 3: Find the longest valid mountain
     *   - For each position i, check if it's a valid peak (left[i] > 1 AND 
     *     right[i] > 1)
     *   - Calculate mountain length = left[i] + right[i] - 1
     *   - Track the maximum mountain length found
     * - Step 4: Return the maximum mountain length (or 0 if no mountain exists)
     * 
     * Time Complexity: O(n) where n is the length of arr array
     *                  - Single pass to calculate left array: O(n)
     *                  - Single pass to calculate right array: O(n)
     *                  - Single pass to find max mountain: O(n)
     * 
     * Space Complexity: O(n) where n is the length of arr array
     *                   - Two arrays left[] and right[] of size n each
     * 
     * @param nums    Integer array (1 <= nums.length <= 10^4, 
     *                0 <= nums[i] <= 10^4)
     * @return        Length of longest mountain subarray, or 0 if none exists
     */
    public static int longestMountain(int[] nums) {
        // Get the length of input array
        int n = nums.length;
        
        // Initialize array to store increasing sequence lengths ending at each 
        // index
        int[] left = new int[n];
        // Each element is at least a sequence of length 1
        Arrays.fill(left, 1);

        // Calculate longest increasing contiguous sequence from left to right
        // For each position i, extend the sequence if previous element is 
        // smaller
        for (int i = 1; i < n; i++) {
            // If previous element is smaller, extend the increasing sequence
            if (nums[i - 1] < nums[i]) {
                // Extend sequence by adding 1 to previous sequence length
                left[i] = left[i - 1] + 1;
            }
        }

        // Initialize array to store decreasing sequence lengths starting at 
        // each index
        int[] right = new int[n];
        // Each element is at least a sequence of length 1
        Arrays.fill(right, 1);
        
        // Calculate longest decreasing contiguous sequence from right to left
        // For each position i, extend the sequence if next element is smaller
        for (int i = n - 2; i >= 0; i--) {
            // If next element is smaller, extend the decreasing sequence
            if (nums[i + 1] < nums[i]) {
                // Extend sequence by adding 1 to next sequence length
                right[i] = right[i + 1] + 1;
            }
        }

        // Find the longest valid mountain subarray
        int maxBitonic = 0;
        // Check each position as potential peak of mountain
        for (int i = 0; i < n; i++) {
            // Valid peak must have elements on both sides (left[i] > 1 and 
            // right[i] > 1)
            if (left[i] > 1 && right[i] > 1) {
                // Mountain length = increasing length + decreasing length - 1 
                // (peak counted twice)
                maxBitonic = Math.max(maxBitonic, left[i] + right[i] - 1);
            }
        }

        // Return the longest mountain length found (0 if no mountain exists)
        return maxBitonic;
    }

    /**
     * Approach 2: Two Pointers
     * 
     * Intuition:
     * - Instead of precomputing increasing/decreasing sequences, we can 
     *   identify peaks on-the-fly and expand from them
     * - A peak is an element that is greater than both its neighbors
     * - Once we find a peak, we expand leftward to find the start of the 
     *   increasing sequence
     * - Similarly, we expand rightward to find the end of the decreasing 
     *   sequence
     * - This approach avoids extra space for storing left[] and right[] arrays
     * - We only process positions that are actual peaks, potentially saving 
     *   computation
     * 
     * Explanation:
     * - Step 1: Iterate through potential peak positions (indices 1 to n-2)
     *   - A peak cannot be at the first or last position since it needs 
     *     neighbors on both sides
     * - Step 2: For each position, check if it's a valid peak
     *   - Peak condition: arr[i] > arr[i-1] AND arr[i] > arr[i+1]
     *   - Only process positions that satisfy this condition
     * - Step 3: Expand leftward from the peak to find mountain start
     *   - Start from position i-1 and move left while elements are increasing
     *   - Stop when we reach the start or when sequence breaks (arr[left] <= 
     *     arr[left-1])
     * - Step 4: Expand rightward from the peak to find mountain end
     *   - Start from position i+1 and move right while elements are decreasing
     *   - Stop when we reach the end or when sequence breaks (arr[right] <= 
     *     arr[right+1])
     * - Step 5: Calculate mountain length and update maximum
     *   - Mountain length = right - left + 1 (inclusive of both endpoints)
     *   - Track the maximum length found across all peaks
     * - Step 6: Return the maximum mountain length (or 0 if no peaks found)
     * 
     * Time Complexity: O(n^2) in worst case, O(n) average case where n is the 
     *                  length of arr array
     *                  - Outer loop iterates through n-2 positions: O(n)
     *                  - For each peak, we expand left and right: O(n) in worst 
     *                    case
     *                  - Worst case occurs when array has many peaks and we 
     *                    expand fully for each
     *                  - Average case is better when peaks are sparse
     * 
     * Space Complexity: O(1) - Only using constant extra space for variables
     *                   - No additional arrays needed
     *                   - More space-efficient than Approach 1
     * 
     * @param arr    Integer array (1 <= arr.length <= 10^4, 
     *               0 <= arr[i] <= 10^4)
     * @return       Length of longest mountain subarray, or 0 if none exists
     */
    public static int longestMountainTwoPointers(int[] arr) {
        // Get the length of input array
        int n = arr.length;
        
        // Track the maximum mountain length found
        int ans = 0;
        
        // Iterate through potential peak positions (cannot be first or last)
        // A peak needs neighbors on both sides to form a valid mountain
        for (int i = 1; i < n - 1; i++) {
            // Check if current position is a valid peak
            // Peak must be greater than both left and right neighbors
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                // Initialize left pointer to position before peak
                int left = i - 1;
                // Expand leftward to find the start of increasing sequence
                // Continue while elements are strictly increasing
                while (left > 0 && arr[left] > arr[left - 1]) {
                    // Move left pointer backward to extend mountain start
                    left--;
                }
                
                // Initialize right pointer to position after peak
                int right = i + 1;
                // Expand rightward to find the end of decreasing sequence
                // Continue while elements are strictly decreasing
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    // Move right pointer forward to extend mountain end
                    right++;
                }
                
                // Calculate mountain length (inclusive of both endpoints)
                // Update maximum mountain length found so far
                ans = Math.max(ans, right - left + 1);
            }
        }
        
        // Return the longest mountain length found (0 if no peaks exist)
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [2,1,4,7,3,2,5], Expected: 5, Output: " + 
            longestMountain(new int[]{2,1,4,7,3,2,5}));
        System.out.println("Input: [2,2,2], Expected: 0, Output: " + 
            longestMountain(new int[]{2,2,2}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [1,2,1], Expected: 3, Output: " + 
            longestMountain(new int[]{1,2,1}));
        System.out.println("Input: [1,2,3,4,5], Expected: 0, Output: " + 
            longestMountain(new int[]{1,2,3,4,5}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [1,2], Expected: 0, Output: " + 
            longestMountain(new int[]{1,2}));
        System.out.println("Input: [1,2,3,2,1], Expected: 5, Output: " + 
            longestMountain(new int[]{1,2,3,2,1}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [0,1,0], Expected: 3, Output: " + 
            longestMountain(new int[]{0,1,0}));
        System.out.println("Input: [0,2,0,2,1,2,3,4,4,1], Expected: 3, Output: " + 
            longestMountain(new int[]{0,2,0,2,1,2,3,4,4,1}));
    }
}
