/**
 * Problem Statement:
 *     LeetCode 316. Remove Duplicate Letters
 * 
 *     Given a string s, remove duplicate letters so that every letter appears once
 *     and only once. You must make sure your result is the smallest in
 *     lexicographical order among all possible results.
 * 
 * Input:
 *     - String s containing lowercase English letters
 *     - 1 <= s.length <= 10^4
 * 
 * Output:
 *     - String containing each letter once, lexicographically smallest
 * 
 * Example 1:
 *     Input: s = "bcabc"
 *     Output: "abc"
 *     
 *     Explanation:
 *     - Remove duplicate 'b' and 'c' keeping lexicographical order
 * 
 * Example 2:
 *     Input: s = "cbacdcbc"
 *     Output: "acdb"
 *     
 *     Explanation:
 *     - Remove duplicate 'b' and 'c' keeping lexicographical order
 */

public class j16RemoveDuplicateLetters {

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "bcabc",           // Basic case
            "cbacdcbc",        // Complex case
            "abacb",           // Multiple 'a' and 'b'
            "leetcode",        // Multiple 'e'
            "zab",             // Already sorted
            "zzzzz"           // All same characters
        };

        for (String s : testCases) {
            System.out.println("Input: \"" + s + "\"");
            System.out.println("Output: \"" + removeDuplicateLetters(s) + "\"");
            System.out.println();
        }
    }

    /**
     * Approach: Using Monotonic Stack (StringBuilder)
     * 
     * Intuition:
     * - Need to maintain characters in lexicographically smallest order
     * - Can remove a character if it appears later and current sequence isn't optimal
     * - Use StringBuilder as stack to maintain the result
     * - Use frequency map to track remaining occurrences
     * - Use visited array to avoid duplicates
     * 
     * Time Complexity: O(n)
     * - Single pass through string: O(n)
     * - Each character pushed/popped at most once
     * - Array operations are O(1) as size is fixed (26)
     * 
     * Space Complexity: O(1)
     * - Fixed size arrays for visited and frequency: O(1)
     * - StringBuilder size limited by alphabet size: O(1)
     */
    public static String removeDuplicateLetters(String s) {
        // Track visited characters and their frequencies
        boolean[] visited = new boolean[26];
        int[] freq = new int[26];

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Use StringBuilder as stack
        StringBuilder result = new StringBuilder();
        
        // Process each character
        for (char c : s.toCharArray()) {
            // Decrease frequency as we process this character
            freq[c - 'a']--;
            
            // Skip if already in result
            if (visited[c - 'a']) {
                continue;
            }

            // Mark current character as visited
            visited[c - 'a'] = true;

            // Remove larger characters if they appear later
            while (result.length() > 0) {
                char last = result.charAt(result.length() - 1);
                // If last character is larger and appears later
                if (last > c && freq[last - 'a'] > 0) {
                    result.deleteCharAt(result.length() - 1);
                    visited[last - 'a'] = false;
                } else {
                    break;
                }
            }

            // Add current character
            result.append(c);
        }

        return result.toString();
    }
}
