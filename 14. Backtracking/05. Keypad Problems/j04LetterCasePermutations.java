/**
 * Problem Statement:
 * 
 *      Given a string `s`, you need to generate all possible strings where the 
 *      case of each letter can be either uppercase or lowercase, while keeping 
 *      the digits unchanged.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 12), containing English letters 
 *       (both uppercase and lowercase) and digits.
 * 
 * Output:
 *     - A list of all possible case permutations of the input string.
 * 
 * Example:
 *     Input:
 *         "a1b2"
 *     Output:
 *         ["a1b2", "A1b2", "a1B2", "A1B2"]
 * 
 *     Explanation:
 *         - Each letter in the input string can be either uppercase or lowercase.
 *         - Digits remain unchanged.
 *         - "a1b2" → original string.
 *         - "A1b2" → 'a' converted to uppercase.
 *         - "a1B2" → 'b' converted to uppercase.
 *         - "A1B2" → both 'a' and 'b' converted to uppercase.
 */

import java.util.ArrayList;
import java.util.List;

public class j04LetterCasePermutations {

    /**
     * Approach: Backtracking with Recursion
     * 
     * Intuition:
     * - Since each letter in the string can be toggled between uppercase and lowercase, 
     *   we generate all possible permutations by exploring both cases recursively.
     * - Digits remain unchanged, meaning they do not create extra branching in the recursion.
     * 
     * Explanation:
     * - We iterate over the string and at each letter:
     *   1. Keep the letter as is (no change).
     *   2. Convert it to its opposite case (upper ↔ lower) and continue recursion.
     * - Digits are added directly to the current string without modification.
     * - If we reach the end of the string, we store the generated permutation.
     * 
     * Time Complexity:
     * - O(2^L) where L is the number of letters in `s` (since each letter has two choices).
     * 
     * Space Complexity:
     * - O(2^L) due to the storage of all possible permutations in the list.
     */
    public List<String> letterCasePermutation(String s) {
        List<String> set = new ArrayList<>();
        generateLetterCasePermutations(s, 0, "", set);
        return set;
    }

    private static void generateLetterCasePermutations(String s, int index, String curr, List<String> set) {
        if (index == s.length()) {
            set.add(curr);
            return;
        }

        char c = s.charAt(index);

        // Add the character as is
        generateLetterCasePermutations(s, index + 1, curr + c, set);

        // If it's a letter, add the opposite case as well
        if (Character.isLetter(c)) {
            char toggledCase = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            generateLetterCasePermutations(s, index + 1, curr + toggledCase, set);
        }
    }
}
