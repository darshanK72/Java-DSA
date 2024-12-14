/*-
 * Problem Statement:
 * 
 *     Given a non-negative integer `num`, repeatedly add all its digits until the result has only one digit.
 *     For example, if the input is `38`, the sum of digits is `3 + 8 = 11`, and the sum of digits of `11` is `1 + 1 = 2`. 
 *     The final result is `2`.
 *     You need to return the result after performing the operation of summing digits repeatedly until a single digit is obtained.
 * 
 * Input:
 *     - A non-negative integer `num` (0 <= num <= 2 * 10^8).
 * 
 * Output:
 *     - The final single digit obtained after repeatedly adding the digits of the number.
 * 
 * Example:
 *     Input:
 *     38
 *     Output:
 *     2
 * 
 *     Explanation:
 *     - The sum of digits of 38 is 11, and the sum of digits of 11 is 2, so the final result is 2.
 */

 import java.util.Scanner;

 public class j04AddDigits {
     public static void main(String args[]) {
         // Reading input
         Scanner in = new Scanner(System.in);
         int n = in.nextInt(); // Input: the number to be processed
         System.out.println(addDigits(n)); // Output: the final single-digit result
         in.close();
     }

     /*-
      * Approach 1: Iterative Approach (Sum of Digits)
      * 
      * Intuition:
      * - We can repeatedly calculate the sum of the digits of the number until we are left with a single digit.
      * - For each iteration, we calculate the sum of digits of the current number, and replace the number with this sum.
      * - Once the number becomes a single digit, we return it as the result.
      * 
      * Time Complexity:
      * - O(log(num)), as each iteration reduces the number of digits in the number.
      * 
      * Space Complexity:
      * - O(1), as we only need a few variables to store the sum of digits and the current number.
      * 
      * @param num The input number.
      * @return The final single digit obtained after repeated addition of digits.
      */
      public static int iterativeAddDigits(int num) {
        // Keep summing digits until num is a single digit
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;  // Add the last digit
                num /= 10;         // Remove the last digit
            }
            num = sum;  // Replace num with the sum of digits
        }
        return num;
    }
 
     /*-
      * Approach 2: Using Mathematical Formula (Digital Root)
      * 
      * Intuition:
      * - The operation of repeatedly adding digits can be reduced to a mathematical formula called the digital root.
      * - The digital root of a number can be calculated as `1 + (num - 1) % 9`. This is based on properties of numbers in base 9.
      * - If `num` is 0, the result is 0, otherwise we can directly calculate the result using this formula.
      * 
      * Time Complexity:
      * - O(1), since the result is computed in constant time using the formula.
      * 
      * Space Complexity:
      * - O(1), as no extra space is used aside from the input and output.
      * 
      * @param num The input number.
      * @return The final single digit obtained after repeated addition of digits.
      */
     public static int addDigits(int num) {
         // Using the digital root formula
         return 1 + (num - 1) % 9;
     }
 }
 