/**
 * Problem Statement:
 * 
 *     Given an array of strings `strs`, return the longest common prefix string amongst them.
 *     If there is no common prefix, return an empty string "".
 * 
 * Input:
 *     - An array of strings `strs` (1 <= strs.length <= 200) where each string has length in the range [0, 200].
 * 
 * Output:
 *     - The longest common prefix string.
 * 
 * Example:
 *     Input:
 *     ["flower", "flow", "flight"]
 *     Output:
 *     "fl"
 * 
 *     Explanation:
 *     - The longest common prefix is "fl".
 */

public class j11LongestCommonPrefix {
    public static void main(String args[]) {
        // Input array of strings
        String[] strs = new String[] { "flower", "flow", "flight" };

        // Output the result
        System.out.println(longestCommonPrefix(strs));
    }

     /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - The idea is to compare each string in the array with the current longest common prefix:
     * - Start with the first string as the initial prefix.
     * - Iterate over each string in the array, progressively shortening the current prefix until it matches 
     *   the start of the string.
     * - If at any point the prefix becomes an empty string, it means there is no  common prefix.
     * - Continue this process until all strings are processed.
     * 
     * Time Complexity: 
     *  - O(S), where S is the sum of all characters in all strings.
     * Space Complexity: O(1), 
     *  - since we only store the prefix and a few variables for comparisons.
     */
    public static String longestCommonPrefixBruitForce(String[] strs) {
        // Start with the first string as the prefix.
        String prefix = strs[0];

        // Iterate through each string in the array
        for (String str : strs) {
            StringBuilder tempPrefix = new StringBuilder();
            int i = 0;
            int j = 0;

            // Compare characters of the current prefix and the current string
            while (i < prefix.length() && j < str.length()) {
                if (prefix.charAt(i) == str.charAt(j)) {
                    tempPrefix.append(prefix.charAt(i));
                } else {
                    break;
                }
                i++;
                j++;
            }

            // Update the prefix with the common part found
            prefix = tempPrefix.toString();
        }

        return prefix;
    }


    /**
     * Approach: Horizontal Scanning (Iterative Comparison)
     * 
     * Intuition:
     * - The idea is to iteratively compare each string in the array with the current longest common prefix:
     *     - Start with the first string as the initial prefix.
     *     - Iterate over each string in the array, progressively shortening the current prefix until it matches the start of the string.
     *     - If at any point the prefix becomes an empty string, it means there is no common prefix.
     * - We continue this process until all strings are processed.
     * 
     * Time Complexity:
     * - O(S), where S is the sum of all characters in all strings. In the worst case, each string is compared fully with the current prefix.
     * 
     * Space Complexity:
     * - O(1), since we only store the prefix and a few variables for comparisons.
     * 
     * @param strs The array of strings.
     * @return The longest common prefix.
     */
    public static String longestCommonPrefix(String[] strs) {
        String out = strs[0]; // Start with the first string as the prefix.

        // Iterate through each string in the array
        for (int i = 1; i < strs.length; i++) {
            // Continuously reduce the prefix by checking it against the current string
            while (strs[i].indexOf(out) != 0) {
                // Shorten the prefix by removing the last character
                out = out.substring(0, out.length() - 1);

                // If the prefix becomes empty, return immediately
                if (out.isEmpty()) {
                    return "";
                }
            }
        }

        // Return the longest common prefix after checking all strings
        return out;
    }
}
