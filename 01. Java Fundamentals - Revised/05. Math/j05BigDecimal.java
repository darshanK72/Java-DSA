/*
 * Topic: Java BigDecimal Class
 * This program demonstrates the usage of BigDecimal class for handling
 * high-precision decimal numbers and financial calculations
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class j05BigDecimal {
    public static void main(String args[]) {
        // Different ways to create BigDecimal objects
        BigDecimal num1 = BigDecimal.valueOf(1.3);                            // From double
        BigDecimal num2 = new BigDecimal("235345235523.234523");             // From String
        BigDecimal num3 = new BigDecimal("876238852839634.4823482384");      // High precision number
        
        System.out.println("Original Numbers:");
        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);
        System.out.println("num3: " + num3);

        // Basic arithmetic operations
        System.out.println("\nBasic Arithmetic:");
        System.out.println("Addition: " + num2.add(num3));
        System.out.println("Subtraction: " + num3.subtract(num2));
        System.out.println("Multiplication: " + num1.multiply(num2));
        
        // Division with rounding
        System.out.println("\nDivision with Different Rounding Modes:");
        BigDecimal dividend = new BigDecimal("10");
        BigDecimal divisor = new BigDecimal("3");
        System.out.println("10/3 (ROUND_HALF_UP): " + 
            dividend.divide(divisor, 4, RoundingMode.HALF_UP));
        System.out.println("10/3 (ROUND_DOWN): " + 
            dividend.divide(divisor, 4, RoundingMode.DOWN));
        System.out.println("10/3 (ROUND_CEILING): " + 
            dividend.divide(divisor, 4, RoundingMode.CEILING));

        // Scaling and precision
        System.out.println("\nScaling and Precision:");
        BigDecimal number = new BigDecimal("123.456789");
        System.out.println("Original: " + number);
        System.out.println("Scale 2: " + number.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Precision 4: " + number.round(new MathContext(4)));

        // Comparison operations
        System.out.println("\nComparisons:");
        BigDecimal comp1 = new BigDecimal("123.45");
        BigDecimal comp2 = new BigDecimal("123.450");
        System.out.println("123.45 equals 123.450: " + comp1.equals(comp2));
        System.out.println("123.45 compareTo 123.450: " + comp1.compareTo(comp2));
        System.out.println("Is 123.45 equal in value to 123.450: " + 
            (comp1.compareTo(comp2) == 0));

        // Special operations
        System.out.println("\nSpecial Operations:");
        System.out.println("Absolute value: " + number.abs());
        System.out.println("Move point left 2: " + number.movePointLeft(2));
        System.out.println("Move point right 2: " + number.movePointRight(2));
        System.out.println("Strip trailing zeros: " + 
            new BigDecimal("123.4500").stripTrailingZeros());
    }
}
