/**
 * Topic: Static Variables and Methods in Java
 * - **Static variables** are shared among all instances of a class, meaning they belong to the class rather than to any specific object.
 * - **Static methods** can be called using the class name, and they can only access static members of the class.
 * - In this example, the `Employee` class has a static variable `count_of_employee`, which keeps track of the number of `Employee` objects created.
 * - Static members are initialized only once, and they maintain a single value across all instances of the class.
 * - Static variables can be accessed both by class name and by object references.
 */

public class j03StaticVars {
    public static void main(String args[]) {
        // Creating Employee objects
        Employee e1 = new Employee("Darshan", 1, 25);
        Employee e2 = new Employee("Abhishek", 2, 21);

        // Accessing the static variable count_of_employee using the class name
        System.out.println(Employee.count_of_employee); // Output: 2 (because two employees are created)

        // Static variable count_of_employee can also be accessed using an object
        // reference (but not recommended)
        // System.out.println(e1.count_of_employee);
        // System.out.println(e2.count_of_employee);

        // Printing details of employees
        System.out.println(e1.name + " " + e1.id + " " + e1.age);
        System.out.println(e2.name + " " + e2.id + " " + e2.age);
    }
}

class Employee {
    // Static variable shared by all instances of Employee class
    static int count_of_employee; // Keeps track of the number of Employee objects created

    // Instance variables for each object of Employee
    String name;
    int id;
    int age;

    // Constructor to initialize Employee object and increment the static variable
    Employee(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
        // Increment the static count_of_employee every time a new object is created
        count_of_employee += 1;
    }
}
