/**
 * Problem Statement:
 * 
 *     A pangram is a sentence containing every letter of the English alphabet at least once. Given a string `sentence`, 
 *     return `true` if it is a pangram, or `false` otherwise.
 * 
 * Input:
 *     - A string `sentence` (1 <= sentence.length <= 1000), consisting of lowercase English letters and possibly spaces.
 * 
 * Output:
 *     - A boolean value indicating whether the sentence is a pangram or not.
 * 
 * Example:
 *     Input:
 *     "thequickbrownfoxjumpsoverthelazydog"
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The sentence contains every letter of the English alphabet at least once, so it is a pangram.
 */

import java.util.Scanner;

public class j05CheckPanagram {
    public static void main(String[] args) {
        // Reading input sentence
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();

        // Check if the sentence is a pangram
        System.out.println(checkIfPangram(sentence));

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Using Character Frequency Array
     * 
     * Intuition:
     * - A pangram is a string that contains every letter of the English alphabet at least once. 
     * - To check if the sentence is a pangram, we can use an array to count the frequency of each letter in the sentence.
     * - If the frequency of every letter (from 'a' to 'z') is at least 1, the sentence is a pangram.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the sentence, as we traverse the string once to count the frequency of characters.
     * 
     * Space Complexity:
     * - O(1), as the array size is fixed at 26 (corresponding to the lowercase English alphabet).
     * 
     * @param sentence The input string sentence.
     * @return true if the sentence is a pangram, false otherwise.
     */
    public static boolean checkIfPangram(String sentence) {
        int[] hash = new int[26];

        // Count the frequency of each character in the sentence
        for (int i = 0; i < sentence.length(); i++) {
            // We ignore spaces and count only lowercase letters
            if (sentence.charAt(i) != ' ') {
                hash[sentence.charAt(i) - 'a']++;
            }
        }

        // Check if all letters from 'a' to 'z' have appeared at least once
        for (int i = 0; i < 26; i++) {
            if (hash[i] == 0)
                return false; // If any letter is missing, return false
        }

        return true; // All letters appeared at least once, it's a pangram
    }
}
