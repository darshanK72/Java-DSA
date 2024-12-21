/*-
 * Topic: Literals in Java
 * - Literals are fixed values that are directly used in Java programs.
 * - Java supports different types of literals: integer literals, floating-point literals, boolean literals, character literals, and string literals.
 * - Integer literals can be in different bases (binary, decimal, hexadecimal, octal).
 * - String literals represent sequences of characters enclosed in double quotes.
 */

public class j16Literals {

	public static void main(String[] args) {

		// Integer Literals
		// Binary Literal (starts with 0b or 0B)
		int binary = 0b10101; // 21 in binary
		System.out.println(binary); // Output: 21

		// Decimal Literal (normal integer values)
		int decimal = 2343;
		System.out.println(decimal); // Output: 2343

		// Hexadecimal Literal (starts with 0x or 0X)
		int hexadecimal = 0x234324; // Represents hexadecimal value
		System.out.println(hexadecimal); // Output: 2280196

		// Octal Literal (starts with 0)
		int octal = 0437; // 437 in octal
		System.out.println(octal); // Output: 287

		// Boolean Literals
		boolean b = true;
		boolean b1 = false;

		System.out.println(b + " " + b1); // Output: true false

		// String Literals
		char c = '1'; // Character literal
		String s = "Hello, World"; // String literal

		System.out.println(c + "\n" + s); // Output: 1 \n Hello, World
	}
}
