/**
 * Problem Statement:
 * 
 *     Given a string `s`, reverse each word in the string. Words are sequences of non-space characters, and they are separated by spaces.
 *     The final string should contain the same words but each word should be reversed.
 *     For example, if `s = "the sky is blue"`, the output should be `"eht yks si eulb"`.
 * 
 * Input:
 *     - A string `s` containing words separated by spaces (1 <= s.length <= 10^4).
 * 
 * Output:
 *     - A string where each word is reversed but the order of words remains the same.
 * 
 * Example:
 *     Input:
 *     "the sky is blue"
 *     Output:
 *     "eht yks si eulb"
 */

import java.util.Scanner;

public class j04ReverseWords2 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Read the input string
        System.out.println(reverseWords(s)); // Output using split & reverse method
        System.out.println(reverseWordsTwoPointers(s)); // Output using two-pointer method
        in.close();
    }

    /**
     * Approach 1: Reverse Words Using Split & Regular Expression
     * 
     * Intuition:
     * - The approach is to first split the string into words, reverse each word, and then join them back together.
     * - We reverse each word individually and handle spaces correctly while concatenating the final result.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the input string. We go through the string once to split it, and reverse each word.
     * 
     * Space Complexity:
     * - O(n), since we use extra space for storing the words and the final result.
     * 
     * @param str The input string.
     * @return The string with each word reversed but the order maintained.
     */
    public static String reverseWords(String str) {
        String[] arr = str.trim().split("\\s+"); // Split the string into words using regular expression.
        StringBuilder out = new StringBuilder(""); // StringBuilder to store the result.
        for (int i = 0; i < arr.length; i++) {
            out.append(reverse(arr[i])); // Reverse each word.
            if (i < arr.length - 1) {
                out.append(" "); // Add a space between words.
            }
        }
        return out.toString(); // Return the final string.
    }

    /**
     * Approach 2: Reverse a Word Using Two Pointers
     * 
     * Intuition:
     * - The function reverses a single word in place using two pointers.
     * - The pointers start from both ends of the word and swap the characters until they meet.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the word, since we iterate through it once.
     * 
     * Space Complexity:
     * - O(n), since we store the word as a character array.
     * 
     * @param str The input word to reverse.
     * @return The reversed word.
     */
    public static String reverse(String str) {
        int s = 0; // Starting pointer
        int e = str.length() - 1; // Ending pointer
        char[] out = str.toCharArray(); // Convert the word to a char array.
        while (s < e) {
            char t = out[s]; // Temporary variable to swap
            out[s] = out[e]; // Swap characters
            out[e] = t;
            s++;
            e--;
        }
        return new String(out); // Return the reversed word.
    }

    /**
     * Approach 3: Reverse Words Using Two Pointers (Alternative method)
     * 
     * Intuition:
     * - We iterate over the string and reverse each word individually using two pointers.
     * - We take care of the spaces in between the words and append them to the result.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the input string. We only iterate through the string once.
     * 
     * Space Complexity:
     * - O(n), since we use a StringBuilder to store the result.
     * 
     * @param str The input string.
     * @return The string with each word reversed but the order maintained.
     */
    public static String reverseWordsTwoPointers(String str) {
        StringBuilder out = new StringBuilder(""); // StringBuilder to store the result.
        int i = 0; // Pointer to track the position in the string.
        while (i < str.length()) {
            int j = i; // Start of the current word
            // Move `i` forward to the end of the word (skip spaces).
            while (i < str.length() && str.charAt(i) != ' ') {
                i++;
            }
            StringBuilder sb = new StringBuilder(""); // To store the current reversed word.
            for (int k = i - 1; k >= j; k--) { // Reverse the current word.
                sb.append(str.charAt(k));
            }
            out.append(sb); // Append the reversed word to the result.
            // Skip spaces in between words.
            while (i < str.length() && str.charAt(i) == ' ') {
                out.append(' '); // Add a space after the word.
                i++;
            }
        }
        return out.toString(); // Return the final string.
    }
}
