/**
 * Problem Statement:
 * 
 *     Given a number n, generate the n-bit Gray code sequence. In a Gray code sequence, two successive 
 *     numbers differ in only one bit.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 16), representing the number of bits.
 * 
 * Output:
 *     - A list of Gray codes in either binary string format or integer format.
 * 
 * Example:
 *     Input:
 *     2
 *     Output:
 *     [0, 1, 3, 2]
 * 
 *     Explanation:
 *     The Gray code sequence for 2 bits is [0, 1, 3, 2], which corresponds to the binary representations
 *     ["00", "01", "11", "10"].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class j01GenerateGrayCode {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(generateGrayCode1(n));
        System.out.println(generateGrayCode2(n));
        in.close();
    }

    /**
     * Approach 1: Recursion with Binary Strings
     * 
     * Intuition:
     * - The Gray code sequence can be constructed recursively. For a given n, the sequence for n bits 
     *   can be built from the sequence for n-1 bits.
     * - Prepend "0" to the first half of the sequence and "1" to the second half (in reverse order).
     * 
     * Time Complexity:
     * - O(2^n), where n is the number of bits. This is because there are 2^n Gray codes to generate.
     * 
     * Space Complexity:
     * - O(2^n), for storing the sequence of Gray codes.
     * 
     * @param n The number of bits for the Gray code sequence.
     * @return A list of binary strings representing the Gray code sequence.
     */
    public static ArrayList<String> generateGrayCode1(int n) {
        if (n == 1)
            return new ArrayList<String>(Arrays.asList("0", "1"));

        ArrayList<String> temp = generateGrayCode1(n - 1);
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            out.add("0" + temp.get(i));
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            out.add("1" + temp.get(i));
        }
        return out;
    }

    /**
     * Approach 2: Recursion with Integers
     * 
     * Intuition:
     * - The Gray code sequence for integers can be generated using bitwise operations. The nth Gray code 
     *   is calculated as n ^ (n >> 1).
     * - For each recursive step, the sequence for n-1 is generated and then adjusted using bitwise 
     *   operations.
     * 
     * Time Complexity:
     * - O(2^n), where n is the number of bits. The time to generate each Gray code is constant.
     * 
     * Space Complexity:
     * - O(2^n), for storing the sequence of Gray codes.
     * 
     * @param n The number of bits for the Gray code sequence.
     * @return A list of integers representing the Gray code sequence.
     */
    public static ArrayList<Integer> generateGrayCode2(int n) {
        if (n == 1) {
            return new ArrayList<>(Arrays.asList(0, 1));
        } else {

            ArrayList<Integer> out = generateGrayCode2(n - 1);
            for (int i = out.size() - 1; i >= 0; i--) {
                out.add(out.get(i) | (1 << (n - 1)));
            }
            return out;
        }
    }
}
