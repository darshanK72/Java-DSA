import java.util.Scanner;
public class j10Methods {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);


        int a = input.nextInt();
        int b = input.nextInt();

        int result = sum(a,b);
        System.out.printf("%d + %d = %d\n", a,b,result);

        greating();
        System.out.println(greating2());


        String my_name = input.next();
        great_me(my_name);



        // Call by value
        System.out.printf("Before Swap : a = %d, b = %d\n",a,b);
        swap(a,b);
        System.out.printf("After Swap : a = %d, b = %d\n",a,b);


        // Call by refference : in java call by refference works only for mutable objects like arrays

        int[] arr = {1,2,3,4,5,6};
        
        System.out.println("Befor Change : \n\tarr[0] = " + arr[0]);

        change(arr);

        System.out.println("After Change : \n\tarr[0] = " + arr[0]);


        // Variable Length Arguments


        var_len(1,2,3,4,5,6,7,8,9,10);
        var_mul_len(1,4,1,2,3,4,5,6,7,8,9,10,11);


        System.out.println(add(234,534));
        System.out.println(add(234,534,324));
        System.out.println(add(1,2,3,4,5,6,7,3,4,5));
        System.out.println(add("Hello Mr ","Darshan"));


        input.close();
    }

    public static int sum(int a,int b)
    {
        return a+b;
    }

    public static void greating()
    {
        System.out.println("Hello World!");
    }
    
    public static String greating2()
    {
        return "Hello!, How are you";
    }

    static void great_me(String name)
    {
        System.out.println("Hello Mr " + name);
    }

    static void swap(int num1,int num2)
    {
        int temp = num1; // num1,num2 are local variables
        num1 = num2;
        num2 = temp;
        System.out.printf("Inside Funciton : a = %d, b = %d\n",num1,num2);
    }

    static void change(int[] a)
    {
        a[0] = 3000;
    }

    static void var_len(int ...v)
    {
        for(int i = 0; i < v.length; i++)
        {
            System.out.print(v[i] + " ");
        }
    }

    static void var_mul_len(int a,int b,int ...v)
    {
        for(int i = a; i <= b; i++)
        {
            System.out.print(v[i] + " ");
        }
    }

    static int add(int a,int b)
    {
        return a+b;
    }

    static int add(int a,int b,int c)
    {
        return a+b+c;
    }

    static int add(int ...v)
    {
        int sum = 0;
        for(int ele:v)
        {
            sum += ele;
        }
        return sum;
    }

    static String add(String s1,String s2)
    {
        return s1+s2;
    }

}
