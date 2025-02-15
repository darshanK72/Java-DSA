/**
 * Problem Statement:
 * 
 * Given an uppercase English string, convert it into a sequence of keypresses 
 * as they would be entered on an old mobile numeric keypad.
 * 
 * Input:
 *     - A string `s` (1 <= s.length() <= 100), consisting of uppercase English 
 *       letters and spaces.
 * 
 * Output:
 *     - A numeric string representing the sequence of keypresses.
 * 
 * Example:
 *     Input:
 *         "HELLO WORLD"
 *     Output:
 *         "4433555 555666096667775553"
 * 
 *     Explanation:
 *         - 'H' → '44' (H is on key 4, pressed twice).
 *         - 'E' → '33' (E is on key 3, pressed twice).
 *         - 'L' → '555' (L is on key 5, pressed three times).
 *         - 'O' → '666' (O is on key 6, pressed three times).
 *         - ' ' → '0' (Space is represented by 0).
 *         - 'W' → '9' (W is on key 9, pressed once).
 *         - 'O' → '666' (same as earlier).
 *         - 'R' → '777' (R is on key 7, pressed three times).
 *         - 'L' → '555' (same as earlier).
 *         - 'D' → '3' (D is on key 3, pressed once).
 */

import java.util.Scanner;

public class j11NumericKeypadSequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(printSequence(s));
        in.close();
    }

    /**
     * Approach: Mapping Characters to Keypresses
     * 
     * Intuition:
     * - On an old mobile keypad, each number key is associated with multiple letters.
     * - Pressing a key multiple times cycles through its letters.
     * - Space is mapped to '0'.
     * - By creating a mapping from characters to their corresponding keypress sequences, 
     *   we can efficiently convert an input string to its numeric representation.
     * 
     * Explanation:
     * - We create an array that maps each character ('A'-'Z' and ' ') to a sequence of numbers.
     * - Iterate through the input string, replacing each character with its corresponding 
     *   sequence from the mapping.
     * - Concatenate the results to build the final output.
     * 
     * Time Complexity:
     * - O(N), where N is the length of the input string (since each character lookup is O(1)).
     * 
     * Space Complexity:
     * - O(1), as we only use a fixed-size mapping array.
     */
    public static String printSequence(String s) {
        // Mapping of characters to keypad sequences
        String[] keyMap = {
                "0", // Space
                "2", "22", "222", // A, B, C
                "3", "33", "333", // D, E, F
                "4", "44", "444", // G, H, I
                "5", "55", "555", // J, K, L
                "6", "66", "666", // M, N, O
                "7", "77", "777", "7777", // P, Q, R, S
                "8", "88", "888", // T, U, V
                "9", "99", "999", "9999" // W, X, Y, Z
        };

        StringBuilder ans = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                ans.append(keyMap[0]);
            } else {
                ans.append(keyMap[c - 'A' + 1]);
            }
        }

        return ans.toString();
    }
}
