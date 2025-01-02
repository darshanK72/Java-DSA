/**
 * Topic: Methods in Java
 * - A method is a block of code that performs a specific task. It allows code reuse, modularity, and makes programs more organized.
 * - In Java, methods are defined within a class and can have various types:
 *   1. **Return Type**: Methods can return values or not return anything (`void`).
 *   2. **Parameters**: Methods can accept input in the form of parameters, allowing them to be reused with different inputs.
 *   3. **Method Overloading**: You can have methods with the same name but different parameters (number/type).
 *   4. **Call by Value**: Java uses pass-by-value for primitive types, meaning the method works on a copy of the data.
 *   5. **Call by Reference**: For objects (like arrays), Java passes the reference, allowing the method to modify the original data.
 *   6. **Varargs**: Java allows methods to accept variable-length arguments (varargs).
 *
 * In this example, we explore:
 * - Basic method usage
 * - Method overloading
 * - Call by value and call by reference
 * - Variable-length arguments (varargs)
 */

import java.util.Scanner; // Importing Scanner class for taking user input

public class j09Methods {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Creating a Scanner object to take user input

        // Reading two integers from the user
        int a = input.nextInt();
        int b = input.nextInt();

        // Calling sum() method to calculate sum of two integers and printing the result
        int result = sum(a, b);
        System.out.printf("%d + %d = %d\n", a, b, result);

        // Calling methods to print greetings
        greating();
        System.out.println(greating2());

        // Taking name input from user and calling great_me() to greet the user by their
        // name
        String my_name = input.next();
        great_me(my_name);

        // Call by value demonstration: swap values of a and b
        System.out.printf("Before Swap: a = %d, b = %d\n", a, b);
        swap(a, b);
        // Values of a and b remain unchanged because of call by value
        System.out.printf("After Swap: a = %d, b = %d\n", a, b);

        // Call by reference demonstration with an array (mutable object)
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        System.out.println("Before Change: \n\tarr[0] = " + arr[0]);
        change(arr); // Modifies the array because arrays are passed by reference
        System.out.println("After Change: \n\tarr[0] = " + arr[0]); // arr[0] is modified

        // Variable-length arguments demonstration
        var_len(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); // Passing 10 integers as arguments
        var_mul_len(1, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11); // Passing integers with a range

        // Demonstrating overloaded add() method with different parameter counts and
        // types
        System.out.println(add(234, 534)); // Adds two integers
        System.out.println(add(234, 534, 324)); // Adds three integers
        System.out.println(add(1, 2, 3, 4, 5, 6, 7, 3, 4, 5)); // Adds many integers
        System.out.println(add("Hello Mr ", "Darshan")); // Concatenates two strings

        input.close(); // Closing the scanner to prevent resource leak
    }

    // A simple method to return the sum of two integers
    public static int sum(int a, int b) {
        return a + b;
    }

    // Method that prints a greeting message
    public static void greating() {
        System.out.println("Hello World!");
    }

    // Method that returns a greeting message as a string
    public static String greating2() {
        return "Hello!, How are you";
    }

    // Method to greet the user with their name
    static void great_me(String name) {
        System.out.println("Hello Mr " + name);
    }

    // Demonstrates call by value: values of num1 and num2 are not swapped in main()
    // method
    static void swap(int num1, int num2) {
        int temp = num1; // num1 and num2 are local variables
        num1 = num2;
        num2 = temp;
        System.out.printf("Inside Function: a = %d, b = %d\n", num1, num2); // Local swap
    }

    // Demonstrates call by reference: modifies the original array passed to it
    static void change(int[] a) {
        a[0] = 3000; // Modifying the array directly
    }

    // Method that accepts variable-length arguments and prints them
    static void var_len(int... v) {
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i] + " ");
        }
        System.out.println();
    }

    // Method that accepts two fixed arguments and a variable-length argument
    static void var_mul_len(int a, int b, int... v) {
        for (int i = a; i <= b; i++) {
            System.out.print(v[i] + " ");
        }
        System.out.println();
    }

    // Overloaded add() method to handle two integers
    static int add(int a, int b) {
        return a + b;
    }

    // Overloaded add() method to handle three integers
    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded add() method to handle variable-length integers
    static int add(int... v) {
        int sum = 0;
        for (int ele : v) {
            sum += ele; // Summing up all the integers
        }
        return sum;
    }

    // Overloaded add() method to concatenate two strings
    static String add(String s1, String s2) {
        return s1 + s2; // Concatenating two strings
    }
}
