/**
 * Problem Statement:
 * 
 *     The task is to convert a string into a zigzag pattern on a given number of rows. 
 *     After converting the string, you need to read it row by row and return the result.
 * 
 *     For example:
 *     - The string "PAYPALISHIRING" is written in a zigzag pattern on 3 rows:
 *     
 *     P   A   H   N
 *     A P L S I I G
 *     Y   I   R
 * 
 *     And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 1000)
 *     - An integer `numRows` (1 <= numRows <= 1000)
 * 
 * Output:
 *     - A string that is the result of reading the zigzag pattern row by row.
 * 
 * Example:
 *     Input:
 *     "PAYPALISHIRING", numRows = 3
 *     Output:
 *     "PAHNAPLSIIGYIR"
 * 
 * Approach:
 *     1. We initialize an array of `StringBuilder` objects, one for each row, to build the result for each row.
 *     2. We iterate through the string, placing characters into the appropriate rows following a zigzag pattern:
 *         - Move down the rows until the last row, then move up the rows until the second row.
 *     3. After filling the rows, we concatenate all rows together to get the final result.
 * 
 * Time Complexity:
 *     - O(n), where `n` is the length of the string. We traverse each character exactly once.
 * 
 * Space Complexity:
 *     - O(n), where `n` is the length of the string. We store the result in an array of `StringBuilder` objects.
 */

import java.util.Scanner;

public class j13ZigZagConversion {
    public static void main(String args[]) {
        // Reading input for the string and number of rows
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int numRows = in.nextInt();

        // Outputting the converted zigzag string
        System.out.println(convert(s, numRows));
        in.close();
    }

    /**
     * Approach: 
     * - We need to place each character of the string `s` into the appropriate row in a zigzag pattern.
     * - To simulate this, we use an array of StringBuilder objects where each element represents a row.
     * - We traverse the string and place characters in rows, then after completing the zigzag pattern, concatenate the rows.
     * 
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(n), where n is the length of the string (due to the array of StringBuilder objects).
     * 
     * @param s The input string to be converted.
     * @param numRows The number of rows in the zigzag pattern.
     * @return The converted zigzag string.
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() < numRows)
            return s; // If numRows is 1, no zigzag pattern is needed.

        // Create an array to store the rows
        StringBuilder[] zag = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            zag[i] = new StringBuilder(); // Initialize each row as a StringBuilder
        }

        int i = 0; // Pointer to traverse the string
        while (i < s.length()) {
            // Going down the rows
            for (int row = 0; row < numRows && i < s.length(); row++) {
                zag[row].append(s.charAt(i++)); // Add character to the current row
            }
            // Going up the rows (except for the first and last rows)
            for (int row = numRows - 2; row > 0 && i < s.length(); row--) {
                zag[row].append(s.charAt(i++)); // Add character to the current row
            }
        }

        // Combine all rows into one string
        StringBuilder out = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            out.append(zag[j]);
        }
        return out.toString(); // Return the final zigzag string
    }
}
