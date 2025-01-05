/**
 * Topic: Understanding Primitive Data Types in Java
 * 
 * In Java, there are 8 primitive data types that serve as the building blocks for data manipulation:
 * 
 * 1. boolean: Represents a true or false value. 
 * 2. byte: Represents an 8-bit signed integer with a range of -128 to 127.
 * 3. char: Represents a 16-bit Unicode character.
 * 4. short: Represents a 16-bit signed integer with a range of -32,768 to 32,767.
 * 5. int: Represents a 32-bit signed integer with a range of -2^31 to 2^31-1.
 * 6. float: Represents a 32-bit floating-point number, used for decimal values.
 * 7. double: Represents a 64-bit floating-point number with double precision.
 * 8. long: Represents a 64-bit signed integer with a very large range.
 * 
 * This program demonstrates how to declare and initialize variables of each primitive data type.
 * Additionally, it shows how to take user input using the Scanner class and store it into these variables.
 * The Scanner class is used to read data from the standard input (keyboard).
 * 
 * The following examples will print the entered values back to the user.
 * 
 * In this program:
 * - `nextByte()`, `nextChar()`, `nextShort()`, `nextInt()`, `nextFloat()`, `nextDouble()`, and `nextLong()` 
 *   are used to take respective inputs from the user.
 * - Error handling is necessary to ensure valid input is entered for each data type.
 * 
 * The program also covers the importance of the suffixes ('f' for float, 'l' for long) and their respective ranges.
 */

import java.util.Scanner;

public class j02PrimitiveDataTypes {
    public static void main(String[] args) {
        // Creating Scanner object for taking user input
        Scanner input = new Scanner(System.in);

        // Primitive Data Types Declaration and Initialization
        boolean flag = true; // Size: 1 bit | Values: true or false
        byte b = 4; // Size: 1 byte | Range: -128 to 127
        char c = 'a'; // Size: 2 bytes | Range: 0 to 65535 (Unicode characters)
        short s = 10; // Size: 2 bytes | Range: -32,768 to 32,767
        int i = 23423; // Size: 4 bytes | Range: -2^31 to 2^31-1
        float f = 234.23f; // Size: 4 bytes | Range: ±3.4e−038 to ±3.4e+038 (suffix 'f' required)
        double d = 2342342342.234223; // Size: 8 bytes | Range: ±1.7e−308 to ±1.7e+308
        long l = 23423534243345345l; // Size: 8 bytes | Range: -2^63 to 2^63-1 (suffix 'l' required)

        // Printing boolean value
        System.out.println("Flag : " + flag);

        // Taking byte input
        System.out.print("Enter Byte : ");
        b = input.nextByte(); // Throws InputMismatchException if input is out of range
        System.out.println("You Entered : " + b);

        // Taking character input
        System.out.print("Enter Character : ");
        c = input.next().charAt(0); // Takes first character of input string
        System.out.println("You Entered : " + c);

        // Taking short input
        System.out.print("Enter Short : ");
        s = input.nextShort(); // Throws InputMismatchException if input is out of range
        System.out.println("You Entered : " + s);

        // Taking integer input
        System.out.print("Enter Integer : ");
        i = input.nextInt(); // Throws InputMismatchException if input is out of range
        System.out.println("You Entered : " + i);

        // Taking float input
        System.out.print("Enter Float : ");
        f = input.nextFloat(); // Can accept numbers with or without decimal points
        System.out.println("You Entered : " + f);

        // Taking double input
        System.out.print("Enter Double : ");
        d = input.nextDouble(); // Provides more precision than float
        System.out.println("You Entered : " + d);

        // Taking long input
        System.out.print("Enter Long : ");
        l = input.nextLong(); // For very large integer values
        System.out.println("You Entered : " + l);

        // Closing scanner to prevent resource leak
        input.close();
    }
}
