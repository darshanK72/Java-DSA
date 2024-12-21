/**
 * Problem Statement:
 * 
 *     Given a list of strings `words`, find all characters that appear in every string, and return them as a list of strings. Each character should appear in the result as many times as it appears in all strings.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100) representing the number of strings.
 *     - An array `words` of `n` strings where each string has at least one character and at most 100 characters.
 * 
 * Output:
 *     - A list of strings where each string represents a character that appears in all strings in the input array. Each character should appear as many times as it appears in the input strings.
 * 
 * Example:
 *     Input:
 *     ["bella", "label", "roller"]
 *     Output:
 *     ["e", "l", "l"]
 * 
 *     Explanation:
 *     The common characters between all strings are 'e', 'l', and 'l'.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class j21FindCommonChars {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of strings
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = in.next(); // Input: each string
        }
        // Output results for different approaches
        System.out.println(commonCharsHashMap(strs)); // Call first approach
        System.out.println(commonCharsArrayHashEfficient(strs)); // Call second approach
        in.close();
    }

    /**
     * Approach 1: Common Characters Using HashMap
     * 
     * Intuition:
     * - We start by counting the frequency of characters in the first string using a HashMap.
     * - For each subsequent string, we update the frequency map, keeping the minimum count of each character that appears in all strings.
     * - This approach ensures that we find the intersection of character frequencies across all strings.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of strings and `m` is the maximum length of any string. We iterate over each character in every string and perform constant-time operations in the HashMap.
     * 
     * Space Complexity:
     * - O(k), where `k` is the number of distinct characters (in this case, at most 256 ASCII characters), which is constant.
     * 
     * @param words The array of strings.
     * @return A list of common characters in all strings.
     */
    public static List<String> commonCharsHashMap(String[] words) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : words[0].toCharArray()) {
            if (!map.containsKey(c))
                map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }

        for (int i = 1; i < words.length; i++) {
            HashMap<Character, Integer> tempMap = new HashMap<>();
            for (char c : words[i].toCharArray()) {
                if (!tempMap.containsKey(c))
                    tempMap.put(c, 0);
                tempMap.put(c, tempMap.get(c) + 1);
            }

            for (char c : map.keySet()) {
                if (tempMap.containsKey(c)) {
                    map.put(c, Math.min(map.get(c), tempMap.get(c)));
                } else {
                    map.put(c, 0);
                }
            }
        }

        ArrayList<String> out = new ArrayList<String>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            for (int k = 0; k < entry.getValue(); k++)
                out.add("" + entry.getKey());

        }
        return out;
    }

    /**
     * Approach 2: Optimized Common Characters Using Array Hashing
     * 
     * Intuition:
     * - This approach uses an integer array of size 26 to store the frequency of each character in the first string.
     * - For each subsequent string, we update the frequency array, keeping the minimum count of each character that appears in all strings.
     * - This approach uses constant space (26 for lowercase English letters), making it more space-efficient than the HashMap approach.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of strings and `m` is the maximum length of any string. We traverse each character of every string, and array operations are constant time.
     * 
     * Space Complexity:
     * - O(1) because we use an array of fixed size (26) to store character counts.
     * 
     * @param words The array of strings.
     * @return A list of common characters in all strings.
     */
    public static List<String> commonCharsArrayHashEfficient(String[] words) {
        int[] map = new int[26];
        for (char c : words[0].toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] tempMap = new int[26];
            for (char c : words[i].toCharArray()) {
                tempMap[c - 'a']++;
            }

            for (int j = 0; j < 26; j++) {
                if (map[j] != 0 && tempMap[j] < map[j]) {
                    map[j] = tempMap[j];
                }
            }
        }
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                for (int k = 0; k < map[i]; k++)
                    out.add("" + (char) ('a' + i));
            }
        }
        return out;
    }

    /**
     * Approach 3: Optimized Common Characters Using Intersection Method
     * 
     * Intuition:
     * - We use a helper function `count` to count the frequency of characters in a string.
     * - We start with the frequency count of the first string and iteratively compute the intersection of character counts across all strings using the `intersection` function.
     * - This approach leverages the concept of intersecting character frequencies, which guarantees that we find the common characters efficiently.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of strings and `m` is the maximum length of any string. We compute the count for each string and then find the intersection for each subsequent string.
     * 
     * Space Complexity:
     * - O(1) because the character count array has a fixed size (26).
     * 
     * @param words The array of strings.
     * @return A list of common characters in all strings.
     */
    public static List<String> commonChars(String[] words) {
        int[] last = count(words[0]);

        for (int i = 1; i < words.length; i++) {
            last = intersection(last, count(words[i]));
        }

        List<String> arr = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (last[i] != 0) {
                char a = (char) ('a' + i);
                String s = String.valueOf(a);
                while (last[i] > 0) {
                    arr.add(s);
                    last[i]--;
                }
            }
        }
        return arr;
    }

    private static int[] intersection(int[] a, int[] b) {
        int[] t = new int[26];
        for (int i = 0; i < 26; i++) {
            t[i] = Math.min(a[i], b[i]);
        }
        return t;
    }

    private static int[] count(String str) {
        int[] t = new int[26];
        for (char c : str.toCharArray()) {
            t[c - 'a']++;
        }
        return t;
    }
}
