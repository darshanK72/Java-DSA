/**
 * Topic: **Abstraction in Java**
 * Abstraction is a process of hiding implementation details and showing only functionality to the user.
 * It can be achieved through abstract classes and interfaces.
 * 
 * In this example:
 * 1) **Abstract Class**: The 'Animal' class is abstract and contains both concrete and abstract methods
 * 2) **Encapsulation**: Private fields with getter/setter methods
 * 3) **Inheritance**: Dog class extends Animal class
 * 
 * **Key Points**:
 * - Abstract classes can have both abstract and non-abstract methods
 * - Cannot create instance of abstract class
 * - If a class has abstract method, the class must be abstract
 * - Abstract methods must be implemented by first concrete subclass
 * - Can have constructors and static methods
 * 
 * Example Structure:
 * - Animal (abstract class): Base template for all animals
 * - Dog class: Concrete implementation of Animal
 */

// Let's enhance the code to demonstrate more abstraction concepts:

abstract class Animal
{
	private int leggs;
	private String species;
	private String name;
	private boolean canFly;
	
	public Animal(int leggs, String species, String name, boolean canFly) {
		super();
		this.setLeggs(leggs);
		this.setSpecies(species);
		this.setName(name);
		this.setCanFly(canFly);
	}

	abstract void sound();

	public boolean isCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public int getLeggs() {
		return leggs;
	}

	public void setLeggs(int leggs) {
		this.leggs = leggs;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// Additional abstract methods
	abstract void eat();
	abstract void move();

	// Concrete method with implementation
	public void sleep() {
		System.out.println(getName() + " is sleeping");
	}

	// Template method pattern
	public final void dailyRoutine() {
		wake();
		eat();
		move();
		sleep();
	}

	// Protected method that can be overridden
	protected void wake() {
		System.out.println(getName() + " is waking up");
	}
}

class Dog extends Animal
{
	private String breed;

	public Dog(int leggs, String species, String name, boolean canFly) {
		super(leggs,species,name,canFly);
		this.breed = "Unknown";
	}

	// Constructor with breed
	public Dog(int leggs, String species, String name, boolean canFly, String breed) {
		super(leggs,species,name,canFly);
		this.breed = breed;
	}

	@Override
	void sound()
	{
		System.out.println("Woof! Woof!");
	}

	@Override
	void eat() {
		System.out.println(getName() + " is eating dog food");
	}

	@Override
	void move() {
		System.out.println(getName() + " is running on " + getLeggs() + " legs");
	}

	@Override
	protected void wake() {
		System.out.println(getName() + " the " + breed + " is stretching and waking up");
	}
}

// Add another concrete class
class Bird extends Animal
{
	private double wingspan;

	public Bird(int leggs, String species, String name, boolean canFly, double wingspan) {
		super(leggs,species,name,canFly);
		this.wingspan = wingspan;
	}

	@Override
	void sound() {
		System.out.println("Tweet! Tweet!");
	}

	@Override
	void eat() {
		System.out.println(getName() + " is pecking seeds");
	}

	@Override
	void move() {
		if (isCanFly()) {
			System.out.println(getName() + " is flying with " + wingspan + "m wingspan");
		} else {
			System.out.println(getName() + " is hopping around");
		}
	}
}

public class j13Abstraction {

	public static void main(String[] args) {
		// Create instances
		Dog dog = new Dog(4, "Dogs", "Max", false, "German Shepherd");
		Bird bird = new Bird(2, "Birds", "Tweety", true, 0.3);

		// Demonstrate abstraction and polymorphism
		Animal[] animals = {dog, bird};
		
		for (Animal animal : animals) {
			System.out.println("\nDaily routine for " + animal.getName() + ":");
			animal.dailyRoutine();
		}
	}

}
