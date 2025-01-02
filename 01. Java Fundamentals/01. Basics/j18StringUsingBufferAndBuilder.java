/**
 * Topic: StringBuffer and StringBuilder in Java
 * - Both StringBuffer and StringBuilder are mutable classes that allow modification of strings without creating new objects.
 * - StringBuffer is synchronized, meaning it is thread-safe, but it is slower than StringBuilder.
 * - StringBuilder is not synchronized, meaning it is faster but not thread-safe.
 * - Both classes provide methods to append, insert, delete, reverse, and modify strings.
 * - The StringBuffer and StringBuilder classes are used when frequent modifications to a string are required, providing better performance than using String concatenation in loops.
 */

public class j18StringUsingBufferAndBuilder {

	public static void main(String[] args) {
		/*
		 * StringBuffer is a mutable object, meaning we can change the content of
		 * StringBuffer.
		 */
		StringBuffer str1 = new StringBuffer("Hello, ");

		// Printing the initial content of StringBuffer
		System.out.println(str1);

		// Appending to string using append() method
		str1.append("my name is ");
		System.out.println(str1); // Output: Hello, my name is

		// Inserting text at a specific index using insert() method
		str1.insert(10, "Java Language");
		System.out.println(str1); // Output: Hello, my Java Languagename is

		// Inserting a space at the 10th index
		str1.insert(10, " ");
		System.out.println(str1); // Output: Hello, my Java Language name is

		// Deleting a portion of the string using delete() method
		str1.delete(10, 31);
		System.out.println(str1); // Output: Hello, my name is

		// Reversing the string using reverse() method
		str1.reverse();
		System.out.println(str1); // Output: si eman ym ,olleH

		// StringBuilder example
		StringBuilder str2 = new StringBuilder("new ");

		// Appending to StringBuilder
		str2.append("my name is ");
		System.out.println(str2); // Output: new my name is

		// Inserting text into StringBuilder
		str2.insert(10, "Java Language");
		System.out.println(str2); // Output: new my Java Language name is

		// Inserting a space in StringBuilder
		str2.insert(10, " ");
		System.out.println(str2); // Output: new my Java Language name is

		// Deleting a portion of the StringBuilder
		str2.delete(10, 31);
		System.out.println(str2); // Output: new my name is

		// Reversing the StringBuilder
		str2.reverse();
		System.out.println(str2); // Output: si eman ym wen
	}
}
