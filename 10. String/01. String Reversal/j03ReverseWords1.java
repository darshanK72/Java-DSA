/**
 * Problem Statement:
 * 
 *     Given a string `s`, reverse the order of words in the string. Words are defined as sequences of non-space characters separated by spaces.
 *     The final string should have the same characters but with the order of words reversed.
 *     For example, if `s = "the sky is blue"`, the output should be `"blue is sky the"`.
 * 
 * Input:
 *     - A string `s` containing words separated by spaces (1 <= s.length <= 10^4).
 * 
 * Output:
 *     - A string with the words in reverse order.
 * 
 * Example:
 *     Input:
 *     "the sky is blue"
 *     Output:
 *     "blue is sky the"
 */

import java.util.Scanner;

public class j03ReverseWords1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Read the input string
        System.out.println(reverseWords(s)); // Output using the split method
        System.out.println(reverseWordsTwoPointers(s)); // Output using the two-pointer method
        in.close();
    }

    /** 
     * Approach 1: Reverse Words Using Split & Regular Expression
     * 
     * Intuition:
     * - The basic idea is to split the input string into an array of words, reverse the array, and then join it back into a single string.
     * - We use regular expressions to split the input by spaces, and then append each word in reverse order.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the input string. We go through the string once to split it, and then join it back.
     * 
     * Space Complexity:
     * - O(n), since we create a temporary array to store the split words.
     * 
     * @param str The input string.
     * @return The string with words in reverse order.
     */
    public static String reverseWords(String str) {
        String[] arr = str.trim().split("\\s+"); // Split the string into words, handling multiple spaces.
        StringBuilder out = new StringBuilder("");
        for (int i = arr.length - 1; i >= 0; i--) {
            out.append(arr[i]);
            if (i > 0) {
                out.append(" "); // Add space between words, except for the last word.
            }
        }
        return out.toString(); // Convert StringBuilder back to string and return it.
    }

    /**
     * Approach 2: Reverse Words Using Two Pointers
     * 
     * Intuition:
     * - We can avoid using extra space by iterating through the string from the end.
     * - Using two pointers, we identify each word by skipping spaces and copying characters into the result.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We iterate through the string only once.
     * 
     * Space Complexity:
     * - O(n), as we use a StringBuilder to store the result.
     * 
     * @param str The input string.
     * @return The string with words in reverse order.
     */
    public static String reverseWordsTwoPointers(String str) {
        StringBuilder out = new StringBuilder(); // To build the output string
        int i = str.length() - 1; // Start from the end of the string
        while (i >= 0) {
            // Skip spaces
            while (i >= 0 && str.charAt(i) == ' ') {
                i--;
            }
            int j = i; // Find the end of the current word
            // Find the beginning of the word
            while (j >= 0 && str.charAt(j) != ' ') {
                j--;
            }
            // Append the word to the result
            if (out.length() > 0 && j < i) {
                out.append(" "); // Add a space between words
            }
            for (int k = j + 1; k <= i; k++) {
                out.append(str.charAt(k)); // Append characters of the word
            }
            i = j; // Move `i` to the next word
        }
        return out.toString(); // Return the final string
    }
}
