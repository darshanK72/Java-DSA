/**
 * Problem Statement:
 * 
 *     Two strings are called isomorphic if the characters in one string can be replaced to get the other string, 
 *     with the same character mapping for each occurrence of a character.
 *     For example, "egg" and "add" are isomorphic, as 'e' can be replaced by 'a' and 'g' by 'd' to form the second string.
 * 
 * Input:
 *     - Two strings `s` and `t` (1 <= s.length, t.length <= 10^5) consisting of lowercase letters.
 * 
 * Output:
 *     - A boolean value indicating whether the two strings are isomorphic.
 * 
 * Example:
 *     Input:
 *     "egg"
 *     "add"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - 'e' is mapped to 'a' and 'g' is mapped to 'd', so the strings are isomorphic.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j10IsomorphicString {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        System.out.println(isIsomorphic(s1, s2));
        System.out.println(isIsomorphicHashMap(s1, s2));
        in.close();
    }

    /**
     * Approach 1: Using Character Hashing (Two-way mapping)
     * 
     * Intuition:
     * - We can iterate over both strings and ensure that each character in one string maps uniquely to a character in the other string.
     * - We use an array to map each character to its corresponding character in the other string. 
     * - We also ensure that no character is mapped to more than one character (i.e., no conflicting mappings).
     * 
     * Time Complexity:
     * - O(n), where n is the length of the strings. We iterate through the strings once.
     * 
     * Space Complexity:
     * - O(1) since the space used for the hash array is constant (256 elements for ASCII characters).
     * 
     * @param s First input string.
     * @param t Second input string.
     * @return true if the strings are isomorphic, false otherwise.
     */
    public static boolean isIsomorphic(String s, String t) {
        return mapToHash(s, t) && mapToHash(t, s);
    }

    /**
     * Helper Function: mapToHash
     * 
     * Intuition:
     * - This function helps to check if there is a valid one-to-one mapping from characters in string `s` to string `t`.
     * - We maintain a hash array to track the character mappings.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string.
     * 
     * Space Complexity:
     * - O(1) as the size of the hash array is constant (256 entries).
     * 
     * @param s First input string.
     * @param t Second input string.
     * @return true if the mapping is valid, false otherwise.
     */
    public static boolean mapToHash(String s, String t) {
        char[] hash = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (hash[c1] != '\0' && hash[c1] != c2)
                return false;
            hash[c1] = c2;
        }
        return true;
    }

    /**
     * Approach 2: Using HashMap (Two-way mapping with auxiliary map)
     * 
     * Intuition:
     * - Instead of a character array, we use two HashMaps: one to map characters from `s` to `t` and another to map characters from `t` to `s`.
     * - We ensure that each character in `s` maps to a unique character in `t` and vice versa.
     * - This method also ensures that there are no conflicting mappings.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the strings. We iterate through both strings once.
     * 
     * Space Complexity:
     * - O(n), as we use two hash maps to store mappings.
     * 
     * @param s First input string.
     * @param t Second input string.
     * @return true if the strings are isomorphic, false otherwise.
     */
    public static boolean isIsomorphicHashMap(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> cmap = new HashMap<>();
        HashMap<Character, Boolean> bmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if (cmap.containsKey(ch1)) {
                if (cmap.get(ch1) != ch2)
                    return false;
            } else {
                if (bmap.containsKey(ch2))
                    return false;
                else {
                    cmap.put(ch1, ch2);
                    bmap.put(ch2, true);
                }
            }
        }
        return true;
    }
}
