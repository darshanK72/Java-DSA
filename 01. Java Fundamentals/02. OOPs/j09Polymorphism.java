/*-
 * Topic: Polymorphism in Java
 * Polymorphism in Java refers to the ability of a single function, method, or operator to operate on different types of data.
 * There are two main types of polymorphism in Java:
 * 
 * 1) **Method Overloading**: Same method name but with different parameters (either in number or type).
 * 2) **Method Overriding**: Subclass provides a specific implementation for a method that is already defined in its superclass.
 * 
 * Polymorphism allows Java to be more flexible and adaptable, supporting the concept of "one interface, multiple implementations."
 * 
 * In this example:
 * - Method overloading is demonstrated by having multiple `add` methods with different argument types.
 * - Method overriding is demonstrated by the `Square` class overriding the `area` method of the `Ractangleee` class.
 */

public class j09Polymorphism {

	public static void main(String[] args) {

		// Demonstrating function overloading
		System.out.println(add(1, 3)); // Integer addition
		System.out.println(add(23, 54)); // Integer addition
		System.out.println(add("Hello, ", "World")); // String concatenation
		System.out.println(add(2343435, 2233423)); // Long addition

		// Demonstrating method overriding with polymorphism
		Ractangleee r1 = new Ractangleee(23, 245);
		System.out.println(r1.area()); // Rectangle area

		Square s = new Square(53); // Creating Square object
		System.out.println(s.area()); // Overridden square area method
	}

	/*
	 * Function Overloading: Same method name with different parameter types
	 */
	static int add(int a, int b) {
		return a + b; // Integer addition
	}

	static String add(String a, String b) {
		return a + b; // String concatenation
	}

	static float add(float a, float b) {
		return a + b; // Float addition
	}

	static long add(long a, long b) {
		return a + b; // Long addition
	}
}

/*
 * Base class for shape representation
 */
class Ractangleee {
	private int length;
	private int breadth;

	// Constructor to initialize length and breadth
	public Ractangleee(int length, int breadth) {
		super();
		this.length = length;
		this.breadth = breadth;
	}

	// Getter and setter methods for length and breadth
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getBreadth() {
		return breadth;
	}

	public void setBreadth(int breadth) {
		this.breadth = breadth;
	}

	// Method to calculate the area of the rectangle
	public int area() {
		return this.length * this.breadth;
	}
}

/*
 * Square class inherits from Ractangleee and overrides area method
 */
class Square extends Ractangleee {

	// Constructor to initialize the side of the square (both length and breadth are
	// the same)
	Square(int side) {
		super(side, side); // Call to parent constructor
	}

	/*
	 * Method Overriding: Subclass provides specific implementation
	 */
	@Override
	public int area() {
		return this.getLength() * this.getBreadth(); // Calculate area of square
	}
}
