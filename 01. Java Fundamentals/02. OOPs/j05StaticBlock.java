/**
 * Topic: Static Blocks in Java
 * - A **static block** in Java is a block of code that is executed once, when the class is loaded into memory. 
 *   It is used to initialize static variables or perform any setup required before an instance of the class is created.
 * - The static block is executed only once during the class loading, regardless of how many objects are created.
 * - In this example, the static block is used to initialize the static variable `population` to 200. Every time a new 
 *   `Human` object is created, the `population` counter is incremented.
 */

public class j05StaticBlock {
    public static void main(String args[]) {
        // Creating Human objects
        Human h1 = new Human(21, "Darshan");

        // Printing the population after first object creation
        System.out.println(Human.population); // Output: 201 (static block initialized population to 200, then
                                              // incremented by 1)

        // Creating second Human object
        Human h2 = new Human(20, "Aakash");

        // Printing the updated population after second object creation
        System.out.println(Human.population); // Output: 202

        // Printing the names of created humans
        System.out.println(h1.name); // Output: Darshan
        System.out.println(h2.name); // Output: Aakash
    }
}

class Human {
    // Static variable to track population of Human objects
    static int population;

    // Instance variables for each Human object
    int age;
    String name;

    // Static block to initialize population variable
    static {
        population = 200; // Population is set to 200 when the class is loaded
    }

    // Constructor to initialize age, name, and increment population
    Human(int age, String name) {
        this.age = age;
        this.name = name;
        Human.population += 1; // Every new Human object increments the population
    }
}
