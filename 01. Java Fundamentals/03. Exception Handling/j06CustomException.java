/**
 * Demonstration of Custom Exception Handling in Java
 * This program implements a simple calculator with custom exception handling
 * for invalid operators
 */

import java.util.Scanner;

public class j06CustomException {
    
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
                    // Throwing custom exception for invalid operator
                    throw new InvalidOperatorException("Invalid operator. Please use +, -, *, or /");
        
            }
        }
        catch(InvalidOperatorException ex)
        {
            System.out.println("Custom Exception: " + ex.getMessage());
        }
        catch(ArithmeticException ex)
        {
            System.out.println("Math Error: " + ex.getMessage());
        }
        catch(Exception ex)
        {
            // Generic exception handler
            System.out.println("Unexpected error: " + ex.getMessage());
        }
        finally
        {
            in.close();
        }
    }
}

/**
 * Custom Exception class for handling invalid operator inputs
 */
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
