/**
 * Problem Statement:
 * 
 *     Given a binary string representing a positive integer, calculate its two's complement.
 *     The two's complement of a binary number is obtained by inverting all the bits (ones complement) 
 *     and then adding 1 to the result.
 * 
 * Input:
 *     - A string `bi` representing the binary number (1 <= bi.length() <= 32).
 * 
 * Output:
 *     - A string representing the two's complement of the binary number.
 * 
 * Example:
 *     Input:
 *         "101"
 *     Output:
 *         "011"
 * 
 *     Explanation:
 *         The binary number "101" represents 5. The two's complement of "101" is "011" which represents -5.
 */

import java.util.Scanner;

public class j07TwosComplement {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String num = in.next(); // Input: binary representation of the number
        System.out.println(twosComplement(num)); // Output: the two's complement of the binary number
        in.close();
    }

    /**
     * Approach: Inversion and Addition of 1 for Two's Complement
     * 
     * Intuition:
     * - First, flip all the bits of the input binary string (ones complement).
     * - Then, add 1 to the flipped binary string to get the two's complement.
     * - This process simulates how two's complement is manually computed.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the input binary string, as each bit is processed.
     * 
     * Space Complexity:
     * - O(n), as we create a new string for the output.
     * 
     * @param bi The input binary string.
     * @return The two's complement of the binary string.
     */
    public static String twosComplement(String bi) {
        String flipped = ""; // Initialize flipped binary string
        for (int i = 0; i < bi.length(); i++) {
            flipped += (bi.charAt(i) - '0' == 0) ? '1' : '0'; // Flip each bit
        }

        int carry = 1; // Initialize carry for addition of 1
        StringBuilder output = new StringBuilder();

        // Add 1 to the flipped binary string
        for (int i = flipped.length() - 1; i >= 0; i--) {
            int d = (flipped.charAt(i) - '0' + carry) % 2; // Add carry and compute bit
            carry = (flipped.charAt(i) - '0' + carry) / 2; // Update carry
            output.insert(0, d); // Insert the computed bit at the front
        }

        return output.toString(); // Return the two's complement result
    }
}
