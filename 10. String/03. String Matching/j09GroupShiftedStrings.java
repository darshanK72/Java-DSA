/**
 * Problem Statement:
 * 
 *     Given a list of strings, group the strings that are "shifted" versions of each other. Two strings are said to be "shifted" if each character in the string is shifted by the same amount in a cyclic manner in comparison to the other string.
 *     For example, "abc" and "bcd" are shifted versions of each other, as each character in "abc" is shifted by 1 to get "bcd".
 * 
 * Input:
 *     - An array of strings `words[]` where each string is composed of lowercase English letters (1 <= words[i].length <= 100, 1 <= words.length <= 10^4).
 * 
 * Output:
 *     - A list of lists where each list contains words that are shifted versions of each other.
 * 
 * Example:
 *     Input:
 *     ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
 *     Output:
 *     [
 *       ["acef"],
 *       ["a","z"],
 *       ["abc","bcd","xyz"],
 *       ["az","ba"]
 *     ]
 * 
 *     Explanation:
 *     - "abc", "bcd", and "xyz" are shifted versions of each other.
 *     - "az" and "ba" are shifted versions of each other.
 *     - "a" and "z" are individual letters that don't have shifted counterparts.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class j09GroupShiftedStrings {
    public static void main(String args[]) {
        String[] words = new String[] { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
        System.out.println(gropuShiftedStrings(words));
    }

    /**
     * Approach: Group Strings Based on Shift Pattern
     * 
     * Intuition:
     * - For each string, we compute a key that represents the shift pattern by comparing consecutive characters.
     * - The key is a string that consists of the differences between consecutive characters, modulo 26, to account for cyclic shifts.
     * - Strings that have the same shift pattern will be grouped together.
     * 
     * Time Complexity:
     * - O(n * k), where n is the number of strings and k is the average length of a string. For each string, we compute the shift pattern which takes O(k).
     * 
     * Space Complexity:
     * - O(n * k) for storing the groups in the HashMap.
     * 
     * @param words The input array of strings.
     * @return List of lists containing shifted groups of strings.
     */
    public static ArrayList<ArrayList<String>> gropuShiftedStrings(String[] words) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        // Process each word
        for (String s : words) {
            StringBuilder key = new StringBuilder();
            char[] chars = s.toCharArray();

            // Compute the shift pattern by comparing consecutive characters
            for (int i = 1; i < chars.length; i++) {
                key.append((chars[i] - chars[i - 1] + 26) % 26 + "#");
            }

            // Use the shift pattern as the key in the map
            if (map.containsKey(key.toString())) {
                ArrayList<String> list = map.get(key.toString());
                list.add(s);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(key.toString(), list);
            }
        }

        // Collect the results from the map
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        for (String key : map.keySet()) {
            out.add(map.get(key));
        }
        return out;
    }
}
