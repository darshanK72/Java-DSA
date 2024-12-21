/**
 * Problem Statement:
 * 
 *     Given two non-negative integers represented as strings, subtract the second number from the first number and return the result as a string.
 *     The result should not have leading zeros, unless the result is zero itself.
 *     If the first number is smaller than the second, return a negative result.
 * 
 * Input:
 *     - Two strings `num1` and `num2` representing non-negative integers.
 *     - Both strings contain only digits and may have leading zeros.
 * 
 * Output:
 *     - Return a string representing the difference between the two numbers.
 *     - If `num1` is smaller than `num2`, the result should be negative.
 * 
 * Example:
 *     Input:
 *     "123"
 *     "45"
 *     Output:
 *     "78"
 * 
 *     Explanation:
 *     123 - 45 = 78.
 */

import java.util.Scanner;

public class j03SubtractTwoStrings {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        // Calling the function to subtract the strings and printing the result
        System.out.println(subtract(s1, s2));
        in.close();
    }

    /**
     * Approach 1: String-based Subtraction with Carry Handling
     * 
     * Intuition:
     * - To subtract two numbers represented as strings, we start from the rightmost end of both strings.
     * - For each digit, we subtract the digits of both numbers (taking care of the carry).
     * - If the first digit is smaller than the second digit, we perform "borrow", i.e., add 10 to the first digit and set a carry of 1.
     * - After processing all digits, we reverse the result string to get the correct answer.
     * - If the result is negative, we return the result with a minus sign.
     * 
     * Time Complexity:
     * - O(num1.length() + num2.length()) - We iterate through both strings once to subtract corresponding digits and handle carries.
     * 
     * Space Complexity:
     * - O(max(num1.length(), num2.length())) - We use a StringBuilder to store the result, which has at most the length of the larger number.
     * 
     * @param num1 The first string representing a number.
     * @param num2 The second string representing a number.
     * @return The difference between the numbers as a string.
     */
    public static String subtract(String num1, String num2) {
        // Handling the case where num1 is smaller than num2
        if (num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0)) {
            return "-" + subtract(num2, num1);
        }

        int i = num1.length() - 1; // Pointer for num1 (start from the least significant digit)
        int j = num2.length() - 1; // Pointer for num2
        int carry = 0; // Carry for handling borrow
        StringBuilder ans = new StringBuilder(); // StringBuilder to hold the result

        // Iterate through both strings from right to left
        while (i >= 0 || j >= 0) {
            int d1 = i >= 0 ? num1.charAt(i) - '0' : 0; // Get digit from num1 or 0 if out of bounds
            int d2 = j >= 0 ? num2.charAt(j) - '0' : 0; // Get digit from num2 or 0 if out of bounds

            // Subtract the digits with carry
            if (d1 >= (d2 + carry)) {
                ans.append((char) ('0' + (d1 - (d2 + carry)))); // No borrow, simply subtract
                carry = 0;
            } else {
                ans.append((char) ('0' + ((d1 + 10) - (d2 + carry)))); // Borrow: add 10 to the current digit of num1
                carry = 1; // Set carry for the next iteration
            }
            i--;
            j--;
        }

        // Remove leading zeros from the result
        while (ans.length() > 1 && ans.charAt(ans.length() - 1) == '0') {
            ans.setLength(ans.length() - 1);
        }

        // Reverse the result to get the correct order
        return ans.reverse().toString();
    }
}
