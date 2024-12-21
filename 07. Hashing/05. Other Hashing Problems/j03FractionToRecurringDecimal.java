/**
 * Problem Statement:
 * 
 *     Given two integers representing the numerator and denominator of a fraction, 
 *     return the fraction in string format. If the fractional part is repeating, 
 *     enclose the repeating part in parentheses.
 * 
 * Input:
 *     - Two integers `numerator` and `denominator`, where:
 *         - The absolute value of the numerator and denominator is at most 10^9.
 *         - The denominator is non-zero.
 * 
 * Output:
 *     - The string representation of the fraction, with repeating decimals enclosed in parentheses.
 * 
 * Example:
 *     Input:
 *         4 333
 *     Output:
 *         "0.(012)"
 * 
 *     Explanation:
 *         The fraction 4/333 is equal to 0.012012012..., and the repeating part "012" 
 *         is enclosed in parentheses.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j03FractionToRecurringDecimal {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int den = in.nextInt();

        // Output the result of the fraction to decimal conversion
        System.out.println(fractionToDecimal(num, den));
        in.close();
    }

    /**
     * Approach: Using Long Division with HashMap to Detect Recurring Decimals
     * 
     * Intuition:
     * - We start by performing integer division to obtain the whole part of the fraction.
     * - For the decimal part, we multiply the remainder by 10 and perform long division step-by-step.
     * - If a remainder repeats (i.e., it is already seen), it indicates the start of a recurring decimal.
     * - To detect this, we use a HashMap to store the positions where each remainder first occurred. 
     * - Once a remainder repeats, we insert parentheses around the repeating decimal part.
     * - If the remainder becomes zero, the division terminates without repeating decimals.
     * 
     * Time Complexity:
     * - O(n), where `n` is the length of the repeating decimal part (in the worst case, it can be up to the 
     *   size of the denominator).
     * 
     * Space Complexity:
     * - O(n), for storing remainders in the HashMap.
     * 
     * @param numerator The numerator of the fraction.
     * @param denominator The denominator of the fraction.
     * @return The string representation of the fraction in decimal form, with repeating decimals in parentheses.
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder ans = new StringBuilder();

        // Handle the case where the numerator is zero
        if (numerator == 0)
            return "0";

        // Handle negative signs
        if ((numerator < 0) ^ (denominator < 0)) {
            ans.append("-");
        }

        // Convert both numerator and denominator to long for handling large values
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Append the integer part of the division to the answer
        ans.append(num / den);

        // Calculate the remainder after the integer division
        long r = num % den;

        // If there is no remainder, return the result
        if (r == 0) {
            return ans.toString();
        } else {
            // Append the decimal point to the result
            ans.append(".");

            // Create a HashMap to store the remainders and their positions in the result
            HashMap<Long, Integer> map = new HashMap<>();

            // Perform long division to calculate the decimal part
            while (r != 0) {
                // If the remainder is already seen, we have a repeating decimal
                if (map.containsKey(r)) {
                    ans.insert(map.get(r), "(");
                    ans.append(")");
                    break;
                } else {
                    // Store the current remainder and its position in the result
                    map.put(r, ans.length());

                    // Multiply the remainder by 10 and perform division
                    r *= 10;
                    ans.append(r / den);

                    // Update the remainder
                    r = r % denominator;
                }
            }
            return ans.toString();
        }
    }
}
