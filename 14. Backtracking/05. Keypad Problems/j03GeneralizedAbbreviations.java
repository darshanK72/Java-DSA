/**
 * Problem Statement:
 * 
 *      Given a word, generate all possible generalized abbreviations of the word. 
 *      A generalized abbreviation is formed by replacing some or all characters 
 *      of the word with their corresponding count while maintaining the relative order.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 20), containing lowercase English letters.
 * 
 * Output:
 *     - A list of all possible abbreviations sorted lexicographically.
 * 
 * Example:
 *     Input:
 *         "word"
 *     Output:
 *         ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "w1r1", "1o1d", 
 *          "1or1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 *     Explanation:
 *         - "word" remains unchanged.
 *         - "1ord" replaces 'w' with '1'.
 *         - "w1rd" replaces 'o' with '1'.
 *         - "wo1d" replaces 'r' with '1'.
 *         - "wor1" replaces 'd' with '1'.
 *         - "2rd" replaces "wo" with '2'.
 *         - "w2d" replaces "or" with '2'.
 *         - "wo2" replaces "rd" with '2'.
 *         - and so on...
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class j03GeneralizedAbbreviations {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        List<String> set = generalizedAbbreviations(s);
        Collections.sort(set);
        System.out.println(set);
        in.close();
    }

    /**
     * Approach: Backtracking with Recursion
     * 
     * Intuition:
     * - The problem requires generating all possible abbreviations by either:
     *   1. Keeping the current character unchanged.
     *   2. Replacing the current character (or sequence) with a number.
     * - We explore all possibilities using backtracking, ensuring we form all possible abbreviations.
     * 
     * Explanation:
     * - At each index, we have two choices:
     *   1. Append the current character to the abbreviation.
     *   2. Increment a count (to represent an abbreviation) and move to the next character.
     * - If we reach the end of the string, we append the accumulated count (if any) and store the result.
     * - The recursive function explores both options at each step.
     * 
     * Time Complexity:
     * - O(2^n), as each character in the string has two choices (abbreviate or keep).
     * 
     * Space Complexity:
     * - O(2^n), for storing all possible abbreviations.
     */
    public static List<String> generalizedAbbreviations(String s) {
        List<String> set = new ArrayList<>();
        generateGeneralizedAbbreviations(s, 0, 0, "", set);
        return set;
    }

    private static void generateGeneralizedAbbreviations(String s, int index, int count, String curr,
            List<String> set) {
        if (index == s.length()) {
            // If count is nonzero, append it before storing
            curr += (count > 0) ? ("" + count) : "";
            set.add(curr);
            return;
        }

        // Option 1: Keep the current character
        String mid = (count > 0) ? ("" + count) : "";
        generateGeneralizedAbbreviations(s, index + 1, 0, curr + mid + s.charAt(index), set);

        // Option 2: Abbreviate the current character
        generateGeneralizedAbbreviations(s, index + 1, count + 1, curr, set);
    }
}
