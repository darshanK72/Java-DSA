/*-
 * Problem Statement:
 *     Remove Consecutive Characters
 * 
 *     Given a string S. For each character in the string, remove all its consecutive
 *     occurrences if found. The final string should have each character at most once.
 * 
 * Input:
 *     - String S where 1 <= S.length <= 10^5
 *     - S consists of lowercase English letters
 * 
 * Output:
 *     - String with consecutive duplicates removed
 * 
 * Example:
 *     Input: S = "abbaca"
 *     Output: "ca"
 *     
 *     Explanation:
 *     - "abbaca" => "aaca" (remove "bb")
 *     - "aaca" => "ca" (remove "aa")
 *     - No more consecutive characters to remove
 */

public class j03RemoveConsecutiveCharacters {

    public static void main(String args[]) {
        // Test cases with different scenarios
        String[] testCases = {
                "abbaca",   // Basic case
                "azxxzy",   // Multiple duplicates
                "aaa",      // Consecutive same chars
                "a",        // Single character
                ""         // Empty string
        };

        // Test each case
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output: \"" + removeConsecutiveCharacter(test) + "\"");
            System.out.println();
        }
    }

    /*-
     * Approach: Using StringBuilder as Stack
     * 
     * Intuition:
     * - Use StringBuilder to simulate stack behavior
     * - For each character, check if it matches last character in result
     * - If match found, remove all consecutive occurrences
     * - If no match, append the character
     * 
     * Time Complexity: O(n)
     * - Single pass through string
     * - Each character is processed exactly once
     * 
     * Space Complexity: O(n)
     * - StringBuilder can store up to n characters in worst case
     */
    public static String removeConsecutiveCharacter(String s) {
        StringBuilder out = new StringBuilder();
        
        // Process each character
        for (char c : s.toCharArray()) {
            // Remove consecutive occurrences if found
            while (out.length() != 0 && out.charAt(out.length() - 1) == c) {
                out.deleteCharAt(out.length() - 1);
            }
            // Add current character
            out.append(c);
        }

        return out.toString();
    }
}
