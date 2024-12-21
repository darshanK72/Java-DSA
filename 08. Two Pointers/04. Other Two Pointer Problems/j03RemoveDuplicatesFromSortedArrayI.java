/**
 * Problem Statement:
 * 
 *     Given a sorted array `arr`, remove the duplicates in-place such that each element appears only once and return the new length of the array.
 *     It is guaranteed that the array is sorted in non-decreasing order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the number of elements in the array.
 *     - An array `arr` of size `n` where each element satisfies (0 <= arr[i] <= 10^4).
 * 
 * Output:
 *     - An integer representing the new length of the array after removing duplicates.
 *     - The modified array will contain the unique elements in the first `k` positions, where `k` is the returned length.
 * 
 * Example:
 *     Input:
 *     arr = {1, 1, 2}
 *     
 *     Output:
 *     2
 *     arr = {1, 2, _}
 * 
 *     Explanation:
 *     After removing the duplicates, the array becomes {1, 2}, and the new length is 2.
 */

import java.util.Scanner;
import java.util.Arrays;

public class j03RemoveDuplicatesFromSortedArrayI {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        // Read the input array
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // Print the original array
        System.out.println(Arrays.toString(arr));

        // Remove duplicates and get the new length
        int k = removeDuplicates(arr);

        // Print the new length and the modified array
        System.out.println(k);
        System.out.println(Arrays.toString(arr));

        in.close();
    }

    /**
     * Approach: Two-pointer approach (O(n))
     * 
     * Intuition:
     * - Since the array is already sorted, duplicates will be adjacent. We can use a two-pointer approach to remove duplicates in-place.
     * - The first pointer (`i`) iterates through the array to check each element, and the second pointer (`k`) keeps track of the unique elements.
     * - If the current element (`arr[i]`) is not equal to the last unique element (`arr[k - 1]`), we add it to the unique section of the array.
     * - This way, the array is modified in place, and the length of the unique section is returned.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the array, as we only traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space for pointers.
     * 
     * @param arr The input sorted array.
     * @return The length of the array after removing duplicates.
     */
    public static int removeDuplicates(int[] arr) {
        int k = 1; // We start with the first element as unique
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[k - 1]) {
                arr[k] = arr[i]; // Assign the unique element to the next position
                k++; // Increment the count of unique elements
            }
        }
        return k; // Return the length of the array with unique elements
    }
}
