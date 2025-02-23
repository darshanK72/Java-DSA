/**
 * Problem Statement:
 * 
 *     Given a non-negative integer `N`, compute the factorial of `N` and return the result as an array of integers, where each element represents a digit of the factorial number.
 *     Since the factorial of large numbers grows rapidly, the result is returned as an array of digits.
 * 
 * Input:
 *     - A single integer `N` (0 <= N <= 1000), representing the number for which the factorial is to be calculated.
 * 
 * Output:
 *     - An array of integers representing the digits of the factorial of `N`.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     [1, 2, 0]
 * 
 *     Explanation:
 *     The factorial of 5 is 120. Therefore, the output is [1, 2, 0].
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class j05FactorialOfLargeNumber {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Input the number for which factorial is to be calculated
        // Calling the factorial method and printing the result
        ArrayList<Integer> result = factorial(N);
        // Printing the result as an array of digits
        for (int digit : result) {
            System.out.print(digit); // Print each digit of the factorial
        }
        System.out.println(); // Print a newline at the end
        in.close();
    }

    /**
     * Approach: Factorial Calculation using BigInteger
     * 
     * Intuition:
     * - Factorial of a number grows rapidly, and for large numbers, the result cannot be stored in standard integer data types. 
     * - We use `BigInteger` class to handle large numbers. The `BigInteger` class supports arbitrary-precision arithmetic, allowing us to compute the factorial of large numbers.
     * - After computing the factorial, the result is converted to a string and each digit is extracted and stored in an ArrayList for the final output.
     * 
     * Time Complexity:
     * - O(N), where `N` is the input number. We iterate from 1 to `N` to compute the factorial.
     * 
     * Space Complexity:
     * - O(log(N!)), which is the number of digits in the factorial. This is because we store each digit of the factorial as an integer in an ArrayList.
     * 
     * @param N The number for which the factorial is to be calculated.
     * @return An ArrayList containing the digits of the factorial of `N`.
     */
    static ArrayList<Integer> factorial(int N) {
        BigInteger fact = new BigInteger("1"); // Initialize the factorial result as 1
        // Loop to calculate factorial by multiplying the numbers
        for (int i = 1; i <= N; i++) {
            fact = fact.multiply(BigInteger.valueOf(i)); // Multiply the current number to the result
        }
        // Convert the factorial to string to extract each digit
        String f = fact.toString();
        ArrayList<Integer> out = new ArrayList<Integer>();
        // Extract each digit of the factorial and store it in the ArrayList
        for (int i = 0; i < f.length(); i++) {
            out.add(f.charAt(i) - '0'); // Convert char to integer and add it to the list
        }
        return out; // Return the list of digits
    }
}
