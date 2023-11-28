import java.util.*;

public class j2ObjectsClasses {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Student s1 = new Student();
        // Creating New Student Object from Student Class

        s1.roll_no = 16;
        s1.name = "Darshan";
        s1.marks = 87.5f;

        s1.great_student();

        in.close();
    }
}

class Student {
    // Attributes, Instent variables
    int roll_no;
    String name;
    float marks;

    // Class Methods
    void great_student() {
        System.out.println(
                "Hello, " + name + ",\nYour Roll No is " + roll_no + "\nYou got " + marks + "% marks in this semester");
    }
}