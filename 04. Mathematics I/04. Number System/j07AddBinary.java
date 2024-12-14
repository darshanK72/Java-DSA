/*-
 * Problem Statement:
 * 
 *     Given two binary strings `a` and `b`, return their sum as a binary string.
 *     You must perform the addition without converting the binary strings to decimal or any other base.
 * 
 * Input:
 *     - Two binary strings `a` and `b` where `1 <= a.length, b.length <= 1000`.
 * 
 * Output:
 *     - The sum of `a` and `b` in binary, as a string.
 * 
 * Example:
 *     Input:
 *     "1010" "1011"
 *     Output:
 *     "10101"
 * 
 *     Explanation:
 *     "1010" + "1011" = "10101"
 */

import java.util.Scanner;

public class j07AddBinary {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String n1 = in.next(); // First binary string `a`
        String n2 = in.next(); // Second binary string `b`

        // Call the solution method and print the result
        System.out.println(addBinary(n1, n2)); // Output: the sum of `a` and `b` in binary

        in.close(); // Close the scanner to release resources
    }

    /*-
     * Approach: Binary Addition
     * 
     * Intuition:
     * - The binary addition is similar to decimal addition, except that we add digits in base 2.
     * - We iterate through the binary strings from the least significant bit (rightmost side), adding corresponding digits along with any carry from the previous step.
     * - We handle the carry by dividing the sum by 2 (carry = sum / 2) and storing the remainder as the result digit (sum % 2).
     * - This continues until both strings are fully traversed and no carry is left.
     * 
     * Time Complexity:
     * - O(max(a.length, b.length)), as we are iterating through the two binary strings once.
     * 
     * Space Complexity:
     * - O(max(a.length, b.length)), for the result string.
     * 
     * @param a The first binary string.
     * @param b The second binary string.
     * @return The sum of `a` and `b` in binary.
     */
    public static String addBinary(String a, String b) {
        StringBuilder output = new StringBuilder(); // To store the result
        int carry = 0; // To store the carry during addition
        int i = a.length() - 1; // Pointer to the last character of `a`
        int j = b.length() - 1; // Pointer to the last character of `b`

        // Loop while there are digits to process or there is a carry left
        while (i >= 0 || j >= 0 || carry > 0) {
            int d = carry; // Start with the carry from the previous addition
            if (i >= 0) { // If there are still digits left in `a`
                d += a.charAt(i) - '0'; // Convert the character to integer and add to the sum
                i--; // Move to the previous digit in `a`
            }
            if (j >= 0) { // If there are still digits left in `b`
                d += b.charAt(j) - '0'; // Convert the character to integer and add to the sum
                j--; // Move to the previous digit in `b`
            }
            output.insert(0, d % 2); // Insert the current sum's binary digit at the beginning
            carry = d / 2; // Update the carry for the next step
        }
        return output.toString(); // Return the final binary sum as a string
    }
}
