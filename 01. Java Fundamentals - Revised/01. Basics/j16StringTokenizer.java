/**
 * Topic: StringTokenizer in Java
 * - The StringTokenizer class is used to split a string into tokens based on specified delimiters.
 * - It provides a simple way to break a string into smaller pieces (tokens).
 * - By default, the delimiters are space, tab, newline, and carriage return.
 * - We can also specify custom delimiters.
 * - StringTokenizer is often used when parsing data or breaking down a string into components.
 */

import java.util.StringTokenizer;

public class j16StringTokenizer {

	public static void main(String[] args) {
		// Creating a StringTokenizer object to tokenize the sentence
		StringTokenizer str = new StringTokenizer("hi this is new sentence");

		// Iterating through each token and printing it
		for (int i = 0; str.hasMoreTokens(); i++) {
			// Printing each token along with its index
			System.out.println("Token " + i + " : " + str.nextToken());
		}
	}
}
