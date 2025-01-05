/**
 * Topic: Classes and Objects in Java
 * - A class is a blueprint for creating objects in Java.
 * - Objects are instances of classes that hold data (fields) and behavior (methods).
 * - An object in Java is created using the `new` keyword followed by the class constructor.
 * - In this example, the `Student` class represents a student, and an object of this class is created to store student details.
 */

import java.util.Scanner;

public class j01ClassesAndObjects {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Creating a new Student object using the constructor
        Student s1 = new Student(); // Object creation

        // Direct initialization of instance variables
        s1.name = "Darshan Khairnar"; // Assigning value to 'name'
        s1.roll_no = 23; // Assigning value to 'roll_no'
        s1.marks = 91.20f; // Assigning value to 'marks'

        // Displaying the values of the Student object
        System.out.println(s1.name); // Output: Darshan Khairnar
        System.out.println(s1.roll_no); // Output: 23
        System.out.println(s1.marks); // Output: 91.20

        in.close();
    }
}

// Class definition for 'Student'
class Student {
    // Instance variable declarations (attributes of the object)
    int roll_no; // Roll number of the student
    String name; // Name of the student
    float marks; // Marks obtained by the student
}
