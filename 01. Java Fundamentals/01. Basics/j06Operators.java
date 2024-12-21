/**
 * Topic: Operators in Java
 * 
 * This program demonstrates the usage of various types of operators in Java:
 * 
 * 1. **Assignment Operators** (`=`)
 *    - Used to assign values to variables.
 *    - Example: `int a = 20;` assigns the value 20 to the variable `a`.
 *
 * 2. **Arithmetic Operators** (`+, -, *, /, %`)
 *    - Used to perform basic mathematical operations.
 *    - Example: Addition, subtraction, multiplication, division, and modulus.
 * 
 * 3. **Compound Assignment Operators** (`+=, -=, *=, /=, %=`)
 *    - A shorthand way to perform arithmetic operations and assignments in one step.
 *    - Example: `a += 10;` adds 10 to `a` and assigns the result to `a`.
 *
 * 4. **Unary Operators** (`-, ++, --`)
 *    - Used to perform operations on a single operand.
 *    - Includes negation, increment, and decrement.
 * 
 * 5. **Relational Operators** (`==, !=, >, <, >=, <=`)
 *    - Used to compare two values and return a boolean result.
 *    - Example: `a == b` checks if `a` is equal to `b`.
 *
 * 6. **Logical Operators** (`&&, ||, !`)
 *    - Used to combine multiple boolean expressions.
 *    - Example: `&&` (AND), `||` (OR), `!` (NOT).
 * 
 * 7. **Bitwise Operators** (`&, |, ^, >>, <<`)
 *    - Used to perform bit-level operations on integers.
 *    - Example: `&` (AND), `|` (OR), `^` (XOR), `>>` (Right shift), `<<` (Left shift).
 *
 * 8. **Ternary Operator** (`?:`)
 *    - A shorthand for `if-else` statement, evaluating a boolean expression.
 *    - Example: `condition ? trueResult : falseResult`.
 * 
 * The program provides examples for all of these operators and prints the results for the user to see.
 */

public class j06Operators {
    public static void main(String[] args) {
        // 1. Assignment Operators (=)
        // Basic variable initialization
        int a = 20; // Simple assignment
        int b = 30;

        // 2. Arithmetic Operators (+, -, *, /, %)
        int sum = a + b; // Addition
        int sub = b - a; // Subtraction
        int mul = a * b; // Multiplication
        int div = b / 10; // Division (integer division)
        int modulo = 21 % 2; // Modulus (remainder)

        System.out.println("\n=== Arithmetic Operators ===");
        System.out.println("a + b = " + sum); // 50
        System.out.println("b - a = " + sub); // 10
        System.out.println("a * b = " + mul); // 600
        System.out.println("b / 10 = " + div); // 3
        System.out.println("21 % 2 = " + modulo); // 1

        // 3. Compound Assignment Operators (+=, -=, *=, /=, %=)
        System.out.println("\n=== Compound Assignment ===");
        a += 10; // Same as: a = a + 10
        b -= 30; // Same as: b = b - 30

        // 4. Unary Operators (-, ++, --)
        System.out.println("\n=== Unary Operators ===");
        sum = -sum; // Negation
        System.out.println("Negation: " + sum);
        b++; // Post-increment
        System.out.println("Post-increment: " + b);
        ++b; // Pre-increment
        System.out.println("Pre-increment: " + b);
        b--; // Post-decrement
        System.out.println("Post-decrement: " + b);
        --b; // Pre-decrement
        System.out.println("Pre-decrement: " + b);

        // 5. Relational Operators (==, !=, >, <, >=, <=)
        System.out.println("\n=== Relational Operators ===");
        System.out.println("10 == 20 = " + (10 == 20)); // Equal to
        System.out.println("a != b = " + (a != b)); // Not equal to
        System.out.println("10 != 30 = " + (10 != 30)); // Not equal to
        System.out.println("10 > 5 = " + (10 > 5)); // Greater than
        System.out.println("10 < 5 = " + (10 < 5)); // Less than
        System.out.println("10 <= 50 = " + (10 <= 50)); // Less than or equal to
        System.out.println("10 >= 59 = " + (10 >= 59)); // Greater than or equal to

        // 6. Logical Operators (&&, ||, !)
        System.out.println("\n=== Logical Operators ===");
        if ((10 < 50) && (11 >= 5)) { // Logical AND
            System.out.println("And (&&) Operator - both conditions true");
        }

        if ((11 < 10) || true) { // Logical OR
            System.out.println("Or (||) Operator - at least one condition true");
        }

        if (!false) { // Logical NOT
            System.out.println("Not (!) Operator - inverts boolean value");
        }

        // 7. Bitwise Operators (&, |, ^, >>, <<)
        System.out.println("\n=== Bitwise Operators ===");
        System.out.println("10 & 2 = " + (10 & 2)); // Bitwise AND
        System.out.println("10 | 0 = " + (10 | 0)); // Bitwise OR
        System.out.println("3 ^ 4 = " + (3 ^ 4)); // Bitwise XOR
        System.out.println("16>>1 = " + (16 >> 1)); // Right shift
        System.out.println("16<<2 = " + (16 << 2)); // Left shift

        // 8. Ternary Operator (?:)
        System.out.println("\n=== Ternary Operator ===");
        int result = (a < b) ? b : a; // If a<b then b, else a
        System.out.println("Max among a and b is : " + result);
    }
}
