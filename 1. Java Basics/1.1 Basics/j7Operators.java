public class j7Operators {

    public static void main(String[] args)
    {
        // Assignment Operators
        int a = 20;
        int b = 30;
        //String s = "Hello, World";
        // float f = 23.22f;
        // double d = 234.234234;
        // long l = 23423432423l;


        // Arithmetic Operators
        int sum = a + b;
        int sub = b - a;
        int mul = a*b;
        int div = b/10;
        int modulo = 21%2;

        System.out.println("a + b = "+sum);
        System.out.println("b - a = " + sub);
        System.out.println("a * b = " + mul);
        System.out.println("b / 10 = " + div);
        System.out.println("21 % 2 = "+ modulo);

        // Compound Assignment
        a += 10;
        b -= 30;
        //d *= 10;

        // Unary Operators
        sum = -sum;
        System.out.println(sum);
        b++;
        System.out.println(b);
        ++b;
        System.out.println(b);
        b--;
        System.out.println(b);
        --b;
        System.out.println(b);


        // Relational Opeators

        System.out.println("10 == 20 = " + (10 == 20));
        System.out.println("a != b = " + (a != b));
        System.out.println("10 != 30 = " + (10 != 30));
        System.out.println("10 > 5 = " + (10 > 5));
        System.out.println("10 < 5 = " + (10 < 5));
        System.out.println("10 <= 50 = " + (10 <= 50));
        System.out.println("10 >= 59 = " + (10 >= 59));

        // Logical Operators

        if((10 < 50) && (11 >= 5))
        {
            System.out.println("And (&&) Operator");
        }

        if((11 < 10) || true)
        {
            System.out.println("Or (||) Operator");
        }

        if(!false)
        {
            System.out.println("Not (!) Operator");
        }

        // Bitwise Operators

        System.out.println("10 & 2 = " + (10 & 2));
        System.out.println("10 ! 0 = " + (10 | 0));
        System.out.println("3 ^ 4 = " + (3 ^ 4));
        System.out.println("16>>1 = " + (16>>1));
        System.out.println("10<<2 = " + (16<<2));


        // Ternery Operator

        int result = (a < b) ? b : a;

        System.out.println("Max among a and b is : " + result);

        


    }
    
}
