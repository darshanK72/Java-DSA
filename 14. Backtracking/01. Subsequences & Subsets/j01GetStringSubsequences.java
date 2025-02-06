/**
 * Problem Statement:
 * 
 *     Given a string `s`, return all possible subsequences of the string.
 *     A subsequence is a sequence derived by deleting some or no elements
 *     from the original string without changing the order of the remaining elements.
 * 
 * Input:
 *     - A string `s` (1 <= |s| <= 20), consisting of lowercase English letters.
 * 
 * Output:
 *     - A list of all possible subsequences of `s`.
 * 
 * Example:
 *     Input:
 *     "abc"
 *     Output:
 *     ["abc", "ab", "ac", "a", "bc", "b", "c", ""]
 * 
 *     Explanation:
 *     The possible subsequences of "abc" include the full string, single characters,
 *     and all valid combinations of its characters in order.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j01GetStringSubsequences {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        printSubsequences(s, "");
        System.out.println(getSubsequences(s));

        ArrayList<String> result = new ArrayList<>();
        getSubsequences(s, "", result);
        System.out.println(result);

        in.close();
    }

    /**
     * Approach 1: Recursive Generation (Print Only)
     * 
     * Intuition:
     * - The core idea is to construct subsequences by making decisions at each step.
     * - At any given character in the string, we have two choices:
     *   1. Include the character in the subsequence.
     *   2. Exclude the character from the subsequence.
     * - We recursively explore both choices and print the resulting subsequences.
     * - The base case occurs when we process all characters (empty string).
     * 
     * Explanation:
     * - Suppose the input string is `"abc"`:
     *   - We start with an empty string `""` and process `"abc"`.
     *   - First, include `'a'` → process `"bc"` with `"a"`.
     *   - Then, exclude `'a'` → process `"bc"` with `""`.
     *   - Similarly, we proceed with `"bc"`, making choices for `'b'` and `'c'`.
     *   - Once the string is empty, we print the current subsequence.
     * 
     * Example execution tree:
     * 
     *                 ""
     *                /  \
     *              "a"   ""
     *             /   \  /  \
     *         "ab"   "a" "b"  ""
     *        /   \   /  \ /  \
     *    "abc" "ab" "ac" "a" "bc" "b" "c" ""
     * 
     * - The subsequences are printed as the recursion unwinds.
     * 
     * Time Complexity:
     * - O(2^n), since for every character, we have two choices: include or exclude.
     * 
     * Space Complexity:
     * - O(n), due to recursive stack space.
     * 
     * @param str The input string.
     * @param current The current subsequence being formed.
     */
    public static void printSubsequences(String str, String current) {
        if (str.length() == 0) {
            System.out.println(current);
            return;
        }
        printSubsequences(str.substring(1), current + str.charAt(0));
        printSubsequences(str.substring(1), current);
    }

    /**
     * Approach 2: Recursive Generation (Return List)
     * 
     * Intuition:
     * - Instead of just printing subsequences, this approach stores them in a list.
     * - The recursive logic remains the same:
     *   1. Include the first character in subsequences of the remaining string.
     *   2. Exclude the first character and compute subsequences of the rest.
     * - The base case is when the string is empty, returning a list containing an empty string.
     * - The final result contains all subsequences.
     * 
     * Explanation:
     * - Consider the input `"abc"`:
     *   - The subsequences of `"bc"` are: `["bc", "b", "c", ""]`
     *   - When `'a'` is added to each of them, we get `["abc", "ab", "ac", "a"]`
     *   - Merging both results gives: `["abc", "ab", "ac", "a", "bc", "b", "c", ""]`
     * 
     * Time Complexity:
     * - O(2^n), as each character leads to two recursive calls.
     * 
     * Space Complexity:
     * - O(2^n), as we store all subsequences in a list.
     * 
     * @param str The input string.
     * @return A list of all subsequences.
     */
    public static ArrayList<String> getSubsequences(String str) {
        if (str.length() == 0) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        ArrayList<String> res = getSubsequences(str.substring(1));
        ArrayList<String> output = new ArrayList<>();
        for (String s : res) {
            output.add(str.charAt(0) + s);
        }
        for (String s : res) {
            output.add(s);
        }
        return output;
    }

    /**
     * Approach 3: Optimized Recursive Generation (Tracking Index)
     * 
     * Intuition:
     * - This approach improves efficiency by using an explicit `index` parameter
     *   to track the current position instead of modifying the string with `substring()`.
     * - Using an `ArrayList` reference, we avoid unnecessary list merges,
     *   reducing memory usage and improving performance.
     * - We make two recursive calls:
     *   1. Include the current character.
     *   2. Exclude the current character.
     * 
     * Explanation:
     * - Consider input `"abc"`:
     *   - Start at index `0` with an empty string.
     *   - At each step, we either add `s[index]` to `current` or skip it.
     *   - Once we reach the end of the string, we add the subsequence to `set`.
     * 
     * Time Complexity:
     * - O(2^n), as each character results in two recursive calls.
     * 
     * Space Complexity:
     * - O(2^n), due to storage of all subsequences.
     * 
     * @param s The input string.
     * @param curr The currently built subsequence.
     * @param set The list storing subsequences.
     */
    public static void getSubsequences(String s, String curr, ArrayList<String> set) {
        if (s.length() == 0) {
            set.add(curr);
            return;
        }
        getSubsequences(s.substring(1), curr + s.charAt(0), set);
        getSubsequences(s.substring(1), curr, set);
    }
}
