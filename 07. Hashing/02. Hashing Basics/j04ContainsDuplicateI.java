/**
 * Problem Statement:
 *     Given an integer array, determine if any value appears at least twice in the array. Return true if any value appears at least twice, and false if every element is distinct.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the array.
 *     - An array `arr` of size `n` where each element satisfies (-10^9 <= arr[i] <= 10^9).
 * 
 * Output:
 *     - Boolean value: true if there are any duplicates in the array, false otherwise.
 * 
 * Example:
 *     Input:
 *     5
 *     1 2 3 4 5
 *     Output:
 *     false
 * 
 *     Input:
 *     6
 *     1 2 3 1 5 6
 *     Output:
 *     true
 * 
 *     Explanation:
 *     In the second example, the number 1 appears twice, so the output is true.
 */

import java.util.HashSet;
import java.util.Scanner;

public class j04ContainsDuplicateI {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt(); // Input: array elements
        }

        System.out.println(containsDuplicate(arr)); // Output the result of the containsDuplicate method
        in.close();
    }

    /**
     * Approach: Using HashSet to check for duplicates
     * 
     * Intuition:
     * - We can use a HashSet to keep track of the elements we've encountered. 
     * - If we encounter an element that is already in the set, it means there's a duplicate, so return true.
     * - Otherwise, we add the element to the set and continue checking the next elements.
     * 
     * Time Complexity:
     * - O(n), where n is the size of the array. This is because we traverse the array once, and checking if an element is in a HashSet takes O(1) on average.
     * 
     * Space Complexity:
     * - O(n), as we store each element in the HashSet in the worst case (if there are no duplicates).
     * 
     * @param nums The input array of numbers.
     * @return A boolean indicating if there are any duplicates in the array.
     */
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>(); // Create a HashSet to store elements
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true; // If the current element is already in the set, a duplicate exists
            else
                set.add(nums[i]); // Otherwise, add the element to the set
        }
        return false; // If no duplicates are found, return false
    }
}
