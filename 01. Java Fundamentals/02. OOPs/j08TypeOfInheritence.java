/*-
 * Topic: Types of Inheritance in Java
 * Inheritance in Java allows one class to inherit the properties and behaviors of another class. There are different types of inheritance:
 * 
 * 1) **Multilevel Inheritance**: A class inherits from another class, and that class itself inherits from another class.
 * 2) **Multiple Inheritance**: A class inherits from more than one class (not supported directly in Java using classes but can be done using interfaces).
 * 3) **Diamond Inheritance**: A class inherits from two classes that share the same methods, leading to ambiguity (handled by interfaces in Java).
 */

public class j08TypeOfInheritence {

	public static void main(String[] args) {

		// Creating an object of the Rectangle class and calculating the area
		Ractangle r1 = new Ractangle(4, 20, 30);
		System.out.println(r1.area());

		// Creating an object of the Triangle class and calculating the area
		Triangle t1 = new Triangle(3, 8, 9, 3);
		System.out.println(t1.area());
	}
}

/*
 * The Shape1 class is a base class with a single field 'sides' which is
 * inherited by both Rectangle and Triangle classes.
 * 
 * In this example:
 * - Rectangle inherits from Shape1 and calculates the area of the rectangle.
 * - Triangle inherits from Shape1 and calculates the area of the triangle using
 * Heron's formula.
 */

// Base class Shape1
class Shape1 {
	private int sides;

	// Default constructor
	public Shape1() {
	}

	// Constructor with sides initialization
	public Shape1(int sides) {
		super();
		this.setSides(sides);
	}

	// Getter and Setter for sides
	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
}

// Rectangle class inherits from Shape1
class Ractangle extends Shape1 {
	private int length;
	private int breadth;

	// Constructor to initialize sides, length, and breadth
	public Ractangle(int sides, int length, int breadth) {
		super(sides); // calling the parent class constructor
		this.length = length;
		this.breadth = breadth;
	}

	// Method to calculate the area of the rectangle
	public float area() {
		return this.length * this.breadth;
	}
}

// Triangle class inherits from Shape1
class Triangle extends Shape1 {
	private int side_1;
	private int side_2;
	private int side_3;

	// Constructor to initialize sides and the three sides of the triangle
	public Triangle(int side, int side_1, int side_2, int side_3) {
		super(side); // calling the parent class constructor
		this.side_1 = side_1;
		this.side_2 = side_2;
		this.side_3 = side_3;
	}

	// Method to calculate the area of the triangle using Heron's formula
	public float area() {
		float semiPerimeter = (this.side_1 + this.side_2 + this.side_3) / 2;
		System.out.println(semiPerimeter);
		return (float) Math.sqrt(semiPerimeter * (semiPerimeter - this.side_1) * (semiPerimeter - this.side_2)
				* (semiPerimeter - this.side_3));
	}
}
