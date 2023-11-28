import java.util.Scanner;

public class j8ConditionalStatements {
    public static void main(String args[])
    {
        //if else statements
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();

        if(a == 20)
        {
            System.out.println("You Entered Twenty");
        }
        else
        {
            System.out.println("You Entered Other Number, Not Twenty");
        }

        System.out.println("\nCalculator programm");
        int x = in.nextInt();
        int y = in.nextInt();

        char c = in.next().charAt(0);

        switch(c)
        {
            case '+':
            System.out.println(x+y);
            break;

            case '-':
            System.out.println(x-y);
            break;

            case '*':
            System.out.println(x*y);
            break;

            case '/':
            System.out.println(x/y);
            break;

            default:
            System.out.println("You entered wrong operator");
            break;

        }

        in.close();
        
    }
    
}
