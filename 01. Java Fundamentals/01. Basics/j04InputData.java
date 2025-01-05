/**
 * This class demonstrates different ways to take input in Java
 * using various Scanner methods for different data types
 */

import java.util.Scanner;

public class j04InputData {
    public static void main(String args[]) {
        // Create Scanner object to read input from standard input (keyboard)
        Scanner in = new Scanner(System.in);

        // Reading boolean input (true/false)
        System.out.print("Enter a boolean value (true/false): ");
        boolean b = in.nextBoolean(); // Accepts "true" or "false" (case-insensitive)
        System.out.println("Boolean: " + b);

        // Reading byte input
        System.out.print("Enter a byte (-128 to 127): ");
        byte bb = in.nextByte(); // Throws exception if input is out of range
        System.out.println("Byte: " + bb);

        // Reading character input
        System.out.print("Enter a character: ");
        char c = in.next().charAt(0); // Takes first character of input string
        System.out.println("Character: " + c);

        // Reading integer input
        System.out.print("Enter an integer: ");
        int integer = in.nextInt(); // Reads a 32-bit integer
        System.out.println("Integer: " + integer);

        // Reading long input
        System.out.print("Enter a long number: ");
        long lng = in.nextLong(); // Reads a 64-bit integer
        System.out.println("Long: " + lng);

        // Reading float input
        System.out.print("Enter a float number: ");
        float f = in.nextFloat(); // Reads decimal number (32-bit)
        System.out.println("Float: " + f);

        // Reading double input
        System.out.print("Enter a double number: ");
        double d = in.nextDouble(); // Reads decimal number (64-bit)
        System.out.println("Double: " + d);

        // Important: nextLine() to handle the leftover newline character
        in.nextLine(); // Consumes the leftover newline character

        // Reading string input
        System.out.print("Enter a string: ");
        String str = in.nextLine(); // Reads entire line including spaces
        System.out.println("String: " + str);

        // Close scanner to prevent resource leak
        in.close();
    }
}