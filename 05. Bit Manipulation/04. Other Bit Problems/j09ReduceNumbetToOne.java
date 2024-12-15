/**
 * Problem Statement:
 * 
 *     You are given a binary string `s` representing a positive integer. Your task is to reduce the number represented by `s` to 1.
 *     The operations allowed are:
 *     - If the number is even, divide it by 2 (equivalent to removing the last '0' from the binary string).
 *     - If the number is odd, add 1 to it.
 * 
 * Input:
 *     - A binary string `s` (1 <= length(s) <= 10^5), where `s` does not contain leading zeros.
 * 
 * Output:
 *     - An integer representing the minimum number of operations required to reduce the number to 1.
 * 
 * Example:
 *     Input:
 *         s = "1101"
 *     Output:
 *         6
 * 
 *     Explanation:
 *         Starting with "1101" (13 in decimal):
 *         - Add 1: "1110" (14)
 *         - Divide by 2: "111" (7)
 *         - Add 1: "1000" (8)
 *         - Divide by 2: "100" (4)
 *         - Divide by 2: "10" (2)
 *         - Divide by 2: "1" (1)
 *         Total steps: 6.
 */

import java.util.Scanner;

public class j09ReduceNumbetToOne {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s = in.next(); // Input: binary string

        // Call the basic solution
        System.out.println(reduceToOne(s));

        // Call the optimized solution
        System.out.println(reduceToOneEfficient(s));

        in.close();
    }

    /**
     * Approach 1: Simulate Binary Reduction with String Manipulation
     * 
     * Intuition:
     * - Simulate the operations using string manipulation to directly represent the binary value.
     * - If the string ends with '0', remove the last character (equivalent to dividing by 2).
     * - If the string ends with '1', simulate adding 1 by creating a new binary string.
     * - Continue until the string becomes "1".
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the length of the string. Each addition operation creates a new string.
     * 
     * Space Complexity:
     * - O(n), due to the temporary string storage during addition operations.
     * 
     * @param s The binary string.
     * @return The number of operations required to reduce the binary number to 1.
     */
    public static int reduceToOne(String s) {
        int count = 0; // Number of operations
        while (s.length() != 1) {
            if (s.charAt(s.length() - 1) == '0') {
                s = s.substring(0, s.length() - 1); // Remove the last character
            } else {
                s = addOne(s); // Add 1 to the binary number
            }
            count++;
        }
        return count;
    }

    /**
     * Helper function to add 1 to a binary string.
     * 
     * @param s The binary string.
     * @return The binary string after adding 1.
     */
    public static String addOne(String s) {
        char carry = '1'; // Carry to add
        StringBuilder sb = new StringBuilder(""); // Resultant binary string
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1' && carry == '1') {
                sb.append('0');
            } else if (s.charAt(i) == '0' && carry == '1') {
                sb.append('1');
                carry = '0'; // No carry after this
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (carry == '1') {
            sb.append('1'); // Append carry if it still exists
        }
        return sb.reverse().toString();
    }

    /**
     * Approach 2: Efficient Simulation with Boolean Carry
     * 
     * Intuition:
     * - Instead of manipulating strings, use a boolean carry to track if a "1" needs to be added.
     * - Traverse the string from right to left, simulating operations without creating new strings.
     * - For odd numbers, increment the step counter and set the carry to true.
     * - For even numbers, simply divide by 2 by reducing the index.
     * - Account for any leftover carry after processing all bits.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the binary string.
     * 
     * Space Complexity:
     * - O(1), as the solution uses constant extra space.
     * 
     * @param s The binary string.
     * @return The number of operations required to reduce the binary number to 1.
     */
    public static int reduceToOneEfficient(String s) {
        boolean carry = false; // Tracks if there is a carry from adding 1
        int ans = 0; // Number of operations
        for (int i = s.length() - 1; i > 0; i--) {
            char c = s.charAt(i); // Current character
            if (carry) {
                // Handle carry over
                if (c == '0') {
                    c = '1'; // Resolve carry by setting to '1'
                    carry = false; // Carry is resolved
                } else {
                    c = '0'; // Add 1 and generate new carry
                }
            }

            if (c == '1') {
                carry = true; // Set carry if current bit is '1'
                ans++;
            }
            ans++; // Each step counts as an operation
        }
        if (carry) {
            ans++; // Account for final carry
        }
        return ans;
    }
}
