/*-
 * Problem Statement:
 *     LeetCode 1047. Remove All Adjacent Duplicates In String
 * 
 *     You are given a string s consisting of lowercase English letters. A duplicate
 *     removal consists of choosing two adjacent and equal letters and removing them.
 *     We repeatedly make duplicate removals on s until we no longer can.
 * 
 * Input:
 *     - String s where 1 <= s.length <= 10^5
 *     - s consists of lowercase English letters
 * 
 * Output:
 *     - Final string after all possible duplicate removals
 * 
 * Example:
 *     Input: s = "abbaca"
 *     Output: "ca"
 *     
 *     Explanation:
 *     - "abbaca" => "aaca" (remove "bb")
 *     - "aaca" => "ca" (remove "aa")
 *     - No more duplicates possible
 */

public class j04RemoveAdjecentDuplicatesI {
    
    public static void main(String args[]) {
        // Test cases with different scenarios
        String[] testCases = {
            "abbaca",           // Basic case
            "azxxzy",          // Multiple duplicates
            "aaa",             // Consecutive same chars
            "a",               // Single character
            ""                 // Empty string
        };

        // Test each case
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output: \"" + removeDuplicates(test) + "\"");
            System.out.println();
        }
    }
    
    /*-
     * Approach: Using StringBuilder as Stack
     * 
     * Intuition:
     * - Use StringBuilder to simulate stack behavior
     * - For each character, check if it matches last character
     * - If match found, remove all consecutive matches
     * - If no match, append the character
     * 
     * Time Complexity: O(n)
     * - Single pass through string
     * - Each character processed at most once
     * 
     * Space Complexity: O(n)
     * - StringBuilder can store up to n characters
     */
    public static String removeDuplicates(String s) {
        StringBuilder out = new StringBuilder();
        
        // Process each character
        for(char c : s.toCharArray()) {
            // Check if current char matches last char in result
            if(!out.isEmpty() && out.charAt(out.length() - 1) == c) {
                // Remove all consecutive matching chars
                while(!out.isEmpty() && out.charAt(out.length() - 1) == c) {
                    out.deleteCharAt(out.length() - 1);
                }
            } else {
                // Add non-duplicate char
                out.append(c);
            }
        }

        return out.toString();
    }
}
