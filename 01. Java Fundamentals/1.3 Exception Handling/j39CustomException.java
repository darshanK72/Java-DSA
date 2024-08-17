import java.util.Scanner;

public class j39CustomException {
    
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Operator : ");
        char op = in.next().charAt(0);

        System.out.print("Enter Two Numbers : ");
        int a = in.nextInt();
        int b = in.nextInt();

        try
        {
            switch(op)
            {
                case '+':
                    System.out.printf("a + b = %d\n",a + b);
                    break;
                
                case '-':
                    System.out.printf("a - b = %d\n",a - b);
                    break;
                
                case '*':
                    System.out.printf("a * b = %d\n",a * b);
                    break;
        
                case '/':
                    System.out.printf("a / b = %d\n",a / b);
                    break;
                
                default:
                    throw new InvalidOperatorException("Incorect Operator");
        
            }
        }
        catch(InvalidOperatorException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch(ArithmeticException ex)
        {
            System.out.print(ex.getMessage());
        }
        finally
        {
            in.close();
        }
    }
}

class InvalidOperatorException extends Exception{

    InvalidOperatorException(String message)
    {
        super(message);
    }

    // public String getMessage()
    // {
    //     return "You Entered Incorrect Operator";
    // }
}
