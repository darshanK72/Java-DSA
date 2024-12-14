/**
 * Problem Statement:
 * 
 *     Given two integers `n` and `r`, calculate the number of permutations of `r` items that can be selected 
 *     from `n` items. The formula for permutations is given by:
 *     P(n, r) = n!/(n - r)!
 * 
 * Input:
 *     - Two integers, `n` and `r` (0 <= r <= n <= 20), representing the total number of items and the number 
 *       of items to be selected.
 * 
 * Output:
 *     - The number of permutations of `r` items selected from `n` items.
 * 
 * Example:
 *     Input:
 *     5 3
 *     Output:
 *     60
 * 
 *     Explanation:
 *     The number of ways to arrange 3 items out of 5 items is 60, which is calculated as:
 *     P(5, 3) = 5!/(5 - 3)! = 120/2 = 60
 */

 import java.util.Scanner;
 
 public class j29Permutations {
 
     public static void main(String args[]) {
         // Reading input
         Scanner in = new Scanner(System.in);
         int n = in.nextInt(); // Input: total number of items (n)
         int r = in.nextInt(); // Input: number of items to select (r)
         
         // Call the method to calculate permutations
         System.out.println(n + "P" + r + " = " + nPr(n, r));
 
         // Close the scanner
         in.close();
     }
 
     /**
      * Approach 1: Permutation Calculation using Factorial
      * 
      * Intuition:
      * - To calculate the number of permutations of `r` items out of `n` items, we use the formula:
      *   P(n, r) = n!/(n - r)!
      * 
      * Time Complexity:
      * - O(n), because calculating factorial takes O(n) time for the `n!` and `(n - r)!`.
      * 
      * Space Complexity:
      * - O(1), as we are only using a fixed amount of space.
      * 
      * @param n The total number of items.
      * @param r The number of items to select.
      * @return The number of permutations.
      */
     public static int nPr(int n, int r) {
         return fact(n) / fact(n - r);
     }
 
     /**
      * Factorial Calculation using Recursion
      * 
      * Intuition:
      * - The factorial of a number `n` is the product of all positive integers less than or equal to `n`.
      * 
      * Time Complexity:
      * - O(n), because the recursive function is called `n` times.
      * 
      * Space Complexity:
      * - O(n), due to the recursive function calls.
      * 
      * @param n The number to calculate the factorial of.
      * @return The factorial of `n`.
      */
     public static int fact(int n) {
         if (n == 1 || n == 0) {
             return 1; // Base case: 0! and 1! both are 1
         }
         return n * fact(n - 1); // Recursive case: n! = n * (n-1)!
     }
 }
 