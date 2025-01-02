/**
 * Basic demonstration of try-catch-finally blocks in Java
 * Shows simple exception handling with division by zero example
 */

public class j01TryCatchFinally {

	public static void main(String[] args) {
		// Variables for demonstration
		int a = 10;
		int b = 0;
		
		try {
			// Attempting division by zero to demonstrate exception handling
			System.out.println("Attempting to divide " + a + " by " + b);
			int c = a/b;
			System.out.println("Result: " + c); // This line won't execute
		}
		catch(ArithmeticException ex) {
			// Specific handling for arithmetic exceptions
			System.out.println("Arithmetic Error: " + ex.getMessage());
			System.out.println("Stack Trace:");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			// Generic exception handler for any other unexpected exceptions
			System.out.println("Unexpected Error: " + ex.getMessage());
		}
		finally {
			// This block always executes, regardless of whether an exception occurred
			System.out.println("Program execution completed.");
		}
	}

}
