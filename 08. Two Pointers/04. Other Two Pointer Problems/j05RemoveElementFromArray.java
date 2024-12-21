/**
 * Problem Statement:
 * 
 *     Given an array `nums` and a value `val`, remove all occurrences of `val` in the array in-place. The relative order of the elements may be changed. 
 *     After removing the occurrences, return the new length of the array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the number of elements in the array.
 *     - An integer `val` (0 <= val <= 10^4), the value to be removed from the array.
 *     - An array `nums` of size `n` where each element satisfies (0 <= nums[i] <= 10^4).
 * 
 * Output:
 *     - An integer representing the new length of the array after removing all occurrences of `val`.
 *     - The first `k` elements of the array will contain the modified array, and the rest of the elements will be irrelevant.
 * 
 * Example:
 *     Input:
 *     nums = [3, 2, 2, 3]
 *     val = 3
 *     
 *     Output:
 *     2
 *     nums = [2, 2, _, _]
 * 
 *     Explanation:
 *     After removing `3`, the array becomes [2, 2], and the new length is 2.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j05RemoveElementFromArray {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        // Read the input array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Read the value to be removed
        int val = in.nextInt();

        // Print the original array
        System.out.println(Arrays.toString(arr));

        // Remove the elements and get the new length
        int k = removeElement(arr, val);

        // Print the new length and the modified array
        System.out.println(k);
        System.out.println(Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach: Two-pointer approach (O(n))
     * 
     * Intuition:
     * - We use two pointers to solve this problem efficiently in-place:
     *     - The first pointer (`j`) iterates over the entire array.
     *     - The second pointer (`i`) keeps track of the position to place the next valid element (not equal to `val`).
     * - If the element at index `j` is not equal to `val`, we place it at index `i`, and increment `i`.
     * - This effectively shifts all elements that are not equal to `val` to the beginning of the array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the array, because we only traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of space for the two pointers.
     * 
     * @param nums The input array.
     * @param val The value to be removed.
     * @return The length of the array after removing all occurrences of `val`.
     */
    public static int removeElement(int[] nums, int val) {
        int i = 0; // Pointer for the next valid position
        for (int j = 0; j < nums.length; j++) {
            // If the current element is not equal to 'val', place it at the next valid
            // position
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i; // Return the new length of the array
    }
}
