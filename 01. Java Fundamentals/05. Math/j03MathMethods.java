/*
 * Topic: Java Math Class Methods
 * This program demonstrates various mathematical operations and functions
 * available in Java's Math class
 */

import java.lang.Math;

public class j03MathMethods {
    public static void main(String args[]) {
        // Basic Math operations
        System.out.println("Basic Math Operations:");
        System.out.println("Absolute value of -23: " + Math.abs(-23));
        System.out.println("Power of 10.20^43: " + Math.pow(10.20, 43));
        System.out.println("Square root of 2352: " + Math.sqrt(2352));

        // Rounding operations
        System.out.println("\nRounding Operations:");
        double number = 25.52;
        System.out.println("Round 23.52: " + Math.round(23.52));
        System.out.println("Floor " + number + ": " + Math.floor(number));
        System.out.println("Ceil " + number + ": " + Math.ceil(number));

        // Min/Max operations
        System.out.println("\nMin/Max Operations:");
        System.out.println("Minimum of 52 and 73: " + Math.min(52, 73));
        System.out.println("Maximum of 634 and 632: " + Math.max(634, 632));

        // Logarithmic operations
        System.out.println("\nLogarithmic Operations:");
        System.out.println("Natural log of e: " + Math.log(Math.E));
        System.out.println("Log base 10 of 10: " + Math.log10(10));

        // Trigonometric operations
        System.out.println("\nTrigonometric Operations:");
        double angle = Math.PI / 4; // 45 degrees
        System.out.println("Sine of 45°: " + Math.sin(angle));
        System.out.println("Cosine of 45°: " + Math.cos(angle));
        System.out.println("Tangent of 45°: " + Math.tan(angle));

        // Constants in Math class
        System.out.println("\nMath Constants:");
        System.out.println("Value of PI: " + Math.PI);
        System.out.println("Value of E: " + Math.E);

        // Additional useful methods
        System.out.println("\nAdditional Operations:");
        System.out.println("Hypotenuse of 3 and 4: " + Math.hypot(3, 4));
        System.out.println("2³: " + Math.pow(2, 3));
        System.out.println("Random number between 0 and 1: " + Math.random());
        System.out.println("Sign of -5: " + Math.signum(-5));
    }
}