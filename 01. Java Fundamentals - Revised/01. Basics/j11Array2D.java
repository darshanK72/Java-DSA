/**
 * Topic: 2D Arrays in Java
 * - A 2D array is an array of arrays, which can be used to represent matrices or grids.
 * - It is a more complex version of the basic array and can store data in rows and columns.
 * - Each element in the 2D array is accessed using two indices: one for the row and one for the column.
 * - In this example, we demonstrate:
 *   1. Declaring and initializing 2D arrays in various ways.
 *   2. Accessing and modifying elements in a 2D array.
 *   3. Printing a 2D array using different methods:
 *      - Regular for loop
 *      - Enhanced for loop (for-each)
 *      - Arrays.toString() for quick array display.
 */

import java.util.Arrays; // Importing Arrays class for converting arrays to a string representation
import java.util.Scanner; // Importing Scanner class for reading user input

public class j11Array2D {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner to read user input

        // Declaring a 2D array with 3 rows and 3 columns
        int[][] arr = new int[3][3];

        // Direct initialization of a 2D array
        // The inner arrays can have different lengths, making it a jagged array
        int[][] arr2 = {
                { 1, 2, 3, 4 },
                { 5, 6, 7 },
                { 8, 9, 10, 11 }
        };

        // Initializing the 2D array 'arr' using user input
        System.out.println("Enter 9 integers for a 3x3 array:");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = in.nextInt(); // Assigning user input to the 2D array
            }
        }

        // Printing the 2D array 'arr2' using a traditional for loop
        System.out.println("Printing arr2 using for loop:");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.print(arr2[i][j] + " "); // Printing each element followed by a space
            }
            System.out.println(); // Printing a newline after each row
        }

        // Printing the 2D array 'arr' using a for-each loop
        System.out.println("Printing arr using for-each loop:");
        for (int[] row : arr) { // Each 'row' is a 1D array (a row in the 2D array)
            for (int x : row) { // Each 'x' is an element in the row
                System.out.print(x + " ");
            }
            System.out.println(); // Printing a newline after each row
        }

        // Printing the 2D array 'arr' using Arrays.toString()
        System.out.println("Printing arr using Arrays.toString():");
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row)); // Arrays.toString() prints the array in a readable format
        }

        in.close(); // Closing the scanner to prevent resource leak
    }
}
