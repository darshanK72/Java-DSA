/**
 * Topic: Demonstrating Different Output Methods in Java
 * 
 * This class demonstrates how to output data in Java using three different methods:
 * 
 * 1. `print()` - This method prints the data without adding a newline character at the end. 
 *    It's useful when you want to continue output on the same line.
 * 
 * 2. `println()` - This method prints the data and automatically moves the cursor to the next line. 
 *    It is useful when you want each output to appear on a new line.
 * 
 * 3. `printf()` - This method allows for formatted output. It provides greater control over how data is displayed. 
 *    It uses format specifiers like %d, %s, etc., to format the output according to the specified format.
 *    In this example, we'll demonstrate formatting for integers, such as padding, alignment, and other number formats.
 * 
 * The program takes an integer input from the user, and then uses the three methods to display that integer.
 * It also demonstrates how to format the integer using `printf()` in different ways, such as padding with zeros and aligning the text.
 * 
 * The Scanner class is used to take input from the user.
 * The program closes the scanner after the input is complete to prevent resource leaks.
 */

import java.util.Scanner;

public class j03OutputTypes {
    public static void main(String[] args) {
        // Creating a Scanner object to take user input
        Scanner input = new Scanner(System.in);

        // Taking integer input from the user
        System.out.print("Enter an integer: ");
        int i = input.nextInt();

        // 1. printf() - Formatted output
        // %d is a format specifier for integers
        // \n adds a newline character
        System.out.printf("You Entered Integer: %d\n", i);

        // 2. print() - Prints without adding a newline
        // Using string concatenation with + operator to combine text and integer
        System.out.print("You Entered Integer " + i); // The cursor stays on the same line after printing

        // 3. println() - Prints and moves to the next line
        // Automatically adds a newline character after printing
        System.out.println("You Entered Integer " + i); // Moves to the next line after printing

        // Additional printf() format examples with the same integer
        System.out.printf("Decimal: %d\n", i); // Regular decimal format
        System.out.printf("Padded: %05d\n", i); // Pad the integer with zeros to ensure width 5
        System.out.printf("Right-aligned: %5d\n", i); // Right-align the integer in a field of width 5
        System.out.printf("Left-aligned: %-5d\n", i); // Left-align the integer in a field of width 5

        // Close the Scanner to prevent resource leak
        input.close();
    }
}
