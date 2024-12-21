/*-
 * Topic: Wrapper Classes in Java
 * - Wrapper classes are used to convert primitive types into objects.
 * - Each primitive type (like `int`, `char`, `boolean`) has a corresponding wrapper class.
 * - The wrapper classes are part of the `java.lang` package, and they are: 
 *   1. `Boolean` for `boolean`
 *   2. `Byte` for `byte`
 *   3. `Character` for `char`
 *   4. `Integer` for `int`
 *   5. `Long` for `long`
 *   6. `Float` for `float`
 *   7. `Double` for `double`
 *   8. `Short` for `short`
 */

import java.util.Scanner;

public class j13WraperClasses {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Creating Wrapper class objects for various primitive data types
        Boolean bb = false; // Wrapper class for boolean
        Byte b = 127; // Wrapper class for byte
        Character c = '2'; // Wrapper class for char
        Integer i = 20234; // Wrapper class for int
        Long l = 2342523534546l; // Wrapper class for long
        Float f = 234.534534f; // Wrapper class for float
        Double d = 235345234.334; // Wrapper class for double
        String s = "Hello, World"; // A regular string variable

        // Printing the values stored in Wrapper class objects
        System.out.println(bb); // Output: false
        System.out.println(b); // Output: 127
        System.out.println(c); // Output: 2
        System.out.println(i); // Output: 20234
        System.out.println(l); // Output: 2342523534546
        System.out.println(f); // Output: 234.53454
        System.out.println(d); // Output: 2.35345234334E11
        System.out.println(s); // Output: Hello, World

        // Wrapper classes provide utility methods like parse methods
        // Example: Parsing string to integer
        String numString = "100";
        int parsedInt = Integer.parseInt(numString); // Converts the string "100" to the primitive int 100
        System.out.println(parsedInt); // Output: 100

        // Example: Converting primitive type to Wrapper class object using valueOf
        // method
        int a = 500;
        Integer integerWrapper = Integer.valueOf(a); // Converts primitive int to Integer object
        System.out.println(integerWrapper); // Output: 500

        in.close();
    }
}
