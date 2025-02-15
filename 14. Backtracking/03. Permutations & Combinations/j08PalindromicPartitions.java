/**
 * Problem Statement:
 * 
 *     Given a string s, partition s such that every substring of the partition is a palindrome.
 *     Return all possible palindrome partitioning of s.
 * 
 * Input:
 *     - A string `s` consisting of lowercase English letters.
 * 
 * Output:
 *     - A list of lists, where each inner list contains strings that are palindromic partitions of `s`.
 * 
 * Example:
 *     Input:
 *         s = "aab"
 *     Output:
 *         [["a","a","b"],["aa","b"]]
 * 
 *     Explanation:
 *         - The palindrome partitioning ["a","a","b"] can be produced by partitioning "aab" into "a", "a", and "b".
 *         - The palindrome partitioning ["aa","b"] can be produced by partitioning "aab" into "aa" and "b".
 */

import java.util.ArrayList;
import java.util.List;

public class j08PalindromicPartitions {

    public static void main(String[] args) {
        String s = "aab";
        System.out.println("Approach 1: Backtracking");
        System.out.println(partition(s));
    }

    /**
     * Approach 1: Backtracking
     * 
     * Intuition:
     * - The problem requires us to generate all possible ways to partition the given string `s`
     *   such that every substring in each partition is a palindrome.
     * - Since we need to explore all possible partitions, a natural approach is **backtracking**,
     *   which allows us to explore all partitions and discard invalid ones.
     * - At every index in the string, we can either take a substring and check if it is a palindrome,
     *   then continue partitioning the remaining string.
     * - If we reach the end of the string with valid partitions, we add the partitioned set to the result.
     * - This ensures that we generate all valid partitions without missing any possibilities.
     * 
     * Explanation:
     * - We start from index `0` and iterate through all possible substrings.
     * - If a substring from `start` to `end` is a palindrome, we recursively call the function
     *   on the remaining part of the string.
     * - If we reach the end of the string, we add the current partitioning list to the final result.
     * - The recursion then backtracks by removing the last added partition and checking for other possibilities.
     * - The `isPalindrome` function checks if a substring is a palindrome by comparing characters from both ends.
     * 
     * Time Complexity:
     * - O(n * 2^n): There are 2^n possible ways to partition a string of length `n` into substrings,
     *   and checking if a substring is a palindrome takes O(n) time.
     * 
     * Space Complexity:
     * - O(n): The space used by the recursion stack and the current list of partitions.
     * 
     * @param s The input string.
     * @return A list of lists containing all possible palindromic partitions.
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        generatePalindromicPartitions(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generatePalindromicPartitions(String s, int start, List<String> currentList,
            List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentList.add(s.substring(start, end + 1));
                generatePalindromicPartitions(s, end + 1, currentList, result);
                currentList.remove(currentList.size() - 1); // Backtrack
            }
        }
    }

    /**
    * Helper Method: isPalindrome
    * 
    * Intuition:
    * - This function checks whether a substring `s[start:end]` is a palindrome.
    * - A palindrome reads the same forward and backward, so we compare characters
    *   from both ends moving towards the center.
    * 
    * Explanation:
    * - We use two pointers: `low` starting from the left and `high` from the right.
    * - We compare `s[low]` and `s[high]`:
    *   - If they are different, return `false` (not a palindrome).
    *   - If they are the same, move both pointers inward.
    * - If the loop completes, the substring is a palindrome, and we return `true`.
    * 
    * Time Complexity:
    * - O(m), where `m` is the length of the substring being checked.
    * 
    * Space Complexity:
    * - O(1), as we use only two pointers.
    * 
    * @param s The input string.
    * @param low The starting index of the substring.
    * @param high The ending index of the substring.
    * @return `true` if the substring `s[low:high]` is a palindrome, otherwise `false`.
    */
    private static boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }

}
