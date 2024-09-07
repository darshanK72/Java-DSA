public class j20Constructors {
    public static void main(String[] args)
    {

        Student2 s1 = new Student2();
        Student2 s2 = new Student2("Darshan Khairnar",21,89.60f);
        Student2 s3 = s2;


        s1.great_student();
        s2.great_student();
        s3.great_student();

        //s2.marks = 100; final things cannot be change in java



    }
    
}

class Student2
{
    String name;
    int roll_no;
    final float marks;

    // Non-paramaterised Constructor
    Student2()
    {
        name = "Not Mentioned";
        roll_no = 0;
        marks = 0.0f;

        System.out.println("Non-Parameterised Constructor Called!");
    }

    // Parameterised Constructor
    Student2(String name,int roll_no,float marks)
    {
        this.name = name;
        this.roll_no = roll_no;
        this.marks = marks;

        System.out.println("Parameterised Constructor Called!");
    }

    // Copy Constructor
    Student2(Student2 s)
    {
        this.name = s.name;
        this.roll_no = s.roll_no;
        this.marks = s.marks;

    }

    void great_student()
    {
        System.out.println("Hello, " + name + ",\nYour Roll No is "+roll_no + "\nYou got "+ marks + "% marks in this semester");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Hello, World");
    }
}
