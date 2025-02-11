/**
 * Problem Statement:
 * 
 *     Given an integer `n`, generate all binary strings of length `n` where no two consecutive 1's appear.
 *     A binary string is a string that consists only of '0' and '1'.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 20), representing the length of the binary string.
 * 
 * Output:
 *     - A list of binary strings of length `n` where no two consecutive '1's appear.
 * 
 * Example:
 *     Input:
 *     n = 3
 *     
 *     Output:
 *     ["000", "001", "010", "100", "101"]
 * 
 *     Explanation:
 *     - The binary strings of length 3 that do not have consecutive 1's are: "000", "001", "010", "100", "101".
 */

import java.util.ArrayList;
import java.util.List;

public class j03GenerateBinaryString {

    public static void main(String[] args) {
        int n = 3; // You can change this value for different tests

        ArrayList<String> set = new ArrayList<>();
        generateBinaryStrings(n, 0, "", set);

        System.out.println("Binary strings of length " + n + " with no consecutive 1's:");
        for (String binaryString : set) {
            System.out.println(binaryString);
        }
    }

    /**
     * Approach 1: Recursive Backtracking
     * 
     * Intuition:
     * - The idea is to generate the binary strings by recursively building each string from left to right.
     * - We start with an empty string and at each step we append either '0' or '1' to the current string.
     * - However, to ensure that no two consecutive 1's appear, we only append '1' if the last character added is not '1'.
     * - If the string reaches the desired length `n`, it is added to the result list.
     * 
     * Time Complexity:
     * - O(2^n) because we are generating all possible binary strings of length n, which are 2^n in number.
     * 
     * Space Complexity:
     * - O(n) for the recursion call stack.
     * 
     * @param n The length of the binary strings to generate.
     * @param index The current index in the binary string.
     * @param current The current binary string being generated.
     * @param set The list to store the generated binary strings.
     * @return A list of binary strings of length n with no consecutive 1's.
     */

    public static void generateBinaryStrings(int n, int index, String current, List<String> set) {
        if (index == n) {
            set.add(current);
            return;
        }

        // If the last character was '1', we can only add '0'
        if (current.length() > 0 && current.charAt(current.length() - 1) == '1') {
            generateBinaryStrings(n, index + 1, current + "0", set);
        } else {
            // We can add both '0' and '1' in this case
            generateBinaryStrings(n, index + 1, current + "0", set);
            generateBinaryStrings(n, index + 1, current + "1", set);
        }
    }
}
