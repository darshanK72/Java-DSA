/** 
 * Problem Statement:
 * 
 *     A self-dividing number is a number that is divisible by each of its digits. For example, 128 is a self-dividing 
 *     number because 128 is divisible by 1, 2, and 8. The task is to find all the self-dividing numbers in a given range [left, right].
 * 
 * Input:
 *     - Two integers `left` and `right` (1 <= left <= right <= 10^6), representing the inclusive range.
 * 
 * Output:
 *     - A list of all self-dividing numbers between `left` and `right`.
 * 
 * Example:
 *     Input:
 *     1 22
 *     Output:
 *     [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 
 *     Explanation:
 *     - The self-dividing numbers between 1 and 22 are [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22].
 * 
 *     Input:
 *     47 85
 *     Output:
 *     [48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 62, 64, 66, 68, 69, 72, 74, 75, 76, 77, 78, 79, 80, 81, 82, 84]
 * 
 *     Explanation:
 *     - The self-dividing numbers between 47 and 85 are [48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 62, 64, 66, 68, 69, 72, 74, 75, 76, 77, 78, 79, 80, 81, 82, 84].
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j10SelfDividingNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int left = in.nextInt(); // Left boundary of the range
        int right = in.nextInt(); // Right boundary of the range
        // Call the method and print the list of self-dividing numbers
        System.out.println(selfDividingNumbers(left, right));
        // Close the scanner
        in.close();
    }

    /** 
     * Approach: Iterate through the given range and check for self-dividing numbers.
     * 
     * Intuition:
     * - For each number in the range, we check if each digit of the number divides the number evenly. 
     * - If any digit is zero or doesn't divide the number, it is not a self-dividing number.
     * 
     * Time Complexity:
     * - O(N * D), where N is the length of the range [left, right], and D is the average number of digits in the numbers.
     * 
     * Space Complexity:
     * - O(N), as we store the self-dividing numbers in an output list.
     * 
     * @param left The left boundary of the range.
     * @param right The right boundary of the range.
     * @return A list of self-dividing numbers in the given range.
     */
    public static List<Integer> selfDividingNumbers(int left, int right) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            int x = i;
            while (x > 0) {
                // Check if the digit is 0 or the number is not divisible by the digit
                if ((x % 10) == 0 || i % (x % 10) != 0)
                    break;
                x /= 10;
            }
            // If the number is divisible by all its digits, add it to the output list
            if (x == 0)
                output.add(i);
        }
        return output;
    }
}
