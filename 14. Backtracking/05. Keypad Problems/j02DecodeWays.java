/**
 * Problem Statement:
 * 
 *      Given a string `s` containing only digits (0-9), return the number of ways it 
 *      can be decoded. Each digit or pair of digits maps to a letter in the English 
 *      alphabet (A-Z) using the following mapping:
 * 
 *          '1' -> 'A', '2' -> 'B', ..., '26' -> 'Z'
 * 
 * Leading zeros are not allowed in valid decodings (e.g., "06" is invalid).
 * 
 * Input:
 *     - A string `s` consisting of digits.
 * 
 * Output:
 *     - All possible decoded strings.
 * 
 * Example:
 *     Input:
 *         "12"
 *     Output:
 *         "AB", "L"
 * 
 *     Explanation:
 *         - '1' -> 'A' and '2' -> 'B' gives "AB".
 *         - '12' -> 'L' gives "L".
 *         - Total ways to decode = 2.
 */

import java.util.Scanner;

public class j02DecodeWays {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        System.out.println("Recursive Approach Output:");
        printDecodedWays(s, "");

        System.out.println("Backtracking Approach Output:");
        generateDecodedWays(s, 0, "");

        in.close();
    }

    /**
     * Approach 1: Recursive Brute Force
     * 
     * Intuition:
     * - The problem can be broken down recursively. At each step, we consider:
     *   1. Decoding the first single digit (if valid).
     *   2. Decoding the first two digits as a pair (if within the range 10-26).
     * - This generates a tree of possible decoding paths.
     * - The base case occurs when we have processed the entire string.
     * - The recursion explores all valid decoding paths.
     * 
     * Explanation:
     * - Base Case: If the string is empty, print the accumulated result.
     * - Recursive Step:
     *   1. Process the first digit and map it to a letter.
     *   2. Recur with the remaining substring.
     *   3. If the first two digits form a valid number (10-26), process them together.
     *   4. Recur with the remaining substring.
     * - This method ensures all valid decodings are explored.
     * 
     * Time Complexity:
     * - O(2^n) in the worst case, as each digit can branch into two recursive calls.
     * 
     * Space Complexity:
     * - O(n) due to recursion depth.
     */
    public static void printDecodedWays(String s, String current) {
        if (s.length() == 0) {
            System.out.println(current);
            return;
        } else if (s.length() == 1) {
            char c = s.charAt(0);
            if (c == '0') {
                return;
            } else {
                int chv = c - '0';
                char code = (char) ('a' + chv - 1);
                System.out.println(current + code);
            }
        } else {
            char c = s.charAt(0);
            String res = s.substring(1);
            if (c == '0') {
                return;
            } else {
                int chv = c - '0';
                char code = (char) ('a' + chv - 1);
                printDecodedWays(res, current + code);
            }

            String ch12 = s.substring(0, 2);
            String newres = s.substring(2);
            int chv = Integer.parseInt(ch12);
            if (chv <= 26) {
                char code = (char) ('a' + chv - 1);
                printDecodedWays(newres, current + code);
            }
        }
    }

    /**
     * Approach 2: Backtracking with Count
     * 
     * Intuition:
     * - This approach is similar to recursion but explicitly counts the valid decodings.
     * - Instead of printing results, we count the number of valid decoding sequences.
     * - It processes one character at a time or two characters if they form a valid pair.
     * - The recursion accumulates the count and returns the total number of decodings.
     * 
     * Explanation:
     * - Base Case: If we reach the end of the string, print the decoded string and return 1.
     * - Recursive Step:
     *   1. Convert the first digit to a letter and recurse.
     *   2. If the next two digits form a valid number (10-26), process them together.
     *   3. Return the total count of valid decoding sequences.
     * - This method efficiently counts all valid decodings.
     * 
     * Time Complexity:
     * - O(2^n) in the worst case.
     * 
     * Space Complexity:
     * - O(n) due to recursion depth.
     */
    public static int generateDecodedWays(String s, int index, String letter) {
        if (index > s.length())
            return 0;
        if (index == s.length()) {
            System.out.println(letter);
            return 1;
        }

        int digit = s.charAt(index) - '0';
        if (digit == 0)
            return 0;

        int count = generateDecodedWays(s, index + 1, letter + (char) (digit + 64));

        if (index + 1 < s.length()) {
            int digit2 = (s.charAt(index) - '0') * 10 + s.charAt(index + 1) - '0';
            if (digit2 >= 10 && digit2 <= 26) {
                count += generateDecodedWays(s, index + 2, letter + (char) (digit2 + 64));
            }
        }
        return count;
    }
}
