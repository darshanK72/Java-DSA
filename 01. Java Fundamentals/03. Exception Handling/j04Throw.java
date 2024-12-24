/**
 * Demonstration of throw keyword in Java
 * Shows how to manually throw exceptions
 */

import java.util.Scanner;


public class j04Throw {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			System.out.println("Enter two numbers:");
			int a = in.nextInt();
			int b = in.nextInt();
			
			// Explicitly throwing exception for division by zero
			if(b == 0) {
				throw new ArithmeticException("Division by zero is not allowed");
			}
			
			// Custom validation
			if(a < 0 || b < 0) {
				throw new IllegalArgumentException("Negative numbers are not allowed");
			}
			
			int c = a/b;
			System.out.println("Result: " + c);
		}
		catch(ArithmeticException ex) {
			System.out.println("Math Error: " + ex.getMessage());
		}
		catch(IllegalArgumentException ex) {
			System.out.println("Input Error: " + ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println("Unexpected Error: " + ex.getMessage());
		}
		finally {
			in.close();
		}
	}

}
