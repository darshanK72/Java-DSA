/**
 * Topic: StringBuilder in Java
 * - The StringBuilder class is used to create mutable (modifiable) strings.
 * - Unlike the String class, which creates immutable strings, StringBuilder allows modifying the string without creating new objects.
 * - It provides methods to append, insert, delete, and modify the contents of a string.
 * - StringBuilder is more efficient than using regular String concatenation in loops because it doesn't create new objects each time.
 */

import java.util.Scanner;

public class j14StringsBuilder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Making StringBuilder Object string
        StringBuilder s = new StringBuilder("Hello,World");
        StringBuilder s1 = new StringBuilder(); // Empty String

        // Iterating through string using charAt
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i)); // Printing each character of the StringBuilder object
        }

        // Printing StringBuilder object
        System.out.println(s); // Output: Hello,World

        // String comparison using compareTo and equals
        StringBuilder s2 = new StringBuilder("Hello,World");

        System.out.println(s1.compareTo(s2)); // Compares based on lexicographic order (negative, zero, or positive)
        System.out.println(s.compareTo(s2)); // Compares two StringBuilder objects
        System.out.println(s.equals(s2)); // Checks if both StringBuilder objects are equal (by reference)

        // Appending to StringBuilder
        s1.append("Hello,World"); // Appends text to the StringBuilder
        System.out.println(s1); // Output: Hello,World

        s.append(" Thank You!"); // Appends text to the StringBuilder
        System.out.println(s); // Output: Hello,World Thank You!

        // Deleting from StringBuilder
        s.deleteCharAt(3); // Deletes the character at index 3
        System.out.println(s); // Output: HelloWorld Thank You!

        s.delete(3, 11); // Deletes characters between index 3 and 11
        System.out.println(s); // Output: Hello Thank You!

        // Searching in StringBuilder
        System.out.println(s.indexOf("You")); // Finds the index of the first occurrence of "You"
        System.out.println(s.indexOf("ank", 21)); // Finds "ank" starting from index 21 (returns -1 if not found)

        // Replacing and Inserting in StringBuilder
        s.replace(1, 10, "Darshanzaz"); // Replaces characters between index 1 and 10 with "Darshanzaz"
        System.out.println(s); // Output: DarszazThank You!

        s.insert(4, "Helloooo"); // Inserts "Helloooo" at index 4
        System.out.println(s); // Output: DarHelloooozazThank You!

        // Substring from StringBuilder
        System.out.println(s.substring(3)); // Prints substring from index 3 to the end
        System.out.println(s.substring(5, 18)); // Prints substring between index 5 and 18

        // Stripping spaces from string
        System.out.println("###2sdfsdfs  ".strip()); // Strips leading and trailing whitespace

        in.close();
    }
}
