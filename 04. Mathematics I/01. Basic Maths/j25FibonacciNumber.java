/**
 * Problem Statement:
 * 
 *     Given a number `n`, the task is to find the nth Fibonacci number. The Fibonacci sequence 
 *     is defined as follows:
 *     F(0) = 0, F(1) = 1, and F(n) = F(n-1) + F(n-2) for n ≥ 2.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 10^5), representing the position in the Fibonacci sequence.
 * 
 * Output:
 *     - The nth Fibonacci number.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     5
 * 
 *     Explanation:
 *     The Fibonacci sequence for n = 5 is: 0, 1, 1, 2, 3, 5. So, the 5th Fibonacci number is 5.
 * 
 */

import java.util.Scanner;

public class j25FibonacciNumber {
  public static void main(String args[]) {
    // Reading input
    Scanner in = new Scanner(System.in);
    int n = in.nextInt(); // Input: the position in the Fibonacci sequence
    System.out.println(nthFib(n)); // Print the nth Fibonacci number
    in.close(); // Close the scanner to avoid memory leaks
  }

  /**
   * Approach 1: Iterative Approach to Find the nth Fibonacci Number
   * 
   * Intuition:
   * - The Fibonacci sequence starts with F(0) = 0, F(1) = 1.
   * - We can compute the nth Fibonacci number iteratively by using two variables to store the 
   *   previous two Fibonacci numbers, iterating from 2 to n.
   * 
   * Time Complexity:
   * - O(N), as we perform N iterations to find the nth Fibonacci number.
   * 
   * Space Complexity:
   * - O(1), as we only use a few variables to store intermediate results.
   * 
   * @param n The position in the Fibonacci sequence.
   * @return The nth Fibonacci number.
   */
  public static int nthFib(int n) {
    if (n == 0)
      return 0; // F(0) = 0
    if (n == 1)
      return 1; // F(1) = 1

    int a = 0; // F(0)
    int b = 1; // F(1)
    int c = a + b; // F(2)

    for (int i = 2; i < n; i++) {
      a = b; // Update a to the previous Fibonacci number
      b = c; // Update b to the current Fibonacci number
      c = a + b; // Calculate the new Fibonacci number
    }

    return c; // Return the nth Fibonacci number
  }

  /**
   * Approach 2: Recursive Approach to Find the nth Fibonacci Number
   * 
   * Intuition:
   * - This approach calculates the nth Fibonacci number recursively.
   * - The function calls itself with the previous two numbers, F(n-1) and F(n-2), and adds them up.
   * - The base case is when n equals 1 or 2.
   * 
   * Time Complexity:
   * - O(2^N), as each function call branches into two more calls, resulting in an exponential growth 
   *   of the recursive calls. This approach is inefficient for large n.
   * 
   * Space Complexity:
   * - O(N), as there are N recursive calls on the stack for the worst case.
   * 
   * @param n The position in the Fibonacci sequence.
   * @return The nth Fibonacci number.
   */
  public static int nthFibRec(int n) {
    if (n == 1)
      return 0; // F(1) = 0
    if (n == 2)
      return 1; // F(2) = 1
    return nthFibRec(n - 1) + nthFibRec(n - 2); // Recursively sum the previous two Fibonacci numbers
  }
}
