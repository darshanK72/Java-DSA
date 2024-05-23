public class j22StaticMethod {
    public static void main(String args[])
    {
        Teacher t1 = new Teacher(201,21,"Darshan");
        Teacher t2 = new Teacher(202,23,"Aakash");

        System.out.println(Teacher.get_count_of_teachers());

        System.out.println(t1.teacher_name);
        System.out.println(t2.teacher_name);
    }
    
}

class Teacher
{
    static int count_of_teachers;
    int teacher_id;
    int teacher_age;
    String teacher_name;

    Teacher(int id,int age,String name)
    {
        this.teacher_id = id;
        this.teacher_age = age;
        this.teacher_name = name;
        Teacher.count_of_teachers += 1;
    }

    static int get_count_of_teachers()
    {
        // System.out.println(this.teacher_id); cannot use non static variables in static methods, they can only access static variables
        return Teacher.count_of_teachers;

    }
}
