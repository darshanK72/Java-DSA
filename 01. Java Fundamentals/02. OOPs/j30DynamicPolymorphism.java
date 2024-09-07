public class j30DynamicPolymorphism {
    public static void main(String args[])
    {

        IntOperators io = new IntOperators();
        IntOperators fo = new FloatOperator();

        System.out.println(io.sum(10,20));
        System.out.println(fo.sum(20,92));
    }
}

/*final class cannot be Inheritence */ class IntOperators
{
    /*final methods cannot be overriden*/int sum(int a,int b)
    {
        System.out.print("Integer Sum : ");
        return a+b;
    }
}

class FloatOperator extends IntOperators
{
    // When we use same name for the method of child class as method of parent class, then it is known as overriding
    // Override keyword is used for checking is it overriding or not
    // for Overriding with it must have same signature
    @Override
    int sum(int a,int b)
    {
        System.out.print("Float Sum : ");
        return a + b;
    }

}
