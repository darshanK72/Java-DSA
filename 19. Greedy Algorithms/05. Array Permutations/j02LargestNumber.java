/**
 * LeetCode 179. Largest Number
 * 
 * Problem Statement:
 *     Given a list of non-negative integers nums, arrange them such that they form
 *     the largest number and return it as a string. The result may be very large,
 *     so you need to return a string instead of an integer.
 * 
 * Input:
 *     - nums (int[]): Array of non-negative integers
 *     - 1 <= nums.length <= 100
 *     - 0 <= nums[i] <= 10^9
 * 
 * Output:
 *     - String representing the largest possible number formed by arranging the
 *       given integers
 * 
 * Example:
 *     Input: nums = [10,2]
 *     Output: "210"
 * 
 *     Explanation:
 *     The possible arrangements are:
 *     - "102" (10 followed by 2)
 *     - "210" (2 followed by 10)
 *     "210" is the largest number.
 */

import java.util.ArrayList;
import java.util.Collections;

public class j02LargestNumber {
    
    /**
     * Approach: Custom Sorting
     * 
     * Intuition:
     * - To form the largest number, we need to arrange numbers in a way that
     *   maximizes the value when concatenated
     * - The key insight is to compare numbers by their concatenated forms
     * - For any two numbers a and b, we compare (b+a) with (a+b)
     * - If (b+a) > (a+b), then b should come before a
     * - This ensures that the concatenated result is maximized
     * 
     * Explanation:
     * 1. Convert all numbers to strings for easier concatenation
     * 2. Sort the strings using custom comparator:
     *    - Compare concatenated forms (b+a) and (a+b)
     *    - Place larger concatenation first
     * 3. Handle special case where all numbers are 0
     * 4. Join all strings to form final result
     * 
     * Time Complexity: O(n log n) where n is the length of nums array
     *                  Due to sorting operation
     * Space Complexity: O(n) to store string representations
     * 
     * @param nums Array of non-negative integers
     * @return    String representing the largest possible number
     */
    public static String largestNumber(int[] nums) {
        // Convert numbers to strings for easier concatenation
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            list.add(Integer.toString(nums[i]));

        // Sort using custom comparator
        Collections.sort(list, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        // Handle case where all numbers are 0
        if (list.get(0).charAt(0) == '0')
            return "0";

        // Join all strings to form final result
        return String.join("", list);
    }

    public static void main(String[] args) {
        
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        int[] nums1 = {10, 2};
        System.out.println("Input: " + java.util.Arrays.toString(nums1));
        System.out.println("Expected: 210, Output: " + largestNumber(nums1));

        // Test Case 2: All zeros
        System.out.println("\nAll Zeros Test Case:");
        int[] nums2 = {0, 0, 0};
        System.out.println("Input: " + java.util.Arrays.toString(nums2));
        System.out.println("Expected: 0, Output: " + largestNumber(nums2));

        // Test Case 3: Single digit numbers
        System.out.println("\nSingle Digit Test Case:");
        int[] nums3 = {3, 30, 34, 5, 9};
        System.out.println("Input: " + java.util.Arrays.toString(nums3));
        System.out.println("Expected: 9534330, Output: " + largestNumber(nums3));

        // Test Case 4: Large numbers
        System.out.println("\nLarge Numbers Test Case:");
        int[] nums4 = {999999991, 9};
        System.out.println("Input: " + java.util.Arrays.toString(nums4));
        System.out.println("Expected: 9999999991, Output: " + largestNumber(nums4));
    }
}
