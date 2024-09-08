public class j10StaticPolymorphism {

    public static void main(String args[])
    {
        Operator a = new Operator();

        System.out.println(a.sum(10,20));
        System.out.println(a.sum(13.23f,536.4f));
        System.out.println(a.sum("Hello","Darshan"));
    }
    
}

class Operator
{
    // Using the same name of function in same class with different signature it is called as Method Overloading it is also known as compile time polymorphism
    int sum(int a,int b)
    {
        System.out.print("Integer Sum : ");
        return a+b;
    }

    float sum(float a,float b)
    {
        System.out.print("Float Sum : ");
        return a + b;
    }

    String sum(String s1,String s2)
    {
        System.out.print("String Sum : ");
        return s1 +" " + s2;
    }
}
