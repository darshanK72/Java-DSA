/**
 * Problem Statement:
 * 
 *     You are given a string `s` consisting of lowercase English letters and an array `shifts` of integers. Each element in
 *     the `shifts` array represents the number of times the corresponding character in the string `s` needs to be shifted
 *     forward in the alphabet. A shift wraps around from 'z' to 'a'.
 * 
 *     Apply the shifts cumulatively from the end of the string to the beginning. After applying all shifts, return the
 *     resulting string.
 * 
 * Input:
 *     - A string `s` of length `n` (1 <= n <= 10^5), consisting of lowercase English letters.
 *     - An integer `n` (size of the string).
 *     - An array `shifts` of size `n` where each element satisfies (0 <= shifts[i] <= 10^9).
 * 
 * Output:
 *     - A string representing the modified string after applying all shifts.
 * 
 * Example:
 *     Input:
 *     "abc"
 *     3
 *     [3, 5, 9]
 * 
 *     Output:
 *     "rpl"
 * 
 *     Explanation:
 *     - Start with "abc".
 *     - The cumulative shifts from the end are calculated as:
 *         shifts[2] = 9, shifts[1] = 5 + 9 = 14, shifts[0] = 3 + 5 + 9 = 17.
 *     - Apply the shifts to the string:
 *         'a' + 17 -> 'r', 'b' + 14 -> 'p', 'c' + 9 -> 'l'.
 *     - Result: "rpl".
 */

import java.util.Scanner;

public class j12ShiftingLetters {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.next(); // Input string
        int n = in.nextInt(); // Number of shifts
        int[] shifts = new int[n];
        for (int i = 0; i < n; i++) {
            shifts[i] = in.nextInt(); // Number of shifts for each character
        }

        // Call the solution method and print the result
        System.out.println(shiftingLetters(s, shifts));
        in.close();
    }

    /**
     * Approach: Cumulative Shifting from End to Start
     * 
     * Intuition:
     * - Each character's shift depends not only on its own value in `shifts` but also on all the shifts that follow it.
     * - To calculate this efficiently, we compute the cumulative shifts while iterating the array from right to left.
     * - Each character is then shifted based on the cumulative shift and wrapped around the alphabet using modulo 26.
     * 
     * Explanation:
     * - Convert the input string `s` into a character array for in-place modification.
     * - Start with a `totalShifts` variable initialized to 0.
     * - Iterate backward through the `shifts` array, updating `totalShifts` and modifying the corresponding character in `s`.
     * - Use modulo 26 to handle wraparound for alphabet shifts.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the string, as we iterate through the string and shifts array once.
     * 
     * Space Complexity:
     * - O(n), for the character array to store the result.
     * 
     * @param s The input string.
     * @param shifts The array of shift values.
     * @return The string after applying all shifts.
     */
    public static String shiftingLetters(String s, int[] shifts) {
        char[] ans = s.toCharArray(); // Convert string to character array
        long totalShifts = 0; // Cumulative shifts

        // Iterate from the end of the shifts array to the beginning
        for (int i = shifts.length - 1; i >= 0; i--) {
            totalShifts += shifts[i]; // Update cumulative shifts
            totalShifts %= 26; // Modulo 26 to handle wraparound
            ans[i] = (char) (((ans[i] - 'a' + totalShifts) % 26) + 'a'); // Apply the shift
        }
        return new String(ans); // Convert back to a string
    }
}
