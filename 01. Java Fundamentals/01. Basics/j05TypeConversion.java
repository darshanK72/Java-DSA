
/**
 * Topic: Type Conversion (Casting) in Java
 * 
 * In this class, we demonstrate both **implicit** (automatic) and **explicit** (manual) type conversions.
 * Type conversion is the process of converting one data type to another.
 * 
 * 1. **Implicit (Automatic) Type Conversion**:
 *    - Java performs this automatically when a smaller data type is assigned to a larger one. 
 *      For example, assigning an `int` value to a `long` or a `byte` to an `int`.
 *    - No data loss occurs when widening the type.
 * 
 * 2. **Explicit (Manual) Type Conversion**:
 *    - Also known as **casting**, explicit conversion is required when narrowing the type, i.e., converting a larger data type to a smaller one. 
 *      For example, converting a `double` to a `float`, or an `int` to a `byte`.
 *    - Data loss can occur if the value exceeds the range of the target type.
 * 
 * This program covers the conversion between:
 * - `boolean` (no conversion to/from other types)
 * - `byte`, `char`, `short`, `int`, `float`, and `double`
 * - Demonstrates narrowing and widening conversions, such as converting `int` to `byte`, `double` to `int`, etc.
 * 
 * The Scanner class is used to take input from the user for each of the data types.
 * 
 * Note: Some conversions may cause data loss, especially when narrowing from larger to smaller types.
 */

import java.util.Scanner;

public class j05TypeConversion {
    public static void main(String[] args) {
        // Creating Scanner object for taking user input
        Scanner input = new Scanner(System.in);

        // Boolean - cannot be converted to/from other types
        boolean bn = true;
        System.out.println("Boolean value: " + bn);

        // Byte input and conversion
        System.out.print("Enter a byte value: ");
        byte b = input.nextByte(); // Range: -128 to 127
        System.out.println("Byte value: " + b);

        // Character input and conversion
        System.out.print("Enter a character: ");
        char c = input.next().charAt(0); // Takes first character
        System.out.println("Character value: " + c);

        // Short input
        // Note: Cannot implicitly convert larger types (int, long, float, double) to
        // short
        System.out.print("Enter a short value: ");
        short s = input.nextShort(); // Range: -32,768 to 32,767
        System.out.println("Short value: " + s);

        // Integer conversions
        System.out.print("Enter an integer: ");
        int i = input.nextInt();
        System.out.println("\nExplicit conversion of Integer to other types:");
        System.out.println("To float: " + (float) i); // Widening - no data loss
        System.out.println("To char: " + (char) i); // May lose data if i > 65535
        System.out.println("To short: " + (short) i); // May lose data if i > 32767
        System.out.println("To byte: " + (byte) i); // May lose data if i > 127

        // Float conversions
        System.out.print("\nEnter a float value: ");
        float f = input.nextFloat();
        System.out.println("\nExplicit conversion of Float to other types:");
        System.out.println("Original float: " + f);
        System.out.println("To int: " + (int) f); // Truncates decimal part
        System.out.println("To char: " + (char) f); // Truncates decimal and converts to Unicode
        System.out.println("To byte: " + (byte) f); // May lose data
        System.out.println("To short: " + (short) f); // May lose data

        // Double conversions
        System.out.print("\nEnter a double value: ");
        double d = input.nextDouble();
        System.out.println("\nExplicit conversion of Double to other types:");
        System.out.println("Original double: " + d);
        System.out.println("To int: " + (int) d); // Truncates decimal part
        System.out.println("To char: " + (char) d); // Truncates decimal and converts to Unicode
        System.out.println("To byte: " + (byte) d); // May lose data
        System.out.println("To short: " + (short) d); // May lose data

        // Example of expression requiring explicit cast
        short x = 20;
        // short y = x * 20; // Error: int cannot be converted to short
        short y = (short) (x * 20); // Explicit cast needed as multiplication produces int
        System.out.println("\nResult of short calculation: y = " + y);

        input.close(); // Close the scanner to prevent resource leak
    }
}
