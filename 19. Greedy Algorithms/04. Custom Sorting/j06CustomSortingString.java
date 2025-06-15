/**
 * LeetCode 791. Custom Sort String
 * 
 * Problem Statement:
 *     You are given two strings order and s. All the characters of order are unique and were sorted
 *     in some custom order previously. Permute the characters of s so that they match the order that
 *     order was sorted. More specifically, if a character x occurs before a character y in order,
 *     then x should occur before y in the permuted string.
 * 
 * Input:
 *     - order (String): A string containing unique characters in custom order
 *     - s (String): The string to be sorted according to order
 * 
 * Output:
 *     - String: The permuted string s sorted according to the custom order
 * 
 * Example:
 *     Input: order = "cba", s = "abcd"
 *     Output: "cbad"
 * 
 *     Explanation:
 *     - 'c' comes before 'b' in order, so 'c' comes first in output
 *     - 'b' comes before 'a' in order, so 'b' comes second
 *     - 'a' comes next
 *     - 'd' is not in order, so it's placed at the end
 */

import java.util.Arrays;

public class j06CustomSortingString {

    /**
     * Approach: Custom Comparator with Array Sorting
     * 
     * Intuition:
     * - We can use the order string as a reference for sorting
     * - Characters in order string have their positions as their sorting priority
     * - Characters not in order string should be placed at the end
     * 
     * Explanation:
     * - Step 1: Convert input string to Character array for sorting
     * - Step 2: Use custom comparator that compares positions in order string
     * - Step 3: Sort array using the custom comparator
     * - Step 4: Convert sorted array back to string
     * 
     * Time Complexity: O(n log n) where n is length of string s
     *                  - Due to Arrays.sort() which uses TimSort
     * Space Complexity: O(n) for storing Character array
     * 
     * @param order    String containing unique characters in custom order
     * @param s        String to be sorted according to order
     * @return         Sorted string according to custom order
     */
    public static String customSortString(String order, String s) {
        // Convert string to Character array for sorting
        Character[] chars = new Character[s.length()];
        for (int i = 0; i < s.length(); i++)
            chars[i] = s.charAt(i);

        // Sort using custom comparator based on order string
        Arrays.sort(chars, (a, b) -> {
            return order.indexOf(a) - order.indexOf(b);
        });

        // Convert sorted array back to string
        StringBuilder out = new StringBuilder();
        for (char c : chars) {
            out.append(c);
        }
        return out.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        System.out.println("Input: order = \"cba\", s = \"abcd\"");
        System.out.println("Expected: \"cbad\", Output: " + 
            customSortString("cba", "abcd"));

        // Test Case 2: Empty string
        System.out.println("\nEdge Case - Empty String:");
        System.out.println("Input: order = \"cba\", s = \"\"");
        System.out.println("Expected: \"\", Output: " + 
            customSortString("cba", ""));

        // Test Case 3: All characters in order
        System.out.println("\nSpecial Case - All chars in order:");
        System.out.println("Input: order = \"cba\", s = \"cba\"");
        System.out.println("Expected: \"cba\", Output: " + 
            customSortString("cba", "cba"));

        // Test Case 4: No characters in order
        System.out.println("\nSpecial Case - No chars in order:");
        System.out.println("Input: order = \"cba\", s = \"xyz\"");
        System.out.println("Expected: \"xyz\", Output: " + 
            customSortString("cba", "xyz"));
    }
}
