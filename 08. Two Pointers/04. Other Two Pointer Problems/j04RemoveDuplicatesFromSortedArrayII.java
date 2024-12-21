/**
 * Problem Statement:
 * 
 *     Given a sorted array `arr`, remove the duplicates such that each element appears at most twice and return the new length.
 *     The array is sorted in non-decreasing order.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), the number of elements in the array.
 *     - An array `arr` of size `n` where each element satisfies (0 <= arr[i] <= 10^4).
 * 
 * Output:
 *     - An integer representing the new length of the array after removing the duplicates such that each element appears at most twice.
 *     - The modified array will contain the unique elements (up to 2 occurrences) in the first `k` positions, where `k` is the returned length.
 * 
 * Example:
 *     Input:
 *     arr = {1, 1, 1, 2, 2, 3}
 *     
 *     Output:
 *     5
 *     arr = {1, 1, 2, 2, 3, _}
 * 
 *     Explanation:
 *     After removing the duplicates, the array becomes {1, 1, 2, 2, 3}, and the new length is 5.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j04RemoveDuplicatesFromSortedArrayII {

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
     * - Since the array is sorted, we can use a modified two-pointer approach to ensure that no element appears more than twice.
     * - The first pointer (`i`) traverses the array, while the second pointer (`j`) keeps track of the position for unique elements (up to two occurrences).
     * - We check for each element: If the current element is not equal to the last unique element, we add it. If it's equal to the last unique element but not repeating more than twice, we also add it.
     * - This way, we maintain the condition of having at most two occurrences of any element in the modified array.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the array, as we traverse the array once.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space for pointers.
     * 
     * @param nums The input sorted array.
     * @return The length of the array after removing duplicates, ensuring each element appears at most twice.
     */
    public static int removeDuplicates(int[] nums) {
        int j = 1; // Start from the second element
        for (int i = 2; i < nums.length; i++) {
            // Case 1: The current element is not the same as the last unique element
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i]; // Assign the unique element to the next position
            }
            // Case 2: The current element is the same as the last unique element but
            // appears for the first time
            else if (nums[j] == nums[i] && nums[j - 1] != nums[i]) {
                j++;
                nums[j] = nums[i]; // Assign the element to the next position
            }
        }
        return j + 1; // Return the length of the array with unique elements (up to 2 occurrences)
    }
}
