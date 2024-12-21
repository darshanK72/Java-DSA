/**
 * Problem Statement:
 * 
 *     Given a string `s`, reverse the string and return the reversed string.
 *     The function should implement two different approaches for reversing the string.
 * 
 * Input:
 *     - A single string `s` (1 <= s.length() <= 1000), which can contain any printable characters.
 * 
 * Output:
 *     - A string that is the reversed version of the input string.
 * 
 * Example:
 *     Input:
 *     "hello"
 *     Output:
 *     "olleh"
 */

import java.util.Scanner;

public class j01ReverseString {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); // Input: the string to be reversed
        System.out.println(reverse1(s)); // Using StringBuilder method
        System.out.println(reverse2(s)); // Using Two Pointers method
        in.close();
    }

    /**
     * Approach 1: Using StringBuilder to reverse the string
     * 
     * Intuition:
     * - StringBuilder is a mutable class that allows us to reverse the string in place using its built-in `reverse()` method.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. The `reverse()` method traverses the string once.
     * 
     * Space Complexity:
     * - O(n), as a new StringBuilder object is created to hold the reversed string.
     * 
     * @param str The input string to be reversed.
     * @return The reversed string.
     */
    public static String reverse1(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString(); // Reverse and convert back to string
    }

    /**
     * Approach 2: Using Two Pointers to reverse the string
     * 
     * Intuition:
     * - We use two pointers: one starting from the beginning and one from the end of the string.
     * - We swap the characters at these pointers, gradually moving towards the center.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string. We only iterate through the string once, swapping characters.
     * 
     * Space Complexity:
     * - O(n), as we convert the string into a character array for swapping characters in place.
     * 
     * @param str The input string to be reversed.
     * @return The reversed string.
     */
    public static String reverse2(String str) {
        int s = 0; // Pointer at the start of the string
        int e = str.length() - 1; // Pointer at the end of the string
        char[] out = str.toCharArray(); // Convert the string to a character array
        while (s < e) {
            // Swap characters
            char t = out[s];
            out[s] = out[e];
            out[e] = t;
            s++; // Move start pointer towards the right
            e--; // Move end pointer towards the left
        }
        return new String(out); // Convert the character array back to a string
    }
}
