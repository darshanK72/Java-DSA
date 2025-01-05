/**
 * Problem Statement:
 * 
 *     A number is called a palindrome if it reads the same backward as forward. For example, 121 is a palindrome,
 *     but 123 is not a palindrome. The task is to determine whether the given number is a palindrome.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 10^6), representing the input number.
 * 
 * Output:
 *     - A string "Palindrome" if the number is a palindrome, otherwise "Not Palindrome".
 * 
 * Example:
 *     Input:
 *     121
 *     Output:
 *     Palindrome
 * 
 *     Explanation:
 *     - The number 121 is the same when reversed, so the output is "Palindrome".
 * 
 *     Input:
 *     123
 *     Output:
 *     Not Palindrome
 * 
 *     Explanation:
 *     - The number 123 is not the same when reversed, so the output is "Not Palindrome".
 */

import java.util.Scanner;

public class j09PalindromeNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input number n
        // Check if the number is palindrome and print the result
        if (isPalindrome(n)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Reverse the Number and Compare
     * 
     * Intuition:
     * - A number is a palindrome if it is equal to its reverse. 
     * - We reverse the digits of the number and check if the reversed number is the same as the original number.
     * 
     * Time Complexity:
     * - O(log10 N), as we reverse the number by processing each of its digits.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space for storing variables like `reverse` and `temp`.
     * 
     * @param n The input number.
     * @return `true` if the number is a palindrome, `false` otherwise.
     */
    public static boolean isPalindrome(int n) {
        int temp = n; // Store the original number
        int reverse = 0; // Variable to store the reversed number
        while (n > 0) {
            reverse = reverse * 10 + n % 10; // Reverse the number
            n /= 10; // Reduce the number
        }
        return reverse == temp; // Check if the reversed number equals the original number
    }
}
