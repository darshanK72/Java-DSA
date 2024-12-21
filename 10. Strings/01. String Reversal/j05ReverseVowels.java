/**
 * Problem Statement:
 * 
 *     Given a string `s`, reverse only the vowels in the string and return the resulting string.
 *     For example, if `s = "hello"`, the output should be `"holle"`.
 * 
 * Input:
 *     - A string `s` containing only English letters (1 <= s.length <= 100).
 * 
 * Output:
 *     - A string with the vowels reversed, while the consonants remain in their original positions.
 * 
 * Example:
 *     Input:
 *     "hello"
 *     Output:
 *     "holle"
 */

import java.util.Scanner;

public class j05ReverseVowels {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Read the input string
        System.out.println(reverseVowels(s)); // Output the result after reversing vowels
        in.close();
    }

    /**
     * Approach: Two Pointer Method
     * 
     * Intuition:
     * - The idea is to use two pointers: one starting at the beginning of the string and the other at the end.
     * - We move both pointers inward, swapping the vowels found at both pointers, and skip the consonants.
     * - This process ensures that only the vowels are reversed while maintaining the positions of consonants.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the input string. We only traverse the string once.
     * 
     * Space Complexity:
     * - O(n), since we use a character array to store the modified string.
     * 
     * @param string The input string.
     * @return The string with only vowels reversed.
     */
    public static String reverseVowels(String string) {
        char[] str = string.toCharArray(); // Convert the string to a character array
        int s = 0; // Start pointer
        int e = string.length() - 1; // End pointer

        // Loop until the pointers meet
        while (s < e) {
            // If both characters at the pointers are vowels, swap them
            if (isVowel(str[s]) && isVowel(str[e])) {
                replace(str, s, e); // Swap the vowels
                s++; // Move the start pointer forward
                e--; // Move the end pointer backward
            }
            // Move the start pointer forward if it's not a vowel
            if (!isVowel(str[s])) {
                s++;
            }
            // Move the end pointer backward if it's not a vowel
            if (!isVowel(str[e])) {
                e--;
            }
        }
        return new String(str); // Convert the character array back to a string
    }

    /**
     * Helper Function: Check if a character is a vowel.
     * 
     * Intuition:
     * - We define vowels as 'a', 'e', 'i', 'o', 'u' (both uppercase and lowercase).
     * - This function returns true if the character is a vowel, and false otherwise.
     * 
     * Time Complexity:
     * - O(1), as we're checking only a small constant number of characters.
     * 
     * @param c The character to check.
     * @return True if the character is a vowel, false otherwise.
     */
    public static boolean isVowel(char c) {
        // Check if the character is one of the vowels (both lowercase and uppercase).
        return c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u'
                || c == 'U';
    }

    /**
     * Helper Function: Swap two characters in the array.
     * 
     * Intuition:
     * - This function swaps the characters at the given indices in the character array.
     * 
     * Time Complexity:
     * - O(1), as it's a simple operation involving two elements in the array.
     * 
     * @param str The character array.
     * @param s The index of the start character to swap.
     * @param e The index of the end character to swap.
     */
    public static void replace(char[] str, int s, int e) {
        // Swap the characters at the given indices
        char temp = str[s];
        str[s] = str[e];
        str[e] = temp;
    }
}
