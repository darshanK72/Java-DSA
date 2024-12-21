/**
 * Problem Statement:
 * 
 *     Given a string `s`, sort its characters in decreasing order based on their frequency. The function should return the sorted string.
 * 
 * Input:
 *     - A string `s` of length `n` (1 <= n <= 10^5) where each character is an ASCII character.
 * 
 * Output:
 *     - A string where characters are sorted by their frequency in descending order.
 * 
 * Example:
 *     Input:
 *     "tree"
 *     Output:
 *     "eetr"
 * 
 *     Explanation:
 *     The character 'e' appears twice, 't' and 'r' each appear once. So, the output is sorted as "eetr".
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class j19SortCharsByFrequency {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the string s
        System.out.println(frequencySortArrayHash(s)); // Call first approach
        System.out.println(frequencySortHashing(s)); // Call second approach
        in.close();
    }

    /**
     * Approach 1: Frequency Sort Using Array Hashing
     * 
     * Intuition:
     * - We will first count the frequency of each character in the string using a simple array of size 128 (for all ASCII characters).
     * - Then, we find the character with the maximum frequency and append it to the result string.
     * - We continue this process until all characters are sorted by frequency.
     * - This approach relies on the fact that there are only 128 possible characters (ASCII), so we use an array to count occurrences.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string. We traverse the string once to build the frequency table, and then we iterate over the array of size 128 (constant time).
     * 
     * Space Complexity:
     * - O(1) since the array has a fixed size of 128 (constant space).
     * 
     * @param s The input string.
     * @return The string sorted by character frequency.
     */
    public static String frequencySortArrayHash(String s) {
        int[] hash = new int[128]; // Frequency array for ASCII characters
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i)]++; // Count frequency of each character
        }

        StringBuilder out = new StringBuilder();
        while (out.length() < s.length()) {
            int max = 0;
            char c = '\0';
            for (int i = 0; i < 128; i++) {
                if (hash[i] > max) {
                    max = hash[i];
                    c = (char) i;
                }
            }
            for (int i = 0; i < max; i++) {
                out.append(c);
            }
            hash[c] = 0; // Reset the frequency of this character after processing
        }
        return out.toString();
    }

    /**
     * Approach 2: Frequency Sort Using Hash Map
     * 
     * Intuition:
     * - First, we use a HashMap to count the frequency of each character in the string.
     * - Then, we create another HashMap to group characters by their frequencies.
     * - Finally, we iterate through the frequencies in descending order and append each character to the result string.
     * - This approach efficiently handles sorting characters by their frequency and works well with large input sizes.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the string. We traverse the string to build the frequency map, and then we iterate over the frequency map.
     * 
     * Space Complexity:
     * - O(n) due to the space used by the frequency map and the character grouping map.
     * 
     * @param s The input string.
     * @return The string sorted by character frequency.
     */
    public static String frequencySortHashing(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1); // Count frequency of each character
        }

        // Create a map that groups characters by their frequency
        HashMap<Integer, ArrayList<Character>> countMap = new HashMap<>();
        for (char c : freqMap.keySet()) {
            int count = freqMap.get(c);
            ArrayList<Character> list = countMap.getOrDefault(count, new ArrayList<>());
            list.add(c);
            countMap.put(count, list);
        }

        // Build the result string by appending characters in descending order of their
        // frequency
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i >= 1; i--) {
            if (countMap.containsKey(i)) {
                for (char c : countMap.get(i)) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c); // Append each character by its frequency
                    }
                }
            }
        }

        return sb.toString();
    }
}
