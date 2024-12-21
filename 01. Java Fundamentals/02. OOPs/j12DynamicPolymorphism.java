/*-
 * Topic: **Dynamic Polymorphism (Method Overriding) in Java**
 * Dynamic polymorphism, also known as runtime polymorphism, is a process in which a call to an overridden method is resolved at runtime.
 * This is done through method overriding, where a subclass provides its own implementation of a method that is already defined in the parent class.
 * 
 * In this example:
 * 1) **Method Overriding**: In the `FloatOperator` class, the `sum` method overrides the `sum` method from the `IntOperators` class.
 * 2) **Dynamic Method Dispatch**: At runtime, Java decides which version of the `sum` method to invoke based on the object being referred to.
 * 
 * **Key Points**:
 * - A subclass method should have the same signature as the superclass method to override it.
 * - The `@Override` annotation helps in ensuring the method is correctly overriding a parent class method.
 * - `final` methods cannot be overridden, and `final` classes cannot be inherited.
 */

public class j12DynamicPolymorphism {
    public static void main(String args[]) {

        // Creating an object of the parent class
        IntOperators io = new IntOperators();
        // Creating an object of the child class (FloatOperator)
        IntOperators fo = new FloatOperator();

        // Calling sum on the parent class object
        System.out.println(io.sum(10, 20)); // Output: Integer Sum : 30

        // Calling sum on the child class object (via parent class reference)
        System.out.println(fo.sum(20, 92)); // Output: Float Sum : 112
    }
}

// Base class IntOperators
class IntOperators {
    // Method that adds two integers
    int sum(int a, int b) {
        System.out.print("Integer Sum : ");
        return a + b;
    }
}

// Subclass FloatOperator, which overrides the sum method
class FloatOperator extends IntOperators {
    // Overriding the sum method in the subclass
    @Override
    int sum(int a, int b) {
        System.out.print("Float Sum : ");
        return a + b;
    }
}
