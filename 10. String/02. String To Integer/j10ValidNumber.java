/**
 * Problem Statement:
 * 
 *     Validate if a given string is a valid number according to specific rules.
 *     A valid number can be an integer or a decimal number with optional signs ('+' or '-'), and it can contain an optional exponent ('e' or 'E').
 *     The rules for a valid number are as follows:
 *     - A number may optionally begin with '+' or '-' sign.
 *     - A valid number can contain digits (0-9).
 *     - A number can have at most one decimal point ('.').
 *     - A valid number can optionally contain an exponent ('e' or 'E') followed by an integer.
 *     - The number should not have any other characters or formatting.
 * 
 * Input:
 *     - A string `s` representing the number to validate (1 <= s.length() <= 20).
 * 
 * Output:
 *     - Return `true` if the string is a valid number, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     "0"
 *     Output:
 *     true
 * 
 *     Input:
 *     " 0.1 "
 *     Output:
 *     true
 * 
 *     Input:
 *     "abc"
 *     Output:
 *     false
 * 
 *     Input:
 *     "1e10"
 *     Output:
 *     true
 * 
 *     Input:
 *     "1e"
 *     Output:
 *     false
 */

import java.util.Scanner;

public class j10ValidNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the string to validate
        // Calling the validNumber method and printing the result
        System.out.println(validNumber(s));
        in.close();
    }

    /**
     * Approach: Parsing and Validating the Number String
     * 
     * Intuition:
     * - The goal is to validate if the string can be considered a valid number under the rules specified.
     * - The string can contain digits, a decimal point, and an exponent ('e' or 'E'). We need to ensure each of these components follows the correct pattern.
     * - We use a finite set of rules:
     *   - Only one decimal point is allowed.
     *   - The exponent can only appear once, and it must be followed by an integer (which may also include a sign).
     *   - A number may start with a '+' or '-' sign.
     * - We process each character in the string and track whether the decimal point, exponent, or digits have appeared, ensuring no invalid sequence appears.
     * 
     * Time Complexity:
     * - O(s.length()), where `s` is the input string. We process each character of the string once.
     * 
     * Space Complexity:
     * - O(1), since we use only a fixed number of variables to track the state.
     * 
     * @param s The input string representing the number to validate.
     * @return `true` if the string is a valid number, otherwise `false`.
     */
    public static boolean validNumber(String s) {
        // Edge case: Empty string is not a valid number
        if (s.isEmpty())
            return false;

        // Flags for tracking components of the number
        boolean hasNum = false; // Tracks if there is at least one digit
        boolean hasDot = false; // Tracks if a decimal point has appeared
        boolean hasExp = false; // Tracks if an exponent has appeared

        // Iterate over the string to check each character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Case 1: Current character is a digit (0-9)
            if (c >= '0' && c <= '9') {
                hasNum = true; // Set the flag for digits
            }
            // Case 2: Current character is a decimal point
            else if (c == '.') {
                // Decimal point is valid if we haven't seen one yet and there's no exponent
                if (hasDot || hasExp)
                    return false;
                hasDot = true; // Mark that we've seen a decimal point
            }
            // Case 3: Current character is 'e' or 'E' (exponent)
            else if (c == 'e' || c == 'E') {
                // Exponent is valid if we haven't seen one already and there's at least one
                // digit before it
                if (hasExp || !hasNum)
                    return false;
                hasExp = true; // Mark that we've seen an exponent
                hasNum = false; // Reset `hasNum` because we need digits after the exponent
            }
            // Case 4: Current character is a '+' or '-'
            else if (c == '+' || c == '-') {
                // Sign is valid only at the beginning or immediately after an 'e' or 'E'
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')
                    return false;
            }
            // Case 5: Invalid character
            else {
                return false;
            }
        }

        // The number is valid if we have seen at least one digit
        return hasNum;
    }
}
