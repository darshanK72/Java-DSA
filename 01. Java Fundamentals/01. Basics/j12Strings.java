/**
 * Topic: String Manipulation in Java
 * - Strings in Java are objects, not primitive types, and are immutable.
 * - Java provides a rich set of methods to manipulate strings.
 * - Common operations include:
 *   1. Concatenation (`+` operator or `concat()` method).
 *   2. Comparing strings (`compareTo()`, `equals()`, `equalsIgnoreCase()`).
 *   3. Extracting substrings (`substring()` method).
 *   4. Character access (`charAt()`).
 *   5. Modifying case (`toUpperCase()`, `toLowerCase()`).
 *   6. Replacing characters (`replace()`).
 *   7. Finding the position of a character (`indexOf()`, `lastIndexOf()`).
 *   8. Checking string length (`length()`).
 *   - In this example, we demonstrate some of the most commonly used string operations.
 */

import java.util.Scanner;

public class j12Strings {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		// Declaring and initializing strings
		String s1 = "Darshan"; // Direct initialization
		String s2 = "Khairnar";
		String s3 = "Abhishek Khairnar";

		// Concatenation of two strings using '+' operator
		String s4 = s1 + " " + s2; // Concatenates "Darshan" and "Khairnar" with a space in between
		System.out.println(s4); // Output: Darshan Khairnar

		// compareTo method compares two strings lexicographically
		// It returns 0 if the strings are equal, a positive value if the calling string
		// is lexicographically greater, and a negative value if it's smaller.
		System.out.println(s1.compareTo(s3)); // Output will be a negative number as "Darshan" is lexicographically
												// smaller than "Abhishek Khairnar"

		// == operator checks if both references point to the same memory location
		// (string pool)
		System.out.println(s1 == s2); // Output: false because the references are different objects in memory

		// substring method extracts a portion of the string from the specified indices
		// Substring from index 2 to 10 (index 10 is not included)
		System.out.println(s3.substring(2, 10)); // Output: hishek K

		// charAt method returns the character at the specified index
		System.out.println(s1.charAt(0)); // Output: D (first character of "Darshan")

		// Length of the string
		String str1 = "my name is Darshan Khairnar"; // A longer string for demonstration
		System.out.println(str1.length()); // Output: 27, length of the string

		// Character at a particular index
		System.out.println(str1.charAt(3)); // Output: n (character at index 3)

		// compareTo compares strings lexicographically
		// It compares each character until a difference is found
		// if greater, returns a positive number, if less, returns a negative number,
		// and if equal, returns 0
		System.out.println(str1.compareTo("ny name is Darshan Khairnar")); // Output: positive number

		// compareToIgnoreCase compares strings ignoring the case
		System.out.println(str1.compareToIgnoreCase("MY NAME IS DARSHAN KHAIRNAR")); // Output: 0 (case doesn't matter)

		// Checking equality using == operator
		System.out.println("hello" == "hello"); // Output: true, same memory location in string pool
		System.out.println("hii" == new String("hii")); // Output: false, different objects in memory

		/*
		 * == checks if two strings are referencing the same object (same memory
		 * location).
		 * It will only be true if both references are pointing to the same object in
		 * the string pool.
		 */

		// Checking equality using equals method
		// equals() compares two strings lexicographically
		System.out.println(str1.equals("my name is Darshan Khairnar")); // Output: true (strings are identical)
		System.out.println(str1.equalsIgnoreCase("MY NAME IS DARSHAN KHAIRNAR")); // Output: true (case-insensitive
																					// comparison)

		// Concatenating strings using concat method
		System.out.println(str1.concat(" Hello, World")); // Output: my name is Darshan Khairnar Hello, World

		// Converting to uppercase and lowercase
		System.out.println("abcd".toUpperCase()); // Output: ABCD
		System.out.println("ABCD".toLowerCase()); // Output: abcd

		// Finding index of a character or substring
		System.out.println(str1.indexOf("Darshan")); // Output: 11 (index where "Darshan" starts)
		System.out.println("abcdefffxyzxzy".indexOf('f')); // Output: 5 (first occurrence of 'f')
		System.out.println("abcdefffxyzxzy".lastIndexOf('f')); // Output: 7 (last occurrence of 'f')

		// Substring method extracts a part of the string from a given index or range
		System.out.println(str1.substring(8)); // Output: Darshan Khairnar (from index 8 to the end)
		System.out.println(str1.substring(3, 11)); // Output: name is (substring from index 3 to 11)

		// Replace method replaces a substring or character with another
		System.out.println(str1.replace("Darshan", "Abhishek")); // Output: my name is Abhishek Khairnar

		// Closing the scanner object to prevent resource leak
		in.close();
	}
}
