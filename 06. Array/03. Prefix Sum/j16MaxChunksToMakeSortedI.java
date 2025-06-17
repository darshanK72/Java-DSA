/**
 * LeetCode 769. Max Chunks To Make Sorted
 * 
 * Problem Statement:
 *     You are given an integer array arr of length n that represents a permutation of
 *     the integers in the range [0, n - 1]. We split the array into some number of
 *     chunks (partitions), and individually sort each chunk. After concatenating them,
 *     the result should equal the sorted array. Return the largest number of chunks we
 *     can make to sort the array.
 * 
 * Input:
 *     - arr[] (1 <= arr.length <= 10)
 *     - arr[i] is a permutation of [0, 1, ..., arr.length - 1]
 * 
 * Output:
 *     - Return the largest number of chunks we can make to sort the array
 * 
 * Example:
 *     Input: arr = [4,3,2,1,0]
 *     Output: 1
 * 
 *     Explanation:
 *     Splitting into two or more chunks will not return the required result.
 *     For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2],
 *     which isn't sorted.
 */

public class j16MaxChunksToMakeSortedI {

    /**
     * Approach 1: Prefix Max and Suffix Min
     * 
     * Intuition:
     * - A chunk can be formed if all elements before it are less than all elements after it
     * - We can use prefix max and suffix min arrays to check this condition
     * - If prefix max before a position is less than suffix min after it, we can form a chunk
     * 
     * Explanation:
     * - Step 1: Create prefix max array to store maximum element up to each index
     * - Step 2: Create suffix min array to store minimum element from each index to end
     * - Step 3: For each position i:
     *   * Check if max of elements before i is less than min of elements after i
     *   * If true, increment chunk count
     * 
     * Time Complexity: O(n) where n is the length of array
     *                  - O(n) for prefix max array
     *                  - O(n) for suffix min array
     *                  - O(n) for checking chunks
     * Space Complexity: O(n) for storing prefix max and suffix min arrays
     * 
     * @param arr    Array representing a permutation of [0, n-1]
     * @return       Maximum number of chunks possible
     */
    public static int maxChunksToSortedPrefixSuffix(int[] arr) {
        int n = arr.length;
        // Create arrays to store prefix maximum and suffix minimum
        int[] preMax = new int[n];
        int[] sufMin = new int[n];
        
        // Calculate prefix maximum array
        int max = arr[0];
        for(int i = 0; i < n; i++){
            max = Math.max(arr[i], max);  // Update max if current element is larger
            preMax[i] = max;              // Store the maximum up to current index
        }

        // Calculate suffix minimum array
        int min = arr[n - 1];
        for(int i = n - 1; i >= 0; i--){
            min = Math.min(arr[i], min);  // Update min if current element is smaller
            sufMin[i] = min;              // Store the minimum from current index to end
        }

        // Count the number of valid chunks
        int chunks = 0;
        for(int i = 0; i < n; i++){
            // Get maximum element before current position (or -1 if at start)
            int prevMax = i > 0 ? preMax[i - 1] : -1;
            // Get minimum element from current position to end
            int nextMin = sufMin[i];
            // If max before is less than min after, we can form a chunk
            if(prevMax < nextMin) chunks++;
        }

        return chunks;
    }

    /**
     * Approach 2: Prefix Sum
     * 
     * Intuition:
     * - For a chunk to be valid, sum of elements in the chunk should equal sum of
     *   indices in that range
     * - We can use prefix sum to check this condition
     * - If sum of elements equals sum of indices up to a point, we can form a chunk
     * 
     * Explanation:
     * - Step 1: Create prefix sum array of indices (0, 1, 2, ...)
     * - Step 2: Keep track of running sum of array elements
     * - Step 3: For each position i:
     *   * If running sum equals prefix sum of indices, increment chunk count
     * 
     * Time Complexity: O(n) where n is the length of array
     *                  - O(n) for creating prefix sum array
     *                  - O(n) for checking chunks
     * Space Complexity: O(n) for storing prefix sum array
     * 
     * @param arr    Array representing a permutation of [0, n-1]
     * @return       Maximum number of chunks possible
     */
    public static int maxChunksToSortedPrefix(int[] arr) {
        int n = arr.length;
        // Create prefix sum array for indices (0, 1, 2, ...)
        int[] prefix = new int[n];
        prefix[0] = 0;  // First element is 0
        for(int i = 1; i < n; i++){
            prefix[i] = prefix[i - 1] + i;  // Sum of indices up to i
        }

        // Keep track of running sum and chunk count
        int ans = 0;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];  // Add current element to running sum
            // If sum equals prefix sum, we can form a chunk
            if(sum == prefix[i]) ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {4, 3, 2, 1, 0};
        System.out.println("Input: arr = [4,3,2,1,0]");
        System.out.println("Expected: 1, Output: " + maxChunksToSortedPrefixSuffix(arr1));

        // Test Case 2: Already sorted
        System.out.println("\nAlready Sorted Case:");
        int[] arr2 = {0, 1, 2, 3, 4};
        System.out.println("Input: arr = [0,1,2,3,4]");
        System.out.println("Expected: 5, Output: " + maxChunksToSortedPrefixSuffix(arr2));

        // Test Case 3: Multiple chunks possible
        System.out.println("\nMultiple Chunks Case:");
        int[] arr3 = {1, 0, 2, 3, 4};
        System.out.println("Input: arr = [1,0,2,3,4]");
        System.out.println("Expected: 4, Output: " + maxChunksToSortedPrefixSuffix(arr3));

        // Test Case 4: Single element
        System.out.println("\nSingle Element Case:");
        int[] arr4 = {0};
        System.out.println("Input: arr = [0]");
        System.out.println("Expected: 1, Output: " + maxChunksToSortedPrefixSuffix(arr4));
    }
}
