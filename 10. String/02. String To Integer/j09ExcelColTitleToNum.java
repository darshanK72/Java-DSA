/**
 * Problem Statement:
 * 
 *     Given a column title as appear in an Excel sheet, return its corresponding column number.
 *     For example:
 *     A -> 1, B -> 2, C -> 3, ..., Z -> 26, AA -> 27, AB -> 28, ...
 * 
 * Input:
 *     - A string `columnTitle` (1 <= columnTitle.length() <= 7) representing the Excel column title.
 * 
 * Output:
 *     - The corresponding column number as an integer.
 * 
 * Example:
 *     Input:
 *     "A"
 *     Output:
 *     1
 * 
 *     Input:
 *     "AB"
 *     Output:
 *     28
 * 
 *     Input:
 *     "ZY"
 *     Output:
 *     701
 */

import java.util.Scanner;

public class j09ExcelColTitleToNum {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String title = in.nextLine(); // Input: Excel column title
        // Calling the titleToNumber method and printing the result
        System.out.println(titleToNumber(title));
        in.close();
    }

    /**
     * Approach: Base 26 Conversion for Excel Columns (Reverse Process)
     * 
     * Intuition:
     * - The column titles in Excel are represented using a base-26 system, where 'A' represents 1, 'B' represents 2, ..., 'Z' represents 26.
     * - For a string of column title, we calculate the column number by treating it as a number in base 26. The first character contributes to the most significant place, and so on.
     * - To obtain the column number, we iterate through the string from the end, and for each character:
     *   - Subtract 'A' from the character and add 1 (to get the numerical value).
     *   - Multiply the current value by 26 for each place value.
     *   - Keep a running sum of these values to get the final column number.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the column title string.
     * 
     * Space Complexity:
     * - O(1), since we only use a fixed amount of extra space.
     * 
     * @param columnTitle The column title to convert to a number.
     * @return The corresponding column number.
     */
    public static int titleToNumber(String columnTitle) {
        int i = columnTitle.length() - 1; // Start from the rightmost character
        int b = 1; // The multiplier for base 26 (1 for the rightmost character)
        int ans = 0; // Variable to hold the final column number
        // Process each character from right to left
        while (i >= 0) {
            ans = ans + (columnTitle.charAt(i) - 'A' + 1) * b; // Calculate the value for the current character
            b *= 26; // Update the multiplier for the next place value
            i--; // Move to the next character
        }
        return ans; // Return the final column number
    }
}
