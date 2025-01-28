/**
 * Problem Statement:
 * 
 *      Given an array `arr` where the difference between adjacent elements is at most `k`, 
 *      implement an efficient algorithm to find the index of a target element `x`.
 *      If the element is not present, return -1.
 * 
 * Input:
 *     - An array `arr` of integers.
 *     - An integer `k` representing the maximum allowed difference between adjacent elements.
 *     - An integer `x` representing the target element to search for.
 * 
 * Output:
 *     - An integer representing the index of `x` in the array if found.
 *     - If `x` is not present, return -1.
 * 
 * Example:
 *     Test case 1:
 *         Input:
 *             arr = {2, 4, 5, 7, 7, 6}
 *             k = 2
 *             x = 6
 *         Output:
 *             Index = 5
 * 
 * Constraints:
 *     - The array may contain duplicate elements.
 *     - The array must adhere to the condition that the difference between adjacent elements is at most `k`.
 */

public class j02SearchInArrayAdjecentDiffAtMostK {
    public static void main(String[] args) {
        // Test case 1: Element is present in the array
        int[] arr1 = { 2, 4, 5, 7, 7, 6 };
        int k1 = 2;
        int x1 = 6;
        System.out.println("Test case 1: " + findStepKeyIndex(arr1, k1, x1)); // Expected output: 5

        // Test case 2: Element is not present in the array
        int[] arr2 = { 2, 4, 5, 7, 7, 6 };
        int k2 = 2;
        int x2 = 8;
        System.out.println("Test case 2: " + findStepKeyIndex(arr2, k2, x2)); // Expected output: -1

        // Test case 3: Element is at the beginning of the array
        int[] arr3 = { 2, 4, 5, 7, 7, 6 };
        int k3 = 2;
        int x3 = 2;
        System.out.println("Test case 3: " + findStepKeyIndex(arr3, k3, x3)); // Expected output: 0

        // Test case 4: Element is at the end of the array
        int[] arr4 = { 2, 4, 5, 7, 7, 6 };
        int k4 = 2;
        int x4 = 6;
        System.out.println("Test case 4: " + findStepKeyIndex(arr4, k4, x4)); // Expected output: 5

        // Test case 5: Array with only one element
        int[] arr5 = { 5 };
        int k5 = 1;
        int x5 = 5;
        System.out.println("Test case 5: " + findStepKeyIndex(arr5, k5, x5)); // Expected output: 0

        // Test case 6: Array with multiple occurrences of the element
        int[] arr6 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int k6 = 1;
        int x6 = 7;
        System.out.println("Test case 6: " + findStepKeyIndex(arr6, k6, x6)); // Expected output: 6
    }

    /**
     * Efficient Search in Array with Adjacent Difference At Most K
     * 
     * Intuition:
     * - The maximum difference between adjacent elements is `k`. This allows us to "jump" 
     *   directly to the approximate location of the target `x` instead of traversing 
     *   each element sequentially.
     * - If the current element is not equal to the target, the jump size is determined 
     *   by the absolute difference between the current element and the target divided by `k`.
     * - This reduces the search space significantly, ensuring an efficient solution.
     * 
     * Steps:
     * 1. Start from the first index (`index = 0`).
     * 2. If the current element is equal to the target, return the index.
     * 3. Calculate the jump size using the formula: `jump = Math.abs((arr[index] - x)) / k`.
     *    - Ensure the jump size is at least 1.
     * 4. Increment the index by the jump size.
     * 5. Repeat until the target is found or the array ends.
     * 
     * Time Complexity:
     * - O(n/k): Where `n` is the array length and `k` is the maximum allowed difference.
     * 
     * Space Complexity:
     * - O(1): Constant space usage.
     * 
     * @param arr The input array.
     * @param k The maximum difference between adjacent elements.
     * @param x The target element to find.
     * @return The index of the target element, or -1 if not found.
     */
    public static int findStepKeyIndex(int[] arr, int k, int x) {
        int index = 0; // Start from the first element
        while (index < arr.length) {
            if (arr[index] == x) // If the current element matches the target
                return index;

            // Calculate the jump size based on the difference with the target
            int jump = Math.abs((arr[index] - x)) / k;

            // Ensure the jump size is at least 1
            if (jump == 0)
                jump = 1;

            index += jump; // Move the index by the jump size
        }
        return -1; // If the target is not found
    }
}
