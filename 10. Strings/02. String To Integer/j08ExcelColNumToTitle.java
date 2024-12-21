/**
 * Problem Statement:
 * 
 *     Given an integer columnNumber, return its corresponding Excel column title as it appears in an Excel sheet.
 *     For example:
 *     A -> 1, B -> 2, C -> 3, ..., Z -> 26, AA -> 27, AB -> 28, ...
 * 
 * Input:
 *     - An integer `columnNumber` (1 <= columnNumber <= 2^31 - 1) representing the column number.
 * 
 * Output:
 *     - The corresponding Excel column title as a string.
 * 
 * Example:
 *     Input:
 *     1
 *     Output:
 *     "A"
 * 
 *     Input:
 *     28
 *     Output:
 *     "AB"
 * 
 *     Input:
 *     701
 *     Output:
 *     "ZY"
 * 
 *     Input:
 *     2147483647
 *     Output:
 *     "FXSHRXW"
 */

import java.util.Scanner;

public class j08ExcelColNumToTitle {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(); // Input: column number
        // Calling the convertToTitle method and printing the result
        System.out.println(convertToTitle(num));
        in.close();
    }

    /**
     * Approach: Base 26 Conversion for Excel Columns
     * 
     * Intuition:
     * - The Excel column system uses a base 26 numbering system where the letters "A" to "Z" correspond to numbers 1 to 26. 
     *   After "Z", the next column is "AA", which corresponds to 27, and so on.
     * - This problem can be solved by performing a base 26 conversion where we treat each set of 26 letters as a "digit" in a positional number system.
     * - We keep subtracting 1 from the column number and taking modulo 26 to get the corresponding letter for each place value.
     * - After calculating the letter for the current "digit", we divide the number by 26 to move to the next place value.
     * 
     * Time Complexity:
     * - O(log26(columnNumber)), which is equivalent to O(log(columnNumber)), because each division reduces the column number.
     * 
     * Space Complexity:
     * - O(log(columnNumber)) due to the string storage as we build the column title.
     * 
     * @param columnNumber The column number to convert to a title.
     * @return The Excel column title corresponding to the given column number.
     */
    public static String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder("");
        while (columnNumber > 0) {
            columnNumber--; // Decrement by 1 to handle 1-indexed column system
            ans.append((char) ('A' + columnNumber % 26)); // Get the character corresponding to the current place value
            columnNumber /= 26; // Move to the next "digit" in base 26
        }
        return ans.reverse().toString(); // Reverse the string as we are constructing it from least significant to most
                                         // significant
    }
}
