/**
 * Problem Statement:
 * 
 *      Given a string of digits, where each digit (2-9) represents a set of 
 *      characters on a traditional mobile phone keypad, generate all possible 
 *      letter combinations that the number could represent.
 * 
 * Input:
 *     - A string `s` consisting of digits (2-9) only.
 * 
 * Output:
 *     - A list of all possible letter combinations the input number could represent.
 * 
 * Example:
 *     Input:
 *         "23"
 *     Output:
 *         ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 * 
 *     Explanation:
 *         - '2' corresponds to "abc"
 *         - '3' corresponds to "def"
 *         - Combining letters of '2' with letters of '3' gives the output.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class j01GetKeypadCombination {

    public static String[] keymap = {
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        System.out.println("Recursive Approach Output:");
        printKeypadCombinations(s, "");

        System.out.println("Backtracking Approach Output:");
        System.out.println(getKeypadCombinations(s));

        System.out.println("Optimized Backtracking Approach Output:");
        System.out.println(keypadCombinations(s));

        in.close();
    }

    /**
     * Approach 1: Recursive Brute Force
     * 
     * Intuition:
     * - We generate all possible letter combinations by recursively processing each digit. 
     * - At each step, we extract the first digit, map it to its corresponding letters, and append 
     *   each letter to the result recursively until all digits are processed.
     * - This approach is straightforward but inefficient because it repeatedly creates new strings 
     *   at each recursion level, leading to high time and space complexity.
     * 
     * Explanation:
     * - Base Case: If the input string is empty, print the accumulated result and return.
     * - Recursive Step:
     *   1. Extract the first digit from the string.
     *   2. Get the corresponding characters for this digit from `keymap`.
     *   3. Iterate through each character in the mapping.
     *   4. Append the character to the current string and recursively process the remaining digits.
     *   5. This continues until all digits are processed.
     * 
     * Time Complexity:
     * - O(4^n), where n is the length of the input string (worst case when digit 7 or 9 appears).
     * 
     * Space Complexity:
     * - O(n) due to recursion stack depth.
     */
    public static void printKeypadCombinations(String str, String current) {
        if (str.length() == 0) {
            System.out.println(current);
            return;
        }
        char c = str.charAt(0);
        String keys = keymap[c - '1'];
        for (int i = 0; i < keys.length(); i++) {
            printKeypadCombinations(str.substring(1), current + keys.charAt(i));
        }
    }

    /**
     * Approach 2: Backtracking (Improved Recursion)
     * 
     * Intuition:
     * - Instead of printing directly, we store the generated combinations in a list.
     * - The idea is to recursively generate letter combinations, but instead of repeatedly 
     *   creating new strings, we maintain a list and build results iteratively.
     * - This reduces redundant computations and improves efficiency over the brute-force approach.
     * 
     * Explanation:
     * - Base Case: If the input string is empty, return a list with an empty string.
     * - Recursive Step:
     *   1. Extract the first digit from the string.
     *   2. Recursively compute the letter combinations for the remaining digits.
     *   3. Retrieve the characters corresponding to the current digit from `keymap`.
     *   4. For each letter in this mapping, append it to each combination in the previous result.
     *   5. Store the new combinations in a new list and return it.
     * 
     * Time Complexity:
     * - O(4^n), as each digit can map to 3-4 characters.
     * 
     * Space Complexity:
     * - O(4^n) for storing the results.
     */
    public static ArrayList<String> getKeypadCombinations(String str) {
        if (str.length() == 0) {
            ArrayList<String> arr = new ArrayList<>();
            arr.add("");
            return arr;
        }
        char c = str.charAt(0);
        String s = str.substring(1);
        ArrayList<String> res = getKeypadCombinations(s);
        String keys = keymap[c - '1'];

        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < keys.length(); i++) {
            for (String r : res) {
                output.add(keys.charAt(i) + r);
            }
        }
        return output;
    }

    /**
     * Approach 3: Optimized Backtracking with StringBuilder
     * 
     * Intuition:
     * - The key improvement here is to use a `StringBuilder` instead of concatenating new strings.
     * - String concatenation in Java creates a new object every time, which is inefficient.
     * - Using a `StringBuilder` allows us to modify the string in-place, reducing memory usage.
     * - We use backtracking to explore all possible letter combinations efficiently.
     * 
     * Explanation:
     * - Base Case: If the index reaches the length of the input string, store the built combination.
     * - Recursive Step:
     *   1. Retrieve the characters mapped to the current digit.
     *   2. Iterate through them, append to the `StringBuilder`, and recurse to the next digit.
     *   3. Backtrack by removing the last appended character to explore new options.
     *   4. This avoids creating unnecessary copies of strings, reducing space complexity.
     * 
     * Time Complexity:
     * - O(4^n), same as previous approaches but optimized in execution.
     * 
     * Space Complexity:
     * - O(n) due to recursion depth, but less than the previous approach due to no extra lists.
     */
    public static List<String> keypadCombinations(String digits) {
        List<String> set = new ArrayList<>();
        if (digits.length() == 0)
            return set;
        String[] words = new String[] {
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        generateLetterCombinations(words, digits, 0, "", set);
        return set;
    }

    public static void generateLetterCombinations(String[] words, String digits, int index, String curr,
            List<String> set) {
        if (index > digits.length())
            return;
        if (index == digits.length()) {
            set.add(curr);
            return;
        }

        String word = words[digits.charAt(index) - '0'];

        for (int i = 0; i < word.length(); i++) {
            generateLetterCombinations(words, digits, index + 1, curr + word.charAt(i), set);
        }
    }
}
