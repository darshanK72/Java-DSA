/**
 * Topic: Conditional Statements in Java
 *
 * In this program, we demonstrate two main types of conditional statements in Java:
 * 
 * 1. **If-Else Statement**:
 *    - Used when you want to make decisions based on a boolean condition.
 *    - If the condition is true, one block of code is executed, and if it is false, another block of code runs.
 *    - Example: Checking whether the entered number is equal to 20 or not.
 *
 * 2. **Switch Statement**:
 *    - Used to select one of many code blocks to execute based on the value of a variable.
 *    - The switch statement is more efficient than using multiple `if-else` statements when you have many possible values to check.
 *    - Example: Performing different operations (addition, subtraction, multiplication, division) based on the entered operator.
 * 
 * The program allows the user to:
 * - Input a number and check if it's equal to 20 using an `if-else` statement.
 * - Perform a basic arithmetic operation based on the operator entered using a `switch` statement.
 */

import java.util.Scanner;

public class j07ConditionalStatements {
    public static void main(String args[]) {
        // Create Scanner object to take user input
        Scanner in = new Scanner(System.in);

        // if-else statement: Checking if the number entered is 20
        System.out.print("Enter a number: ");
        int a = in.nextInt();

        if (a == 20) {
            System.out.println("You Entered Twenty");
        } else {
            System.out.println("You Entered Other Number, Not Twenty");
        }

        // Printing message for Calculator Program
        System.out.println("\nCalculator Program");

        // Input two integers and the operator
        System.out.print("Enter first number: ");
        int x = in.nextInt();
        System.out.print("Enter second number: ");
        int y = in.nextInt();

        System.out.print("Enter an operator (+, -, *, /): ");
        char c = in.next().charAt(0); // Reading the operator

        // switch statement: Performing arithmetic operations based on the entered
        // operator
        switch (c) {
            case '+':
                System.out.println("Result: " + (x + y));
                break;

            case '-':
                System.out.println("Result: " + (x - y));
                break;

            case '*':
                System.out.println("Result: " + (x * y));
                break;

            case '/':
                if (y != 0) {
                    System.out.println("Result: " + (x / y));
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                }
                break;

            default:
                System.out.println("You entered a wrong operator.");
                break;
        }

        // Close the Scanner object to avoid memory leaks
        in.close();
    }
}
