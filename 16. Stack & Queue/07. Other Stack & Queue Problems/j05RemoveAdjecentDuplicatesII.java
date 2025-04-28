/*-
 * Problem Statement:
 *     LeetCode 1209. Remove All Adjacent Duplicates in String II
 * 
 *     You are given a string s and an integer k, a k duplicate removal consists of
 *     choosing k adjacent and equal letters from s and removing them, causing the
 *     left and the right side of the deleted substring to concatenate together.
 *     We repeatedly make k duplicate removals on s until we no longer can.
 * 
 * Input:
 *     - String s where 1 <= s.length <= 10^5
 *     - Integer k where 1 <= k <= 10^4
 *     - s consists of lowercase English letters
 * 
 * Output:
 *     - Final string after all possible k duplicate removals
 * 
 * Example:
 *     Input: s = "deeedbbcccbdaa", k = 3
 *     Output: "aa"
 *     
 *     Explanation:
 *     - "deeedbbcccbdaa" => "ddbbbdaa" (remove "eee")
 *     - "ddbbbdaa" => "dddaa" (remove "bbb")
 *     - "dddaa" => "aa" (remove "ddd")
 *     - No more 3 consecutive duplicate letters
 */

import java.util.Stack;

public class j05RemoveAdjecentDuplicatesII {

    /*-
     * Helper Class: Pair to store character and its frequency
     * Maintains count of consecutive occurrences of a character
     */
    static class Pair {
        char c;  // Character
        int f;   // Frequency

        public Pair(char c, int f) {
            this.c = c;
            this.f = f;
        }
    }

    public static void main(String args[]) {
        // Test cases with different scenarios
        String[] testCases = {
            "deeedbbcccbdaa",     // Basic case
            "pbbcggttciiippooaais", // Multiple duplicates
            "abcd",               // No duplicates
            "aabbcc",            // Consecutive same chars
            ""                   // Empty string
        };

        // Test each case with k=3
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output: \"" + removeDuplicates(test, 3) + "\"");
            System.out.println();
        }
    }

    /*-
     * Approach: Using Stack with Frequency Counter
     * 
     * Intuition:
     * - Use stack to track characters and their consecutive counts
     * - When count reaches k, remove the character group
     * - Stack stores Pair objects (char, frequency)
     * - Build final string from remaining stack elements
     * 
     * Time Complexity: O(n)
     * - Single pass through string
     * - Each character processed exactly once
     * - StringBuilder operations are O(1)
     * 
     * Space Complexity: O(n)
     * - Stack stores at most n/k character groups
     * - StringBuilder for output string
     */
    public static String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();

        // Process each character
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().c == c) {
                // Increment frequency for consecutive char
                stack.peek().f++;
                // Remove group if frequency reaches k
                if (stack.peek().f == k) {
                    stack.pop();
                }
            } else {
                // Add new char with frequency 1
                stack.push(new Pair(c, 1));
            }
        }

        // Build result string
        StringBuilder out = new StringBuilder();
        while (!stack.isEmpty()) {
            // Add each char its frequency times
            for (int i = 0; i < stack.peek().f; i++) {
                out.append(stack.peek().c);
            }
            stack.pop();
        }
        return out.reverse().toString();
    }
}
