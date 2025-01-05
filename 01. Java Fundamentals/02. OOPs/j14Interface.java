/**
 * Topic: **Interfaces in Java**
 * An interface in Java is a blueprint of a class that contains abstract methods.
 * It represents a contract that implementing classes must fulfill by providing concrete implementations of all declared methods.
 * 
 * In this example, we demonstrate:
 * 1) **Interface Definition**: The 'shapes' interface declares common geometric methods that all shape classes must implement.
 * 2) **Multiple Interface Implementation**: Shows how a class can implement multiple interfaces (shapes, Rotatable).
 * 3) **Interface as Type**: Using interface reference to achieve polymorphism.
 * 
 * **Key Points**:
 * - Interfaces can only declare abstract methods (prior to Java 8)
 * - A class can implement multiple interfaces (unlike inheritance where only one class can be extended)
 * - All methods in an interface are implicitly public and abstract
 * - Interface provides 100% abstraction
 * - Variables in interface are implicitly public, static, and final
 * 
 * Example Structure:
 * - shapes interface: Defines area() and volume() calculations
 * - Rotatable interface: Defines rotation behavior
 * - Cone class: Implements shapes interface
 * - Cylinder class: Implements both shapes and Rotatable interfaces
 */

public class j14Interface {

	public static void main(String[] args) {
		// Create objects of different shapes
		Cone cone = new Cone(5, 10);
		Cylinder cylinder = new Cylinder(3, 8);
		
		// Demonstrate polymorphism using interface reference
		shapes[] shapeArray = {cone, cylinder};
		
		// Calculate and display areas and volumes
		for (shapes shape : shapeArray) {
			System.out.println("Shape: " + shape.getClass().getSimpleName());
			System.out.println("Area: " + shape.area());
			System.out.println("Volume: " + shape.volume());
			System.out.println();
		}
		
		// Demonstrate interface type checking
		if (cylinder instanceof Rotatable) {
			((Rotatable)cylinder).rotate(45);
		}
		
		// Demonstrate multiple interface implementation
		if (cylinder instanceof Colorable) {
			((Colorable)cylinder).setColor("Red");
			System.out.println("Cylinder color: " + ((Colorable)cylinder).getColor());
		}
		
		// Demonstrate default method
		System.out.println("Cylinder circumference: " + cylinder.getCircumference());
		
		// Demonstrate static interface method
		System.out.println("Is 5 a valid dimension? " + Measurable.isValidDimension(5));
	}

}

// Interface defining common methods for geometric shapes
interface shapes
{
	float area();
	float volume();
}

// Interface for shapes that can be rotated
interface Rotatable
{
	void rotate(int degrees);
}

// Add a new interface for colorable shapes
interface Colorable {
	void setColor(String color);
	String getColor();
}

// Add a new interface with default method (Java 8 feature)
interface Measurable {
	default double getCircumference() {
		return 0.0; // Default implementation
	}
	
	// Static method in interface (Java 8 feature)
	static boolean isValidDimension(double value) {
		return value > 0;
	}
}

// Cone class implementing the shapes interface
class Cone implements shapes
{
	private int radius;
	private int height;
	
	// Constructor
	public Cone(int radius, int height) {
		this.radius = radius;
		this.height = height;
	}
	
	public float area()
	{
		return (float)(Math.PI*radius*(radius + Math.sqrt(radius*radius + height*height)))  ;
	}
	
	public float volume()
	{
		return (float) (Math.PI * radius * radius * height)/3;
	}
	
}

// Cylinder class implementing both shapes and Rotatable interfaces
class Cylinder implements shapes, Rotatable, Colorable, Measurable
{
	private int radius;
	private int height;
	private int rotation = 0;
	private String color = "None";
	
	public Cylinder(int radius, int height) {
		this.radius = radius;
		this.height = height;
	}
	
	public float area()
	{
		return (float)(2 * Math.PI * radius * (radius + height));
	}
	
	public float volume()
	{
		return (float)(Math.PI * radius * radius * height);
	}
	
	public void rotate(int degrees) {
		this.rotation = (this.rotation + degrees) % 360;
		System.out.println("Cylinder rotated to " + this.rotation + " degrees");
	}
	
	@Override
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String getColor() {
		return this.color;
	}
	
	@Override
	public double getCircumference() {
		return 2 * Math.PI * radius;
	}
}

