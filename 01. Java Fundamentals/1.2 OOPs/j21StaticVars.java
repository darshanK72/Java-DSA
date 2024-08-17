public class j21StaticVars {
    public static void main(String args[])
    {
        Employee e1 = new Employee("Darshan", 1, 25);
        Employee e2 = new Employee("Abhishek", 2, 21);

        System.out.println(Employee.count_of_employee);
        //System.out.println(e1.count_of_employee);
        //System.out.println(e2.count_of_employee);

        System.out.println(e1.name + " " + e1.id + " " + e1.age + " ");
        System.out.println(e2.name + " " + e2.id + " " + e2.age + " ");

    }
    
}

class Employee
{
    static int count_of_employee; // Static members of class can be accessed using Class name as well as object names
    String name;
    int id;
    int age;

    Employee(String name,int id,int age)
    {
        this.name = name;
        this.id = id;
        this.age = age;
        count_of_employee += 1;
    }
}
