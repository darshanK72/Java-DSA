/*-
 * Topic: Constructors in Java
 * - Constructors are special methods in Java used to initialize objects when they are created.
 * - There are three types of constructors in Java:
 *   1. **Non-parameterized Constructor**: Initializes an object with default values.
 *   2. **Parameterized Constructor**: Initializes an object with provided values.
 *   3. **Copy Constructor**: Initializes a new object as a copy of an existing object.
 * - Constructors have the same name as the class and do not have a return type.
 * - In this example, the `Student2` class demonstrates these types of constructors.
 */

public class j02Constructors {
    public static void main(String[] args) {

        // Creating Student objects using different constructors
        Student2 s1 = new Student2(); // Non-parameterized constructor
        Student2 s2 = new Student2("Darshan Khairnar", 21, 89.60f); // Parameterized constructor
        Student2 s3 = s2; // Copying object reference from s2 to s3

        // Calling method to print student details
        s1.great_student(); // Output using non-parameterized constructor
        s2.great_student(); // Output using parameterized constructor
        s3.great_student(); // Output using copied reference of s2

        // The following line is commented because 'marks' is declared as final
        // s2.marks = 100; // Error: Cannot assign a value to final variable 'marks'
    }
}

class Student2 {
    String name;
    int roll_no;
    final float marks; // Final variable cannot be changed once initialized

    // Non-parameterized Constructor (default values)
    Student2() {
        name = "Not Mentioned"; // Default value for name
        roll_no = 0; // Default value for roll_no
        marks = 0.0f; // Default value for marks

        System.out.println("Non-Parameterised Constructor Called!");
    }

    // Parameterized Constructor (custom initialization)
    Student2(String name, int roll_no, float marks) {
        this.name = name; // Initialize name with provided value
        this.roll_no = roll_no; // Initialize roll_no with provided value
        this.marks = marks; // Initialize marks with provided value

        System.out.println("Parameterised Constructor Called!");
    }

    // Copy Constructor (copying the values from another Student2 object)
    Student2(Student2 s) {
        this.name = s.name; // Copy name from the passed object
        this.roll_no = s.roll_no; // Copy roll_no from the passed object
        this.marks = s.marks; // Copy marks from the passed object
    }

    // Method to print student details
    void great_student() {
        System.out.println(
                "Hello, " + name + ",\nYour Roll No is " + roll_no + "\nYou got " + marks + "% marks in this semester");
    }

    // Finalize method (garbage collection cleanup, not typically used in Java)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Hello, World");
    }
}
