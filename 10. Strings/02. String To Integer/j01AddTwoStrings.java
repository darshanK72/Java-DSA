/**
 * Problem Statement:
 * 
 *     Given two non-negative integers represented as strings, return the sum of them as a string.
 *     You must not use any built-in BigInteger library or convert the inputs to integers directly.
 * 
 * Input:
 *     - Two strings `num1` and `num2` representing non-negative integers.
 *     - The length of each string is between 1 and 100.
 *     - Both strings contain only digits and represent valid integers.
 * 
 * Output:
 *     - Return a string representing the sum of `num1` and `num2`.
 * 
 * Example:
 *     Input:
 *     "11"
 *     "123"
 *     Output:
 *     "134"
 * 
 *     Explanation:
 *     The sum of 11 and 123 is 134.
 */

import java.util.Scanner;

public class j01AddTwoStrings {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        // Calling the function to add the strings and printing the result
        System.out.println(addStrings(s1, s2));
        in.close();
    }

    /**
     * Approach 1: Simple Iterative Approach
     * 
     * Intuition:
     * - We traverse both strings from the right end, simulating the process of addition in a column-wise fashion, as done manually.
     * - A carry variable is maintained to hold the overflow when the sum of two digits exceeds 9.
     * - At each step, we add the digits of both strings (if available) along with the carry from the previous step.
     * - After processing all digits, we append the final carry (if any) and reverse the result to get the correct sum.
     * 
     * Time Complexity:
     * - O(num1.length() + num2.length()) - We traverse both strings once, so the time complexity is proportional to the sum of their lengths.
     * 
     * Space Complexity:
     * - O(max(num1.length(), num2.length())) - The space complexity is determined by the result string, which is at most the length of the larger input string.
     * 
     * @param num1 The first string representing a number.
     * @param num2 The second string representing a number.
     * @return The sum of the numbers as a string.
     */
    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1; // Pointer for the last character of num1
        int j = num2.length() - 1; // Pointer for the last character of num2
        int carry = 0; // Initialize carry to 0
        StringBuilder ans = new StringBuilder(); // StringBuilder to build the result string

        // Loop until all digits are processed or there is a carry left
        while (i >= 0 || j >= 0 || carry > 0) {
            int d = carry; // Start with carry from previous step

            // Add digit from num1 if there is any
            if (i >= 0) {
                d += num1.charAt(i) - '0'; // Convert char to int
            }

            // Add digit from num2 if there is any
            if (j >= 0) {
                d += num2.charAt(j) - '0'; // Convert char to int
            }

            // Append the current digit to the result
            ans.append((char) ('0' + d % 10));

            // Calculate the new carry
            carry = d / 10;

            // Move to the next digits
            i--;
            j--;
        }

        // Reverse the result to get the correct order
        return ans.reverse().toString();
    }
}
