
/**
 * Problem Statement:
 * 
 *     Write a program that checks whether a given number is Odd or Even. The program
 *     should accept two types of input:
 *     1. An integer input where the program checks if the number is Odd or Even.
 *     2. A string input representing a number, and checks the last digit of the number to determine if it is Odd or Even.
 * 
 *     The program should output the result for both inputs, indicating whether they are Odd or Even.
 * 
 * Input:
 *     - The first input is an integer.
 *     - The second input is a string representing a number.
 * 
 * Output:
 *     - For the integer input, print whether it is Odd or Even.
 *     - For the string input, print whether the last digit of the number is Odd or Even.
 * 
 * Example:
 *     Input:
 *     5
 *     246
 * 
 *     Output:
 *     Number 5 is Odd
 *     Number 246 is Even
 * 
 * Constraints:
 *     - The first input is a non-negative integer.
 *     - The second input is a non-negative number represented as a string.
 */

import java.util.Scanner;

public class j01OddEven {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Reading the first input as an integer and checking if it is Odd or Even
        int number1 = in.nextInt();
        System.out.println("Number " + number1 + " is " + (isOdd(number1) ? "Odd" : "Even"));

        // Reading the second input as a string and checking if the last digit is Odd or
        // Even
        String number2 = in.next();
        System.out.println("Number " + number2 + " is " + (isOdd(number2) ? "Odd" : "Even"));

        in.close();
    }

    /**
     * Method to check if a given integer is Odd.
     * 
     * Time Complexity: O(1), as modulus operation takes constant time.
     * Space Complexity: O(1), no additional space is used.
     * 
     * @param number The integer to check.
     * @return true if the number is Odd, false if the number is Even.
     */
    public static boolean isOdd(int number) {
        return number % 2 == 1; // Returns true if the number is odd, false if even
    }

    /**
     * Method to check if the last digit of the string number is Odd.
     * 
     * Time Complexity: O(1), as it only checks the last character of the string.
     * Space Complexity: O(1), no additional space is used.
     * 
     * @param number The string representing the number.
     * @return true if the last digit of the number is Odd, false if Even.
     */
    public static boolean isOdd(String number) {
        // Check the last character of the string to determine if it's an odd or even
        // digit
        return number.charAt(number.length() - 1) % 2 == 1; // If last digit is odd
    }
}
