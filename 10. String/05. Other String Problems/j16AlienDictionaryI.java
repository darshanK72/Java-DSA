/**
 * LeetCode 953. Verifying an Alien Dictionary
 * 
 * Problem Statement:
 *     In an alien language, the letters of the English alphabet are rearranged in a
 *     different order. Given a sequence of words written in this alien language and
 *     the order of the alphabet, return true if and only if the given words are
 *     sorted lexicographically in this alien language.
 * 
 * Input:
 *     - words (1 <= words.length <= 100)
 *         Array of strings, each string consists of lowercase English letters
 *         (1 <= words[i].length <= 20)
 *     - order (order.length == 26)
 *         A string of 26 unique lowercase English letters representing the order
 *         of the alien alphabet
 * 
 * Output:
 *     - Boolean value: true if words are sorted as per alien order, false otherwise
 * 
 * Example:
 *     Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 *     Output: true
 *     Explanation:
 *         'h' comes before 'l' in the alien order, so 'hello' < 'leetcode'.
 * 
 *     Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 *     Output: false
 *     Explanation:
 *         'd' comes after 'l' in the alien order, so 'word' > 'world'.
 * 
 *     Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 *     Output: false
 *     Explanation:
 *         'apple' is longer but starts with 'app', so not sorted.
 */

public class j16AlienDictionaryI {

    /**
     * Approach: Mapping and Pairwise Comparison
     * 
     * Intuition:
     * - Map each character in the alien order to its index for O(1) lookup.
     * - Compare each adjacent pair of words using the alien order.
     * - If any pair is out of order, return false.
     * 
     * Explanation:
     * - Step 1: Build a mapping from character to its position in the alien order.
     * - Step 2: For each adjacent pair, compare characters one by one using the map.
     * - Step 3: If a word is a prefix of the next but longer, it's not sorted.
     * - Step 4: If all pairs are sorted, return true.
     * 
     * Time Complexity: O(N * L), where N = number of words, L = max word length
     * Space Complexity: O(1), since the map size is constant (26 letters)
     * 
     * @param words    Array of words to check (1 <= words.length <= 100)
     * @param order    String representing alien alphabet order (length 26)
     * @return         True if words are sorted as per alien order, false otherwise
     */
    public static boolean isAlienSorted(String[] words, String order) {
        // Map each character to its index in the alien order
        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {
            map[order.charAt(i) - 'a'] = i; // Map char to its order index
        }

        // Compare each adjacent pair of words
        for (int i = 0; i < words.length - 1; i++) {
            if (compareTo(words[i], words[i + 1], map) == 1)
                return false; // Found a pair out of order
        }
        return true; // All pairs are in correct order
    }

    /**
     * Helper Method: compareTo
     * 
     * Intuition:
     * - Compares two words character by character using the alien order map.
     * - Handles prefix cases where one word is a prefix of the other.
     * 
     * Explanation:
     * - Step 1: Iterate through both words until a mismatch or end of one word.
     * - Step 2: If mismatch, compare using the alien order map.
     * - Step 3: If one word ends, shorter word is considered smaller.
     * 
     * Time Complexity: O(L), L = max length of the two words
     * Space Complexity: O(1)
     * 
     * @param a    First word
     * @param b    Second word
     * @param map  Mapping from char to alien order index
     * @return     -1 if a < b, 1 if a > b, 0 if equal
     */
    private static int compareTo(String a, String b, int[] map) {
        int i = 0;
        int j = 0;
        // Compare characters until mismatch or end of one word
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) != b.charAt(j))
                break;
            i++;
            j++;
        }
        // If all previous chars are equal, shorter word is smaller
        if (i == a.length() && j == b.length()) return 0;
        if (i == a.length()) return -1;
        if (j == b.length()) return 1;
        // Compare using alien order
        return (map[a.charAt(i) - 'a'] > map[b.charAt(j) - 'a']) ? 1 : -1;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: ['hello','leetcode'], order: 'hlabcdefgijkmnopqrstuvwxyz', Expected: true, Output: " +
            isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println("Input: ['word','world','row'], order: 'worldabcefghijkmnpqstuvxyz', Expected: false, Output: " +
            isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: ['apple','app'], order: 'abcdefghijklmnopqrstuvwxyz', Expected: false, Output: " +
            isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println("Input: ['a'], order: 'abcdefghijklmnopqrstuvwxyz', Expected: true, Output: " +
            isAlienSorted(new String[]{"a"}, "abcdefghijklmnopqrstuvwxyz"));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: ['a','b','c'], order: 'abcdefghijklmnopqrstuvwxyz', Expected: true, Output: " +
            isAlienSorted(new String[]{"a","b","c"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println("Input: ['z','y','x'], order: 'zyxwvutsrqponmlkjihgfedcba', Expected: true, Output: " +
            isAlienSorted(new String[]{"z","y","x"}, "zyxwvutsrqponmlkjihgfedcba"));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: ['app','apple'], order: 'abcdefghijklmnopqrstuvwxyz', Expected: true, Output: " +
            isAlienSorted(new String[]{"app","apple"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println("Input: ['abc','ab'], order: 'abcdefghijklmnopqrstuvwxyz', Expected: false, Output: " +
            isAlienSorted(new String[]{"abc","ab"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}