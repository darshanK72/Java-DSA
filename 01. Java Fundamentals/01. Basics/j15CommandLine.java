/*-
 * Topic: Command Line Arguments in Java
 * - Command-line arguments are passed to the Java program when it is executed.
 * - These arguments are received in the `args` array in the `main` method of the Java program.
 * - The `args` array stores the arguments as `String` values.
 * - Command-line arguments are typically used to allow the user to pass input to a program at runtime.
 */

public class j15CommandLine {

	public static void main(String[] args) {
		// Iterating through the command-line arguments
		for (int i = 0; i < args.length; i++) {
			// Printing each command-line argument passed
			System.out.println(args[i]);
		}
	}
}
