/**
 * Problem Statement:
 * 
 *     Given a Roman numeral, convert it to an integer. Roman numerals are represented by seven different symbols: 
 *     I, V, X, L, C, D, M. The value of a Roman numeral is the sum of the values of the symbols that make it up, 
 *     with the following exceptions:
 *     - I can be placed before V (5) and X (10) to make 4 and 9. 
 *     - X can be placed before L (50) and C (100) to make 40 and 90.
 *     - C can be placed before D (500) and M (1000) to make 400 and 900.
 * 
 * Input:
 *     - A string `s` representing the Roman numeral (1 <= s.length() <= 15).
 * 
 * Output:
 *     - The integer value of the given Roman numeral.
 * 
 * Example:
 *     Input:
 *     "III"
 *     Output:
 *     3
 * 
 *     Input:
 *     "IV"
 *     Output:
 *     4
 * 
 *     Input:
 *     "IX"
 *     Output:
 *     9
 * 
 *     Input:
 *     "LVIII"
 *     Output:
 *     58
 * 
 *     Input:
 *     "MCMXCIV"
 *     Output:
 *     1994
 */

import java.util.Scanner;

public class j07RomanToInteger {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String num = in.nextLine(); // Input: Roman numeral string
        // Calling the romanToInt method and printing the result
        System.out.println(romanToInt(num));
        in.close();
    }

    /**
     * Approach: Roman to Integer Conversion
     * 
     * Intuition:
     * - The Roman numeral system has a specific rule where certain smaller values placed before larger values are subtracted.
     * - We iterate through the Roman numeral string, calculating the value of each character and adjusting for any subtraction rules.
     * - Specifically, if a character represents a value smaller than the character that follows it (e.g., I before V or X), 
     *   we subtract the smaller value instead of adding it.
     * 
     * Time Complexity:
     * - O(s.length()), where `s` is the length of the input string. We traverse the string once.
     * 
     * Space Complexity:
     * - O(1), as we use a constant amount of extra space for the result and helper variables.
     * 
     * @param s The input Roman numeral string.
     * @return The integer value corresponding to the Roman numeral.
     */
    public static int romanToInt(String s) {
        int ans = 0;
        int num = 0;
        // Loop through each character in the string
        for (int i = 0; i < s.length(); i++) {
            // Get the integer value of the current Roman numeral character
            num = getVal(s.charAt(i));
            // If the next character represents a larger value, subtract the current value
            if (i < s.length() - 1 && num < getVal(s.charAt(i + 1))) {
                ans -= num;
            } else {
                ans += num;
            }
        }
        return ans;
    }

    /**
     * Helper Method: getVal
     * 
     * Intuition:
     * - This helper method converts a Roman numeral character to its corresponding integer value.
     * 
     * Time Complexity:
     * - O(1), as it performs a constant time operation (a switch statement).
     * 
     * Space Complexity:
     * - O(1), as we only return a single integer value.
     * 
     * @param c The Roman numeral character.
     * @return The corresponding integer value of the Roman numeral.
     */
    public static int getVal(char c) {
        int number = 0;
        switch (c) {
            case 'M' -> number = 1000;
            case 'D' -> number = 500;
            case 'C' -> number = 100;
            case 'L' -> number = 50;
            case 'X' -> number = 10;
            case 'V' -> number = 5;
            case 'I' -> number = 1;
        }
        return number;
    }
}
