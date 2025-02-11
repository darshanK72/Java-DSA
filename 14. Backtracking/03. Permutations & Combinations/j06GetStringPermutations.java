/**
 * Problem Statement:
 * 
 *     Given a string `s`, the task is to generate all possible unique permutations 
 *     of the string while considering duplicate characters. The output should contain 
 *     only distinct permutations.
 * 
 * Input:
 *     - A string `s` consisting of lowercase English letters (1 <= s.length <= 10).
 * 
 * Output:
 *     - A list of all unique permutations of the string `s`.
 * 
 * Example:
 *     Input:
 *     "abc"
 *     
 *     Output:
 *     ["abc", "acb", "bac", "bca", "cab", "cba"]
 * 
 *     Explanation:
 *     The output shows all possible permutations of the string "abc".
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class j06GetStringPermutations {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.next();

        // Approach 1: Backtracking using a visited array
        printPermutations1(s, "", new boolean[s.length()]);

        // Approach 2: Backtracking with a HashSet to store unique permutations
        HashSet<String> set = new HashSet<>();
        generateStringPermutations1(s, "", set, new boolean[s.length()]);
        System.out.println(set);

        // Approach 3: Backtracking with swapping technique
        ArrayList<String> set2 = new ArrayList<>();
        generateStringPermutations3(s, 0, s.length() - 1, set2);
        System.out.println(set2);

        in.close();
    }

    /**
     * Approach 1: Backtracking with visited array
     * 
     * Intuition:
     * - The algorithm uses a boolean array `visited` to keep track of the characters 
     *   already included in the current permutation.
     * - We iterate through each character and recursively generate all possible permutations.
     * 
     * Explanation:
     * - If the length of the `current` string equals the length of `s`, it is a valid permutation.
     * - The loop iterates through each character, marking it as visited, adding it to `current`,
     *   and calling the function recursively.
     * - After recursion, we backtrack by unmarking the character as visited.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the string.
     * - Generating n! permutations, and each takes O(n) operations.
     * 
     * Space Complexity:
     * - O(n), due to recursion depth.
     */
    public static void printPermutations1(String s, String current, boolean[] visited) {
        if (current.length() == s.length()) {
            System.out.println(current);
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                printPermutations1(s, current + s.charAt(i), visited);
                visited[i] = false;
            }
        }
    }

    /**
     * Approach 2: Backtracking with HashSet for Unique Permutations
     * 
     * Intuition:
     * - Similar to Approach 1, but instead of printing the permutations, 
     *   we store them in a HashSet to ensure uniqueness.
     * 
     * Explanation:
     * - A visited array keeps track of already used characters.
     * - We generate all permutations and store them in a HashSet to remove duplicates.
     * - Backtracking ensures all possibilities are explored.
     * 
     * Time Complexity:
     * - O(n! * n), where n is the length of the string.
     * - Since we store permutations in a HashSet, insertion takes O(1), 
     *   making it more efficient in eliminating duplicates.
     * 
     * Space Complexity:
     * - O(n! * n), due to storing permutations in the HashSet.
     */
    public static void generateStringPermutations1(String s, String curr, HashSet<String> set, boolean[] visited) {
        if (curr.length() == s.length()) {
            set.add(curr);
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                generateStringPermutations1(s, curr + s.charAt(i), set, visited);
                visited[i] = false;
            }
        }
    }

    /**
     * Approach 3: Backtracking with Swapping (Optimized Approach)
     * 
     * Intuition:
     * - This approach does not require extra space for visited arrays.
     * - Instead, it swaps characters in place to generate permutations.
     * - This avoids duplicate calculations and reduces auxiliary space.
     * 
     * Explanation:
     * - The algorithm starts from index `left` and recursively generates permutations.
     * - We swap characters at `left` and `i`, then recursively call for `left+1`.
     * - After recursion, we swap back to restore the original order (backtracking).
     * - This ensures all possible permutations are generated efficiently.
     * 
     * Time Complexity:
     * - O(n!), since we generate all possible permutations.
     * 
     * Space Complexity:
     * - O(n), as we use a recursion stack of depth `n`.
     */
    public static void generateStringPermutations3(String str, int left, int right, ArrayList<String> set) {
        if (left == right) {
            set.add(str);
        } else {
            for (int i = left; i <= right; i++) {
                str = swap(str, left, i);
                generateStringPermutations3(str, left + 1, right, set);
                str = swap(str, left, i); // Backtracking step
            }
        }
    }

    /**
     * Helper Method: Swap Two Characters in a String
     * - Converts string to a character array, swaps elements, and returns the modified string.
     */
    public static String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
