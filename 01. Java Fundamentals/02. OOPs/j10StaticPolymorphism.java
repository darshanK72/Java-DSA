/*-
 * Topic: Static Polymorphism (Method Overloading) in Java
 * Static polymorphism, also known as compile-time polymorphism, occurs when a method is defined multiple times in the same class with different parameters (either in number or type). 
 * Java resolves the method to call at compile time based on the method signature.
 * 
 * This example demonstrates method overloading, where the same method name `sum` is used with different argument types (int, float, and String).
 * 
 * In this example:
 * - The `sum` method is overloaded in the `Operator` class for integers, floats, and strings.
 */

public class j10StaticPolymorphism {

    public static void main(String args[]) {
        Operator a = new Operator();

        // Calling overloaded sum method for different argument types
        System.out.println(a.sum(10, 20)); // Integer Sum
        System.out.println(a.sum(13.23f, 536.4f)); // Float Sum
        System.out.println(a.sum("Hello", "Darshan")); // String Concatenation
    }

}

/*
 * Operator class demonstrating method overloading
 */
class Operator {

    // Method for summing two integers
    int sum(int a, int b) {
        System.out.print("Integer Sum : ");
        return a + b;
    }

    // Method for summing two floats
    float sum(float a, float b) {
        System.out.print("Float Sum : ");
        return a + b;
    }

    // Method for concatenating two strings
    String sum(String s1, String s2) {
        System.out.print("String Sum : ");
        return s1 + " " + s2; // String concatenation
    }
}
