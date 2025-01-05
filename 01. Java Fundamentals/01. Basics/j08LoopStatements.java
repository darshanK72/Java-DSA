/**
 * Topic: Loop Statements in Java
 *
 * In this program, we demonstrate three types of loop statements in Java:
 *
 * 1. **For Loop**:
 *    - Used to execute a block of code a specific number of times.
 *    - It is ideal when you know the number of iterations in advance.
 *    - Example: Printing the multiplication table for a given number.
 *
 * 2. **While Loop**:
 *    - Used to execute a block of code as long as a specified condition is true.
 *    - The condition is evaluated before each iteration.
 *    - Example: Summing up input numbers until a sentinel value (-1) is entered to stop.
 *
 * 3. **Do-While Loop**:
 *    - Similar to the while loop, but the condition is checked **after** the block of code is executed.
 *    - This ensures that the code block is always executed at least once.
 *    - Example: Printing numbers starting from the user input and continuing until the value exceeds 20.
 *
 * The program allows the user to interact with the loops by inputting values for each loop type and observing how
 * each loop behaves based on the input.
 */

import java.util.Scanner;

public class j08LoopStatements {

    public static void main(String[] args) {
        // Create Scanner object to take user input
        Scanner input = new Scanner(System.in);

        // For loop: Printing the multiplication table of a given number
        System.out.print("Enter a number for multiplication table: ");
        int num = input.nextInt(); // Input the number for which the table is to be printed

        System.out.println("Multiplication Table of " + num + ":");
        for (int i = 1; i <= 10; i++) {
            // The loop runs 10 times to print the table (from 1 to 10)
            System.out.println(num + " X " + i + " = " + num * i);
        }

        // While loop: Sum all numbers input until the user enters -1
        // This continues until the user enters -1, and the sum is printed at the end
        System.out.println("\nEnter numbers to sum (Enter -1 to stop): ");
        int s = input.nextInt(); // First input number
        int sum = 0; // Initialize sum to 0
        while (s != -1) {
            // Loop continues as long as input is not -1
            sum += s; // Add the number to the sum
            s = input.nextInt(); // Get next input number
        }
        System.out.println("Total sum: " + sum);

        // Do-While loop: Print numbers from the user input and stop when greater than
        // 20
        System.out.println("\nEnter a starting number: ");
        int i = input.nextInt(); // Input starting number for do-while loop

        System.out.println("Printing numbers using do-while loop:");
        do {
            // This block is guaranteed to execute at least once, no matter what the
            // condition is
            System.out.println(i);
            i++; // Increment the number by 1
        } while (i <= 20); // Loop continues until 'i' exceeds 20

        // Close the Scanner object to avoid memory leaks
        input.close();
    }
}
