/**
 * Problem Statement:
 * 
 *     Given two strings `name` and `typed`, return `true` if `typed` is a long-pressed version of `name`.
 *     A string `typed` is a long-pressed version of a string `name` if it is possible to obtain `typed` from `name` by:
 *     - Pressing some characters multiple times (possibly zero times).
 *     - The characters in `typed` should appear in the same order as in `name`.
 * 
 *     For example:
 *     - name = "alex", typed = "aaleex" -> Return true (Characters 'a' and 'e' are long-pressed).
 *     - name = "saeed", typed = "ssaaedd" -> Return false (Extra 'd' at the end).
 * 
 * Input:
 *     - Two strings: `name` (1 <= name.length <= 100) and `typed` (1 <= typed.length <= 100).
 * 
 * Output:
 *     - `true` if `typed` is a long-pressed version of `name`, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     "alex"
 *     "aaleex"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The typed string "aaleex" is a valid long-pressed version of "alex" because:
 *     - The 'a' is repeated, 'e' is repeated, and 'x' is intact.
 */

import java.util.Scanner;

public class j04LongPressedName {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String name = in.nextLine(); // Input: name string
        String typed = in.nextLine(); // Input: typed string
        System.out.println(isLongPressedName(name, typed)); // Call to check long-pressed version
        in.close();
    }

    /**
     * Approach: Two Pointer Technique for Matching Characters
     * 
     * Intuition:
     * - We use two pointers to traverse both the `name` and `typed` strings.
     * - The first pointer (`i`) traverses the `name` string, while the second pointer (`j`) traverses the `typed` string.
     * - We check if the current characters from both strings match. If they do, we move both pointers forward.
     * - If the characters do not match but the current character in `typed` is the same as the previous one (indicating a long press), 
     *   we only move the pointer in `typed` to the next character.
     * - If neither condition is met, it means the `typed` string cannot be a long-pressed version of `name`.
     * - The process continues until either all characters from `name` are matched or we find an invalid mismatch.
     * 
     * Time Complexity:
     * - O(n + m), where `n` is the length of `name` and `m` is the length of `typed`. 
     *   We traverse both strings once.
     * 
     * Space Complexity:
     * - O(1), since we only use two pointers and no additional space is required.
     * 
     * @param name The original name string.
     * @param typed The string that may be a long-pressed version of `name`.
     * @return `true` if `typed` is a long-pressed version of `name`, otherwise `false`.
     */
    public static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;

        // Traverse the typed string
        while (j < typed.length()) {
            // If characters match, move both pointers
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            }
            // If the current character in typed is a repetition of the previous one, move
            // the pointer in typed
            else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            }
            // If neither condition is satisfied, return false
            else {
                return false;
            }
        }

        // The typed string is a long-pressed version of name if we've matched all
        // characters from name
        return i == name.length();
    }
}
