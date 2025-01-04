/**
 * Problem Statement:
 * 
 *     You are given a sorted array `nums` where every element appears exactly twice, except for one element which appears only once. 
 *     Find that single element that appears only once.
 *     Your solution should run in O(log n) time complexity.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - A sorted array `nums` of size `n` where every element appears exactly twice, except one element that appears only once.
 * 
 * Output:
 *     - The element that appears only once in the sorted array.
 * 
 * Example:
 *     Input:
 *     7
 *     1 1 2 2 3 4 4
 *     Output:
 *     3
 *     Explanation: The single element that appears only once is `3`.
 * 
 *     Input:
 *     5
 *     0 1 1 2 2
 *     Output:
 *     0
 *     Explanation: The single element that appears only once is `0`.
 */

import java.util.Scanner;

public class j04SingleElementInSortedArray {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // The size of the array
        int[] arr = new int[n];

        // Reading the array elements
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input each element of the array
        }

        // Call the function to find the single non-duplicate element
        System.out.println(singleNonDuplicate(arr));
        in.close();
    }

    /**
     * Approach 1: Binary Search for Single Non-Duplicate Element
     * 
     * Intuition:
     * - We are given a sorted array where all elements appear twice except one element which appears only once. 
     *   We can use binary search to efficiently find this single element in O(log n) time.
     * - The key observation is that in a sorted array, elements that appear twice will be adjacent to each other.
     *   If the middle element is different from both of its neighbors, it is the single element. 
     * - If the middle element matches the next one and the index is even, or if the middle element matches the previous one and the index is odd,
     *   we know that the single element lies in the right half. Otherwise, it lies in the left half.
     * 
     * Time Complexity:
     * - O(log n), as we are using binary search to reduce the search space.
     * 
     * Space Complexity:
     * - O(1), using constant space for the binary search.
     * 
     * @param nums The input array of numbers.
     * @return The element that appears only once in the array.
     */
    public static int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        // Handle base cases
        if (n == 1)
            return nums[0]; // Single element in the array
        if (nums[0] != nums[1])
            return nums[0]; // First element is unique
        if (nums[n - 1] != nums[n - 2])
            return nums[n - 1]; // Last element is unique

        int s = 1;
        int e = n - 2;

        // Binary search to find the single element
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid]; // Found the single element
            }

            // If the mid element matches the next element and mid is even or if it matches
            // the previous and mid is odd,
            // the unique element lies to the right side of mid.
            if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || (mid % 2 == 1 && nums[mid] == nums[mid - 1])) {
                s = mid + 1;
            } else {
                e = mid - 1; // Otherwise, it lies to the left side of mid
            }
        }

        return -1; // This line will never be reached, as the problem guarantees a single element.
    }
}
