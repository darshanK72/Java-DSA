/*
 * Topic: Random String Generation in Java
 * This program demonstrates various ways to generate random strings
 * using the Random class and character manipulation
 */

import java.util.Random;

public class j02RandomString {
    public static void main(String args[]) {
        Random rd = new Random();

        // Generate random lowercase letters
        System.out.println("Random lowercase letters:");
        for (int i = 0; i < 10; i++) {
            System.out.println((char)(97 + rd.nextInt(26))); // 97 is 'a' in ASCII
        }

        // Generate random uppercase letters
        System.out.println("\nRandom uppercase letters:");
        for (int i = 0; i < 10; i++) {
            System.out.println((char)(65 + rd.nextInt(26))); // 65 is 'A' in ASCII
        }

        // Generate random string of specified length
        System.out.println("\nRandom string (10 characters):");
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // Mix of uppercase and lowercase letters
            boolean isUpperCase = rd.nextBoolean();
            if (isUpperCase) {
                randomString.append((char)(65 + rd.nextInt(26)));
            } else {
                randomString.append((char)(97 + rd.nextInt(26)));
            }
        }
        System.out.println(randomString.toString());

        // Generate random alphanumeric string
        System.out.println("\nRandom alphanumeric string (15 characters):");
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomAlphanumeric = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            randomAlphanumeric.append(alphanumeric.charAt(rd.nextInt(alphanumeric.length())));
        }
        System.out.println(randomAlphanumeric.toString());
    }
}
