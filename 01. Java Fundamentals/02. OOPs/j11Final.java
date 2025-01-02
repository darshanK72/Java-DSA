/**
 * Topic: `final` Keyword in Java
 * The `final` keyword in Java is used to define constants, prevent method overriding, and prevent class inheritance.
 * 
 * In this example:
 * 1) **Final Variable**: A variable declared with the `final` keyword cannot be reassigned a new value once it is initialized.
 * 2) **Final Method**: A method declared with the `final` keyword cannot be overridden in subclasses.
 * 3) **Final Class**: A class declared as `final` cannot be extended (i.e., it cannot be subclassed).
 */

public class j11Final {

	public static void main(String[] args) {
		final int speed_limit = 30;

		/*
		 * Uncommenting the next line will result in an error because the variable is
		 * final.
		 * speed_limit += 32; // Cannot modify a final variable.
		 */

		// Creating an object of Addition2 (which extends the Addition class)
		Addition2 n = new Addition2();

		// Calling the method add() from Addition2 class
		System.out.println(n.add(3, 2)); // Output: 5

		// Printing the final variable speed_limit
		System.out.println(speed_limit); // Output: 30
	}
}

// Base class Addition with a final method
class Addition {
	final int add(int a, int b) {
		return a + b;
	}
}

// Subclass Addition2 cannot override the final method from Addition class
final class Addition2 extends Addition {
	// Uncommenting the next block will result in a compile-time error because we
	// cannot override the final method.
	/*
	 * int add(int a, int b) {
	 * return a + b;
	 * }
	 */
}

// Uncommenting the next class will result in an error because Addition2 is a
// final class and cannot be extended
/*
 * class Addition3 extends Addition2 {
 * 
 * }
 */
