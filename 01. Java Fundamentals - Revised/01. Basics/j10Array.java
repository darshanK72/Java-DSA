/**
 * Topic: Arrays in Java
 * - An array in Java is a container object that holds a fixed number of values of a single type.
 * - Arrays are zero-indexed, meaning the first element is at index 0, the second at index 1, and so on.
 * - Arrays in Java are fixed in size, meaning once created, the size cannot be changed.
 * - In this example, we demonstrate:
 *   1. Declaring and initializing arrays.
 *   2. Accessing and modifying array elements.
 *   3. Using loops to manipulate and print array elements.
 *   4. Using `Arrays.toString()` method for easy display of arrays.
 * - Arrays can store primitive data types or references to objects.
 */

import java.util.Arrays; // Importing Arrays class for easy array operations like converting to string

public class j10Array {
    public static void main(String args[]) {
        // Declaring an array of integers with a size of 20
        int[] arr = new int[20];

        // Initializing the array with the square of index + 1 (i.e., 1^2, 2^2, 3^2,
        // ..., 20^2)
        for (int i = 0; i < 20; i++) {
            arr[i] = (i + 1) * (i + 1); // Storing the square of (i + 1) at index i
        }

        // Printing the elements of the array using a simple loop
        System.out.print("Array Elements: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(arr[i] + " "); // Printing each element separated by space
        }
        System.out.println(); // Moving to the next line after printing the array elements

        // Using Arrays.toString() to print the array in a more readable format
        System.out.println("Array printed using Arrays.toString(): " + Arrays.toString(arr));
    }
}
