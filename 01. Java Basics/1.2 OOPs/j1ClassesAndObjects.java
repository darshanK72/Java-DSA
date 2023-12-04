import java.util.Scanner;
public class j1ClassesAndObjects
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);


        Student s1 = new Student();// Making new Object

        // Direct initilization of instent variables
        s1.name = "Darshan Khainrar";
        s1.roll_no = 23;
        s1.marks = 91.20f;


        System.out.println(s1.name);
        System.out.println(s1.roll_no);
        System.out.println(s1.marks);

        in.close();
    }

}


// class student is new user defined object
class Student
{
    // instent variable declaration
    int roll_no;
    String name;
    float marks;
}