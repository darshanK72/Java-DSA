/*
 * Topic: Random Number Generation in Java
 * This program demonstrates various ways to generate random numbers
 * using the Random class from java.util package
 */

import java.util.Random;

public class j01Ramdom {

    public static void main(String args[]) {
        Random rd = new Random();

        // Generate numbers between 5 (inclusive) and 10 (exclusive)
        System.out.println("Numbers between 5 and 10:");
        for (int i = 0; i < 20; i++) {
            int randomNumber = rd.nextInt(5, 10);
            System.out.println("Random Number : " + randomNumber);
        }

        // Generate random integers with no bounds
        System.out.println("\nRandom integers (no bounds):");
        for (int i = 0; i < 5; i++) {
            System.out.println("Random Integer: " + rd.nextInt());
        }

        // Generate random doubles (between 0.0 and 1.0)
        System.out.println("\nRandom doubles between 0.0 and 1.0:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Random Double: " + rd.nextDouble());
        }

        // Generate random booleans
        System.out.println("\nRandom boolean values:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Random Boolean: " + rd.nextBoolean());
        }

        // Generate numbers within a specific range (0 to 100)
        System.out.println("\nNumbers between 0 and 100:");
        for (int i = 0; i < 5; i++) {
            int randomInRange = rd.nextInt(101); // 0 to 100 inclusive
            System.out.println("Random Number (0-100): " + randomInRange);
        }
    }
}
