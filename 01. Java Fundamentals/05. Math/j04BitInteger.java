/*
 * Topic: Java BigInteger Class
 * This program demonstrates the usage of BigInteger class for handling
 * very large integer numbers that exceed the capacity of primitive data types
 */

import java.math.BigInteger;

public class j04BitInteger {
    public static void main(String args[]) {
        // Different ways to create BigInteger objects
        BigInteger num1 = BigInteger.valueOf(13);                                  // From long
        BigInteger num2 = new BigInteger("235345235234523");                      // From String
        BigInteger num3 = new BigInteger("8762388528394823482384");              // Very large number
        
        System.out.println("Original Numbers:");
        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);
        System.out.println("num3: " + num3);

        // Basic arithmetic operations
        System.out.println("\nBasic Arithmetic:");
        System.out.println("Multiplication (num1 * num2): " + num1.multiply(num2));
        System.out.println("Division (num2 / num1): " + num2.divide(num1));
        System.out.println("Addition (num2 + num3): " + num2.add(num3));
        System.out.println("Subtraction (num3 - num2): " + num3.subtract(num2));
        System.out.println("Modulus (num2 % num1): " + num2.mod(num1));

        // Power and absolute operations
        System.out.println("\nPower and Absolute:");
        BigInteger negative = new BigInteger("-123456789");
        System.out.println("Power (num1^3): " + num1.pow(3));
        System.out.println("Absolute value of -123456789: " + negative.abs());

        // Comparison operations
        System.out.println("\nComparisons:");
        System.out.println("num1 equals num2: " + num1.equals(num2));
        System.out.println("num2 compared to num1: " + num2.compareTo(num1));  // > 0 if num2 > num1

        // Bit operations
        System.out.println("\nBit Operations:");
        BigInteger bit1 = new BigInteger("60");  // 111100 in binary
        BigInteger bit2 = new BigInteger("13");  // 001101 in binary
        System.out.println("AND operation: " + bit1.and(bit2));
        System.out.println("OR operation: " + bit1.or(bit2));
        System.out.println("XOR operation: " + bit1.xor(bit2));

        // Other useful methods
        System.out.println("\nOther Operations:");
        System.out.println("GCD of 60 and 13: " + bit1.gcd(bit2));
        System.out.println("Next probable prime after num1: " + num1.nextProbablePrime());
        System.out.println("Bit length of num2: " + num2.bitLength());
        System.out.println("Is num1 probably prime? " + num1.isProbablePrime(1));
    }
}
