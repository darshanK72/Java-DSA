/**
 * Problem Statement:
 * 
 *     Given two non-negative integers represented as strings, multiply the two numbers and return the result as a string.
 *     Note that the result should not contain leading zeros, unless the result is zero itself.
 * 
 * Input:
 *     - Two strings `num1` and `num2` representing non-negative integers.
 *     - Both strings contain only digits, and may have leading zeros.
 * 
 * Output:
 *     - Return a string representing the product of the two numbers.
 * 
 * Example:
 *     Input:
 *     "123"
 *     "456"
 *     Output:
 *     "56088"
 * 
 *     Explanation:
 *     123 * 456 = 56088.
 */

import java.util.Scanner;

public class j04MultiplyTwoStrings {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        // Calling the function to multiply the strings and printing the result
        System.out.println(multiply(s1, s2));
        in.close();
    }

    /**
     * Approach 1: String-based Multiplication
     * 
     * Intuition:
     * - Multiplying two numbers represented as strings can be done using the traditional long multiplication method.
     * - We multiply each digit of the second number with the first number, and then shift the result based on the position of the digit in the second number.
     * - For each multiplication, we add the results, accounting for carries.
     * - The result string is constructed by adding partial products from each multiplication step.
     * 
     * Time Complexity:
     * - O(num1.length() * num2.length()) - We multiply each digit of num1 with each digit of num2.
     * 
     * Space Complexity:
     * - O(num1.length() + num2.length()) - The result can be at most the sum of the lengths of num1 and num2.
     * 
     * @param num1 The first string representing a number.
     * @param num2 The second string representing a number.
     * @return The product of the two numbers as a string.
     */
    public static String multiply(String num1, String num2) {
        // If either of the numbers is "0", the result is "0"
        if (num2.equals("0") || num1.equals("0"))
            return "0";

        int i = num2.length() - 1; // Pointer for num2 (starting from the rightmost digit)
        String ans = ""; // Final result
        String k = ""; // For handling positional shifts of the partial product

        // Multiply each digit of num2 with num1
        while (i >= 0) {
            int carry = 0; // Carry for handling overflow during multiplication
            int j = num1.length() - 1; // Pointer for num1
            StringBuilder temp = new StringBuilder(""); // Temporary string for each partial product

            // Multiply each digit of num1 with current digit of num2
            while (j >= 0 || carry > 0) {
                int d = 0; // Partial product digit
                if (j >= 0) {
                    d = (num1.charAt(j) - '0'); // Convert char to digit
                }
                if (i >= 0) {
                    d *= (num2.charAt(i) - '0'); // Multiply with current digit of num2
                }

                d += carry; // Add carry from previous step
                temp.append((char) (d % 10 + '0')); // Store the current digit of the product
                carry = d / 10; // Calculate the new carry
                j--; // Move left in num1
            }

            // Add the partial product to the final result
            ans = add(ans, temp.reverse().toString() + k);
            k += "0"; // Shift the product position by adding zeros
            i--; // Move left in num2
        }
        return ans;
    }

    /**
     * Approach 2: Addition of Partial Products
     * 
     * Intuition:
     * - In multiplication, the partial products must be added together. This is handled by a helper function that performs addition.
     * - We perform the addition of the result string and the current partial product string.
     * 
     * Time Complexity:
     * - O(num1.length() + num2.length()) - We iterate through each string during the addition process.
     * 
     * Space Complexity:
     * - O(num1.length() + num2.length()) - The space used for storing the result and temporary strings.
     * 
     * @param num1 The first string representing a number.
     * @param num2 The second string representing a number.
     * @return The sum of the two strings as a string.
     */
    public static String add(String num1, String num2) {
        int i = num1.length() - 1; // Pointer for num1
        int j = num2.length() - 1; // Pointer for num2
        int carry = 0; // Carry for addition
        StringBuilder ans = new StringBuilder(); // StringBuilder to hold the result

        // Add digits from both strings with carry
        while (i >= 0 || j >= 0 || carry > 0) {
            int d = carry; // Start with carry
            if (i >= 0) {
                d += num1.charAt(i) - '0'; // Add digit from num1
            }
            if (j >= 0) {
                d += num2.charAt(j) - '0'; // Add digit from num2
            }

            ans.append((char) ('0' + d % 10)); // Append the current digit to the result
            carry = d / 10; // Calculate the new carry
            i--; // Move left in num1
            j--; // Move left in num2
        }
        return ans.reverse().toString(); // Return the result after reversing it
    }
}
