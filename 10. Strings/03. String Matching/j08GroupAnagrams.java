/**
 * Problem Statement:
 * 
 *     Given an array of strings, group the anagrams together. An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once. 
 *     For example, "eat", "tea", and "ate" are anagrams of each other.
 * 
 * Input:
 *     - An array of strings `strs[]` where each string contains lowercase English letters (1 <= strs[i].length <= 100, 1 <= strs.length <= 10^4).
 * 
 * Output:
 *     - A list of lists where each list contains words that are anagrams of each other.
 * 
 * Example:
 *     Input:
 *     ["eat", "tea", "tan", "ate", "nat", "bat"]
 *     Output:
 *     [
 *       ["eat", "tea", "ate"],
 *       ["tan", "nat"],
 *       ["bat"]
 *     ]
 * 
 *     Explanation:
 *     - "eat", "tea", "ate" are anagrams and grouped together.
 *     - "tan", "nat" are anagrams and grouped together.
 *     - "bat" is a standalone word with no anagrams in the list.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class j08GroupAnagrams {
    public static void main(String args[]) {
        String[] strs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagramsHashMap(strs));
        System.out.println(groupAnagramsHashMapEfficient(strs));
    }

    /**
     * Approach 1: Brute Force Method
     * 
     * Intuition:
     * - We iterate through each string and compare it with every other string in the array to check if they are anagrams. 
     * - If two strings are anagrams, we group them together and set one of them as null to mark it as visited.
     * - This approach directly compares strings character by character to identify anagrams.
     * 
     * Time Complexity:
     * - O(n^2 * k) where n is the number of strings and k is the maximum length of a string. 
     *   For each string, we perform a comparison with every other string and each comparison involves checking characters.
     * 
     * Space Complexity:
     * - O(n) for storing the result as an ArrayList.
     * 
     * @param strs The input array of strings.
     * @return List of lists containing anagram groups.
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null)
                continue;
            ArrayList<String> temp = new ArrayList<>();
            temp.add(strs[i]);
            for (int j = i + 1; j < strs.length; j++) {
                if (strs[j] != null && isAnagram(strs[i], strs[j])) {
                    temp.add(strs[j]);
                    strs[j] = null;
                }
            }
            out.add(temp);
        }
        return out;
    }

    /**
     * Helper Method to Check if Two Strings are Anagrams
     * 
     * Intuition:
     * - To determine if two strings are anagrams, we count the frequency of characters in both strings.
     * - If the frequency counts match for all characters, the strings are anagrams.
     * 
     * Time Complexity:
     * - O(k) where k is the length of the strings, as we iterate over each character.
     * 
     * Space Complexity:
     * - O(1) since we use fixed-size arrays for counting characters (of size 26 for lowercase English letters).
     * 
     * @param s1 First string.
     * @param s2 Second string.
     * @return true if the strings are anagrams, false otherwise.
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hash1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            hash2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (hash1[i] != hash2[i])
                return false;
        }
        return true;
    }

    /**
     * Approach 2: Using HashMap of Character Frequencies
     * 
     * Intuition:
     * - For each string, we compute the frequency of each character and use it as a key in a HashMap.
     * - If the key (frequency map) already exists in the HashMap, we append the string to the list associated with that key. 
     * - If not, we create a new entry in the HashMap for that key.
     * 
     * Time Complexity:
     * - O(n * k) where n is the number of strings and k is the average length of the strings.
     *   Each string requires creating a frequency map of characters.
     * 
     * Space Complexity:
     * - O(n * k) for storing the frequency map and the result in the HashMap.
     * 
     * @param strs The input array of strings.
     * @return List of lists containing anagram groups.
     */
    public static List<List<String>> groupAnagramsHashMap(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        HashMap<HashMap<Character, Integer>, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            HashMap<Character, Integer> cmap = new HashMap<>();
            for (char c : s.toCharArray()) {
                cmap.put(c, cmap.getOrDefault(c, 0) + 1);
            }
            if (map.containsKey(cmap)) {
                ArrayList<String> list = map.get(cmap);
                list.add(s);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(cmap, list);
            }
        }

        for (HashMap<Character, Integer> key : map.keySet()) {
            out.add(map.get(key));
        }
        return out;
    }

    /**
     * Approach 3: Using Sorted String as Key in HashMap (Most Optimized)
     * 
     * Intuition:
     * - Instead of using a frequency map, we can sort the characters of each string and use the sorted string as a key in the HashMap.
     * - Anagrams will have the same sorted string, so they will be grouped together in the HashMap under the same key.
     * 
     * Time Complexity:
     * - O(n * k log k) where n is the number of strings and k is the average length of the strings. Sorting each string takes O(k log k).
     * 
     * Space Complexity:
     * - O(n * k) for storing the sorted strings and the result in the HashMap.
     * 
     * @param strs The input array of strings.
     * @return List of lists containing anagram groups.
     */
    public static List<List<String>> groupAnagramsHashMapEfficient(String[] strs) {
        ArrayList<List<String>> out = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cars = s.toCharArray();
            Arrays.sort(cars);
            String sorts = new String(cars);
            if (map.containsKey(sorts)) {
                ArrayList<String> list = map.get(sorts);
                list.add(s);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorts, list);
            }
        }

        for (String key : map.keySet()) {
            out.add(map.get(key));
        }
        return out;
    }
}
