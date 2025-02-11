/**
 * Problem Statement:
 * 
 *     Given a string `s`, the task is to generate all possible unique palindromic permutations 
 *     of the string, if possible.
 * 
 * Input:
 *     - A string `s` consisting of lowercase English letters (1 <= s.length <= 10).
 * 
 * Output:
 *     - A list of all unique palindromic permutations of the string `s`.
 *     - If no palindromic permutation is possible, return an empty list.
 * 
 * Example 1:
 *     Input:
 *     "aabb"
 *     
 *     Output:
 *     ["abba", "baab"]
 * 
 *     Explanation:
 *     The given string has two possible palindromic permutations.
 * 
 * Example 2:
 *     Input:
 *     "abbab"
 *     
 *     Output:
 *     []
 * 
 *     Explanation:
 *     It is impossible to form a palindromic permutation from "abbab".
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class j07PalindromicPermutations {

    public static void main(String args[]) {
        // Example test case
        String s = "abbab";
        List<String> ans = generatePalindromePermutations(s);
        System.out.println(ans);
    }

    /**
     * Approach: Backtracking with Frequency Map
     * 
     * Intuition:
     * - A string can have a palindromic permutation if **at most one** character 
     *   appears an odd number of times.
     * - We count the frequency of each character.
     * - If more than one character has an odd count, return an empty list (no solution).
     * - Otherwise, we form half of the palindrome and use backtracking to generate 
     *   all unique permutations of that half.
     * - The full palindrome is created by mirroring the first half.
     * 
     * Explanation:
     * 1. Build a frequency map of characters.
     * 2. Identify the single odd-count character (if any).
     * 3. Convert each character count to half (as only half is needed to generate unique permutations).
     * 4. Use backtracking to generate all permutations of this half.
     * 5. Construct the full palindrome by appending its reverse to the half.
     * 
     * Time Complexity:
     * - O(n! / (n/2)!), since we generate half-length permutations.
     * 
     * Space Complexity:
     * - O(n), due to recursion and storage.
     */
    public static List<String> generatePalindromePermutations(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Count character frequencies
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        ArrayList<String> ans = new ArrayList<>();
        char odd = '0'; // Character with an odd frequency (if any)
        int len = 0; // Half-length of the palindrome

        // Step 2: Process character frequencies
        for (char key : map.keySet()) {
            int freq = map.get(key);

            // If odd frequency is found, allow only one odd-count character
            if (freq % 2 == 1 && odd == '0') {
                odd = key;
                map.put(key, freq / 2); // Reduce count for half palindrome
                len += freq / 2;
            } else if (freq % 2 == 0) {
                map.put(key, freq / 2);
                len += freq / 2;
            } else {
                // More than one odd character makes palindrome impossible
                return ans;
            }
        }

        // Step 3: Generate all permutations of half-length string
        generateStringPermutations(map, "", ans, odd, len);
        Collections.sort(ans);
        return ans;
    }

    /**
     * Helper Method: Generate Half-Length Palindromic Permutations
     * 
     * - Uses backtracking to generate all unique permutations of half of the palindrome.
     * - Appends its reverse to construct the full palindrome.
     */
    public static void generateStringPermutations(HashMap<Character, Integer> map, String curr, ArrayList<String> ans,
            char odd, int len) {
        // Base Case: When we reach half-length, form the full palindrome
        if (curr.length() == len) {
            String rev = new StringBuilder(curr).reverse().toString();
            if (odd != '0') {
                curr += odd; // Insert the odd character in the middle
            }
            ans.add(curr + rev); // Mirror to form palindrome
            return;
        }

        // Recursive Case: Generate permutations using available characters
        for (char key : map.keySet()) {
            if (map.get(key) > 0) {
                map.put(key, map.get(key) - 1);
                generateStringPermutations(map, curr + key, ans, odd, len);
                map.put(key, map.get(key) + 1); // Backtrack
            }
        }
    }
}
