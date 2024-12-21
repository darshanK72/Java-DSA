/*-
 * Topic: Inheritance in Java
 * - **Inheritance** is a mechanism where one class acquires the properties and behaviors (fields and methods) of another class. In Java, a class can inherit from another using the `extends` keyword.
 * - In this example, the `Circle` class extends the `Shape` class, meaning it inherits the `vertices` and `edges` properties from `Shape` and adds its own property `circumference`.
 * - The main advantage of inheritance is **code reusability**, as the child class doesn't need to rewrite the code for the properties of the parent class.
 */

public class j06Inheritence {
    public static void main(String args[]) {
        // Creating Shape object
        Shape s = new Shape(2, 1);

        // Printing the properties of Shape
        System.out.println("No of Vertices = " + s.vertices + " | No of Edges = " + s.edges);

        // Creating Circle object which inherits from Shape
        Circle c = new Circle(1, 1, 15);

        // Printing the properties of Circle
        System.out.println("No of Vertices = " + c.vertices + " | No of Edges = " + c.edges + " | Circumference = "
                + c.circumference);

        // Creating Circle object but referring it through Shape reference
        Shape s1 = new Circle(2, 3, 55);

        // Printing the properties of Shape
        // We cannot access child class specific properties (circumference) through
        // parent class reference
        System.out.println("No of Vertices = " + s1.vertices + " | No of Edges = " + s1.edges);
        // Uncommenting the following line will result in a compile-time error because
        // `s1` is of type Shape and doesn't have access to `circumference` of Circle.
        // System.out.println("Circumference = " + s1.circumference);
    }
}

// Parent class Shape
class Shape {
    int vertices;
    int edges;

    // Default constructor
    Shape() {
        this.vertices = 0;
        this.edges = 0;
    }

    // Constructor to initialize vertices and edges
    Shape(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    // Copy constructor
    Shape(Shape s) {
        this.vertices = s.vertices;
        this.edges = s.edges;
    }
}

// Child class Circle which extends Shape
class Circle extends Shape {
    int circumference;

    // Default constructor for Circle
    Circle() {
        super(); // Calls the parent class constructor
        this.circumference = 0;
    }

    // Constructor to initialize Shape properties and circumference
    Circle(int vertices, int edges, int circumference) {
        super(vertices, edges); // Calls the parent class constructor to initialize Shape properties
        this.circumference = circumference;
    }

    // Copy constructor for Circle
    Circle(Circle c) {
        super(c); // Calls the parent class copy constructor
        this.circumference = c.circumference;
    }
}
