public class j05StaticBlock {
    public static void main(String args[])
    {
        Human h1 = new Human(21,"Darshan");

        System.out.println(Human.population);

        Human h2 = new Human(20, "Aakash");

        System.out.println(Human.population);

        System.out.println(h1.name);
        System.out.println(h2.name);
    }
    
}

class Human
{
    static int population;
    int age;
    String name;

    static{
        population = 200;
    } // Static block only execuated once when first object is created, it is use to initilized the values of static variables specially.

    Human(int age,String name)
    {
        this.age = age;
        this.name = name;
        Human.population += 1;
    }
}
