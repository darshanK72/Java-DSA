/**
 * LeetCode 942. DI String Match
 * 
 * Problem Statement:
 *     A permutation perm of n + 1 integers of all the integers in the range [0, n] is called a
 *     valid permutation if for all valid i, if s[i] == 'I' then perm[i] < perm[i + 1], and if
 *     s[i] == 'D' then perm[i] > perm[i + 1]. Given a string s of length n, return any valid
 *     permutation perm of [0, 1, ..., n].
 * 
 * Input:
 *     - str (String): String containing only 'I' and 'D' characters
 * 
 * Output:
 *     - int[]: A valid permutation of [0, 1, ..., n] where n is length of string
 * 
 * Example:
 *     Input: s = "IDID"
 *     Output: [0, 4, 1, 3, 2]
 * 
 *     Explanation:
 *     - For 'I' at index 0: 0 < 4
 *     - For 'D' at index 1: 4 > 1
 *     - For 'I' at index 2: 1 < 3
 *     - For 'D' at index 3: 3 > 2
 */

public class j11DIStringMatch {

    /**
     * Approach: Two-Pointer Greedy
     * 
     * Intuition:
     * - For 'I', we need a smaller number followed by a larger number
     * - For 'D', we need a larger number followed by a smaller number
     * - Use two pointers: one for smallest available number (s) and one for largest (e)
     * - For 'I', use smallest number; for 'D', use largest number
     * 
     * Explanation:
     * - Step 1: Initialize pointers s=0 and e=n
     * - Step 2: For each character in string:
     *   - If 'I': use smallest available number (s) and increment s
     *   - If 'D': use largest available number (e) and decrement e
     * - Step 3: Place last number based on last character
     * 
     * Time Complexity: O(n) where n is length of string
     *                  - Single pass through the string
     * Space Complexity: O(n) for storing the result array
     * 
     * @param str    String containing only 'I' and 'D' characters
     * @return       A valid permutation of [0, 1, ..., n]
     */
    public static int[] diStringMatch(String str) {
        // Initialize pointers for smallest and largest available numbers
        int s = 0;
        int e = str.length();
        int i = 0;
        int[] out = new int[str.length() + 1];

        // Process each character in the string
        while (i < str.length()) {
            if (str.charAt(i) == 'I') {
                // For 'I', use smallest available number
                out[i] = s;
                s++;
            } else {
                // For 'D', use largest available number
                out[i] = e;
                e--;
            }
            i++;
        }

        // Place last number based on last character
        if (str.charAt(i - 1) == 'I')
            out[i] = s;
        else
            out[i] = e;

        return out;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        String str1 = "IDID";
        System.out.println("Input: " + str1);
        System.out.println("Expected: [0, 4, 1, 3, 2], Output: " + 
            java.util.Arrays.toString(diStringMatch(str1)));

        // Test Case 2: All increasing
        System.out.println("\nSpecial Case - All Increasing:");
        String str2 = "III";
        System.out.println("Input: " + str2);
        System.out.println("Expected: [0, 1, 2, 3], Output: " + 
            java.util.Arrays.toString(diStringMatch(str2)));

        // Test Case 3: All decreasing
        System.out.println("\nSpecial Case - All Decreasing:");
        String str3 = "DDD";
        System.out.println("Input: " + str3);
        System.out.println("Expected: [3, 2, 1, 0], Output: " + 
            java.util.Arrays.toString(diStringMatch(str3)));

        // Test Case 4: Empty string
        System.out.println("\nEdge Case - Empty String:");
        String str4 = "";
        System.out.println("Input: " + str4);
        System.out.println("Expected: [0], Output: " + 
            java.util.Arrays.toString(diStringMatch(str4)));

        // Test Case 5: Alternating pattern
        System.out.println("\nSpecial Case - Alternating Pattern:");
        String str5 = "IDIDID";
        System.out.println("Input: " + str5);
        System.out.println("Expected: [0, 6, 1, 5, 2, 4, 3], Output: " + 
            java.util.Arrays.toString(diStringMatch(str5)));
    }
}
