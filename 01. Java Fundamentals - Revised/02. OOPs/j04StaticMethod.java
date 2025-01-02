/**
 * Topic: Static Methods in Java
 * - **Static methods** are methods that belong to the class, not instances of the class. They can be 
 *    called using the class name, and they can only access **static** variables or other **static** methods.
 * - A static method cannot refer to instance variables or instance methods directly. This is because 
 *   instance members belong to specific objects, while static methods belong to the class itself.
 * - In the example, the `get_count_of_teachers()` method is static, which allows it to return the count 
 *   of `Teacher` objects created without needing an instance.
 */

public class j04StaticMethod {
    public static void main(String args[]) {
        // Creating Teacher objects
        Teacher t1 = new Teacher(201, 21, "Darshan");
        Teacher t2 = new Teacher(202, 23, "Aakash");

        // Accessing the static method using the class name
        System.out.println(Teacher.get_count_of_teachers()); // Output: 2 (because two Teacher objects are created)

        // Printing teacher names from objects
        System.out.println(t1.teacher_name); // Output: Darshan
        System.out.println(t2.teacher_name); // Output: Aakash
    }
}

class Teacher {
    // Static variable to track the number of Teacher objects
    static int count_of_teachers;

    // Instance variables for each teacher object
    int teacher_id;
    int teacher_age;
    String teacher_name;

    // Constructor to initialize Teacher object and increment the static variable
    Teacher(int id, int age, String name) {
        this.teacher_id = id;
        this.teacher_age = age;
        this.teacher_name = name;
        // Increment the static variable count_of_teachers when a new Teacher is created
        Teacher.count_of_teachers += 1;
    }

    // Static method to get the count of Teacher objects created
    static int get_count_of_teachers() {
        // Cannot access instance variables like teacher_id directly in a static method
        return Teacher.count_of_teachers;
    }
}
