/*-
 * Problem Statement:
 * 
 *      Implement the Jump Search algorithm to efficiently search for a target element 
 *      in a sorted array. Jump Search uses a step-based approach to divide the array into blocks
 *      and then performs a linear search within a block where the target might exist.
 * 
 * Input:
 *     - A sorted array `arr` of integers.
 *     - An integer `target` representing the element to search for.
 * 
 * Output:
 *     - An integer representing the index of the target element if found.
 *     - If the target is not found, return -1.
 * 
 * Example:
 *     Input:
 *         arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21}
 *         target = 15
 *     Output:
 *         Element 15 found at index 7
 * 
 * Constraints:
 *     - The input array must be sorted.
 */

public class j01JumpSearchInSortedArray {

    public static void main(String[] args) {
        // Example array (must be sorted)
        int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21 };
        int target = 15; // Target to find

        // Perform Jump Search
        int index = jumpSearch(arr, target);

        // Output the result
        if (index != -1) {
            System.out.println("Element " + target + " found at index " + index);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }
    }

    /*-
     * Jump Search Algorithm
     * 
     * Intuition:
     * - Jump Search divides the array into fixed-size blocks and checks the last element 
     *   of each block. If the target is smaller than the last element of a block, 
     *   a linear search is performed within that block.
     * 
     * Steps:
     * 1. Calculate the step size as the square root of the array size.
     * 2. Perform a jump through the array blocks to find the block where the target might exist.
     * 3. Once the block is identified, perform a linear search within the block.
     * 4. Return the index of the target if found, otherwise return -1.
     * 
     * Time Complexity:
     * - O(√n): Jumping through blocks and linear searching in one block.
     * 
     * Space Complexity:
     * - O(1): Constant space is used.
     * 
     * @param arr The sorted array to search in.
     * @param target The element to search for.
     * @return The index of the target element, or -1 if not found.
     */
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length; // Get the array size

        // Step size for jumping: square root of array size
        int step = (int) Math.sqrt(n);
        int prev = 0; // Tracks the start of the current block

        // Step 1: Jump ahead until we find a block where the target might exist
        while (arr[Math.min(step, n) - 1] < target) {
            prev = step; // Move to the next block
            step += (int) Math.sqrt(n); // Calculate the next step
            if (prev >= n) { // If we've gone past the array size, the target is not present
                return -1;
            }
        }

        // Step 2: Perform linear search in the identified block
        while (arr[prev] < target) {
            prev++; // Move one element at a time
            if (prev == Math.min(step, n)) { // Reached the end of the block or array
                return -1;
            }
        }

        // Step 3: Check if the element is found
        if (arr[prev] == target) {
            return prev; // Return the index of the target
        }

        return -1; // Target is not present
    }
}
