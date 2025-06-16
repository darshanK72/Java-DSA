/**
 * GeeksForGeeks: Maximize sum of consecutive differences in a circular array
 * 
 * Problem Statement:
 *     Given an array of n elements. Consider array as circular array i.e. element after an is a1.
 *     The task is to find maximum sum of the absolute difference between consecutive elements with
 *     rearrangement of array elements allowed i.e. after any rearrangement of array elements find
 *     |a1 – a2| + |a2 – a3| + …… + |an – 1 – an| + |an – a1|.
 * 
 * Input:
 *     - arr (Long[]): Array of numbers
 * 
 * Output:
 *     - long: Maximum possible sum of absolute differences
 * 
 * Example:
 *     Input: arr = {4, 2, 1, 8}
 *     Output: 18
 * 
 *     Explanation:
 *     - Rearrange array as {1, 8, 2, 4}
 *     - Sum = |1-8| + |8-2| + |2-4| + |4-1|
 *     - Sum = 7 + 6 + 2 + 3 = 18
 */

import java.util.Arrays;

public class j12SwapAndMaximize {

    /**
     * Approach 1: Alternating Min-Max Pattern
     * 
     * Intuition:
     * - To maximize sum, we need maximum difference between consecutive elements
     * - Sort array and alternate between smallest and largest elements
     * - This creates maximum possible differences between adjacent elements
     * 
     * Explanation:
     * - Step 1: Sort array in ascending order
     * - Step 2: Use two pointers (i at start, j at end)
     * - Step 3: Alternate between taking differences:
     *   - First take (max - min)
     *   - Then take (next max - next min)
     *   - Continue until pointers meet
     * - Step 4: Add final difference with first element
     * 
     * Time Complexity: O(n log n) where n is size of array
     *                  - Due to sorting
     * Space Complexity: O(1) as we modify array in-place
     * 
     * @param arr    Array of numbers
     * @return       Maximum possible sum of absolute differences
     */
    public static long maxSum1(Long[] arr) {
        // Sort array for min-max pattern
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        long ans = 0;
        int x = 0;
        
        // Alternate between min and max elements
        while (i < j) {
            ans += (arr[j] - arr[i]);
            if (x % 2 == 0)
                i++;
            else
                j--;
            x++;
        }

        // Add final difference with first element
        ans += (arr[j] - arr[0]);

        return ans;
    }

    /**
     * Approach 2: Symmetric Pattern
     * 
     * Intuition:
     * - For maximum sum, we can pair smallest with largest, second smallest with
     *   second largest, and so on
     * - This creates maximum possible differences between adjacent elements
     * - The pattern is symmetric around the middle
     * 
     * Explanation:
     * - Step 1: Sort array in ascending order
     * - Step 2: Use two pointers (i at start, j at end)
     * - Step 3: Take differences between paired elements
     * - Step 4: Double the sum as pattern is symmetric
     * 
     * Time Complexity: O(n log n) where n is size of array
     *                  - Due to sorting
     * Space Complexity: O(1) as we modify array in-place
     * 
     * @param arr    Array of numbers
     * @return       Maximum possible sum of absolute differences
     */
    public static long maxSum2(Long[] arr) {
        // Sort array for symmetric pattern
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        long ans = 0;
        
        // Take differences between paired elements
        while (i < j) {
            ans += (arr[j] - arr[i]);
            i++;
            j--;
        }

        // Double the sum as pattern is symmetric
        return 2 * ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        Long[] arr1 = {4L, 2L, 1L, 8L};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Expected: 18");
        System.out.println("Output (Approach 1): " + maxSum1(arr1));
        System.out.println("Output (Approach 2): " + maxSum2(arr1));

        // Test Case 2: Already sorted array
        System.out.println("\nSpecial Case - Already Sorted:");
        Long[] arr2 = {1L, 2L, 3L, 4L};
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Expected: 6");
        System.out.println("Output (Approach 1): " + maxSum1(arr2));
        System.out.println("Output (Approach 2): " + maxSum2(arr2));

        // Test Case 3: All same elements
        System.out.println("\nSpecial Case - All Same Elements:");
        Long[] arr3 = {5L, 5L, 5L, 5L};
        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("Expected: 0");
        System.out.println("Output (Approach 1): " + maxSum1(arr3));
        System.out.println("Output (Approach 2): " + maxSum2(arr3));

        // Test Case 4: Two elements
        System.out.println("\nEdge Case - Two Elements:");
        Long[] arr4 = {1L, 2L};
        System.out.println("Input: " + Arrays.toString(arr4));
        System.out.println("Expected: 2");
        System.out.println("Output (Approach 1): " + maxSum1(arr4));
        System.out.println("Output (Approach 2): " + maxSum2(arr4));

        // Test Case 5: Large numbers
        System.out.println("\nSpecial Case - Large Numbers:");
        Long[] arr5 = {1000L, 1L, 999L, 2L};
        System.out.println("Input: " + Arrays.toString(arr5));
        System.out.println("Expected: 1996");
        System.out.println("Output (Approach 1): " + maxSum1(arr5));
        System.out.println("Output (Approach 2): " + maxSum2(arr5));
    }
}
