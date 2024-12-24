/**
 * Demonstration of multiple catch blocks in Java
 * Shows how to handle different types of exceptions separately
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class j02MultipleCatchBlock {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			// Get input from user
			System.out.println("Enter first number:");
			int a = in.nextInt();
			
			System.out.println("Enter second number:");
			int b = in.nextInt();
			
			// Perform division and display result
			int c = a/b;
			System.out.println("Result of " + a + " / " + b + " = " + c);
			
			// Demonstrate array bounds exception
			int[] arr = new int[5];
			System.out.println("Accessing array element: " + arr[10]); // Will throw exception
		}
		catch(ArithmeticException ex) {
			// Handles division by zero
			System.out.println("Math Error: " + ex.getMessage());
		}
		catch(InputMismatchException ex) {
			// Handles invalid input format
			System.out.println("Input Error: Please enter valid numbers");
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			// Handles array index out of bounds
			System.out.println("Array Error: " + ex.getMessage());
		}
		catch(Exception ex) {
			// Catches any other unexpected exceptions
			System.out.println("Unexpected Error: " + ex.getMessage());
		}
		finally {
			// Clean up resources
			if(in != null) {
				in.close();
				System.out.println("Scanner closed successfully");
			}
		}
	}

}
