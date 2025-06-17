/**
 * LeetCode 768. Max Chunks To Make Sorted II
 * 
 * Problem Statement:
 *     You are given an integer array arr. We split the array into some number of chunks
 *     (partitions), and individually sort each chunk. After concatenating them, the result
 *     should equal the sorted array. Return the largest number of chunks we can make to
 *     sort the array. This is a harder version of Max Chunks To Make Sorted I, as the
 *     array can contain duplicate elements and is not necessarily a permutation.
 * 
 * Input:
 *     - arr[] (1 <= arr.length <= 2000)
 *     - arr[i] (0 <= arr[i] <= 10^8)
 * 
 * Output:
 *     - Return the largest number of chunks we can make to sort the array
 * 
 * Example:
 *     Input: arr = [2,1,3,4,4]
 *     Output: 4
 * 
 *     Explanation:
 *     We can split into two chunks, such as [2, 1], [3, 4, 4].
 *     However, splitting into [2, 1, 3], [4, 4] is the highest number of chunks possible.
 *     After sorting each chunk and concatenating, we get [1, 2, 3, 4, 4].
 */

public class j17MaxChunksToMakeSortedII {

    /**
     * Approach: Prefix Max and Suffix Min
     * 
     * Intuition:
     * - A chunk can be formed if all elements before it are less than or equal to all
     *   elements after it
     * - We can use prefix max and suffix min arrays to check this condition
     * - If prefix max before a position is less than or equal to suffix min after it,
     *   we can form a chunk
     * - This approach handles duplicate elements correctly
     * 
     * Explanation:
     * - Step 1: Create prefix max array to store maximum element up to each index
     * - Step 2: Create suffix min array to store minimum element from each index to end
     * - Step 3: For each position i:
     *   * Check if max of elements before i is less than or equal to min of elements after i
     *   * If true, increment chunk count
     * 
     * Time Complexity: O(n) where n is the length of array
     *                  - O(n) for prefix max array
     *                  - O(n) for suffix min array
     *                  - O(n) for checking chunks
     * Space Complexity: O(n) for storing prefix max and suffix min arrays
     * 
     * @param arr    Array of integers that may contain duplicates
     * @return       Maximum number of chunks possible
     */
    public static int maxChunksToSorted(int[] arr) {
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
            // If max before is less than or equal to min after, we can form a chunk
            // Note: Using <= instead of < to handle duplicate elements
            if(prevMax <= nextMin) chunks++;
        }

        return chunks;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] arr1 = {2, 1, 3, 4, 4};
        System.out.println("Input: arr = [2,1,3,4,4]");
        System.out.println("Expected: 4, Output: " + maxChunksToSorted(arr1));

        // Test Case 2: All elements same
        System.out.println("\nAll Elements Same Case:");
        int[] arr2 = {5, 5, 5, 5};
        System.out.println("Input: arr = [5,5,5,5]");
        System.out.println("Expected: 4, Output: " + maxChunksToSorted(arr2));

        // Test Case 3: Already sorted
        System.out.println("\nAlready Sorted Case:");
        int[] arr3 = {1, 2, 2, 3, 4};
        System.out.println("Input: arr = [1,2,2,3,4]");
        System.out.println("Expected: 5, Output: " + maxChunksToSorted(arr3));

        // Test Case 4: Single element
        System.out.println("\nSingle Element Case:");
        int[] arr4 = {1};
        System.out.println("Input: arr = [1]");
        System.out.println("Expected: 1, Output: " + maxChunksToSorted(arr4));
    }
}
