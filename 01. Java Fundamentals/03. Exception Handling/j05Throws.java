/**
 * Demonstration of throws keyword in Java
 * Shows how to declare exceptions that a method might throw
 */

import java.util.Scanner;
import java.util.InputMismatchException;


public class j05Throws {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			System.out.println("Enter two numbers for division:");
			int a = in.nextInt();
			int b = in.nextInt();
			
			// Calling method that declares throws
			System.out.println("Result: " + divide(a,b));
		}
		catch(ArithmeticException ex) {
			System.out.println("Division Error: " + ex.getMessage());
		}
		catch(InputMismatchException ex) {
			System.out.println("Invalid input: Please enter numbers only");
		}
		finally {
			in.close();
		}
	}
	
	/**
	 * Divides two numbers and throws ArithmeticException if division by zero
	 * @param a numerator
	 * @param b denominator
	 * @return result of division
	 * @throws ArithmeticException when denominator is zero
	 */
	static int divide(int a, int b) throws ArithmeticException {
		if(b == 0) {
			throw new ArithmeticException("Cannot divide by zero!");
		}
		return a/b;
	}

}
