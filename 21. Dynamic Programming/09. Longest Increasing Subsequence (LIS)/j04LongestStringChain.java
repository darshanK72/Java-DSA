/**
 * LeetCode 1048. Longest String Chain
 * 
 * Problem Statement:
 *     Given a list of words, each word consists of English lowercase letters.
 *     Let's say word1 is a predecessor of word2 if and only if we can add 
 *     exactly one letter anywhere in word1 to make it equal to word2. For 
 *     example, "abc" is a predecessor of "abac".
 *     A word chain is a sequence of words [word_1, word_2, ..., word_k] with 
 *     k >= 1, where word_1 is a predecessor of word_2, word_2 is a 
 *     predecessor of word_3, and so on.
 *     Return the longest possible length of a word chain with words chosen 
 *     from the given list of words.
 * 
 * Input:
 *     - words (1 <= words.length <= 1000, 1 <= words[i].length <= 16, 
 *       words[i] only consists of English lowercase letters)
 * 
 * Output:
 *     - Integer representing the longest possible word chain length
 * 
 * Example:
 *     Input: ["a","b","ba","bca","bda","bdca"]
 *     Output: 4
 * 
 *     Explanation:
 *     One of the longest word chain is "a","ba","bda","bdca". The chain 
 *     length is 4.
 * 
 *     Input: ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 *     Output: 5
 * 
 *     Explanation:
 *     All the words can be put in a word chain ["xb", "xbc", "cxbc", 
 *     "pcxbc", "pcxbcf"].
 */

import java.util.Arrays;

public class j04LongestStringChain {

    /**
     * Approach: Dynamic Programming
     * 
     * Intuition:
     * - This problem is similar to Longest Increasing Subsequence (LIS), but 
     *   instead of checking if numbers are in order, we check if one word is 
     *   a predecessor of another
     * - Sorting by length is crucial: if word1 is a predecessor of word2, 
     *   then word1.length() < word2.length()
     * - After sorting, we only need to check if shorter words are predecessors 
     *   of longer words
     * - For each word, we find the longest chain ending at that word by 
     *   checking all previous words
     * - The key insight: if word[j] is a predecessor of word[i], we can 
     *   extend the chain ending at j with word[i]
     * 
     * Explanation:
     * - Step 1: Sort words by length
     *   - Ensures that if word1 is a predecessor of word2, word1 comes 
     *     before word2
     *   - This allows us to only check previous words when building chains
     * - Step 2: Initialize DP array
     *   - dp[i] stores the length of longest chain ending at word[i]
     *   - Initialize all dp[i] = 1 (each word is a chain of length 1)
     * - Step 3: Build DP array
     *   - For each word at index i, check all previous words j < i
     *   - If word[j] is a predecessor of word[i], extend the chain
     *   - Update dp[i] = max(dp[i], dp[j] + 1) if extending gives longer chain
     * - Step 4: Track maximum chain length
     *   - Update maxLength as we process each word
     *   - Return the maximum chain length found
     * 
     * Time Complexity: O(n^2 * L) where n is the number of words and L is 
     *                  the average word length
     *                  - Sorting: O(n log n)
     *                  - Nested loops: O(n^2)
     *                  - isDiffOne check: O(L) per pair
     * 
     * Space Complexity: O(n) where n is the number of words
     *                   - dp[] array: O(n)
     * 
     * @param words    Array of strings (1 <= words.length <= 1000, 
     *                 1 <= words[i].length <= 16)
     * @return         Longest possible word chain length
     */
    public int longestStrChain(String[] words) {
        // Get the number of words
        int n = words.length;
        
        // Initialize DP array to store longest chain ending at each word
        int[] dp = new int[n];
        // Each word is at least a chain of length 1
        Arrays.fill(dp, 1);
        
        // Track the maximum chain length found
        int maxLength = 0;
        
        // Sort words by length to ensure predecessors come before successors
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        // Build DP array: for each word, find longest chain ending at it
        for (int i = 0; i < n; i++) {
            // Check all previous words to find valid predecessor relationships
            for (int j = 0; j < i; j++) {
                // If word[j] is a predecessor of word[i], we can extend chain
                if (isDiffOne(words[i], words[j])) {
                    // Update chain length by taking maximum of current value 
                    // and extended chain from j
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update maximum chain length found so far
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        // Return the longest chain length
        return maxLength;
    }

    /**
     * Helper Method: Check if one string is predecessor of another
     * 
     * Intuition:
     * - word1 is a predecessor of word2 if word2 can be formed by adding 
     *   exactly one letter to word1
     * - This means word2.length() = word1.length() + 1
     * - We use two pointers to check if word1 is a subsequence of word2 
     *   (allowing one extra character in word2)
     * 
     * Explanation:
     * - Step 1: Check length difference
     *   - If difference is not exactly 1, return false immediately
     * - Step 2: Use two pointers to verify subsequence
     *   - i points to word2 (longer string)
     *   - j points to word1 (shorter string)
     *   - If characters match, advance both pointers
     *   - If characters don't match, only advance i (skip the extra character 
     *     in word2)
     * - Step 3: Verify all characters of word1 were matched
     *   - If j reaches end of word1, all characters were found in order
     *   - Return true if j == word1.length()
     * 
     * Time Complexity: O(L) where L is the length of the longer string
     *                  - Single pass through both strings
     * 
     * Space Complexity: O(1) - Only using constant extra space
     * 
     * @param s1    Longer string (potential successor)
     * @param s2    Shorter string (potential predecessor)
     * @return      True if s2 is a predecessor of s1, false otherwise
     */
    public boolean isDiffOne(String s1, String s2) {
        // Predecessor must be exactly one character shorter
        if (s1.length() - s2.length() != 1) {
            return false;
        }
        
        // Use two pointers to check if s2 is a subsequence of s1
        int i = 0; // Pointer for s1 (longer string)
        int j = 0; // Pointer for s2 (shorter string)
        
        // Traverse both strings
        while (i < s1.length() && j < s2.length()) {
            // If characters don't match, skip character in s1 (the extra one)
            if (s1.charAt(i) != s2.charAt(j)) {
                i++;
            } else {
                // Characters match, advance both pointers
                i++;
                j++;
            }
        }
        
        // s2 is a predecessor if all its characters were found in s1 in order
        return j == s2.length();
    }

    public static void main(String[] args) {
        j04LongestStringChain solution = new j04LongestStringChain();
        
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: [\"a\",\"b\",\"ba\",\"bca\",\"bda\",\"bdca\"], Expected: 4, Output: " + 
            solution.longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
        System.out.println("Input: [\"xbc\",\"pcxbcf\",\"xb\",\"cxbc\",\"pcxbc\"], Expected: 5, Output: " + 
            solution.longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"}));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: [\"a\"], Expected: 1, Output: " + 
            solution.longestStrChain(new String[]{"a"}));
        System.out.println("Input: [\"a\",\"b\"], Expected: 1, Output: " + 
            solution.longestStrChain(new String[]{"a","b"}));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: [\"abcd\",\"dbqca\"], Expected: 1, Output: " + 
            solution.longestStrChain(new String[]{"abcd","dbqca"}));
        System.out.println("Input: [\"a\",\"ab\",\"abc\",\"abcd\"], Expected: 4, Output: " + 
            solution.longestStrChain(new String[]{"a","ab","abc","abcd"}));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: [\"a\",\"b\",\"ab\",\"abc\"], Expected: 3, Output: " + 
            solution.longestStrChain(new String[]{"a","b","ab","abc"}));
        System.out.println("Input: [\"a\",\"ba\",\"bca\",\"bdca\",\"bda\"], Expected: 4, Output: " + 
            solution.longestStrChain(new String[]{"a","ba","bca","bdca","bda"}));
    }
}
