/**
 * Problem Statement:
 * 
 *     Given two non-negative integers represented as strings, compare their values without converting them directly to integers.
 *     Return:
 *     - 1 if the first number is smaller than the second.
 *     - 2 if the first number is larger than the second.
 *     - 3 if both numbers are equal.
 *     Leading zeros should not be considered in the comparison.
 * 
 * Input:
 *     - Two strings `a` and `b` representing non-negative integers.
 *     - Both strings contain only digits, and may have leading zeros.
 * 
 * Output:
 *     - Return an integer:
 *         - 1 if the first number is smaller than the second.
 *         - 2 if the first number is larger than the second.
 *         - 3 if both numbers are equal.
 * 
 * Example:
 *     Input:
 *     "000123"
 *     "123"
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The numbers 123 and 123 are equal after removing the leading zeros.
 */

import java.util.Scanner;

public class j02CompareToNumbers {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        // Calling the function to compare the two numbers and printing the result
        System.out.println(compare(s1, s2));
        in.close();
    }

    /**
     * Approach 1: String Length and Character-by-Character Comparison
     * 
     * Intuition:
     * - To compare two numbers represented as strings, we first strip any leading zeros, as they do not affect the value of the number.
     * - After removing leading zeros, we compare the lengths of the two strings:
     *     - If one string is longer, it represents the larger number.
     *     - If the strings are of the same length, we compare their characters one by one from left to right.
     * - If all characters are the same, the numbers are equal.
     * 
     * Time Complexity:
     * - O(n + m), where n is the length of the first string and m is the length of the second string. We iterate through both strings once to remove leading zeros and compare characters.
     * 
     * Space Complexity:
     * - O(1) - We use a constant amount of extra space, apart from the input strings.
     * 
     * @param a The first string representing a number.
     * @param b The second string representing a number.
     * @return 1 if the first number is smaller, 2 if larger, or 3 if equal.
     */
    public static int compare(String a, String b) {
        // Removing leading zeros from both strings
        StringBuilder aa = new StringBuilder(a);
        StringBuilder bb = new StringBuilder(b);

        while (aa.length() > 0 && aa.charAt(0) == '0') {
            aa.deleteCharAt(0);
        }
        while (bb.length() > 0 && bb.charAt(0) == '0') {
            bb.deleteCharAt(0);
        }

        // Compare lengths of the strings after removing leading zeros
        if (aa.length() < bb.length()) {
            return 1; // a is smaller than b
        } else if (aa.length() > bb.length()) {
            return 2; // a is larger than b
        } else {
            // If lengths are equal, compare the characters one by one
            for (int i = 0; i < aa.length(); i++) {
                if (aa.charAt(i) < bb.charAt(i)) {
                    return 1; // a is smaller than b
                } else if (aa.charAt(i) > bb.charAt(i)) {
                    return 2; // a is larger than b
                }
            }
        }
        return 3; // a and b are equal
    }
}
