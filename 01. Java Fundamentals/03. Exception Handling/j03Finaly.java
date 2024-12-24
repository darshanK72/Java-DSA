/**
 * Demonstration of finally block in Java
 * Shows how finally block is always executed regardless of exceptions
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class j03Finaly {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			// Get input from user
			System.out.println("Enter first number:");
			int a = in.nextInt();
			
			System.out.println("Enter second number:");
			int b = in.nextInt();
			
			// Perform division
			int c = a/b;
			System.out.println("Result: " + c);
			
			// Demonstrate resource handling
			String str = "Test String";
			System.out.println(str.length()); // Now it won't throw NullPointerException
		}
		catch(ArithmeticException ex) {
			System.out.println("Math Error: Division by zero is not allowed");
			System.out.println("Exception details: " + ex.toString());
		}
		catch(InputMismatchException ex) {
			System.out.println("Input Error: Please enter valid integers only");
			System.out.println("Exception details: " + ex.toString());
		}
		catch(NullPointerException ex) {
			System.out.println("Null Pointer Error: Attempting to access null object");
			System.out.println("Exception details: " + ex.toString());
		}
		catch(Exception ex) {
			System.out.println("Unexpected Error occurred");
			System.out.println("Exception details: " + ex.toString());
		}
		finally {
			// This block will always execute
			System.out.println("\nFinally block execution:");
			System.out.println("1. Cleaning up resources");
			System.out.println("2. Closing scanner");
			if(in != null) {
				in.close();
			}
			System.out.println("3. Program execution completed");
		}
	}

}
