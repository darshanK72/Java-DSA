/**
 * LeetCode 1502. Can Make Arithmetic Progression From Sequence
 * 
 * Problem Statement:
 *     Given an array of numbers arr, return true if the array can be rearranged to form an
 *     arithmetic progression. Otherwise, return false. A sequence of numbers is called an
 *     arithmetic progression if the difference between any two consecutive elements is the same.
 * 
 * Input:
 *     - arr (int[]): Array of numbers to check
 * 
 * Output:
 *     - boolean: True if array can form arithmetic progression, false otherwise
 * 
 * Example:
 *     Input: arr = [3, 5, 1]
 *     Output: true
 * 
 *     Explanation:
 *     - We can reorder the elements as [1, 3, 5] or [5, 3, 1]
 *     - The difference between consecutive elements is 2
 *     - [1, 3, 5] forms an arithmetic progression with common difference 2
 */

import java.util.Arrays;

public class j10ArithmeticProgressionInSequence {

    /**
     * Approach: Sorting and Difference Check
     * 
     * Intuition:
     * - For a sequence to be an arithmetic progression, differences between consecutive
     *   elements must be constant
     * - After sorting, we can easily check if differences are equal
     * - If any difference differs from the first difference, sequence is not arithmetic
     * 
     * Explanation:
     * - Step 1: Sort array to get elements in ascending order
     * - Step 2: Calculate common difference from first two elements
     * - Step 3: Check if difference between all consecutive pairs is same
     * - Step 4: Return true if all differences match, false otherwise
     * 
     * Time Complexity: O(n log n) where n is size of array
     *                  - Due to sorting of array
     * Space Complexity: O(1) as we modify array in-place
     * 
     * @param arr    Array of numbers to check
     * @return       True if array can form arithmetic progression, false otherwise
     */
    public static boolean canMakeArithmeticProgression(int[] arr) {
        // Sort array to get elements in ascending order
        Arrays.sort(arr);
        
        // Calculate common difference from first two elements
        int d = arr[1] - arr[0];
        
        // Check if difference between all consecutive pairs is same
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != d)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case - valid arithmetic progression
        System.out.println("\nBasic Test Case - Valid:");
        int[] arr1 = {3, 5, 1};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Expected: true, Output: " + canMakeArithmeticProgression(arr1));

        // Test Case 2: Basic case - invalid arithmetic progression
        System.out.println("\nBasic Test Case - Invalid:");
        int[] arr2 = {1, 2, 4};
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Expected: false, Output: " + canMakeArithmeticProgression(arr2));

        // Test Case 3: Already sorted arithmetic progression
        System.out.println("\nSpecial Case - Already Sorted:");
        int[] arr3 = {1, 3, 5, 7, 9};
        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("Expected: true, Output: " + canMakeArithmeticProgression(arr3));

        // Test Case 4: Single element
        System.out.println("\nEdge Case - Single Element:");
        int[] arr4 = {5};
        System.out.println("Input: " + Arrays.toString(arr4));
        System.out.println("Expected: true, Output: " + canMakeArithmeticProgression(arr4));

        // Test Case 5: Two elements
        System.out.println("\nEdge Case - Two Elements:");
        int[] arr5 = {1, 2};
        System.out.println("Input: " + Arrays.toString(arr5));
        System.out.println("Expected: true, Output: " + canMakeArithmeticProgression(arr5));

        // Test Case 6: All same elements
        System.out.println("\nSpecial Case - All Same Elements:");
        int[] arr6 = {2, 2, 2, 2};
        System.out.println("Input: " + Arrays.toString(arr6));
        System.out.println("Expected: true, Output: " + canMakeArithmeticProgression(arr6));
    }
}
